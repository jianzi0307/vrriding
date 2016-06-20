package com.pkuvr.game_server.service;

import com.pkuvr.game_server.cache.PlayerRealtimePvpCacheManager;
import com.pkuvr.game_server.cache.PlayerRoleCacheManager;
import com.pkuvr.game_server.cachevo.PlayerRealtimePvpVO;
import com.pkuvr.game_server.cachevo.PlayerRoleVO;
import com.pkuvr.game_server.constant.AppConfig;
import com.pkuvr.game_server.dao.DichonorrewardMapper;
import com.pkuvr.game_server.dao.RealtimepvpnpchistoryMapper;
import com.pkuvr.game_server.dao.RealtimepvpplayerhistoryMapper;
import com.pkuvr.game_server.dao.RealtimepvptokenhistoryMapper;
import com.pkuvr.game_server.domain.*;
import com.pkuvr.game_server.enumerate.TaskTypeCode;
import com.pkuvr.game_server.exception.*;
import com.pkuvr.game_server.network.GameServerSendService;
import com.pkuvr.game_server.proto.serverproto.Pvp_Battle_Start_Response.PvpBattleTypeEnum;
import com.pkuvr.game_server.redis.BaseZSetCpt;
import com.pkuvr.game_server.vo.*;
import javolution.util.FastMap;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.Future;

@Service("RealTimePvpService")
public class RealTimePvpService {
    private static final Logger logger = Logger.getLogger(RealTimePvpService.class);
    @Resource
    private PlayerRoleCacheManager playerRoleCacheManager;
    @Resource
    private PlayerRealtimePvpCacheManager playerRealtimePvpCacheManager;
    @Resource
    private DichonorrewardMapper dichonorrewardMapper;
    @Resource
    private RealtimepvpplayerhistoryMapper realtimepvpplayerhistoryMapper;
    @Resource
    private RealtimepvpnpchistoryMapper realtimepvpnpchistoryMapper;
    @Resource
    private RealtimepvptokenhistoryMapper realtimepvptokenhistoryMapper;
    @Resource
    private SendService sendService;
    @Resource
    private BaseZSetCpt pvpRoomZSetCpt; //pvp房间玩家排名
    @Resource
    private BaseZSetCpt pvpMatchZSetCpt; //pvp房间开始对战匹配玩家排名
    @Resource
    private Properties serverConfig;
    @Resource
    private ThreadPoolTaskScheduler pvpDeployTaskExecutor;

    // 布阵
    private FastMap<String, Future<?>> deployTasksMap = new FastMap<String, Future<?>>().shared();

    private GameServerSendService gameServerSendService = GameServerSendService.getInstance();

