package com.pkuvr.game_server.service;

import com.pkuvr.game_server.cache.PlayerRoleCacheManager;
import com.pkuvr.game_server.cachevo.PlayerRoleVO;
import com.pkuvr.game_server.constant.SeaErrorCode;
import com.pkuvr.game_server.constant.SmOpCode;
import com.pkuvr.game_server.dao.PlayerresourcemineMapper;
import com.pkuvr.game_server.domain.Playerresourcemine;
import com.pkuvr.game_server.domain.PlayerresourcemineExample;
import com.pkuvr.game_server.enumerate.ConsumptionTypeEnum;
import com.pkuvr.game_server.enumerate.ResourcesEnum;
import com.pkuvr.game_server.exception.*;
import com.pkuvr.game_server.network.GameServerSendService;
import com.pkuvr.game_server.network.serverpackets.*;
import com.pkuvr.game_server.proto.commons.BI_Battle_Unit.Battle_Unit;
import com.pkuvr.game_server.proto.commons.BI_Battle_Unit.Battle_Unit.Skill;
import com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Position.Battle_Unit_Position;
import com.pkuvr.game_server.proto.commons.BI_Battle_Unit_Start.Battle_Unit_Start_Mes;
import com.pkuvr.game_server.proto.commons.BI_Mine_Productivity.Mine_Productivity_Mes;
import com.pkuvr.game_server.proto.commons.BI_Pvp_Rank_Info.Pvp_Rank_Info;
import com.pkuvr.game_server.proto.serverproto.CR_Add_Honor_Send_Response.CR_Add_Honor_Send;
import com.pkuvr.game_server.proto.serverproto.CR_Add_Resource_Send_Response.CR_Add_Resource_Send;
import com.pkuvr.game_server.proto.serverproto.CR_Add_Task_Finish_Send_Response.CR_Add_Task_Finish_Send;
import com.pkuvr.game_server.proto.serverproto.CR_Add_Token_Send_Response.CR_Add_Token_Send;
import com.pkuvr.game_server.proto.serverproto.CR_Guide_Step_Finish_Send_Response.CR_Guide_Step_Finish_Send;
import com.pkuvr.game_server.proto.serverproto.CR_Mine_Info_Get_Notify_Response.CR_Mine_Info_Get_Notify_Res;
import com.pkuvr.game_server.proto.serverproto.CR_Mine_Num_Notify_Send_Response.CR_Mine_Num_Notify_Send;
import com.pkuvr.game_server.proto.serverproto.CR_Mine_Productivity_Send_Response.CR_Mine_Productivity_Send;
import com.pkuvr.game_server.proto.serverproto.CR_Reduce_Battle_Unit_Send_Response.CR_Reduce_Battle_Unit_Send;
import com.pkuvr.game_server.proto.serverproto.CR_Reduce_Plunder_Resource_Send_Response.CR_Reduce_Plunder_Resource_Send;
import com.pkuvr.game_server.proto.serverproto.CR_Role_Info_Get_Notify_Response.CR_Role_Info_Get_Notify_Res;
import com.pkuvr.game_server.proto.serverproto.CR_Role_Info_Send_Response.CR_Role_Info_Send;
import com.pkuvr.game_server.proto.serverproto.CR_Sub_Resource_Send_Response.CR_Sub_Resource_Send;
import com.pkuvr.game_server.proto.serverproto.GS2C_Friend_Invite_Send.Friend_Invite_Send;
import com.pkuvr.game_server.proto.serverproto.Pvp_Battle_Start_Response.PvpBattleTypeEnum;
import com.pkuvr.game_server.proto.serverproto.Pvp_Battle_Start_Response.Pvp_Battle_Start_Res;
import com.pkuvr.game_server.proto.serverproto.Pvp_Deploy_Verify_Notify_Response.Pvp_Deploy_Verify_Notify_Res;
import com.pkuvr.game_server.proto.serverproto.Pvp_Disconnect_Response.Pvp_Disconnect_Res;
import com.pkuvr.game_server.proto.serverproto.Pvp_Friend_Notify_Response.PvpMessageEnum;
import com.pkuvr.game_server.proto.serverproto.Pvp_Friend_Notify_Response.Pvp_Friend_Notify_Res;
import com.pkuvr.game_server.proto.serverproto.Pvp_Player_Exit_Notify_Response.Pvp_Player_Exit_Notify_Res;
import com.pkuvr.game_server.proto.serverproto.Pvp_Start_Response.Pvp_Start_Res;
import com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Command_Notify_Response.Pvp_Sync_Command_Notify_Res;
import com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Common_Notify_Response.Pvp_Sync_Common_Notify_Res;
import com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Dead_Notify_Response.Pvp_Sync_Dead_Notify_Res;
import com.pkuvr.game_server.proto.serverproto.Pvp_Sync_Tactics_Notify_Response.Pvp_Sync_Tactics_Notify_Res;
import com.pkuvr.game_server.redis.BaseZSetCpt;
import com.pkuvr.game_server.vo.*;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 用来辅助下发消息的类
 *
 * @author ZY
 */
