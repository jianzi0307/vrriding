package com.pkuvr.game_server.vo;

public class BattleUnitStartVO {
    private int battleUnitID; // 作战单位id
    private int unitLevel; // 等级
    private int unitNum; //

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

}
