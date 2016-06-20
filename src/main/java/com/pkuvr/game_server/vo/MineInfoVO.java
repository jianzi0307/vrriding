package com.pkuvr.game_server.vo;

import java.util.List;

public class MineInfoVO {
    private long resMineInstanceID; // 资源点ID
    private int resMineID; // 资源点模板ID
    private int mineBuildingID; // 资源点建筑ID
    private int mineBuildingLevel; // 资源点建筑等级
    private int health; // 资源点血量
    private int produceResType; // 产出资源类型
    private int producePerHour; // 资源产量/小时
    private int storageLimit; // 资源存储上限
    private int mineState; // 资源点状态，1为NPC占领，2为玩家占领，3为其他玩家占领
    private boolean isUnlock; // 资源点是否解锁
    private String mineNpcInfo; // npc部队信息
    private String npcBlockHouseNpcInfo; // npc碉堡信息
    private int otherRoleId; // 其他玩家角色ID
    private String otherRoleName; // 其他玩家角色名
    private int otherCampId; // 其他玩家阵营ID
    private String otherAvatar; // 其他玩家头像
    private int otherGeneralDegree; // 其他玩家级别
    private int otherRoleHonor; // 其他玩家荣誉
    private boolean otherIsOnline; // 其他玩家是否在线
    private List<PvpBattleUnitVO> battleUnitInfo; // 其他玩家作战单位信息
    private String otherBlockHouseNpcInfo; // 其他玩家碉堡信息
    private int rewardHonor; // 胜利荣誉值
    private int failHonor; // 失败荣誉值
    private int rewardRes; // 胜利奖励资源
    private List<BattleUnitVO> unitInfos;
    private int collectTotalSecond;
    private int collectLeftSecond;
    private int canCollectNum;
    private boolean isProtectedByItem; // 资源点是否被保护道具保护
    private int otherServerId;

    public List<BattleUnitVO> getUnitInfos() {
        return unitInfos;
    }

    public void setUnitInfos(List<BattleUnitVO> unitInfos) {
        this.unitInfos = unitInfos;
    }

    public long getResMineInstanceID() {
        return resMineInstanceID;
    }

    public void setResMineInstanceID(long resMineInstanceID) {
        this.resMineInstanceID = resMineInstanceID;
    }

    public int getResMineID() {
        return resMineID;
    }

    public void setResMineID(int resMineID) {
        this.resMineID = resMineID;
    }

    public int getMineBuildingID() {
        return mineBuildingID;
    }

    public void setMineBuildingID(int mineBuildingID) {
        this.mineBuildingID = mineBuildingID;
    }

    public int getMineBuildingLevel() {
        return mineBuildingLevel;
    }

    public void setMineBuildingLevel(int mineBuildingLevel) {
        this.mineBuildingLevel = mineBuildingLevel;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getProduceResType() {
        return produceResType;
    }

    public void setProduceResType(int produceResType) {
        this.produceResType = produceResType;
    }

    public int getProducePerHour() {
        return producePerHour;
    }

    public void setProducePerHour(int producePerHour) {
        this.producePerHour = producePerHour;
    }

    public int getStorageLimit() {
        return storageLimit;
    }

    public void setStorageLimit(int storageLimit) {
        this.storageLimit = storageLimit;
    }

    public int getMineState() {
        return mineState;
    }

    public void setMineState(int mineState) {
        this.mineState = mineState;
    }

    public boolean isUnlock() {
        return isUnlock;
    }

    public void setUnlock(boolean isUnlock) {
        this.isUnlock = isUnlock;
    }

    public String getMineNpcInfo() {
        return mineNpcInfo;
    }

    public void setMineNpcInfo(String mineNpcInfo) {
        this.mineNpcInfo = mineNpcInfo;
    }

    public String getNpcBlockHouseNpcInfo() {
        return npcBlockHouseNpcInfo;
    }

    public void setNpcBlockHouseNpcInfo(String npcBlockHouseNpcInfo) {
        this.npcBlockHouseNpcInfo = npcBlockHouseNpcInfo;
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

    public int getOtherRoleHonor() {
        return otherRoleHonor;
    }

    public void setOtherRoleHonor(int otherRoleHonor) {
        this.otherRoleHonor = otherRoleHonor;
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

    public int getRewardHonor() {
        return rewardHonor;
    }

    public void setRewardHonor(int rewardHonor) {
        this.rewardHonor = rewardHonor;
    }

    public int getFailHonor() {
        return failHonor;
    }

    public void setFailHonor(int failHonor) {
        this.failHonor = failHonor;
    }

    public int getRewardRes() {
        return rewardRes;
    }

    public void setRewardRes(int rewardRes) {
        this.rewardRes = rewardRes;
    }

    public int getCollectTotalSecond() {
        return collectTotalSecond;
    }

    public void setCollectTotalSecond(int collectTotalSecond) {
        this.collectTotalSecond = collectTotalSecond;
    }

    public int getCollectLeftSecond() {
        return collectLeftSecond;
    }

    public void setCollectLeftSecond(int collectLeftSecond) {
        this.collectLeftSecond = collectLeftSecond;
    }

    public int getCanCollectNum() {
        return canCollectNum;
    }

    public void setCanCollectNum(int canCollectNum) {
        this.canCollectNum = canCollectNum;
    }

    public boolean isProtectedByItem() {
        return isProtectedByItem;
    }

    public void setProtectedByItem(boolean isProtectedByItem) {
        this.isProtectedByItem = isProtectedByItem;
    }

    public int getOtherServerId() {
        return otherServerId;
    }

    public void setOtherServerId(int otherServerId) {
        this.otherServerId = otherServerId;
    }


}
