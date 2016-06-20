package com.pkuvr.game_server.protoservice;

import com.pkuvr.game_server.cache.PlayerBufferCacheMananger;
import com.pkuvr.game_server.constant.AppConfig;
import com.pkuvr.game_server.constant.SeaErrorCode;
import com.pkuvr.game_server.exception.SeaException;
import com.pkuvr.game_server.proto.clientproto.Mine_Battle_End_Request.Mine_Battle_End_Req;
import com.pkuvr.game_server.proto.clientproto.Mine_Battle_Start_Request.Mine_Battle_Start_Req;
import com.pkuvr.game_server.proto.clientproto.Mine_Change_Player_Request.Mine_Change_Player_Req;
import com.pkuvr.game_server.proto.clientproto.Mine_Collect_Log_Request.Mine_Collect_Log_Req;
import com.pkuvr.game_server.proto.clientproto.Mine_Collect_Start_Request.Mine_Collect_Start_Req;
import com.pkuvr.game_server.proto.clientproto.Mine_Defense_Deploy_Request.Mine_Defense_Deploy_Req;
import com.pkuvr.game_server.proto.clientproto.Mine_Defense_Info_Request.Mine_Defense_Info_Req;
import com.pkuvr.game_server.proto.clientproto.Mine_Enter_Request.Mine_Enter_Req;
import com.pkuvr.game_server.proto.clientproto.Mine_Harvest_Resource_Request.Mine_Harvest_Resource_Req;
import com.pkuvr.game_server.proto.clientproto.Mine_Info_Request.Mine_Info_Req;
import com.pkuvr.game_server.proto.clientproto.Mine_Protect_Time_Request.Mine_Protect_Time_Req;
import com.pkuvr.game_server.proto.clientproto.Mine_Unlock_Refresh_Request.Mine_Unlock_Refresh_Req;
import com.pkuvr.game_server.proto.commons.BI_Battle_Unit.Battle_Unit;
import com.pkuvr.game_server.proto.commons.BI_Battle_Unit.Battle_Unit.Builder;
import com.pkuvr.game_server.proto.commons.BI_Battle_Unit.Battle_Unit.Skill;
import com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Position.Battle_Unit_Position;
import com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes;
import com.pkuvr.game_server.proto.commons.BI_Mine_Info;
import com.pkuvr.game_server.proto.commons.BI_Mine_Resource;
import com.pkuvr.game_server.proto.commons.BI_Player_Tactics.Player_Tactics_Mes;
import com.pkuvr.game_server.proto.commons.BI_Player_Tactics.Player_Tactics_Unit;
import com.pkuvr.game_server.proto.commons.BI_Zone_Info;
import com.pkuvr.game_server.proto.serverproto.Mine_Battle_End_Response.Mine_Battle_End_Res;
import com.pkuvr.game_server.proto.serverproto.Mine_Battle_Log_Response.Mine_Battle_Info_Mes;
import com.pkuvr.game_server.proto.serverproto.Mine_Battle_Log_Response.Mine_Battle_Log_Res;
import com.pkuvr.game_server.proto.serverproto.Mine_Battle_Start_Response.Mine_Battle_Start_Res;
import com.pkuvr.game_server.proto.serverproto.Mine_Change_Player_Response.Mine_Change_Player_Res;
import com.pkuvr.game_server.proto.serverproto.Mine_Collect_Log_Response.Mine_Collect_Info_Mes;
import com.pkuvr.game_server.proto.serverproto.Mine_Collect_Log_Response.Mine_Collect_Log_Res;
import com.pkuvr.game_server.proto.serverproto.Mine_Collect_Start_Response.Mine_Collect_Start_Res;
import com.pkuvr.game_server.proto.serverproto.Mine_Defense_Deploy_Response.Mine_Defense_Deploy_Res;
import com.pkuvr.game_server.proto.serverproto.Mine_Defense_Info_Response.Mine_Defense_Info_Res;
import com.pkuvr.game_server.proto.serverproto.Mine_Enter_Response.Mine_Enter_Res;
import com.pkuvr.game_server.proto.serverproto.Mine_Harvest_Query_Response.Mine_Harvest_Query_Res;
import com.pkuvr.game_server.proto.serverproto.Mine_Harvest_Resource_Response.Mine_Harvest_Resource_Res;
import com.pkuvr.game_server.proto.serverproto.Mine_Info_Response.Mine_Info_Res;
import com.pkuvr.game_server.proto.serverproto.Mine_List_Response.Mine_List_Res;
import com.pkuvr.game_server.proto.serverproto.Mine_Protect_Time_Response.Mine_Protect_Time_Res;
import com.pkuvr.game_server.proto.serverproto.Mine_Unlock_Refresh_Response.Mine_Unlock_Refresh_Res;
import com.pkuvr.game_server.service.MineCollectService;
import com.pkuvr.game_server.service.MineResourceService;
import com.pkuvr.game_server.service.MineService;
import com.pkuvr.game_server.service.ZoneService;
import com.pkuvr.game_server.vo.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("MineControl")
public class MineControl {
    @Resource
    private MineService mineService;
    @Resource
    private MineCollectService mineCollectService;
    @Resource
    private ZoneService zoneService;
    @Resource
    private MineResourceService playerMineResourceService;
    @Resource
    private PlayerBufferCacheMananger playerBufferCacheMananger;
    @Resource
    private BattleUnitControl battleUnitControl;

