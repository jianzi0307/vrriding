package com.pkuvr.game_server.vo;

import java.util.List;

public class ZoneBattleInfoVO {
    private long battleLogId;                                  // 战斗日志id
    private int battleResult;                                  // 攻击结果
    private long battleTime;                                   // 攻击时间
    private List<BattleUnitStartVO> attackerBattleUnit;        // 攻击方投入兵力
    private List<BattleUnitStartVO> damageBattleUnit;          // 攻击方损失兵力
    private int resMineID;                                     // 资源点ID
    private int attackerRoleId;                                // 攻击方角色ID
    private String attackerRoleName;                           // 攻击方角色名
    private int attackerCampId;                                // 攻击方阵营ID
    private String attackerAvatar;                             // 攻击方头像
    private int attackerGeneralDegree;                         // 攻击方级别
    private int attackerRoleGrade;                               // 攻击方战斗力
    private int rewardGold;                                       // 黄金
    private int rewardOil;                                       // 石油
    private int rewardStone;                                   // 石头
    private int rewardIron;                                       // 钢材
    private boolean isRevanche;                                // 是否复仇
    private boolean isOnline;                                  // 是否在线
    private boolean isRevancheLog;                             // 是否复仇成功

    public long getBattleLogId() {
        return battleLogId;
    }

    public void setBattleLogId(long battleLogId) {
        this.battleLogId = battleLogId;
    }

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

    public int getAttackerRoleGrade() {
        return attackerRoleGrade;
    }

    public void setAttackerRoleGrade(int attackerRoleGrade) {
        this.attackerRoleGrade = attackerRoleGrade;
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

    public boolean isRevanche() {
        return isRevanche;
    }

    public void setRevanche(boolean isRevanche) {
        this.isRevanche = isRevanche;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean isOnline) {
        this.isOnline = isOnline;
    }

    public boolean isRevancheLog() {
        return isRevancheLog;
    }

    public void setRevancheLog(boolean isRevancheLog) {
        this.isRevancheLog = isRevancheLog;
    }
}
