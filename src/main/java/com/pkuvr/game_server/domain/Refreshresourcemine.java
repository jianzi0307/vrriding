package com.pkuvr.game_server.domain;

public class Refreshresourcemine extends RefreshresourcemineKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column RefreshResourceMine.isUnlock
     *
     * @mbggenerated Thu May 19 12:37:02 CST 2016
     */
    private Boolean isunlock;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column RefreshResourceMine.isFirstRefresh
     *
     * @mbggenerated Thu May 19 12:37:02 CST 2016
     */
    private Boolean isfirstrefresh;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column RefreshResourceMine.refreshTime
     *
     * @mbggenerated Thu May 19 12:37:02 CST 2016
     */
    private Long refreshtime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column RefreshResourceMine.isUnlock
     *
     * @return the value of RefreshResourceMine.isUnlock
     * @mbggenerated Thu May 19 12:37:02 CST 2016
     */
    public Boolean getIsunlock() {
        return isunlock;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column RefreshResourceMine.isUnlock
     *
     * @param isunlock the value for RefreshResourceMine.isUnlock
     * @mbggenerated Thu May 19 12:37:02 CST 2016
     */
    public void setIsunlock(Boolean isunlock) {
        this.isunlock = isunlock;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column RefreshResourceMine.isFirstRefresh
     *
     * @return the value of RefreshResourceMine.isFirstRefresh
     * @mbggenerated Thu May 19 12:37:02 CST 2016
     */
    public Boolean getIsfirstrefresh() {
        return isfirstrefresh;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column RefreshResourceMine.isFirstRefresh
     *
     * @param isfirstrefresh the value for RefreshResourceMine.isFirstRefresh
     * @mbggenerated Thu May 19 12:37:02 CST 2016
     */
    public void setIsfirstrefresh(Boolean isfirstrefresh) {
        this.isfirstrefresh = isfirstrefresh;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column RefreshResourceMine.refreshTime
     *
     * @return the value of RefreshResourceMine.refreshTime
     * @mbggenerated Thu May 19 12:37:02 CST 2016
     */
    public Long getRefreshtime() {
        return refreshtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column RefreshResourceMine.refreshTime
     *
     * @param refreshtime the value for RefreshResourceMine.refreshTime
     * @mbggenerated Thu May 19 12:37:02 CST 2016
     */
    public void setRefreshtime(Long refreshtime) {
        this.refreshtime = refreshtime;
    }
}