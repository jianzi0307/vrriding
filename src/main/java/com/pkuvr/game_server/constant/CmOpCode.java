package com.pkuvr.game_server.constant;


/**
 * <p>类说明:</p>
 * <p>文件名： OperatorCodeConfig.java</p>
 * <p>创建人及时间：	宋士龙 2012-6-12</p>
 * <p>
 * <p>修改人：</p>
 * <p>修改时间：</p>
 * <p>修改描述：</p>
 **/
public enum CmOpCode {

    // 0x1e,登陆及创建用户相关
    ENTER_CROSS_SERVER_REQ(0x1e01, "登录跨服服务器"),
    RECONNECTION_REQ(0x1e06, "断线重登陆游戏"),
    HEARTBEAT_REQ(0x1e07, "心跳"),
    CR_ROLE_INFO_SYNC_REQ(0x1e08, "跨服同步用户信息"),
    //CR_MINE_INFO_SYNC_REQ( 0x1e09, "跨服同步资源点信息" ),

    // 0x2c,实时PVP系统
    PVP_ROOM_REQ(0x2c01, "pvp房间"),
    PVP_MATCH_REQ(0x2c02, "pvp匹配对手"),
    PVP_BATTLE_UNIT_DEPLOY_REQ(0x2c04, "pvp布阵"),
    PVP_SYNC_ATTACK_REQ(0x2c06, "同步攻击"),
    PVP_SYNC_DEAD_REQ(0x2c07, "同步死亡"),
    PVP_SYNC_TACTICS_REQ(0x2c08, "同步战术"),
    PVP_SYNC_COMMON_REQ(0x2c0b, "同步通用信息"),
    PVP_BATTLE_END_REQ(0x2c10, "pvp战斗结束"),
    PVP_SYNC_COMMAND_REQ(0x2c13, "同步战术指令"),
    PVP_PLAYER_EXIT_REQ(0x2c15, "pvp玩家退出"),
    PVP_NPC_BATTLE_MATCH_REQ(0x2c0a, "pvp npc匹配"),
    PVP_NPC_BATTLE_START_REQ(0x2c18, "pvp npc战斗开始"),
    PVP_NPC_BATTLE_END_REQ(0x2c19, "pvp npc战斗结束"),
    PVP_FRIEND_REQ(0x2c20, "好友切磋请求"),
    PVP_FRIEND_CONFIRM_REQ(0x2c22, "好友切磋请求确认"),
    PVP_TOKEN_INFO_REQ(0x2c23, "获取玩家pvp代币获取次数"),


    // 0x22,资源点PVP系统
    MINE_ENTER_REQ(0x2200, "进入资源点"),
    MINE_LIST_REQ(0x2201, "获取资源点列表"),
    MINE_DEFEND_INFO_REQ(0x2202, "获取资源点防御信息"),
    MINE_DEFEND_DEPLOY_REQ(0x2203, "资源点布防"),
    MINE_BATTLE_START_REQ(0x2204, "资源点战斗开始"),
    MINE_BATTLE_END_REQ(0x2205, "资源点战斗结束"),
    MINE_BATTLE_LOG_REQ(0x2206, "资源点战斗Log获取"),
    MINE_CHANGE_PLAYER_REQ(0x2207, "更换资源点对手"),
    MINE_INFO_REQ(0x2208, "获取资源点信息"),
    MINE_HARVEST_QUERY_REQ(0x2209, "资源矿点收集资源查询"),
    MINE_HARVEST_RESOURCE_REQ(0x2210, "资源矿点收集资源"),
    MINE_UNLOCK_REFRESH_REQ(0x2211, "解锁刷新资源"),

    MINE_COLLECT_START_REQ(0x2222, "资源点收集开始"),
    MINE_COLLECT_LOG_REQ(0x2223, "资源点收集Log获取"),
    MINE_PROTECT_TIME_REQ(0x2224, "资源点保护剩余时间获取"),

    ZONE_DEFEND_INFO_REQ(0x2212, "获取玩家防区防御信息"),
    ZONE_DEFEND_DEPLOY_REQ(0x2213, "玩家防区布防"),
    ZONE_BATTLE_START_REQ(0x2214, "玩家防区战斗开始"),
    ZONE_BATTLE_END_REQ(0x2215, "玩家防区战斗结束"),
    ZONE_BATTLE_LOG_REQ(0x2216, "玩家防区战斗Log获取"),
    ZONE_CHANGE_PLAYER_REQ(0x2217, "更换玩家防区对手"),
    ZONE_INFO_REQ(0x2218, "获取玩家防区信息"),
    ZONE_UNLOCK_REFRESH_REQ(0x2219, "解锁刷新玩家防区"),