@Service("SendService")
public class SendService {
    private static final Logger logger = Logger.getLogger(SendService.class);
    @Resource
    private PlayerRoleCacheManager playerRoleCacheManager;
    @Resource
    private BaseZSetCpt pvpRoomZSetCpt;
    @Resource
    private PlayerWealthDegreeService playerWealthDegreeService;
    @Resource
    private PlayerresourcemineMapper playerresourcemineMapper;
    private GameServerSendService gameServerSendService = GameServerSendService.getInstance();

    /**
     * pvp下发匹配成功信息
     *
     * @param roleId
     */
    public void sendPvpMatchSuccess(int roleId, PvpRankInfoVO playerRoleRank, PvpRankInfoVO otherPlayerRoleRank,
                                    int rewardHonor, int subHonor, boolean isLauncher, long deployEndTime, String pvpMap, boolean isFriendPvp) {
        Pvp_Start_Res.Builder resBuilder = Pvp_Start_Res.newBuilder();

        Pvp_Rank_Info.Builder ownItem = Pvp_Rank_Info.newBuilder();
        ownItem.setPvpRank(playerRoleRank.getPvpRank());
        ownItem.setAvatar(playerRoleRank.getAvatar());
        ownItem.setCampId(playerRoleRank.getCampId());
        ownItem.setGeneralDegree(playerRoleRank.getGeneralDegree());
        ownItem.setHonor(playerRoleRank.getHonor());
        ownItem.setRoleId(playerRoleRank.getRoleId());
        ownItem.setRoleName(playerRoleRank.getRoleName());
        resBuilder.setPlayerRoleRank(ownItem);

        Pvp_Rank_Info.Builder otherItem = Pvp_Rank_Info.newBuilder();
        otherItem.setPvpRank(otherPlayerRoleRank.getPvpRank());
        otherItem.setAvatar(otherPlayerRoleRank.getAvatar());
        otherItem.setCampId(otherPlayerRoleRank.getCampId());
        otherItem.setGeneralDegree(otherPlayerRoleRank.getGeneralDegree());
        otherItem.setHonor(otherPlayerRoleRank.getHonor());
        otherItem.setRoleId(otherPlayerRoleRank.getRoleId());
        otherItem.setRoleName(otherPlayerRoleRank.getRoleName());
        resBuilder.setOtherPlayerRoleRank(otherItem);

        resBuilder.setRewardHonor(rewardHonor);
        resBuilder.setSubHonor(subHonor);
        resBuilder.setIsLauncher(isLauncher);
        resBuilder.setDeployEndTime(deployEndTime);
        resBuilder.setPvpMap(pvpMap);
        SM_PVP_START sendMessage = new SM_PVP_START(resBuilder.build().toByteArray());
        if (!isFriendPvp) {
            //activeService.addFinishNum(ActivityTaskEnum.PLAY_PVP, roleId, 1);
        }
        gameServerSendService.sendMessage(roleId, sendMessage);
    }

