package com.pkuvr.game_server.vo;

public class PvpMatchVO {
    private boolean matchResult;
    private PvpRankInfoVO ownPlayerRank;
    private PvpRankInfoVO otherPlayerRank;
    private int ownRewardHonor;
    private int ownSubHonor;
    private int otherRewardHonor;
    private int otherSubHonor;
    private int matchTime;
    private int npcMatchTime;

    public boolean isMatchResult() {
        return matchResult;
    }

    public void setMatchResult(boolean matchResult) {
        this.matchResult = matchResult;
    }

    public PvpRankInfoVO getOwnPlayerRank() {
        return ownPlayerRank;
    }

    public void setOwnPlayerRank(PvpRankInfoVO ownPlayerRank) {
        this.ownPlayerRank = ownPlayerRank;
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

    public int getOtherRewardHonor() {
        return otherRewardHonor;
    }

    public void setOtherRewardHonor(int otherRewardHonor) {
        this.otherRewardHonor = otherRewardHonor;
    }

    public int getOtherSubHonor() {
        return otherSubHonor;
    }

    public void setOtherSubHonor(int otherSubHonor) {
        this.otherSubHonor = otherSubHonor;
    }

    public int getMatchTime() {
        return matchTime;
    }

    public void setMatchTime(int matchTime) {
        this.matchTime = matchTime;
    }

    public int getNpcMatchTime() {
        return npcMatchTime;
    }

    public void setNpcMatchTime(int npcMatchTime) {
        this.npcMatchTime = npcMatchTime;
    }
}
