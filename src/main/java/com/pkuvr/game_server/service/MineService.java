package com.pkuvr.game_server.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.pkuvr.game_server.cache.OwnPlayerMineAttackedCacheManager;
import com.pkuvr.game_server.cache.PlayerMineAttackedCacheManager;
import com.pkuvr.game_server.cache.PlayerMineInstanceAttackedCacheManager;
import com.pkuvr.game_server.cache.PlayerRoleCacheManager;
import com.pkuvr.game_server.cachevo.PlayerRoleVO;
import com.pkuvr.game_server.constant.AppConfig;
import com.pkuvr.game_server.constant.GuidePromptConfig;
import com.pkuvr.game_server.constant.SmOpCode;
import com.pkuvr.game_server.dao.*;
import com.pkuvr.game_server.domain.*;
import com.pkuvr.game_server.enumerate.ConsumptionTypeEnum;
import com.pkuvr.game_server.exception.*;
import com.pkuvr.game_server.network.GameServerSendService;
import com.pkuvr.game_server.redis.BaseZSetCpt;
import com.pkuvr.game_server.redis.PlayerResourceMineHMapCpt;
import com.pkuvr.game_server.vo.*;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

@Service("MineService")
public class MineService {
    private static final Logger logger = Logger.getLogger(MineService.class);

    @Resource
    private RefreshresourcemineMapper refreshresourcemineMapper;
    @Resource
    private DicresourceminetypeMapper dicresourceminetypeMapper;
    @Resource
    private DicresourcemineMapper dicresourcemineMapper;
    @Resource
    private NpcresourcemineMapper npcresourcemineMapper;
    @Resource
    private DicminebuildingMapper dicminebuildingMapper;
    @Resource
    private DicunlockresourcemineMapper dicunlockresourcemineMapper;
    @Resource
    private PlayerresourcemineMapper playerresourcemineMapper;
    @Resource
    private ResourceminebattlelogMapper resourceminebattlelogMapper;
    @Resource
    private MinerefreshrecordMapper minerefreshrecordMapper;
    @Resource
    private MineattackhistoryMapper mineattackhistoryMapper;
    @Resource
    private PlayerroleMapper playerRoleMapper;
    @Resource
    private PlayerresourceminecollectMapper playerresourceminecollectMapper;
    @Resource
    private ResourceminecollectlogMapper resourceminecollectlogMapper;
    @Resource
    private MineResourceService playerMineResourceService;
    @Resource
    private DichonorrewardMapper dichonorrewardMapper;
    @Resource
    private PlayermineleftresourceMapper playermineleftresourceMapper;
    @Resource
    private PlayerRoleCacheManager playerRoleCacheManager;
    @Resource
    private PlayerMineAttackedCacheManager playerMineAttackedCacheManager;
    @Resource
    private PlayerMineAttackedCacheManager ownMineAttackedCacheManager;
    @Resource
    private OwnPlayerMineAttackedCacheManager ownPlayerMineAttackedCacheManager;
    @Resource
    private PlayerMineInstanceAttackedCacheManager playerMineInstanceAttackedCacheManager;
    @Resource
    private ObjectMapper jacksonObjectMapper;
    @Resource
    private PlayerResourceMineHMapCpt playerResourceMineHMapCpt;
    @Resource
    private BaseZSetCpt gradeRankingZSetCpt; // 评分排行榜
    @Resource
    private SendService sendService;
    @Resource
    private PlayerprotectionbuffMapper playerprotectionbuffMapper;

