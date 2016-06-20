package com.pkuvr.game_server.service;

import com.pkuvr.game_server.cache.PlayerRealtimePvpCacheManager;
import com.pkuvr.game_server.cache.PlayerRoleCacheManager;
import com.pkuvr.game_server.cachevo.PlayerRealtimePvpVO;
import com.pkuvr.game_server.cachevo.PlayerRoleVO;
import com.pkuvr.game_server.constant.AppConfig;
import com.pkuvr.game_server.dao.DichonorrewardMapper;
import com.pkuvr.game_server.dao.RealtimepvpnpchistoryMapper;
import com.pkuvr.game_server.dao.RealtimepvptokenhistoryMapper;
import com.pkuvr.game_server.domain.Dichonorreward;
import com.pkuvr.game_server.domain.DichonorrewardExample;
import com.pkuvr.game_server.domain.Realtimepvpnpchistory;
import com.pkuvr.game_server.domain.Realtimepvptokenhistory;
import com.pkuvr.game_server.enumerate.TaskTypeCode;
import com.pkuvr.game_server.exception.*;
import com.pkuvr.game_server.network.GameServerSendService;
import com.pkuvr.game_server.redis.BaseZSetCpt;
import com.pkuvr.game_server.vo.*;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

@Service("RealTimePvpNpcService")
public class RealTimePvpNpcService {
    private static final Logger logger = Logger.getLogger(RealTimePvpService.class);
    @Resource
    private PlayerRoleCacheManager playerRoleCacheManager;
    @Resource
    private PlayerRealtimePvpCacheManager playerRealtimePvpCacheManager;
    @Resource
    private DichonorrewardMapper dichonorrewardMapper;
    @Resource
    private RealtimepvpnpchistoryMapper realtimepvpnpchistoryMapper;
    @Resource
    private RealtimepvptokenhistoryMapper realtimepvptokenhistoryMapper;
    @Resource
    private SendService sendService;
    @Resource
    private BaseZSetCpt pvpRoomZSetCpt; //pvp房间玩家排名
    @Resource
    private Properties serverConfig;
    //@Resource private ActivityActiveService activityActiveService;

    private GameServerSendService gameServerSendService = GameServerSendService.getInstance();

