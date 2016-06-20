package com.pkuvr.game_server.protoservice;

import com.pkuvr.game_server.constant.SeaErrorCode;
import com.pkuvr.game_server.exception.SeaException;
import com.pkuvr.game_server.proto.clientproto.Pvp_Battle_End_Request.Pvp_Battle_End_Req;
import com.pkuvr.game_server.proto.clientproto.Pvp_Battle_Unit_Deploy_Request.Pvp_Battle_Unit_Deploy_Req;
import com.pkuvr.game_server.proto.clientproto.Pvp_Match_Request.Pvp_Match_Req;
import com.pkuvr.game_server.proto.clientproto.Pvp_Npc_Battle_End_Request.Pvp_Npc_Battle_End_Req;
import com.pkuvr.game_server.proto.clientproto.Pvp_Npc_Battle_Match_Request.Pvp_Npc_Battle_Match_Req;
import com.pkuvr.game_server.proto.clientproto.Pvp_Npc_Battle_Start_Request.Pvp_Npc_Battle_Start_Req;
import com.pkuvr.game_server.proto.clientproto.Pvp_Player_Exit_Request.Pvp_Player_Exit_Req;
import com.pkuvr.game_server.proto.clientproto.Pvp_Room_Request.Pvp_Room_Req;
import com.pkuvr.game_server.proto.clientproto.Pvp_Sync_Command_Request.Pvp_Sync_Command_Req;
import com.pkuvr.game_server.proto.clientproto.Pvp_Sync_Common_Request.Pvp_Sync_Common_Req;
import com.pkuvr.game_server.proto.clientproto.Pvp_Sync_Dead_Request.Pvp_Sync_Dead_Req;
import com.pkuvr.game_server.proto.clientproto.Pvp_Sync_Tactics_Request.Pvp_Sync_Tactics_Req;
import com.pkuvr.game_server.proto.commons.BI_Battle_Unit.Battle_Unit;
import com.pkuvr.game_server.proto.commons.BI_Battle_Unit.Battle_Unit.Skill;
import com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Position.Battle_Unit_Position;
import com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes;
import com.pkuvr.game_server.proto.commons.BI_Player_Tactics.Player_Tactics_Mes;
import com.pkuvr.game_server.proto.commons.BI_Player_Tactics.Player_Tactics_Unit;
import com.pkuvr.game_server.proto.commons.BI_Pvp_Rank_Info.Pvp_Rank_Info;
import com.pkuvr.game_server.proto.serverproto.Pvp_Battle_End_Response.Pvp_Battle_End_Res;
import com.pkuvr.game_server.proto.serverproto.Pvp_Battle_Unit_Deploy_Response.Pvp_Battle_Unit_Deploy_Res;
import com.pkuvr.game_server.proto.serverproto.Pvp_Match_Response.Pvp_Match_Res;
import com.pkuvr.game_server.proto.serverproto.Pvp_Npc_Battle_End_Response.Pvp_Npc_Battle_End_Res;
import com.pkuvr.game_server.proto.serverproto.Pvp_Npc_Battle_Match_Response.Pvp_Npc_Battle_Match_Res;
import com.pkuvr.game_server.proto.serverproto.Pvp_Npc_Battle_Start_Response.Pvp_Npc_Battle_Start_Res;
import com.pkuvr.game_server.proto.serverproto.Pvp_Player_Exit_Response.Pvp_Player_Exit_Res;
import com.pkuvr.game_server.proto.serverproto.Pvp_Room_Response.Pvp_Room_Res;
import com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Command_Response.Pvp_Sync_Command_Res;
import com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Common_Response.Pvp_Sync_Common_Res;
import com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Dead_Response.Pvp_Sync_Dead_Res;
import com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Tactics_Response.Pvp_Sync_Tactics_Res;
import com.pkuvr.game_server.proto.serverproto.Pvp_Token_Info_Response.Pvp_Token_Info_Res;
import com.pkuvr.game_server.service.RealTimePvpNpcService;
import com.pkuvr.game_server.service.RealTimePvpService;
import com.pkuvr.game_server.vo.*;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("RealTimePvpControl")
public class RealTimePvpControl {
    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(RealTimePvpControl.class);
    @Resource
    private RealTimePvpService realTimePvpService;
    @Resource
    private RealTimePvpNpcService realTimePvpNpcService;