    private GameServerSendService gameServerSendService = GameServerSendService.getInstance();

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void enterMine(int roleId) throws NoSuchRoleException, MineUnderAttackedException {
        if (roleId < 0)
            throw new NoSuchRoleException();

        if (playerMineAttackedCacheManager.get(roleId) != null)
            throw new MineUnderAttackedException();

    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public List<MineInfoVO> getMineList(int roleId) throws NoSuchRoleException, TaskDataException {
        List<MineInfoVO> resultList = new ArrayList<MineInfoVO>();
        if (roleId < 0)
            throw new NoSuchRoleException();

        RefreshresourcemineExample refreshresourcemineExample = new RefreshresourcemineExample();
        refreshresourcemineExample.createCriteria().andRoleidEqualTo(roleId);
        List<Refreshresourcemine> refreshMineList = refreshresourcemineMapper.selectByExample(refreshresourcemineExample);
        // 如果玩家首次使用资源点功能，为玩家刷新出4个初始npc资源点
        if ((refreshMineList == null) || (refreshMineList.size() == 0)) {
            DicresourceminetypeExample dicresourceminetypeExample = new DicresourceminetypeExample();
            dicresourceminetypeExample.createCriteria().andRestypeEqualTo((short) 1);
            List<Dicresourceminetype> dicResourceMineTypeList = dicresourceminetypeMapper.selectByExample(dicresourceminetypeExample);
            List<Integer> resMineTypeIdList = new ArrayList<Integer>();
            for (Dicresourceminetype dicresourceminetype : dicResourceMineTypeList) {
                resMineTypeIdList.add(dicresourceminetype.getResminetypeid());
            }
            DicresourcemineExample dicresourcemineExample = new DicresourcemineExample();
            dicresourcemineExample.createCriteria().andResminetypeidIn(resMineTypeIdList);
            List<Dicresourcemine> dicResourceMineList = dicresourcemineMapper.selectByExample(dicresourcemineExample);
            for (Dicresourcemine dicresourcemine : dicResourceMineList) {
                Npcresourcemine record = new Npcresourcemine();
                record.setResmineid(dicresourcemine.getResmineid());
                record.setMinestate((short) 0);
                int insertRes = npcresourcemineMapper.insert(record);
                if (insertRes > 0) {
                    Refreshresourcemine refreshMine = new Refreshresourcemine();
                    refreshMine.setRoleid(roleId);
                    refreshMine.setNpcresmineid(record.getNpcresmineid());
                    refreshMine.setIsunlock(true);
                    refreshMine.setRefreshtime(System.currentTimeMillis());
                    refreshresourcemineMapper.insert(refreshMine);
                    refreshMineList.add(refreshMine);
                }
            }
        }

        // 根据为每个玩家刷出npc和玩家总资源点列表逐个判断，该资源点是被npc占领还是玩家自身占领或者是其他玩家占领
        for (Refreshresourcemine refreshresourcemine : refreshMineList) {
            MineInfoVO mineInfoVO = new MineInfoVO();

            Npcresourcemine npcresourcemine = npcresourcemineMapper.selectByPrimaryKey(refreshresourcemine.getNpcresmineid());
            if ((npcresourcemine.getMinestate() == (short) 0) || (npcresourcemine.getMinestate() == (short) 1)) {
                if ((refreshresourcemine != null) && (refreshresourcemine.getIsunlock() != null))
                    mineInfoVO.setUnlock(refreshresourcemine.getIsunlock());

                mineInfoVO.setResMineInstanceID(npcresourcemine.getNpcresmineid());
                mineInfoVO.setResMineID(npcresourcemine.getResmineid());
                mineInfoVO.setMineState(1);
                Dicresourcemine dicresourcemine = dicresourcemineMapper.selectByPrimaryKey(npcresourcemine.getResmineid());
                DicminebuildingKey dicminebuildingKey = new DicminebuildingKey();
                dicminebuildingKey.setMinebuildingid(dicresourcemine.getMinebuildingid());
                dicminebuildingKey.setMinebuildinglevel(dicresourcemine.getMinebuildinglevel());
                Dicminebuilding dicminebuilding = dicminebuildingMapper.selectByPrimaryKey(dicminebuildingKey);
                mineInfoVO.setMineBuildingID(dicresourcemine.getMinebuildingid());
                mineInfoVO.setMineBuildingLevel(dicresourcemine.getMinebuildinglevel());
                mineInfoVO.setHealth(dicminebuilding.getHealth());
                mineInfoVO.setProduceResType(dicminebuilding.getProducerestype());
                mineInfoVO.setProducePerHour(dicminebuilding.getProduceperhour());
                mineInfoVO.setStorageLimit(dicminebuilding.getStoragelimit());
                mineInfoVO.setRewardRes((int) (dicminebuilding.getStoragelimit() * AppConfig.MINE_GRAB_NPC_RATE));
                mineInfoVO.setProtectedByItem(false);

                Dicresourceminetype dicresourceminetype = dicresourceminetypeMapper.selectByPrimaryKey(dicresourcemine.getResminetypeid());
                if (dicresourceminetype != null) {
                    mineInfoVO.setNpcBlockHouseNpcInfo(dicresourceminetype.getBlockhousenpcinfo());
                    mineInfoVO.setMineNpcInfo(dicresourceminetype.getMinenpcinfo());
                }
            } else if ((npcresourcemine.getMinestate() == (short) 2) || (npcresourcemine.getMinestate() == (short) 3)) {
                if ((refreshresourcemine != null) && (refreshresourcemine.getIsunlock() != null))
                    mineInfoVO.setUnlock(refreshresourcemine.getIsunlock());

                mineInfoVO.setResMineInstanceID(npcresourcemine.getNpcresmineid());
                mineInfoVO.setResMineID(npcresourcemine.getResmineid());

                PlayerresourcemineExample playerresourcemineExample = new PlayerresourcemineExample();
                playerresourcemineExample.createCriteria().andNpcresmineidEqualTo(npcresourcemine.getNpcresmineid());
                List<Playerresourcemine> playerresourcemineList = playerresourcemineMapper.selectByExample(playerresourcemineExample);

                if (playerresourcemineList.size() > 0) {
                    Playerresourcemine playerresourcemine = (Playerresourcemine) playerresourcemineList.get(0);
                    DicminebuildingKey dicminebuildingKey = new DicminebuildingKey();
                    dicminebuildingKey.setMinebuildingid(playerresourcemine.getMinebuildingid());
                    dicminebuildingKey.setMinebuildinglevel(playerresourcemine.getMinebuildinglevel());
                    Dicminebuilding dicminebuilding = dicminebuildingMapper.selectByPrimaryKey(dicminebuildingKey);
                    if (playerresourcemine.getRoleid() == roleId) {
                        mineInfoVO.setMineState(2);
                        mineInfoVO.setMineBuildingID(playerresourcemine.getMinebuildingid());
                        mineInfoVO.setMineBuildingLevel(playerresourcemine.getMinebuildinglevel());
                        mineInfoVO.setHealth(dicminebuilding.getHealth());
                        mineInfoVO.setProduceResType(dicminebuilding.getProducerestype());
                        mineInfoVO.setProducePerHour(dicminebuilding.getProduceperhour());
                        mineInfoVO.setStorageLimit(dicminebuilding.getStoragelimit());
                    } else {
                        mineInfoVO.setMineState(3);
                        mineInfoVO.setMineBuildingID(playerresourcemine.getMinebuildingid());
                        mineInfoVO.setMineBuildingLevel(playerresourcemine.getMinebuildinglevel());
                        mineInfoVO.setHealth(dicminebuilding.getHealth());
                        mineInfoVO.setProduceResType(dicminebuilding.getProducerestype());
                        mineInfoVO.setProducePerHour(dicminebuilding.getProduceperhour());
                        mineInfoVO.setStorageLimit(dicminebuilding.getStoragelimit());
                        PlayerRoleVO resPlayerRoleVO = playerRoleCacheManager.get(playerresourcemine.getRoleid());
                        mineInfoVO.setOtherRoleId(resPlayerRoleVO.getRoleId());
                        mineInfoVO.setOtherRoleName(resPlayerRoleVO.getRoleName());
                        mineInfoVO.setOtherCampId(resPlayerRoleVO.getCampId());
                        mineInfoVO.setOtherGeneralDegree(resPlayerRoleVO.getRoleLevel());
                        mineInfoVO.setOtherAvatar(resPlayerRoleVO.getRoleAvatar());
                        mineInfoVO.setOtherRoleHonor(resPlayerRoleVO.getRoleHonor());
                        mineInfoVO.setOtherIsOnline(gameServerSendService.isOnline(resPlayerRoleVO.getRoleId()));
                        mineInfoVO.setOtherServerId(resPlayerRoleVO.getServerId());

                        PlayerresourcemineKey playerresourcemineKey = new PlayerresourcemineKey();
                        playerresourcemineKey.setRoleid(resPlayerRoleVO.getRoleId());
                        playerresourcemineKey.setNpcresmineid(npcresourcemine.getNpcresmineid());
                        PlayerresourcemineWithBLOBs playerresourcemineWithBLOBs = playerresourcemineMapper.selectByPrimaryKey(playerresourcemineKey);
                        if (playerresourcemineWithBLOBs != null) {
                            mineInfoVO.setBattleUnitInfo(getMinePlayerBattleUnit(playerresourcemineWithBLOBs));
                            mineInfoVO.setOtherBlockHouseNpcInfo(playerresourcemineWithBLOBs.getBlockhousenpcinfo());
                        }

                        PlayerMineResourceVO playerMineResourceVO = playerMineResourceService.getMineProductResource(playerresourcemine.getRoleid(), playerresourcemine.getNpcresmineid());
                        mineInfoVO.setRewardRes((int) (playerMineResourceVO.getResource() * AppConfig.MINE_GRAB_PLAYER_RATE));
                        mineInfoVO.setOtherServerId(resPlayerRoleVO.getServerId());
                    }
                    mineInfoVO.setProtectedByItem(isProtectedByItem(playerresourcemine.getRoleid()));
                }

            }

            Playerresourceminecollect playerresourceminecollect = playerresourceminecollectMapper.selectByPrimaryKey(npcresourcemine.getNpcresmineid());
            if (playerresourceminecollect != null && playerresourceminecollect.getRoleid() > 0) {
                long currentTime = System.currentTimeMillis();
                // 如果采集时间大于当前时间的话，则删除掉playerresourceminecollect表数据并增加对应资源并写入Log
                if (currentTime > playerresourceminecollect.getCollectendtime()) {
                    sendService.sendCrossAddResource(playerresourceminecollect.getRoleid(), playerresourceminecollect.getProducerestype(), playerresourceminecollect.getRewardresource(), ConsumptionTypeEnum.MINE_COLLECT_REWARD);

                    Resourceminecollectlog record = new Resourceminecollectlog();
                    record.setRoleid(playerresourceminecollect.getRoleid());
                    record.setResmineinstanceid(npcresourcemine.getNpcresmineid());
                    record.setProducerestype(playerresourceminecollect.getProducerestype());
                    record.setRewardresource(playerresourceminecollect.getRewardresource());
                    record.setCollectresult(AppConfig.BATTLE_SUCCESS_RESULT);
                    record.setCollectendtime(playerresourceminecollect.getCollectendtime());
                    resourceminecollectlogMapper.insertSelective(record);

                    playerresourceminecollectMapper.deleteByPrimaryKey(npcresourcemine.getNpcresmineid());
                } else {
                    int collectLeftSecond = (int) ((playerresourceminecollect.getCollectendtime() - currentTime) * 0.001);
                    int collectTotalSecond = (int) ((playerresourceminecollect.getCollectendtime() - playerresourceminecollect.getCollectstarttime()) * 0.001);
                    mineInfoVO.setCollectTotalSecond(collectTotalSecond);
                    mineInfoVO.setCollectLeftSecond(collectLeftSecond);
                    mineInfoVO.setCanCollectNum(playerresourceminecollect.getRewardresource());
                }
            } else {
                Dicresourcemine dicresourcemine = dicresourcemineMapper.selectByPrimaryKey(npcresourcemine.getResmineid());
                DicminebuildingKey dicminebuildingKey = new DicminebuildingKey();
                dicminebuildingKey.setMinebuildingid(dicresourcemine.getMinebuildingid());
                dicminebuildingKey.setMinebuildinglevel(dicresourcemine.getMinebuildinglevel());
                Dicminebuilding dicminebuilding = dicminebuildingMapper.selectByPrimaryKey(dicminebuildingKey);
                int collectResourceNum = (dicminebuilding.getMinebuildinglevel() * dicminebuilding.getMinebuildinglevel() * 100);
                int collectSecond = (dicminebuilding.getMinebuildinglevel() * 30) / 600;
                mineInfoVO.setCollectTotalSecond((collectSecond + 1) * 600);
                mineInfoVO.setCollectLeftSecond(0);
                mineInfoVO.setCanCollectNum(collectResourceNum);
            }
            resultList.add(mineInfoVO);
        }

        return resultList;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public MineInfoVO getMineInfo(int roleId, long resMineID) throws NoSuchRoleException, MineNotExistException, TaskDataException {
        MineInfoVO mineInfoVO = new MineInfoVO();
        if (roleId < 0)
            throw new NoSuchRoleException();

        Npcresourcemine npcresourcemine = npcresourcemineMapper.selectByPrimaryKey(resMineID);
        if (npcresourcemine == null) {
            throw new MineNotExistException();
        }

        RefreshresourcemineKey refreshresourcemineKey = new RefreshresourcemineKey();
        refreshresourcemineKey.setRoleid(roleId);
        refreshresourcemineKey.setNpcresmineid(resMineID);
        Refreshresourcemine refreshresourcemine = refreshresourcemineMapper.selectByPrimaryKey(refreshresourcemineKey);
        if (refreshresourcemine == null) {
            throw new MineNotExistException();
        }

        logger.info("npcresourcemine.getMinestate() === " + npcresourcemine.getMinestate());
        if ((npcresourcemine.getMinestate() == (short) 0) || (npcresourcemine.getMinestate() == (short) 1)) {
            if ((refreshresourcemine != null) && (refreshresourcemine.getIsunlock() != null))
                mineInfoVO.setUnlock(refreshresourcemine.getIsunlock());

            mineInfoVO.setResMineInstanceID(npcresourcemine.getNpcresmineid());
            mineInfoVO.setResMineID(npcresourcemine.getResmineid());
            mineInfoVO.setMineState(1);
            Dicresourcemine dicresourcemine = dicresourcemineMapper.selectByPrimaryKey(npcresourcemine.getResmineid());
            DicminebuildingKey dicminebuildingKey = new DicminebuildingKey();
            dicminebuildingKey.setMinebuildingid(dicresourcemine.getMinebuildingid());
            dicminebuildingKey.setMinebuildinglevel(dicresourcemine.getMinebuildinglevel());
            Dicminebuilding dicminebuilding = dicminebuildingMapper.selectByPrimaryKey(dicminebuildingKey);
            mineInfoVO.setMineBuildingID(dicresourcemine.getMinebuildingid());
            mineInfoVO.setMineBuildingLevel(dicresourcemine.getMinebuildinglevel());
            mineInfoVO.setHealth(dicminebuilding.getHealth());
            mineInfoVO.setProduceResType(dicminebuilding.getProducerestype());
            mineInfoVO.setProducePerHour(dicminebuilding.getProduceperhour());
            mineInfoVO.setStorageLimit(dicminebuilding.getStoragelimit());
            mineInfoVO.setRewardRes((int) (dicminebuilding.getStoragelimit() * AppConfig.MINE_GRAB_NPC_RATE));
            mineInfoVO.setProtectedByItem(false);
            Dicresourceminetype dicresourceminetype = dicresourceminetypeMapper.selectByPrimaryKey(dicresourcemine.getResminetypeid());
            if (dicresourceminetype != null) {
                mineInfoVO.setNpcBlockHouseNpcInfo(dicresourceminetype.getBlockhousenpcinfo());
                mineInfoVO.setMineNpcInfo(dicresourceminetype.getMinenpcinfo());
            }
            logger.info("--------------------mineInfoVO.getRewardRes() = " + mineInfoVO.getRewardRes());
        } else if ((npcresourcemine.getMinestate() == (short) 2) || (npcresourcemine.getMinestate() == (short) 3)) {
            if ((refreshresourcemine != null) && (refreshresourcemine.getIsunlock() != null))
                mineInfoVO.setUnlock(refreshresourcemine.getIsunlock());

            mineInfoVO.setResMineInstanceID(npcresourcemine.getNpcresmineid());
            mineInfoVO.setResMineID(npcresourcemine.getResmineid());

            PlayerresourcemineExample playerresourcemineExample = new PlayerresourcemineExample();
            playerresourcemineExample.createCriteria().andNpcresmineidEqualTo(npcresourcemine.getNpcresmineid());
            List<Playerresourcemine> playerresourcemineList = playerresourcemineMapper.selectByExample(playerresourcemineExample);

            if (playerresourcemineList.size() > 0) {
                Playerresourcemine playerresourcemine = (Playerresourcemine) playerresourcemineList.get(0);
                DicminebuildingKey dicminebuildingKey = new DicminebuildingKey();
                dicminebuildingKey.setMinebuildingid(playerresourcemine.getMinebuildingid());
                dicminebuildingKey.setMinebuildinglevel(playerresourcemine.getMinebuildinglevel());
                Dicminebuilding dicminebuilding = dicminebuildingMapper.selectByPrimaryKey(dicminebuildingKey);
                if (playerresourcemine.getRoleid() == roleId) {
                    mineInfoVO.setMineState(2);
                    mineInfoVO.setMineBuildingID(playerresourcemine.getMinebuildingid());
                    mineInfoVO.setMineBuildingLevel(playerresourcemine.getMinebuildinglevel());
                    mineInfoVO.setHealth(dicminebuilding.getHealth());
                    mineInfoVO.setProduceResType(dicminebuilding.getProducerestype());
                    mineInfoVO.setProducePerHour(dicminebuilding.getProduceperhour());
                    mineInfoVO.setStorageLimit(dicminebuilding.getStoragelimit());
                } else {
                    mineInfoVO.setMineState(3);
                    mineInfoVO.setMineBuildingID(playerresourcemine.getMinebuildingid());
                    mineInfoVO.setMineBuildingLevel(playerresourcemine.getMinebuildinglevel());
                    mineInfoVO.setHealth(dicminebuilding.getHealth());
                    mineInfoVO.setProduceResType(dicminebuilding.getProducerestype());
                    mineInfoVO.setProducePerHour(dicminebuilding.getProduceperhour());
                    mineInfoVO.setStorageLimit(dicminebuilding.getStoragelimit());
                    PlayerRoleVO resPlayerRoleVO = playerRoleCacheManager.get(playerresourcemine.getRoleid());
                    mineInfoVO.setOtherRoleId(resPlayerRoleVO.getRoleId());
                    mineInfoVO.setOtherRoleName(resPlayerRoleVO.getRoleName());
                    mineInfoVO.setOtherCampId(resPlayerRoleVO.getCampId());
                    mineInfoVO.setOtherGeneralDegree(resPlayerRoleVO.getRoleLevel());
                    mineInfoVO.setOtherAvatar(resPlayerRoleVO.getRoleAvatar());
                    mineInfoVO.setOtherRoleHonor(resPlayerRoleVO.getRoleHonor());
                    mineInfoVO.setOtherIsOnline(gameServerSendService.isOnline(resPlayerRoleVO.getRoleId()));
                    mineInfoVO.setOtherServerId(resPlayerRoleVO.getServerId());

                    PlayerresourcemineKey playerresourcemineKey = new PlayerresourcemineKey();
                    playerresourcemineKey.setRoleid(resPlayerRoleVO.getRoleId());
                    playerresourcemineKey.setNpcresmineid(npcresourcemine.getNpcresmineid());
                    PlayerresourcemineWithBLOBs playerresourcemineWithBLOBs = playerresourcemineMapper.selectByPrimaryKey(playerresourcemineKey);
                    if (playerresourcemineWithBLOBs != null) {
                        List<PvpBattleUnitVO> minePlayerBattleUnit = getMinePlayerBattleUnit(playerresourcemineWithBLOBs);
                        mineInfoVO.setBattleUnitInfo(minePlayerBattleUnit);
                        mineInfoVO.setOtherBlockHouseNpcInfo(playerresourcemineWithBLOBs.getBlockhousenpcinfo());
                        mineInfoVO.setUnitInfos(getUnitInfos(resPlayerRoleVO.getRoleId(), minePlayerBattleUnit));
                    }

                    PlayerMineResourceVO playerMineResourceVO = playerMineResourceService.getMineProductResource(playerresourcemine.getRoleid(), playerresourcemine.getNpcresmineid());
                    mineInfoVO.setRewardRes((int) (playerMineResourceVO.getResource() * AppConfig.MINE_GRAB_PLAYER_RATE));
                    logger.info("-------------player-------mineInfoVO.getRewardRes() = " + mineInfoVO.getRewardRes());
                }
                mineInfoVO.setProtectedByItem(isProtectedByItem(playerresourcemine.getRoleid()));
            }
        }

        Playerresourceminecollect playerresourceminecollect = playerresourceminecollectMapper.selectByPrimaryKey(npcresourcemine.getNpcresmineid());
        if (playerresourceminecollect != null && playerresourceminecollect.getRoleid() > 0) {
            long currentTime = System.currentTimeMillis();
            // 如果采集时间大于当前时间的话，则删除掉playerresourceminecollect表数据并增加对应资源并写入Log
            if (currentTime > playerresourceminecollect.getCollectendtime()) {
                sendService.sendCrossAddResource(playerresourceminecollect.getRoleid(), playerresourceminecollect.getProducerestype(), playerresourceminecollect.getRewardresource(), ConsumptionTypeEnum.MINE_COLLECT_REWARD);

                Resourceminecollectlog record = new Resourceminecollectlog();
                record.setRoleid(playerresourceminecollect.getRoleid());
                record.setResmineinstanceid(npcresourcemine.getNpcresmineid());
                record.setProducerestype(playerresourceminecollect.getProducerestype());
                record.setRewardresource(playerresourceminecollect.getRewardresource());
                record.setCollectresult(AppConfig.BATTLE_SUCCESS_RESULT);
                record.setCollectendtime(playerresourceminecollect.getCollectendtime());
                resourceminecollectlogMapper.insertSelective(record);

                playerresourceminecollectMapper.deleteByPrimaryKey(npcresourcemine.getNpcresmineid());
            } else {
                int collectLeftSecond = (int) ((playerresourceminecollect.getCollectendtime() - currentTime) * 0.001);
                int collectTotalSecond = (int) ((playerresourceminecollect.getCollectendtime() - playerresourceminecollect.getCollectstarttime()) * 0.001);
                mineInfoVO.setCollectTotalSecond(collectTotalSecond);
                mineInfoVO.setCollectLeftSecond(collectLeftSecond);
                mineInfoVO.setCanCollectNum(playerresourceminecollect.getRewardresource());
            }
        } else {
            Dicresourcemine dicresourcemine = dicresourcemineMapper.selectByPrimaryKey(npcresourcemine.getResmineid());
            DicminebuildingKey dicminebuildingKey = new DicminebuildingKey();
            dicminebuildingKey.setMinebuildingid(dicresourcemine.getMinebuildingid());
            dicminebuildingKey.setMinebuildinglevel(dicresourcemine.getMinebuildinglevel());
            Dicminebuilding dicminebuilding = dicminebuildingMapper.selectByPrimaryKey(dicminebuildingKey);
            int collectResourceNum = (dicminebuilding.getMinebuildinglevel() * dicminebuilding.getMinebuildinglevel() * 100);
            int collectSecond = (dicminebuilding.getMinebuildinglevel() * 30) / 600;
            mineInfoVO.setCollectTotalSecond((collectSecond + 1) * 600);
            mineInfoVO.setCollectLeftSecond(0);
            mineInfoVO.setCanCollectNum(collectResourceNum);
        }

        return mineInfoVO;
    }


    private List<BattleUnitVO> getUnitInfos(int roleId, List<PvpBattleUnitVO> unitList) throws NoSuchRoleException {

        List<BattleUnitVO> units = Lists.newArrayList();

        Map<Integer, Integer> unitNumMap = Maps.newHashMap();

        for (PvpBattleUnitVO unit : unitList) {
            Integer num = unitNumMap.get(unit.getBattleUnitID());
            if (num == null) {
                unitNumMap.put(unit.getBattleUnitID(), 1);
            } else {
                unitNumMap.put(unit.getBattleUnitID(), num + 1);
            }
        }

        PlayerRoleVO playerRoleVO = playerRoleCacheManager.get(roleId);
        Map<Integer, BattleUnitVO> battleUnitMap = playerRoleVO.getBattleUnitMap();
        for (Entry<Integer, Integer> entry : unitNumMap.entrySet()) {
            BattleUnitVO unit = battleUnitMap.get(entry.getKey());
            if (unit != null) {
                unit.setStorageNum(entry.getValue());
                units.add(unit);
            }
        }

        return units;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public MineDefenseInfoVO getMineDefenseInfo(int roleId, long resMineID) throws NoSuchRoleException, MineNotExistException {
        MineDefenseInfoVO mineDefenseInfoVO = new MineDefenseInfoVO();
        if (roleId < 0)
            throw new NoSuchRoleException();

        PlayerresourcemineKey playerresourcemineKey = new PlayerresourcemineKey();
        playerresourcemineKey.setRoleid(roleId);
        playerresourcemineKey.setNpcresmineid(resMineID);
        PlayerresourcemineWithBLOBs playerresourcemineWithBLOBs = playerresourcemineMapper.selectByPrimaryKey(playerresourcemineKey);
        if (playerresourcemineWithBLOBs == null) {
            throw new MineNotExistException();
        }

        mineDefenseInfoVO.setResMineInstanceID(playerresourcemineWithBLOBs.getNpcresmineid());
        mineDefenseInfoVO.setMineBuildingID(playerresourcemineWithBLOBs.getMinebuildingid());
        mineDefenseInfoVO.setMineBuildingLevel(playerresourcemineWithBLOBs.getMinebuildinglevel());
        if (playerresourcemineWithBLOBs.getBlockhousenpcinfo() != null)
            mineDefenseInfoVO.setBlockHouseNPCInfo(playerresourcemineWithBLOBs.getBlockhousenpcinfo());
        if (playerresourcemineWithBLOBs.getMineinfo() != null)
            mineDefenseInfoVO.setMineInfo(playerresourcemineWithBLOBs.getMineinfo());
        if (playerresourcemineWithBLOBs.getCommanderinstruction() != null)
            mineDefenseInfoVO.setCommanderInstruction(playerresourcemineWithBLOBs.getCommanderinstruction());
        if (playerresourcemineWithBLOBs.getLandmineinfo() != null)
            mineDefenseInfoVO.setLandMineInfo(playerresourcemineWithBLOBs.getLandmineinfo());

        List<PvpBattleUnitVO> resultList = getMinePlayerBattleUnit(playerresourcemineWithBLOBs);
        List<BattleUnitVO> unitInfos = getUnitInfos(roleId, resultList);
        mineDefenseInfoVO.setUnits(unitInfos);
        mineDefenseInfoVO.setBattleUnitInfo(resultList);

        return mineDefenseInfoVO;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int mineDefenseDeploy(int roleId, long resMineID, int battleUnitIndex, List<PvpBattleUnitVO> battleUnitList, String mineInfo, String blockHouseNPCInfo,
                                 int commanderInstruction, String landMineInfo) throws NoSuchRoleException, MineNotExistException, MineBlockHouseErrorException, MineInfoErrorException,
            BattleUnitNotExistException, BattleUnitNotEnoughException, NotReachLevelException {
        int result = 0;
        if (roleId < 0)
            throw new NoSuchRoleException();

        PlayerresourcemineKey playerresourcemineKey = new PlayerresourcemineKey();
        playerresourcemineKey.setRoleid(roleId);
        playerresourcemineKey.setNpcresmineid(resMineID);
        PlayerresourcemineWithBLOBs playerresourcemine = playerresourcemineMapper.selectByPrimaryKey(playerresourcemineKey);
        if (playerresourcemine == null) {
            throw new MineNotExistException();
        }

        Map<Integer, Integer> battleUnitNumMap = new HashMap<Integer, Integer>();
        for (PvpBattleUnitVO battleUnit : battleUnitList) {
            if (battleUnitNumMap.get(battleUnit.getBattleUnitID()) != null) {
                battleUnitNumMap.put(battleUnit.getBattleUnitID(),
                        battleUnitNumMap.get(battleUnit.getBattleUnitID()) + 1);
            } else {
                battleUnitNumMap.put(battleUnit.getBattleUnitID(), 1);
            }
        }

        PlayerRoleVO playerRoleVO = playerRoleCacheManager.get(roleId);
        Map<Integer, BattleUnitVO> battleUnitMap = playerRoleVO.getBattleUnitMap();
        for (PvpBattleUnitVO battleUnit : battleUnitList) {
            logger.info("battleUnit.getBattleUnitID()=======================================" + battleUnit.getBattleUnitID());
            BattleUnitVO playerBattleUnitVO = battleUnitMap.get(battleUnit.getBattleUnitID());
            if (playerBattleUnitVO != null) {
                if ((battleUnitNumMap.get(battleUnit.getBattleUnitID()) != null) &&
                        (battleUnitNumMap.get(battleUnit.getBattleUnitID()) > playerBattleUnitVO.getStorageNum()))
                    throw new BattleUnitNotEnoughException();
            }
        }

        if ((mineInfo != null) && (!"".equals(mineInfo))) {
            playerresourcemine.setMineinfo(mineInfo);
        }
        if ((blockHouseNPCInfo != null) && (!"".equals(blockHouseNPCInfo))) {
            playerresourcemine.setBlockhousenpcinfo(blockHouseNPCInfo);
        }
        playerresourcemine.setCommanderinstruction(commanderInstruction);

        try {
            if (battleUnitIndex == 1) {
                playerresourcemine.setBattleunitinfo1(jacksonObjectMapper.writeValueAsString(battleUnitList));
                playerresourcemine.setBattleunitinfo2("");
                playerresourcemine.setBattleunitinfo3("");
                playerresourcemine.setBattleunitinfo4("");
            } else if (battleUnitIndex == 2) {
                playerresourcemine.setBattleunitinfo2(jacksonObjectMapper.writeValueAsString(battleUnitList));
            } else if (battleUnitIndex == 3) {
                playerresourcemine.setBattleunitinfo3(jacksonObjectMapper.writeValueAsString(battleUnitList));
            } else if (battleUnitIndex == 4) {
                playerresourcemine.setBattleunitinfo4(jacksonObjectMapper.writeValueAsString(battleUnitList));
            } else if (battleUnitIndex == 5) {
                playerresourcemine.setLandmineinfo(landMineInfo);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        result = playerresourcemineMapper.updateByPrimaryKeySelective(playerresourcemine);

        // 新手
        if (playerRoleVO.isGuideSaveFormation()) {
            Npcresourcemine mine = npcresourcemineMapper.selectByPrimaryKey(resMineID);
            if (mine.getResmineid() == 101) {
                sendService.sendCrossGuideFinishStep(roleId, GuidePromptConfig.SAVE_FORMATION);
            }
        }

        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public MineBattleStartVO mineBattleStart(int roleId, long resMineID, List<BattleUnitVO> battleUnitList)
            throws NoSuchRoleException, BattleUnitNotEnoughException, PlayerTonNotEnoughException, BattleUnitNumberZeroException,
            MineNotExistException, BattleUnitNotExistException, MineOwnerOnlineException, MineInstanceUnderAttackedException, NotReachLevelException, SeaException {
        MineBattleStartVO mineBattleStartVO = new MineBattleStartVO();
        if (roleId < 0)
            throw new NoSuchRoleException();

        Npcresourcemine npcresourcemine = npcresourcemineMapper.selectByPrimaryKey(resMineID);
        if (npcresourcemine == null) {
            throw new MineNotExistException();
        }

        if (battleUnitList.size() == 0)
            throw new BattleUnitNumberZeroException();

        PlayerRoleVO playerRoleVO = playerRoleCacheManager.get(roleId);
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

        // 如果玩家还处于资源点保护道具保护期间则保护立即失效
        Playerprotectionbuff buff = playerprotectionbuffMapper.selectByPrimaryKey(roleId);
        if (buff != null) {
            playerprotectionbuffMapper.deleteByPrimaryKey(roleId);
        }

        boolean isPlayer = false;
        if (npcresourcemine.getMinestate() == (int) 2) {
            isPlayer = true;
        }

        if (isPlayer) {
            int resPlayerId = 0;
            PlayerresourcemineExample playerresourcemineExample = new PlayerresourcemineExample();
            playerresourcemineExample.createCriteria().andNpcresmineidEqualTo(resMineID);
            List<PlayerresourcemineWithBLOBs> playerresourcemineList = playerresourcemineMapper.selectByExampleWithBLOBs(playerresourcemineExample);
            PlayerresourcemineWithBLOBs playerresourcemine = null;
            if (playerresourcemineList.size() > 0) {
                playerresourcemine = (PlayerresourcemineWithBLOBs) playerresourcemineList.get(0);
                resPlayerId = playerresourcemine.getRoleid();
                mineBattleStartVO.setPlayerId(resPlayerId);

                //判断该玩家是否在线，如果在线则不能攻击并提示
                if (gameServerSendService.isOnline(resPlayerId)) {
                    throw new MineOwnerOnlineException();
                }
                if (playerMineInstanceAttackedCacheManager.get(resMineID) != null)
                    throw new MineInstanceUnderAttackedException();

                playerMineInstanceAttackedCacheManager.put(resMineID, 0);
            }
            mineBattleStartVO.setMineInfo(playerresourcemine.getMineinfo());
            mineBattleStartVO.setBlockHouseNPCInfo(playerresourcemine.getBlockhousenpcinfo());
            if (playerresourcemine.getCommanderinstruction() != null)
                mineBattleStartVO.setCommanderInstruction(playerresourcemine.getCommanderinstruction());
            if (playerresourcemine.getLandmineinfo() != null)
                mineBattleStartVO.setLandMineInfo(playerresourcemine.getLandmineinfo());

            List<PvpBattleUnitVO> resultList = new ArrayList<PvpBattleUnitVO>();
            if (playerresourcemine.getBattleunitinfo1() != null && !"".equals(playerresourcemine.getBattleunitinfo1())) {
                try {
                    List<PvpBattleUnitVO> playerBattleUnitList = jacksonObjectMapper.readValue(playerresourcemine.getBattleunitinfo1(),
                            new TypeReference<List<PvpBattleUnitVO>>() {
                            });
                    resultList.addAll(playerBattleUnitList);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            if (playerresourcemine.getBattleunitinfo2() != null && !"".equals(playerresourcemine.getBattleunitinfo2())) {
                try {
                    List<PvpBattleUnitVO> playerBattleUnitList = jacksonObjectMapper.readValue(playerresourcemine.getBattleunitinfo2(),
                            new TypeReference<List<PvpBattleUnitVO>>() {
                            });
                    resultList.addAll(playerBattleUnitList);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            if (playerresourcemine.getBattleunitinfo3() != null && !"".equals(playerresourcemine.getBattleunitinfo3())) {
                try {
                    List<PvpBattleUnitVO> playerBattleUnitList = jacksonObjectMapper.readValue(playerresourcemine.getBattleunitinfo3(),
                            new TypeReference<List<PvpBattleUnitVO>>() {
                            });
                    resultList.addAll(playerBattleUnitList);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            if (playerresourcemine.getBattleunitinfo4() != null && !"".equals(playerresourcemine.getBattleunitinfo4())) {
                try {
                    List<PvpBattleUnitVO> playerBattleUnitList = jacksonObjectMapper.readValue(playerresourcemine.getBattleunitinfo4(),
                            new TypeReference<List<PvpBattleUnitVO>>() {
                            });
                    resultList.addAll(playerBattleUnitList);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            Map<Integer, BattleUnitVO> battleUnitIdMap = new HashMap<Integer, BattleUnitVO>();
            List<BattleUnitVO> playerBattleUnitList = new ArrayList<BattleUnitVO>();
            PlayerRoleVO playerRoleVORes = playerRoleCacheManager.get(resPlayerId);
            Map<Integer, BattleUnitVO> battleUnitMapRes = playerRoleVORes.getBattleUnitMap();
            for (PvpBattleUnitVO pvpBattleUnitVO : resultList) {
                if (battleUnitIdMap.get(pvpBattleUnitVO.getBattleUnitID()) == null) {
                    BattleUnitVO battleUnitVO = battleUnitMapRes.get(pvpBattleUnitVO.getBattleUnitID());
                    if (battleUnitVO != null) {
                        playerBattleUnitList.add(battleUnitVO);
                        battleUnitIdMap.put(pvpBattleUnitVO.getBattleUnitID(), battleUnitVO);
                    }
                }
            }
            mineBattleStartVO.setPlayerBattleUnitList(playerBattleUnitList);
            mineBattleStartVO.setBattleUnitMapInfo(resultList);
            mineBattleStartVO.setPlayer(true);

            List<PlayerTacticVO> tacticsList = playerRoleVORes.getTacticsList();

            mineBattleStartVO.setPlayerTactics(tacticsList);
            mineBattleStartVO.setPlayerPower(playerRoleVORes.getRolePower());
            playerMineAttackedCacheManager.put(resPlayerId, resMineID);
            ownMineAttackedCacheManager.put(roleId, resMineID);
            ownPlayerMineAttackedCacheManager.put(roleId, resPlayerId);
        } else {
            Dicresourcemine dicresourcemine = dicresourcemineMapper.selectByPrimaryKey(npcresourcemine.getResmineid());
            Dicresourceminetype dicresourceminetype = dicresourceminetypeMapper.selectByPrimaryKey(dicresourcemine.getResminetypeid());
            mineBattleStartVO.setMineInfo(dicresourceminetype.getMineinfo());
            mineBattleStartVO.setBlockHouseNPCInfo(dicresourceminetype.getBlockhousenpcinfo());
            mineBattleStartVO.setMineNPCInfo(dicresourceminetype.getMinenpcinfo());
            mineBattleStartVO.setNpcTactics(dicresourceminetype.getAvailabletactics());
            mineBattleStartVO.setPlayer(false);

            if (dicresourceminetype.getChangeai() != null) {
                mineBattleStartVO.setChangeAI(dicresourceminetype.getChangeai());
            }
            if (dicresourceminetype.getChangeaitime() != null) {
                mineBattleStartVO.setChangeAITime(dicresourceminetype.getChangeaitime());
            }
        }
        mineBattleStartVO.setLimitTime(180);

        // 新手
        if (playerRoleVO.isGuideBattleStart()) {
            if (npcresourcemine.getResmineid() == 101) {
                sendService.sendCrossGuideFinishStep(roleId, GuidePromptConfig.START_BATTLE);
            }
        }

        return mineBattleStartVO;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public MineBattleEndVO mineBattleEnd(int roleId, long resMineID, int battleRes, List<BattleUnitStartVO> totalBattleUnitList, List<BattleUnitStartVO> damageBattleUnitList)
            throws NoSuchRoleException, MineNotExistException, BattleResultErrorException, DamageBattleUnitTooLargeException,
            BattleUnitNotExistException, BattleUnitNumberZeroException, TaskDataException, CanNotCollectResourceException,
            MineNotPlayerException, MineCannotChangePlayerException, MineChangePlayerNotFoundException,
            JsonParseException, JsonMappingException, IOException, SeaException {
        MineBattleEndVO mineBattleEndVO = new MineBattleEndVO();
        if (roleId < 0)
            throw new NoSuchRoleException();

        Npcresourcemine npcresourcemine = npcresourcemineMapper.selectByPrimaryKey(resMineID);
        if (npcresourcemine == null) {
            throw new MineNotExistException();
        }

        if (totalBattleUnitList.size() == 0)
            throw new BattleUnitNumberZeroException();
        if ((battleRes != AppConfig.BATTLE_SUCCESS_RESULT) && (battleRes != AppConfig.BATTLE_FAILED_RESULT) && (battleRes != AppConfig.BATTLE_TIMEOUT_RESULT))
            throw new BattleResultErrorException();

        List<BattleUnitVO> reduceBattleUnitList = new ArrayList<BattleUnitVO>();

        PlayerRoleVO playerRoleVO = playerRoleCacheManager.get(roleId);
        Map<Integer, BattleUnitVO> battleUnitMap = playerRoleVO.getBattleUnitMap();
        for (BattleUnitStartVO battleUnitVO : damageBattleUnitList) {
            BattleUnitVO playerBattleUnitVO = battleUnitMap.get(battleUnitVO.getBattleUnitID());
            if (playerBattleUnitVO == null) {
                throw new BattleUnitNotExistException();
            } else {
                playerBattleUnitVO.setUnitNum(battleUnitVO.getUnitNum());
                reduceBattleUnitList.add(playerBattleUnitVO);
            }
        }

        sendService.sendCrossReduceBattleUnit(roleId, reduceBattleUnitList);

        boolean isPlayer = false;
        int resPlayerId = 0;
        Playerresourcemine playerresourcemine = null;
        if (npcresourcemine.getMinestate() == (int) 2) {
            isPlayer = true;
            PlayerresourcemineExample playerresourcemineExample = new PlayerresourcemineExample();
            playerresourcemineExample.createCriteria().andNpcresmineidEqualTo(resMineID);
            List<Playerresourcemine> playerresourcemineList = playerresourcemineMapper.selectByExample(playerresourcemineExample);
            if (playerresourcemineList.size() > 0) {
                playerresourcemine = (Playerresourcemine) playerresourcemineList.get(0);
                resPlayerId = playerresourcemine.getRoleid();
            }
        }

        // 如果玩家战斗力差值大于10%，攻击方进攻失败后刷新该点对手，不再可见战斗力差值大于10%的对手
        boolean isRefreshPlayer = false;

        if (isPlayer) {
            int playerGrade = gradeRankingZSetCpt.getScore(roleId);
            int resPlayerGrade = gradeRankingZSetCpt.getScore(resPlayerId);

            double gradeSub = (playerGrade - resPlayerGrade) / playerGrade;

            if ((gradeSub > 0.1) || (gradeSub < -0.1)) {
                isRefreshPlayer = true;
            }
        }

        // 根据战斗结果刷新资源点归属,计算玩家获取资源以及更新资源库存,并刷新资源点
        short produceResType = 0;
        int rewardResource = 0;
        if (battleRes == AppConfig.BATTLE_SUCCESS_RESULT) {
            // 根据战斗结果刷新资源点归属
            if (isPlayer) {
                PlayerresourcemineKey playerresourcemineKey = new PlayerresourcemineKey();
                playerresourcemineKey.setRoleid(resPlayerId);
                playerresourcemineKey.setNpcresmineid(resMineID);
                Playerresourcemine record = playerresourcemineMapper.selectByPrimaryKey(playerresourcemineKey);

                // 根据当前时间计算资源库存
                HavestVO havestVO = playerMineResourceService.getMineBuildingProductivity(resPlayerId, resMineID);

                rewardResource = (int) (havestVO.getHavest() * AppConfig.MINE_GRAB_PLAYER_RATE);
                int leftResource = havestVO.getHavest() - rewardResource;

                playerresourcemineMapper.deleteByPrimaryKey(playerresourcemineKey);

                boolean isAddLevel = true;
                MineattackhistoryKey mineattackhistoryKey = new MineattackhistoryKey();
                mineattackhistoryKey.setAttackerroleid(roleId);
                mineattackhistoryKey.setNpcresmineid(resMineID);
                Mineattackhistory mineattackhistory = mineattackhistoryMapper.selectByPrimaryKey(mineattackhistoryKey);
                if (mineattackhistory == null) {
                    Mineattackhistory mineAttackRecord = new Mineattackhistory();
                    mineAttackRecord.setAttackerroleid(roleId);
                    mineAttackRecord.setNpcresmineid(resMineID);
                    mineAttackRecord.setAttacktimes((short) 1);
                    mineattackhistoryMapper.insertSelective(mineAttackRecord);
                } else {
                    if (mineattackhistory.getAttacktimes() < (short) 1) {
                        mineattackhistory.setAttacktimes((short) (mineattackhistory.getAttacktimes() + 1));
                        mineattackhistoryMapper.updateByPrimaryKeySelective(mineattackhistory);
                    } else {
                        isAddLevel = false;
                    }
                }

                PlayerresourcemineWithBLOBs newRecord = new PlayerresourcemineWithBLOBs();
                newRecord.setRoleid(roleId);
                newRecord.setNpcresmineid(resMineID);
                newRecord.setMinebuildingid(record.getMinebuildingid());

                int level = (int) record.getMinebuildinglevel();
                if (isAddLevel && (level < 100)) {
                    level = level + 1;
                }
                newRecord.setMinebuildinglevel((short) level);

                newRecord.setMineinfo(record.getMineinfo());
                newRecord.setBlockhousenpcinfo(record.getBlockhousenpcinfo());
                DicminebuildingKey dicminebuildingKey = new DicminebuildingKey();
                dicminebuildingKey.setMinebuildingid(record.getMinebuildingid());
                dicminebuildingKey.setMinebuildinglevel((short) level);
                Dicminebuilding dicminebuilding = dicminebuildingMapper.selectByPrimaryKey(dicminebuildingKey);
                newRecord.setProducerestype(dicminebuilding.getProducerestype());
                newRecord.setProduceperhour(dicminebuilding.getProduceperhour());
                newRecord.setStoragelimit(dicminebuilding.getStoragelimit());
                newRecord.setResourcestorage(0);
                newRecord.setStoragecaculatetime(System.currentTimeMillis());
                playerresourcemineMapper.insertSelective(newRecord);
                playerResourceMineHMapCpt.saveOrUpdate(resMineID, roleId);

                // 计算玩家获取资源，如果攻击的是玩家获取玩家库存资源的50%，然后剩余50%存入被攻击玩家的库存
                produceResType = dicminebuilding.getProducerestype();

                sendService.sendCrossAddResource(roleId, dicminebuilding.getProducerestype(), rewardResource, ConsumptionTypeEnum.MINE_BATTLE_REWARD);

                PlayermineleftresourceKey playermineleftresourceKey = new PlayermineleftresourceKey();
                playermineleftresourceKey.setRoleid(resPlayerId);
                playermineleftresourceKey.setProducerestype(produceResType);
                Playermineleftresource playermineleftresource = playermineleftresourceMapper.selectByPrimaryKey(playermineleftresourceKey);
                if (playermineleftresource == null) {
                    Playermineleftresource leftResourceRecord = new Playermineleftresource();
                    leftResourceRecord.setRoleid(resPlayerId);
                    leftResourceRecord.setProducerestype(produceResType);
                    leftResourceRecord.setResourcestorage(leftResource);
                    playermineleftresourceMapper.insertSelective(leftResourceRecord);
                } else {
                    playermineleftresource.setResourcestorage(playermineleftresource.getResourcestorage() + leftResource);
                    playermineleftresourceMapper.updateByPrimaryKeySelective(playermineleftresource);
                }

            } else {
                Dicresourcemine dicresourcemine = dicresourcemineMapper.selectByPrimaryKey(npcresourcemine.getResmineid());
                Dicresourceminetype dicresourceminetype = dicresourceminetypeMapper.selectByPrimaryKey(dicresourcemine.getResminetypeid());
                PlayerresourcemineWithBLOBs newRecord = new PlayerresourcemineWithBLOBs();
                newRecord.setRoleid(roleId);
                newRecord.setNpcresmineid(resMineID);
                newRecord.setMinebuildingid(dicresourcemine.getMinebuildingid());
                int level = (int) dicresourcemine.getMinebuildinglevel();
                if (level < 100) {
                    level = level + 1;
                }
                newRecord.setMinebuildinglevel((short) level);
                newRecord.setMineinfo(dicresourceminetype.getMineinfo());
                newRecord.setBlockhousenpcinfo(dicresourceminetype.getBlockhousenpcinfo());
                DicminebuildingKey dicminebuildingKey = new DicminebuildingKey();
                dicminebuildingKey.setMinebuildingid(dicresourcemine.getMinebuildingid());
                dicminebuildingKey.setMinebuildinglevel((short) level);
                Dicminebuilding dicminebuilding = dicminebuildingMapper.selectByPrimaryKey(dicminebuildingKey);
                newRecord.setProducerestype(dicminebuilding.getProducerestype());
                newRecord.setProduceperhour(dicminebuilding.getProduceperhour());
                newRecord.setStoragelimit(dicminebuilding.getStoragelimit());
                newRecord.setResourcestorage(0);
                newRecord.setStoragecaculatetime(System.currentTimeMillis());
                playerresourcemineMapper.insertSelective(newRecord);
                playerResourceMineHMapCpt.saveOrUpdate(resMineID, roleId);

                // 如果是初始化资源点，玩家攻下后将该资源点状态置为3；如果是npc资源点玩家攻下后将该资源点状态置为2
                if (npcresourcemine.getMinestate() == (short) 0) {
                    npcresourcemine.setMinestate((short) 3);
                } else {
                    npcresourcemine.setMinestate((short) 2);
                }
                npcresourcemineMapper.updateByPrimaryKeySelective(npcresourcemine);

                // 计算玩家获取资源，如果攻击的是NPC一次性获取资源存储上限的5%
                DicminebuildingKey dicminebuildingKeyNpc = new DicminebuildingKey();
                dicminebuildingKeyNpc.setMinebuildingid(dicresourcemine.getMinebuildingid());
                dicminebuildingKeyNpc.setMinebuildinglevel((short) (level - 1));
                Dicminebuilding dicminebuildingNpc = dicminebuildingMapper.selectByPrimaryKey(dicminebuildingKeyNpc);
                rewardResource = (int) (dicminebuildingNpc.getStoragelimit() * AppConfig.MINE_GRAB_NPC_RATE) + 450;
                sendService.sendCrossAddResource(roleId, dicminebuilding.getProducerestype(), rewardResource, ConsumptionTypeEnum.MINE_BATTLE_REWARD);
            }

            // 刷新资源点
            DicunlockresourcemineExample dicunlockresourcemineExample = new DicunlockresourcemineExample();
            dicunlockresourcemineExample.createCriteria().andResmineidEqualTo(npcresourcemine.getResmineid());
            List<Dicunlockresourcemine> unlockResourceMineList = dicunlockresourcemineMapper.selectByExample(dicunlockresourcemineExample);

            for (Dicunlockresourcemine dicunlockresourcemine : unlockResourceMineList) {
                if (isRefreshNewResMineId(roleId, dicunlockresourcemine.getUnlockresmineid())) {
                    if (isRefreshNpc()) {
                        Npcresourcemine record = new Npcresourcemine();
                        record.setResmineid(dicunlockresourcemine.getUnlockresmineid());
                        record.setMinestate((short) 1);
                        npcresourcemineMapper.insert(record);

                        Refreshresourcemine refreshresourcemine = new Refreshresourcemine();
                        refreshresourcemine.setRoleid(roleId);
                        refreshresourcemine.setNpcresmineid(record.getNpcresmineid());
                        refreshresourcemine.setIsunlock(false);
                        refreshresourcemine.setRefreshtime(System.currentTimeMillis());
                        refreshresourcemineMapper.insertSelective(refreshresourcemine);
                    } else {
                        NpcresourcemineExample npcresourcemineExample = new NpcresourcemineExample();
                        npcresourcemineExample.createCriteria()
                                .andResmineidEqualTo(dicunlockresourcemine.getUnlockresmineid())
                                .andMinestateEqualTo((short) 2);
                        List<Npcresourcemine> npcMineList = npcresourcemineMapper.selectByExample(npcresourcemineExample);
                        // 如果解锁资源点ID查找不到玩家占领则刷新一个npc
                        if (npcMineList.size() > 0) {
                            int matchRoleId = 0;
                            Map<Integer, Long> npcRoleMap = new HashMap<Integer, Long>();
                            for (Npcresourcemine npcresourcemineRole : npcMineList) {
                                int npcRoleId = playerResourceMineHMapCpt.getMineRoleId(npcresourcemineRole.getNpcresmineid());
                                if ((npcRoleId > 0) && (npcRoleMap.get(npcRoleId) == null)) {
                                    npcRoleMap.put(npcRoleId, npcresourcemineRole.getNpcresmineid());
                                }
                            }
                            logger.info("roleId ==== " + roleId);
                            for (int key : npcRoleMap.keySet()) {
                                logger.info("key ==== " + key + " npcRoleMap ==== " + npcRoleMap.get(key));
                            }

                            matchRoleId = gradeMatchRoleId(npcRoleMap, playerRoleVO);
                            logger.info("matchRoleId ==== " + matchRoleId);

                            if ((matchRoleId > 0) && (matchRoleId != roleId)) {
                                long matchRoleMine = npcRoleMap.get(matchRoleId);
                                Refreshresourcemine refreshresourcemine = new Refreshresourcemine();
                                refreshresourcemine.setRoleid(roleId);
                                refreshresourcemine.setNpcresmineid(matchRoleMine);
                                refreshresourcemine.setIsunlock(false);
                                refreshresourcemine.setRefreshtime(System.currentTimeMillis());
                                refreshresourcemineMapper.insertSelective(refreshresourcemine);
                            } else {
                                Npcresourcemine record = new Npcresourcemine();
                                record.setResmineid(dicunlockresourcemine.getUnlockresmineid());
                                record.setMinestate((short) 1);
                                npcresourcemineMapper.insert(record);

                                Refreshresourcemine refreshresourcemine = new Refreshresourcemine();
                                refreshresourcemine.setRoleid(roleId);
                                refreshresourcemine.setNpcresmineid(record.getNpcresmineid());
                                refreshresourcemine.setIsunlock(false);
                                refreshresourcemine.setRefreshtime(System.currentTimeMillis());
                                refreshresourcemineMapper.insertSelective(refreshresourcemine);
                            }
                        } else {
                            Npcresourcemine record = new Npcresourcemine();
                            record.setResmineid(dicunlockresourcemine.getUnlockresmineid());
                            record.setMinestate((short) 1);
                            npcresourcemineMapper.insert(record);

                            Refreshresourcemine refreshresourcemine = new Refreshresourcemine();
                            refreshresourcemine.setRoleid(roleId);
                            refreshresourcemine.setNpcresmineid(record.getNpcresmineid());
                            refreshresourcemine.setIsunlock(false);
                            refreshresourcemine.setRefreshtime(System.currentTimeMillis());
                            refreshresourcemineMapper.insertSelective(refreshresourcemine);
                        }
                    }
                }
            }

            notifyMineNum(roleId);
        } else {
            logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++isPlayer=====" + isPlayer +
                    " +++++++++++++++isRefreshPlayer=============" + isRefreshPlayer);
            if (isPlayer) {
                // 如果玩家荣誉差值大于100，攻击方进攻失败后刷新该点对手，不再可见荣誉差值大于100的对手
                if (isRefreshPlayer) {
                    changePlayer(roleId, resMineID, false);
                }
            }
        }

        // 记录战斗日志
        if (isPlayer) {
            Resourceminebattlelog battleLog = new Resourceminebattlelog();
            battleLog.setAttackerroleid(roleId);
            battleLog.setAttackeeroleid(resPlayerId);
            battleLog.setAttackresult((short) battleRes);
            battleLog.setAttacktime(System.currentTimeMillis());
            battleLog.setRewardhonor(0);
            battleLog.setResmineinstanceid(resMineID);
            battleLog.setResmineid(npcresourcemine.getResmineid());
            PlayerRoleVO attackerPlayerRoleVO = playerRoleCacheManager.get(roleId);
            battleLog.setAttackerrolename(attackerPlayerRoleVO.getRoleName());
            battleLog.setAttackercampid((short) attackerPlayerRoleVO.getCampId());
            battleLog.setAttackeravatar(attackerPlayerRoleVO.getRoleAvatar());
            battleLog.setAttackergeneraldegree(attackerPlayerRoleVO.getRoleLevel());
            battleLog.setAttackerrolehonor(attackerPlayerRoleVO.getRoleHonor());
            battleLog.setProducerestype(produceResType);
            battleLog.setRewardresource(rewardResource);

            try {
                battleLog.setAttackerbattleunit(jacksonObjectMapper.writeValueAsString(totalBattleUnitList));
                battleLog.setDamagebattleunit(jacksonObjectMapper.writeValueAsString(damageBattleUnitList));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            resourceminebattlelogMapper.insertSelective(battleLog);
            ownMineAttackedCacheManager.remove(roleId);
            ownPlayerMineAttackedCacheManager.remove(roleId);
            playerMineAttackedCacheManager.remove(resPlayerId);
            playerMineInstanceAttackedCacheManager.remove(resMineID);

            // 判断被攻击资源岛是否正在采集中，如果采集结束则给被攻击方加资源，如果未结束的话则增加采集失败日志
            Playerresourceminecollect playerresourceminecollect = playerresourceminecollectMapper.selectByPrimaryKey(resMineID);
            if (playerresourceminecollect != null && playerresourceminecollect.getRoleid() == resPlayerId) {
                long currentTime = System.currentTimeMillis();
                // 如果采集时间大于当前时间的话，则删除掉playerresourceminecollect表数据并增加对应资源并写入Log
                if (currentTime > playerresourceminecollect.getCollectendtime()) {
                    sendService.sendCrossAddResource(playerresourceminecollect.getRoleid(), playerresourceminecollect.getProducerestype(), playerresourceminecollect.getRewardresource(), ConsumptionTypeEnum.MINE_COLLECT_REWARD);

                    Resourceminecollectlog record = new Resourceminecollectlog();
                    record.setRoleid(playerresourceminecollect.getRoleid());
                    record.setResmineinstanceid(resMineID);
                    record.setProducerestype(playerresourceminecollect.getProducerestype());
                    record.setRewardresource(playerresourceminecollect.getRewardresource());
                    record.setCollectresult(AppConfig.BATTLE_SUCCESS_RESULT);
                    record.setCollectendtime(playerresourceminecollect.getCollectendtime());
                    resourceminecollectlogMapper.insertSelective(record);

                    playerresourceminecollectMapper.deleteByPrimaryKey(npcresourcemine.getNpcresmineid());
                } else if (currentTime >= playerresourceminecollect.getCollectstarttime() && currentTime <= playerresourceminecollect.getCollectendtime()) {
                    Resourceminecollectlog record = new Resourceminecollectlog();
                    record.setRoleid(playerresourceminecollect.getRoleid());
                    record.setResmineinstanceid(resMineID);
                    record.setCollectresult(AppConfig.BATTLE_FAILED_RESULT);
                    record.setCollectendtime(currentTime);
                    record.setAttackerroleid(roleId);
                    record.setAttackerrolename(attackerPlayerRoleVO.getRoleName());
                    resourceminecollectlogMapper.insertSelective(record);
                }
            }

        }

        return mineBattleEndVO;
    }

    /**
     * 播报
     *
     * @param roleId
     * @throws NoSuchRoleException
     */
    private void notifyMineNum(int roleId) throws NoSuchRoleException {

        int num = getPlayerMineNum(roleId);

        if (num / 10 > 0 && num % 10 == 0) {

            PlayerRoleVO playerRoleVO = playerRoleCacheManager.get(roleId);

            List<String> paramList = Lists.newArrayList();
            paramList.add(playerRoleVO.getRoleName());
            paramList.add(num + "");

            sendService.sendCrossMineNumNotify(roleId, playerRoleVO.getRoleName(), num);
        }

    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public List<MineBattleInfoVO> getMineBattleInfoList(int roleId) throws NoSuchRoleException {
        List<MineBattleInfoVO> resultList = new ArrayList<MineBattleInfoVO>();
        if (roleId < 0)
            throw new NoSuchRoleException();

        // 获取一个月内的战斗日志
        long beginDate = System.currentTimeMillis() - 2592000000L;
        ResourceminebattlelogExample resourceminebattlelogExample = new ResourceminebattlelogExample();
        resourceminebattlelogExample.createCriteria().andAttackeeroleidEqualTo(roleId).andAttacktimeGreaterThanOrEqualTo(beginDate);
        resourceminebattlelogExample.setOrderByClause("battleLogID desc");
        List<Resourceminebattlelog> resMineBattleLogList = resourceminebattlelogMapper.selectByExample(resourceminebattlelogExample);
        for (Resourceminebattlelog resourceminebattlelog : resMineBattleLogList) {
            MineBattleInfoVO mineBattleInfoVO = new MineBattleInfoVO();
            mineBattleInfoVO.setBattleResult(resourceminebattlelog.getAttackresult());
            // 将时间转成string型
            mineBattleInfoVO.setBattleTime(resourceminebattlelog.getAttacktime());
            try {
                List<BattleUnitStartVO> attackerBattleUnitList = jacksonObjectMapper.readValue(resourceminebattlelog.getAttackerbattleunit(),
                        new TypeReference<List<BattleUnitStartVO>>() {
                        });
                mineBattleInfoVO.setAttackerBattleUnit(attackerBattleUnitList);
                List<BattleUnitStartVO> damageBattleUnitList = jacksonObjectMapper.readValue(resourceminebattlelog.getDamagebattleunit(),
                        new TypeReference<List<BattleUnitStartVO>>() {
                        });
                mineBattleInfoVO.setDamageBattleUnit(damageBattleUnitList);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            mineBattleInfoVO.setAttackerRoleId(resourceminebattlelog.getAttackerroleid());
            mineBattleInfoVO.setAttackerRoleName(resourceminebattlelog.getAttackerrolename());
            mineBattleInfoVO.setAttackerAvatar(resourceminebattlelog.getAttackeravatar());
            mineBattleInfoVO.setAttackerCampId(resourceminebattlelog.getAttackercampid());
            mineBattleInfoVO.setAttackerGeneralDegree(resourceminebattlelog.getAttackergeneraldegree());
            mineBattleInfoVO.setAttackerRoleHonor(resourceminebattlelog.getAttackerrolehonor());
            mineBattleInfoVO.setAttackerRoleGrade(gradeRankingZSetCpt.getScore(resourceminebattlelog.getAttackerroleid()));
            mineBattleInfoVO.setRewardHonor(resourceminebattlelog.getRewardhonor());
            mineBattleInfoVO.setResMineInstanceID(resourceminebattlelog.getResmineinstanceid());
            mineBattleInfoVO.setResMineID(resourceminebattlelog.getResmineid());
            mineBattleInfoVO.setProduceResType(resourceminebattlelog.getProducerestype());
            mineBattleInfoVO.setRewardResource(resourceminebattlelog.getRewardresource());
            resultList.add(mineBattleInfoVO);
        }
        return resultList;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public MineInfoVO changePlayer(int roleId, long resMineID, boolean isCheckRefreshTime) throws NoSuchRoleException,
            MineNotExistException, MineNotPlayerException, MineCannotChangePlayerException, MineChangePlayerNotFoundException {
        MineInfoVO mineInfoVO = new MineInfoVO();
        if (roleId < 0)
            throw new NoSuchRoleException();

        logger.info(" ++++++++ resMineID =" + resMineID + "+++++++++++++");
        Npcresourcemine npcresourcemine = npcresourcemineMapper.selectByPrimaryKey(resMineID);
        if (npcresourcemine == null) {
            throw new MineNotExistException();
        }

        if (npcresourcemine.getMinestate() != (short) 2) {
            throw new MineNotPlayerException();
        }

        RefreshresourcemineKey refreshresourcemineKeyDel = new RefreshresourcemineKey();
        refreshresourcemineKeyDel.setRoleid(roleId);
        refreshresourcemineKeyDel.setNpcresmineid(resMineID);
        Refreshresourcemine refreshresourcemineDel = refreshresourcemineMapper.selectByPrimaryKey(refreshresourcemineKeyDel);
        if (isCheckRefreshTime) {
            if (refreshresourcemineDel != null) {
                if (refreshresourcemineDel.getIsfirstrefresh()) {
                    long refreshTime = refreshresourcemineDel.getRefreshtime() + 43200000L;
                    if (refreshTime > System.currentTimeMillis()) {
                        throw new MineCannotChangePlayerException();
                    }
                }
            }
        }

        PlayerRoleVO playerRoleVO = playerRoleCacheManager.get(roleId);

        NpcresourcemineExample npcresourcemineExample = new NpcresourcemineExample();
        npcresourcemineExample.createCriteria()
                .andResmineidEqualTo(npcresourcemine.getResmineid())
                .andMinestateEqualTo((short) 2).andNpcresmineidNotEqualTo(resMineID);
        List<Npcresourcemine> npcMineList = npcresourcemineMapper.selectByExample(npcresourcemineExample);
        // 如果解锁资源点ID查找不到玩家占领则刷新一个npc
        if (npcMineList.size() > 0) {
            long matchRoleMine = resMineID;
            int matchRoleId = 0;
            Map<Integer, Long> npcRoleMap = new HashMap<Integer, Long>();
            for (Npcresourcemine npcresourcemineRole : npcMineList) {
                int npcRoleId = playerResourceMineHMapCpt.getMineRoleId(npcresourcemineRole.getNpcresmineid());
                if ((npcRoleId > 0) && (npcRoleMap.get(npcRoleId) == null)) {
                    npcRoleMap.put(npcRoleId, npcresourcemineRole.getNpcresmineid());
                }
            }
            matchRoleId = gradeMatchRoleId(npcRoleMap, playerRoleVO);
            logger.info(" ------------- changePlayer matchRoleId =" + matchRoleId + " ----------------------- + roleId = " + roleId);
            if ((matchRoleId > 0) && (matchRoleId != roleId)) {
                matchRoleMine = npcRoleMap.get(matchRoleId);
                logger.info(" matchRoleMine =" + matchRoleMine);
                refreshresourcemineMapper.deleteByPrimaryKey(refreshresourcemineKeyDel);

                Refreshresourcemine refreshresourcemine = new Refreshresourcemine();
                refreshresourcemine.setRoleid(roleId);
                refreshresourcemine.setNpcresmineid(matchRoleMine);
                refreshresourcemine.setIsunlock(true);
                refreshresourcemine.setIsfirstrefresh(true);
                refreshresourcemine.setRefreshtime(System.currentTimeMillis());
                refreshresourcemineMapper.insertSelective(refreshresourcemine);
            } else {
                if (isCheckRefreshTime) {
                    throw new MineChangePlayerNotFoundException();
                }
            }
            logger.info(" ++++++++ matchRoleMine =" + matchRoleMine + "+++++++++++++");
            npcresourcemine = npcresourcemineMapper.selectByPrimaryKey(matchRoleMine);
            mineInfoVO.setResMineInstanceID(npcresourcemine.getNpcresmineid());
            mineInfoVO.setResMineID(npcresourcemine.getResmineid());

            PlayerresourcemineExample playerresourcemineExample = new PlayerresourcemineExample();
            playerresourcemineExample.createCriteria().andNpcresmineidEqualTo(npcresourcemine.getNpcresmineid());
            List<Playerresourcemine> playerresourcemineList = playerresourcemineMapper.selectByExample(playerresourcemineExample);

            if (playerresourcemineList.size() > 0) {
                Playerresourcemine playerresourcemine = (Playerresourcemine) playerresourcemineList.get(0);
                DicminebuildingKey dicminebuildingKey = new DicminebuildingKey();
                dicminebuildingKey.setMinebuildingid(playerresourcemine.getMinebuildingid());
                dicminebuildingKey.setMinebuildinglevel(playerresourcemine.getMinebuildinglevel());
                Dicminebuilding dicminebuilding = dicminebuildingMapper.selectByPrimaryKey(dicminebuildingKey);

                mineInfoVO.setMineState(3);
                mineInfoVO.setUnlock(true);
                mineInfoVO.setMineBuildingID(playerresourcemine.getMinebuildingid());
                mineInfoVO.setMineBuildingLevel(playerresourcemine.getMinebuildinglevel());
                mineInfoVO.setHealth(dicminebuilding.getHealth());
                mineInfoVO.setProduceResType(dicminebuilding.getProducerestype());
                mineInfoVO.setProducePerHour(dicminebuilding.getProduceperhour());
                mineInfoVO.setStorageLimit(dicminebuilding.getStoragelimit());
                PlayerRoleVO resPlayerRoleVO = playerRoleCacheManager.get(playerresourcemine.getRoleid());
                mineInfoVO.setOtherRoleId(resPlayerRoleVO.getRoleId());
                mineInfoVO.setOtherRoleName(resPlayerRoleVO.getRoleName());
                mineInfoVO.setOtherCampId(resPlayerRoleVO.getCampId());
                mineInfoVO.setOtherGeneralDegree(resPlayerRoleVO.getRoleLevel());
                mineInfoVO.setOtherAvatar(resPlayerRoleVO.getRoleAvatar());
                mineInfoVO.setOtherRoleHonor(resPlayerRoleVO.getRoleHonor());
                mineInfoVO.setOtherServerId(resPlayerRoleVO.getServerId());

                PlayerMineResourceVO playerMineResourceVO = playerMineResourceService.getMineProductResource(playerresourcemine.getRoleid(), playerresourcemine.getNpcresmineid());
                mineInfoVO.setRewardRes((int) (playerMineResourceVO.getResource() * AppConfig.MINE_GRAB_PLAYER_RATE));
                logger.info("-------------player-------mineInfoVO.getRewardRes() = " + mineInfoVO.getRewardRes());
                int honorSub = playerRoleVO.getRoleHonor() - resPlayerRoleVO.getRoleHonor();
                DichonorrewardExample dichonorrewardExample = new DichonorrewardExample();
                dichonorrewardExample.createCriteria()
                        .andBeginhonorLessThanOrEqualTo(playerRoleVO.getRoleHonor())
                        .andEndhonorGreaterThanOrEqualTo(playerRoleVO.getRoleHonor())
                        .andBeginvalueLessThanOrEqualTo(honorSub)
                        .andEndvalueGreaterThanOrEqualTo(honorSub);
                List<Dichonorreward> honorRewardList = dichonorrewardMapper.selectByExample(dichonorrewardExample);
                if (honorRewardList.size() > 0) {
                    Dichonorreward dichonorreward = honorRewardList.get(0);
                    mineInfoVO.setRewardHonor(dichonorreward.getRewardhonor());
                    mineInfoVO.setFailHonor(dichonorreward.getSubhonor());
                }

                PlayerresourcemineKey playerresourcemineKey = new PlayerresourcemineKey();
                playerresourcemineKey.setRoleid(playerresourcemine.getRoleid());
                playerresourcemineKey.setNpcresmineid(npcresourcemine.getNpcresmineid());
                PlayerresourcemineWithBLOBs playerresourcemineWithBLOBs = playerresourcemineMapper.selectByPrimaryKey(playerresourcemineKey);
                if (playerresourcemineWithBLOBs != null) {
                    List<PvpBattleUnitVO> minePlayerBattleUnit = getMinePlayerBattleUnit(playerresourcemineWithBLOBs);
                    mineInfoVO.setBattleUnitInfo(minePlayerBattleUnit);
                    mineInfoVO.setOtherBlockHouseNpcInfo(playerresourcemineWithBLOBs.getBlockhousenpcinfo());
                    mineInfoVO.setUnitInfos(getUnitInfos(playerresourcemine.getRoleid(), minePlayerBattleUnit));
                }
            }
        }
        return mineInfoVO;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int getPlayerMineNum(int roleId) throws NoSuchRoleException {
        int mineNum = 0;
        PlayerresourcemineExample playerresourcemineExample = new PlayerresourcemineExample();
        playerresourcemineExample.createCriteria().andRoleidEqualTo(roleId);
        List<Playerresourcemine> playerresourcemineList = playerresourcemineMapper.selectByExample(playerresourcemineExample);
        mineNum = playerresourcemineList.size();
        return mineNum;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void playerExitMine(int roleId) throws NoSuchRoleException {
        if (roleId < 0)
            throw new NoSuchRoleException();

        Long resMineID = ownMineAttackedCacheManager.get(roleId);
        logger.info("--playerExitMine-----------resMineID========" + resMineID);
        if (resMineID != null) {
            logger.info("--resMineID----------" + resMineID);
            if (playerMineInstanceAttackedCacheManager.get(resMineID) != null) {
                playerMineInstanceAttackedCacheManager.remove(resMineID);
            }
        }
        Integer resPlayerId = ownPlayerMineAttackedCacheManager.get(roleId);
        if (resPlayerId != null) {
            logger.info("--resPlayerId.intValue()----------" + resPlayerId.intValue());
            playerMineAttackedCacheManager.remove(resPlayerId.intValue());
        }
        ownMineAttackedCacheManager.remove(roleId);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void unlockRefreshMine(int roleId, long resMineID) throws NoSuchRoleException, MineNotExistException,
            MineAlreadyUnlockedException, TaskDataException, NotEnoughDiamondException, NotEnoughStoneException,
            NotEnoughOilException, NotEnoughIronException, NotEnoughGoldException, NotEnoughWealthException, NotEnoughResourceException {
        if (roleId < 0)
            throw new NoSuchRoleException();

        RefreshresourcemineKey refreshresourcemineKey = new RefreshresourcemineKey();
        refreshresourcemineKey.setRoleid(roleId);
        refreshresourcemineKey.setNpcresmineid(resMineID);
        Refreshresourcemine refreshresourcemine = refreshresourcemineMapper.selectByPrimaryKey(refreshresourcemineKey);
        if (refreshresourcemine == null) {
            throw new MineNotExistException();
        }
        if (refreshresourcemine.getIsunlock()) {
            throw new MineAlreadyUnlockedException();
        }

        Npcresourcemine npcresourcemine = npcresourcemineMapper.selectByPrimaryKey(resMineID);
        DicunlockresourcemineExample dicunlockresourcemineExample = new DicunlockresourcemineExample();
        dicunlockresourcemineExample.createCriteria().andUnlockresmineidEqualTo(npcresourcemine.getResmineid());
        List<Dicunlockresourcemine> unlockResourceMineList = dicunlockresourcemineMapper.selectByExample(dicunlockresourcemineExample);
        if (unlockResourceMineList.size() > 0) {
            Dicunlockresourcemine dicunlockresourcemine = unlockResourceMineList.get(0);
            Playerrole playerrole = playerRoleMapper.selectByPrimaryKey(roleId);
            int shortage = 0;
            int costGold = 0;
            int cosumeGold = dicunlockresourcemine.getUnlockconsumegold();
            if (cosumeGold > playerrole.getRolegold()) {
                shortage = cosumeGold - playerrole.getRolegold();
                costGold = playerrole.getRolegold();
            } else {
                costGold = cosumeGold;
            }

            int caculateDiamondNum = 0;

            if (shortage > 0) {
                caculateDiamondNum = (int) Math.ceil((shortage * 0.1)
                        / (AppConfig.ROLE_RESOURCE_COST_DIAMOND * 0.1));
            }

            if (playerrole.getRolediamond() < caculateDiamondNum) {
                throw new NotEnoughResourceException();
            }

            ChangePlayerWealthVO changePlayerWealthVO = new ChangePlayerWealthVO(roleId, SmOpCode.HEARTBEAT_RES, ConsumptionTypeEnum.MINE_RESOURCE_UNLOCK, "");
            changePlayerWealthVO.setGold(costGold);
            changePlayerWealthVO.setDiamond(caculateDiamondNum);
            sendService.sendCrossSubResource(roleId, changePlayerWealthVO);

            refreshresourcemine.setIsunlock(true);
            refreshresourcemine.setRefreshtime(System.currentTimeMillis());
            refreshresourcemineMapper.updateByPrimaryKeySelective(refreshresourcemine);
        }

    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int getProtectByItemLeftSeconds(int roleId) {
        int protectByItemLeftSeconds = 0;
        long now = System.currentTimeMillis();

        Playerprotectionbuff buff = playerprotectionbuffMapper.selectByPrimaryKey(roleId);

        if (buff != null) {
            if (buff.getEndtime() > now) {
                protectByItemLeftSeconds = (int) ((buff.getEndtime() - now) * 0.001);
            }
        }

        return protectByItemLeftSeconds;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    private boolean isRefreshNewResMineId(int roleId, int unlockResMineId) {
        boolean isRefreshNewResMineId = false;
        List<Integer> unlockedResMineIdlist = new ArrayList<Integer>();
        RefreshresourcemineExample refreshresourcemineExample = new RefreshresourcemineExample();
        refreshresourcemineExample.createCriteria().andRoleidEqualTo(roleId);
        List<Refreshresourcemine> refreshMineList = refreshresourcemineMapper.selectByExample(refreshresourcemineExample);
        for (Refreshresourcemine refreshresourcemine : refreshMineList) {
            Npcresourcemine npcresourcemine = npcresourcemineMapper.selectByPrimaryKey(refreshresourcemine.getNpcresmineid());
            unlockedResMineIdlist.add(npcresourcemine.getResmineid());
        }
        if (!unlockedResMineIdlist.contains(unlockResMineId)) {
            isRefreshNewResMineId = true;
        }
        return isRefreshNewResMineId;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    private boolean isRefreshNpc() {
        boolean isRefreshNpc = true;
        Minerefreshrecord npcRecord = minerefreshrecordMapper.selectByPrimaryKey(1);
        if (npcRecord == null) {
            Minerefreshrecord newRecord = new Minerefreshrecord();
            newRecord.setMinetypeid(1);
            newRecord.setRefreshnum(1);
            minerefreshrecordMapper.insertSelective(newRecord);
        }
        Minerefreshrecord playerRecord = minerefreshrecordMapper.selectByPrimaryKey(2);
        if (playerRecord == null) {
            Minerefreshrecord newRecord = new Minerefreshrecord();
            newRecord.setMinetypeid(2);
            newRecord.setRefreshnum(1);
            minerefreshrecordMapper.insertSelective(newRecord);
        }

        if ((npcRecord != null) && (playerRecord != null)) {
            logger.info("npcResourceMineList.size()=" + npcRecord.getRefreshnum() + "  playerResourceMineList.size()= " + playerRecord.getRefreshnum());
            double npcRate = (npcRecord.getRefreshnum() * 0.1) / (playerRecord.getRefreshnum() * 0.1);
            logger.info("npcRate===============" + npcRate);
            if (npcRate > 0.66) {
                logger.info("npcRate=" + npcRate);
                isRefreshNpc = false;
                playerRecord.setRefreshnum(playerRecord.getRefreshnum() + 1);
                minerefreshrecordMapper.updateByPrimaryKeySelective(playerRecord);
            } else {
                npcRecord.setRefreshnum(npcRecord.getRefreshnum() + 1);
                minerefreshrecordMapper.updateByPrimaryKeySelective(npcRecord);
            }
        }

        return isRefreshNpc;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    private int gradeMatchRoleId(Map<Integer, Long> npcRoleMap, PlayerRoleVO playerRoleVO) {
        int matchRoleId = 0;
        int playerGrade = gradeRankingZSetCpt.getScore(playerRoleVO.getRoleId());
        matchRoleId = getMatchRoleId(playerRoleVO.getRoleId(), playerGrade, 0.1, npcRoleMap);
        if (matchRoleId == 0) {
            matchRoleId = getMatchRoleId(playerRoleVO.getRoleId(), playerGrade, 0.2, npcRoleMap);
            if (matchRoleId == 0) {
                matchRoleId = getMatchRoleId(playerRoleVO.getRoleId(), playerGrade, 0.4, npcRoleMap);
                if (matchRoleId == 0) {
                    matchRoleId = getMatchRoleId(playerRoleVO.getRoleId(), playerGrade, 0.6, npcRoleMap);
                }
            }
        }
        return matchRoleId;
    }

    private int getMatchRoleId(int roleId, int playerGrade, double queryRate, Map<Integer, Long> npcRoleMap) {
        int matchRoleId = 0;
        int beginGrade = (int) (playerGrade * (1 - queryRate));
        int endGrade = (int) (playerGrade * (1 + queryRate));
        List<Integer> gradeMatchRoleList = gradeRankingZSetCpt.getByScore(beginGrade, endGrade);
        List<Integer> matchRoleList = new ArrayList<Integer>();
        for (Integer id : gradeMatchRoleList) {
            if (npcRoleMap.get(id) != null) {
                matchRoleList.add(id);
            }
        }
        if (matchRoleList.size() > 0) {
            if (matchRoleList.size() == 1) {
                if (matchRoleList.get(0) != roleId) {
                    matchRoleId = matchRoleList.get(0);
                }
            } else {
                // 按照数据长度大小随机取一个
                Random random = new Random();
                int index = random.nextInt(matchRoleList.size() - 1) % (matchRoleList.size());
                if (matchRoleList.get(index) != roleId) {
                    matchRoleId = matchRoleList.get(index);
                }
            }
        }
        return matchRoleId;
    }

    private List<PvpBattleUnitVO> getMinePlayerBattleUnit(PlayerresourcemineWithBLOBs playerresourcemineWithBLOBs) {
        List<PvpBattleUnitVO> resultList = new ArrayList<PvpBattleUnitVO>();
        if (playerresourcemineWithBLOBs.getBattleunitinfo1() != null && !"".equals(playerresourcemineWithBLOBs.getBattleunitinfo1())) {
            try {
                List<PvpBattleUnitVO> playerBattleUnitList = jacksonObjectMapper.readValue(playerresourcemineWithBLOBs.getBattleunitinfo1(),
                        new TypeReference<List<PvpBattleUnitVO>>() {
                        });
                resultList.addAll(playerBattleUnitList);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if (playerresourcemineWithBLOBs.getBattleunitinfo2() != null && !"".equals(playerresourcemineWithBLOBs.getBattleunitinfo2())) {
            try {
                List<PvpBattleUnitVO> playerBattleUnitList = jacksonObjectMapper.readValue(playerresourcemineWithBLOBs.getBattleunitinfo2(),
                        new TypeReference<List<PvpBattleUnitVO>>() {
                        });
                resultList.addAll(playerBattleUnitList);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if (playerresourcemineWithBLOBs.getBattleunitinfo3() != null && !"".equals(playerresourcemineWithBLOBs.getBattleunitinfo3())) {
            try {
                List<PvpBattleUnitVO> playerBattleUnitList = jacksonObjectMapper.readValue(playerresourcemineWithBLOBs.getBattleunitinfo3(),
                        new TypeReference<List<PvpBattleUnitVO>>() {
                        });
                resultList.addAll(playerBattleUnitList);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if (playerresourcemineWithBLOBs.getBattleunitinfo4() != null && !"".equals(playerresourcemineWithBLOBs.getBattleunitinfo4())) {
            try {
                List<PvpBattleUnitVO> playerBattleUnitList = jacksonObjectMapper.readValue(playerresourcemineWithBLOBs.getBattleunitinfo4(),
                        new TypeReference<List<PvpBattleUnitVO>>() {
                        });
                resultList.addAll(playerBattleUnitList);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return resultList;
    }

    private int[][] getTacticsFromJson(String availableTactics) {
        int[][] res = null;
        try {
            res = jacksonObjectMapper.readValue(availableTactics, int[][].class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;
    }

    /**
     * 判断是否该用户资源点和防区是否被保护道具保护
     *
     * @param roleId
     * @return
     */
    public boolean isProtectedByItem(int roleId) {

        boolean result = false;

        long now = System.currentTimeMillis();

        Playerprotectionbuff buff = playerprotectionbuffMapper.selectByPrimaryKey(roleId);

        if (buff != null) {
            if (buff.getEndtime() > now) {
                result = true;
            } else {
                playerprotectionbuffMapper.deleteByPrimaryKey(roleId);
            }
        }

        return result;
    }
}