    /**
     * 玩家进入资源点
     *
     * @param roleId
     * @param byteData
     * @return
     */
    public byte[] enterMine(int roleId, byte[] byteData) {
        Mine_Enter_Res.Builder resBuilder = Mine_Enter_Res.newBuilder();
        try {
            Mine_Enter_Req req = Mine_Enter_Req.parseFrom(byteData);
            mineService.enterMine(roleId);
            resBuilder.setErrorCode(SeaErrorCode.OK.getErrorCode());
        } catch (Exception e) {
            resBuilder.setErrorCode(SeaException.logoutException(e, roleId));
        }
        return resBuilder.build().toByteArray();
    }

    /**
     * 获取玩家资源点列表
     *
     * @param roleId
     * @param byteData
     * @return
     */
    public byte[] getMineList(int roleId, byte[] byteData) {
        Mine_List_Res.Builder resBuilder = Mine_List_Res.newBuilder();

        try {
            List<MineInfoVO> mineList = mineService.getMineList(roleId);
            for (MineInfoVO mineInfoVO : mineList) {
                BI_Mine_Info.Mine_Info_Mes.Builder item = BI_Mine_Info.Mine_Info_Mes.newBuilder();
                item.setResMineInstanceID(mineInfoVO.getResMineInstanceID());
                item.setResMineID(mineInfoVO.getResMineID());
                item.setMineBuildingID(mineInfoVO.getMineBuildingID());
                item.setMineBuildingLevel(mineInfoVO.getMineBuildingLevel());
                item.setHealth(mineInfoVO.getHealth());
                item.setMineState(mineInfoVO.getMineState());
                item.setIsUnlock(mineInfoVO.isUnlock());
                item.setProducePerHour(mineInfoVO.getProducePerHour());
                item.setProduceResType(mineInfoVO.getProduceResType());
                item.setRewardRes(mineInfoVO.getRewardRes());
                if (mineInfoVO.getNpcBlockHouseNpcInfo() != null) {
                    item.setNpcBlockHouseNpcInfo(mineInfoVO.getNpcBlockHouseNpcInfo());
                }
                if (mineInfoVO.getMineNpcInfo() != null) {
                    item.setMineNpcInfo(mineInfoVO.getMineNpcInfo());
                }
                if (mineInfoVO.getOtherRoleId() > 0) {
                    item.setOtherRoleId(mineInfoVO.getOtherRoleId());
                    item.setOtherRoleName(mineInfoVO.getOtherRoleName());
                    item.setOtherCampId(mineInfoVO.getOtherCampId());
                    item.setOtherAvatar(mineInfoVO.getOtherAvatar());
                    item.setOtherGeneralDegree(mineInfoVO.getOtherGeneralDegree());
                    //item.setOtherRoleHonor(mineInfoVO.getOtherRoleHonor());
                    item.setOtherIsOnline(mineInfoVO.isOtherIsOnline());
                    item.setOtherBlockHouseNpcInfo(mineInfoVO.getOtherBlockHouseNpcInfo());
                    item.setOtherServerId(mineInfoVO.getOtherServerId());
                }
                item.setCanCollectNum(mineInfoVO.getCanCollectNum());
                item.setCollectLeftSecond(mineInfoVO.getCollectLeftSecond());
                item.setCollectTotalSecond(mineInfoVO.getCollectTotalSecond());
                item.setIsProtectedByItem(mineInfoVO.isProtectedByItem());
                resBuilder.addMineList(item);
            }

            List<ZoneInfoVO> zoneList = zoneService.getZoneList(roleId);
            for (ZoneInfoVO zoneInfoVO : zoneList) {
                BI_Zone_Info.Zone_Info_Mes.Builder item = BI_Zone_Info.Zone_Info_Mes.newBuilder();
                item.setResMineID(zoneInfoVO.getResMineID());
                item.setIsUnlock(zoneInfoVO.isUnlock());
                item.setIsChangePlayer(zoneInfoVO.isChangePlayer());
                item.setIsProtected(zoneInfoVO.isProtected());

                if (zoneInfoVO.getOtherRoleId() > 0) {
                    item.setOtherHeaderquarterLevel(zoneInfoVO.getOtherHeaderquarterLevel());
                    item.setOtherRoleId(zoneInfoVO.getOtherRoleId());
                    item.setOtherRoleName(zoneInfoVO.getOtherRoleName());
                    item.setOtherCampId(zoneInfoVO.getOtherCampId());
                    item.setOtherAvatar(zoneInfoVO.getOtherAvatar());
                    item.setOtherGeneralDegree(zoneInfoVO.getOtherGeneralDegree());
                    item.setOtherRoleGrade(zoneInfoVO.getOtherRoleGrade());
                    item.setOtherIsOnline(zoneInfoVO.isOtherIsOnline());
                    if (zoneInfoVO.getOtherBlockHouseNpcInfo() != null)
                        item.setOtherBlockHouseNpcInfo(zoneInfoVO.getOtherBlockHouseNpcInfo());
                    item.setRewardGold(zoneInfoVO.getRewardGold());
                    item.setRewardOil(zoneInfoVO.getRewardOil());
                    item.setRewardStone(zoneInfoVO.getRewardStone());
                    item.setRewardIron(zoneInfoVO.getRewardIron());
                    item.setOtherServerId(zoneInfoVO.getOtherServerId());
                }

                resBuilder.addZoneList(item);
            }

            resBuilder.setRoleCollectNum(mineCollectService.getCollectTimes(roleId));
            resBuilder.setErrorCode(SeaErrorCode.OK.getErrorCode());
        } catch (Exception e) {
            resBuilder.setErrorCode(SeaException.logoutException(e, roleId));
        }
        return resBuilder.build().toByteArray();
    }

