package com.pkuvr.game_server.vo;

import java.util.List;

public class BattleUnitVO implements Cloneable {
    private int battleUnitID; //作战单位id
    private int unitLevel; // 等级
    private int unitNum; //
    private int storageNum; //库存数量
    private int campID; // 阵营id
    private int type; // 类别

    private int subType; // 子类型
    private int cannonType; // 炮弹类型
    private int modelAmount; //模型数量
    private int effectID; //特效id
    private int nameInCh; // 中文名
    private int nameInEn; //英文名
    private String model; // 模型

    private String icon; //图标
    private int health; //生命值
    private int attackDamage; //攻击伤害
    private int frontArmor; // 正装甲
    private int sideArmor; // 侧装甲
    private float attackFrequency; //攻击速度
    private float attackRange; // 攻击范围
    private float disAttackRange; //失效攻击范围
    private float sightRange; //视野范围

    private float speedOnGrass; // 草地移动速度
    private float speedOnHill; //山地移动速度
    private float speedOnSand; //沙地移动速度
    private float speedOnSnow; //雪地移动速度
    private float speedOnRoad; //公路移动速度
    private float rotationSpeed; //旋转速度

    private float accurateRate; //精准率
    private float dodgeRate; //躲避率
    private float critRate; //重创率
    private float innerRange; //内杀伤范围
    private float innerDamageRate; //内范围杀伤比例
    private float outerRange; //外杀伤范围
    private float outerDamageRate; //外范围杀伤比例
    private int consumeGold; //消耗黄金
    private int consumeIron; //消耗钢材
    private int consumeOil; //消耗油料
    private int consumeTime; //消耗时间
    private float cancelReturnPercent; // 取消生产返还比
    private int costTroops; //所占兵力
    private int requireBuildingLevel; //需求生产建筑等级

    private String speedForView; //真实速度
    private String enginePowerForView; // 发动机功率
    private String weightForView; //重量
    private int rewardPower; //击杀奖励行动力

    private int updateConsumeGold;
    private int updateConsumeTime;
    private float cancelUpdateReturnPercent;
    private int updateRewardExp;
    private int buildingID;

    private String deathParticle;
    private String deathAction;

    private int fighting;

    private int classLevel;

    private int ownLimitNum;

    private List<SkillVO> skill;

    public List<SkillVO> getSkill() {
        return skill;
    }

    public void setSkill(List<SkillVO> skill) {
        this.skill = skill;
    }

    public int getFighting() {
        return fighting;
    }

    public void setFighting(int fighting) {
        this.fighting = fighting;
    }

    public int getBattleUnitID() {
        return battleUnitID;
    }

    public void setBattleUnitID(int battleUnitID) {
        this.battleUnitID = battleUnitID;
    }

    public int getUnitLevel() {
        return unitLevel;
    }

    public void setUnitLevel(int unitLevel) {
        this.unitLevel = unitLevel;
    }

    public int getUnitNum() {
        return unitNum;
    }

    public void setUnitNum(int unitNum) {
        this.unitNum = unitNum;
    }

    public int getStorageNum() {
        return storageNum;
    }

    public void setStorageNum(int storageNum) {
        this.storageNum = storageNum;
    }

    public int getCampID() {
        return campID;
    }

