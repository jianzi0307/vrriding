package com.pkuvr.game_server.vo;

import java.util.List;

public class MineBattleStartVO {
    private int limitTime; // 限定时间
    private boolean isPlayer;
    private int playerId; // 其他玩家ID
    private List<PlayerTacticVO> playerTactics; // 其他玩家可使用战术
    private List<BattleUnitVO> playerBattleUnitList; // 其他玩家作战单位列表
    private List<PvpBattleUnitVO> battleUnitMapInfo; // 玩家作战单位部署信息
    private String mineInfo; // 资源岛信息
    private String mineNPCInfo; // 资源岛NPC信息
    private String npcTactics; // npc可使用战术
    private String blockHouseNPCInfo; // 碉堡NPC信息
    private int ownParaTroopsID; // 自己伞兵作战单位ID
    private float ownParaTroopsLevel; // 自己伞兵作战单位
    private int playerParaTroopsID; // 玩家伞兵作战单位ID
    private float playerParaTroopsLevel; // 玩家伞兵作战单位
    private int npcParaTroopsID; // npc伞兵作战单位ID
    private float npcParaTroopsLevel; // npc伞兵作战单位

    private int commanderInstruction; // 指挥官指令
    private String landMineInfo; // 地雷信息
    private int changeAI; // npc changeAI
    private int changeAITime; // npc changeAITime
    private int playerPower;

    public int getLimitTime() {
        return limitTime;
    }

    public void setLimitTime(int limitTime) {
        this.limitTime = limitTime;
    }

    public boolean isPlayer() {
        return isPlayer;
    }

    public void setPlayer(boolean isPlayer) {
        this.isPlayer = isPlayer;
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

    public String getMineInfo() {
        return mineInfo;
    }

    public void setMineInfo(String mineInfo) {
        this.mineInfo = mineInfo;
    }

    public String getMineNPCInfo() {
        return mineNPCInfo;
    }

    public void setMineNPCInfo(String mineNPCInfo) {
        this.mineNPCInfo = mineNPCInfo;
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

    public int getNpcParaTroopsID() {
        return npcParaTroopsID;
    }

    public void setNpcParaTroopsID(int npcParaTroopsID) {
        this.npcParaTroopsID = npcParaTroopsID;
    }

    public float getNpcParaTroopsLevel() {
        return npcParaTroopsLevel;
    }

    public void setNpcParaTroopsLevel(float npcParaTroopsLevel) {
        this.npcParaTroopsLevel = npcParaTroopsLevel;
    }

    public String getNpcTactics() {
        return npcTactics;
    }

    public void setNpcTactics(String npcTactics) {
        this.npcTactics = npcTactics;
    }

    public List<PvpBattleUnitVO> getBattleUnitMapInfo() {
        return battleUnitMapInfo;
    }

    public void setBattleUnitMapInfo(List<PvpBattleUnitVO> battleUnitMapInfo) {
        this.battleUnitMapInfo = battleUnitMapInfo;
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

    public int getChangeAI() {
        return changeAI;
    }

    public void setChangeAI(int changeAI) {
        this.changeAI = changeAI;
    }

    public int getChangeAITime() {
        return changeAITime;
    }

    public void setChangeAITime(int changeAITime) {
        this.changeAITime = changeAITime;
    }

    public int getPlayerPower() {
        return playerPower;
    }

    public void setPlayerPower(int playerPower) {
        this.playerPower = playerPower;
    }
}
