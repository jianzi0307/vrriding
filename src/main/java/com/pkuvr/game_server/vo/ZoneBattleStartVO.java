package com.pkuvr.game_server.vo;

import java.util.List;

public class ZoneBattleStartVO {
    private int limitTime; // 限定时间
    private int headerquartersLevel; // 指挥部等级
    private int playerId; // 其他玩家ID
    private List<PlayerTacticVO> playerTactics; // 其他玩家可使用战术
    private List<BattleUnitVO> playerBattleUnitList; // 其他玩家作战单位列表
    private List<PvpBattleUnitVO> battleUnitMapInfo; // 玩家作战单位部署信息
    private String blockHouseNPCInfo; // 碉堡NPC信息
    private int ownParaTroopsID; // 自己伞兵作战单位ID
    private float ownParaTroopsLevel; // 自己伞兵作战单位
    private int playerParaTroopsID; // 玩家伞兵作战单位ID
    private float playerParaTroopsLevel; // 玩家伞兵作战单位
    private int commanderInstruction; // 指挥官指令
    private String landMineInfo; // 地雷信息
    private int playerPower;

    public int getLimitTime() {
        return limitTime;
    }

    public void setLimitTime(int limitTime) {
        this.limitTime = limitTime;
    }

    public int getHeaderquartersLevel() {
        return headerquartersLevel;
    }

    public void setHeaderquartersLevel(int headerquartersLevel) {
        this.headerquartersLevel = headerquartersLevel;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public List<PlayerTacticVO> getPlayerTactics() {
        return playerTactics;
    }

    public void setPlayerTactics(List<PlayerTacticVO> playerTactics) {
        this.playerTactics = playerTactics;
    }

    public List<BattleUnitVO> getPlayerBattleUnitList() {
        return playerBattleUnitList;
    }

    public void setPlayerBattleUnitList(List<BattleUnitVO> playerBattleUnitList) {
        this.playerBattleUnitList = playerBattleUnitList;
    }

    public List<PvpBattleUnitVO> getBattleUnitMapInfo() {
        return battleUnitMapInfo;
    }

    public void setBattleUnitMapInfo(List<PvpBattleUnitVO> battleUnitMapInfo) {
        this.battleUnitMapInfo = battleUnitMapInfo;
    }

    public String getBlockHouseNPCInfo() {
        return blockHouseNPCInfo;
    }

    public void setBlockHouseNPCInfo(String blockHouseNPCInfo) {
        this.blockHouseNPCInfo = blockHouseNPCInfo;
    }

    public int getOwnParaTroopsID() {
        return ownParaTroopsID;
    }

    public void setOwnParaTroopsID(int ownParaTroopsID) {
        this.ownParaTroopsID = ownParaTroopsID;
    }

    public float getOwnParaTroopsLevel() {
        return ownParaTroopsLevel;
    }

    public void setOwnParaTroopsLevel(float ownParaTroopsLevel) {
        this.ownParaTroopsLevel = ownParaTroopsLevel;
    }

    public int getPlayerParaTroopsID() {
        return playerParaTroopsID;
    }

    public void setPlayerParaTroopsID(int playerParaTroopsID) {
        this.playerParaTroopsID = playerParaTroopsID;
    }

    public float getPlayerParaTroopsLevel() {
        return playerParaTroopsLevel;
    }

    public void setPlayerParaTroopsLevel(float playerParaTroopsLevel) {
        this.playerParaTroopsLevel = playerParaTroopsLevel;
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

    public int getPlayerPower() {
        return playerPower;
    }

    public void setPlayerPower(int playerPower) {
        this.playerPower = playerPower;
    }
}
