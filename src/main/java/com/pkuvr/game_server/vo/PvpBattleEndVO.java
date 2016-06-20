package com.pkuvr.game_server.vo;

public class PvpBattleEndVO {
    private int battleResult;
    private int rewardHonor;
    private int honorToken;
    private int tokenLeftTimes;

    public int getBattleResult() {
        return battleResult;
    }

    public void setBattleResult(int battleResult) {
        this.battleResult = battleResult;
    }

    public int getRewardHonor() {
        return rewardHonor;
    }

    public void setRewardHonor(int rewardHonor) {
        this.rewardHonor = rewardHonor;
    }

    public int getHonorToken() {
        return honorToken;
    }

    public void setHonorToken(int honorToken) {
        this.honorToken = honorToken;
    }

    public int getTokenLeftTimes() {
        return tokenLeftTimes;
    }

    public void setTokenLeftTimes(int tokenLeftTimes) {
        this.tokenLeftTimes = tokenLeftTimes;
    }

}