    /**
     * 进入pvp房间
     *
     * @param roleId
     * @param byteData
     * @return
     */
    public byte[] pvpRoom(int roleId, byte[] byteData) {
        Pvp_Room_Res.Builder resBuilder = Pvp_Room_Res.newBuilder();
        try {
            Pvp_Room_Req req = Pvp_Room_Req.parseFrom(byteData);
            float clientTime = req.getClientTime();
            PvpRoomVO pvpRoomVO = realTimePvpService.enterPvpRoom(roleId);
            PvpRankInfoVO ownRankInfoVO = pvpRoomVO.getOwnRankInfo();
            Pvp_Rank_Info.Builder ownItem = Pvp_Rank_Info.newBuilder();
            ownItem.setPvpRank(ownRankInfoVO.getPvpRank());
            ownItem.setAvatar(ownRankInfoVO.getAvatar());
            ownItem.setCampId(ownRankInfoVO.getCampId());
            ownItem.setGeneralDegree(ownRankInfoVO.getGeneralDegree());
            ownItem.setHonor(ownRankInfoVO.getHonor());
            ownItem.setRoleId(ownRankInfoVO.getRoleId());
            ownItem.setRoleName(ownRankInfoVO.getRoleName());
            resBuilder.setOwnRank(ownItem);

            List<PvpRankInfoVO> rankList = pvpRoomVO.getRankInfoList();
            for (PvpRankInfoVO pvpRankInfoVO : rankList) {
                Pvp_Rank_Info.Builder item = Pvp_Rank_Info.newBuilder();
                item.setPvpRank(pvpRankInfoVO.getPvpRank());
                item.setAvatar(pvpRankInfoVO.getAvatar());
                item.setCampId(pvpRankInfoVO.getCampId());
                item.setGeneralDegree(pvpRankInfoVO.getGeneralDegree());
                item.setHonor(pvpRankInfoVO.getHonor());
                item.setRoleId(pvpRankInfoVO.getRoleId());
                item.setRoleName(pvpRankInfoVO.getRoleName());
                resBuilder.addRankInfoList(item);
            }
            // 构造返回值
            resBuilder.setClientTime(clientTime);

            resBuilder.setErrorCode(SeaErrorCode.OK.getErrorCode());

        } catch (Exception e) {
            resBuilder.setErrorCode(SeaException.logoutException(e, roleId));
        }
        return resBuilder.build().toByteArray();
    }

    /**
     * pvp匹配对手
     *
     * @param roleId
     * @param byteData
     * @return
     */
    public byte[] pvpMatch(int roleId, byte[] byteData) {
        Pvp_Match_Res.Builder resBuilder = Pvp_Match_Res.newBuilder();
        try {
            Pvp_Match_Req req = Pvp_Match_Req.parseFrom(byteData);
            float clientTime = req.getClientTime();
            int matchOpcode = req.getMatchOpcode();
            String pvpMap = req.getPvpMap();
            resBuilder.setClientTime(clientTime);
            if (matchOpcode == 1) {
                PvpMatchVO pvpMatchVO = realTimePvpService.pvpMatch(roleId, pvpMap);
                resBuilder.setMatchTime(pvpMatchVO.getMatchTime());
                resBuilder.setMatchNpcTime(pvpMatchVO.getNpcMatchTime());
            } else {
                realTimePvpService.pvpMatchCancel(roleId);
            }

            resBuilder.setErrorCode(SeaErrorCode.OK.getErrorCode());
        } catch (Exception e) {
            resBuilder.setErrorCode(SeaException.logoutException(e, roleId));
        }
        return resBuilder.build().toByteArray();
    }

