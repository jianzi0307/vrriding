package com.pkuvr.game_server.vo;

import java.util.List;

public class PvpNpcBattleStartVO {
    private List<PlayerTacticVO> playerTactics; // 其他玩家可使用战术
    private List<BattleUnitVO> playerBattleUnitList; // 其他玩家作战单位列表
    private int playerPower;
    private PvpRankInfoVO otherPlayerRank;
    private int ownRewardHonor;
    private int ownSubHonor;

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

    public int getPlayerPower() {
        return playerPower;
    }

    public void setPlayerPower(int playerPower) {
        this.playerPower = playerPower;
    }

    public PvpRankInfoVO getOtherPlayerRank() {
        return otherPlayerRank;
    }

    public void setOtherPlayerRank(PvpRankInfoVO otherPlayerRank) {
        this.otherPlayerRank = otherPlayerRank;
    }

    public int getOwnRewardHonor() {
        return ownRewardHonor;
    }

    public void setOwnRewardHonor(int ownRewardHonor) {
        this.ownRewardHonor = ownRewardHonor;
    }

    public int getOwnSubHonor() {
        return ownSubHonor;
    }

    public void setOwnSubHonor(int ownSubHonor) {
        this.ownSubHonor = ownSubHonor;
    }

}
