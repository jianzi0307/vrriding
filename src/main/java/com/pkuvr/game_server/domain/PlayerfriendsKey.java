package com.pkuvr.game_server.domain;

public class PlayerfriendsKey {

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column PlayerFriends.roleID
     *
     * @mbggenerated Thu May 19 12:37:02 CST 2016
     */
    private Integer roleid;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column PlayerFriends.friendID
     *
     * @mbggenerated Thu May 19 12:37:02 CST 2016
     */
    private Integer friendid;

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column PlayerFriends.roleID
     *
     * @return the value of PlayerFriends.roleID
     * @mbggenerated Thu May 19 12:37:02 CST 2016
     */
    public Integer getRoleid() {
        return roleid;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column PlayerFriends.roleID
     *
     * @param roleid the value for PlayerFriends.roleID
     * @mbggenerated Thu May 19 12:37:02 CST 2016
     */
    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column PlayerFriends.friendID
     *
     * @return the value of PlayerFriends.friendID
     * @mbggenerated Thu May 19 12:37:02 CST 2016
     */
    public Integer getFriendid() {
        return friendid;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column PlayerFriends.friendID
     *
     * @param friendid the value for PlayerFriends.friendID
     * @mbggenerated Thu May 19 12:37:02 CST 2016
     */
    public void setFriendid(Integer friendid) {
        this.friendid = friendid;
    }
}