    /**
     * 获取玩家资源点信息
     *
     * @param roleId
     * @param byteData
     * @return
     */
    public byte[] getMineInfo(int roleId, byte[] byteData) {
        Mine_Info_Res.Builder resBuilder = Mine_Info_Res.newBuilder();
        try {
            Mine_Info_Req req = Mine_Info_Req.parseFrom(byteData);

            MineInfoVO mineInfoVO = mineService.getMineInfo(roleId, req.getResMineInstanceID());
            BI_Mine_Info.Mine_Info_Mes.Builder item = BI_Mine_Info.Mine_Info_Mes.newBuilder();
            item.setResMineInstanceID(mineInfoVO.getResMineInstanceID());
            item.setResMineID(mineInfoVO.getResMineID());
            item.setMineBuildingID(mineInfoVO.getMineBuildingID());
            item.setMineBuildingLevel(mineInfoVO.getMineBuildingLevel());
            item.setHealth(mineInfoVO.getHealth());
            item.setMineState(mineInfoVO.getMineState());
            item.setIsUnlock(mineInfoVO.isUnlock());
            item.setProducePerHour(mineInfoVO.getProducePerHour());
            item.setProduceResType(mineInfoVO.getProduceResType());
            item.setRewardRes(mineInfoVO.getRewardRes());
            item.setCanCollectNum(mineInfoVO.getCanCollectNum());
            item.setCollectLeftSecond(mineInfoVO.getCollectLeftSecond());
            item.setCollectTotalSecond(mineInfoVO.getCollectTotalSecond());
            item.setIsProtectedByItem(mineInfoVO.isProtectedByItem());
            if (mineInfoVO.getNpcBlockHouseNpcInfo() != null) {
                item.setNpcBlockHouseNpcInfo(mineInfoVO.getNpcBlockHouseNpcInfo());
            }
            if (mineInfoVO.getMineNpcInfo() != null) {
                item.setMineNpcInfo(mineInfoVO.getMineNpcInfo());
            }
            if (mineInfoVO.getOtherRoleId() > 0) {
                item.setOtherRoleId(mineInfoVO.getOtherRoleId());
                item.setOtherRoleName(mineInfoVO.getOtherRoleName());
                item.setOtherCampId(mineInfoVO.getOtherCampId());
                item.setOtherAvatar(mineInfoVO.getOtherAvatar());
                item.setOtherGeneralDegree(mineInfoVO.getOtherGeneralDegree());
                //item.setOtherRoleHonor(mineInfoVO.getOtherRoleHonor());
                item.setOtherIsOnline(mineInfoVO.isOtherIsOnline());
                item.setOtherBlockHouseNpcInfo(mineInfoVO.getOtherBlockHouseNpcInfo());
                if (mineInfoVO.getBattleUnitInfo() != null) {
                    for (PvpBattleUnitVO pvpBattleUnitVO : mineInfoVO.getBattleUnitInfo()) {
                        Battle_Unit_Position.Builder battleUnitBuilder = Battle_Unit_Position.newBuilder();
                        battleUnitBuilder.setBattleUnitID(pvpBattleUnitVO.getBattleUnitID());
                        battleUnitBuilder.setUnitLevel(pvpBattleUnitVO.getUnitLevel());
                        battleUnitBuilder.setXPosition(pvpBattleUnitVO.getxPosition());
                        battleUnitBuilder.setZPosition(pvpBattleUnitVO.getzPosition());
                        item.addOtherBattleUnitInfo(battleUnitBuilder);
                    }
                }
                item.setOtherServerId(mineInfoVO.getOtherServerId());
            }

            List<BattleUnitVO> unitInfos = mineInfoVO.getUnitInfos();
            if (unitInfos != null && unitInfos.size() > 0) {
                for (BattleUnitVO battleUnitVO : unitInfos) {
                    item.addUnitInfo(battleUnitControl.buildBattleUnit(battleUnitVO));
                }
            }

            resBuilder.setErrorCode(SeaErrorCode.OK.getErrorCode());
            resBuilder.setMineList(item);
        } catch (Exception e) {
            resBuilder.setErrorCode(SeaException.logoutException(e, roleId));
        }
        return resBuilder.build().toByteArray();
    }

    /**
     * 获取玩家资源点防御信息
     *
     * @param roleId
     * @param byteData
     * @return
     */
    public byte[] getMineDefenseInfo(int roleId, byte[] byteData) {
        Mine_Defense_Info_Res.Builder resBuilder = Mine_Defense_Info_Res.newBuilder();

        try {
            Mine_Defense_Info_Req req = Mine_Defense_Info_Req.parseFrom(byteData);
            long resMineIntanceID = req.getResMineInstanceID();
            MineDefenseInfoVO mineDefenseInfoVO = mineService.getMineDefenseInfo(roleId, resMineIntanceID);
            resBuilder.setErrorCode(SeaErrorCode.OK.getErrorCode());

            if (mineDefenseInfoVO.getBattleUnitInfo() != null) {
                for (PvpBattleUnitVO pvpBattleUnitVO : mineDefenseInfoVO.getBattleUnitInfo()) {
                    Battle_Unit_Position.Builder battleUnitBuilder = Battle_Unit_Position.newBuilder();
                    battleUnitBuilder.setBattleUnitID(pvpBattleUnitVO.getBattleUnitID());
                    battleUnitBuilder.setUnitLevel(pvpBattleUnitVO.getUnitLevel());
                    battleUnitBuilder.setXPosition(pvpBattleUnitVO.getxPosition());
                    battleUnitBuilder.setZPosition(pvpBattleUnitVO.getzPosition());
                    resBuilder.addBattleUnitInfo(battleUnitBuilder);
                }
            }

            if (mineDefenseInfoVO.getBlockHouseNPCInfo() != null)
                resBuilder.setBlockHouseNPCInfo(mineDefenseInfoVO.getBlockHouseNPCInfo());

            resBuilder.setMineBuildingID(mineDefenseInfoVO.getMineBuildingID());
            resBuilder.setMineBuildingLevel(mineDefenseInfoVO.getMineBuildingLevel());

            if (mineDefenseInfoVO.getMineInfo() != null)
                resBuilder.setMineInfo(mineDefenseInfoVO.getMineInfo());

            if (mineDefenseInfoVO.getLandMineInfo() != null)
                resBuilder.setLandMineInfo(mineDefenseInfoVO.getLandMineInfo());

            resBuilder.setCommanderInstruction(mineDefenseInfoVO.getCommanderInstruction());
            resBuilder.setResMineInstanceID(mineDefenseInfoVO.getResMineInstanceID());

            List<BattleUnitVO> units = mineDefenseInfoVO.getUnits();
            for (BattleUnitVO battleUnitVO : units) {
                Builder buildBattleUnit = battleUnitControl.buildBattleUnit(battleUnitVO);
                resBuilder.addUnitInfo(buildBattleUnit);
            }

        } catch (Exception e) {
            resBuilder.setErrorCode(SeaException.logoutException(e, roleId));
        }
        return resBuilder.build().toByteArray();
    }

