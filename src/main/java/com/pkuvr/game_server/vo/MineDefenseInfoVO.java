package com.pkuvr.game_server.vo;

import java.util.List;

public class MineDefenseInfoVO {
    private long resMineInstanceID; // 资源点ID
    private int mineBuildingID; // 资源点建筑ID
    private int mineBuildingLevel; // 资源点建筑等级
    private List<PvpBattleUnitVO> battleUnitInfo; // 玩家作战单位信息
    private String mineInfo; // 资源岛信息
    private String blockHouseNPCInfo; // 碉堡NPC信息
    private int commanderInstruction; // 指挥官指令
    private String landMineInfo; // 地雷信息
    private List<BattleUnitVO> units;

    public List<BattleUnitVO> getUnits() {
        return units;
    }

    public void setUnits(List<BattleUnitVO> units) {
        this.units = units;
    }

    public long getResMineInstanceID() {
        return resMineInstanceID;
    }

    public void setResMineInstanceID(long resMineInstanceID) {
        this.resMineInstanceID = resMineInstanceID;
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

    public List<PvpBattleUnitVO> getBattleUnitInfo() {
        return battleUnitInfo;
    }

    public void setBattleUnitInfo(List<PvpBattleUnitVO> battleUnitInfo) {
        this.battleUnitInfo = battleUnitInfo;
    }

    public String getBlockHouseNPCInfo() {
        return blockHouseNPCInfo;
    }

    public void setBlockHouseNPCInfo(String blockHouseNPCInfo) {
        this.blockHouseNPCInfo = blockHouseNPCInfo;
    }

    public String getMineInfo() {
        return mineInfo;
    }

    public void setMineInfo(String mineInfo) {
        this.mineInfo = mineInfo;
    }

    public int getCommanderInstruction() {
        return commanderInstruction;
    }

    public void setCommanderInstruction(int commanderInstruction) {
        this.commanderInstruction = commanderInstruction;
    }

    public String getLandMineInfo() {
        return landMineInfo;
    }

    public void setLandMineInfo(String landMineInfo) {
        this.landMineInfo = landMineInfo;
    }

}