    /**
     * pvp部署兵力
     *
     * @param roleId
     * @param byteData
     * @return
     */
    public byte[] pvpDeployBattleUnit(int roleId, byte[] byteData) {
        Pvp_Battle_Unit_Deploy_Res.Builder resBuilder = Pvp_Battle_Unit_Deploy_Res.newBuilder();
        try {
            Pvp_Battle_Unit_Deploy_Req req = Pvp_Battle_Unit_Deploy_Req.parseFrom(byteData);
            float clientTime = req.getClientTime();
            resBuilder.setClientTime(clientTime);
            List<Battle_Unit_Position> pvpBattleUnitList = req.getBattleUnitListList();
            List<PvpBattleUnitVO> battleUnitList = new ArrayList<PvpBattleUnitVO>();
            for (Battle_Unit_Position pvpBattleUnit : pvpBattleUnitList) {
                PvpBattleUnitVO pvpBattleUnitVO = new PvpBattleUnitVO();
                pvpBattleUnitVO.setBattleUnitID(pvpBattleUnit.getBattleUnitID());
                pvpBattleUnitVO.setUnitLevel(pvpBattleUnit.getUnitLevel());
                pvpBattleUnitVO.setUnitNum(1);
                pvpBattleUnitVO.setxPosition(pvpBattleUnit.getXPosition());
                pvpBattleUnitVO.setzPosition(pvpBattleUnit.getZPosition());
                pvpBattleUnitVO.setBattleUnitInstanceID(pvpBattleUnit.getBattleUnitInstanceID());
                battleUnitList.add(pvpBattleUnitVO);
            }

            realTimePvpService.battleUnitDeploy(roleId, req.getBattleUnitIndex(), battleUnitList, req.getLandMineInfo());
            resBuilder.setErrorCode(SeaErrorCode.OK.getErrorCode());
        } catch (Exception e) {
            resBuilder.setErrorCode(SeaException.logoutException(e, roleId));
        }
        return resBuilder.build().toByteArray();
    }

    /**
     * pvp同步战术
     *
     * @param roleId
     * @param byteData
     * @return
     */
    public byte[] pvpSyncTactics(int roleId, byte[] byteData) {
        Pvp_Sync_Tactics_Res.Builder resBuilder = Pvp_Sync_Tactics_Res.newBuilder();
        try {
            Pvp_Sync_Tactics_Req req = Pvp_Sync_Tactics_Req.parseFrom(byteData);

            long serverTime = realTimePvpService.syncTactics(roleId, req.getOtherPlayerRoleId(),
                    req.getTacticsId(), req.getTacticsLevel(), req.getTacticsxPosition(),
                    req.getTacticszPosition(), req.getTacticsCommon(), req.getClientTime());

            resBuilder.setErrorCode(SeaErrorCode.OK.getErrorCode());
            resBuilder.setServerTime(serverTime);
        } catch (Exception e) {
            resBuilder.setErrorCode(SeaException.logoutException(e, roleId));
        }
        return resBuilder.build().toByteArray();
    }

    /**
     * pvp同步死亡
     *
     * @param roleId
     * @param byteData
     * @return
     */
    public byte[] pvpSyncDead(int roleId, byte[] byteData) {
        Pvp_Sync_Dead_Res.Builder resBuilder = Pvp_Sync_Dead_Res.newBuilder();
        try {
            Pvp_Sync_Dead_Req req = Pvp_Sync_Dead_Req.parseFrom(byteData);

            long serverTime = realTimePvpService.syncBattleUnitDead(roleId, req.getOtherPlayerRoleId(),
                    req.getDeadBattleUnitListList(), req.getClientTime());

            resBuilder.setErrorCode(SeaErrorCode.OK.getErrorCode());
            resBuilder.setServerTime(serverTime);
        } catch (Exception e) {
            resBuilder.setErrorCode(SeaException.logoutException(e, roleId));
        }
        return resBuilder.build().toByteArray();
    }

    /**
     * pvp同步指挥官指令
     *
     * @param roleId
     * @param byteData
     * @return
     */
    public byte[] pvpSyncCommand(int roleId, byte[] byteData) {
        Pvp_Sync_Command_Res.Builder resBuilder = Pvp_Sync_Command_Res.newBuilder();
        try {
            Pvp_Sync_Command_Req req = Pvp_Sync_Command_Req.parseFrom(byteData);

            long serverTime = realTimePvpService.syncCommand(roleId, req.getOtherPlayerRoleId(), req.getCommandId(), req.getClientTime());

            resBuilder.setErrorCode(SeaErrorCode.OK.getErrorCode());
            resBuilder.setServerTime(serverTime);
        } catch (Exception e) {
            resBuilder.setErrorCode(SeaException.logoutException(e, roleId));
        }
        return resBuilder.build().toByteArray();
    }

