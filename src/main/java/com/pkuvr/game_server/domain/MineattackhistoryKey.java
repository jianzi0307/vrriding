package com.pkuvr.game_server.domain;

public class MineattackhistoryKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MineAttackHistory.NpcResMineID
     *
     * @mbggenerated Fri Jun 19 17:02:01 CST 2015
     */
    private Long npcresmineid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MineAttackHistory.attackerRoleID
     *
     * @mbggenerated Fri Jun 19 17:02:01 CST 2015
     */
    private Integer attackerroleid;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MineAttackHistory.NpcResMineID
     *
     * @return the value of MineAttackHistory.NpcResMineID
     * @mbggenerated Fri Jun 19 17:02:01 CST 2015
     */
    public Long getNpcresmineid() {
        return npcresmineid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MineAttackHistory.NpcResMineID
     *
     * @param npcresmineid the value for MineAttackHistory.NpcResMineID
     * @mbggenerated Fri Jun 19 17:02:01 CST 2015
     */
    public void setNpcresmineid(Long npcresmineid) {
        this.npcresmineid = npcresmineid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MineAttackHistory.attackerRoleID
     *
     * @return the value of MineAttackHistory.attackerRoleID
     * @mbggenerated Fri Jun 19 17:02:01 CST 2015
     */
    public Integer getAttackerroleid() {
        return attackerroleid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MineAttackHistory.attackerRoleID
     *
     * @param attackerroleid the value for MineAttackHistory.attackerRoleID
     * @mbggenerated Fri Jun 19 17:02:01 CST 2015
     */
    public void setAttackerroleid(Integer attackerroleid) {
        this.attackerroleid = attackerroleid;
    }
}