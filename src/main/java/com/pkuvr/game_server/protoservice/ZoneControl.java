package com.pkuvr.game_server.protoservice;

import com.pkuvr.game_server.cache.PlayerBufferCacheMananger;
import com.pkuvr.game_server.constant.SeaErrorCode;
import com.pkuvr.game_server.exception.SeaException;
import com.pkuvr.game_server.proto.clientproto.Zone_Battle_End_Request.Zone_Battle_End_Req;
import com.pkuvr.game_server.proto.clientproto.Zone_Battle_Start_Request.Zone_Battle_Start_Req;
import com.pkuvr.game_server.proto.clientproto.Zone_Change_Player_Request.Zone_Change_Player_Req;
import com.pkuvr.game_server.proto.clientproto.Zone_Defense_Deploy_Request.Zone_Defense_Deploy_Req;
import com.pkuvr.game_server.proto.clientproto.Zone_Defense_Info_Request.Zone_Defense_Info_Req;
import com.pkuvr.game_server.proto.clientproto.Zone_Info_Request.Zone_Info_Req;
import com.pkuvr.game_server.proto.clientproto.Zone_Unlock_Refresh_Request.Zone_Unlock_Refresh_Req;
import com.pkuvr.game_server.proto.commons.BI_Battle_Unit.Battle_Unit;
import com.pkuvr.game_server.proto.commons.BI_Battle_Unit.Battle_Unit.Builder;
import com.pkuvr.game_server.proto.commons.BI_Battle_Unit.Battle_Unit.Skill;
import com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Position.Battle_Unit_Position;
import com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes;
import com.pkuvr.game_server.proto.commons.BI_Player_Tactics.Player_Tactics_Mes;
import com.pkuvr.game_server.proto.commons.BI_Player_Tactics.Player_Tactics_Unit;
import com.pkuvr.game_server.proto.commons.BI_Zone_Info;
import com.pkuvr.game_server.proto.serverproto.Zone_Battle_End_Response.Zone_Battle_End_Res;
import com.pkuvr.game_server.proto.serverproto.Zone_Battle_Log_Response.Zone_Battle_Info_Mes;
import com.pkuvr.game_server.proto.serverproto.Zone_Battle_Log_Response.Zone_Battle_Log_Res;
import com.pkuvr.game_server.proto.serverproto.Zone_Battle_Start_Response.Zone_Battle_Start_Res;
import com.pkuvr.game_server.proto.serverproto.Zone_Change_Player_Response.Zone_Change_Player_Res;
import com.pkuvr.game_server.proto.serverproto.Zone_Defense_Deploy_Response.Zone_Defense_Deploy_Res;
import com.pkuvr.game_server.proto.serverproto.Zone_Defense_Info_Response.Zone_Defense_Info_Res;
import com.pkuvr.game_server.proto.serverproto.Zone_Info_Response.Zone_Info_Res;
import com.pkuvr.game_server.proto.serverproto.Zone_Unlock_Refresh_Response.Zone_Unlock_Refresh_Res;
import com.pkuvr.game_server.service.ZoneService;
import com.pkuvr.game_server.vo.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("ZoneControl")
public class ZoneControl {
    @Resource
    private ZoneService zoneService;
    @Resource
    private PlayerBufferCacheMananger playerBufferCacheMananger;
    @Resource
    private BattleUnitControl battleUnitControl;

