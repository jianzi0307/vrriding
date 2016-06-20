package com.pkuvr.game_server.vo;

import com.pkuvr.game_server.constant.SmOpCode;
import com.pkuvr.game_server.enumerate.ConsumptionTypeEnum;

public class ChangePlayerWealthVO {
    private int roleId; // 玩家id
    private int diamond; // 钻石数
    private int gold; // 黄金数
    private int iron; // 钢铁
    private int oil; // 石油
    private int stone; // 石材
    private int ton; // 上阵吨位
    private int power; // 行动力
    private SmOpCode reasonCode; // 改变的原因
    private ConsumptionTypeEnum consumptionType = ConsumptionTypeEnum.Unknown; // 消费点枚举
    private String consumptionInfo; // 消费内容(物品id,科技id,联合主键下划线分割)

    /**
     * 构造函数
     *
     * @param roleId          玩家id
     * @param consumptionType 消费点枚举
     * @param consumptionInfo 消费内容(物品id,科技id,联合主键下划线分割)
     */
    public ChangePlayerWealthVO(int roleId, SmOpCode reasonCode,
                                ConsumptionTypeEnum consumptionType, String consumptionInfo) {
        this.roleId = roleId;
        this.reasonCode = reasonCode;
        this.consumptionType = consumptionType;
        this.consumptionInfo = consumptionInfo;
        this.diamond = 0;  // 钻石数
        this.gold = 0;     // 黄金数
        this.iron = 0;     // 钢铁
        this.oil = 0;      // 石油
        this.stone = 0;    // 石材
        this.ton = 0;      // 上阵吨位
        this.power = 0;    // 行动力
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getDiamond() {
        return diamond;
    }

    public void setDiamond(int diamond) {
        this.diamond = diamond;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getIron() {
        return iron;
    }

    public void setIron(int iron) {
        this.iron = iron;
    }

    public int getOil() {
        return oil;
    }

    public void setOil(int oil) {
        this.oil = oil;
    }

    public int getStone() {
        return stone;
    }

    public void setStone(int stone) {
        this.stone = stone;
    }

    public int getTon() {
        return ton;
    }

    public void setTon(int ton) {
        this.ton = ton;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public SmOpCode getReasonCode() {
        return reasonCode;
    }

    public void setReasonCode(SmOpCode reasonCode) {
        this.reasonCode = reasonCode;
    }

    public ConsumptionTypeEnum getConsumptionType() {
        return consumptionType;
    }

    public void setConsumptionType(ConsumptionTypeEnum consumptionType) {
        this.consumptionType = consumptionType;
    }

    public String getConsumptionInfo() {
        return consumptionInfo;
    }

    public void setConsumptionInfo(String consumptionInfo) {
        this.consumptionInfo = consumptionInfo;
    }

}
