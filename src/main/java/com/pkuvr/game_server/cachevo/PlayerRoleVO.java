package com.pkuvr.game_server.cachevo;

import com.pkuvr.game_server.vo.BattleUnitVO;
import com.pkuvr.game_server.vo.PlayerTacticVO;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class PlayerRoleVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private int roleId; // 玩家id
    private int roleLevel; //玩家级别
    private int serverId; // 玩家所在服务器
    private int campId; // 玩家阵营id
    private int roleExp; //玩家经验
    private String roleName; // 角色名(唯一索引)
    private String roleAvatar; // 头像
    private int roleGender; // 性别(1男2女)
    private int rankLevel;
    private int rolePower; // 行动力
    private int roleTon; // 上阵吨位
    private int roleHonor;// 荣誉
    private List<PlayerTacticVO> tacticsList;
    private Map<Integer, BattleUnitVO> battleUnitMap;
    private Map<Integer, Integer> baseResoucePlunderMap;

    private int roleDiamond;
    private int roleGold;
    private int roleIron;
    private int roleOil;
    private int roleStone;
    private int combatValue;
    private int headerquartersLevel;    // 指挥部level
    private int goldStorageLimit;       // 黄金存储上限
    private int ironStorageLimit;       // 钢材存储上限
    private int stoneStorageLimit;      // 石材存储上限
    private int oilStorageLimit;        // 石油存储上限

    private boolean isGuideSaveFormation;
    private boolean isGuideBattleStart;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getRoleLevel() {
        return roleLevel;
    }

    public void setRoleLevel(int roleLevel) {
        this.roleLevel = roleLevel;
    }

    public int getServerId() {
        return serverId;
    }

    public void setServerId(int serverId) {
        this.serverId = serverId;
    }

    public int getCampId() {
        return campId;
    }

    public void setCampId(int campId) {
        this.campId = campId;
    }

    public int getRoleExp() {
        return roleExp;
    }

    public void setRoleExp(int roleExp) {
        this.roleExp = roleExp;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleAvatar() {
        return roleAvatar;
    }

    public void setRoleAvatar(String roleAvatar) {
        this.roleAvatar = roleAvatar;
    }

    public int getRoleGender() {
        return roleGender;
    }

    public void setRoleGender(int roleGender) {
        this.roleGender = roleGender;
    }

    public int getRankLevel() {
        return rankLevel;
    }

    public void setRankLevel(int rankLevel) {
        this.rankLevel = rankLevel;
    }

    public int getRolePower() {
        return rolePower;
    }

    public void setRolePower(int rolePower) {
        this.rolePower = rolePower;
    }

    public int getRoleTon() {
        return roleTon;
    }

    public void setRoleTon(int roleTon) {
        this.roleTon = roleTon;
    }

    public int getRoleHonor() {
        return roleHonor;
    }

    public void setRoleHonor(int roleHonor) {
        this.roleHonor = roleHonor;
    }

    public List<PlayerTacticVO> getTacticsList() {
        return tacticsList;
    }

    public void setTacticsList(List<PlayerTacticVO> tacticsList) {
        this.tacticsList = tacticsList;
    }

    public Map<Integer, BattleUnitVO> getBattleUnitMap() {
        return battleUnitMap;
    }

    public void setBattleUnitMap(Map<Integer, BattleUnitVO> battleUnitMap) {
        this.battleUnitMap = battleUnitMap;
    }

    public int getRoleDiamond() {
        return roleDiamond;
    }

    public void setRoleDiamond(int roleDiamond) {
        this.roleDiamond = roleDiamond;
    }

    public int getRoleGold() {
        return roleGold;
    }

    public void setRoleGold(int roleGold) {
        this.roleGold = roleGold;
    }

    public int getRoleIron() {
        return roleIron;
    }

    public void setRoleIron(int roleIron) {
        this.roleIron = roleIron;
    }

    public int getRoleOil() {
        return roleOil;
    }

    public void setRoleOil(int roleOil) {
        this.roleOil = roleOil;
    }

    public int getRoleStone() {
        return roleStone;
    }

    public void setRoleStone(int roleStone) {
        this.roleStone = roleStone;
    }

    public int getCombatValue() {
        return combatValue;
    }

    public void setCombatValue(int combatValue) {
        this.combatValue = combatValue;
    }

    public int getHeaderquartersLevel() {
        return headerquartersLevel;
    }

    public void setHeaderquartersLevel(int headerquartersLevel) {
        this.headerquartersLevel = headerquartersLevel;
    }

    public int getGoldStorageLimit() {
        return goldStorageLimit;
    }

    public void setGoldStorageLimit(int goldStorageLimit) {
        this.goldStorageLimit = goldStorageLimit;
    }

    public int getIronStorageLimit() {
        return ironStorageLimit;
    }

    public void setIronStorageLimit(int ironStorageLimit) {
        this.ironStorageLimit = ironStorageLimit;
    }

    public int getStoneStorageLimit() {
        return stoneStorageLimit;
    }

    public void setStoneStorageLimit(int stoneStorageLimit) {
        this.stoneStorageLimit = stoneStorageLimit;
    }

    public int getOilStorageLimit() {
        return oilStorageLimit;
    }

    public void setOilStorageLimit(int oilStorageLimit) {
        this.oilStorageLimit = oilStorageLimit;
    }

    public boolean isGuideSaveFormation() {
        return isGuideSaveFormation;
    }

    public void setGuideSaveFormation(boolean isGuideSaveFormation) {
        this.isGuideSaveFormation = isGuideSaveFormation;
    }

    public boolean isGuideBattleStart() {
        return isGuideBattleStart;
    }

    public void setGuideBattleStart(boolean isGuideBattleStart) {
        this.isGuideBattleStart = isGuideBattleStart;
    }

    public Map<Integer, Integer> getBaseResoucePlunderMap() {
        return baseResoucePlunderMap;
    }

    public void setBaseResoucePlunderMap(Map<Integer, Integer> baseResoucePlunderMap) {
        this.baseResoucePlunderMap = baseResoucePlunderMap;
    }

}
