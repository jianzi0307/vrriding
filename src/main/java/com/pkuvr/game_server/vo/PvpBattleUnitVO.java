package com.pkuvr.game_server.vo;

public class PvpBattleUnitVO {
    private int battleUnitID; // 作战单位id
    private int unitLevel; // 等级
    private int unitNum; // 数量
    private int xPosition; // x坐标
    private int zPosition; // z坐标
    private int battleUnitInstanceID;
    private int health;

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

    public int getxPosition() {
        return xPosition;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getzPosition() {
        return zPosition;
    }

    public void setzPosition(int zPosition) {
        this.zPosition = zPosition;
    }

    public int getBattleUnitInstanceID() {
        return battleUnitInstanceID;
    }

    public void setBattleUnitInstanceID(int battleUnitInstanceID) {
        this.battleUnitInstanceID = battleUnitInstanceID;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

}
