package com.pkuvr.game_server.vo;

public class PvpRankInfoVO {
    private int pvpRank; // pvp排名
    private int roleId; // 玩家角色ID
    private String roleName; // 玩家角色名
    private int campId; // 玩家阵营ID
    private String avatar; // 玩家头像
    private int generalDegree; // 玩家级别
    private int honor; // 荣誉值

    public int getPvpRank() {
        return pvpRank;
    }

    public void setPvpRank(int pvpRank) {
        this.pvpRank = pvpRank;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getCampId() {
        return campId;
    }

    public void setCampId(int campId) {
        this.campId = campId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getGeneralDegree() {
        return generalDegree;
    }

    public void setGeneralDegree(int generalDegree) {
        this.generalDegree = generalDegree;
    }

    public int getHonor() {
        return honor;
    }

    public void setHonor(int honor) {
        this.honor = honor;
    }

}
