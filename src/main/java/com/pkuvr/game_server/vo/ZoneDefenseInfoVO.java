package com.pkuvr.game_server.vo;

import java.util.List;

public class ZoneDefenseInfoVO {

    private int headerquartersLevel; // 指挥部等级
    private List<PvpBattleUnitVO> battleUnitInfo; // 玩家作战单位信息
    private String blockHouseNPCInfo; // 碉堡NPC信息
    private int commanderInstruction; // 指挥官指令
    private String landMineInfo; // 地雷信息
    private List<BattleUnitVO> units;
    private boolean isProtected;

    public int getHeaderquartersLevel() {
        return headerquartersLevel;
    }

    public void setHeaderquartersLevel(int headerquartersLevel) {
        this.headerquartersLevel = headerquartersLevel;
    }

    public List<BattleUnitVO> getUnits() {
        return units;
    }

    public void setUnits(List<BattleUnitVO> units) {
        this.units = units;
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

    public boolean isProtected() {
        return isProtected;
    }

    public void setProtected(boolean isProtected) {
        this.isProtected = isProtected;
    }
}