    /**
     * pvp同步通用指令
     *
     * @param roleId
     * @param byteData
     * @return
     */
    public byte[] pvpSyncCommon(int roleId, byte[] byteData) {
        Pvp_Sync_Common_Res.Builder resBuilder = Pvp_Sync_Common_Res.newBuilder();
        try {
            Pvp_Sync_Common_Req req = Pvp_Sync_Common_Req.parseFrom(byteData);

            long serverTime = realTimePvpService.syncCommon(roleId, req.getOtherPlayerRoleId(), req.getCommonStr(), req.getClientTime(), req.getIsSendAll());

            resBuilder.setErrorCode(SeaErrorCode.OK.getErrorCode());
            resBuilder.setServerTime(serverTime);
        } catch (Exception e) {
            resBuilder.setErrorCode(SeaException.logoutException(e, roleId));
        }
        return resBuilder.build().toByteArray();
    }

    /**
     * pvp战斗结束
     *
     * @param roleId
     * @param byteData
     * @return
     */
    public byte[] pvpBattleEnd(int roleId, byte[] byteData) {
        Pvp_Battle_End_Res.Builder resBuilder = Pvp_Battle_End_Res.newBuilder();
        try {
            Pvp_Battle_End_Req req = Pvp_Battle_End_Req.parseFrom(byteData);
            int battleResult = req.getBattleRes();
            boolean normalEnd = req.getBattleNormalEnd();
            List<BattleUnitVO> damageBattleUnitVOList = new ArrayList<BattleUnitVO>();
            List<Battle_Unit_Start_Mes> battleUnitList = req.getOwnDamageListList();
            for (Battle_Unit_Start_Mes battle_Unit_End_Mes : battleUnitList) {
                BattleUnitVO reqBattleUnitVO = new BattleUnitVO();
                reqBattleUnitVO.setBattleUnitID(battle_Unit_End_Mes.getBattleUnitID());
                reqBattleUnitVO.setUnitLevel(battle_Unit_End_Mes.getUnitLevel());
                reqBattleUnitVO.setUnitNum(battle_Unit_End_Mes.getUnitNum());
                damageBattleUnitVOList.add(reqBattleUnitVO);
            }

            List<BattleUnitVO> otherBattleUnitVOList = new ArrayList<BattleUnitVO>();
            List<Battle_Unit_Start_Mes> otherBattleUnitList = req.getOtherDamageListList();
            for (Battle_Unit_Start_Mes battle_Unit_End_Mes : otherBattleUnitList) {
                BattleUnitVO reqBattleUnitVO = new BattleUnitVO();
                reqBattleUnitVO.setBattleUnitID(battle_Unit_End_Mes.getBattleUnitID());
                reqBattleUnitVO.setUnitLevel(battle_Unit_End_Mes.getUnitLevel());
                reqBattleUnitVO.setUnitNum(battle_Unit_End_Mes.getUnitNum());
                otherBattleUnitVOList.add(reqBattleUnitVO);
            }

            PvpBattleEndVO pvpBattleEndVO = null;
            if (normalEnd) {
                pvpBattleEndVO = realTimePvpService.pvpBattleEndNormal(roleId, battleResult, damageBattleUnitVOList);
            } else {
                pvpBattleEndVO = realTimePvpService.pvpBattleEndUnormal(roleId, battleResult, damageBattleUnitVOList, otherBattleUnitVOList);
            }

            resBuilder.setErrorCode(SeaErrorCode.OK.getErrorCode());
            resBuilder.setBattleRes(pvpBattleEndVO.getBattleResult());
            resBuilder.setRewardHonor(pvpBattleEndVO.getRewardHonor());
            resBuilder.setRewardHonorToken(pvpBattleEndVO.getHonorToken());
            resBuilder.setTokenLeftTimes(pvpBattleEndVO.getTokenLeftTimes());
        } catch (Exception e) {
            resBuilder.setErrorCode(SeaException.logoutException(e, roleId));
        }
        return resBuilder.build().toByteArray();
    }