    /**
     * 资源点防御布置
     *
     * @param roleId
     * @param byteData
     * @return
     */
    public byte[] mineDefenseDeploy(int roleId, byte[] byteData) {
        Mine_Defense_Deploy_Res.Builder resBuilder = Mine_Defense_Deploy_Res.newBuilder();
        try {
            Mine_Defense_Deploy_Req req = Mine_Defense_Deploy_Req.parseFrom(byteData);
            List<Battle_Unit_Position> reqBattleUnitList = req.getBattleUnitInfoList();
            List<PvpBattleUnitVO> battleUnitList = new ArrayList<PvpBattleUnitVO>();
            for (Battle_Unit_Position mineBattleUnit : reqBattleUnitList) {
                PvpBattleUnitVO pvpBattleUnitVO = new PvpBattleUnitVO();
                pvpBattleUnitVO.setBattleUnitID(mineBattleUnit.getBattleUnitID());
                pvpBattleUnitVO.setUnitLevel(mineBattleUnit.getUnitLevel());
                pvpBattleUnitVO.setxPosition(mineBattleUnit.getXPosition());
                pvpBattleUnitVO.setzPosition(mineBattleUnit.getZPosition());
                battleUnitList.add(pvpBattleUnitVO);
            }
            int result = mineService.mineDefenseDeploy(roleId, req.getResMineInstanceID(), req.getBattleUnitIndex(), battleUnitList,
                    req.getMineInfo(), req.getBlockHouseNPCInfo(), req.getCommanderInstruction(), req.getLandMineInfo());
            if (result == 1) {
                resBuilder.setErrorCode(SeaErrorCode.OK.getErrorCode());
                resBuilder.setBattleUnitIndex(req.getBattleUnitIndex());
            } else {
                resBuilder.setErrorCode(SeaErrorCode.ERROR_UNKNOW.getErrorCode());
            }

        } catch (Exception e) {
            resBuilder.setErrorCode(SeaException.logoutException(e, roleId));
        }
        return resBuilder.build().toByteArray();
    }