    /**
     * pvp下发战斗开始信息
     *
     * @param roleId
     */
    public void sendPvpBattleStart(int roleId, int otherRoleId, int otherRoleCampId, String otherAvatar, int otherFactionBattleRecord, List<PvpBattleUnitVO> otherPlayerList, String landMineInfo, PvpBattleTypeEnum battleType, boolean isReduceBlood) throws NoSuchRoleException {
        Pvp_Battle_Start_Res.Builder resBuilder = Pvp_Battle_Start_Res.newBuilder();
        PlayerRoleVO otherPlayerRoleVO = playerRoleCacheManager.get(otherRoleId);
        int playerHonor = pvpRoomZSetCpt.getScore(roleId);
        int otherPlayerHonor = pvpRoomZSetCpt.getScore(otherRoleId);
        resBuilder.setPlayerGrade(playerHonor);
        resBuilder.setOtherPlayerRoleId(otherRoleId);
        resBuilder.setOtherRoleName(otherPlayerRoleVO.getRoleName());
        resBuilder.setOtherPlayerGrade(otherPlayerHonor);
        resBuilder.setBattleStartTime(System.currentTimeMillis() + 4000L);
        resBuilder.setOtherLandMineInfo(landMineInfo);
        resBuilder.setBattleType(battleType);
        resBuilder.setOtherPlayerCampId(otherRoleCampId);
        resBuilder.setOtherRoleAvatar(otherAvatar);
        resBuilder.setOtherFactionBattleRecord(otherFactionBattleRecord);

        for (PvpBattleUnitVO pvpBattleUnitVO : otherPlayerList) {
            BattleUnitVO battleUnitVO = otherPlayerRoleVO.getBattleUnitMap().get(pvpBattleUnitVO.getBattleUnitID());

            Battle_Unit_Position.Builder pvpBattleUnit = Battle_Unit_Position.newBuilder();
            Battle_Unit.Builder battleUnit = Battle_Unit.newBuilder();
            battleUnit.setBattleUnitID(battleUnitVO.getBattleUnitID());
            battleUnit.setUnitLevel(battleUnitVO.getUnitLevel());
            battleUnit.setStorageNum(battleUnitVO.getStorageNum());
            battleUnit.setAccurateRate(battleUnitVO.getAccurateRate());
            if (!isReduceBlood)
                battleUnit.setHealth(battleUnitVO.getHealth());
            else
                battleUnit.setHealth(pvpBattleUnitVO.getHealth());
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

            pvpBattleUnit.setBattleUnitID(pvpBattleUnitVO.getBattleUnitID());
            pvpBattleUnit.setUnitLevel(pvpBattleUnitVO.getUnitLevel());
            pvpBattleUnit.setXPosition(pvpBattleUnitVO.getxPosition());
            pvpBattleUnit.setZPosition(pvpBattleUnitVO.getzPosition());
            pvpBattleUnit.setBattleUnitInstanceID(pvpBattleUnitVO.getBattleUnitInstanceID());
            pvpBattleUnit.setHealth(battleUnit.getHealth());
            resBuilder.addOtherBattleUnitPosition(pvpBattleUnit);
            resBuilder.addOtherBattleUnitList(battleUnit);
        }
        SM_PVP_BATTLE_START sendMessage = new SM_PVP_BATTLE_START(resBuilder.build().toByteArray());

        gameServerSendService.sendMessage(roleId, sendMessage);
    }

    /**
     * 下发玩家战术
     *
     * @param roleId
     */
    public void sendPvpTactics(int roleId, int otherRoleId, int tacticsId, int taticsLevel,
                               int tacticsxPosition, int tacticszPosition, String tacticsCommon, long serverTime) {
        Pvp_Sync_Tactics_Notify_Res.Builder resBuilder = Pvp_Sync_Tactics_Notify_Res.newBuilder();
        resBuilder.setTacticsId(tacticsId);
        resBuilder.setTacticsLevel(taticsLevel);
        resBuilder.setTacticsxPosition(tacticsxPosition);
        resBuilder.setTacticszPosition(tacticszPosition);
        resBuilder.setTacticsCommon(tacticsCommon);
        resBuilder.setServerTime(serverTime);
        SM_PVP_SYNC_TACTICS_NOTIFY sendMessage = new SM_PVP_SYNC_TACTICS_NOTIFY(resBuilder.build().toByteArray());
        gameServerSendService.sendMessage(otherRoleId, sendMessage);
    }

    /**
     * 下发玩家战术指令
     *
     * @param roleId
     */
    public void sendPvpCommand(int roleId, int otherRoleId, int commandId, long serverTime) {
        Pvp_Sync_Command_Notify_Res.Builder resBuilder = Pvp_Sync_Command_Notify_Res.newBuilder();
        resBuilder.setCommandId(commandId);
        resBuilder.setServerTime(serverTime);
        SM_PVP_SYNC_COMMAND_NOTIFY sendMessage = new SM_PVP_SYNC_COMMAND_NOTIFY(resBuilder.build().toByteArray());
        gameServerSendService.sendMessage(otherRoleId, sendMessage);
    }