    /**
     * 进入pvp room
     *
     * @param roleId 玩家id
     * @param
     * @return
     * @throws
     * @throws
     * @throws
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public PvpRoomVO enterPvpRoom(int roleId) throws NoSuchRoleException {
        PvpRoomVO pvpRoomVO = new PvpRoomVO();
        if (roleId < 0)
            throw new NoSuchRoleException();

        List<PvpRankInfoVO> rankInfoList = new ArrayList<PvpRankInfoVO>();
        List<Integer> rankList = pvpRoomZSetCpt.get(0, 99);

        for (int i = 1; i < rankList.size() + 1; i++) {
            int rankRoleId = rankList.get(i - 1);
            PlayerRoleVO rankRoleVO = playerRoleCacheManager.get(rankRoleId);
            if (rankRoleVO != null) {
                PvpRankInfoVO pvpRankInfoVO = new PvpRankInfoVO();
                pvpRankInfoVO.setPvpRank(i);
                pvpRankInfoVO.setRoleId(rankRoleId);
                pvpRankInfoVO.setRoleName(rankRoleVO.getRoleName());
                pvpRankInfoVO.setCampId(rankRoleVO.getCampId());
                pvpRankInfoVO.setGeneralDegree(rankRoleVO.getRoleLevel());
                pvpRankInfoVO.setAvatar(rankRoleVO.getRoleAvatar());
                pvpRankInfoVO.setHonor(rankRoleVO.getRoleHonor());
                rankInfoList.add(pvpRankInfoVO);
            }
        }

        int roleRank = pvpRoomZSetCpt.getRank(roleId) + 1;
        PlayerRoleVO playerRoleVO = playerRoleCacheManager.get(roleId);
        if (playerRoleVO != null) {
            PvpRankInfoVO ownRankInfoVO = new PvpRankInfoVO();
            ownRankInfoVO.setPvpRank(roleRank);
            ownRankInfoVO.setRoleId(roleId);
            ownRankInfoVO.setRoleName(playerRoleVO.getRoleName());
            ownRankInfoVO.setCampId(playerRoleVO.getCampId());
            ownRankInfoVO.setGeneralDegree(playerRoleVO.getRoleLevel());
            ownRankInfoVO.setAvatar(playerRoleVO.getRoleAvatar());
            ownRankInfoVO.setHonor(playerRoleVO.getRoleHonor());

            pvpRoomVO.setRankInfoList(rankInfoList);
            pvpRoomVO.setOwnRankInfo(ownRankInfoVO);
        }

        return pvpRoomVO;
    }

    /**
     * @param roleId 玩家id
     * @param
     * @return
     * @throws SeaException pvp匹配
     * @throws
     * @throws
     * @throws
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public PvpMatchVO pvpMatch(int roleId, String pvpMap) throws NoSuchRoleException, TaskDataException, PvpMatchOverTimesException, SeaException {
        PvpMatchVO pvpMatchVO = new PvpMatchVO();
        if (roleId < 0)
            throw new NoSuchRoleException();


        pvpMatchZSetCpt.deleteById(roleId);
        playerRealtimePvpCacheManager.remove(roleId);

        PlayerRealtimePvpVO playerRealtimePvpVOCheck = playerRealtimePvpCacheManager.get(roleId);
        if (playerRealtimePvpVOCheck == null) {
            PlayerRoleVO playerRoleVO = playerRoleCacheManager.get(roleId);
            pvpMatchZSetCpt.saveOrUpdate(roleId, playerRoleVO.getRoleHonor());
            RealTimePvpTimeService.addPvpMatchTimeToMap(roleId, System.currentTimeMillis());

            int matchRoleId = 0;
            for (int i = 0; i < 3; i++) {
                matchRoleId = getMatchRoleId(roleId, playerRoleVO);
                if (matchRoleId > 0) {
                    break;
                }
            }

            if (matchRoleId > 0) {
                // 每天同一玩家只能打3次
                RealtimepvpplayerhistoryKey realtimepvpplayerhistoryKey = new RealtimepvpplayerhistoryKey();
                realtimepvpplayerhistoryKey.setRoleid(roleId);
                realtimepvpplayerhistoryKey.setAttackeeroleid(matchRoleId);
                Realtimepvpplayerhistory realtimepvpplayerhistory = realtimepvpplayerhistoryMapper.selectByPrimaryKey(realtimepvpplayerhistoryKey);
                if (realtimepvpplayerhistory != null) {
                    if (realtimepvpplayerhistory.getAttacktimes() < Short.valueOf(serverConfig.getProperty(AppConfig.PVP_PLAYER_MAX_TIMES))) {
                        realtimepvpplayerhistory.setAttacktimes((short) (realtimepvpplayerhistory.getAttacktimes() + 1));
                        realtimepvpplayerhistoryMapper.updateByPrimaryKeySelective(realtimepvpplayerhistory);
                    }
                } else {
                    Realtimepvpplayerhistory insertRecord = new Realtimepvpplayerhistory();
                    insertRecord.setRoleid(roleId);
                    insertRecord.setAttackeeroleid(matchRoleId);
                    insertRecord.setAttacktimes((short) 1);
                    realtimepvpplayerhistoryMapper.insert(insertRecord);
                }

                PvpRankInfoVO ownRankInfoVO = new PvpRankInfoVO();
                int roleRank = pvpRoomZSetCpt.getRank(roleId);
                ownRankInfoVO.setPvpRank(roleRank);
                ownRankInfoVO.setRoleId(roleId);
                ownRankInfoVO.setRoleName(playerRoleVO.getRoleName());
                ownRankInfoVO.setCampId(playerRoleVO.getCampId());
                ownRankInfoVO.setGeneralDegree(playerRoleVO.getRankLevel());
                ownRankInfoVO.setAvatar(playerRoleVO.getRoleAvatar());
                ownRankInfoVO.setHonor(playerRoleVO.getRoleHonor());
                pvpMatchVO.setOwnPlayerRank(ownRankInfoVO);

                PlayerRoleVO matchRoleVO = playerRoleCacheManager.get(matchRoleId);
                PvpRankInfoVO matchPvpRankInfoVO = new PvpRankInfoVO();
                int matchRoleRank = pvpRoomZSetCpt.getRank(matchRoleId);
                matchPvpRankInfoVO.setPvpRank(matchRoleRank);
                matchPvpRankInfoVO.setRoleId(matchRoleId);
                matchPvpRankInfoVO.setRoleName(matchRoleVO.getRoleName());
                matchPvpRankInfoVO.setCampId(matchRoleVO.getCampId());
                matchPvpRankInfoVO.setGeneralDegree(matchRoleVO.getRankLevel());
                matchPvpRankInfoVO.setAvatar(matchRoleVO.getRoleAvatar());
                matchPvpRankInfoVO.setHonor(matchRoleVO.getRoleHonor());
                pvpMatchVO.setOtherPlayerRank(matchPvpRankInfoVO);

                int honorSub = playerRoleVO.getRoleHonor() - matchRoleVO.getRoleHonor();
                DichonorrewardExample dichonorrewardExample = new DichonorrewardExample();
                dichonorrewardExample.createCriteria()
                        .andBeginhonorLessThanOrEqualTo(playerRoleVO.getRoleHonor())
                        .andEndhonorGreaterThanOrEqualTo(playerRoleVO.getRoleHonor())
                        .andBeginvalueLessThanOrEqualTo(honorSub)
                        .andEndvalueGreaterThanOrEqualTo(honorSub);
                List<Dichonorreward> honorRewardList = dichonorrewardMapper.selectByExample(dichonorrewardExample);
                Dichonorreward dichonorreward = honorRewardList.get(0);
                pvpMatchVO.setOwnRewardHonor(dichonorreward.getRewardhonor());
                pvpMatchVO.setOwnSubHonor(dichonorreward.getSubhonor());

                int honorSubOther = matchRoleVO.getRoleHonor() - playerRoleVO.getRoleHonor();
                DichonorrewardExample dichonorrewardExampleOther = new DichonorrewardExample();
                dichonorrewardExampleOther.createCriteria()
                        .andBeginhonorLessThanOrEqualTo(matchRoleVO.getRoleHonor())
                        .andEndhonorGreaterThanOrEqualTo(matchRoleVO.getRoleHonor())
                        .andBeginvalueLessThanOrEqualTo(honorSubOther)
                        .andEndvalueGreaterThanOrEqualTo(honorSubOther);
                List<Dichonorreward> honorRewardListOther = dichonorrewardMapper.selectByExample(dichonorrewardExampleOther);
                Dichonorreward dichonorrewardOther = honorRewardListOther.get(0);
                pvpMatchVO.setOtherRewardHonor(dichonorrewardOther.getRewardhonor());
                pvpMatchVO.setOtherSubHonor(dichonorrewardOther.getSubhonor());

                // 计算匹配时间
                long matchRoleTime = RealTimePvpTimeService.getPvpMatchTimeFromMap(matchRoleId);
                int matchTimeSecond = (int) ((System.currentTimeMillis() - matchRoleTime) * 0.001);
                logger.info("matchTimeSecond === " + matchTimeSecond);
                if (matchTimeSecond < 60)
                    RealTimePvpTimeService.addPvpMatchTime(matchTimeSecond);

                // 从pvpMatchZSetCpt中删除掉匹配成功的玩家
                pvpMatchZSetCpt.deleteById(roleId);
                pvpMatchZSetCpt.deleteById(matchRoleId);
                RealTimePvpTimeService.removePvpMatchTimeFromMap(roleId);
                RealTimePvpTimeService.removePvpMatchTimeFromMap(matchRoleId);
                playerRealtimePvpCacheManager.remove(roleId);
                playerRealtimePvpCacheManager.remove(matchRoleId);

                // 匹配成功后将匹配成功的roleid写入本地缓存
                String deployRoomId = roleId + "#" + matchRoleId;
                PlayerRealtimePvpVO ownRealtimePvpVO = new PlayerRealtimePvpVO();
                ownRealtimePvpVO.setOtherRoleId(matchRoleId);
                ownRealtimePvpVO.setRewardHonor(pvpMatchVO.getOwnRewardHonor());
                ownRealtimePvpVO.setSubHonor(pvpMatchVO.getOwnSubHonor());
                ownRealtimePvpVO.setPvpMap(pvpMap);
                ownRealtimePvpVO.setBattleEnd(false);
                ownRealtimePvpVO.setRewardHonor(false);
                ownRealtimePvpVO.setDeployRoomId(deployRoomId);
                playerRealtimePvpCacheManager.put(roleId, ownRealtimePvpVO);

                PlayerRealtimePvpVO playerRealtimePvpVO = new PlayerRealtimePvpVO();
                playerRealtimePvpVO.setOtherRoleId(roleId);
                playerRealtimePvpVO.setRewardHonor(pvpMatchVO.getOtherRewardHonor());
                playerRealtimePvpVO.setSubHonor(pvpMatchVO.getOtherSubHonor());
                playerRealtimePvpVO.setPvpMap(pvpMap);
                playerRealtimePvpVO.setBattleEnd(false);
                playerRealtimePvpVO.setRewardHonor(false);
                playerRealtimePvpVO.setDeployRoomId(deployRoomId);
                playerRealtimePvpCacheManager.put(matchRoleId, playerRealtimePvpVO);

                // 启动45秒后执行的定时任务
                Date startDate = new Date(System.currentTimeMillis() + 45000L);
                Future<?> pvpDeployScheduledFuture = pvpDeployTaskExecutor.schedule(new PvpDeployVerifyTask(roleId), startDate);
                deployTasksMap.put(deployRoomId, pvpDeployScheduledFuture);

                long deployEndTime = System.currentTimeMillis() + 45000L;

                sendService.sendPvpMatchSuccess(roleId, ownRankInfoVO, matchPvpRankInfoVO,
                        pvpMatchVO.getOwnRewardHonor(), pvpMatchVO.getOwnSubHonor(), false, deployEndTime, pvpMap, false);

                sendService.sendPvpMatchSuccess(matchRoleId, matchPvpRankInfoVO, ownRankInfoVO,
                        pvpMatchVO.getOtherRewardHonor(), pvpMatchVO.getOtherSubHonor(), true, deployEndTime, pvpMap, false);

                sendService.sendCrossAddTaskFinish(matchRoleId, TaskTypeCode.ATHLETICS_NUM.getType());
                sendService.sendCrossAddTaskFinish(roleId, TaskTypeCode.ATHLETICS_NUM.getType());

                pvpMatchVO.setMatchResult(true);
            } else if (!gameServerSendService.isOnline(matchRoleId)) {
                pvpMatchZSetCpt.deleteById(matchRoleId);
                pvpMatchVO.setMatchResult(false);
            } else {
                pvpMatchVO.setMatchResult(false);
            }
        }
        pvpMatchVO.setMatchTime(RealTimePvpTimeService.getAvgPvpMatchTime());
        pvpMatchVO.setNpcMatchTime(RealTimePvpTimeService.getAvgNpcMatchTime());
        return pvpMatchVO;
    }

    /**
     * 取消pvp匹配
     *
     * @param roleId 玩家id
     * @param
     * @return
     * @throws
     * @throws
     * @throws
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void pvpMatchCancel(int roleId) throws NoSuchRoleException {
        if (roleId < 0)
            throw new NoSuchRoleException();
        // 从pvpMatchZSetCpt中删除掉玩家
        pvpMatchZSetCpt.deleteById(roleId);
        RealTimePvpTimeService.removePvpMatchTimeFromMap(roleId);
    }

    /**
     * pvp中玩家断线处理
     *
     * @param roleId 玩家id
     * @param
     * @return
     * @throws
     * @throws
     * @throws
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void pvpDisconnect(int roleId) throws NoSuchRoleException {
        PlayerRealtimePvpVO playerRealtimePvpVO = playerRealtimePvpCacheManager.get(roleId);
        PlayerRealtimePvpVO otherRealtimePvpVO = null;
        if (playerRealtimePvpVO != null) {
            if (playerRealtimePvpVO.getDeployRoomId() != null) {
                if (deployTasksMap.get(playerRealtimePvpVO.getDeployRoomId()) != null) {
                    Future<?> pvpDeployScheduledFuture = deployTasksMap.remove(playerRealtimePvpVO.getDeployRoomId());
                    if (pvpDeployScheduledFuture != null)
                        pvpDeployScheduledFuture.cancel(false);
                }
            }

            otherRealtimePvpVO = playerRealtimePvpCacheManager.get(playerRealtimePvpVO.getOtherRoleId());
            if (!playerRealtimePvpVO.isRewardHonor()) {
                sendService.sendCrossAddHonor(roleId, playerRealtimePvpVO.getSubHonor());
                addPlayerToken(roleId, AppConfig.PVP_FAIL_HONOR_TOKEN);
            }
            logger.info("before roleId=======" + roleId);
            playerRealtimePvpCacheManager.remove(roleId);
            pvpMatchZSetCpt.deleteById(roleId);
            logger.info(" end roleId=======" + roleId);
            if (otherRealtimePvpVO != null) {
                otherRealtimePvpVO.setRewardHonor(true);
                otherRealtimePvpVO.setBattleResult(AppConfig.BATTLE_SUCCESS_RESULT);
                playerRealtimePvpCacheManager.put(playerRealtimePvpVO.getOtherRoleId(), otherRealtimePvpVO);

                PlayerRoleVO playerRoleVO = playerRoleCacheManager.get(roleId);
                sendService.sendPvpDisconnect(playerRealtimePvpVO.getOtherRoleId(), roleId, playerRoleVO.getRoleName(),
                        AppConfig.BATTLE_SUCCESS_RESULT, otherRealtimePvpVO.getRewardHonor());
            }
        } else {
            pvpMatchZSetCpt.deleteById(roleId);
        }

    }

    /**
     * 实时pvp战前部署
     *
     * @param roleId 玩家id
     * @param
     * @return
     * @throws
     * @throws
     * @throws
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public PvpBattleUnitDeployVO battleUnitDeploy(final int roleId, int battleUnitIndex, List<PvpBattleUnitVO> battleUnitList, String landMineInfo)
            throws NoSuchRoleException, BattleUnitNumberZeroException, BattleUnitNotExistException,
            BattleUnitNotEnoughException, PvpOtherNotOnlineException {
        PvpBattleUnitDeployVO pvpBattleUnitDeployVO = new PvpBattleUnitDeployVO();
        if (roleId < 0)
            throw new NoSuchRoleException();
        if ((battleUnitIndex != 5) && ((battleUnitList == null) || (battleUnitList.size() == 0)))
            throw new BattleUnitNumberZeroException();

        Map<Integer, Integer> battleUnitNumMap = new HashMap<Integer, Integer>();
        for (PvpBattleUnitVO pvpBattleUnitVO : battleUnitList) {
            if (battleUnitNumMap.get(pvpBattleUnitVO.getBattleUnitID()) != null) {
                battleUnitNumMap.put(pvpBattleUnitVO.getBattleUnitID(),
                        battleUnitNumMap.get(pvpBattleUnitVO.getBattleUnitID()) + 1);
            } else {
                battleUnitNumMap.put(pvpBattleUnitVO.getBattleUnitID(), 1);
            }
        }

        PlayerRoleVO playerRoleVO = playerRoleCacheManager.get(roleId);
        Map<Integer, BattleUnitVO> battleUnitMap = playerRoleVO.getBattleUnitMap();
        for (PvpBattleUnitVO pvpBattleUnitVO : battleUnitList) {
            BattleUnitVO playerBattleUnitVO = battleUnitMap.get(pvpBattleUnitVO.getBattleUnitID());
            if (playerBattleUnitVO == null) {
                throw new BattleUnitNotExistException();
            } else {
                if ((battleUnitNumMap.get(pvpBattleUnitVO.getBattleUnitID()) != null) &&
                        (battleUnitNumMap.get(pvpBattleUnitVO.getBattleUnitID()) > playerBattleUnitVO.getStorageNum()))
                    throw new BattleUnitNotEnoughException();
            }
        }

        PlayerRealtimePvpVO ownRealtimePvpVO = playerRealtimePvpCacheManager.get(roleId);
        if (ownRealtimePvpVO != null) {
            int otherRoleId = ownRealtimePvpVO.getOtherRoleId();
            PlayerRealtimePvpVO playerRealtimePvpVO = playerRealtimePvpCacheManager.get(otherRoleId);
            if (playerRealtimePvpVO != null) {
                int otherBattleUnitIndex = playerRealtimePvpVO.getBattleUnitIndex();
                logger.info("roleId======" + roleId + "    otherRoleId==========" + otherRoleId + "    otherBattleUnitIndex=========" + otherBattleUnitIndex +
                        "        battleUnitIndex=========" + battleUnitIndex);
                // 通过对手部署作战单位列表的大小来判断对手是否已经部署成功
                if ((battleUnitIndex == 5) && (otherBattleUnitIndex == 5) &&
                        (!ownRealtimePvpVO.isBattleStart()) && (!playerRealtimePvpVO.isBattleStart())) {
                    List<PvpBattleUnitVO> ownBattleUnitList = new ArrayList<PvpBattleUnitVO>();
                    List<PvpBattleUnitVO> otherBattleUnitList = new ArrayList<PvpBattleUnitVO>();
                    if (ownRealtimePvpVO.getPlayerBattleUnitList1().size() > 0)
                        ownBattleUnitList.addAll(ownRealtimePvpVO.getPlayerBattleUnitList1());
                    if (ownRealtimePvpVO.getPlayerBattleUnitList2().size() > 0)
                        ownBattleUnitList.addAll(ownRealtimePvpVO.getPlayerBattleUnitList2());
                    if (ownRealtimePvpVO.getPlayerBattleUnitList3().size() > 0)
                        ownBattleUnitList.addAll(ownRealtimePvpVO.getPlayerBattleUnitList3());
                    if (ownRealtimePvpVO.getPlayerBattleUnitList4().size() > 0)
                        ownBattleUnitList.addAll(ownRealtimePvpVO.getPlayerBattleUnitList4());

                    if (playerRealtimePvpVO.getPlayerBattleUnitList1().size() > 0)
                        otherBattleUnitList.addAll(playerRealtimePvpVO.getPlayerBattleUnitList1());
                    if (playerRealtimePvpVO.getPlayerBattleUnitList2().size() > 0)
                        otherBattleUnitList.addAll(playerRealtimePvpVO.getPlayerBattleUnitList2());
                    if (playerRealtimePvpVO.getPlayerBattleUnitList3().size() > 0)
                        otherBattleUnitList.addAll(playerRealtimePvpVO.getPlayerBattleUnitList3());
                    if (playerRealtimePvpVO.getPlayerBattleUnitList4().size() > 0)
                        otherBattleUnitList.addAll(playerRealtimePvpVO.getPlayerBattleUnitList4());

                    pvpBattleUnitDeployVO.setDeployResult(1);
                    pvpBattleUnitDeployVO.setOtherRoleId(otherRoleId);
                    pvpBattleUnitDeployVO.setPlayerBattleUnitList(ownBattleUnitList);
                    pvpBattleUnitDeployVO.setOtherBattleUnitList(otherBattleUnitList);
                    //判断该玩家是否在线
                    if (!gameServerSendService.isOnline(otherRoleId)) {
                        throw new PvpOtherNotOnlineException();
                    }

                    // 部署成功后向两个客户端下发开战通知和战斗单位
                    final String battleRoomId = roleId + "#" + otherRoleId;
                    ownRealtimePvpVO.setBattleStart(true);
                    playerRealtimePvpVO.setBattleStart(true);
                    ownRealtimePvpVO.setBattleRoomId(battleRoomId);
                    playerRealtimePvpVO.setBattleRoomId(battleRoomId);

                    playerRealtimePvpCacheManager.put(roleId, ownRealtimePvpVO);
                    playerRealtimePvpCacheManager.put(otherRoleId, playerRealtimePvpVO);

                    logger.info("-----------------playerRealtimePvpVO.getLandMineInfo() === " + playerRealtimePvpVO.getLandMineInfo());
                    sendService.sendPvpBattleStart(roleId, otherRoleId, playerRoleCacheManager.get(otherRoleId).getCampId(), playerRoleCacheManager.get(otherRoleId).getRoleAvatar(), 0, otherBattleUnitList, playerRealtimePvpVO.getLandMineInfo(), PvpBattleTypeEnum.COMMON, false);
                    logger.info("-----------------landMineInfo ===== " + landMineInfo);
                    sendService.sendPvpBattleStart(otherRoleId, roleId, playerRoleCacheManager.get(roleId).getCampId(), playerRoleCacheManager.get(roleId).getRoleAvatar(), 0, ownBattleUnitList, landMineInfo, PvpBattleTypeEnum.COMMON, false);

                    // 正式开战时停止布阵检查定时任务
                    if (ownRealtimePvpVO.getDeployRoomId() != null) {
                        if (deployTasksMap.get(ownRealtimePvpVO.getDeployRoomId()) != null) {
                            Future<?> pvpDeployScheduledFuture = deployTasksMap.remove(ownRealtimePvpVO.getDeployRoomId());
                            if (pvpDeployScheduledFuture != null)
                                pvpDeployScheduledFuture.cancel(false);
                        }
                    }

                } else {
                    pvpBattleUnitDeployVO.setDeployResult(-1);
                    if (battleUnitIndex == 1) {
                        ownRealtimePvpVO.setPlayerBattleUnitList1(battleUnitList);
                    } else if (battleUnitIndex == 2) {
                        ownRealtimePvpVO.setPlayerBattleUnitList2(battleUnitList);
                    } else if (battleUnitIndex == 3) {
                        ownRealtimePvpVO.setPlayerBattleUnitList3(battleUnitList);
                    } else if (battleUnitIndex == 4) {
                        ownRealtimePvpVO.setPlayerBattleUnitList4(battleUnitList);
                    } else if (battleUnitIndex == 5) {
                        ownRealtimePvpVO.setLandMineInfo(landMineInfo);
                    }
                    ownRealtimePvpVO.setBattleUnitIndex(battleUnitIndex);
                    ownRealtimePvpVO.setBattleStart(false);
                    playerRealtimePvpCacheManager.put(roleId, ownRealtimePvpVO);
                }
            }
        }
        return pvpBattleUnitDeployVO;
    }

    /**
     * pvp同步死亡
     *
     * @param roleId 玩家id
     * @param
     * @return
     * @throws
     * @throws
     * @throws
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public long syncBattleUnitDead(int roleId, int otherRoleId, List<Integer> deadBattleUnitList, long clientTime)
            throws NoSuchRoleException, PvpOtherNotOnlineException {
        // 服务器根据两个客户端的最慢网络延时下发最终的客户端执行时间戳
        long serverTime = System.currentTimeMillis();

        if ((roleId < 0) || (otherRoleId < 0))
            throw new NoSuchRoleException();

        long newDelay = serverTime - clientTime;
        logger.info("---syncBattleUnitDead------------------newDelay ====== " + newDelay);
        PlayerRealtimePvpVO ownRealtimePvpVO = playerRealtimePvpCacheManager.get(roleId);
        if (ownRealtimePvpVO != null) {
            ownRealtimePvpVO.setDelayTime(newDelay);
            playerRealtimePvpCacheManager.put(roleId, ownRealtimePvpVO);
        }

        PlayerRealtimePvpVO playerRealtimePvpVO = playerRealtimePvpCacheManager.get(otherRoleId);
        if (playerRealtimePvpVO != null) {
            long otherDeloy = playerRealtimePvpVO.getDelayTime();
            if (otherDeloy > newDelay)
                newDelay = otherDeloy;
        }
        if (newDelay < 100L) {
            newDelay = 100L;
        }
        if (newDelay > 500L) {
            newDelay = 500L;
        }
        serverTime = serverTime + newDelay;

        //判断该玩家是否在线
        if (!gameServerSendService.isOnline(otherRoleId)) {
            throw new PvpOtherNotOnlineException();
        }

        if (deadBattleUnitList.size() > 0) {
            sendService.sendPlayerBattleUnitDead(otherRoleId, deadBattleUnitList, serverTime);
        }

        return serverTime;
    }

    /**
     * pvp同步战术
     *
     * @param roleId 玩家id
     * @param
     * @return
     * @throws
     * @throws
     * @throws
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public long syncTactics(int roleId, int otherRoleId, int tacticsId, int tacticsLevel, int tacticsxPosition,
                            int tacticszPosition, String tacticsCommon, long clientTime)
            throws NoSuchRoleException, PvpOtherNotOnlineException, PlayerTacticsNotExistException {
        // 服务器根据两个客户端的最慢网络延时下发最终的客户端执行时间戳
        long serverTime = System.currentTimeMillis();
        if ((roleId < 0) || (otherRoleId < 0))
            throw new NoSuchRoleException();

        long newDelay = serverTime - clientTime;
        logger.info("---syncTactics------------------newDelay ====== " + newDelay);
        PlayerRealtimePvpVO ownRealtimePvpVO = playerRealtimePvpCacheManager.get(roleId);
        if (ownRealtimePvpVO != null) {
            ownRealtimePvpVO.setDelayTime(newDelay);
            playerRealtimePvpCacheManager.put(roleId, ownRealtimePvpVO);
        }

        PlayerRealtimePvpVO playerRealtimePvpVO = playerRealtimePvpCacheManager.get(otherRoleId);
        if (playerRealtimePvpVO != null) {
            long otherDeloy = playerRealtimePvpVO.getDelayTime();
            if (otherDeloy > newDelay)
                newDelay = otherDeloy;
        }
        if (newDelay < 100L) {
            newDelay = 100L;
        }
        if (newDelay > 500L) {
            newDelay = 500L;
        }
        serverTime = serverTime + newDelay;

        PlayerRoleVO ownRoleVO = playerRoleCacheManager.get(roleId);
        List<Integer> tacticsList = new ArrayList<Integer>();
        List<PlayerTacticVO> playerTacticsList = ownRoleVO.getTacticsList();
        for (PlayerTacticVO playerTacticVO : playerTacticsList) {
            tacticsList.add(playerTacticVO.getTacticID());
        }

//		if ((tacticsList == null) || (!tacticsList.contains(tacticsId))) {
//			throw new PlayerTacticsNotExistException();
//		}

        //判断该玩家是否在线
        if (!gameServerSendService.isOnline(otherRoleId)) {
            throw new PvpOtherNotOnlineException();
        }

        sendService.sendPvpTactics(roleId, otherRoleId, tacticsId,
                tacticsLevel, tacticsxPosition, tacticszPosition, tacticsCommon, serverTime);

        return serverTime;
    }

    /**
     * pvp同步指挥官指令
     *
     * @param roleId 玩家id
     * @param
     * @return
     * @throws
     * @throws
     * @throws
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public long syncCommand(int roleId, int otherRoleId, int commandId, long clientTime) throws NoSuchRoleException, PvpOtherNotOnlineException {
        // 服务器根据两个客户端的最慢网络延时下发最终的客户端执行时间戳
        long serverTime = System.currentTimeMillis();
        if ((roleId < 0) || (otherRoleId < 0))
            throw new NoSuchRoleException();

        long newDelay = serverTime - clientTime;
        logger.info("---syncCommand------------------newDelay ====== " + newDelay);
        PlayerRealtimePvpVO ownRealtimePvpVO = playerRealtimePvpCacheManager.get(roleId);
        if (ownRealtimePvpVO != null) {
            ownRealtimePvpVO.setDelayTime(newDelay);
            playerRealtimePvpCacheManager.put(roleId, ownRealtimePvpVO);
        }

        PlayerRealtimePvpVO playerRealtimePvpVO = playerRealtimePvpCacheManager.get(otherRoleId);
        if (playerRealtimePvpVO != null) {
            long otherDeloy = playerRealtimePvpVO.getDelayTime();
            if (otherDeloy > newDelay)
                newDelay = otherDeloy;
        }
        if (newDelay < 100L) {
            newDelay = 100L;
        }
        if (newDelay > 500L) {
            newDelay = 500L;
        }
        serverTime = serverTime + newDelay;

        //判断该玩家是否在线
        if (!gameServerSendService.isOnline(otherRoleId)) {
            throw new PvpOtherNotOnlineException();
        }

        sendService.sendPvpCommand(roleId, otherRoleId, commandId, serverTime);

        return serverTime;
    }

    /**
     * pvp同步通用信息
     *
     * @param roleId 玩家id
     * @param
     * @return
     * @throws
     * @throws
     * @throws
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public long syncCommon(int roleId, int otherRoleId, String commonStr, long clientTime, boolean isSendAll) throws NoSuchRoleException, PvpOtherNotOnlineException {
        // 服务器根据两个客户端的最慢网络延时下发最终的客户端执行时间戳
        long serverTime = System.currentTimeMillis();
        if ((roleId < 0) || (otherRoleId < 0))
            throw new NoSuchRoleException();

        logger.info("commonStr========== " + commonStr + "--------------------clientTime ====== " + clientTime);
        long newDelay = serverTime - clientTime;
        logger.info("---syncCommon------------------newDelay ====== " + newDelay);
        PlayerRealtimePvpVO ownRealtimePvpVO = playerRealtimePvpCacheManager.get(roleId);
        if (ownRealtimePvpVO != null) {
            ownRealtimePvpVO.setDelayTime(newDelay);
            playerRealtimePvpCacheManager.put(roleId, ownRealtimePvpVO);
        }

        PlayerRealtimePvpVO playerRealtimePvpVO = playerRealtimePvpCacheManager.get(otherRoleId);
        if (playerRealtimePvpVO != null) {
            long otherDeloy = playerRealtimePvpVO.getDelayTime();
            if (otherDeloy > newDelay)
                newDelay = otherDeloy;
        }
        if (newDelay < 100L) {
            newDelay = 100L;
        }
        if (newDelay > 500L) {
            newDelay = 500L;
        }
        serverTime = serverTime + newDelay;

        //判断该玩家是否在线
        if (!gameServerSendService.isOnline(otherRoleId)) {
            throw new PvpOtherNotOnlineException();
        }

        logger.info("roleId ==== " + roleId + "   otherRoleId ===== " + otherRoleId + "   commonStr ==== " + commonStr);

        sendService.sendPvpCommon(roleId, otherRoleId, commonStr, serverTime);

        if (isSendAll) {
            sendService.sendPvpCommon(otherRoleId, roleId, commonStr, serverTime);
        }

        return serverTime;
    }


    /**
     * @param roleId 玩家id
     * @param
     * @return
     * @throws IOException
     * @throws JsonMappingException
     * @throws JsonParseException   pvp战斗正常结束
     * @throws
     * @throws
     * @throws
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public PvpBattleEndVO pvpBattleEndNormal(int roleId, int battleRes, List<BattleUnitVO> damageBattleUnitList) throws NoSuchRoleException,
            BattleResultErrorException, DamageBattleUnitTooLargeException, BattleUnitNotExistException, JsonParseException, JsonMappingException, IOException {
        PvpBattleEndVO pvpBattleEndVO = new PvpBattleEndVO();
        if (roleId < 0)
            throw new NoSuchRoleException();

        if ((battleRes != AppConfig.BATTLE_SUCCESS_RESULT) && (battleRes != AppConfig.BATTLE_FAILED_RESULT)
                && (battleRes != AppConfig.BATTLE_TIMEOUT_RESULT))
            throw new BattleResultErrorException();

        int rewardHonor = 0;
        // 荣誉代币
        int honorToken = 0;
        int leftTokenTimes = 0;

        PlayerRealtimePvpVO ownRealtimePvpVO = playerRealtimePvpCacheManager.get(roleId);
        PlayerRealtimePvpVO otherRealtimePvpVO = playerRealtimePvpCacheManager.get(ownRealtimePvpVO.getOtherRoleId());
        if (!ownRealtimePvpVO.isBattleEnd() && !otherRealtimePvpVO.isBattleEnd()) {
            ownRealtimePvpVO.setBattleEnd(true);
            ownRealtimePvpVO.setBattleResult(battleRes);
            playerRealtimePvpCacheManager.put(roleId, ownRealtimePvpVO);
            if (battleRes == AppConfig.BATTLE_SUCCESS_RESULT) {
                rewardHonor = ownRealtimePvpVO.getRewardHonor();
                honorToken = AppConfig.PVP_WIN_HONOR_TOKEN;
            } else if (battleRes == AppConfig.BATTLE_FAILED_RESULT) {
                rewardHonor = ownRealtimePvpVO.getSubHonor();
                honorToken = AppConfig.PVP_FAIL_HONOR_TOKEN;
            } else {
                battleRes = AppConfig.BATTLE_FAILED_RESULT;
                rewardHonor = ownRealtimePvpVO.getSubHonor();
                honorToken = AppConfig.PVP_FAIL_HONOR_TOKEN;
            }
            pvpBattleEndVO.setBattleResult(battleRes);
            pvpBattleEndVO.setRewardHonor(rewardHonor);
            if (!ownRealtimePvpVO.isRewardHonor()) {
                ownRealtimePvpVO.setRewardHonor(true);
                playerRealtimePvpCacheManager.put(roleId, ownRealtimePvpVO);
                sendService.sendCrossAddHonor(roleId, rewardHonor);
                if (rewardHonor != 0) {
                    PlayerTokenVO playerTokenVO = addPlayerToken(roleId, honorToken);
                    leftTokenTimes = playerTokenVO.getLeftTimes();
                    if (!playerTokenVO.isAddToken()) {
                        honorToken = 0;
                    }
                } else {
                    honorToken = 0;
                }
            }

        } else if (!ownRealtimePvpVO.isBattleEnd() && otherRealtimePvpVO.isBattleEnd()) {
            int otherBattleRes = otherRealtimePvpVO.getBattleResult();
            if (otherBattleRes == AppConfig.BATTLE_FAILED_RESULT) {
                rewardHonor = ownRealtimePvpVO.getRewardHonor();
                honorToken = AppConfig.PVP_WIN_HONOR_TOKEN;
                pvpBattleEndVO.setBattleResult(AppConfig.BATTLE_SUCCESS_RESULT);
            } else if (otherBattleRes == AppConfig.BATTLE_SUCCESS_RESULT) {
                rewardHonor = ownRealtimePvpVO.getSubHonor();
                honorToken = AppConfig.PVP_FAIL_HONOR_TOKEN;
                pvpBattleEndVO.setBattleResult(AppConfig.BATTLE_FAILED_RESULT);
            } else {
                rewardHonor = ownRealtimePvpVO.getSubHonor();
                honorToken = AppConfig.PVP_FAIL_HONOR_TOKEN;
                pvpBattleEndVO.setBattleResult(AppConfig.BATTLE_FAILED_RESULT);
            }

            pvpBattleEndVO.setRewardHonor(rewardHonor);

            if (!ownRealtimePvpVO.isRewardHonor()) {
                sendService.sendCrossAddHonor(roleId, rewardHonor);
                if (rewardHonor != 0) {
                    PlayerTokenVO playerTokenVO = addPlayerToken(roleId, honorToken);
                    leftTokenTimes = playerTokenVO.getLeftTimes();
                    if (!playerTokenVO.isAddToken()) {
                        honorToken = 0;
                    }
                } else {
                    honorToken = 0;
                }
            }

            playerRealtimePvpCacheManager.remove(roleId);
            playerRealtimePvpCacheManager.remove(ownRealtimePvpVO.getOtherRoleId());
        } else {
            throw new BattleResultErrorException();
        }

        pvpBattleEndVO.setHonorToken(honorToken);
        pvpBattleEndVO.setTokenLeftTimes(leftTokenTimes);
        //playerBattleUnitService.reduceBattleUnit(roleId, damageBattleUnitList);
        return pvpBattleEndVO;
    }

    /**
     * @param roleId 玩家id
     * @param
     * @return
     * @throws IOException
     * @throws JsonMappingException
     * @throws JsonParseException   pvp战斗非正常结束，比如玩家断线
     * @throws
     * @throws
     * @throws
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public PvpBattleEndVO pvpBattleEndUnormal(int roleId, int battleRes, List<BattleUnitVO> damageBattleUnitList, List<BattleUnitVO> otherBattleUnitList) throws NoSuchRoleException,
            DamageBattleUnitTooLargeException, BattleUnitNotExistException, JsonParseException, JsonMappingException, IOException {
        PvpBattleEndVO pvpBattleEndVO = new PvpBattleEndVO();
        if (roleId < 0)
            throw new NoSuchRoleException();

        PlayerRealtimePvpVO ownRealtimePvpVO = playerRealtimePvpCacheManager.get(roleId);

        playerRealtimePvpCacheManager.remove(roleId);

        pvpBattleEndVO.setBattleResult(ownRealtimePvpVO.getBattleResult());
        if (ownRealtimePvpVO.getBattleResult() == AppConfig.BATTLE_SUCCESS_RESULT) {
            pvpBattleEndVO.setRewardHonor(ownRealtimePvpVO.getRewardHonor());
            sendService.sendCrossAddHonor(roleId, ownRealtimePvpVO.getRewardHonor());
            if (ownRealtimePvpVO.getRewardHonor() != 0) {
                PlayerTokenVO playerTokenVO = addPlayerToken(roleId, AppConfig.PVP_WIN_HONOR_TOKEN);
                if (!playerTokenVO.isAddToken()) {
                    pvpBattleEndVO.setHonorToken(0);
                } else {
                    pvpBattleEndVO.setHonorToken(AppConfig.PVP_WIN_HONOR_TOKEN);
                }
                pvpBattleEndVO.setTokenLeftTimes(playerTokenVO.getLeftTimes());
            } else {
                pvpBattleEndVO.setHonorToken(0);
            }
        } else {
            pvpBattleEndVO.setRewardHonor(ownRealtimePvpVO.getSubHonor());
            sendService.sendCrossAddHonor(roleId, ownRealtimePvpVO.getSubHonor());
            if (ownRealtimePvpVO.getSubHonor() != 0) {
                PlayerTokenVO playerTokenVO = addPlayerToken(roleId, AppConfig.PVP_FAIL_HONOR_TOKEN);
                if (!playerTokenVO.isAddToken()) {
                    pvpBattleEndVO.setHonorToken(0);
                } else {
                    pvpBattleEndVO.setHonorToken(AppConfig.PVP_FAIL_HONOR_TOKEN);
                }
                pvpBattleEndVO.setTokenLeftTimes(playerTokenVO.getLeftTimes());
            } else {
                pvpBattleEndVO.setHonorToken(0);
            }
        }

        return pvpBattleEndVO;
    }

    /**
     * pvp中玩家主动退出
     *
     * @param roleId 玩家id
     * @param
     * @return
     * @throws
     * @throws
     * @throws
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public PvpBattleEndVO pvpPlayerExit(int roleId, List<BattleUnitVO> damageBattleUnitList) throws NoSuchRoleException,
            DamageBattleUnitTooLargeException, BattleUnitNotExistException, JsonParseException, JsonMappingException, IOException {
        PvpBattleEndVO pvpBattleEndVO = new PvpBattleEndVO();
        if (roleId < 0)
            throw new NoSuchRoleException();

        int rewardHonor = 0;
        PlayerRoleVO playerRoleVO = playerRoleCacheManager.get(roleId);
        PlayerRealtimePvpVO ownRealtimePvpVO = playerRealtimePvpCacheManager.get(roleId);
        ownRealtimePvpVO.setBattleEnd(true);
        ownRealtimePvpVO.setBattleResult(AppConfig.BATTLE_FAILED_RESULT);
        playerRealtimePvpCacheManager.put(roleId, ownRealtimePvpVO);
        rewardHonor = ownRealtimePvpVO.getSubHonor();

        if (!ownRealtimePvpVO.isRewardHonor()) {
            ownRealtimePvpVO.setRewardHonor(true);
            playerRealtimePvpCacheManager.put(roleId, ownRealtimePvpVO);
            sendService.sendCrossAddHonor(roleId, rewardHonor);
            if (rewardHonor != 0) {
                addPlayerToken(roleId, AppConfig.PVP_FAIL_HONOR_TOKEN);
                pvpBattleEndVO.setHonorToken(AppConfig.PVP_FAIL_HONOR_TOKEN);
            }
        }

        // playerBattleUnitService.reduceBattleUnit(roleId, damageBattleUnitList);
        sendService.sendPvpPlayerExit(ownRealtimePvpVO.getOtherRoleId(), roleId, playerRoleVO.getRoleName());
        pvpBattleEndVO.setBattleResult(AppConfig.BATTLE_FAILED_RESULT);
        pvpBattleEndVO.setRewardHonor(rewardHonor);
        return pvpBattleEndVO;
    }

    /**
     * 获取玩家pvp代币
     *
     * @param roleId 玩家id
     * @param
     * @return
     * @throws
     * @throws
     * @throws
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int getPvpTokenInfo(int roleId) throws NoSuchRoleException {
        int pvpTokenLeftTimes = 0;
        Realtimepvptokenhistory realtimepvptokenhistory = realtimepvptokenhistoryMapper.selectByPrimaryKey(roleId);
        if (realtimepvptokenhistory == null) {
            pvpTokenLeftTimes = 5;
        } else {
            pvpTokenLeftTimes = 5 - realtimepvptokenhistory.getTokentimes();
        }
        return pvpTokenLeftTimes;
    }

    /**
     * 给玩家增加pvp代币
     *
     * @param roleId 玩家id
     * @param
     * @return
     * @throws
     * @throws
     * @throws
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    private PlayerTokenVO addPlayerToken(int roleId, int honorToken) {
        PlayerTokenVO playerTokenVO = new PlayerTokenVO();
        int leftTimes = 0;
        boolean isAddToken = false;
        boolean isUpdateRecord = false;
        Realtimepvptokenhistory realtimepvptokenhistory = realtimepvptokenhistoryMapper.selectByPrimaryKey(roleId);
        if (realtimepvptokenhistory == null) {
            realtimepvptokenhistory = new Realtimepvptokenhistory();
            realtimepvptokenhistory.setRoleid(roleId);
            realtimepvptokenhistory.setTokentimes((short) 1);
            realtimepvptokenhistoryMapper.insertSelective(realtimepvptokenhistory);
            isAddToken = true;

        } else {
            if (realtimepvptokenhistory.getTokentimes() < 5) {
                isAddToken = true;
                isUpdateRecord = true;
            }
        }

        if (isAddToken) {
            sendService.sendCrossAddToken(roleId, honorToken);
            leftTimes = 4;
        }
        if (isUpdateRecord) {
            realtimepvptokenhistory.setTokentimes((short) (realtimepvptokenhistory.getTokentimes() + 1));
            realtimepvptokenhistoryMapper.updateByPrimaryKeySelective(realtimepvptokenhistory);
            leftTimes = 5 - realtimepvptokenhistory.getTokentimes();
        }
        playerTokenVO.setLeftTimes(leftTimes);
        playerTokenVO.setAddToken(isAddToken);
        return playerTokenVO;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    private int getMatchRoleId(int roleId, PlayerRoleVO playerRoleVO) {
        // 匹配算法为先查找荣誉值在50范围内的匹配玩家，如果没有找到的话将范围扩展到100
        int matchRoleId = 0;
        // 开始荣誉值，玩家荣誉值减50
        double beginHonor100 = (playerRoleVO.getRoleHonor() - 50) >= 0 ? (playerRoleVO.getRoleHonor() - 50) : 0;
        // 结束荣誉值，玩家荣誉值加50
        double endHonor100 = playerRoleVO.getRoleHonor() + 50;
        // 匹配出的玩家ID
        List<Integer> matchRoleList100Zset = pvpMatchZSetCpt.getByScore(beginHonor100, endHonor100);
        List<Integer> matchRoleList100 = new ArrayList<Integer>();
        for (int id : matchRoleList100Zset) {
            if (id != roleId) {
                matchRoleList100.add(id);
            }
        }
        if (matchRoleList100.size() > 0) {
            if (matchRoleList100.size() == 1) {
                matchRoleId = matchRoleList100.get(0);
            } else {
                // 按照数据长度大小随机取一个
                Random random = new Random();
                int index = random.nextInt(matchRoleList100.size() - 1) % (matchRoleList100.size());
                matchRoleId = matchRoleList100.get(index);
            }
        } else {
            // 开始荣誉值，玩家荣誉值减100
            double beginHonor200 = (playerRoleVO.getRoleHonor() - 100) >= 0 ? (playerRoleVO.getRoleHonor() - 100) : 0;
            // 结束荣誉值，玩家荣誉值加100
            double endHonor200 = playerRoleVO.getRoleHonor() + 100;
            // 匹配出的玩家ID
            List<Integer> matchRoleList200Zset = pvpMatchZSetCpt.getByScore(beginHonor200, endHonor200);
            List<Integer> matchRoleList200 = new ArrayList<Integer>();
            for (int id : matchRoleList200Zset) {
                if (id != roleId) {
                    matchRoleList200.add(id);
                }
            }
            if (matchRoleList200.size() > 0) {
                if (matchRoleList200.size() == 1) {
                    matchRoleId = matchRoleList200.get(0);
                } else {
                    // 按照数据长度大小随机取一个
                    Random random = new Random();
                    int index = random.nextInt(matchRoleList200.size() - 1) % (matchRoleList200.size());
                    matchRoleId = matchRoleList200.get(index);
                }
            }
        }
        if ((matchRoleId > 0) && (matchRoleId != roleId) && (gameServerSendService.isOnline(matchRoleId))) {
            RealtimepvpplayerhistoryKey realtimepvpplayerhistoryKey = new RealtimepvpplayerhistoryKey();
            realtimepvpplayerhistoryKey.setRoleid(roleId);
            realtimepvpplayerhistoryKey.setAttackeeroleid(matchRoleId);
            Realtimepvpplayerhistory realtimepvpplayerhistory = realtimepvpplayerhistoryMapper.selectByPrimaryKey(realtimepvpplayerhistoryKey);
            if (realtimepvpplayerhistory != null) {
                if (realtimepvpplayerhistory.getAttacktimes() >= Short.valueOf(serverConfig.getProperty(AppConfig.PVP_PLAYER_MAX_TIMES))) {
                    return 0;
                } else {
                    return matchRoleId;
                }
            } else {
                return matchRoleId;
            }
        } else {
            return 0;
        }
    }

    /**
     * pvp玩家布阵倒计时结束校验
     */
    private class PvpDeployVerifyTask implements Runnable {
        private int roleId;

