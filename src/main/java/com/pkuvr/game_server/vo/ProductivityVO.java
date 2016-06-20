package com.pkuvr.game_server.vo;

/**
 * 玩家资源产出效率（小时）
 *
 * @author EvilHades
 */
public class ProductivityVO {

    private int buildingID;
    private int productivity;
    private int functionID;

    public int getBuildingID() {
        return buildingID;
    }

    public void setBuildingID(int buildingID) {
        this.buildingID = buildingID;
    }

    public int getProductivity() {
        return productivity;
    }

    public void setProductivity(int productivity) {
        this.productivity = productivity;
    }

    public int getFunctionID() {
        return functionID;
    }

    public void setFunctionID(int functionID) {
        this.functionID = functionID;
    }

}