    /**
     * 下发玩家通用信息
     *
     * @param roleId
     */
    public void sendPvpCommon(int roleId, int otherRoleId, String commonStr, long serverTime) {
        Pvp_Sync_Common_Notify_Res.Builder resBuilder = Pvp_Sync_Common_Notify_Res.newBuilder();
        resBuilder.setCommandStr(commonStr);
        resBuilder.setServerTime(serverTime);
        SM_PVP_SYNC_COMMON_NOTIFY sendMessage = new SM_PVP_SYNC_COMMON_NOTIFY(resBuilder.build().toByteArray());
        gameServerSendService.sendMessage(otherRoleId, sendMessage);
    }

    /**
     * 下发玩家战斗单位死亡
     *
     * @param otherRoleId
     * @param deadBattleUnitList
     * @param serverTime
     */
    public void sendPlayerBattleUnitDead(int otherRoleId, List<Integer> deadBattleUnitList, long serverTime) {
        Pvp_Sync_Dead_Notify_Res.Builder resBuilder = Pvp_Sync_Dead_Notify_Res.newBuilder();
        for (Integer instanceID : deadBattleUnitList) {
            resBuilder.addDeadBattleUnitList(instanceID);
        }
        resBuilder.setServerTime(serverTime);
        SM_PVP_SYNC_DEAD_NOTIFY sendMessage = new SM_PVP_SYNC_DEAD_NOTIFY(resBuilder.build().toByteArray());
        gameServerSendService.sendMessage(otherRoleId, sendMessage);
    }

    /**
     * 下发玩家pvp离线信息
     *
     * @param roleId
     */
    public void sendPvpDisconnect(int roleId, int otherRoleId, String otherRoleName, int battleRes, int rewardHonor) {
        Pvp_Disconnect_Res.Builder resBuilder = Pvp_Disconnect_Res.newBuilder();
        resBuilder.setMatchRoleId(otherRoleId);
        resBuilder.setMatchRoleName(otherRoleName);
        resBuilder.setBattleRes(battleRes);
        resBuilder.setRewardHonor(rewardHonor);
        SM_PVP_DISCONNECT sendMessage = new SM_PVP_DISCONNECT(resBuilder.build().toByteArray());
        gameServerSendService.sendMessage(roleId, sendMessage);
    }

    /**
     * 下发玩家pvp退出信息
     *
     * @param roleId
     */
    public void sendPvpPlayerExit(int roleId, int otherRoleId, String otherRoleName) {
        Pvp_Player_Exit_Notify_Res.Builder resBuilder = Pvp_Player_Exit_Notify_Res.newBuilder();
        resBuilder.setOtherPlayerRoleId(otherRoleId);
        resBuilder.setOtherPlayerRoleName(otherRoleName);

        SM_PVP_PLAYER_EXIT_NOTIFY sendMessage = new SM_PVP_PLAYER_EXIT_NOTIFY(resBuilder.build().toByteArray());
        gameServerSendService.sendMessage(roleId, sendMessage);
    }

    /**
     * 下发玩家pvp布阵检查信息
     *
     * @param roleId
     */
    public void sendPvpDeployVerify(int roleId, int battleRes) {
        Pvp_Deploy_Verify_Notify_Res.Builder resBuilder = Pvp_Deploy_Verify_Notify_Res.newBuilder();
        resBuilder.setBattleRes(battleRes);

        SM_PVP_DEPLOY_VERIFY_NOTIFY sendMessage = new SM_PVP_DEPLOY_VERIFY_NOTIFY(resBuilder.build().toByteArray());
        gameServerSendService.sendMessage(roleId, sendMessage);
    }

    /**
     * 下发好友切磋信息
     *
     * @param roleId
     * @param otherPlayerRoleRank
     * @param pvpMessage
     * @param refuseStr
     */
    public void sendFriendPvpInfo(int roleId, PvpRankInfoVO otherPlayerRoleRank, PvpMessageEnum pvpMessage, String refuseStr) {
        Pvp_Friend_Notify_Res.Builder resBuilder = Pvp_Friend_Notify_Res.newBuilder();

        Pvp_Rank_Info.Builder otherItem = Pvp_Rank_Info.newBuilder();
        //otherItem.setPvpRank(otherPlayerRoleRank.getPvpRank());
        otherItem.setAvatar(otherPlayerRoleRank.getAvatar());
        otherItem.setCampId(otherPlayerRoleRank.getCampId());
        otherItem.setGeneralDegree(otherPlayerRoleRank.getGeneralDegree());
        otherItem.setHonor(otherPlayerRoleRank.getHonor());
        otherItem.setRoleId(otherPlayerRoleRank.getRoleId());
        otherItem.setRoleName(otherPlayerRoleRank.getRoleName());
        resBuilder.setPlayerRoleRank(otherItem);
        resBuilder.setPvpMessage(pvpMessage);
        resBuilder.setReasonStr(refuseStr);

        byte[] send = resBuilder.build().toByteArray();
        SM_PVP_FRIEND_NOTIFY sendMessage = new SM_PVP_FRIEND_NOTIFY(send);
        gameServerSendService.sendMessage(roleId, sendMessage);
    }

