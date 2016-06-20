package com.pkuvr.game_server.protoservice;

import com.pkuvr.game_server.cache.PlayerBufferCacheMananger;
import com.pkuvr.game_server.cache.PlayerRoleCacheManager;
import com.pkuvr.game_server.cachevo.PlayerBufferVO;
import com.pkuvr.game_server.cachevo.PlayerRoleVO;
import com.pkuvr.game_server.constant.SeaErrorCode;
import com.pkuvr.game_server.dao.PlayercrossmineinfoMapper;
import com.pkuvr.game_server.dao.PlayerroleMapper;
import com.pkuvr.game_server.domain.Playercrossmineinfo;
import com.pkuvr.game_server.domain.Playerrole;
import com.pkuvr.game_server.exception.SeaException;
import com.pkuvr.game_server.proto.clientproto.CR_Mine_Info_Sync_Request.CR_Mine_Info_Sync_Req;
import com.pkuvr.game_server.proto.clientproto.CR_Mine_Productivity_Get_Request.CR_Mine_Productivity_Get_Req;
import com.pkuvr.game_server.proto.clientproto.CR_Role_Info_Get_Request.Base_Plunder_Resource_Mes;
import com.pkuvr.game_server.proto.clientproto.CR_Role_Info_Get_Request.CR_Role_Info_Get_Req;
import com.pkuvr.game_server.proto.clientproto.CR_Role_Info_Sync_Request.CR_Role_Info_Sync_Req;
import com.pkuvr.game_server.proto.commons.BI_Battle_Unit.Battle_Unit;
import com.pkuvr.game_server.proto.commons.BI_Battle_Unit.Battle_Unit.Skill;
import com.pkuvr.game_server.proto.commons.BI_Mine_Productivity.Mine_Productivity_Mes;
import com.pkuvr.game_server.proto.commons.BI_Player_Tactics.Player_Tactics_Mes;
import com.pkuvr.game_server.proto.commons.BI_Player_Tactics.Player_Tactics_Unit;
import com.pkuvr.game_server.proto.serverproto.CR_Mine_Info_Sync_Response.CR_Mine_Info_Sync_Res;
import com.pkuvr.game_server.proto.serverproto.CR_Role_Info_Sync_Response.CR_Role_Info_Sync_Res;
import com.pkuvr.game_server.redis.BaseZSetCpt;
import com.pkuvr.game_server.service.SendService;
import com.pkuvr.game_server.vo.BattleUnitVO;
import com.pkuvr.game_server.vo.MineProductivityVO;
import com.pkuvr.game_server.vo.PlayerTacticVO;
import com.pkuvr.game_server.vo.SkillVO;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("CrossServerControl")
public class CrossServerControl {
    private static final Logger logger = Logger.getLogger(CrossServerControl.class);
    @Resource
    private PlayerRoleCacheManager playerRoleCacheManager;
    @Resource
    private PlayerBufferCacheMananger playerBufferCacheMananger;
    @Resource
    private SendService sendService;
    @Resource
    private BaseZSetCpt pvpRoomZSetCpt;       //pvp房间玩家排名
    @Resource
    private BaseZSetCpt gradeRankingZSetCpt;  // 评分排行榜
    @Resource
    private PlayerroleMapper playerRoleMapper;
    @Resource
    private PlayercrossmineinfoMapper playercrossmineinfoMapper;
    @Resource
    private ObjectMapper jacksonObjectMapper;