    /**
     * pvp玩家中途退出
     *
     * @param roleId
     * @param byteData
     * @return
     */
    public byte[] pvpPlayerExit(int roleId, byte[] byteData) {
        Pvp_Player_Exit_Res.Builder resBuilder = Pvp_Player_Exit_Res.newBuilder();
        try {
            Pvp_Player_Exit_Req req = Pvp_Player_Exit_Req.parseFrom(byteData);

            List<BattleUnitVO> damageBattleUnitVOList = new ArrayList<BattleUnitVO>();
            List<Battle_Unit_Start_Mes> battleUnitList = req.getOwnDamageListList();
            for (Battle_Unit_Start_Mes battle_Unit_End_Mes : battleUnitList) {
                BattleUnitVO reqBattleUnitVO = new BattleUnitVO();
                reqBattleUnitVO.setBattleUnitID(battle_Unit_End_Mes.getBattleUnitID());
                reqBattleUnitVO.setUnitLevel(battle_Unit_End_Mes.getUnitLevel());
                reqBattleUnitVO.setUnitNum(battle_Unit_End_Mes.getUnitNum());
                damageBattleUnitVOList.add(reqBattleUnitVO);
            }
            PvpBattleEndVO pvpBattleEndVO = realTimePvpService.pvpPlayerExit(roleId, damageBattleUnitVOList);
            resBuilder.setErrorCode(SeaErrorCode.OK.getErrorCode());
            resBuilder.setBattleRes(pvpBattleEndVO.getBattleResult());
            resBuilder.setRewardHonor(pvpBattleEndVO.getRewardHonor());
            resBuilder.setRewardHonorToken(pvpBattleEndVO.getHonorToken());
        } catch (Exception e) {
            resBuilder.setErrorCode(SeaException.logoutException(e, roleId));
        }
        return resBuilder.build().toByteArray();
    }


    /**
     * pvp没匹配到玩家开始匹配npc
     *
     * @param roleId
     * @param byteData
     * @return
     */
    public byte[] pvpNpcBattleMatch(int roleId, byte[] byteData) {
        Pvp_Npc_Battle_Match_Res.Builder resBuilder = Pvp_Npc_Battle_Match_Res.newBuilder();
        try {
            Pvp_Npc_Battle_Match_Req req = Pvp_Npc_Battle_Match_Req.parseFrom(byteData);
            PvpNpcBattleMatchVO pvpBattleMatchVO = realTimePvpNpcService.pvpNpcBattleMatch(roleId, req.getMatchNpcTime());
            PvpRankInfoVO otherPlayerRank = pvpBattleMatchVO.getOtherPlayerRank();
            Pvp_Rank_Info.Builder otherItem = Pvp_Rank_Info.newBuilder();
            otherItem.setPvpRank(otherPlayerRank.getPvpRank());
            otherItem.setAvatar(otherPlayerRank.getAvatar());
            otherItem.setCampId(otherPlayerRank.getCampId());
            otherItem.setGeneralDegree(otherPlayerRank.getGeneralDegree());
            otherItem.setHonor(otherPlayerRank.getHonor());
            otherItem.setRoleId(otherPlayerRank.getRoleId());
            otherItem.setRoleName(otherPlayerRank.getRoleName());
            resBuilder.setPlayerRank(otherItem);
            resBuilder.setErrorCode(SeaErrorCode.OK.getErrorCode());
        } catch (Exception e) {
            resBuilder.setErrorCode(SeaException.logoutException(e, roleId));
        }
        return resBuilder.build().toByteArray();
    }

    /**
     * pvp没匹配到玩家开始npc战斗开始
     *
     * @param roleId
     * @param byteData
     * @return
     */
    public byte[] pvpNpcBattleStart(int roleId, byte[] byteData) {
        Pvp_Npc_Battle_Start_Res.Builder resBuilder = Pvp_Npc_Battle_Start_Res.newBuilder();
        try {
            Pvp_Npc_Battle_Start_Req req = Pvp_Npc_Battle_Start_Req.parseFrom(byteData);
            int minePower = req.getMineCastPower();
            List<BattleUnitVO> reqBattleUnitVOList = new ArrayList<BattleUnitVO>();
            List<Battle_Unit_Start_Mes> battleUnitList = req.getBattleUnitListList();
            for (Battle_Unit_Start_Mes battle_Unit_Start_Mes : battleUnitList) {
                BattleUnitVO reqBattleUnitVO = new BattleUnitVO();
                reqBattleUnitVO.setBattleUnitID(battle_Unit_Start_Mes.getBattleUnitID());
                reqBattleUnitVO.setUnitLevel(battle_Unit_Start_Mes.getUnitLevel());
                reqBattleUnitVO.setUnitNum(battle_Unit_Start_Mes.getUnitNum());
                reqBattleUnitVOList.add(reqBattleUnitVO);
            }

            PvpNpcBattleStartVO pvpBattleStartVO = realTimePvpNpcService.pvpNpcBattleStart(roleId, minePower, reqBattleUnitVOList);
            Player_Tactics_Mes.Builder tacticsItem = Player_Tactics_Mes.newBuilder();
            if ((pvpBattleStartVO.getPlayerTactics() != null) && (pvpBattleStartVO.getPlayerTactics().size() > 0)) {
                List<PlayerTacticVO> tacticsList = pvpBattleStartVO.getPlayerTactics();
                for (PlayerTacticVO playerTacticVO : tacticsList) {
                    Player_Tactics_Unit.Builder unitItem = Player_Tactics_Unit.newBuilder();
                    unitItem.setTacticsID(playerTacticVO.getTacticID());
                    unitItem.setTacticsLevel(playerTacticVO.getLevel());
                    tacticsItem.addTacticsList(unitItem);
                }
            }
            resBuilder.setEnemyTactic(tacticsItem);

            // 如果对手是玩家的话，需要根据玩家的科技、勋章等对战斗单位进行强化
            if ((pvpBattleStartVO.getPlayerBattleUnitList() != null) && (pvpBattleStartVO.getPlayerBattleUnitList().size() > 0)) {
                for (BattleUnitVO battleUnitVO : pvpBattleStartVO.getPlayerBattleUnitList()) {
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

                    resBuilder.addEnemyUnitList(battleUnit);
                }
            }

            resBuilder.setErrorCode(SeaErrorCode.OK.getErrorCode());
            resBuilder.setRewardHonor(pvpBattleStartVO.getOwnRewardHonor());
            resBuilder.setSubHonor(pvpBattleStartVO.getOwnSubHonor());

        } catch (Exception e) {
            resBuilder.setErrorCode(SeaException.logoutException(e, roleId));
        }
        return resBuilder.build().toByteArray();
    }