    public void sendFriendInvite(int roleId, String name, String avatar, int camp, int level, int vip) {

        Friend_Invite_Send.Builder resBuilder = Friend_Invite_Send.newBuilder();
        resBuilder.setName(name);
        resBuilder.setAvatar(avatar);
        resBuilder.setCamp(camp);
        resBuilder.setLevel(level);
        resBuilder.setVip(vip);

        resBuilder.setErrorCode(SeaErrorCode.OK.getErrorCode());
        gameServerSendService.sendMessage(roleId, new SM_FRIEND_INVITE_SEND(resBuilder.build().toByteArray()));
    }

    public void sendCrossRoleInfo(int roleId, int senderRoleId, int serverId) {
        CR_Role_Info_Send.Builder resBuilder = CR_Role_Info_Send.newBuilder();

        resBuilder.setRoleId(roleId);
        resBuilder.setSenderRoleId(senderRoleId);
        gameServerSendService.sendMessage(serverId, new SM_CR_ROLE_INFO_SEND(resBuilder.build().toByteArray()));

    }

    public void sendCrossAddHonor(int roleId, int changeNum) {
        PlayerRoleVO playerRoleVO = playerRoleCacheManager.get(roleId);
        if (playerRoleVO != null && playerRoleVO.getServerId() > 0) {
            if (changeNum != 0) {
                int nowHonor = playerRoleVO.getRoleHonor() + changeNum;
                if (nowHonor < 0) {
                    nowHonor = 0;
                }
                pvpRoomZSetCpt.saveOrUpdate(roleId, nowHonor);
                playerRoleVO.setRoleHonor(nowHonor);
                playerRoleCacheManager.put(roleId, playerRoleVO);
            }

            CR_Add_Honor_Send.Builder resBuilder = CR_Add_Honor_Send.newBuilder();

            resBuilder.setRoleId(roleId);
            resBuilder.setChangeNum(changeNum);
            gameServerSendService.sendMessage(playerRoleVO.getServerId(), new SM_CR_ADD_HONOR_SEND(resBuilder.build().toByteArray()));

        }
    }

    public void sendCrossAddToken(int roleId, int changeNum) {
        PlayerRoleVO playerRoleVO = playerRoleCacheManager.get(roleId);
        if (playerRoleVO != null && playerRoleVO.getServerId() > 0) {

            CR_Add_Token_Send.Builder resBuilder = CR_Add_Token_Send.newBuilder();

            resBuilder.setRoleId(roleId);
            resBuilder.setChangeNum(changeNum);
            gameServerSendService.sendMessage(playerRoleVO.getServerId(), new SM_CR_ADD_TOKEN_SEND(resBuilder.build().toByteArray()));
        }
    }

    public void sendCrossAddTaskFinish(int roleId, int taskType) {
        PlayerRoleVO playerRoleVO = playerRoleCacheManager.get(roleId);
        if (playerRoleVO != null && playerRoleVO.getServerId() > 0) {

            CR_Add_Task_Finish_Send.Builder resBuilder = CR_Add_Task_Finish_Send.newBuilder();

            resBuilder.setRoleId(roleId);
            resBuilder.setTaskType(taskType);
            gameServerSendService.sendMessage(playerRoleVO.getServerId(), new SM_CR_ADD_TASK_FINISH_SEND(resBuilder.build().toByteArray()));
        }
    }

    public void sendCrossRoleInfoGetNotify(int senderRoleId, int roleId) {
        CR_Role_Info_Get_Notify_Res.Builder resBuilder = CR_Role_Info_Get_Notify_Res.newBuilder();

        resBuilder.setRoleId(roleId);
        gameServerSendService.sendMessage(senderRoleId, new SM_ROLE_INFO_GET_NOTIFY(resBuilder.build().toByteArray()));
    }

