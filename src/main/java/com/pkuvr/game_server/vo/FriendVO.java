package com.pkuvr.game_server.vo;

public class FriendVO {
    private int roleId; // 玩家角色ID
    private String roleName; // 角色名
    private int campId; // 阵营ID
    private String avatar; // 头像
    private int generalDegree; // 玩家级别
    private int isOnline;
    private String lastLogoutTime;
    private int vipLevel;

    public int getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(int vipLevel) {
        this.vipLevel = vipLevel;
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

    public int getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(int isOnline) {
        this.isOnline = isOnline;
    }

    public String getLastLogoutTime() {
        return lastLogoutTime;
    }

    public void setLastLogoutTime(String lastLogoutTime) {
        this.lastLogoutTime = lastLogoutTime;
    }

}
