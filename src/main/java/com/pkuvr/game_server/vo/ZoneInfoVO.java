package com.pkuvr.game_server.vo;

import java.util.List;

public class ZoneInfoVO {
    private int resMineID;                   // 资源点ID
    private boolean isUnlock;                // 资源点是否解锁
    private boolean isChangePlayer;          // 资源点是否可以更换对手
    private int otherHeaderquarterLevel;     // 资源点建筑ID
    private int otherRoleId;                 // 其他玩家角色ID
    private String otherRoleName;            // 其他玩家角色名
    private int otherCampId;                 // 其他玩家阵营ID
    private String otherAvatar;              // 其他玩家头像
    private int otherGeneralDegree;          // 其他玩家级别
    private int otherRoleGrade;              // 其他玩家战斗力
    private boolean otherIsOnline;           // 其他玩家是否在线
    private List<PvpBattleUnitVO> battleUnitInfo;      // 其他玩家作战单位信息
    private String otherBlockHouseNpcInfo;             // 其他玩家碉堡信息
    private List<BattleUnitVO> unitInfos;
    private int rewardGold;                               // 黄金
    private int rewardOil;                               // 石油
    private int rewardStone;                           // 石头
    private int rewardIron;                               // 钢材
    private boolean isProtected;              // 是否被保护
    private int otherServerId;

    public List<BattleUnitVO> getUnitInfos() {
        return unitInfos;
    }

    public void setUnitInfos(List<BattleUnitVO> unitInfos) {
        this.unitInfos = unitInfos;
    }

    public int getResMineID() {
        return resMineID;
    }

    public void setResMineID(int resMineID) {
        this.resMineID = resMineID;
    }

    public boolean isUnlock() {
        return isUnlock;
    }

    public void setUnlock(boolean isUnlock) {
        this.isUnlock = isUnlock;
    }

    public boolean isChangePlayer() {
        return isChangePlayer;
    }

    public void setChangePlayer(boolean isChangePlayer) {
        this.isChangePlayer = isChangePlayer;
    }

    public int getOtherRoleId() {
        return otherRoleId;
    }

    public void setOtherRoleId(int otherRoleId) {
        this.otherRoleId = otherRoleId;
    }

    public String getOtherRoleName() {
        return otherRoleName;
    }

    public void setOtherRoleName(String otherRoleName) {
        this.otherRoleName = otherRoleName;
    }

    public int getOtherCampId() {
        return otherCampId;
    }

    public void setOtherCampId(int otherCampId) {
        this.otherCampId = otherCampId;
    }

    public String getOtherAvatar() {
        return otherAvatar;
    }

    public void setOtherAvatar(String otherAvatar) {
        this.otherAvatar = otherAvatar;
    }

    public int getOtherGeneralDegree() {
        return otherGeneralDegree;
    }

    public void setOtherGeneralDegree(int otherGeneralDegree) {
        this.otherGeneralDegree = otherGeneralDegree;
    }

    public boolean isOtherIsOnline() {
        return otherIsOnline;
    }

    public void setOtherIsOnline(boolean otherIsOnline) {
        this.otherIsOnline = otherIsOnline;
    }

    public List<PvpBattleUnitVO> getBattleUnitInfo() {
        return battleUnitInfo;
    }

    public void setBattleUnitInfo(List<PvpBattleUnitVO> battleUnitInfo) {
        this.battleUnitInfo = battleUnitInfo;
    }

    public String getOtherBlockHouseNpcInfo() {
        return otherBlockHouseNpcInfo;
    }

    public void setOtherBlockHouseNpcInfo(String otherBlockHouseNpcInfo) {
        this.otherBlockHouseNpcInfo = otherBlockHouseNpcInfo;
    }

    public int getOtherHeaderquarterLevel() {
        return otherHeaderquarterLevel;
    }

    public void setOtherHeaderquarterLevel(int otherHeaderquarterLevel) {
        this.otherHeaderquarterLevel = otherHeaderquarterLevel;
    }

    public int getOtherRoleGrade() {
        return otherRoleGrade;
    }

    public void setOtherRoleGrade(int otherRoleGrade) {
        this.otherRoleGrade = otherRoleGrade;
    }

    public int getRewardGold() {
        return rewardGold;
    }

    public void setRewardGold(int rewardGold) {
        this.rewardGold = rewardGold;
    }

    public int getRewardOil() {
        return rewardOil;
    }

    public void setRewardOil(int rewardOil) {
        this.rewardOil = rewardOil;
    }

    public int getRewardStone() {
        return rewardStone;
    }

    public void setRewardStone(int rewardStone) {
        this.rewardStone = rewardStone;
    }

    public int getRewardIron() {
        return rewardIron;
    }

    public void setRewardIron(int rewardIron) {
        this.rewardIron = rewardIron;
    }

    public boolean isProtected() {
        return isProtected;
    }

    public void setProtected(boolean isProtected) {
        this.isProtected = isProtected;
    }

    public int getOtherServerId() {
        return otherServerId;
    }

    public void setOtherServerId(int otherServerId) {
        this.otherServerId = otherServerId;
    }
}