    /**
     * 获取玩家防区信息
     *
     * @param roleId
     * @param byteData
     * @return
     */
    public byte[] getZoneInfo(int roleId, byte[] byteData) {
        Zone_Info_Res.Builder resBuilder = Zone_Info_Res.newBuilder();
        try {
            Zone_Info_Req req = Zone_Info_Req.parseFrom(byteData);

            ZoneInfoVO zoneInfoVO = zoneService.getZoneInfo(roleId, req.getResMineID());
            BI_Zone_Info.Zone_Info_Mes.Builder item = BI_Zone_Info.Zone_Info_Mes.newBuilder();
            item.setResMineID(zoneInfoVO.getResMineID());
            item.setIsUnlock(zoneInfoVO.isUnlock());
            item.setIsChangePlayer(zoneInfoVO.isChangePlayer());

            if (zoneInfoVO.getOtherRoleId() > 0) {
                item.setIsProtected(zoneInfoVO.isProtected());
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
                if (zoneInfoVO.getBattleUnitInfo() != null) {
                    for (PvpBattleUnitVO pvpBattleUnitVO : zoneInfoVO.getBattleUnitInfo()) {
                        Battle_Unit_Position.Builder battleUnitBuilder = Battle_Unit_Position.newBuilder();
                        battleUnitBuilder.setBattleUnitID(pvpBattleUnitVO.getBattleUnitID());
                        battleUnitBuilder.setUnitLevel(pvpBattleUnitVO.getUnitLevel());
                        battleUnitBuilder.setXPosition(pvpBattleUnitVO.getxPosition());
                        battleUnitBuilder.setZPosition(pvpBattleUnitVO.getzPosition());
                        item.addOtherBattleUnitInfo(battleUnitBuilder);
                    }
                }
                item.setRewardGold(zoneInfoVO.getRewardGold());
                item.setRewardOil(zoneInfoVO.getRewardOil());
                item.setRewardStone(zoneInfoVO.getRewardStone());
                item.setRewardIron(zoneInfoVO.getRewardIron());
                item.setOtherServerId(zoneInfoVO.getOtherServerId());
            }

            List<BattleUnitVO> unitInfos = zoneInfoVO.getUnitInfos();
            if (unitInfos != null && unitInfos.size() > 0) {
                for (BattleUnitVO battleUnitVO : unitInfos) {
                    item.addUnitInfo(battleUnitControl.buildBattleUnit(battleUnitVO));
                }
            }

            resBuilder.setErrorCode(SeaErrorCode.OK.getErrorCode());
            resBuilder.setZoneList(item);
        } catch (Exception e) {
            resBuilder.setErrorCode(SeaException.logoutException(e, roleId));
        }
        return resBuilder.build().toByteArray();
    }

    /**
     * 获取玩家防区防御信息
     *
     * @param roleId
     * @param byteData
     * @return
     */
    public byte[] getZoneDefenseInfo(int roleId, byte[] byteData) {
        Zone_Defense_Info_Res.Builder resBuilder = Zone_Defense_Info_Res.newBuilder();

        try {
            Zone_Defense_Info_Req req = Zone_Defense_Info_Req.parseFrom(byteData);
            ZoneDefenseInfoVO mineDefenseInfoVO = zoneService.getZoneDefenseInfo(req.getRoleId());
            resBuilder.setErrorCode(SeaErrorCode.OK.getErrorCode());
            resBuilder.setHeaderquartersLevel(mineDefenseInfoVO.getHeaderquartersLevel());
            resBuilder.setIsProtected(mineDefenseInfoVO.isProtected());
            if (mineDefenseInfoVO.getHeaderquartersLevel() > 0) {
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

                if (mineDefenseInfoVO.getLandMineInfo() != null)
                    resBuilder.setLandMineInfo(mineDefenseInfoVO.getLandMineInfo());

                resBuilder.setCommanderInstruction(mineDefenseInfoVO.getCommanderInstruction());

                List<BattleUnitVO> units = mineDefenseInfoVO.getUnits();
                if (units != null) {
                    for (BattleUnitVO battleUnitVO : units) {
                        Builder buildBattleUnit = battleUnitControl.buildBattleUnit(battleUnitVO);
                        resBuilder.addUnitInfo(buildBattleUnit);
                    }
                }
            }

        } catch (Exception e) {
            resBuilder.setErrorCode(SeaException.logoutException(e, roleId));
        }
        return resBuilder.build().toByteArray();
    }