    public void setCampID(int campID) {
        this.campID = campID;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getSubType() {
        return subType;
    }

    public void setSubType(int subType) {
        this.subType = subType;
    }

    public int getCannonType() {
        return cannonType;
    }

    public void setCannonType(int cannonType) {
        this.cannonType = cannonType;
    }

    public int getModelAmount() {
        return modelAmount;
    }

    public void setModelAmount(int modelAmount) {
        this.modelAmount = modelAmount;
    }

    public int getEffectID() {
        return effectID;
    }

    public void setEffectID(int effectID) {
        this.effectID = effectID;
    }

    public int getNameInCh() {
        return nameInCh;
    }

    public void setNameInCh(int nameInCh) {
        this.nameInCh = nameInCh;
    }

    public int getNameInEn() {
        return nameInEn;
    }

    public void setNameInEn(int nameInEn) {
        this.nameInEn = nameInEn;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public int getFrontArmor() {
        return frontArmor;
    }

    public void setFrontArmor(int frontArmor) {
        this.frontArmor = frontArmor;
    }

    public int getSideArmor() {
        return sideArmor;
    }

    public void setSideArmor(int sideArmor) {
        this.sideArmor = sideArmor;
    }

    public float getAttackFrequency() {
        return attackFrequency;
    }

    public void setAttackFrequency(float attackFrequency) {
        this.attackFrequency = attackFrequency;
    }

    public float getAttackRange() {
        return attackRange;
    }

    public void setAttackRange(float attackRange) {
        this.attackRange = attackRange;
    }

    public float getDisAttackRange() {
        return disAttackRange;
    }

    public void setDisAttackRange(float disAttackRange) {
        this.disAttackRange = disAttackRange;
    }

    public float getSightRange() {
        return sightRange;
    }

    public void setSightRange(float sightRange) {
        this.sightRange = sightRange;
    }

    public float getSpeedOnGrass() {
        return speedOnGrass;
    }

    public void setSpeedOnGrass(float speedOnGrass) {
        this.speedOnGrass = speedOnGrass;
    }

    public float getSpeedOnHill() {
        return speedOnHill;
    }

    public void setSpeedOnHill(float speedOnHill) {
        this.speedOnHill = speedOnHill;
    }

    public float getSpeedOnSand() {
        return speedOnSand;
    }

    public void setSpeedOnSand(float speedOnSand) {
        this.speedOnSand = speedOnSand;
    }

    public float getSpeedOnSnow() {
        return speedOnSnow;
    }

    public void setSpeedOnSnow(float speedOnSnow) {
        this.speedOnSnow = speedOnSnow;
    }

    public float getSpeedOnRoad() {
        return speedOnRoad;
    }

    public void setSpeedOnRoad(float speedOnRoad) {
        this.speedOnRoad = speedOnRoad;
    }

    public float getRotationSpeed() {
        return rotationSpeed;
    }

    public void setRotationSpeed(float rotationSpeed) {
        this.rotationSpeed = rotationSpeed;
    }

    public float getAccurateRate() {
        return accurateRate;
    }

    public void setAccurateRate(float accurateRate) {
        this.accurateRate = accurateRate;
    }

    public float getDodgeRate() {
        return dodgeRate;
    }

    public void setDodgeRate(float dodgeRate) {
        this.dodgeRate = dodgeRate;
    }

    public float getCritRate() {
        return critRate;
    }

    public void setCritRate(float critRate) {
        this.critRate = critRate;
    }

    public float getInnerRange() {
        return innerRange;
    }

    public void setInnerRange(float innerRange) {
        this.innerRange = innerRange;
    }

    public float getInnerDamageRate() {
        return innerDamageRate;
    }

    public void setInnerDamageRate(float innerDamageRate) {
        this.innerDamageRate = innerDamageRate;
    }

    public float getOuterRange() {
        return outerRange;
    }

    public void setOuterRange(float outerRange) {
        this.outerRange = outerRange;
    }

    public float getOuterDamageRate() {
        return outerDamageRate;
    }

    public void setOuterDamageRate(float outerDamageRate) {
        this.outerDamageRate = outerDamageRate;
    }

    public int getConsumeGold() {
        return consumeGold;
    }

    public void setConsumeGold(int consumeGold) {
        this.consumeGold = consumeGold;
    }

    public int getConsumeIron() {
        return consumeIron;
    }

    public void setConsumeIron(int consumeIron) {
        this.consumeIron = consumeIron;
    }

    public int getConsumeOil() {
        return consumeOil;
    }

    public void setConsumeOil(int consumeOil) {
        this.consumeOil = consumeOil;
    }

    public int getConsumeTime() {
        return consumeTime;
    }

    public void setConsumeTime(int consumeTime) {
        this.consumeTime = consumeTime;
    }

    public float getCancelReturnPercent() {
        return cancelReturnPercent;
    }

    public void setCancelReturnPercent(float cancelReturnPercent) {
        this.cancelReturnPercent = cancelReturnPercent;
    }

    public int getCostTroops() {
        return costTroops;
    }

    public void setCostTroops(int costTroops) {
        this.costTroops = costTroops;
    }

    public int getRequireBuildingLevel() {
        return requireBuildingLevel;
    }

    public void setRequireBuildingLevel(int requireBuildingLevel) {
        this.requireBuildingLevel = requireBuildingLevel;
    }

    public String getSpeedForView() {
        return speedForView;
    }

    public void setSpeedForView(String speedForView) {
        this.speedForView = speedForView;
    }

    public String getEnginePowerForView() {
        return enginePowerForView;
    }

    public void setEnginePowerForView(String enginePowerForView) {
        this.enginePowerForView = enginePowerForView;
    }

    public String getWeightForView() {
        return weightForView;
    }

    public void setWeightForView(String weightForView) {
        this.weightForView = weightForView;
    }

    public int getRewardPower() {
        return rewardPower;
    }

    public void setRewardPower(int rewardPower) {
        this.rewardPower = rewardPower;
    }

    public int getUpdateConsumeGold() {
        return updateConsumeGold;
    }

    public void setUpdateConsumeGold(int updateConsumeGold) {
        this.updateConsumeGold = updateConsumeGold;
    }

    public int getUpdateConsumeTime() {
        return updateConsumeTime;
    }

    public void setUpdateConsumeTime(int updateConsumeTime) {
        this.updateConsumeTime = updateConsumeTime;
    }

    public float getCancelUpdateReturnPercent() {
        return cancelUpdateReturnPercent;
    }

    public void setCancelUpdateReturnPercent(float cancelUpdateReturnPercent) {
        this.cancelUpdateReturnPercent = cancelUpdateReturnPercent;
    }

    public int getUpdateRewardExp() {
        return updateRewardExp;
    }

    public void setUpdateRewardExp(int updateRewardExp) {
        this.updateRewardExp = updateRewardExp;
    }

    public int getBuildingID() {
        return buildingID;
    }

    public void setBuildingID(int buildingID) {
        this.buildingID = buildingID;
    }

    public String getDeathParticle() {
        return deathParticle;
    }

    public void setDeathParticle(String deathParticle) {
        this.deathParticle = deathParticle;
    }

    public String getDeathAction() {
        return deathAction;
    }

    public void setDeathAction(String deathAction) {
        this.deathAction = deathAction;
    }


    public int getClassLevel() {
        return classLevel;
    }

    public void setClassLevel(int classLevel) {
        this.classLevel = classLevel;
    }


    public int getOwnLimitNum() {
        return ownLimitNum;
    }

    public void setOwnLimitNum(int ownLimitNum) {
        this.ownLimitNum = ownLimitNum;
    }

    public BattleUnitVO clone() {
        BattleUnitVO o = null;
        try {
            o = (BattleUnitVO) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return o;
    }

}
