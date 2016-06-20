package com.pkuvr.game_server.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.pkuvr.game_server.cache.PlayerRoleCacheManager;
import com.pkuvr.game_server.cache.PlayerZoneAttackedCacheManager;
import com.pkuvr.game_server.cachevo.PlayerRoleVO;
import com.pkuvr.game_server.constant.AppConfig;
import com.pkuvr.game_server.constant.SeaErrorCode;
import com.pkuvr.game_server.constant.SmOpCode;
import com.pkuvr.game_server.dao.*;
import com.pkuvr.game_server.domain.*;
import com.pkuvr.game_server.enumerate.ConsumptionTypeEnum;
import com.pkuvr.game_server.enumerate.ResourcesEnum;
import com.pkuvr.game_server.exception.*;
import com.pkuvr.game_server.network.GameServerSendService;
import com.pkuvr.game_server.redis.BaseZSetCpt;
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

@Service("ZoneService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ZoneService {
    private static final Logger logger = Logger.getLogger(ZoneService.class);
    @Resource
    private PlayerzoneplunderMapper playerzoneplunderMapper;
    @Resource
    private PlayerdefensezoneMapper playerdefensezoneMapper;
    @Resource
    private DefensezonebattlelogMapper defensezonebattlelogMapper;
    @Resource
    private DicresourceminetypeMapper dicresourceminetypeMapper;
    @Resource
    private DicresourcemineMapper dicresourcemineMapper;
    @Resource
    private DicunlockresourcemineMapper dicunlockresourcemineMapper;
    @Resource
    private PlayerroleMapper playerRoleMapper;
    @Resource
    private PlayerprotectionbuffMapper playerprotectionbuffMapper;
    @Resource
    private MineResourceService mineResourceService;
    @Resource
    private MineService mineService;
    @Resource
    private SendService sendService;
    @Resource
    private ObjectMapper jacksonObjectMapper;
    @Resource
    private PlayerRoleCacheManager playerRoleCacheManager;
    @Resource
    private PlayerZoneAttackedCacheManager playerZoneAttackedCacheManager;
    @Resource
    private BaseZSetCpt gradeRankingZSetCpt;
    private GameServerSendService gameServerSendService = GameServerSendService.getInstance();

    public ZoneInfoVO getZoneInfo(int roleId, int resMineID) throws NoSuchRoleException, SeaException {
        ZoneInfoVO zoneInfoVO = new ZoneInfoVO();

        PlayerzoneplunderKey playerzoneplunderKey = new PlayerzoneplunderKey();
        playerzoneplunderKey.setRoleid(roleId);
        playerzoneplunderKey.setResmineid(resMineID);
        Playerzoneplunder playerzoneplunder = playerzoneplunderMapper.selectByPrimaryKey(playerzoneplunderKey);
        if (playerzoneplunder == null || playerzoneplunder.getPlunderroleid() == null) {
            throw new SeaException(SeaErrorCode.ZONE_NOT_EXIST, roleId + "_");
        }

        int plunderRoleId = playerzoneplunder.getPlunderroleid();
        if (plunderRoleId > 0) {
            zoneInfoVO.setResMineID(resMineID);
            zoneInfoVO.setUnlock(playerzoneplunder.getIsunlock());
            long refreshTime = playerzoneplunder.getRefreshtime();
            long timePasted = System.currentTimeMillis() - refreshTime;
            if (timePasted <= AppConfig.ZONE_CHANGE_PLAYER_CD) {
                zoneInfoVO.setChangePlayer(false);
            } else {
                zoneInfoVO.setChangePlayer(true);
            }


            PlayerRoleVO otherPlayerRoleVO = playerRoleCacheManager.get(plunderRoleId);
            zoneInfoVO.setOtherHeaderquarterLevel(otherPlayerRoleVO.getHeaderquartersLevel());
            zoneInfoVO.setOtherRoleId(plunderRoleId);
            zoneInfoVO.setOtherRoleName(otherPlayerRoleVO.getRoleName());
            zoneInfoVO.setOtherCampId(otherPlayerRoleVO.getCampId());
            zoneInfoVO.setOtherGeneralDegree(otherPlayerRoleVO.getRoleLevel());
            zoneInfoVO.setOtherAvatar(otherPlayerRoleVO.getRoleAvatar());
            zoneInfoVO.setOtherRoleGrade(gradeRankingZSetCpt.getScore(plunderRoleId));
            zoneInfoVO.setOtherIsOnline(gameServerSendService.isOnline(plunderRoleId));
            zoneInfoVO.setOtherServerId(otherPlayerRoleVO.getServerId());

            boolean isProtected = false;
            PlayerdefensezoneWithBLOBs playerdefensezoneWithBLOBs = playerdefensezoneMapper.selectByPrimaryKey(plunderRoleId);
            if (playerdefensezoneWithBLOBs != null) {
                List<PvpBattleUnitVO> zonePlayerBattleUnit = getZonePlayerBattleUnit(playerdefensezoneWithBLOBs);
                zoneInfoVO.setBattleUnitInfo(zonePlayerBattleUnit);
                zoneInfoVO.setOtherBlockHouseNpcInfo(playerdefensezoneWithBLOBs.getBlockhousenpcinfo());
                zoneInfoVO.setUnitInfos(getUnitInfos(plunderRoleId, zonePlayerBattleUnit));

                if (playerdefensezoneWithBLOBs.getIseverprotected()) {
                    isProtected = playerdefensezoneWithBLOBs.getIseverprotected();
                } else {
                    if (playerdefensezoneWithBLOBs.getIsprotected()) {
                        long protectedTime = playerdefensezoneWithBLOBs.getProtectedtime();
                        long timePastedZone = System.currentTimeMillis() - protectedTime;
                        if (timePastedZone <= 28800000L) {
                            isProtected = true;
                        }
                    }
                }

                zoneInfoVO.setProtected(isProtected);
            }

            if (!isProtected) {
                int protectLeftSeconds = mineService.getProtectByItemLeftSeconds(plunderRoleId);
                if (protectLeftSeconds > 0) {
                    zoneInfoVO.setProtected(true);
                }
            }

            Playerrole playerroleRes = playerRoleMapper.selectByPrimaryKey(plunderRoleId);
            int goldStorageLimit = otherPlayerRoleVO.getGoldStorageLimit();
            int ironStorageLimit = otherPlayerRoleVO.getIronStorageLimit();
            int oilStorageLimit = otherPlayerRoleVO.getOilStorageLimit();
            int stoneStorageLimit = otherPlayerRoleVO.getStoneStorageLimit();

            // 掠夺资源由三部分组成，玩家已有资源 + 玩家主城建筑库存待收取资源 + 玩家资源点库存待收取资源
            int plunderGoldNum = caculatePlunderResourceNum(playerroleRes.getRolegold(), goldStorageLimit);
            int plunderIronNum = caculatePlunderResourceNum(playerroleRes.getRoleiron(), ironStorageLimit);
            int plunderOilNum = caculatePlunderResourceNum(playerroleRes.getRoleoil(), oilStorageLimit);
            int plunderStoneNum = caculatePlunderResourceNum(playerroleRes.getRolestone(), stoneStorageLimit);

            Map<Integer, Integer> baseResourceMap = otherPlayerRoleVO.getBaseResoucePlunderMap();
            Map<Short, Double> mineResourceMap = mineResourceService.getMinePlunderResourceNum(plunderRoleId);

            if (baseResourceMap != null && baseResourceMap.get(AppConfig.GOLD_FUNCTION_ID) != null)
                plunderGoldNum += baseResourceMap.get(AppConfig.GOLD_FUNCTION_ID).intValue();
            if (mineResourceMap != null && mineResourceMap.get((short) ResourcesEnum.GOLD.getTypeID()) != null)
                plunderGoldNum += mineResourceMap.get((short) ResourcesEnum.GOLD.getTypeID()).intValue();

            if (baseResourceMap != null && baseResourceMap.get(AppConfig.IRON_FUNCTION_ID) != null)
                plunderIronNum += baseResourceMap.get(AppConfig.IRON_FUNCTION_ID).intValue();
            if (mineResourceMap != null && mineResourceMap.get((short) ResourcesEnum.IRON.getTypeID()) != null)
                plunderIronNum += mineResourceMap.get((short) ResourcesEnum.IRON.getTypeID()).intValue();

            if (baseResourceMap != null && baseResourceMap.get(AppConfig.OIL_FUNCTION_ID) != null)
                plunderOilNum += baseResourceMap.get(AppConfig.OIL_FUNCTION_ID).intValue();
            if (mineResourceMap != null && mineResourceMap.get((short) ResourcesEnum.OIL.getTypeID()) != null)
                plunderOilNum += mineResourceMap.get((short) ResourcesEnum.OIL.getTypeID()).intValue();

            if (baseResourceMap != null && baseResourceMap.get(AppConfig.STONE_FUNCTION_ID) != null)
                plunderStoneNum += baseResourceMap.get(AppConfig.STONE_FUNCTION_ID).intValue();
            if (mineResourceMap != null && mineResourceMap.get((short) ResourcesEnum.STONE.getTypeID()) != null)
                plunderStoneNum += mineResourceMap.get((short) ResourcesEnum.STONE.getTypeID()).intValue();

            zoneInfoVO.setRewardGold(plunderGoldNum);
            zoneInfoVO.setRewardIron(plunderIronNum);
            zoneInfoVO.setRewardOil(plunderOilNum);
            zoneInfoVO.setRewardStone(plunderStoneNum);
        }

        return zoneInfoVO;
    }

    public int zoneDefenseDeploy(int roleId, int battleUnitIndex, List<PvpBattleUnitVO> battleUnitList, String blockHouseNPCInfo,
                                 int commanderInstruction, String landMineInfo) throws NoSuchRoleException, MineNotExistException,
            MineBlockHouseErrorException, MineInfoErrorException, BattleUnitNotExistException, BattleUnitNotEnoughException, SeaException {
        int result = 0;
        if (roleId < 0)
            throw new NoSuchRoleException();

        boolean isInsertRecord = false;
        PlayerdefensezoneWithBLOBs playerdefensezone = playerdefensezoneMapper.selectByPrimaryKey(roleId);

        if (playerdefensezone == null) {
            playerdefensezone = new PlayerdefensezoneWithBLOBs();
            playerdefensezone.setRoleid(roleId);
            playerdefensezone.setIsprotected(false);
            playerdefensezone.setIseverprotected(false);
            isInsertRecord = true;
        }


        Map<Integer, Integer> battleUnitNumMap = new HashMap<Integer, Integer>();
        for (PvpBattleUnitVO battleUnit : battleUnitList) {
            if (battleUnitNumMap.get(battleUnit.getBattleUnitID()) != null) {
                battleUnitNumMap.put(battleUnit.getBattleUnitID(), battleUnitNumMap.get(battleUnit.getBattleUnitID()) + 1);
            } else {
                battleUnitNumMap.put(battleUnit.getBattleUnitID(), 1);
            }
        }

        PlayerRoleVO playerRoleVO = playerRoleCacheManager.get(roleId);
        Map<Integer, BattleUnitVO> battleUnitMap = playerRoleVO.getBattleUnitMap();
        for (PvpBattleUnitVO battleUnit : battleUnitList) {
            BattleUnitVO playerBattleUnitVO = battleUnitMap.get(battleUnit.getBattleUnitID());
            if (playerBattleUnitVO == null) {
                throw new BattleUnitNotExistException();
            } else {
                if ((battleUnitNumMap.get(battleUnit.getBattleUnitID()) != null) &&
                        (battleUnitNumMap.get(battleUnit.getBattleUnitID()) > playerBattleUnitVO.getStorageNum()))
                    throw new BattleUnitNotEnoughException();
            }
        }


        if ((blockHouseNPCInfo != null) && (!"".equals(blockHouseNPCInfo))) {
            playerdefensezone.setBlockhousenpcinfo(blockHouseNPCInfo);
        }
        playerdefensezone.setCommanderinstruction(commanderInstruction);

        try {
            if (battleUnitIndex == 1) {
                playerdefensezone.setBattleunitinfo1(jacksonObjectMapper.writeValueAsString(battleUnitList));
                playerdefensezone.setBattleunitinfo2("");
                playerdefensezone.setBattleunitinfo3("");
                playerdefensezone.setBattleunitinfo4("");
            } else if (battleUnitIndex == 2) {
                playerdefensezone.setBattleunitinfo2(jacksonObjectMapper.writeValueAsString(battleUnitList));
            } else if (battleUnitIndex == 3) {
                playerdefensezone.setBattleunitinfo3(jacksonObjectMapper.writeValueAsString(battleUnitList));
            } else if (battleUnitIndex == 4) {
                playerdefensezone.setBattleunitinfo4(jacksonObjectMapper.writeValueAsString(battleUnitList));
            } else if (battleUnitIndex == 5) {
                playerdefensezone.setLandmineinfo(landMineInfo);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if (isInsertRecord) {
            result = playerdefensezoneMapper.insertSelective(playerdefensezone);
        } else {
            result = playerdefensezoneMapper.updateByPrimaryKeySelective(playerdefensezone);
        }

        return result;
    }

    public ZoneDefenseInfoVO getZoneDefenseInfo(int roleId) throws NoSuchRoleException, MineNotExistException, SeaException {
        ZoneDefenseInfoVO zoneDefenseInfoVO = new ZoneDefenseInfoVO();
        if (roleId < 0)
            throw new NoSuchRoleException();

        zoneDefenseInfoVO.setHeaderquartersLevel(playerRoleCacheManager.get(roleId).getHeaderquartersLevel());

        PlayerdefensezoneWithBLOBs playerdefensezoneWithBLOBs = playerdefensezoneMapper.selectByPrimaryKey(roleId);
        if (playerdefensezoneWithBLOBs != null) {
            if (playerdefensezoneWithBLOBs.getBlockhousenpcinfo() != null)
                zoneDefenseInfoVO.setBlockHouseNPCInfo(playerdefensezoneWithBLOBs.getBlockhousenpcinfo());
            if (playerdefensezoneWithBLOBs.getCommanderinstruction() != null)
                zoneDefenseInfoVO.setCommanderInstruction(playerdefensezoneWithBLOBs.getCommanderinstruction());
            if (playerdefensezoneWithBLOBs.getLandmineinfo() != null)
                zoneDefenseInfoVO.setLandMineInfo(playerdefensezoneWithBLOBs.getLandmineinfo());

            List<PvpBattleUnitVO> resultList = getZonePlayerBattleUnit(playerdefensezoneWithBLOBs);
            List<BattleUnitVO> unitInfos = getUnitInfos(roleId, resultList);
            zoneDefenseInfoVO.setUnits(unitInfos);
            zoneDefenseInfoVO.setBattleUnitInfo(resultList);
        }

        boolean isProtected = false;
        if (playerdefensezoneWithBLOBs != null && playerdefensezoneWithBLOBs.getIseverprotected() != null) {
            if (playerdefensezoneWithBLOBs.getIseverprotected()) {
                isProtected = playerdefensezoneWithBLOBs.getIseverprotected();
            } else {
                if (playerdefensezoneWithBLOBs.getIsprotected()) {
                    long protectedTime = playerdefensezoneWithBLOBs.getProtectedtime();
                    long timePasted = System.currentTimeMillis() - protectedTime;
                    if (timePasted <= 28800000L) {
                        isProtected = true;
                    }
                }
            }
        }

        if (!isProtected) {
            int protectLeftSeconds = mineService.getProtectByItemLeftSeconds(roleId);
            if (protectLeftSeconds > 0) {
                isProtected = true;
            }
        }

        zoneDefenseInfoVO.setProtected(isProtected);

        return zoneDefenseInfoVO;
    }

    public List<ZoneInfoVO> getZoneList(int roleId) throws NoSuchRoleException, SeaException {
        List<ZoneInfoVO> resultList = new ArrayList<ZoneInfoVO>();
        PlayerzoneplunderExample playerzoneplunderExample = new PlayerzoneplunderExample();
        playerzoneplunderExample.createCriteria().andRoleidEqualTo(roleId);
        List<Playerzoneplunder> playerZoneList = playerzoneplunderMapper.selectByExample(playerzoneplunderExample);

        if ((playerZoneList == null) || (playerZoneList.size() == 0)) {
            int resMineTypeId = 0;
            DicresourceminetypeExample dicresourceminetypeExample = new DicresourceminetypeExample();
            dicresourceminetypeExample.createCriteria().andRestypeEqualTo((short) 3);
            List<Dicresourceminetype> dicResourceMineTypeList = dicresourceminetypeMapper.selectByExample(dicresourceminetypeExample);
            if (dicResourceMineTypeList != null && dicResourceMineTypeList.size() > 0) {
                Dicresourceminetype dicresourceminetype = dicResourceMineTypeList.get(0);
                resMineTypeId = dicresourceminetype.getResminetypeid();
            }

            DicresourcemineExample dicresourcemineExample = new DicresourcemineExample();
            dicresourcemineExample.createCriteria().andResminetypeidEqualTo(resMineTypeId);
            dicresourcemineExample.setOrderByClause("resMineID asc");
            List<Dicresourcemine> dicResourceMineList = dicresourcemineMapper.selectByExample(dicresourcemineExample);
            for (int i = 0; i < 2; i++) {
                Dicresourcemine dicresourcemine = dicResourceMineList.get(i);
                ZoneInfoVO zoneInfoVO = refreshPlayerZone(roleId, dicresourcemine.getResmineid(), true);
                Playerzoneplunder record = new Playerzoneplunder();
                record.setRoleid(roleId);
                record.setResmineid(dicresourcemine.getResmineid());
                record.setIsplunder(false);
                record.setPlunderroleid(zoneInfoVO.getOtherRoleId());
                record.setRefreshtime(System.currentTimeMillis());
                record.setIsunlock(true);
                playerzoneplunderMapper.insertSelective(record);
                playerZoneList.add(record);
            }
        }

        for (Playerzoneplunder playerzoneplunder : playerZoneList) {
            ZoneInfoVO zoneInfoVO = refreshPlayerZone(roleId, playerzoneplunder.getResmineid(), false);
            if (zoneInfoVO.getResMineID() > 0) {
                resultList.add(zoneInfoVO);
            }
        }

        return resultList;
    }

    public ZoneBattleStartVO zoneBattleStart(int roleId, int resMineID, List<BattleUnitVO> battleUnitList)
            throws NoSuchRoleException, BattleUnitNotEnoughException, PlayerTonNotEnoughException, BattleUnitNumberZeroException,
            MineNotExistException, BattleUnitNotExistException, MineInstanceUnderAttackedException, NotReachLevelException, SeaException {
        ZoneBattleStartVO zoneBattleStartVO = new ZoneBattleStartVO();
        if (roleId < 0)
            throw new NoSuchRoleException();

        PlayerdefensezoneWithBLOBs playerdefensezoneOwnWithBLOBs = playerdefensezoneMapper.selectByPrimaryKey(roleId);
        if (playerdefensezoneOwnWithBLOBs != null) {
            if (playerdefensezoneOwnWithBLOBs.getIseverprotected() || playerdefensezoneOwnWithBLOBs.getIsprotected()) {
                playerdefensezoneOwnWithBLOBs.setIseverprotected(false);
                playerdefensezoneOwnWithBLOBs.setIsprotected(false);
                playerdefensezoneMapper.updateByPrimaryKeySelective(playerdefensezoneOwnWithBLOBs);
            }
        }

        PlayerzoneplunderKey playerzoneplunderKey = new PlayerzoneplunderKey();
        playerzoneplunderKey.setRoleid(roleId);
        playerzoneplunderKey.setResmineid(resMineID);
        Playerzoneplunder playerzoneplunder = playerzoneplunderMapper.selectByPrimaryKey(playerzoneplunderKey);
        if (playerzoneplunder == null || playerzoneplunder.getPlunderroleid() == null) {
            throw new SeaException(SeaErrorCode.ZONE_NOT_EXIST, roleId + "_");
        }

        PlayerdefensezoneWithBLOBs playerdefensezoneWithBLOBs = playerdefensezoneMapper.selectByPrimaryKey(playerzoneplunder.getPlunderroleid());
        if (playerdefensezoneWithBLOBs == null) {
            throw new SeaException(SeaErrorCode.ZONE_NOT_EXIST, roleId + "_");
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


        int resPlayerId = playerzoneplunder.getPlunderroleid();
        zoneBattleStartVO.setPlayerId(resPlayerId);
        zoneBattleStartVO.setHeaderquartersLevel(playerRoleCacheManager.get(resPlayerId).getHeaderquartersLevel());

        //判断该玩家是否在线，如果在线则不能攻击并提示
        if (gameServerSendService.isOnline(resPlayerId)) {
            throw new SeaException(SeaErrorCode.ZONE_OWNER_ONLINE, roleId + "_");
        }

        //判断该玩家防区是否被攻击，如果是则不能攻击并提示
        if (playerZoneAttackedCacheManager.get(resPlayerId) != null) {
            throw new SeaException(SeaErrorCode.ZONE_UNDER_ATTACKTED, roleId + "_");
        }

        //判断该玩家是否在保护状态，而且判断该玩家一天之内是否被攻击5次以上
        if (playerdefensezoneWithBLOBs.getIseverprotected()) {
            throw new SeaException(SeaErrorCode.ZONE_UNDER_PROTECTED, roleId + "_");
        }
        if (playerdefensezoneWithBLOBs.getIsprotected()) {
            long protectedTime = playerdefensezoneWithBLOBs.getProtectedtime();
            long timePasted = System.currentTimeMillis() - protectedTime;
            if (timePasted <= 28800000L) {
                throw new SeaException(SeaErrorCode.ZONE_UNDER_PROTECTED, roleId + "_");
            }
        }

        //判断玩家是否处于保护道具保护下
        if (mineService.isProtectedByItem(resPlayerId)) {
            throw new SeaException(SeaErrorCode.ZONE_UNDER_PROTECTED_BY_ITEM, roleId + "_");
        }

        // 如果玩家还处于资源点保护道具保护期间则保护立即失效
        Playerprotectionbuff buff = playerprotectionbuffMapper.selectByPrimaryKey(roleId);
        if (buff != null) {
            playerprotectionbuffMapper.deleteByPrimaryKey(roleId);
        }


        zoneBattleStartVO.setBlockHouseNPCInfo(playerdefensezoneWithBLOBs.getBlockhousenpcinfo());
        if (playerdefensezoneWithBLOBs.getCommanderinstruction() != null)
            zoneBattleStartVO.setCommanderInstruction(playerdefensezoneWithBLOBs.getCommanderinstruction());
        if (playerdefensezoneWithBLOBs.getLandmineinfo() != null)
            zoneBattleStartVO.setLandMineInfo(playerdefensezoneWithBLOBs.getLandmineinfo());

        List<PvpBattleUnitVO> resultList = new ArrayList<PvpBattleUnitVO>();
        if (playerdefensezoneWithBLOBs.getBattleunitinfo1() != null && !"".equals(playerdefensezoneWithBLOBs.getBattleunitinfo1())) {
            try {
                List<PvpBattleUnitVO> playerBattleUnitList = jacksonObjectMapper.readValue(playerdefensezoneWithBLOBs.getBattleunitinfo1(),
                        new TypeReference<List<PvpBattleUnitVO>>() {
                        });
                resultList.addAll(playerBattleUnitList);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if (playerdefensezoneWithBLOBs.getBattleunitinfo2() != null && !"".equals(playerdefensezoneWithBLOBs.getBattleunitinfo2())) {
            try {
                List<PvpBattleUnitVO> playerBattleUnitList = jacksonObjectMapper.readValue(playerdefensezoneWithBLOBs.getBattleunitinfo2(),
                        new TypeReference<List<PvpBattleUnitVO>>() {
                        });
                resultList.addAll(playerBattleUnitList);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if (playerdefensezoneWithBLOBs.getBattleunitinfo3() != null && !"".equals(playerdefensezoneWithBLOBs.getBattleunitinfo3())) {
            try {
                List<PvpBattleUnitVO> playerBattleUnitList = jacksonObjectMapper.readValue(playerdefensezoneWithBLOBs.getBattleunitinfo3(),
                        new TypeReference<List<PvpBattleUnitVO>>() {
                        });
                resultList.addAll(playerBattleUnitList);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if (playerdefensezoneWithBLOBs.getBattleunitinfo4() != null && !"".equals(playerdefensezoneWithBLOBs.getBattleunitinfo4())) {
            try {
                List<PvpBattleUnitVO> playerBattleUnitList = jacksonObjectMapper.readValue(playerdefensezoneWithBLOBs.getBattleunitinfo4(),
                        new TypeReference<List<PvpBattleUnitVO>>() {
                        });
                resultList.addAll(playerBattleUnitList);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        PlayerRoleVO playerRoleVORes = playerRoleCacheManager.get(resPlayerId);
        Map<Integer, BattleUnitVO> battleUnitIdMapRes = playerRoleVORes.getBattleUnitMap();
        Map<Integer, BattleUnitVO> battleUnitIdMap = new HashMap<Integer, BattleUnitVO>();
        List<BattleUnitVO> playerBattleUnitList = new ArrayList<BattleUnitVO>();
        for (PvpBattleUnitVO pvpBattleUnitVO : resultList) {
            if (battleUnitIdMap.get(pvpBattleUnitVO.getBattleUnitID()) == null) {
                BattleUnitVO battleUnitVO = battleUnitIdMapRes.get(pvpBattleUnitVO.getBattleUnitID());
                if (battleUnitVO != null) {
                    playerBattleUnitList.add(battleUnitVO);
                    battleUnitIdMap.put(pvpBattleUnitVO.getBattleUnitID(), battleUnitVO);
                }
            }
        }

        zoneBattleStartVO.setPlayerBattleUnitList(playerBattleUnitList);
        zoneBattleStartVO.setBattleUnitMapInfo(resultList);

        List<PlayerTacticVO> tacticsList = playerRoleVORes.getTacticsList();
        zoneBattleStartVO.setPlayerTactics(tacticsList);
        zoneBattleStartVO.setPlayerPower(playerRoleVORes.getRolePower());
        zoneBattleStartVO.setLimitTime(180);

        playerZoneAttackedCacheManager.put(resPlayerId, resPlayerId);
        return zoneBattleStartVO;
    }

    public ZoneBattleEndVO zoneBattleEnd(int roleId, int resMineID, int battleRes, List<BattleUnitStartVO> totalBattleUnitList, List<BattleUnitStartVO> damageBattleUnitList)
            throws NoSuchRoleException, MineNotExistException, BattleResultErrorException, DamageBattleUnitTooLargeException,
            BattleUnitNotExistException, BattleUnitNumberZeroException, TaskDataException, CanNotCollectResourceException,
            MineNotPlayerException, MineCannotChangePlayerException, MineChangePlayerNotFoundException,
            JsonParseException, JsonMappingException, IOException, NotEnoughWealthException, NotEnoughDiamondException,
            NotEnoughStoneException, NotEnoughOilException, NotEnoughIronException, NotEnoughGoldException, SeaException {
        ZoneBattleEndVO zoneBattleEndVO = new ZoneBattleEndVO();
        if (roleId < 0)
            throw new NoSuchRoleException();

        PlayerzoneplunderKey playerzoneplunderKey = new PlayerzoneplunderKey();
        playerzoneplunderKey.setRoleid(roleId);
        playerzoneplunderKey.setResmineid(resMineID);
        Playerzoneplunder playerzoneplunder = playerzoneplunderMapper.selectByPrimaryKey(playerzoneplunderKey);
        if (playerzoneplunder == null || playerzoneplunder.getPlunderroleid() == null) {
            throw new SeaException(SeaErrorCode.ZONE_NOT_EXIST, roleId + "_");
        }

        PlayerdefensezoneWithBLOBs playerdefensezoneWithBLOBs = playerdefensezoneMapper.selectByPrimaryKey(playerzoneplunder.getPlunderroleid());
        if (playerdefensezoneWithBLOBs == null) {
            throw new SeaException(SeaErrorCode.ZONE_NOT_EXIST, roleId + "_");
        }

        if (totalBattleUnitList.size() == 0)
            throw new BattleUnitNumberZeroException();
        if ((battleRes != AppConfig.BATTLE_SUCCESS_RESULT) && (battleRes != AppConfig.BATTLE_FAILED_RESULT))
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

        Defensezonebattlelog battleLog = new Defensezonebattlelog();
        battleLog.setAttackerroleid(roleId);
        battleLog.setAttackeeroleid(playerzoneplunder.getPlunderroleid());
        battleLog.setAttackresult((short) battleRes);
        battleLog.setAttacktime(System.currentTimeMillis());
        battleLog.setResmineid(resMineID);
        PlayerRoleVO attackerPlayerRoleVO = playerRoleCacheManager.get(roleId);
        battleLog.setAttackerrolename(attackerPlayerRoleVO.getRoleName());
        battleLog.setAttackercampid((short) attackerPlayerRoleVO.getCampId());
        battleLog.setAttackeravatar(attackerPlayerRoleVO.getRoleAvatar());
        battleLog.setAttackergeneraldegree(attackerPlayerRoleVO.getRoleLevel());
        battleLog.setAttackerrolegrade(gradeRankingZSetCpt.getScore(roleId));
        battleLog.setAttackeerolegrade(gradeRankingZSetCpt.getScore(playerzoneplunder.getPlunderroleid()));

        try {
            battleLog.setAttackerbattleunit(jacksonObjectMapper.writeValueAsString(totalBattleUnitList));
            battleLog.setDamagebattleunit(jacksonObjectMapper.writeValueAsString(damageBattleUnitList));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        int pluderGoldNum = 0;
        int pluderIronNum = 0;
        int pluderOilNum = 0;
        int pluderStoneNum = 0;
        if (battleRes == AppConfig.BATTLE_SUCCESS_RESULT) {
            Playerrole playerroleRes = playerRoleMapper.selectByPrimaryKey(playerzoneplunder.getPlunderroleid());
            PlayerRoleVO playerRoleVORes = playerRoleCacheManager.get(playerzoneplunder.getPlunderroleid());
            int goldStorageLimit = playerRoleVORes.getGoldStorageLimit();
            int ironStorageLimit = playerRoleVORes.getIronStorageLimit();
            int oilStorageLimit = playerRoleVORes.getOilStorageLimit();
            int stoneStorageLimit = playerRoleVORes.getStoneStorageLimit();
            pluderGoldNum = caculatePlunderResourceNum(playerroleRes.getRolegold(), goldStorageLimit);
            int middlePluderGoldNum = pluderGoldNum;
            pluderIronNum = caculatePlunderResourceNum(playerroleRes.getRoleiron(), ironStorageLimit);
            int middlePluderIronNum = pluderIronNum;
            pluderOilNum = caculatePlunderResourceNum(playerroleRes.getRoleoil(), oilStorageLimit);
            int middlePluderOilNum = pluderOilNum;
            pluderStoneNum = caculatePlunderResourceNum(playerroleRes.getRolestone(), stoneStorageLimit);
            int middlePluderStoneNum = pluderStoneNum;

            // 掠夺资源由三部分组成，玩家已有资源 + 玩家主城建筑库存待收取资源 + 玩家资源点库存待收取资源
            Map<Integer, Integer> baseResourceMap = playerRoleVORes.getBaseResoucePlunderMap();
            Map<Short, Double> mineResourceMap = mineResourceService.getMinePlunderResourceNum(playerzoneplunder.getPlunderroleid());

            if (baseResourceMap != null && baseResourceMap.get(AppConfig.GOLD_FUNCTION_ID) != null)
                pluderGoldNum += baseResourceMap.get(AppConfig.GOLD_FUNCTION_ID).intValue();
            if (mineResourceMap != null && mineResourceMap.get((short) ResourcesEnum.GOLD.getTypeID()) != null)
                pluderGoldNum += mineResourceMap.get((short) ResourcesEnum.GOLD.getTypeID()).intValue();

            if (baseResourceMap != null && baseResourceMap.get(AppConfig.IRON_FUNCTION_ID) != null)
                pluderIronNum += baseResourceMap.get(AppConfig.IRON_FUNCTION_ID).intValue();
            if (mineResourceMap != null && mineResourceMap.get((short) ResourcesEnum.IRON.getTypeID()) != null)
                pluderIronNum += mineResourceMap.get((short) ResourcesEnum.IRON.getTypeID()).intValue();

            if (baseResourceMap != null && baseResourceMap.get(AppConfig.OIL_FUNCTION_ID) != null)
                pluderOilNum += baseResourceMap.get(AppConfig.OIL_FUNCTION_ID).intValue();
            if (mineResourceMap != null && mineResourceMap.get((short) ResourcesEnum.OIL.getTypeID()) != null)
                pluderOilNum += mineResourceMap.get((short) ResourcesEnum.OIL.getTypeID()).intValue();

            if (baseResourceMap != null && baseResourceMap.get(AppConfig.STONE_FUNCTION_ID) != null)
                pluderStoneNum += baseResourceMap.get(AppConfig.STONE_FUNCTION_ID).intValue();
            if (mineResourceMap != null && mineResourceMap.get((short) ResourcesEnum.STONE.getTypeID()) != null)
                pluderStoneNum += mineResourceMap.get((short) ResourcesEnum.STONE.getTypeID()).intValue();

            if (pluderGoldNum > 0) {
                sendService.sendCrossAddResource(roleId, (short) 1, pluderGoldNum, ConsumptionTypeEnum.ZONE_RESOURCE_PLUNDER);

                if (middlePluderGoldNum > 0) {
                    ChangePlayerWealthVO changePlayerWealthVO = new ChangePlayerWealthVO(playerzoneplunder.getPlunderroleid(), SmOpCode.HEARTBEAT_RES, ConsumptionTypeEnum.ZONE_RESOURCE_PLUNDER, "");
                    changePlayerWealthVO.setGold(middlePluderGoldNum);
                    sendService.sendCrossSubResource(playerzoneplunder.getPlunderroleid(), changePlayerWealthVO);
                }
                sendService.sendCrossReducePlunderResource(playerzoneplunder.getPlunderroleid());
                mineResourceService.reduceMinePlunderResource(playerzoneplunder.getPlunderroleid());
            }

            if (pluderIronNum > 0) {
                sendService.sendCrossAddResource(roleId, (short) 4, pluderIronNum, ConsumptionTypeEnum.ZONE_RESOURCE_PLUNDER);

                if (middlePluderIronNum > 0) {
                    ChangePlayerWealthVO changePlayerWealthVO = new ChangePlayerWealthVO(playerzoneplunder.getPlunderroleid(), SmOpCode.HEARTBEAT_RES, ConsumptionTypeEnum.ZONE_RESOURCE_PLUNDER, "");
                    changePlayerWealthVO.setIron(middlePluderIronNum);
                    sendService.sendCrossSubResource(playerzoneplunder.getPlunderroleid(), changePlayerWealthVO);
                }
                sendService.sendCrossReducePlunderResource(playerzoneplunder.getPlunderroleid());
                mineResourceService.reduceMinePlunderResource(playerzoneplunder.getPlunderroleid());
            }

            if (pluderOilNum > 0) {
                sendService.sendCrossAddResource(roleId, (short) 3, pluderOilNum, ConsumptionTypeEnum.ZONE_RESOURCE_PLUNDER);

                if (middlePluderOilNum > 0) {
                    ChangePlayerWealthVO changePlayerWealthVO = new ChangePlayerWealthVO(playerzoneplunder.getPlunderroleid(), SmOpCode.HEARTBEAT_RES, ConsumptionTypeEnum.ZONE_RESOURCE_PLUNDER, "");
                    changePlayerWealthVO.setOil(middlePluderOilNum);
                    sendService.sendCrossSubResource(playerzoneplunder.getPlunderroleid(), changePlayerWealthVO);
                }
                sendService.sendCrossReducePlunderResource(playerzoneplunder.getPlunderroleid());
                mineResourceService.reduceMinePlunderResource(playerzoneplunder.getPlunderroleid());
            }

            if (pluderStoneNum > 0) {
                sendService.sendCrossAddResource(roleId, (short) 2, pluderStoneNum, ConsumptionTypeEnum.ZONE_RESOURCE_PLUNDER);

                if (middlePluderStoneNum > 0) {
                    ChangePlayerWealthVO changePlayerWealthVO = new ChangePlayerWealthVO(playerzoneplunder.getPlunderroleid(), SmOpCode.HEARTBEAT_RES, ConsumptionTypeEnum.ZONE_RESOURCE_PLUNDER, "");
                    changePlayerWealthVO.setStone(middlePluderStoneNum);
                    sendService.sendCrossSubResource(playerzoneplunder.getPlunderroleid(), changePlayerWealthVO);
                }
                sendService.sendCrossReducePlunderResource(playerzoneplunder.getPlunderroleid());
                mineResourceService.reduceMinePlunderResource(playerzoneplunder.getPlunderroleid());
            }

            // 刷新玩家防区对手
            playerzoneplunder.setIsplunder(true);
            playerzoneplunder.setPlundertime(System.currentTimeMillis());
            playerzoneplunderMapper.updateByPrimaryKeySelective(playerzoneplunder);

            // 更新被攻击方次数
            int attackedtimes = 0;
            if (playerdefensezoneWithBLOBs.getAttackedtimes() == null) {
                attackedtimes = 1;
            } else {
                attackedtimes = playerdefensezoneWithBLOBs.getAttackedtimes() + 1;
            }
            if (attackedtimes == 5) {
                playerdefensezoneWithBLOBs.setIseverprotected(true);
            } else if (attackedtimes == 3) {
                playerdefensezoneWithBLOBs.setIsprotected(true);
                playerdefensezoneWithBLOBs.setProtectedtime(System.currentTimeMillis());
            }
            playerdefensezoneWithBLOBs.setAttackedtimes(attackedtimes);
            playerdefensezoneMapper.updateByPrimaryKeySelective(playerdefensezoneWithBLOBs);

            battleLog.setIsrevanche(true);

            // 战斗胜利后解锁新的玩家防区
            DicunlockresourcemineExample dicunlockresourcemineExample = new DicunlockresourcemineExample();
            dicunlockresourcemineExample.createCriteria().andResmineidEqualTo(resMineID);
            List<Dicunlockresourcemine> unlockResourceMineList = dicunlockresourcemineMapper.selectByExample(dicunlockresourcemineExample);

            for (Dicunlockresourcemine dicunlockresourcemine : unlockResourceMineList) {
                refreshPlayerZone(roleId, dicunlockresourcemine.getUnlockresmineid(), false);
            }
        } else {
            battleLog.setIsrevanche(false);
        }

        // 记录日志
        battleLog.setIsrevanchelog(false);
        battleLog.setRewardgold(pluderGoldNum);
        battleLog.setRewardiron(pluderIronNum);
        battleLog.setRewardoil(pluderOilNum);
        battleLog.setRewardstone(pluderStoneNum);
        defensezonebattlelogMapper.insertSelective(battleLog);
        playerZoneAttackedCacheManager.remove(playerzoneplunder.getPlunderroleid());
        zoneBattleEndVO.setRewardGold(pluderGoldNum);
        zoneBattleEndVO.setRewardIron(pluderIronNum);
        zoneBattleEndVO.setRewardOil(pluderOilNum);
        zoneBattleEndVO.setRewardStone(pluderStoneNum);
        return zoneBattleEndVO;
    }

    public ZoneInfoVO refreshPlayerZone(int roleId, int resMineID, boolean isInitial) throws NoSuchRoleException, SeaException {
        ZoneInfoVO zoneInfoVO = new ZoneInfoVO();

        List<Integer> alreadyRefreshRoleList = new ArrayList<Integer>();
        PlayerzoneplunderExample playerzoneplunderExample = new PlayerzoneplunderExample();
        playerzoneplunderExample.createCriteria().andRoleidEqualTo(roleId);
        List<Playerzoneplunder> refreshedPlayerZoneList = playerzoneplunderMapper.selectByExample(playerzoneplunderExample);
        if (refreshedPlayerZoneList != null && refreshedPlayerZoneList.size() > 0) {
            for (Playerzoneplunder playerzoneplunderRes : refreshedPlayerZoneList) {
                alreadyRefreshRoleList.add(playerzoneplunderRes.getPlunderroleid());
            }
        }

        PlayerzoneplunderKey playerzoneplunderKey = new PlayerzoneplunderKey();
        playerzoneplunderKey.setRoleid(roleId);
        playerzoneplunderKey.setResmineid(resMineID);
        Playerzoneplunder playerzoneplunder = playerzoneplunderMapper.selectByPrimaryKey(playerzoneplunderKey);
        if (playerzoneplunder != null) {
            // 如果playerzoneplunder.getPlunderroleid()为0的话证明之前没有刷出玩家需要重新刷新
            if (playerzoneplunder.getPlunderroleid() == 0) {
                int matchRoleId = gradeMatchRoleId(roleId, alreadyRefreshRoleList);
                if (matchRoleId > 0) {
                    playerzoneplunder.setPlunderroleid(matchRoleId);
                    playerzoneplunder.setRefreshtime(System.currentTimeMillis());
                    playerzoneplunderMapper.updateByPrimaryKeySelective(playerzoneplunder);
                    zoneInfoVO.setUnlock(true);
                    zoneInfoVO.setResMineID(resMineID);
                    zoneInfoVO.setChangePlayer(false);
                    setZoneInfoVO(zoneInfoVO, matchRoleId);
                }
            } else {
                // 判断该防区是否能被刷新，被攻击过的防区2.4小时后可以刷新
                if (playerzoneplunder.getIsplunder()) {
                    long plunderTime = playerzoneplunder.getPlundertime();
                    long timePasted = System.currentTimeMillis() - plunderTime;
                    if (timePasted > AppConfig.ZONE_CHANGE_PLAYER_CD) {
                        int matchRoleId = gradeMatchRoleId(roleId, alreadyRefreshRoleList);
                        if (matchRoleId > 0) {
                            playerzoneplunder.setPlunderroleid(matchRoleId);
                            playerzoneplunder.setIsplunder(false);
                            playerzoneplunder.setPlundertime(null);
                            playerzoneplunder.setRefreshtime(System.currentTimeMillis());
                            playerzoneplunder.setIsunlock(true);
                            playerzoneplunderMapper.updateByPrimaryKeySelective(playerzoneplunder);

                            zoneInfoVO.setResMineID(resMineID);
                            zoneInfoVO.setUnlock(playerzoneplunder.getIsunlock());
                            zoneInfoVO.setChangePlayer(false);
                            setZoneInfoVO(zoneInfoVO, matchRoleId);
                        }
                    } else {
                        zoneInfoVO.setResMineID(resMineID);
                    }
                } else {
                    long refreshTime = playerzoneplunder.getRefreshtime();
                    long timePasted = System.currentTimeMillis() - refreshTime;
                    if (timePasted > AppConfig.ZONE_CHANGE_PLAYER_CD) {
                        zoneInfoVO.setChangePlayer(true);
                    } else {
                        zoneInfoVO.setChangePlayer(false);
                    }
                    zoneInfoVO.setResMineID(resMineID);
                    zoneInfoVO.setUnlock(playerzoneplunder.getIsunlock());
                    setZoneInfoVO(zoneInfoVO, playerzoneplunder.getPlunderroleid());
                }
            }
        } else {
            int matchRoleId = gradeMatchRoleId(roleId, alreadyRefreshRoleList);
            logger.info("  after battle end ====== " + matchRoleId);
            if (matchRoleId > 0) {
                if (!isInitial) {
                    Playerzoneplunder record = new Playerzoneplunder();
                    record.setRoleid(roleId);
                    record.setResmineid(resMineID);
                    record.setPlunderroleid(matchRoleId);
                    record.setIsplunder(false);
                    record.setPlundertime(null);
                    record.setRefreshtime(System.currentTimeMillis());
                    record.setIsunlock(false);
                    playerzoneplunderMapper.insertSelective(record);
                    zoneInfoVO.setUnlock(false);
                } else {
                    zoneInfoVO.setUnlock(true);
                }

                zoneInfoVO.setResMineID(resMineID);
                zoneInfoVO.setChangePlayer(false);
                setZoneInfoVO(zoneInfoVO, matchRoleId);
            }
        }

        return zoneInfoVO;
    }

    public ZoneInfoVO changePlayerZone(int roleId, int resMineID) throws NoSuchRoleException, SeaException {
        ZoneInfoVO zoneInfoVO = new ZoneInfoVO();

        PlayerzoneplunderKey playerzoneplunderKey = new PlayerzoneplunderKey();
        playerzoneplunderKey.setRoleid(roleId);
        playerzoneplunderKey.setResmineid(resMineID);
        Playerzoneplunder playerzoneplunder = playerzoneplunderMapper.selectByPrimaryKey(playerzoneplunderKey);
        if (playerzoneplunder == null || playerzoneplunder.getPlunderroleid() == null) {
            throw new SeaException(SeaErrorCode.ZONE_NOT_EXIST, roleId + "_");
        }

        // 判断刷新时间是否满1小时，如果1小时候原有敌人依然存在的点，刷新点敌人不消失，出现更换对手按钮，可以更换新敌人
        long refreshTime = playerzoneplunder.getRefreshtime();
        long timePasted = System.currentTimeMillis() - refreshTime;
        if (timePasted <= AppConfig.ZONE_CHANGE_PLAYER_CD) {
            throw new SeaException(SeaErrorCode.ZONE_CANNOT_CHANGE_PLAYER, roleId + "_");
        }

        List<Integer> alreadyRefreshRoleList = new ArrayList<Integer>();
        PlayerzoneplunderExample playerzoneplunderExample = new PlayerzoneplunderExample();
        playerzoneplunderExample.createCriteria().andRoleidEqualTo(roleId);
        List<Playerzoneplunder> refreshedPlayerZoneList = playerzoneplunderMapper.selectByExample(playerzoneplunderExample);
        if (refreshedPlayerZoneList != null && refreshedPlayerZoneList.size() > 0) {
            for (Playerzoneplunder playerzoneplunderRes : refreshedPlayerZoneList) {
                alreadyRefreshRoleList.add(playerzoneplunderRes.getPlunderroleid());
            }
        }

        int matchRoleId = gradeMatchRoleId(roleId, alreadyRefreshRoleList);
        boolean ifUpdateDB = true;
        if (matchRoleId == 0) {
            ifUpdateDB = false;
            matchRoleId = playerzoneplunder.getPlunderroleid();
        }
        if (matchRoleId > 0) {
            if (ifUpdateDB) {
                playerzoneplunder.setPlunderroleid(matchRoleId);
                playerzoneplunder.setIsplunder(false);
                playerzoneplunder.setPlundertime(null);
                playerzoneplunder.setRefreshtime(System.currentTimeMillis());
                playerzoneplunder.setIsunlock(true);
                playerzoneplunderMapper.updateByPrimaryKeySelective(playerzoneplunder);
            }

            zoneInfoVO.setResMineID(resMineID);
            zoneInfoVO.setUnlock(true);
            PlayerRoleVO otherPlayerRoleVO = playerRoleCacheManager.get(matchRoleId);
            zoneInfoVO.setOtherHeaderquarterLevel(otherPlayerRoleVO.getHeaderquartersLevel());
            zoneInfoVO.setOtherRoleId(matchRoleId);
            zoneInfoVO.setOtherRoleName(otherPlayerRoleVO.getRoleName());
            zoneInfoVO.setOtherCampId(otherPlayerRoleVO.getCampId());
            zoneInfoVO.setOtherGeneralDegree(otherPlayerRoleVO.getRoleLevel());
            zoneInfoVO.setOtherAvatar(otherPlayerRoleVO.getRoleAvatar());
            zoneInfoVO.setOtherRoleGrade(gradeRankingZSetCpt.getScore(matchRoleId));
            zoneInfoVO.setOtherIsOnline(gameServerSendService.isOnline(matchRoleId));
            zoneInfoVO.setOtherServerId(otherPlayerRoleVO.getServerId());

            PlayerdefensezoneWithBLOBs playerdefensezoneWithBLOBs = playerdefensezoneMapper.selectByPrimaryKey(matchRoleId);
            if (playerdefensezoneWithBLOBs != null) {
                List<PvpBattleUnitVO> zonePlayerBattleUnit = getZonePlayerBattleUnit(playerdefensezoneWithBLOBs);
                zoneInfoVO.setBattleUnitInfo(zonePlayerBattleUnit);
                zoneInfoVO.setOtherBlockHouseNpcInfo(playerdefensezoneWithBLOBs.getBlockhousenpcinfo());
                zoneInfoVO.setUnitInfos(getUnitInfos(matchRoleId, zonePlayerBattleUnit));
            }

            Playerrole playerroleRes = playerRoleMapper.selectByPrimaryKey(matchRoleId);

            // 掠夺资源由三部分组成，玩家已有资源 + 玩家主城建筑库存待收取资源 + 玩家资源点库存待收取资源
            int plunderGoldNum = caculatePlunderResourceNum(playerroleRes.getRolegold(), otherPlayerRoleVO.getGoldStorageLimit());
            int plunderIronNum = caculatePlunderResourceNum(playerroleRes.getRoleiron(), otherPlayerRoleVO.getIronStorageLimit());
            int plunderOilNum = caculatePlunderResourceNum(playerroleRes.getRoleoil(), otherPlayerRoleVO.getOilStorageLimit());
            int plunderStoneNum = caculatePlunderResourceNum(playerroleRes.getRolestone(), otherPlayerRoleVO.getStoneStorageLimit());

            Map<Integer, Integer> baseResourceMap = otherPlayerRoleVO.getBaseResoucePlunderMap();
            Map<Short, Double> mineResourceMap = mineResourceService.getMinePlunderResourceNum(matchRoleId);

            if (baseResourceMap != null && baseResourceMap.get(AppConfig.GOLD_FUNCTION_ID) != null)
                plunderGoldNum += baseResourceMap.get(AppConfig.GOLD_FUNCTION_ID).intValue();
            if (mineResourceMap != null && mineResourceMap.get((short) ResourcesEnum.GOLD.getTypeID()) != null)
                plunderGoldNum += mineResourceMap.get((short) ResourcesEnum.GOLD.getTypeID()).intValue();

            if (baseResourceMap != null && baseResourceMap.get(AppConfig.IRON_FUNCTION_ID) != null)
                plunderIronNum += baseResourceMap.get(AppConfig.IRON_FUNCTION_ID).intValue();
            if (mineResourceMap != null && mineResourceMap.get((short) ResourcesEnum.IRON.getTypeID()) != null)
                plunderIronNum += mineResourceMap.get((short) ResourcesEnum.IRON.getTypeID()).intValue();

            if (baseResourceMap != null && baseResourceMap.get(AppConfig.OIL_FUNCTION_ID) != null)
                plunderOilNum += baseResourceMap.get(AppConfig.OIL_FUNCTION_ID).intValue();
            if (mineResourceMap != null && mineResourceMap.get((short) ResourcesEnum.OIL.getTypeID()) != null)
                plunderOilNum += mineResourceMap.get((short) ResourcesEnum.OIL.getTypeID()).intValue();

            if (baseResourceMap != null && baseResourceMap.get(AppConfig.STONE_FUNCTION_ID) != null)
                plunderStoneNum += baseResourceMap.get(AppConfig.STONE_FUNCTION_ID).intValue();
            if (mineResourceMap != null && mineResourceMap.get((short) ResourcesEnum.STONE.getTypeID()) != null)
                plunderStoneNum += mineResourceMap.get((short) ResourcesEnum.STONE.getTypeID()).intValue();

            zoneInfoVO.setRewardGold(plunderGoldNum);
            zoneInfoVO.setRewardIron(plunderIronNum);
            zoneInfoVO.setRewardOil(plunderOilNum);
            zoneInfoVO.setRewardStone(plunderStoneNum);
        }

        return zoneInfoVO;
    }

    public void unlockRefreshZone(int roleId, int resMineID) throws NoSuchRoleException, SeaException,
            TaskDataException, NotEnoughDiamondException, NotEnoughStoneException, NotEnoughOilException,
            NotEnoughIronException, NotEnoughGoldException, NotEnoughWealthException, NotEnoughResourceException {
        if (roleId < 0)
            throw new NoSuchRoleException();

        PlayerzoneplunderKey playerzoneplunderKey = new PlayerzoneplunderKey();
        playerzoneplunderKey.setRoleid(roleId);
        playerzoneplunderKey.setResmineid(resMineID);
        Playerzoneplunder playerzoneplunder = playerzoneplunderMapper.selectByPrimaryKey(playerzoneplunderKey);
        if (playerzoneplunder == null || playerzoneplunder.getPlunderroleid() == null) {
            throw new SeaException(SeaErrorCode.ZONE_NOT_EXIST, roleId + "_");
        }
        if (playerzoneplunder.getIsunlock()) {
            throw new SeaException(SeaErrorCode.ZONE_IS_ALREADY_UNLOCKED, roleId + "_");
        }

        DicunlockresourcemineExample dicunlockresourcemineExample = new DicunlockresourcemineExample();
        dicunlockresourcemineExample.createCriteria().andUnlockresmineidEqualTo(resMineID);
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

            playerzoneplunder.setIsunlock(true);
            playerzoneplunder.setRefreshtime(System.currentTimeMillis());
            playerzoneplunderMapper.updateByPrimaryKeySelective(playerzoneplunder);
        }
    }

    public List<ZoneBattleInfoVO> getZoneBattleInfoList(int roleId, int logType) throws NoSuchRoleException {
        List<ZoneBattleInfoVO> resultList = new ArrayList<ZoneBattleInfoVO>();
        if (roleId < 0)
            throw new NoSuchRoleException();

        // 获取一个月内的战斗日志
        long beginDate = System.currentTimeMillis() - 2592000000L;
        DefensezonebattlelogExample resourceminebattlelogExample = new DefensezonebattlelogExample();
        if (logType == 1) {
            resourceminebattlelogExample.createCriteria().andAttackeeroleidEqualTo(roleId).andIsrevanchelogEqualTo(false).andAttacktimeGreaterThanOrEqualTo(beginDate);
        } else if (logType == 2) {
            resourceminebattlelogExample.createCriteria().andAttackerroleidEqualTo(roleId).andIsrevanchelogEqualTo(true).andAttacktimeGreaterThanOrEqualTo(beginDate);
        } else {
            resourceminebattlelogExample.createCriteria().andAttackeeroleidEqualTo(roleId).andIsrevanchelogEqualTo(true).andAttacktimeGreaterThanOrEqualTo(beginDate);
        }
        resourceminebattlelogExample.setOrderByClause("battleLogID desc");
        List<Defensezonebattlelog> resMineBattleLogList = defensezonebattlelogMapper.selectByExample(resourceminebattlelogExample);
        for (Defensezonebattlelog resourceminebattlelog : resMineBattleLogList) {
            ZoneBattleInfoVO mineBattleInfoVO = new ZoneBattleInfoVO();
            mineBattleInfoVO.setBattleLogId(resourceminebattlelog.getBattlelogid());
            mineBattleInfoVO.setResMineID(resourceminebattlelog.getResmineid());
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
            mineBattleInfoVO.setAttackerRoleGrade(resourceminebattlelog.getAttackerrolegrade());
            mineBattleInfoVO.setRewardGold(resourceminebattlelog.getRewardgold());
            mineBattleInfoVO.setRewardOil(resourceminebattlelog.getRewardoil());
            mineBattleInfoVO.setRewardStone(resourceminebattlelog.getRewardstone());
            mineBattleInfoVO.setRewardIron(resourceminebattlelog.getRewardiron());
            mineBattleInfoVO.setRevanche(resourceminebattlelog.getIsrevanche());
            mineBattleInfoVO.setOnline(gameServerSendService.isOnline(resourceminebattlelog.getAttackerroleid()));
            resultList.add(mineBattleInfoVO);
        }
        return resultList;
    }

    public ZoneBattleStartVO revancheBattleStart(int roleId, long battleLogId, List<BattleUnitVO> battleUnitList)
            throws NoSuchRoleException, BattleUnitNotEnoughException, PlayerTonNotEnoughException,
            BattleUnitNumberZeroException, BattleUnitNotExistException, SeaException {
        ZoneBattleStartVO zoneBattleStartVO = new ZoneBattleStartVO();

        PlayerdefensezoneWithBLOBs playerdefensezoneOwnWithBLOBs = playerdefensezoneMapper.selectByPrimaryKey(roleId);
        if (playerdefensezoneOwnWithBLOBs != null) {
            if (playerdefensezoneOwnWithBLOBs.getIseverprotected() || playerdefensezoneOwnWithBLOBs.getIsprotected()) {
                playerdefensezoneOwnWithBLOBs.setIseverprotected(false);
                playerdefensezoneOwnWithBLOBs.setIsprotected(false);
                playerdefensezoneMapper.updateByPrimaryKeySelective(playerdefensezoneOwnWithBLOBs);
            }
        }

        Defensezonebattlelog defensezonebattlelog = defensezonebattlelogMapper.selectByPrimaryKey(battleLogId);
        if (defensezonebattlelog == null) {
            throw new SeaException(SeaErrorCode.ZONE_CANNOT_REVANCHE, roleId + "_");
        }
        if (!defensezonebattlelog.getIsrevanche()) {
            throw new SeaException(SeaErrorCode.ZONE_CANNOT_REVANCHE, roleId + "_");
        }

        int resPlayerId = defensezonebattlelog.getAttackerroleid();
        //判断该玩家是否在线，如果在线则不能攻击并提示
        if (gameServerSendService.isOnline(resPlayerId)) {
            throw new SeaException(SeaErrorCode.ZONE_OWNER_ONLINE, roleId + "_");
        }

        PlayerdefensezoneWithBLOBs playerdefensezoneWithBLOBs = playerdefensezoneMapper.selectByPrimaryKey(resPlayerId);
        if (playerdefensezoneWithBLOBs == null) {
            throw new SeaException(SeaErrorCode.ZONE_NOT_EXIST, roleId + "_");
        }

        //判断玩家是否处于保护道具保护下
        if (mineService.isProtectedByItem(resPlayerId)) {
            throw new SeaException(SeaErrorCode.ZONE_UNDER_PROTECTED_BY_ITEM, roleId + "_");
        }

        // 如果玩家还处于资源点保护道具保护期间则保护立即失效
        Playerprotectionbuff buff = playerprotectionbuffMapper.selectByPrimaryKey(roleId);
        if (buff != null) {
            playerprotectionbuffMapper.deleteByPrimaryKey(roleId);
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

        zoneBattleStartVO.setPlayerId(resPlayerId);
        zoneBattleStartVO.setHeaderquartersLevel(playerRoleCacheManager.get(resPlayerId).getHeaderquartersLevel());

        zoneBattleStartVO.setBlockHouseNPCInfo(playerdefensezoneWithBLOBs.getBlockhousenpcinfo());
        if (playerdefensezoneWithBLOBs.getCommanderinstruction() != null)
            zoneBattleStartVO.setCommanderInstruction(playerdefensezoneWithBLOBs.getCommanderinstruction());
        if (playerdefensezoneWithBLOBs.getLandmineinfo() != null)
            zoneBattleStartVO.setLandMineInfo(playerdefensezoneWithBLOBs.getLandmineinfo());

        List<PvpBattleUnitVO> resultList = new ArrayList<PvpBattleUnitVO>();
        if (playerdefensezoneWithBLOBs.getBattleunitinfo1() != null && !"".equals(playerdefensezoneWithBLOBs.getBattleunitinfo1())) {
            try {
                List<PvpBattleUnitVO> playerBattleUnitList = jacksonObjectMapper.readValue(playerdefensezoneWithBLOBs.getBattleunitinfo1(),
                        new TypeReference<List<PvpBattleUnitVO>>() {
                        });
                resultList.addAll(playerBattleUnitList);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if (playerdefensezoneWithBLOBs.getBattleunitinfo2() != null && !"".equals(playerdefensezoneWithBLOBs.getBattleunitinfo2())) {
            try {
                List<PvpBattleUnitVO> playerBattleUnitList = jacksonObjectMapper.readValue(playerdefensezoneWithBLOBs.getBattleunitinfo2(),
                        new TypeReference<List<PvpBattleUnitVO>>() {
                        });
                resultList.addAll(playerBattleUnitList);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if (playerdefensezoneWithBLOBs.getBattleunitinfo3() != null && !"".equals(playerdefensezoneWithBLOBs.getBattleunitinfo3())) {
            try {
                List<PvpBattleUnitVO> playerBattleUnitList = jacksonObjectMapper.readValue(playerdefensezoneWithBLOBs.getBattleunitinfo3(),
                        new TypeReference<List<PvpBattleUnitVO>>() {
                        });
                resultList.addAll(playerBattleUnitList);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if (playerdefensezoneWithBLOBs.getBattleunitinfo4() != null && !"".equals(playerdefensezoneWithBLOBs.getBattleunitinfo4())) {
            try {
                List<PvpBattleUnitVO> playerBattleUnitList = jacksonObjectMapper.readValue(playerdefensezoneWithBLOBs.getBattleunitinfo4(),
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
        zoneBattleStartVO.setPlayerBattleUnitList(playerBattleUnitList);
        zoneBattleStartVO.setBattleUnitMapInfo(resultList);

        List<PlayerTacticVO> tacticsList = playerRoleVORes.getTacticsList();
        zoneBattleStartVO.setPlayerTactics(tacticsList);
        zoneBattleStartVO.setPlayerPower(playerRoleVORes.getRolePower());
        zoneBattleStartVO.setLimitTime(180);

        return zoneBattleStartVO;
    }

    public ZoneBattleEndVO revancheBattleEnd(int roleId, long battleLogId, int battleRes, List<BattleUnitStartVO> totalBattleUnitList, List<BattleUnitStartVO> damageBattleUnitList)
            throws NoSuchRoleException, MineNotExistException, BattleResultErrorException, DamageBattleUnitTooLargeException,
            BattleUnitNotExistException, BattleUnitNumberZeroException, TaskDataException, CanNotCollectResourceException,
            MineNotPlayerException, MineCannotChangePlayerException, MineChangePlayerNotFoundException,
            JsonParseException, JsonMappingException, IOException, NotEnoughWealthException, NotEnoughDiamondException,
            NotEnoughStoneException, NotEnoughOilException, NotEnoughIronException, NotEnoughGoldException, SeaException {
        ZoneBattleEndVO zoneBattleEndVO = new ZoneBattleEndVO();
        if (roleId < 0)
            throw new NoSuchRoleException();

        Defensezonebattlelog defensezonebattlelog = defensezonebattlelogMapper.selectByPrimaryKey(battleLogId);
        if (defensezonebattlelog == null) {
            throw new SeaException(SeaErrorCode.ZONE_CANNOT_REVANCHE, roleId + "_");
        }

        if (totalBattleUnitList.size() == 0)
            throw new BattleUnitNumberZeroException();
        if ((battleRes != AppConfig.BATTLE_SUCCESS_RESULT) && (battleRes != AppConfig.BATTLE_FAILED_RESULT))
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

        int pluderGoldNum = 0;
        int pluderIronNum = 0;
        int pluderOilNum = 0;
        int pluderStoneNum = 0;
        if (battleRes == AppConfig.BATTLE_SUCCESS_RESULT) {
            defensezonebattlelog.setIsrevanche(false);
            defensezonebattlelogMapper.updateByPrimaryKeySelective(defensezonebattlelog);

            Defensezonebattlelog battleLog = new Defensezonebattlelog();
            battleLog.setAttackerroleid(roleId);
            battleLog.setAttackeeroleid(defensezonebattlelog.getAttackerroleid());
            battleLog.setAttackresult((short) battleRes);
            battleLog.setAttacktime(System.currentTimeMillis());
            battleLog.setResmineid(defensezonebattlelog.getResmineid());
            PlayerRoleVO attackerPlayerRoleVO = playerRoleCacheManager.get(roleId);
            battleLog.setAttackerrolename(attackerPlayerRoleVO.getRoleName());
            battleLog.setAttackercampid((short) attackerPlayerRoleVO.getCampId());
            battleLog.setAttackeravatar(attackerPlayerRoleVO.getRoleAvatar());
            battleLog.setAttackergeneraldegree(attackerPlayerRoleVO.getRoleLevel());
            battleLog.setAttackerrolegrade(gradeRankingZSetCpt.getScore(roleId));
            battleLog.setAttackeerolegrade(gradeRankingZSetCpt.getScore(defensezonebattlelog.getAttackerroleid()));

            try {
                battleLog.setAttackerbattleunit(jacksonObjectMapper.writeValueAsString(totalBattleUnitList));
                battleLog.setDamagebattleunit(jacksonObjectMapper.writeValueAsString(damageBattleUnitList));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            Playerrole playerroleRes = playerRoleMapper.selectByPrimaryKey(defensezonebattlelog.getAttackerroleid());
            PlayerRoleVO playerRoleVORes = playerRoleCacheManager.get(defensezonebattlelog.getAttackerroleid());
            int goldStorageLimit = playerRoleVORes.getGoldStorageLimit();
            int ironStorageLimit = playerRoleVORes.getIronStorageLimit();
            int oilStorageLimit = playerRoleVORes.getOilStorageLimit();
            int stoneStorageLimit = playerRoleVORes.getStoneStorageLimit();
            pluderGoldNum = caculatePlunderResourceNum(playerroleRes.getRolegold(), goldStorageLimit);
            int middlePluderGoldNum = pluderGoldNum;
            pluderIronNum = caculatePlunderResourceNum(playerroleRes.getRoleiron(), ironStorageLimit);
            int middlePluderIronNum = pluderIronNum;
            pluderOilNum = caculatePlunderResourceNum(playerroleRes.getRoleoil(), oilStorageLimit);
            int middlePluderOilNum = pluderOilNum;
            pluderStoneNum = caculatePlunderResourceNum(playerroleRes.getRolestone(), stoneStorageLimit);
            int middlePluderStoneNum = pluderStoneNum;

            // 掠夺资源由三部分组成，玩家已有资源 + 玩家主城建筑库存待收取资源 + 玩家资源点库存待收取资源
            Map<Integer, Integer> baseResourceMap = playerRoleVORes.getBaseResoucePlunderMap();
            Map<Short, Double> mineResourceMap = mineResourceService.getMinePlunderResourceNum(defensezonebattlelog.getAttackerroleid());

            if (baseResourceMap != null && baseResourceMap.get(AppConfig.GOLD_FUNCTION_ID) != null)
                pluderGoldNum += baseResourceMap.get(AppConfig.GOLD_FUNCTION_ID).intValue();
            if (mineResourceMap != null && mineResourceMap.get((short) ResourcesEnum.GOLD.getTypeID()) != null)
                pluderGoldNum += mineResourceMap.get((short) ResourcesEnum.GOLD.getTypeID()).intValue();

            if (baseResourceMap != null && baseResourceMap.get(AppConfig.IRON_FUNCTION_ID) != null)
                pluderIronNum += baseResourceMap.get(AppConfig.IRON_FUNCTION_ID).intValue();
            if (mineResourceMap != null && mineResourceMap.get((short) ResourcesEnum.IRON.getTypeID()) != null)
                pluderIronNum += mineResourceMap.get((short) ResourcesEnum.IRON.getTypeID()).intValue();

            if (baseResourceMap != null && baseResourceMap.get(AppConfig.OIL_FUNCTION_ID) != null)
                pluderOilNum += baseResourceMap.get(AppConfig.OIL_FUNCTION_ID).intValue();
            if (mineResourceMap != null && mineResourceMap.get((short) ResourcesEnum.OIL.getTypeID()) != null)
                pluderOilNum += mineResourceMap.get((short) ResourcesEnum.OIL.getTypeID()).intValue();

            if (baseResourceMap != null && baseResourceMap.get(AppConfig.STONE_FUNCTION_ID) != null)
                pluderStoneNum += baseResourceMap.get(AppConfig.STONE_FUNCTION_ID).intValue();
            if (mineResourceMap != null && mineResourceMap.get((short) ResourcesEnum.STONE.getTypeID()) != null)
                pluderStoneNum += mineResourceMap.get((short) ResourcesEnum.STONE.getTypeID()).intValue();

            // 如果是玩家剩余资源数满足条件就扣除剩余资源，如果剩余资源不足的话就扣除可以收取的资源库存，更新资源矿点的资源刷新时间减少玩家上线后可收集资源数量
            if (pluderGoldNum > 0) {
                sendService.sendCrossAddResource(roleId, (short) 1, pluderGoldNum, ConsumptionTypeEnum.ZONE_RESOURCE_PLUNDER);

                if (middlePluderGoldNum > 0) {
                    ChangePlayerWealthVO changePlayerWealthVO = new ChangePlayerWealthVO(defensezonebattlelog.getAttackerroleid(), SmOpCode.HEARTBEAT_RES, ConsumptionTypeEnum.ZONE_RESOURCE_PLUNDER, "");
                    changePlayerWealthVO.setGold(middlePluderGoldNum);
                    sendService.sendCrossSubResource(defensezonebattlelog.getAttackerroleid(), changePlayerWealthVO);
                }
                sendService.sendCrossReducePlunderResource(defensezonebattlelog.getAttackerroleid());
                mineResourceService.reduceMinePlunderResource(defensezonebattlelog.getAttackerroleid());
            }

            if (pluderIronNum > 0) {
                sendService.sendCrossAddResource(roleId, (short) 4, pluderIronNum, ConsumptionTypeEnum.ZONE_RESOURCE_PLUNDER);

                if (middlePluderIronNum > 0) {
                    ChangePlayerWealthVO changePlayerWealthVO = new ChangePlayerWealthVO(defensezonebattlelog.getAttackerroleid(), SmOpCode.HEARTBEAT_RES, ConsumptionTypeEnum.ZONE_RESOURCE_PLUNDER, "");
                    changePlayerWealthVO.setIron(middlePluderIronNum);
                    sendService.sendCrossSubResource(defensezonebattlelog.getAttackerroleid(), changePlayerWealthVO);
                }
                sendService.sendCrossReducePlunderResource(defensezonebattlelog.getAttackerroleid());
                mineResourceService.reduceMinePlunderResource(defensezonebattlelog.getAttackerroleid());
            }

            if (pluderOilNum > 0) {
                sendService.sendCrossAddResource(roleId, (short) 3, pluderOilNum, ConsumptionTypeEnum.ZONE_RESOURCE_PLUNDER);

                if (middlePluderOilNum > 0) {
                    ChangePlayerWealthVO changePlayerWealthVO = new ChangePlayerWealthVO(defensezonebattlelog.getAttackerroleid(), SmOpCode.HEARTBEAT_RES, ConsumptionTypeEnum.ZONE_RESOURCE_PLUNDER, "");
                    changePlayerWealthVO.setOil(middlePluderOilNum);
                    sendService.sendCrossSubResource(defensezonebattlelog.getAttackerroleid(), changePlayerWealthVO);
                }
                sendService.sendCrossReducePlunderResource(defensezonebattlelog.getAttackerroleid());
                mineResourceService.reduceMinePlunderResource(defensezonebattlelog.getAttackerroleid());
            }

            if (pluderStoneNum > 0) {
                sendService.sendCrossAddResource(roleId, (short) 2, pluderStoneNum, ConsumptionTypeEnum.ZONE_RESOURCE_PLUNDER);

                if (middlePluderStoneNum > 0) {
                    ChangePlayerWealthVO changePlayerWealthVO = new ChangePlayerWealthVO(defensezonebattlelog.getAttackerroleid(), SmOpCode.HEARTBEAT_RES, ConsumptionTypeEnum.ZONE_RESOURCE_PLUNDER, "");
                    changePlayerWealthVO.setStone(middlePluderStoneNum);
                    sendService.sendCrossSubResource(defensezonebattlelog.getAttackerroleid(), changePlayerWealthVO);
                }
                sendService.sendCrossReducePlunderResource(defensezonebattlelog.getAttackerroleid());
                mineResourceService.reduceMinePlunderResource(defensezonebattlelog.getAttackerroleid());
            }

            battleLog.setIsrevanche(false);
            // 记录日志
            battleLog.setIsrevanchelog(true);
            battleLog.setRewardgold(pluderGoldNum);
            battleLog.setRewardiron(pluderIronNum);
            battleLog.setRewardoil(pluderOilNum);
            battleLog.setRewardstone(pluderStoneNum);
            defensezonebattlelogMapper.insertSelective(battleLog);

            zoneBattleEndVO.setRewardGold(pluderGoldNum);
            zoneBattleEndVO.setRewardIron(pluderIronNum);
            zoneBattleEndVO.setRewardOil(pluderOilNum);
            zoneBattleEndVO.setRewardStone(pluderStoneNum);
        }

        return zoneBattleEndVO;
    }

    private void setZoneInfoVO(ZoneInfoVO zoneInfoVO, int matchRoleId) throws NoSuchRoleException {
        PlayerRoleVO otherPlayerRoleVO = playerRoleCacheManager.get(matchRoleId);
        if (otherPlayerRoleVO != null) {
            zoneInfoVO.setOtherHeaderquarterLevel(otherPlayerRoleVO.getHeaderquartersLevel());
            zoneInfoVO.setOtherRoleId(matchRoleId);
            zoneInfoVO.setOtherRoleName(otherPlayerRoleVO.getRoleName());
            zoneInfoVO.setOtherCampId(otherPlayerRoleVO.getCampId());
            zoneInfoVO.setOtherGeneralDegree(otherPlayerRoleVO.getRoleLevel());
            zoneInfoVO.setOtherAvatar(otherPlayerRoleVO.getRoleAvatar());
            zoneInfoVO.setOtherRoleGrade(gradeRankingZSetCpt.getScore(matchRoleId));
            zoneInfoVO.setOtherIsOnline(gameServerSendService.isOnline(matchRoleId));
            zoneInfoVO.setOtherServerId(otherPlayerRoleVO.getServerId());
            zoneInfoVO.setProtected(false);

            PlayerdefensezoneWithBLOBs playerdefensezoneWithBLOBs = playerdefensezoneMapper.selectByPrimaryKey(matchRoleId);
            if (playerdefensezoneWithBLOBs != null) {
                List<PvpBattleUnitVO> zonePlayerBattleUnit = getZonePlayerBattleUnit(playerdefensezoneWithBLOBs);
                zoneInfoVO.setBattleUnitInfo(zonePlayerBattleUnit);
                zoneInfoVO.setOtherBlockHouseNpcInfo(playerdefensezoneWithBLOBs.getBlockhousenpcinfo());
                zoneInfoVO.setUnitInfos(getUnitInfos(matchRoleId, zonePlayerBattleUnit));
            }

            Playerrole playerroleRes = playerRoleMapper.selectByPrimaryKey(matchRoleId);
            PlayerRoleVO playerRoleVORes = playerRoleCacheManager.get(matchRoleId);
            int goldStorageLimit = otherPlayerRoleVO.getGoldStorageLimit();
            int ironStorageLimit = otherPlayerRoleVO.getIronStorageLimit();
            int oilStorageLimit = otherPlayerRoleVO.getOilStorageLimit();
            int stoneStorageLimit = otherPlayerRoleVO.getStoneStorageLimit();

            // 掠夺资源由三部分组成，玩家已有资源 + 玩家主城建筑库存待收取资源 + 玩家资源点库存待收取资源
            int plunderGoldNum = caculatePlunderResourceNum(playerroleRes.getRolegold(), goldStorageLimit);
            int plunderIronNum = caculatePlunderResourceNum(playerroleRes.getRoleiron(), ironStorageLimit);
            int plunderOilNum = caculatePlunderResourceNum(playerroleRes.getRoleoil(), oilStorageLimit);
            int plunderStoneNum = caculatePlunderResourceNum(playerroleRes.getRolestone(), stoneStorageLimit);

            Map<Integer, Integer> baseResourceMap = playerRoleVORes.getBaseResoucePlunderMap();
            Map<Short, Double> mineResourceMap = mineResourceService.getMinePlunderResourceNum(matchRoleId);

            if (baseResourceMap != null && baseResourceMap.get(AppConfig.GOLD_FUNCTION_ID) != null)
                plunderGoldNum += baseResourceMap.get(AppConfig.GOLD_FUNCTION_ID).intValue();
            if (mineResourceMap != null && mineResourceMap.get((short) ResourcesEnum.GOLD.getTypeID()) != null)
                plunderGoldNum += mineResourceMap.get((short) ResourcesEnum.GOLD.getTypeID()).intValue();

            if (baseResourceMap != null && baseResourceMap.get(AppConfig.IRON_FUNCTION_ID) != null)
                plunderIronNum += baseResourceMap.get(AppConfig.IRON_FUNCTION_ID).intValue();
            if (mineResourceMap != null && mineResourceMap.get((short) ResourcesEnum.IRON.getTypeID()) != null)
                plunderIronNum += mineResourceMap.get((short) ResourcesEnum.IRON.getTypeID()).intValue();

            if (baseResourceMap != null && baseResourceMap.get(AppConfig.OIL_FUNCTION_ID) != null)
                plunderOilNum += baseResourceMap.get(AppConfig.OIL_FUNCTION_ID).intValue();
            if (mineResourceMap != null && mineResourceMap.get((short) ResourcesEnum.OIL.getTypeID()) != null)
                plunderOilNum += mineResourceMap.get((short) ResourcesEnum.OIL.getTypeID()).intValue();

            if (baseResourceMap != null && baseResourceMap.get(AppConfig.STONE_FUNCTION_ID) != null)
                plunderStoneNum += baseResourceMap.get(AppConfig.STONE_FUNCTION_ID).intValue();
            if (mineResourceMap != null && mineResourceMap.get((short) ResourcesEnum.STONE.getTypeID()) != null)
                plunderStoneNum += mineResourceMap.get((short) ResourcesEnum.STONE.getTypeID()).intValue();

            zoneInfoVO.setRewardGold(plunderGoldNum);
            zoneInfoVO.setRewardIron(plunderIronNum);
            zoneInfoVO.setRewardOil(plunderOilNum);
            zoneInfoVO.setRewardStone(plunderStoneNum);
        }
    }

    private List<PvpBattleUnitVO> getZonePlayerBattleUnit(PlayerdefensezoneWithBLOBs playerdefensezoneWithBLOBs) {
        List<PvpBattleUnitVO> resultList = new ArrayList<PvpBattleUnitVO>();
        if (playerdefensezoneWithBLOBs.getBattleunitinfo1() != null && !"".equals(playerdefensezoneWithBLOBs.getBattleunitinfo1())) {
            try {
                List<PvpBattleUnitVO> playerBattleUnitList = jacksonObjectMapper.readValue(playerdefensezoneWithBLOBs.getBattleunitinfo1(),
                        new TypeReference<List<PvpBattleUnitVO>>() {
                        });
                resultList.addAll(playerBattleUnitList);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if (playerdefensezoneWithBLOBs.getBattleunitinfo2() != null && !"".equals(playerdefensezoneWithBLOBs.getBattleunitinfo2())) {
            try {
                List<PvpBattleUnitVO> playerBattleUnitList = jacksonObjectMapper.readValue(playerdefensezoneWithBLOBs.getBattleunitinfo2(),
                        new TypeReference<List<PvpBattleUnitVO>>() {
                        });
                resultList.addAll(playerBattleUnitList);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if (playerdefensezoneWithBLOBs.getBattleunitinfo3() != null && !"".equals(playerdefensezoneWithBLOBs.getBattleunitinfo3())) {
            try {
                List<PvpBattleUnitVO> playerBattleUnitList = jacksonObjectMapper.readValue(playerdefensezoneWithBLOBs.getBattleunitinfo3(),
                        new TypeReference<List<PvpBattleUnitVO>>() {
                        });
                resultList.addAll(playerBattleUnitList);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if (playerdefensezoneWithBLOBs.getBattleunitinfo4() != null && !"".equals(playerdefensezoneWithBLOBs.getBattleunitinfo4())) {
            try {
                List<PvpBattleUnitVO> playerBattleUnitList = jacksonObjectMapper.readValue(playerdefensezoneWithBLOBs.getBattleunitinfo4(),
                        new TypeReference<List<PvpBattleUnitVO>>() {
                        });
                resultList.addAll(playerBattleUnitList);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return resultList;
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

    private int caculatePlunderResourceNum(int playerResourceNum, int resourceStorageLimit) {
        int plunderResourceNum = 0;
        int resourcePlunder = (int) (resourceStorageLimit * 0.1);
        if (playerResourceNum > resourcePlunder) {
            plunderResourceNum = (int) ((playerResourceNum - resourcePlunder) * 0.25);
        }
        return plunderResourceNum;
    }

    private int gradeMatchRoleId(int roleId, List<Integer> alreadyRefreshedList) {
        int matchRoleId = 0;
        int playerGrade = gradeRankingZSetCpt.getScore(roleId);
        matchRoleId = getMatchRoleId(roleId, playerGrade, 0.1, alreadyRefreshedList);
        if (matchRoleId == 0) {
            matchRoleId = getMatchRoleId(roleId, playerGrade, 0.2, alreadyRefreshedList);
        }
        if (matchRoleId == 0) {
            matchRoleId = getMatchRoleId(roleId, playerGrade, 0.5, alreadyRefreshedList);
        }
        if (matchRoleId == 0) {
            matchRoleId = getMatchRoleId(roleId, playerGrade, 1, alreadyRefreshedList);
        }
        return matchRoleId;
    }

    private int getMatchRoleId(int roleId, int playerGrade, double queryRate, List<Integer> alreadyRefreshedList) {
        int matchRoleId = 0;
        int beginGrade = (int) (playerGrade * (1 - queryRate));
        int endGrade = (int) (playerGrade * (1 + queryRate));
        List<Integer> gradeMatchRoleList = gradeRankingZSetCpt.getByScore(beginGrade, endGrade);
        List<Integer> matchRoleList = new ArrayList<Integer>();
        for (Integer id : gradeMatchRoleList) {
            // 先检查该用户是否使用资源点保护道具
            boolean isProtected = mineService.isProtectedByItem(id);
            if (!isProtected) {
                PlayerdefensezoneWithBLOBs playerdefensezoneWithBLOBs = playerdefensezoneMapper.selectByPrimaryKey(id);
                if ((playerdefensezoneWithBLOBs != null) && (!playerdefensezoneWithBLOBs.getIseverprotected())) {
                    if (playerdefensezoneWithBLOBs.getIsprotected()) {
                        long protectedTime = playerdefensezoneWithBLOBs.getProtectedtime();
                        long timePasted = System.currentTimeMillis() - protectedTime;
                        if (timePasted > 28800000L) {
                            matchRoleList.add(id);
                        }
                    } else {
                        matchRoleList.add(id);
                    }
                }
            }
        }
        if (matchRoleList.size() > 0) {
            if (matchRoleList.size() == 1) {
                if ((matchRoleList.get(0) != roleId) && (!alreadyRefreshedList.contains(matchRoleList.get(0)))) {
                    matchRoleId = matchRoleList.get(0);
                }
            } else {
                // 按照数据长度大小随机取一个
                Random random = new Random();
                int index = random.nextInt(matchRoleList.size() - 1) % (matchRoleList.size());
                if ((matchRoleList.get(index) != roleId) && (!alreadyRefreshedList.contains(matchRoleList.get(index)))) {
                    matchRoleId = matchRoleList.get(index);
                }
            }
        }
        return matchRoleId;
    }

}
