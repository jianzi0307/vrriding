package com.pkuvr.game_server.vo;

public class MineCollectLogVO {
    private int roleID; // 玩家ID
    private long resMineInstanceID; // 资源点ID
    private int collectResult; // 收集结果
    private long collectEndTime; // 收集结束时间
    private int produceResType; // 资源类型
    private int rewardResource; // 获取资源量
    private int attackerRoleId; // 攻击方角色ID
    private String attackerRoleName; // 攻击方角色名

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public long getResMineInstanceID() {
        return resMineInstanceID;
    }

    public void setResMineInstanceID(long resMineInstanceID) {
        this.resMineInstanceID = resMineInstanceID;
    }

    public int getCollectResult() {
        return collectResult;
    }

    public void setCollectResult(int collectResult) {
        this.collectResult = collectResult;
    }

    public long getCollectEndTime() {
        return collectEndTime;
    }

    public void setCollectEndTime(long collectEndTime) {
        this.collectEndTime = collectEndTime;
    }

    public int getProduceResType() {
        return produceResType;
    }

    public void setProduceResType(int produceResType) {
        this.produceResType = produceResType;
    }

    public int getRewardResource() {
        return rewardResource;
    }

    public void setRewardResource(int rewardResource) {
        this.rewardResource = rewardResource;
    }

    public int getAttackerRoleId() {
        return attackerRoleId;
    }

    public void setAttackerRoleId(int attackerRoleId) {
        this.attackerRoleId = attackerRoleId;
    }

    public String getAttackerRoleName() {
        return attackerRoleName;
    }

    public void setAttackerRoleName(String attackerRoleName) {
        this.attackerRoleName = attackerRoleName;
    }

}