    public void sendCrossMineInfoGetNotify(int senderRoleId, int roleId) {
        CR_Mine_Info_Get_Notify_Res.Builder resBuilder = CR_Mine_Info_Get_Notify_Res.newBuilder();

        resBuilder.setRoleId(roleId);
        gameServerSendService.sendMessage(senderRoleId, new SM_MINE_INFO_GET_NOTIFY(resBuilder.build().toByteArray()));
    }

    public void sendCrossAddResource(int roleId, short produceResType, int changeNum, ConsumptionTypeEnum consumptionTypeEnum) {
        PlayerRoleVO playerRoleVO = playerRoleCacheManager.get(roleId);
        if (playerRoleVO != null && playerRoleVO.getServerId() > 0) {
            ChangePlayerWealthVO changePlayerWealthVO = new ChangePlayerWealthVO(roleId, SmOpCode.HEARTBEAT_RES, consumptionTypeEnum, "");
            ResourcesEnum resEnum = ResourcesEnum.getResource(produceResType);
            switch (resEnum) {
                case GOLD:
                    changePlayerWealthVO.setGold(changeNum);
                    break;
                case STONE:
                    changePlayerWealthVO.setStone(changeNum);
                    break;
                case OIL:
                    changePlayerWealthVO.setOil(changeNum);
                    break;
                case IRON:
                    changePlayerWealthVO.setIron(changeNum);
                    break;
                default:
                    break;
            }
            playerWealthDegreeService.addByPrimaryKey(changePlayerWealthVO, false);

            // 发送跨服加资源协议
            CR_Add_Resource_Send.Builder resBuilder = CR_Add_Resource_Send.newBuilder();

            resBuilder.setRoleId(roleId);
            resBuilder.setProduceResType(produceResType);
            resBuilder.setChangeNum(changeNum);
            gameServerSendService.sendMessage(playerRoleVO.getServerId(), new SM_CR_ADD_RESOURCE_SEND(resBuilder.build().toByteArray()));
        }
    }

    public void sendCrossSubResource(int roleId, ChangePlayerWealthVO changePlayerWealthVO) throws NotEnoughWealthException, TaskDataException, NotEnoughDiamondException, NotEnoughStoneException, NotEnoughOilException, NotEnoughIronException, NotEnoughGoldException {
        PlayerRoleVO playerRoleVO = playerRoleCacheManager.get(roleId);
        if (playerRoleVO != null && playerRoleVO.getServerId() > 0) {
            playerWealthDegreeService.subByPrimaryKey(changePlayerWealthVO);

            // 发送跨服减资源协议
            CR_Sub_Resource_Send.Builder resBuilder = CR_Sub_Resource_Send.newBuilder();

            resBuilder.setRoleId(roleId);
            resBuilder.setDiamondNum(changePlayerWealthVO.getDiamond());
            resBuilder.setGoldNum(changePlayerWealthVO.getGold());
            resBuilder.setOilNum(changePlayerWealthVO.getOil());
            resBuilder.setStoneNum(changePlayerWealthVO.getStone());
            resBuilder.setIronNum(changePlayerWealthVO.getIron());
            gameServerSendService.sendMessage(playerRoleVO.getServerId(), new SM_CR_SUB_RESOURCE_SEND(resBuilder.build().toByteArray()));
        }
    }