    /**
     * 防区防御布置
     *
     * @param roleId
     * @param byteData
     * @return
     */
    public byte[] zoneDefenseDeploy(int roleId, byte[] byteData) {
        Zone_Defense_Deploy_Res.Builder resBuilder = Zone_Defense_Deploy_Res.newBuilder();
        try {
            Zone_Defense_Deploy_Req req = Zone_Defense_Deploy_Req.parseFrom(byteData);
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
            int result = zoneService.zoneDefenseDeploy(roleId, req.getBattleUnitIndex(), battleUnitList,
                    req.getBlockHouseNPCInfo(), req.getCommanderInstruction(), req.getLandMineInfo());
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
     * 防区战斗开始
     *
     * @param roleId
     * @param byteData
     * @return
     */
    public byte[] zoneBattleStart(int roleId, byte[] byteData) {
        Zone_Battle_Start_Res.Builder resBuilder = Zone_Battle_Start_Res.newBuilder();

        try {
            Zone_Battle_Start_Req req = Zone_Battle_Start_Req.parseFrom(byteData);
            boolean isRevanche = req.getIsRevanche();
            List<BattleUnitVO> reqBattleUnitVOList = new ArrayList<BattleUnitVO>();
            List<Battle_Unit_Start_Mes> battleUnitList = req.getBattleUnitListList();
            for (Battle_Unit_Start_Mes battle_Unit_Mes : battleUnitList) {
                BattleUnitVO reqBattleUnitVO = new BattleUnitVO();
                reqBattleUnitVO.setBattleUnitID(battle_Unit_Mes.getBattleUnitID());
                reqBattleUnitVO.setUnitLevel(battle_Unit_Mes.getUnitLevel());
                reqBattleUnitVO.setUnitNum(battle_Unit_Mes.getUnitNum());
                reqBattleUnitVOList.add(reqBattleUnitVO);
            }

            ZoneBattleStartVO zoneBattleStartVO = null;
            if (isRevanche) {
                zoneBattleStartVO = zoneService.revancheBattleStart(roleId, req.getBattleLogID(), reqBattleUnitVOList);
            } else {
                zoneBattleStartVO = zoneService.zoneBattleStart(roleId, req.getResMineID(), reqBattleUnitVOList);
            }


            resBuilder.setErrorCode(SeaErrorCode.OK.getErrorCode());
            resBuilder.setLimitTime(zoneBattleStartVO.getLimitTime());
            resBuilder.setHeaderquartersLevel(zoneBattleStartVO.getHeaderquartersLevel());

            Player_Tactics_Mes.Builder tacticsItem = Player_Tactics_Mes.newBuilder();
            tacticsItem.setPlayerPower(zoneBattleStartVO.getPlayerPower());
            if ((zoneBattleStartVO.getPlayerTactics() != null) && (zoneBattleStartVO.getPlayerTactics().size() > 0)) {
                List<PlayerTacticVO> tacticsList = zoneBattleStartVO.getPlayerTactics();
                for (PlayerTacticVO playerTacticVO : tacticsList) {
                    Player_Tactics_Unit.Builder unitItem = Player_Tactics_Unit.newBuilder();
                    unitItem.setTacticsID(playerTacticVO.getTacticID());
                    unitItem.setTacticsLevel(playerTacticVO.getLevel());
                    tacticsItem.addTacticsList(unitItem);
                }
            }
            resBuilder.setPlayerTactics(tacticsItem);

            resBuilder.setCommanderInstruction(zoneBattleStartVO.getCommanderInstruction());
            if (zoneBattleStartVO.getLandMineInfo() != null)
                resBuilder.setLandMineInfo(zoneBattleStartVO.getLandMineInfo());

            // 如果对手是玩家的话，需要根据玩家的科技、勋章等对战斗单位进行强化
            if ((zoneBattleStartVO.getPlayerBattleUnitList() != null) && (zoneBattleStartVO.getPlayerBattleUnitList().size() > 0)) {
                for (BattleUnitVO battleUnitVO : zoneBattleStartVO.getPlayerBattleUnitList()) {
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

            if (zoneBattleStartVO.getBattleUnitMapInfo() != null) {
                for (PvpBattleUnitVO pvpBattleUnitVO : zoneBattleStartVO.getBattleUnitMapInfo()) {
                    Battle_Unit_Position.Builder battleUnitBuilder = Battle_Unit_Position.newBuilder();
                    battleUnitBuilder.setBattleUnitID(pvpBattleUnitVO.getBattleUnitID());
                    battleUnitBuilder.setUnitLevel(pvpBattleUnitVO.getUnitLevel());
                    battleUnitBuilder.setXPosition(pvpBattleUnitVO.getxPosition());
                    battleUnitBuilder.setZPosition(pvpBattleUnitVO.getzPosition());
                    resBuilder.addBattleUnitMapInfo(battleUnitBuilder);
                }
            }

            if (zoneBattleStartVO.getBlockHouseNPCInfo() != null)
                resBuilder.setBlockHouseNPCInfo(zoneBattleStartVO.getBlockHouseNPCInfo());

        } catch (Exception e) {
            resBuilder.setErrorCode(SeaException.logoutException(e, roleId));
        }
        return resBuilder.build().toByteArray();
    }

    /**
     * 防区战斗结束
     *
     * @param roleId
     * @param byteData
     * @return
     */
    public byte[] zoneBattleEnd(int roleId, byte[] byteData) {
        Zone_Battle_End_Res.Builder resBuilder = Zone_Battle_End_Res.newBuilder();

        try {
            Zone_Battle_End_Req req = Zone_Battle_End_Req.parseFrom(byteData);
            int battleRes = req.getBattleRes();
            boolean isRevanche = req.getIsRevanche();
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

            ZoneBattleEndVO mineBattleStartVO = null;
            if (isRevanche) {
                mineBattleStartVO = zoneService.revancheBattleEnd(roleId, req.getBattleLogID(), battleRes, reqBattleUnitVOList, reqDamageBattleUnitVOList);
            } else {
                mineBattleStartVO = zoneService.zoneBattleEnd(roleId, req.getResMineID(), battleRes, reqBattleUnitVOList, reqDamageBattleUnitVOList);
            }

            resBuilder.setErrorCode(SeaErrorCode.OK.getErrorCode());
            resBuilder.setRewardGold(mineBattleStartVO.getRewardGold());
            resBuilder.setRewardIron(mineBattleStartVO.getRewardIron());
            resBuilder.setRewardOil(mineBattleStartVO.getRewardOil());
            resBuilder.setRewardStone(mineBattleStartVO.getRewardStone());
        } catch (Exception e) {
            resBuilder.setErrorCode(SeaException.logoutException(e, roleId));
        }
        return resBuilder.build().toByteArray();
    }

    /**
     * 获取防区战斗日志
     *
     * @param roleId
     * @param byteData
     * @return
     */
    public byte[] getZoneBattleLog(int roleId, byte[] byteData) {
        Zone_Battle_Log_Res.Builder resBuilder = Zone_Battle_Log_Res.newBuilder();
        try {
            List<ZoneBattleInfoVO> mineBattleInfoList1 = zoneService.getZoneBattleInfoList(roleId, 1);
            List<ZoneBattleInfoVO> mineBattleInfoList2 = zoneService.getZoneBattleInfoList(roleId, 2);
            List<ZoneBattleInfoVO> mineBattleInfoList3 = zoneService.getZoneBattleInfoList(roleId, 3);

            for (ZoneBattleInfoVO mineBattleInfoVO : mineBattleInfoList1) {
                Zone_Battle_Info_Mes.Builder item = Zone_Battle_Info_Mes.newBuilder();
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
                item.setBattleLogID(mineBattleInfoVO.getBattleLogId());
                item.setResMineID(mineBattleInfoVO.getResMineID());
                item.setBattleTime(mineBattleInfoVO.getBattleTime());
                item.setAttackerRoleId(mineBattleInfoVO.getAttackerRoleId());
                item.setAttackerRoleName(mineBattleInfoVO.getAttackerRoleName());
                item.setAttackerAvatar(mineBattleInfoVO.getAttackerAvatar());
                item.setAttackerCampId(mineBattleInfoVO.getAttackerCampId());
                item.setAttackerGeneralDegree(mineBattleInfoVO.getAttackerGeneralDegree());
                item.setAttackerRoleGrade(mineBattleInfoVO.getAttackerRoleGrade());
                item.setRewardGold(mineBattleInfoVO.getRewardGold());
                item.setRewardOil(mineBattleInfoVO.getRewardOil());
                item.setRewardStone(mineBattleInfoVO.getRewardStone());
                item.setRewardIron(mineBattleInfoVO.getRewardIron());
                item.setIsRevanche(mineBattleInfoVO.isRevanche());
                item.setIsOnline(mineBattleInfoVO.isOnline());
                resBuilder.addAttackedList(item);
            }
            for (ZoneBattleInfoVO mineBattleInfoVO : mineBattleInfoList2) {
                Zone_Battle_Info_Mes.Builder item = Zone_Battle_Info_Mes.newBuilder();
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
                item.setBattleLogID(mineBattleInfoVO.getBattleLogId());
                item.setResMineID(mineBattleInfoVO.getResMineID());
                item.setBattleTime(mineBattleInfoVO.getBattleTime());
                item.setAttackerRoleId(mineBattleInfoVO.getAttackerRoleId());
                item.setAttackerRoleName(mineBattleInfoVO.getAttackerRoleName());
                item.setAttackerAvatar(mineBattleInfoVO.getAttackerAvatar());
                item.setAttackerCampId(mineBattleInfoVO.getAttackerCampId());
                item.setAttackerGeneralDegree(mineBattleInfoVO.getAttackerGeneralDegree());
                item.setAttackerRoleGrade(mineBattleInfoVO.getAttackerRoleGrade());
                item.setRewardGold(mineBattleInfoVO.getRewardGold());
                item.setRewardOil(mineBattleInfoVO.getRewardOil());
                item.setRewardStone(mineBattleInfoVO.getRewardStone());
                item.setRewardIron(mineBattleInfoVO.getRewardIron());
                item.setIsRevanche(mineBattleInfoVO.isRevanche());
                item.setIsOnline(mineBattleInfoVO.isOnline());
                resBuilder.addRevancheSuccessList(item);
            }
            for (ZoneBattleInfoVO mineBattleInfoVO : mineBattleInfoList3) {
                Zone_Battle_Info_Mes.Builder item = Zone_Battle_Info_Mes.newBuilder();
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
                item.setBattleLogID(mineBattleInfoVO.getBattleLogId());
                item.setResMineID(mineBattleInfoVO.getResMineID());
                item.setBattleTime(mineBattleInfoVO.getBattleTime());
                item.setAttackerRoleId(mineBattleInfoVO.getAttackerRoleId());
                item.setAttackerRoleName(mineBattleInfoVO.getAttackerRoleName());
                item.setAttackerAvatar(mineBattleInfoVO.getAttackerAvatar());
                item.setAttackerCampId(mineBattleInfoVO.getAttackerCampId());
                item.setAttackerGeneralDegree(mineBattleInfoVO.getAttackerGeneralDegree());
                item.setAttackerRoleGrade(mineBattleInfoVO.getAttackerRoleGrade());
                item.setRewardGold(mineBattleInfoVO.getRewardGold());
                item.setRewardOil(mineBattleInfoVO.getRewardOil());
                item.setRewardStone(mineBattleInfoVO.getRewardStone());
                item.setRewardIron(mineBattleInfoVO.getRewardIron());
                item.setIsRevanche(mineBattleInfoVO.isRevanche());
                item.setIsOnline(mineBattleInfoVO.isOnline());
                resBuilder.addRevanchedList(item);
            }
            resBuilder.setErrorCode(SeaErrorCode.OK.getErrorCode());
        } catch (Exception e) {
            resBuilder.setErrorCode(SeaException.logoutException(e, roleId));
        }
        return resBuilder.build().toByteArray();
    }

    /**
     * 防区刷新玩家
     *
     * @param roleId
     * @param byteData
     * @return
     */
    public byte[] zoneChangePlayer(int roleId, byte[] byteData) {
        Zone_Change_Player_Res.Builder resBuilder = Zone_Change_Player_Res.newBuilder();
        try {
            Zone_Change_Player_Req req = Zone_Change_Player_Req.parseFrom(byteData);

            ZoneInfoVO zoneInfoVO = zoneService.changePlayerZone(roleId, req.getResMineID());
            BI_Zone_Info.Zone_Info_Mes.Builder item = BI_Zone_Info.Zone_Info_Mes.newBuilder();

            item.setIsUnlock(zoneInfoVO.isUnlock());
            item.setResMineID(zoneInfoVO.getResMineID());
            item.setIsChangePlayer(zoneInfoVO.isChangePlayer());

            if (zoneInfoVO.getOtherRoleId() > 0) {
                item.setOtherHeaderquarterLevel(zoneInfoVO.getOtherHeaderquarterLevel());
                item.setOtherRoleId(zoneInfoVO.getOtherRoleId());
                item.setOtherRoleName(zoneInfoVO.getOtherRoleName());
                item.setOtherCampId(zoneInfoVO.getOtherCampId());
                item.setOtherAvatar(zoneInfoVO.getOtherAvatar());
                item.setOtherGeneralDegree(zoneInfoVO.getOtherGeneralDegree());
                item.setOtherRoleGrade(zoneInfoVO.getOtherRoleGrade());
                item.setOtherIsOnline(zoneInfoVO.isOtherIsOnline());
                item.setOtherBlockHouseNpcInfo(zoneInfoVO.getOtherBlockHouseNpcInfo());
                if (zoneInfoVO.getBattleUnitInfo() != null) {
                    for (PvpBattleUnitVO pvpBattleUnitVO : zoneInfoVO.getBattleUnitInfo()) {
                        Battle_Unit_Position.Builder battleUnitBuilder = Battle_Unit_Position.newBuilder();
                        battleUnitBuilder.setBattleUnitID(pvpBattleUnitVO.getBattleUnitID());
                        battleUnitBuilder.setUnitLevel(pvpBattleUnitVO.getUnitLevel());
                        battleUnitBuilder.setXPosition(pvpBattleUnitVO.getxPosition());
                        battleUnitBuilder.setZPosition(pvpBattleUnitVO.getzPosition());
                        item.addOtherBattleUnitInfo(battleUnitBuilder);
                    }
                }
                item.setRewardGold(zoneInfoVO.getRewardGold());
                item.setRewardOil(zoneInfoVO.getRewardOil());
                item.setRewardStone(zoneInfoVO.getRewardStone());
                item.setRewardIron(zoneInfoVO.getRewardIron());
            }

            List<BattleUnitVO> unitInfos = zoneInfoVO.getUnitInfos();
            if (unitInfos != null && unitInfos.size() > 0) {
                for (BattleUnitVO battleUnitVO : unitInfos) {
                    item.addUnitInfo(battleUnitControl.buildBattleUnit(battleUnitVO));
                }
            }

            resBuilder.setErrorCode(SeaErrorCode.OK.getErrorCode());
            resBuilder.setNewZoneInfo(item);
        } catch (Exception e) {
            resBuilder.setErrorCode(SeaException.logoutException(e, roleId));
        }
        return resBuilder.build().toByteArray();
    }

    /**
     * 玩家防区解锁
     *
     * @param roleId
     * @param byteData
     * @return
     */
    public byte[] zoneUnlockRefresh(int roleId, byte[] byteData) {
        Zone_Unlock_Refresh_Res.Builder resBuilder = Zone_Unlock_Refresh_Res.newBuilder();
        try {
            Zone_Unlock_Refresh_Req req = Zone_Unlock_Refresh_Req.parseFrom(byteData);
            zoneService.unlockRefreshZone(roleId, req.getResMineID());

            resBuilder.setErrorCode(SeaErrorCode.OK.getErrorCode());
        } catch (Exception e) {
            resBuilder.setErrorCode(SeaException.logoutException(e, roleId));
        }
        return resBuilder.build().toByteArray();
    }

}
