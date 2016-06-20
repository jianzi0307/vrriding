package com.pkuvr.game_server.vo;

public class MineHarvestResourceVO {
    private int produceResType;
    private int produceNum;
    private boolean isMineCarDrive;
    private int mineResourceNum;
    private int produceMuduls;
    private int producePerHour;

    public int getProduceResType() {
        return produceResType;
    }

    public void setProduceResType(int produceResType) {
        this.produceResType = produceResType;
    }

    public int getProduceNum() {
        return produceNum;
    }

    public void setProduceNum(int produceNum) {
        this.produceNum = produceNum;
    }

    public boolean isMineCarDrive() {
        return isMineCarDrive;
    }

    public void setMineCarDrive(boolean isMineCarDrive) {
        this.isMineCarDrive = isMineCarDrive;
    }

    public int getMineResourceNum() {
        return mineResourceNum;
    }

    public void setMineResourceNum(int mineResourceNum) {
        this.mineResourceNum = mineResourceNum;
    }

    public int getProduceMuduls() {
        return produceMuduls;
    }

    public void setProduceMuduls(int produceMuduls) {
        this.produceMuduls = produceMuduls;
    }

    public int getProducePerHour() {
        return producePerHour;
    }

    public void setProducePerHour(int producePerHour) {
        this.producePerHour = producePerHour;
    }

}
