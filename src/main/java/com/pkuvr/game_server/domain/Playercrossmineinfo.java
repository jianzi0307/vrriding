package com.pkuvr.game_server.domain;

public class Playercrossmineinfo {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PlayerCrossMineInfo.roleID
     *
     * @mbggenerated Mon May 30 15:00:44 CST 2016
     */
    private Integer roleid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PlayerCrossMineInfo.mineProductivityList
     *
     * @mbggenerated Mon May 30 15:00:44 CST 2016
     */
    private String mineproductivitylist;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PlayerCrossMineInfo.roleID
     *
     * @return the value of PlayerCrossMineInfo.roleID
     * @mbggenerated Mon May 30 15:00:44 CST 2016
     */
    public Integer getRoleid() {
        return roleid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PlayerCrossMineInfo.roleID
     *
     * @param roleid the value for PlayerCrossMineInfo.roleID
     * @mbggenerated Mon May 30 15:00:44 CST 2016
     */
    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PlayerCrossMineInfo.mineProductivityList
     *
     * @return the value of PlayerCrossMineInfo.mineProductivityList
     * @mbggenerated Mon May 30 15:00:44 CST 2016
     */
    public String getMineproductivitylist() {
        return mineproductivitylist;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PlayerCrossMineInfo.mineProductivityList
     *
     * @param mineproductivitylist the value for PlayerCrossMineInfo.mineProductivityList
     * @mbggenerated Mon May 30 15:00:44 CST 2016
     */
    public void setMineproductivitylist(String mineproductivitylist) {
        this.mineproductivitylist = mineproductivitylist;
    }
}