    /**
     * 资源点战斗开始
     *
     * @param roleId
     * @param byteData
     * @return
     */
    public byte[] mineBattleStart(int roleId, byte[] byteData) {
        Mine_Battle_Start_Res.Builder resBuilder = Mine_Battle_Start_Res.newBuilder();

        try {
            Mine_Battle_Start_Req req = Mine_Battle_Start_Req.parseFrom(byteData);
            long resMineIntanceID = req.getResMineInstanceID();
            List<BattleUnitVO> reqBattleUnitVOList = new ArrayList<BattleUnitVO>();
            List<Battle_Unit_Start_Mes> battleUnitList = req.getBattleUnitListList();
            for (Battle_Unit_Start_Mes battle_Unit_Mes : battleUnitList) {
                BattleUnitVO reqBattleUnitVO = new BattleUnitVO();
                reqBattleUnitVO.setBattleUnitID(battle_Unit_Mes.getBattleUnitID());
                reqBattleUnitVO.setUnitLevel(battle_Unit_Mes.getUnitLevel());
                reqBattleUnitVO.setUnitNum(battle_Unit_Mes.getUnitNum());
                reqBattleUnitVOList.add(reqBattleUnitVO);
            }
            MineBattleStartVO mineBattleStartVO = mineService.mineBattleStart(roleId, resMineIntanceID, reqBattleUnitVOList);
            resBuilder.setErrorCode(SeaErrorCode.OK.getErrorCode());
            resBuilder.setLimitTime(mineBattleStartVO.getLimitTime());
            resBuilder.setChangeAI(mineBattleStartVO.getChangeAI());
            resBuilder.setChangeAITime(mineBattleStartVO.getChangeAITime());
            resBuilder.setIsPlayer(mineBattleStartVO.isPlayer());

            Player_Tactics_Mes.Builder tacticsItem = Player_Tactics_Mes.newBuilder();
            tacticsItem.setPlayerPower(mineBattleStartVO.getPlayerPower());
            if ((mineBattleStartVO.getPlayerTactics() != null) && (mineBattleStartVO.getPlayerTactics().size() > 0)) {
                List<PlayerTacticVO> tacticsList = mineBattleStartVO.getPlayerTactics();
                for (PlayerTacticVO playerTacticVO : tacticsList) {
                    Player_Tactics_Unit.Builder unitItem = Player_Tactics_Unit.newBuilder();
                    unitItem.setTacticsID(playerTacticVO.getTacticID());
                    unitItem.setTacticsLevel(playerTacticVO.getLevel());
                    tacticsItem.addTacticsList(unitItem);
                }
            }
            resBuilder.setPlayerTactics(tacticsItem);

            resBuilder.setCommanderInstruction(mineBattleStartVO.getCommanderInstruction());
            if (mineBattleStartVO.getLandMineInfo() != null)
                resBuilder.setLandMineInfo(mineBattleStartVO.getLandMineInfo());

            // 如果对手是玩家的话，需要根据玩家的科技、勋章等对战斗单位进行强化
            if ((mineBattleStartVO.getPlayerBattleUnitList() != null) && (mineBattleStartVO.getPlayerBattleUnitList().size() > 0)) {
                for (BattleUnitVO battleUnitVO : mineBattleStartVO.getPlayerBattleUnitList()) {
                    Battle_Unit.Builder battleUnit = Battle_Unit.newBuilder();
                    battleUnit.setBattleUnitID(battleUnitVO.getBattleUnitID());
                    battleUnit.setUnitLevel(battleUnitVO.getUnitLevel());
                    battleUnit.setStorageNum(battleUnitVO.getStorageNum());
                    battleUnit.setAccurateRate(battleUnitVO.getAccurateRate());
                    battleUnit.setHealth(battleUnitVO.getHealth());
                    battleUnit.setAttackDamage(battleUnitVO.getAttackDamage());
                    battleUnit.setFrontArmor(battleUnitVO.getFrontArmor());
                    battleUnit.setSideArmor(battleUnitVO.getSideArmor());
                    battleUnit.setAttackFrequency(battleUnitVO.getAttackFrequency());
                    battleUnit.setAttackRange(battleUnitVO.getAttackRange());
                    battleUnit.setCampID(battleUnitVO.getCampID());
                    battleUnit.setCritRate(battleUnitVO.getCritRate());
                    battleUnit.setDisAttackRange(battleUnitVO.getDisAttackRange());
                    battleUnit.setDodgeRate(battleUnitVO.getDodgeRate());
                    if (battleUnitVO.getIcon() != null)
                        battleUnit.setIcon(battleUnitVO.getIcon());
                    battleUnit.setInnerDamageRate(battleUnitVO.getInnerDamageRate());
                    battleUnit.setInnerRange(battleUnitVO.getInnerRange());
                    battleUnit.setOuterDamageRate(battleUnitVO.getOuterDamageRate());
                    battleUnit.setOuterRange(battleUnitVO.getOuterRange());
                    battleUnit.setSightRange(battleUnitVO.getSightRange());
                    battleUnit.setType(battleUnitVO.getType());
                    battleUnit.setSubType(battleUnitVO.getSubType());
                    battleUnit.setCannonType(battleUnitVO.getCannonType());
                    battleUnit.setModelAmount(battleUnitVO.getModelAmount());
                    battleUnit.setEffectID(battleUnitVO.getEffectID());
                    battleUnit.setNameInCh(battleUnitVO.getNameInCh());
                    battleUnit.setNameInEn(battleUnitVO.getNameInEn());
                    if (battleUnitVO.getModel() != null)
                        battleUnit.setModel(battleUnitVO.getModel());
                    battleUnit.setSpeedOnGrass(battleUnitVO.getSpeedOnGrass());
                    battleUnit.setSpeedOnHill(battleUnitVO.getSpeedOnHill());
                    battleUnit.setSpeedOnSand(battleUnitVO.getSpeedOnSand());
                    battleUnit.setSpeedOnSnow(battleUnitVO.getSpeedOnSnow());
                    battleUnit.setSpeedOnRoad(battleUnitVO.getSpeedOnRoad());
                    battleUnit.setRotationSpeed(battleUnitVO.getRotationSpeed());
                    if (battleUnitVO.getSpeedForView() != null)
                        battleUnit.setSpeedForView(battleUnitVO.getSpeedForView());
                    if (battleUnitVO.getEnginePowerForView() != null)
                        battleUnit.setEnginePowerForView(battleUnitVO.getEnginePowerForView());
                    if (battleUnitVO.getWeightForView() != null)
                        battleUnit.setWeightForView(battleUnitVO.getWeightForView());
                    battleUnit.setRewardPower(battleUnitVO.getRewardPower());

                    if (battleUnitVO.getDeathAction() != null) {
                        battleUnit.setDeathAction(battleUnitVO.getDeathAction());
                    }

                    if (battleUnitVO.getDeathParticle() != null) {
                        battleUnit.setDeathParticle(battleUnitVO.getDeathParticle());
                    }
                    battleUnit.setClassLevel(battleUnitVO.getClassLevel());

                    List<SkillVO> skill = battleUnitVO.getSkill();
                    for (SkillVO skillVO : skill) {
                        Skill.Builder skillInfo = Skill.newBuilder();
                        skillInfo.setLevel(skillVO.getLevel());
                        skillInfo.setSkill(skillVO.getSkill());

                        battleUnit.addSkill(skillInfo);
                    }

                    resBuilder.addPlayerBattleUnitInfo(battleUnit);
                }
            }

            if (mineBattleStartVO.getBattleUnitMapInfo() != null) {
                for (PvpBattleUnitVO pvpBattleUnitVO : mineBattleStartVO.getBattleUnitMapInfo()) {
                    Battle_Unit_Position.Builder battleUnitBuilder = Battle_Unit_Position.newBuilder();
                    battleUnitBuilder.setBattleUnitID(pvpBattleUnitVO.getBattleUnitID());
                    battleUnitBuilder.setUnitLevel(pvpBattleUnitVO.getUnitLevel());
                    battleUnitBuilder.setXPosition(pvpBattleUnitVO.getxPosition());
                    battleUnitBuilder.setZPosition(pvpBattleUnitVO.getzPosition());
                    resBuilder.addBattleUnitMapInfo(battleUnitBuilder);
                }
            }

            if (mineBattleStartVO.getMineInfo() != null)
                resBuilder.setMineInfo(mineBattleStartVO.getMineInfo());

            if (mineBattleStartVO.getMineNPCInfo() != null)
                resBuilder.setMineNPCInfo(mineBattleStartVO.getMineNPCInfo());

            if (mineBattleStartVO.getNpcTactics() != null)
                resBuilder.setNpcTactics(mineBattleStartVO.getNpcTactics());

            if (mineBattleStartVO.getBlockHouseNPCInfo() != null)
                resBuilder.setBlockHouseNPCInfo(mineBattleStartVO.getBlockHouseNPCInfo());

        } catch (Exception e) {
            resBuilder.setErrorCode(SeaException.logoutException(e, roleId));
        }
        return resBuilder.build().toByteArray();
    }