    /**
     * @param roleId 玩家id
     * @param
     * @return
     * @throws TaskDataException 为实时pvp玩家匹配Npc对手
     * @throws
     * @throws
     * @throws
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public PvpNpcBattleMatchVO pvpNpcBattleMatch(int roleId, int npcMatchTime) throws NoSuchRoleException, PvpNpcMatchOverTimesException, PvpMatchFailedException, TaskDataException {
        PvpNpcBattleMatchVO pvpNpcBattleMatchVO = new PvpNpcBattleMatchVO();
        if (roleId < 0)
            throw new NoSuchRoleException();
        playerRealtimePvpCacheManager.remove(roleId);

        if (npcMatchTime < 60)
            RealTimePvpTimeService.addPvpMatchTime(npcMatchTime);

        PlayerRealtimePvpVO playerRealtimePvpVOCheck = playerRealtimePvpCacheManager.get(roleId);
        if (playerRealtimePvpVOCheck == null) {
            PlayerRoleVO playerRoleVO = playerRoleCacheManager.get(roleId);

            int playerId = 0;
            for (int i = 1; i < 11; i++) {
                int matchRoleId = getMatchRoleId(roleId, playerRoleVO, (i * 50));
                if ((matchRoleId > 0) && (matchRoleId != roleId)) {
                    playerId = matchRoleId;
                    break;
//			    	 // 判断取出的玩家兵力是否到达上限的70%
//			    	 PlayerRoleVO matchRoleVO = playerRoleCacheManager.get(matchRoleId);
//			    	 int playerBattleUnitTon = playerBattleUnitService.getPlayerBattleUnitTon(matchRoleId, false);
//					 double percent = (playerBattleUnitTon * 0.1 )/ (matchRoleVO.getRoleTon() * 0.1);
//			
//					 if(percent >= EnemyListService.ENEMY_TROOPS_PROPORTION){
//						 playerId = matchRoleId;
//						 break;
//					 }
                }
            }

            if (playerId > 0) {
                PlayerRoleVO enemyRoleVO = playerRoleCacheManager.get(playerId);
                int honorSub = playerRoleVO.getRoleHonor() - enemyRoleVO.getRoleHonor();
                DichonorrewardExample dichonorrewardExample = new DichonorrewardExample();
                dichonorrewardExample.createCriteria()
                        .andBeginhonorLessThanOrEqualTo(playerRoleVO.getRoleHonor())
                        .andEndhonorGreaterThanOrEqualTo(playerRoleVO.getRoleHonor())
                        .andBeginvalueLessThanOrEqualTo(honorSub)
                        .andEndvalueGreaterThanOrEqualTo(honorSub);
                List<Dichonorreward> honorRewardList = dichonorrewardMapper.selectByExample(dichonorrewardExample);
                Dichonorreward dichonorreward = honorRewardList.get(0);

                PlayerRealtimePvpVO ownRealtimePvpVO = new PlayerRealtimePvpVO();
                ownRealtimePvpVO.setOtherRoleId(playerId);
                ownRealtimePvpVO.setRewardHonor(dichonorreward.getRewardhonor());
                ownRealtimePvpVO.setSubHonor(dichonorreward.getSubhonor());
                //ownRealtimePvpVO.setPvpMap(pvpMap);
                ownRealtimePvpVO.setBattleEnd(false);
                ownRealtimePvpVO.setRewardHonor(false);
                playerRealtimePvpCacheManager.put(roleId, ownRealtimePvpVO);

                int roleRank = pvpRoomZSetCpt.getRank(playerId) + 1;
                PvpRankInfoVO otherRankInfoVO = new PvpRankInfoVO();
                otherRankInfoVO.setPvpRank(roleRank);
                otherRankInfoVO.setRoleId(playerId);
                otherRankInfoVO.setRoleName(enemyRoleVO.getRoleName());
                otherRankInfoVO.setCampId(enemyRoleVO.getCampId());
                otherRankInfoVO.setGeneralDegree(enemyRoleVO.getRankLevel());
                otherRankInfoVO.setAvatar(enemyRoleVO.getRoleAvatar());
                otherRankInfoVO.setHonor(enemyRoleVO.getRoleHonor());

                pvpNpcBattleMatchVO.setOtherPlayerRank(otherRankInfoVO);
            } else {
                throw new PvpMatchFailedException();
            }
        }

        //activityActiveService.addFinishNum(ActivityTaskEnum.PLAY_PVP, roleId, 1);
        sendService.sendCrossAddTaskFinish(roleId, TaskTypeCode.ATHLETICS_NUM.getType());

        return pvpNpcBattleMatchVO;
    }

    /**
     * 为实时pvp玩家匹配Npc对手，战斗开始
     *
     * @param roleId 玩家id
     * @param
     * @return
     * @throws
     * @throws
     * @throws
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public PvpNpcBattleStartVO pvpNpcBattleStart(int roleId, int minePower, List<BattleUnitVO> battleUnitList)
            throws NoSuchRoleException, PlayerPowerNotEnoughException, BattleUnitNotEnoughException, PlayerTonNotEnoughException, BattleUnitNumberZeroException, BattleUnitNotExistException {
        PvpNpcBattleStartVO pvpNpcBattleStartVO = new PvpNpcBattleStartVO();
        if (roleId < 0)
            throw new NoSuchRoleException();

        PlayerRoleVO playerRoleVO = playerRoleCacheManager.get(roleId);
        if (minePower > playerRoleVO.getRolePower())
            throw new PlayerPowerNotEnoughException();

        Realtimepvpnpchistory realtimepvpplayerhistory = realtimepvpnpchistoryMapper.selectByPrimaryKey(roleId);
        if (realtimepvpplayerhistory != null) {
            realtimepvpplayerhistory.setAttacktimes((short) (realtimepvpplayerhistory.getAttacktimes() + 1));
            realtimepvpnpchistoryMapper.updateByPrimaryKeySelective(realtimepvpplayerhistory);
        } else {
            Realtimepvpnpchistory insertRecord = new Realtimepvpnpchistory();
            insertRecord.setRoleid(roleId);
            insertRecord.setAttacktimes((short) 1);
            realtimepvpnpchistoryMapper.insert(insertRecord);
        }

        Map<Integer, BattleUnitVO> battleUnitMap = playerRoleVO.getBattleUnitMap();
        for (BattleUnitVO battleUnitVO : battleUnitList) {
            BattleUnitVO playerBattleUnitVO = battleUnitMap.get(battleUnitVO.getBattleUnitID());
            if (playerBattleUnitVO == null) {
                throw new BattleUnitNotExistException();
            } else {
                if (battleUnitVO.getUnitNum() > playerBattleUnitVO.getStorageNum())
                    throw new BattleUnitNotEnoughException();

            }
        }

        PlayerRealtimePvpVO playerRealtimePvpVOCheck = playerRealtimePvpCacheManager.get(roleId);
        if (playerRealtimePvpVOCheck != null) {
            int playerId = playerRealtimePvpVOCheck.getOtherRoleId();
            PlayerRoleVO enemyRoleVO = playerRoleCacheManager.get(playerId);

            pvpNpcBattleStartVO.setOwnRewardHonor(playerRealtimePvpVOCheck.getRewardHonor());
            pvpNpcBattleStartVO.setOwnSubHonor(playerRealtimePvpVOCheck.getSubHonor());

            List<PlayerTacticVO> tacticsList = new ArrayList<PlayerTacticVO>();
            List<PlayerTacticVO> playerTacticsList = enemyRoleVO.getTacticsList();
            List<PlayerTacticVO> playerTacticsListSix = new ArrayList<PlayerTacticVO>();
            if (playerTacticsList.size() > 6) {
                playerTacticsListSix = playerTacticsList.subList(0, 6);
            } else {
                playerTacticsListSix = playerTacticsList;
            }
            for (PlayerTacticVO tacticsVO : playerTacticsListSix) {
                PlayerTacticVO playerTacticVO = new PlayerTacticVO();
                playerTacticVO.setTacticID(tacticsVO.getTacticID());
                playerTacticVO.setLevel(tacticsVO.getLevel());
                tacticsList.add(playerTacticVO);
            }
            pvpNpcBattleStartVO.setPlayerTactics(tacticsList);
            pvpNpcBattleStartVO.setPlayerPower(enemyRoleVO.getRolePower());

            List<BattleUnitVO> playerBattleUnitList = new ArrayList<BattleUnitVO>();
            Map<Integer, BattleUnitVO> enemyBattleUnitMap = enemyRoleVO.getBattleUnitMap();
            for (Integer key : enemyBattleUnitMap.keySet()) {
                playerBattleUnitList.add(enemyBattleUnitMap.get(key));
            }
            pvpNpcBattleStartVO.setPlayerBattleUnitList(playerBattleUnitList);
        }

        return pvpNpcBattleStartVO;
    }

    /**
     * 为实时pvp玩家匹配Npc对手，战斗结束
     *
     * @param roleId 玩家id
     * @param
     * @return
     * @throws
     * @throws
     * @throws
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public PvpNpcBattleEndVO pvpNpcBattleEnd(int roleId, int battleRes, List<BattleUnitStartVO> damageBattleUnitList)
            throws NoSuchRoleException, BattleResultErrorException, DamageBattleUnitTooLargeException,
            BattleUnitNotExistException, BattleUnitNumberZeroException, IOException {
        PvpNpcBattleEndVO pvpNpcBattleEndVO = new PvpNpcBattleEndVO();

        if ((battleRes != AppConfig.BATTLE_SUCCESS_RESULT) && (battleRes != AppConfig.BATTLE_FAILED_RESULT))
            throw new BattleResultErrorException();

        int rewardHonor = 0;
        // 荣誉代币
        int honorToken = 0;
        int leftTokenTimes = 0;

        PlayerRealtimePvpVO ownRealtimePvpVO = playerRealtimePvpCacheManager.get(roleId);
        if (ownRealtimePvpVO != null) {
            if (battleRes == AppConfig.BATTLE_SUCCESS_RESULT) {
                rewardHonor = ownRealtimePvpVO.getRewardHonor();
                honorToken = AppConfig.PVP_WIN_HONOR_TOKEN;
                if (rewardHonor != 0) {
                    PlayerTokenVO playerTokenVO = addPlayerToken(roleId, honorToken);
                    leftTokenTimes = playerTokenVO.getLeftTimes();
                    if (!playerTokenVO.isAddToken()) {
                        honorToken = 0;
                    }
                } else {
                    honorToken = 0;
                }
            } else if (battleRes == AppConfig.BATTLE_FAILED_RESULT) {
                rewardHonor = ownRealtimePvpVO.getSubHonor();
                honorToken = AppConfig.PVP_FAIL_HONOR_TOKEN;
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
        }

        sendService.sendCrossAddHonor(roleId, rewardHonor);
        pvpNpcBattleEndVO.setRewardHonor(rewardHonor);
        pvpNpcBattleEndVO.setBattleRes(battleRes);
        pvpNpcBattleEndVO.setHonorToken(honorToken);
        pvpNpcBattleEndVO.setTokenLeftTimes(leftTokenTimes);
        playerRealtimePvpCacheManager.remove(roleId);
        return pvpNpcBattleEndVO;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    private int getMatchRoleId(int roleId, PlayerRoleVO playerRoleVO, int honor) {
        int matchRoleId = 0;
        // 开始荣誉值，玩家荣誉值减50
        double beginHonor50 = (playerRoleVO.getRoleHonor() - honor) >= 0 ? (playerRoleVO.getRoleHonor() - honor) : 0;
        // 结束荣誉值，玩家荣誉值加50
        double endHonor50 = playerRoleVO.getRoleHonor();
        // 匹配出的玩家ID
        List<Integer> matchRoleList50Zset = pvpRoomZSetCpt.getByScore(beginHonor50, endHonor50);
        List<Integer> matchRoleList50 = new ArrayList<Integer>();
        for (int id : matchRoleList50Zset) {
            if (id != roleId) {
                matchRoleList50.add(id);
            }
        }
        if (matchRoleList50.size() > 0) {
            if (matchRoleList50.size() == 1) {
                matchRoleId = matchRoleList50.get(0);
            } else {
                // 按照数据长度大小随机取一个
                Random random = new Random();
                int index = random.nextInt(matchRoleList50.size() - 1) % (matchRoleList50.size());
                matchRoleId = matchRoleList50.get(index);
            }
        } else {
            // 开始荣誉值，玩家荣誉值减100
            double beginHonor100 = (playerRoleVO.getRoleHonor() - (honor * 2)) >= 0 ? (playerRoleVO.getRoleHonor() - (honor * 2)) : 0;
            // 结束荣誉值，玩家荣誉值加100
            double endHonor100 = playerRoleVO.getRoleHonor();
            // 匹配出的玩家ID
            List<Integer> matchRoleList100Zset = pvpRoomZSetCpt.getByScore(beginHonor100, endHonor100);
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
            }
        }

        return matchRoleId;
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

}
