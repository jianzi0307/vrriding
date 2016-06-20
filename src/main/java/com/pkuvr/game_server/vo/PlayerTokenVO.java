package com.pkuvr.game_server.vo;

public class PlayerTokenVO {
    private boolean isAddToken;
    private int leftTimes;

    public boolean isAddToken() {
        return isAddToken;
    }

    public void setAddToken(boolean isAddToken) {
        this.isAddToken = isAddToken;
    }

    public int getLeftTimes() {
        return leftTimes;
    }

    public void setLeftTimes(int leftTimes) {
        this.leftTimes = leftTimes;
    }
}
