package com.pkuvr.game_server.constant;

public enum SeaErrorCode {
    OK(10000, "", true),
    ERROR_UNKNOW(10001, "未知错误", true),
    ERROR_PARAM(10002, "参数错误", true),
    LOGIN_FAULT(10003, "登录失败", true),
    NO_SUCH_ROLE(10004, "玩家帐号不存在", true),
    ROLE_ALREADY_EXISTS(10005, "玩家帐号已经存在", true),

    PVP_MATCH_FAILED(19101, "匹配对手失败，请重新匹配 ", false),
    PVP_DEPLOY_FAILED(19102, "对手还在布阵，请稍后 ", false),
    PVP_OTHER_NOT_ONLINE(19103, "对手已经断线  ", false),
    PVP_TACTICS_NOT_EXIST(19104, "玩家未掌握该战术 ", false),
    PVP_MATCH_OVER_TIMES(19105, "该对手今日匹配次数用完 ", false),
    PVP_NPC_MATCH_OVER_TIMES(19106, "每日匹配NPC次数用完 ", false),

    MINE_NOT_EXIST(19001, "该资源矿点不存在 ", true),
    MINE_INFO_ERROR(19002, "资源矿点矿场信息错误 ", true),
    MINE_BLOCK_HOUSE_ERROR(19003, "资源矿点碉堡信息错误 ", true),
    MINE_OWNER_ONLINE(19004, "目前敌方指挥官在线，敌军警觉请稍后再试 ", false),
    MINE_NOT_PLAYER(19005, "该资源矿点不是玩家占领 ", true),
    MINE_UNDER_ATTACKED(19006, "您的资源矿正在被攻击，请稍后再试  ", false),
    MINE_INSTANCE_UNDER_ATTACKED(19007, "该资源矿点正在被其他人攻击，请稍后再试  ", false),
    MINE_ALREADY_UNLOCKED(19008, "该资源矿点已经解锁  ", false),
    MINE_CANNOT_CHANGE_PLAYER(19009, "该资源矿点距离上次更换玩家操作还未满12小时，请稍后再试  ", false),
    MINE_CHANGE_PLAYER_NOT_FOUND(19010, "该资源矿点更换玩家未找到战力值相匹配的玩家，请稍后再试  ", false),
    MINE_PROTECTED(19011, "该玩家在保护中", false),
    ZONE_NOT_EXIST(19012, "该玩家防区不存在 ", false),
    ZONE_OWNER_ONLINE(19013, "目前敌方指挥官在线，敌军警觉请稍后再试 ", false),
    ZONE_UNDER_PROTECTED(19014, "敌人在保护时间内，不能发动进攻 ", false),
    ZONE_IS_ALREADY_UNLOCKED(19015, "该玩家防区已经解锁 ", false),
    ZONE_CANNOT_CHANGE_PLAYER(19016, "该防区刷新时间未到，不能更换对手", false),
    ZONE_PLUNDERED_CANNOT_REFRESH(19017, "该防区离被攻击时间未满2.4小时，不能刷新", false),
    ZONE_UNDER_ATTACKTED(19018, "该玩家防区正处于其他玩家攻击当中，暂时不能发动进攻，请稍后再试  ", false),
    ZONE_CANNOT_REVANCHE(19019, "您不满足复仇条件，不能进行复仇", false),
    ZONE_UNDER_PROTECTED_BY_ITEM(19020, "对方处于保护时间内，不能发动进攻 ", false),

    MINE_ALREADY_IN_COLLECTED(19021, "您已经有资源点正在采集中", false),
    MINE_CAN_NOT_COLLECT(19022, "该资源点不属于您，不能进行采集操作", false),
    MINE_COLLECT_TOO_MANY(19023, "您每日最多只能进行3次采集操作", false),

    NOT_ENOUGH_WEALTH(20001, "钻石、黄金、油料、钢铁等 其中之一不足 ", false),
    NOT_ENOUGH_DIAMOND(20002, "钻石数量不足 ", false),
    NOT_ENOUGH_GOLD(20003, "黄金数量不足 ", false),
    NOT_ENOUGH_OIL(20004, "油料数量不足 ", false),
    NOT_ENOUGH_IRON(20005, "钢铁数量不足 ", false),
    NOT_ENOUGH_STONE(20006, "石材数量不足 ", false),
    NOT_ENOUGH_RESOURCE(20007, "资源数量不足", false),

    BLACKLIST_ALREADY_EXISTS(10053, "黑名单已经添加过", false),
    BLACKLIST_NOT_EXISTS(10054, "该用户已经不在黑名单，不能进行删除操作", false),
    FRIEND_APPLY_ALREADY_EXISTS(10060, "加好友申请已经添加过", false),
    FRIEND_APPLY_OP_FAIL(10061, "好友申请操作失败", false),
    OWN_FRIEND_APPLY_OP_FAIL(10062, "自身提交好友申请操作失败", false),;

    private int errorCode;
    private String desc;
    private boolean isWriteLog = true; // 如果日志等级为error级,则 true 写入日志中;如果日志等级为 info ,则无论真假都写入日志中

    private SeaErrorCode(int errorCode, String desc, boolean isWriteLog) {
        this.errorCode = errorCode;
        this.desc = desc;
        this.isWriteLog = isWriteLog;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getDesc() {
        return desc;
    }

    public boolean isWriteLog() {
        return isWriteLog;
    }

    @Override
    public String toString() {
        return Integer.toString(errorCode) + " " + desc;
    }
}