    public void sendCrossReduceBattleUnit(int roleId, List<BattleUnitVO> reduceBattleUnitList) {

        // 减少跨服服务器缓存兵力数
        PlayerRoleVO playerRoleVO = playerRoleCacheManager.get(roleId);
        if (playerRoleVO != null && playerRoleVO.getServerId() > 0) {
            Map<Integer, BattleUnitVO> battleUnitMap = playerRoleVO.getBattleUnitMap();
            // 发送跨服减少兵力协议
            CR_Reduce_Battle_Unit_Send.Builder resBuilder = CR_Reduce_Battle_Unit_Send.newBuilder();

            resBuilder.setRoleId(roleId);
            for (BattleUnitVO battleUnitVO : reduceBattleUnitList) {
                Battle_Unit_Start_Mes.Builder battleUnitReduce = Battle_Unit_Start_Mes.newBuilder();
                battleUnitReduce.setBattleUnitID(battleUnitVO.getBattleUnitID());
                battleUnitReduce.setUnitLevel(battleUnitVO.getUnitLevel());
                battleUnitReduce.setUnitNum(battleUnitVO.getUnitNum());
                resBuilder.addDamageList(battleUnitReduce);

                BattleUnitVO cacheBattleUnitVO = battleUnitMap.get(battleUnitVO.getBattleUnitID());
                if (cacheBattleUnitVO != null) {
                    cacheBattleUnitVO.setUnitNum(cacheBattleUnitVO.getUnitNum() - battleUnitVO.getUnitNum());
                    battleUnitMap.put(battleUnitVO.getBattleUnitID(), cacheBattleUnitVO);
                }
            }
            playerRoleVO.setBattleUnitMap(battleUnitMap);
            playerRoleCacheManager.put(roleId, playerRoleVO);
            gameServerSendService.sendMessage(playerRoleVO.getServerId(), new SM_CR_REDUCE_BATTLE_UNIT_SEND(resBuilder.build().toByteArray()));
        }
    }


    public void sendCrossMineNumNotify(int roleId, String roleName, int mineNum) {
        PlayerRoleVO playerRoleVO = playerRoleCacheManager.get(roleId);
        if (playerRoleVO != null && playerRoleVO.getServerId() > 0) {
            // 跨服发送资源点占领广播协议
            CR_Mine_Num_Notify_Send.Builder resBuilder = CR_Mine_Num_Notify_Send.newBuilder();

            resBuilder.setRoleId(roleId);
            resBuilder.setRoleName(roleName);
            resBuilder.setMineNum(mineNum);
            gameServerSendService.sendMessage(playerRoleVO.getServerId(), new SM_CR_MINE_NUM_NOTIFY_SEND(resBuilder.build().toByteArray()));
        }
    }

    public void sendCrossGuideFinishStep(int roleId, String guidePrompt) {
        PlayerRoleVO playerRoleVO = playerRoleCacheManager.get(roleId);
        if (playerRoleVO != null && playerRoleVO.getServerId() > 0) {
            // 跨服发送新手引导步骤完成
            CR_Guide_Step_Finish_Send.Builder resBuilder = CR_Guide_Step_Finish_Send.newBuilder();

            resBuilder.setRoleId(roleId);
            resBuilder.setGuidePrompt(guidePrompt);
            gameServerSendService.sendMessage(playerRoleVO.getServerId(), new SM_CR_GUIDE_STEP_FINISH_SEND(resBuilder.build().toByteArray()));
        }
    }

    public void sendCrossReducePlunderResource(int roleId) {
        PlayerRoleVO playerRoleVO = playerRoleCacheManager.get(roleId);
        if (playerRoleVO != null && playerRoleVO.getServerId() > 0) {
            // 跨服发送减少该玩家主城建筑被掠夺资源数
            CR_Reduce_Plunder_Resource_Send.Builder resBuilder = CR_Reduce_Plunder_Resource_Send.newBuilder();

            resBuilder.setRoleId(roleId);
            gameServerSendService.sendMessage(playerRoleVO.getServerId(), new SM_CR_REDUCE_PLUNDER_RESOURCE_SEND(resBuilder.build().toByteArray()));
        }
    }

    public void sendCrossMineProductivityGet(int roleId, int senderRoleId, int serverId) {
        PlayerresourcemineExample keyExample = new PlayerresourcemineExample();
        keyExample.createCriteria().andRoleidEqualTo(roleId);
        List<Playerresourcemine> list = playerresourcemineMapper.selectByExample(keyExample);
        CR_Mine_Productivity_Send.Builder resBuilder = CR_Mine_Productivity_Send.newBuilder();
        resBuilder.setRoleId(roleId);
        resBuilder.setSenderRoleId(senderRoleId);
        for (Playerresourcemine playermineresource : list) {
            Mine_Productivity_Mes.Builder mineBuilder = Mine_Productivity_Mes.newBuilder();
            mineBuilder.setNpcResMineId(playermineresource.getNpcresmineid());
            mineBuilder.setProduceResType(playermineresource.getProducerestype());
            mineBuilder.setProductivity(playermineresource.getProduceperhour());
            resBuilder.addMineProduceList(mineBuilder);
        }
        gameServerSendService.sendMessage(serverId, new SM_CR_MINE_PRODUCTIVITY_SEND(resBuilder.build().toByteArray()));
    }

}