        public PvpDeployVerifyTask(int roleId) {
            this.roleId = roleId;
        }

        public void run() {
            logger.info("---------------------45秒定时开始--------------" + roleId);
            PlayerRealtimePvpVO ownRealtimePvpVO = playerRealtimePvpCacheManager.get(roleId);
            PlayerRealtimePvpVO otherRealtimePvpVO = null;
            if (ownRealtimePvpVO != null) {
                otherRealtimePvpVO = playerRealtimePvpCacheManager.get(ownRealtimePvpVO.getOtherRoleId());
                if (otherRealtimePvpVO != null) {
                    if (!(ownRealtimePvpVO.isBattleStart() && otherRealtimePvpVO.isBattleStart())) {
                        boolean ownAlreadyDeployed = false;
                        boolean otherAlreadyDeployed = false;

                        if ((ownRealtimePvpVO != null) && ((ownRealtimePvpVO.getPlayerBattleUnitList1().size() > 0)
                                || (ownRealtimePvpVO.getPlayerBattleUnitList2().size() > 0)
                                || (ownRealtimePvpVO.getPlayerBattleUnitList3().size() > 0)
                                || (ownRealtimePvpVO.getPlayerBattleUnitList4().size() > 0))) {
                            ownAlreadyDeployed = true;
                        }

                        if ((otherRealtimePvpVO != null) && ((otherRealtimePvpVO.getPlayerBattleUnitList1().size() > 0)
                                || (otherRealtimePvpVO.getPlayerBattleUnitList2().size() > 0)
                                || (otherRealtimePvpVO.getPlayerBattleUnitList3().size() > 0)
                                || (otherRealtimePvpVO.getPlayerBattleUnitList4().size() > 0))) {
                            otherAlreadyDeployed = true;
                        }

                        if ((ownAlreadyDeployed == true) && (otherAlreadyDeployed == false)) {
                            ownRealtimePvpVO.setBattleResult(AppConfig.BATTLE_SUCCESS_RESULT);
                            otherRealtimePvpVO.setBattleResult(AppConfig.BATTLE_FAILED_RESULT);
                        } else if ((ownAlreadyDeployed == false) && (otherAlreadyDeployed == true)) {
                            ownRealtimePvpVO.setBattleResult(AppConfig.BATTLE_FAILED_RESULT);
                            otherRealtimePvpVO.setBattleResult(AppConfig.BATTLE_SUCCESS_RESULT);
                        } else {
                            ownRealtimePvpVO.setBattleResult(AppConfig.BATTLE_FAILED_RESULT);
                            otherRealtimePvpVO.setBattleResult(AppConfig.BATTLE_FAILED_RESULT);
                        }

                        sendService.sendPvpDeployVerify(roleId, ownRealtimePvpVO.getBattleResult());
                        sendService.sendPvpDeployVerify(ownRealtimePvpVO.getOtherRoleId(), otherRealtimePvpVO.getBattleResult());
                        playerRealtimePvpCacheManager.put(roleId, ownRealtimePvpVO);
                        playerRealtimePvpCacheManager.put(ownRealtimePvpVO.getOtherRoleId(), otherRealtimePvpVO);
                    }
                }
            }

            if (ownRealtimePvpVO.getDeployRoomId() != null) {
                if (deployTasksMap.get(ownRealtimePvpVO.getDeployRoomId()) != null) {
                    Future<?> pvpDeployScheduledFuture = deployTasksMap.remove(ownRealtimePvpVO.getDeployRoomId());
                    if (pvpDeployScheduledFuture != null)
                        pvpDeployScheduledFuture.cancel(false);
                }
            }
        }
    }
}
