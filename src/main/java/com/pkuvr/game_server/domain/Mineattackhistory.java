package com.pkuvr.game_server.domain;

public class Mineattackhistory extends MineattackhistoryKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MineAttackHistory.attackTimes
     *
     * @mbggenerated Fri Jun 19 17:02:01 CST 2015
     */
    private Short attacktimes;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MineAttackHistory.attackTimes
     *
     * @return the value of MineAttackHistory.attackTimes
     * @mbggenerated Fri Jun 19 17:02:01 CST 2015
     */
    public Short getAttacktimes() {
        return attacktimes;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MineAttackHistory.attackTimes
     *
     * @param attacktimes the value for MineAttackHistory.attackTimes
     * @mbggenerated Fri Jun 19 17:02:01 CST 2015
     */
    public void setAttacktimes(Short attacktimes) {
        this.attacktimes = attacktimes;
    }
}