    // 0x30,聊天系统
    WORLD_CHAT_REQ(0x3001, "世界聊天"),
    FRIEND_CHAT_REQ(0x3002, "私聊"),
    FACTION_CHAT_REQ(0x3005, "军团聊天"),
    CHAT_LIST_REQ(0x3007, "获取历史聊天信息"),

    // 0x31,好友系统
    ADD_FRIEND_REQ(0x3101, "增加好友"),
    DEIETE_FRIEND_REQ(0x3102, "删除好友"),
    FRIEND_LIST_REQ(0x3103, "获取好友列表"),
    FIND_FRIEND_REQ(0x3104, "查找好友"),
    FRIEND_INVITE_SEND(0X3105, "被添加好友提示"),
    ADD_BLACKLIST_REQ(0x3106, "增加黑名单"),
    DEIETE_BLACKLIST_REQ(0x3107, "删除黑名单"),
    BLACK_LIST_REQ(0x3108, "获取黑名单列表"),

    CR_ADD_FRIEND_APPLY_REQ(0x3109, "增加好友申请"),
    CR_DEIETE_FRIEND_REQ(0x3110, "删除好友"),
    CR_FRIEND_LIST_REQ(0x3111, "获取好友列表"),
    CR_FIND_FRIEND_REQ(0x3112, "查找好友"),
    CR_ADD_BLACKLIST_REQ(0x3113, "增加黑名单"),
    CR_DEIETE_BLACKLIST_REQ(0x3114, "删除黑名单"),
    CR_BLACK_LIST_REQ(0x3115, "获取黑名单列表"),
    CR_FRIEND_APPLY_LIST_REQ(0x3116, "获取加好友申请列表"),
    CR_FRIEND_APPLY_OP_REQ(0x3117, "好友申请列表操作"),
    CR_OWN_FRIEND_APPLY_LIST_REQ(0x3118, "获取个人提交的好友申请列表"),
    CR_CANCEL_OWN_FRIEND_APPLY_REQ(0x3119, "删除个人提交的好友申请"),

    // 0x32  邮件系统
    MAIL_GET_CONTENT_REQ(0x3201, "获取邮件内容"),
    MAIL_GET_LIST_REQ(0x3202, "获取邮件列表"),
    MAIL_RECEIVE_GIFT_REQ(0x3203, "获取邮件礼包"),
    MAIL_WRITE_CONTENT_REQ(0x3204, "玩家写邮件内容"),

    // 0x37 排行榜系统
    RANKING_LEVEL_REQ(0x3701, "等级排行榜"),
    RANKING_RANK_REQ(0x3702, "军衔排行榜"),
    RANKING_SEMIAUTOMATIC_REQ(0x3703, "王牌指挥排行榜"),
    RANKING_COPY_REQ(0x3704, "副本排名"),
    RANKING_REWARD_REQ(0x3705, "领取排行榜奖励"),
    RANKING_REWARD_TYPE_REQ(0x3706, "排行榜奖励状态"),
    RANKING_HONNOR_REQ(0x3707, "荣誉排行榜"),
    RANKING_COPY_JAPAN_REQ(0x3708, "日本副本排行榜"),
    RANKING_GRADE_REQ(0x3709, "评分排行榜"),
    RANKING_LADDER_REQ(0x370a, "天梯排行榜"),
    RANKING_FACTION_SCORE_REQ(0x370B, "公会积分排行榜"),


    //0x4F测试
    CR_LOGIN_REQ(0x4F01, "游戏服务器登入"),
    CR_PING_REQ(0x4F02, "游戏服务器ping包"),
    CR_RECONNECT_REQ(0x4F03, "游戏服务器重新登入"),

    CR_ROLE_INFO_GET_REQ(0x4F07, "跨服获取用户信息"),
    CR_MINE_PRODUCTIVITY_GET_REQ(0x4F17, "跨服获取资源点产出资源数");

    private short opCode;
    private String desc;

    private CmOpCode(int opCode, String desc) {
        this.opCode = (short) opCode;
        this.desc = desc;
    }

    public short getOpCode() {
        return opCode;
    }

    public String getDesc() {
        return desc;
    }
}