    /**
     * pvp没匹配到玩家开始npc战斗结束
     *
     * @param roleId
     * @param byteData
     * @return
     */
    public byte[] pvpNpcBattleEnd(int roleId, byte[] byteData) {
        Pvp_Npc_Battle_End_Res.Builder resBuilder = Pvp_Npc_Battle_End_Res.newBuilder();
        try {
            Pvp_Npc_Battle_End_Req req = Pvp_Npc_Battle_End_Req.parseFrom(byteData);
            List<BattleUnitStartVO> reqDamageBattleUnitVOList = new ArrayList<BattleUnitStartVO>();
            List<Battle_Unit_Start_Mes> damageBattleUnitList = req.getOwnDamageListList();
            for (Battle_Unit_Start_Mes battle_Unit_Mes : damageBattleUnitList) {
                BattleUnitStartVO reqBattleUnitVO = new BattleUnitStartVO();
                reqBattleUnitVO.setBattleUnitID(battle_Unit_Mes.getBattleUnitID());
                reqBattleUnitVO.setUnitLevel(battle_Unit_Mes.getUnitLevel());
                reqBattleUnitVO.setUnitNum(battle_Unit_Mes.getUnitNum());
                reqDamageBattleUnitVOList.add(reqBattleUnitVO);
            }
            int battleRes = req.getBattleRes();
            PvpNpcBattleEndVO pvpBattleEndVO = realTimePvpNpcService.pvpNpcBattleEnd(roleId, battleRes, reqDamageBattleUnitVOList);

            resBuilder.setErrorCode(SeaErrorCode.OK.getErrorCode());
            resBuilder.setBattleRes(pvpBattleEndVO.getBattleRes());
            resBuilder.setRewardHonor(pvpBattleEndVO.getRewardHonor());
            resBuilder.setRewardHonorToken(pvpBattleEndVO.getHonorToken());
            resBuilder.setTokenLeftTimes(pvpBattleEndVO.getTokenLeftTimes());

        } catch (Exception e) {
            resBuilder.setErrorCode(SeaException.logoutException(e, roleId));
        }
        return resBuilder.build().toByteArray();
    }

    /**
     * pvp代币次数
     *
     * @param roleId
     * @param byteData
     * @return
     */
    public byte[] pvpTokenInfo(int roleId, byte[] byteData) {
        Pvp_Token_Info_Res.Builder resBuilder = Pvp_Token_Info_Res.newBuilder();
        try {

            int tokenLeftTimes = realTimePvpService.getPvpTokenInfo(roleId);

            resBuilder.setErrorCode(SeaErrorCode.OK.getErrorCode());
            resBuilder.setTokenLeftTimes(tokenLeftTimes);
        } catch (Exception e) {
            resBuilder.setErrorCode(SeaException.logoutException(e, roleId));
        }
        return resBuilder.build().toByteArray();
    }


}