    /**
     * 资源点战斗结束
     *
     * @param roleId
     * @param byteData
     * @return
     */
    public byte[] mineBattleEnd(int roleId, byte[] byteData) {
        Mine_Battle_End_Res.Builder resBuilder = Mine_Battle_End_Res.newBuilder();

        try {
            Mine_Battle_End_Req req = Mine_Battle_End_Req.parseFrom(byteData);
            long resMineIntanceID = req.getResMineInstanceID();
            int battleRes = req.getBattleRes();
            List<BattleUnitStartVO> reqBattleUnitVOList = new ArrayList<BattleUnitStartVO>();
            List<Battle_Unit_Start_Mes> battleUnitList = req.getTotalListList();
            for (Battle_Unit_Start_Mes battle_Unit_Mes : battleUnitList) {
                BattleUnitStartVO reqBattleUnitVO = new BattleUnitStartVO();
                reqBattleUnitVO.setBattleUnitID(battle_Unit_Mes.getBattleUnitID());
                reqBattleUnitVO.setUnitLevel(battle_Unit_Mes.getUnitLevel());
                reqBattleUnitVO.setUnitNum(battle_Unit_Mes.getUnitNum());
                reqBattleUnitVOList.add(reqBattleUnitVO);
            }

            List<BattleUnitStartVO> reqDamageBattleUnitVOList = new ArrayList<BattleUnitStartVO>();
            List<Battle_Unit_Start_Mes> damageBattleUnitList = req.getDamageListList();
            for (Battle_Unit_Start_Mes battle_Unit_Mes : damageBattleUnitList) {
                BattleUnitStartVO reqBattleUnitVO = new BattleUnitStartVO();
                reqBattleUnitVO.setBattleUnitID(battle_Unit_Mes.getBattleUnitID());
                reqBattleUnitVO.setUnitLevel(battle_Unit_Mes.getUnitLevel());
                reqBattleUnitVO.setUnitNum(battle_Unit_Mes.getUnitNum());
                reqDamageBattleUnitVOList.add(reqBattleUnitVO);
            }
            MineBattleEndVO mineBattleStartVO = mineService.mineBattleEnd(roleId, resMineIntanceID, battleRes, reqBattleUnitVOList, reqDamageBattleUnitVOList);
            resBuilder.setErrorCode(SeaErrorCode.OK.getErrorCode());
            resBuilder.setGold(mineBattleStartVO.getGold());
            resBuilder.setIron(mineBattleStartVO.getIron());
            resBuilder.setOil(mineBattleStartVO.getOil());
            resBuilder.setStone(mineBattleStartVO.getStone());
        } catch (Exception e) {
            resBuilder.setErrorCode(SeaException.logoutException(e, roleId));
        }
        return resBuilder.build().toByteArray();
    }

    /**
     * 获取资源点战斗日志
     *
     * @param roleId
     * @param byteData
     * @return
     */
    public byte[] getMineBattleLog(int roleId, byte[] byteData) {
        Mine_Battle_Log_Res.Builder resBuilder = Mine_Battle_Log_Res.newBuilder();
        try {
            List<MineBattleInfoVO> mineBattleInfoList = mineService.getMineBattleInfoList(roleId);
            for (MineBattleInfoVO mineBattleInfoVO : mineBattleInfoList) {
                Mine_Battle_Info_Mes.Builder item = Mine_Battle_Info_Mes.newBuilder();
                item.setBattleResult(mineBattleInfoVO.getBattleResult());
                for (BattleUnitStartVO battleUnitStartVO : mineBattleInfoVO.getAttackerBattleUnit()) {
                    Battle_Unit_Start_Mes.Builder unitItem = Battle_Unit_Start_Mes.newBuilder();
                    unitItem.setBattleUnitID(battleUnitStartVO.getBattleUnitID());
                    unitItem.setUnitLevel(battleUnitStartVO.getUnitLevel());
                    unitItem.setUnitNum(battleUnitStartVO.getUnitNum());
                    item.addAttackerBattleUnit(unitItem);
                }
                for (BattleUnitStartVO battleUnitStartVO : mineBattleInfoVO.getDamageBattleUnit()) {
                    Battle_Unit_Start_Mes.Builder unitItem = Battle_Unit_Start_Mes.newBuilder();
                    unitItem.setBattleUnitID(battleUnitStartVO.getBattleUnitID());
                    unitItem.setUnitLevel(battleUnitStartVO.getUnitLevel());
                    unitItem.setUnitNum(battleUnitStartVO.getUnitNum());
                    item.addDamageBattleUnit(unitItem);
                }
                item.setBattleTime(mineBattleInfoVO.getBattleTime());
                item.setAttackerRoleId(mineBattleInfoVO.getAttackerRoleId());
                item.setAttackerRoleName(mineBattleInfoVO.getAttackerRoleName());
                item.setAttackerAvatar(mineBattleInfoVO.getAttackerAvatar());
                item.setAttackerCampId(mineBattleInfoVO.getAttackerCampId());
                item.setAttackerGeneralDegree(mineBattleInfoVO.getAttackerGeneralDegree());
                item.setAttackerRoleHonor(mineBattleInfoVO.getAttackerRoleHonor());
                item.setAttackerRoleGrade(mineBattleInfoVO.getAttackerRoleGrade());
                item.setRewardHonor(mineBattleInfoVO.getRewardHonor());
                item.setResMineInstanceID(mineBattleInfoVO.getResMineInstanceID());
                item.setResMineID(mineBattleInfoVO.getResMineID());
                item.setProduceResType(mineBattleInfoVO.getProduceResType());
                item.setRewardResource(mineBattleInfoVO.getRewardResource());
                resBuilder.addAttackedList(item);

            }
            resBuilder.setErrorCode(SeaErrorCode.OK.getErrorCode());
        } catch (Exception e) {
            resBuilder.setErrorCode(SeaException.logoutException(e, roleId));
        }
        return resBuilder.build().toByteArray();
    }