    /**
     * 跨服获取玩家信息
     *
     * @param roleId
     * @param byteData
     * @return
     */
    public void crRoleInfoGet(byte[] byteData) {
        try {
            CR_Role_Info_Get_Req req = CR_Role_Info_Get_Req.parseFrom(byteData);
            logger.info("req.getRoleId() =========== " + req.getRoleId() + "    req.getRoleName() =========  " + req.getRoleName());
            logger.info("req.getSenderRoleId()=========== " + req.getSenderRoleId());
            PlayerRoleVO playerRoleVO = new PlayerRoleVO();
            playerRoleVO.setRoleId(req.getRoleId());
            playerRoleVO.setServerId(req.getServerId());
            playerRoleVO.setRoleName(req.getRoleName());
            playerRoleVO.setCampId(req.getCampId());
            playerRoleVO.setRoleAvatar(req.getAvatar());
            playerRoleVO.setRoleLevel(req.getGeneralDegree());
            playerRoleVO.setRoleHonor(req.getHonor());
            playerRoleVO.setRoleTon(req.getRoleTon());
            playerRoleVO.setRolePower(req.getRolePower());
            playerRoleVO.setCombatValue(req.getCombatValue());
            playerRoleVO.setRoleDiamond(req.getRoleDiamond());
            playerRoleVO.setRoleGold(req.getRoleGold());
            playerRoleVO.setRoleStone(req.getRoleStone());
            playerRoleVO.setRoleOil(req.getRoleOil());
            playerRoleVO.setRoleIron(req.getRoleIron());
            playerRoleVO.setIronStorageLimit(req.getIronStorageLimit());
            playerRoleVO.setGoldStorageLimit(req.getGoldStorageLimit());
            playerRoleVO.setOilStorageLimit(req.getOilStorageLimit());
            playerRoleVO.setStoneStorageLimit(req.getStoneStorageLimit());
            playerRoleVO.setHeaderquartersLevel(req.getHeaderquartersLevel());
            playerRoleVO.setGuideBattleStart(req.getIsGuideBattleStart());
            playerRoleVO.setGuideSaveFormation(req.getIsGuideSaveFormation());

            Player_Tactics_Mes tacticsMes = req.getTacticsList();
            List<Player_Tactics_Unit> tacticsList = tacticsMes.getTacticsListList();
            List<PlayerTacticVO> playerTacticsList = new ArrayList<PlayerTacticVO>();
            for (Player_Tactics_Unit tacticsUnit : tacticsList) {
                PlayerTacticVO playerTacticVO = new PlayerTacticVO();
                playerTacticVO.setTacticID(tacticsUnit.getTacticsID());
                playerTacticVO.setLevel(tacticsUnit.getTacticsLevel());
                playerTacticsList.add(playerTacticVO);
            }
            playerRoleVO.setTacticsList(playerTacticsList);

            List<Battle_Unit> battleUnitList = req.getPlayerBattleUnitInfoList();
            List<BattleUnitVO> battleUnitVoList = new ArrayList<BattleUnitVO>();
            Map<Integer, BattleUnitVO> battleUnitMap = new HashMap<Integer, BattleUnitVO>();
            for (Battle_Unit battleUnit : battleUnitList) {
                BattleUnitVO battleUnitVO = new BattleUnitVO();
                battleUnitVO.setBattleUnitID(battleUnit.getBattleUnitID());
                battleUnitVO.setUnitLevel(battleUnit.getUnitLevel());
                battleUnitVO.setStorageNum(battleUnit.getStorageNum());
                battleUnitVO.setAccurateRate(battleUnit.getAccurateRate());
                battleUnitVO.setHealth(battleUnit.getHealth());
                battleUnitVO.setAttackDamage(battleUnit.getAttackDamage());
                battleUnitVO.setFrontArmor(battleUnit.getFrontArmor());
                battleUnitVO.setSideArmor(battleUnit.getSideArmor());
                battleUnitVO.setAttackFrequency(battleUnit.getAttackFrequency());
                battleUnitVO.setAttackRange(battleUnit.getAttackRange());
                battleUnitVO.setCampID(battleUnit.getCampID());
                battleUnitVO.setCritRate(battleUnit.getCritRate());
                battleUnitVO.setDisAttackRange(battleUnit.getDisAttackRange());
                battleUnitVO.setDodgeRate(battleUnit.getDodgeRate());
                if (battleUnit.getIcon() != null)
                    battleUnitVO.setIcon(battleUnit.getIcon());
                battleUnitVO.setInnerDamageRate(battleUnit.getInnerDamageRate());
                battleUnitVO.setInnerRange(battleUnit.getInnerRange());
                battleUnitVO.setOuterDamageRate(battleUnit.getOuterDamageRate());
                battleUnitVO.setOuterRange(battleUnit.getOuterRange());
                battleUnitVO.setSightRange(battleUnit.getSightRange());
                battleUnitVO.setType(battleUnit.getType());
                battleUnitVO.setSubType(battleUnit.getSubType());
                battleUnitVO.setCannonType(battleUnit.getCannonType());
                battleUnitVO.setModelAmount(battleUnit.getModelAmount());
                battleUnitVO.setEffectID(battleUnit.getEffectID());
                battleUnitVO.setNameInCh(battleUnit.getNameInCh());
                battleUnitVO.setNameInEn(battleUnit.getNameInEn());
                if (battleUnit.getModel() != null)
                    battleUnitVO.setModel(battleUnit.getModel());
                battleUnitVO.setSpeedOnGrass(battleUnit.getSpeedOnGrass());
                battleUnitVO.setSpeedOnHill(battleUnit.getSpeedOnHill());
                battleUnitVO.setSpeedOnSand(battleUnit.getSpeedOnSand());
                battleUnitVO.setSpeedOnSnow(battleUnit.getSpeedOnSnow());
                battleUnitVO.setSpeedOnRoad(battleUnit.getSpeedOnRoad());
                battleUnitVO.setRotationSpeed(battleUnit.getRotationSpeed());
                if (battleUnit.getSpeedForView() != null)
                    battleUnitVO.setSpeedForView(battleUnit.getSpeedForView());
                if (battleUnit.getEnginePowerForView() != null)
                    battleUnitVO.setEnginePowerForView(battleUnit.getEnginePowerForView());
                if (battleUnit.getWeightForView() != null)
                    battleUnitVO.setWeightForView(battleUnit.getWeightForView());
                battleUnitVO.setRewardPower(battleUnit.getRewardPower());

                if (battleUnit.getDeathAction() != null) {
                    battleUnitVO.setDeathAction(battleUnit.getDeathAction());
                }

                if (battleUnit.getDeathParticle() != null) {
                    battleUnitVO.setDeathParticle(battleUnit.getDeathParticle());
                }

                battleUnitVO.setClassLevel(battleUnit.getClassLevel());
                List<SkillVO> skillResultList = new ArrayList<SkillVO>();
                List<Skill> skillList = battleUnit.getSkillList();
                for (Skill skill : skillList) {
                    SkillVO skillVO = new SkillVO();
                    skillVO.setLevel(skill.getLevel());
                    skillVO.setSkill(skill.getSkill());
                    skillResultList.add(skillVO);
                }
                battleUnitVO.setSkill(skillResultList);

                battleUnitMap.put(battleUnit.getBattleUnitID(), battleUnitVO);
                battleUnitVoList.add(battleUnitVO);
            }

            playerRoleVO.setBattleUnitMap(battleUnitMap);

            Map<Integer, Integer> baseResoucePlunderMap = new HashMap<Integer, Integer>();
            List<Base_Plunder_Resource_Mes> basePlunderResourceList = req.getBasePlunderResourceList();
            for (Base_Plunder_Resource_Mes basePlunderResourceMes : basePlunderResourceList) {
                baseResoucePlunderMap.put(basePlunderResourceMes.getBaseFunctionId(), basePlunderResourceMes.getBaseProductivity());
            }
            playerRoleVO.setBaseResoucePlunderMap(baseResoucePlunderMap);

            playerRoleCacheManager.put(req.getRoleId(), playerRoleVO);

            Playerrole playerrole = playerRoleMapper.selectByPrimaryKey(req.getRoleId());
            boolean isInsertRecord = false;
            if (playerrole == null) {
                isInsertRecord = true;
                playerrole = new Playerrole();
            }

            playerrole.setRoleid(req.getRoleId());
            playerrole.setServerid(req.getServerId());
            playerrole.setRolename(req.getRoleName());
            playerrole.setCampid((short) req.getCampId());
            playerrole.setRoleavatar(req.getAvatar());
            playerrole.setRolelevel(req.getGeneralDegree());
            playerrole.setRolehonor(req.getHonor());
            playerrole.setRoleton(req.getRoleTon());
            playerrole.setRolepower(req.getRolePower());
            playerrole.setCombatvalue(req.getCombatValue());
            playerrole.setRolediamond(req.getRoleDiamond());
            playerrole.setRolegold(req.getRoleGold());
            playerrole.setRolestone(req.getRoleStone());
            playerrole.setRoleoil(req.getRoleOil());
            playerrole.setRoleiron(req.getRoleIron());
            playerrole.setIronstoragelimit(req.getIronStorageLimit());
            playerrole.setGoldstoragelimit(req.getGoldStorageLimit());
            playerrole.setOilstoragelimit(req.getOilStorageLimit());
            playerrole.setStonestoragelimit(req.getStoneStorageLimit());
            playerrole.setHeaderquarterslevel(req.getHeaderquartersLevel());
            playerrole.setIsguidebattlestart(req.getIsGuideBattleStart());
            playerrole.setIsguidesaveformation(req.getIsGuideSaveFormation());

            try {
                playerrole.setTacticslist(jacksonObjectMapper.writeValueAsString(playerTacticsList));
                playerrole.setBattleunitlist(jacksonObjectMapper.writeValueAsString(battleUnitVoList));
                playerrole.setBaseresourcepluderlist(jacksonObjectMapper.writeValueAsString(baseResoucePlunderMap));
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            if (isInsertRecord) {
                playerRoleMapper.insertSelective(playerrole);
            } else {
                playerRoleMapper.updateByPrimaryKeySelective(playerrole);
            }

            pvpRoomZSetCpt.saveOrUpdate(req.getRoleId(), playerRoleVO.getRoleHonor());
            gradeRankingZSetCpt.saveOrUpdate(req.getRoleId(), playerRoleVO.getCombatValue());

            sendService.sendCrossRoleInfoGetNotify(req.getSenderRoleId(), req.getRoleId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 跨服获取玩家资源矿点相关信息
     *
     * @param roleId
     * @param byteData
     * @return
     */
    public void crRoleMineProductivityGet(byte[] byteData) {
        try {
            CR_Mine_Productivity_Get_Req req = CR_Mine_Productivity_Get_Req.parseFrom(byteData);
            int roleId = req.getRoleId();
            List<Mine_Productivity_Mes> mineProductivityList = req.getMineProduceListList();
            List<MineProductivityVO> inputList = new ArrayList<MineProductivityVO>();
            for (Mine_Productivity_Mes mineProductivityMes : mineProductivityList) {
                MineProductivityVO mineProductivityVO = new MineProductivityVO();
                mineProductivityVO.setNpcResMineId(mineProductivityMes.getNpcResMineId());
                mineProductivityVO.setProduceResType((short) mineProductivityMes.getProduceResType());
                mineProductivityVO.setProductivity(mineProductivityMes.getProductivity());
                inputList.add(mineProductivityVO);
            }
            PlayerBufferVO playerBufferVO = new PlayerBufferVO();
            playerBufferVO.setMineProductivityList(inputList);
            playerBufferCacheMananger.put(roleId, playerBufferVO);

            Playercrossmineinfo playercrossmineinfo = playercrossmineinfoMapper.selectByPrimaryKey(roleId);
            boolean isInsertRecord = false;
            if (playercrossmineinfo == null) {
                isInsertRecord = true;
                playercrossmineinfo = new Playercrossmineinfo();
            }

            playercrossmineinfo.setRoleid(roleId);
            try {
                playercrossmineinfo.setMineproductivitylist(jacksonObjectMapper.writeValueAsString(inputList));
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            if (isInsertRecord) {
                playercrossmineinfoMapper.insertSelective(playercrossmineinfo);
            } else {
                playercrossmineinfoMapper.updateByPrimaryKeySelective(playercrossmineinfo);
            }
            sendService.sendCrossMineInfoGetNotify(req.getRoleId(), req.getRoleId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 跨服同步玩家相关信息
     *
     * @param roleId
     * @param byteData
     * @return
     */
    public byte[] crossSyncRoleInfo(int roleId, byte[] byteData) {
        CR_Role_Info_Sync_Res.Builder resBuilder = CR_Role_Info_Sync_Res.newBuilder();
        try {
            CR_Role_Info_Sync_Req req = CR_Role_Info_Sync_Req.parseFrom(byteData);
            int syncRoleId = req.getRoleId();
            int serverId = req.getServerId();
            sendService.sendCrossRoleInfo(syncRoleId, roleId, serverId);
            // 构造返回值
            resBuilder.setErrorCode(SeaErrorCode.OK.getErrorCode());

        } catch (Exception e) {
            resBuilder.setErrorCode(SeaException.logoutException(e, roleId));
        }
        return resBuilder.build().toByteArray();
    }

    /**
     * 跨服同步玩家资源点相关信息
     *
     * @param roleId
     * @param byteData
     * @return
     */
    public byte[] crossSyncMineInfo(int roleId, byte[] byteData) {
        CR_Mine_Info_Sync_Res.Builder resBuilder = CR_Mine_Info_Sync_Res.newBuilder();
        try {
            CR_Mine_Info_Sync_Req req = CR_Mine_Info_Sync_Req.parseFrom(byteData);
            int syncRoleId = req.getRoleId();
            int serverId = req.getServerId();
            sendService.sendCrossMineProductivityGet(syncRoleId, roleId, serverId);
            // 构造返回值
            resBuilder.setErrorCode(SeaErrorCode.OK.getErrorCode());

        } catch (Exception e) {
            resBuilder.setErrorCode(SeaException.logoutException(e, roleId));
        }
        return resBuilder.build().toByteArray();
    }
}
