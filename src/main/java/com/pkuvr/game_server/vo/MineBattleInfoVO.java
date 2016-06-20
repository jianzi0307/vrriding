package com.pkuvr.game_server.vo;

import java.util.List;

public class MineBattleInfoVO {
    private int battleResult; // 攻击结果
    private long battleTime; // 攻击时间
    private List<BattleUnitStartVO> attackerBattleUnit; // 攻击方投入兵力
    private List<BattleUnitStartVO> damageBattleUnit; // 攻击方损失兵力
    private long resMineInstanceID; // 资源点ID
    private int resMineID; // 资源点模板ID
    private int attackerRoleId; // 攻击方角色ID
    private String attackerRoleName; // 攻击方角色名
    private int attackerCampId; // 攻击方阵营ID
    private String attackerAvatar; // 攻击方头像
    private int attackerGeneralDegree; // 攻击方级别
    private int attackerRoleHonor; // 攻击方荣誉值
    private int attackerRoleGrade; // 攻击方战力值
    private int rewardHonor; // 荣誉值
    private int produceResType; // 资源类型
    private int rewardResource; // 获取资源量

    public int getBattleResult() {
        return battleResult;
    }

    public void setBattleResult(int battleResult) {
        this.battleResult = battleResult;
    }

    public long getBattleTime() {
        return battleTime;
    }

    public void setBattleTime(long battleTime) {
        this.battleTime = battleTime;
    }

    public List<BattleUnitStartVO> getAttackerBattleUnit() {
        return attackerBattleUnit;
    }

    public void setAttackerBattleUnit(List<BattleUnitStartVO> attackerBattleUnit) {
        this.attackerBattleUnit = attackerBattleUnit;
    }

    public List<BattleUnitStartVO> getDamageBattleUnit() {
        return damageBattleUnit;
    }

    public void setDamageBattleUnit(List<BattleUnitStartVO> damageBattleUnit) {
        this.damageBattleUnit = damageBattleUnit;
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

    public int getAttackerCampId() {
        return attackerCampId;
    }

    public void setAttackerCampId(int attackerCampId) {
        this.attackerCampId = attackerCampId;
    }

    public String getAttackerAvatar() {
        return attackerAvatar;
    }

    public void setAttackerAvatar(String attackerAvatar) {
        this.attackerAvatar = attackerAvatar;
    }

    public int getAttackerGeneralDegree() {
        return attackerGeneralDegree;
    }

    public void setAttackerGeneralDegree(int attackerGeneralDegree) {
        this.attackerGeneralDegree = attackerGeneralDegree;
    }

    public int getAttackerRoleHonor() {
        return attackerRoleHonor;
    }

    public void setAttackerRoleHonor(int attackerRoleHonor) {
        this.attackerRoleHonor = attackerRoleHonor;
    }

    public int getAttackerRoleGrade() {
        return attackerRoleGrade;
    }

    public void setAttackerRoleGrade(int attackerRoleGrade) {
        this.attackerRoleGrade = attackerRoleGrade;
    }

    public int getRewardHonor() {
        return rewardHonor;
    }

    public void setRewardHonor(int rewardHonor) {
        this.rewardHonor = rewardHonor;
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

}