    /**
     * 资源矿点刷新玩家
     *
     * @param roleId
     * @param byteData
     * @return
     */
    public byte[] mineChangePlayer(int roleId, byte[] byteData) {
        Mine_Change_Player_Res.Builder resBuilder = Mine_Change_Player_Res.newBuilder();
        try {
            Mine_Change_Player_Req req = Mine_Change_Player_Req.parseFrom(byteData);

            MineInfoVO mineInfoVO = mineService.changePlayer(roleId, req.getResMineInstanceID(), true);
            BI_Mine_Info.Mine_Info_Mes.Builder item = BI_Mine_Info.Mine_Info_Mes.newBuilder();
            item.setResMineInstanceID(mineInfoVO.getResMineInstanceID());
            item.setIsUnlock(mineInfoVO.isUnlock());
            item.setResMineID(mineInfoVO.getResMineID());
            item.setMineBuildingID(mineInfoVO.getMineBuildingID());
            item.setMineBuildingLevel(mineInfoVO.getMineBuildingLevel());
            item.setHealth(mineInfoVO.getHealth());
            item.setMineState(mineInfoVO.getMineState());
            item.setProducePerHour(mineInfoVO.getProducePerHour());
            item.setProduceResType(mineInfoVO.getProduceResType());
            item.setRewardRes(mineInfoVO.getRewardRes());

            if (mineInfoVO.getOtherRoleId() > 0) {
                item.setOtherRoleId(mineInfoVO.getOtherRoleId());
                item.setOtherRoleName(mineInfoVO.getOtherRoleName());
                item.setOtherCampId(mineInfoVO.getOtherCampId());
                item.setOtherAvatar(mineInfoVO.getOtherAvatar());
                item.setOtherGeneralDegree(mineInfoVO.getOtherGeneralDegree());
                //item.setOtherRoleHonor(mineInfoVO.getOtherRoleHonor());
                item.setOtherBlockHouseNpcInfo(mineInfoVO.getOtherBlockHouseNpcInfo());
                if (mineInfoVO.getBattleUnitInfo() != null) {
                    for (PvpBattleUnitVO pvpBattleUnitVO : mineInfoVO.getBattleUnitInfo()) {
                        Battle_Unit_Position.Builder battleUnitBuilder = Battle_Unit_Position.newBuilder();
                        battleUnitBuilder.setBattleUnitID(pvpBattleUnitVO.getBattleUnitID());
                        battleUnitBuilder.setUnitLevel(pvpBattleUnitVO.getUnitLevel());
                        battleUnitBuilder.setXPosition(pvpBattleUnitVO.getxPosition());
                        battleUnitBuilder.setZPosition(pvpBattleUnitVO.getzPosition());
                        item.addOtherBattleUnitInfo(battleUnitBuilder);
                    }
                }
            }

            resBuilder.setErrorCode(SeaErrorCode.OK.getErrorCode());
            resBuilder.setNewMineInfo(item);
        } catch (Exception e) {
            resBuilder.setErrorCode(SeaException.logoutException(e, roleId));
        }
        return resBuilder.build().toByteArray();
    }

    /**
     * 查询玩家资源矿点可收割资源
     *
     * @param roleId
     * @param byteData
     * @return
     */
    public byte[] mineHarvestQuery(int roleId, byte[] byteData) {
        Mine_Harvest_Query_Res.Builder resBuilder = Mine_Harvest_Query_Res.newBuilder();
        try {

            List<MineHarvestResourceVO> mineHarvestResList = playerMineResourceService.queryHarvestResource(roleId);
            for (MineHarvestResourceVO mineHarvestResourceVO : mineHarvestResList) {
                BI_Mine_Resource.Mine_Resource_Mes.Builder item = BI_Mine_Resource.Mine_Resource_Mes.newBuilder();
                item.setProduceNum(mineHarvestResourceVO.getProduceNum());
                item.setProduceResType(mineHarvestResourceVO.getProduceResType());
                item.setIsMineCarDrive(mineHarvestResourceVO.isMineCarDrive());
                item.setProduceMuduls(mineHarvestResourceVO.getProduceMuduls());
                item.setMineResourceNum(mineHarvestResourceVO.getMineResourceNum());
                item.setProduceNumPerhour(mineHarvestResourceVO.getProducePerHour());
                resBuilder.addHarvestRes(item);
            }

            resBuilder.setErrorCode(SeaErrorCode.OK.getErrorCode());
        } catch (Exception e) {
            resBuilder.setErrorCode(SeaException.logoutException(e, roleId));
        }
        return resBuilder.build().toByteArray();
    }

    /**
     * 玩家资源矿点收割资源
     *
     * @param roleId
     * @param byteData
     * @return
     */
    public byte[] mineHarvestResource(int roleId, byte[] byteData) {
        Mine_Harvest_Resource_Res.Builder resBuilder = Mine_Harvest_Resource_Res.newBuilder();
        try {
            Mine_Harvest_Resource_Req req = Mine_Harvest_Resource_Req.parseFrom(byteData);
            MineHarvestResourceVO mineHarvestResourceVO = playerMineResourceService.harvestResource(roleId, (short) req.getProduceResType());
            BI_Mine_Resource.Mine_Resource_Mes.Builder item = BI_Mine_Resource.Mine_Resource_Mes.newBuilder();
            item.setProduceNum(mineHarvestResourceVO.getProduceNum());
            item.setProduceResType(mineHarvestResourceVO.getProduceResType());
            resBuilder.setHarvestRes(item);

            resBuilder.setErrorCode(SeaErrorCode.OK.getErrorCode());
        } catch (Exception e) {
            resBuilder.setErrorCode(SeaException.logoutException(e, roleId));
        }
        return resBuilder.build().toByteArray();
    }

    /**
     * 玩家资源矿点解锁
     *
     * @param roleId
     * @param byteData
     * @return
     */
    public byte[] mineUnlockRefresh(int roleId, byte[] byteData) {
        Mine_Unlock_Refresh_Res.Builder resBuilder = Mine_Unlock_Refresh_Res.newBuilder();
        try {
            Mine_Unlock_Refresh_Req req = Mine_Unlock_Refresh_Req.parseFrom(byteData);
            mineService.unlockRefreshMine(roleId, req.getResMineInstanceID());

            resBuilder.setErrorCode(SeaErrorCode.OK.getErrorCode());
        } catch (Exception e) {
            resBuilder.setErrorCode(SeaException.logoutException(e, roleId));
        }
        return resBuilder.build().toByteArray();
    }

    /**
     * 资源开始收集
     *
     * @param roleId
     * @param byteData
     * @return
     */
    public byte[] mineCollectStart(int roleId, byte[] byteData) {
        Mine_Collect_Start_Res.Builder resBuilder = Mine_Collect_Start_Res.newBuilder();
        try {
            Mine_Collect_Start_Req req = Mine_Collect_Start_Req.parseFrom(byteData);
            mineCollectService.collectStart(roleId, req.getResMineInstanceID());
            resBuilder.setErrorCode(SeaErrorCode.OK.getErrorCode());
        } catch (Exception e) {
            resBuilder.setErrorCode(SeaException.logoutException(e, roleId));
        }
        return resBuilder.build().toByteArray();

    }

    /**
     * 玩家进入资源点
     *
     * @param roleId
     * @param byteData
     * @return
     */
    public byte[] mineCollectLog(int roleId, byte[] byteData) {
        Mine_Collect_Log_Res.Builder resBuilder = Mine_Collect_Log_Res.newBuilder();
        try {
            Mine_Collect_Log_Req req = Mine_Collect_Log_Req.parseFrom(byteData);
            List<MineCollectLogVO> collectLogList = mineCollectService.getCollectLogList(roleId);
            for (MineCollectLogVO mineCollectLogVO : collectLogList) {
                Mine_Collect_Info_Mes.Builder collectBuilder = Mine_Collect_Info_Mes.newBuilder();
                collectBuilder.setRoleID(mineCollectLogVO.getRoleID());
                collectBuilder.setResMineInstanceID(mineCollectLogVO.getResMineInstanceID());
                collectBuilder.setCollectEndTime(mineCollectLogVO.getCollectEndTime());
                collectBuilder.setCollectResult(mineCollectLogVO.getCollectResult());
                if (mineCollectLogVO.getCollectResult() == AppConfig.BATTLE_SUCCESS_RESULT) {
                    collectBuilder.setProduceResType(mineCollectLogVO.getProduceResType());
                    collectBuilder.setRewardResource(mineCollectLogVO.getRewardResource());
                } else {
                    collectBuilder.setAttackerRoleId(mineCollectLogVO.getAttackerRoleId());
                    collectBuilder.setAttackerRoleName(mineCollectLogVO.getAttackerRoleName());
                }
                resBuilder.addCollectList(collectBuilder);
            }
            resBuilder.setErrorCode(SeaErrorCode.OK.getErrorCode());
        } catch (Exception e) {
            resBuilder.setErrorCode(SeaException.logoutException(e, roleId));
        }
        return resBuilder.build().toByteArray();
    }

    /**
     * 获取资源点保护道具剩余时间（秒数）
     *
     * @param roleId
     * @param byteData
     * @return
     */
    public byte[] mineProtectByItemLeftTime(int roleId, byte[] byteData) {
        Mine_Protect_Time_Res.Builder resBuilder = Mine_Protect_Time_Res.newBuilder();
        try {
            Mine_Protect_Time_Req req = Mine_Protect_Time_Req.parseFrom(byteData);
            int protectByItemLeftTime = mineService.getProtectByItemLeftSeconds(roleId);
            resBuilder.setProtectItemLeftTime(protectByItemLeftTime);
            resBuilder.setErrorCode(SeaErrorCode.OK.getErrorCode());
        } catch (Exception e) {
            resBuilder.setErrorCode(SeaException.logoutException(e, roleId));
        }
        return resBuilder.build().toByteArray();

    }
}
