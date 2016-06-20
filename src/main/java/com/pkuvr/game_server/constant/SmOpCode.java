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
public enum SmOpCode {

    ERROR(0x0000, "错误"),

    // 0x1e,登陆及创建用户相关
    ENTER_CROSS_SERVER_RES(0x1e01, "登录跨服服务器"),
    MULTIPLE_LOGIN(0x1e03, "多重登陆,后一个人把前一个人踢出游戏"),
    RECONNECTION_RES(0x1e06, "断线重登陆游戏 "),
    HEARTBEAT_RES(0x1e07, "心跳"),
    CR_ROLE_INFO_SYNC_RES(0x1e08, "跨服同步用户信息"),
    //CR_MINE_INFO_SYNC_RES( 0x1e09, "跨服同步资源点信息" ),

    // 0x2c,实时PVP系统
    PVP_ROOM_RES(0x2c01, "pvp房间"),
    PVP_MATCH_RES(0x2c02, "pvp匹配对手"),
    PVP_START_RES(0x2c03, "pvp开始"),
    PVP_BATTLE_UNIT_DEPLOY_RES(0x2c04, "pvp布阵"),
    PVP_BATTLE_START_RES(0x2c05, "pvp战斗开始"),
    PVP_SYNC_ATTACK_RES(0x2c06, "同步攻击"),
    PVP_SYNC_DEAD_RES(0x2c07, "同步死亡"),
    PVP_SYNC_TACTICS_RES(0x2c08, "同步战术"),
    PVP_SYNC_COMMON_RES(0x2c0b, "同步通用信息"),
    PVP_SYNC_COMMON_NOTIFY_RES(0x2c0c, "同步通用信息下发"),
    PVP_DISCONNECT_RES(0x2c09, "pvp过程中断线"),
    PVP_BATTLE_END_RES(0x2c10, "pvp战斗结束"),
    PVP_SYNC_DEAD_NOTIFY_RES(0x2c11, "pvp死亡同步下发"),
    PVP_SYNC_TACTICS_NOTIFY_RES(0x2c12, "pvp战术同步下发"),
    PVP_SYNC_COMMAND_RES(0x2c13, "同步战术指令"),
    PVP_SYNC_COMMAND_NOTIFY_RES(0x2c14, "同步战术指令下发"),
    PVP_PLAYER_EXIT_RES(0x2c15, "pvp玩家退出"),
    PVP_PLAYER_EXIT_NOTIGY_RES(0x2c16, "pvp玩家退出下发"),
    PVP_DEPLOY_VERIFY_NOTIFY_RES(0x2c17, "pvp布阵超时检查下发"),
    PVP_NPC_BATTLE_MATCH_RES(0x2c0a, "pvp npc匹配"),
    PVP_NPC_BATTLE_START_RES(0x2c18, "pvp npc战斗开始"),
    PVP_NPC_BATTLE_END_RES(0x2c19, "pvp npc战斗结束"),
    PVP_FRIEND_RES(0x2c20, "好友切磋请求"),
    PVP_FRIEND_NOTIFY_RES(0x2c21, "好友切磋请求下发"),
    PVP_FRIEND_CONFIRM_RES(0x2c22, "好友切磋请求确认"),
    PVP_TOKEN_INFO_RES(0x2c23, "获取玩家pvp代币获取次数"),


    // 0x22,资源点PVP系统
    MINE_ENTER_RES(0x2200, "进入资源点"),
    MINE_LIST_RES(0x2201, "获取资源点列表"),
    MINE_DEFEND_INFO_RES(0x2202, "获取资源点防御信息"),
    MINE_DEFEND_DEPLOY_RES(0x2203, "资源点布防"),
    MINE_BATTLE_START_RES(0x2204, "资源点战斗开始"),
    MINE_BATTLE_END_RES(0x2205, "资源点战斗结束"),
    MINE_BATTLE_LOG_RES(0x2206, "资源点战斗Log获取"),
    MINE_CHANGE_PLAYER_RES(0x2207, "更换资源点对手"),
    MINE_INFO_RES(0x2208, "获取资源点信息"),
    MINE_HARVEST_QUERY_RES(0x2209, "资源矿点收集资源查询"),
    MINE_HARVEST_RESOURCE_RES(0x2210, "资源矿点收集资源"),
    MINE_UNLOCK_REFRESH_RES(0x2211, "解锁刷新资源"),

    MINE_COLLECT_START_RES(0x2222, "资源点收集开始"),
    MINE_COLLECT_LOG_RES(0x2223, "资源点收集Log获取"),
    MINE_PROTECT_TIME_RES(0x2224, "资源点保护剩余时间获取"),

    ZONE_DEFEND_INFO_RES(0x2212, "获取玩家防区防御信息"),
    ZONE_DEFEND_DEPLOY_RES(0x2213, "玩家防区布防"),
    ZONE_BATTLE_START_RES(0x2214, "玩家防区战斗开始"),
    ZONE_BATTLE_END_RES(0x2215, "玩家防区战斗结束"),
    ZONE_BATTLE_LOG_RES(0x2216, "玩家防区战斗Log获取"),
    ZONE_CHANGE_PLAYER_RES(0x2217, "更换玩家防区对手"),
    ZONE_INFO_RES(0x2218, "获取玩家防区信息"),
    ZONE_UNLOCK_REFRESH_RES(0x2219, "解锁刷新玩家防区"),

    // 0x30,聊天系统
    WORLD_CHAT_RES(0x3001, "世界聊天"),
    FRIEND_CHAT_RES(0x3002, "私聊"),
    FACTION_CHAT_RES(0x3005, "军团聊天"),
    WORLD_CHAT_NOTIFY_RES(0x3003, "下发世界聊天信息"),
    FRIEND_CHAT_NOTIFY_RES(0x3004, "下发私聊信息"),
    FACTION_CHAT_NOTIFY_RES(0x3006, "下发军团聊天信息"),
    CHAT_LIST_RES(0x3007, "获取历史聊天信息"),

    // 0x31,好友系统
    ADD_FRIEND_RES(0x3101, "增加好友"),
    DEIETE_FRIEND_RES(0x3102, "删除好友"),
    FRIEND_LIST_RES(0x3103, "获取好友列表"),
    FIND_FRIEND_RES(0x3104, "查找好友"),
    FRIEND_INVITE_SEND(0X3105, "被添加好友提示"),
    ADD_BLACKLIST_RES(0x3106, "增加黑名单"),
    DEIETE_BLACKLIST_RES(0x3107, "删除黑名单"),
    BLACK_LIST_RES(0x3108, "获取黑名单列表"),

    CR_ADD_FRIEND_APPLY_RES(0x3109, "增加好友申请"),
    CR_DEIETE_FRIEND_RES(0x3110, "删除好友"),
    CR_FRIEND_LIST_RES(0x3111, "获取好友列表"),
    CR_FIND_FRIEND_RES(0x3112, "查找好友"),
    CR_ADD_BLACKLIST_RES(0x3113, "增加黑名单"),
    CR_DEIETE_BLACKLIST_RES(0x3114, "删除黑名单"),
    CR_BLACK_LIST_RES(0x3115, "获取黑名单列表"),
    CR_FRIEND_APPLY_LIST_RES(0x3116, "获取加好友申请列表"),
    CR_FRIEND_APPLY_OP_RES(0x3117, "好友申请列表操作"),
    CR_OWN_FRIEND_APPLY_LIST_RES(0x3118, "获取个人提交的好友申请列表"),
    CR_CANCEL_OWN_FRIEND_APPLY_RES(0x3119, "删除个人提交的好友申请"),

    // 0x32 邮件系统
    MAIL_GET_CONTENT_RES(0x3201, "获取邮件内容"),
    MAIL_GET_LIST_RES(0x3202, "获取邮件列表"),
    MAIL_RECEIVE_GIFT_RES(0x3203, "获取邮件礼包"),
    MAIL_WRITE_CONTENT_RES(0x3204, "玩家写邮件内容"),

    // 0x33 内购商城系统
    INSOURCE_ITEM_LIST_RES(0x3301, "商城列表"),
    INSOURCE_BUY_ITEM_RES(0x3302, "购买商城物品"),
    INSOURCE_MALL_BUFF_RES(0x3303, "获取商城Buff"),
    INSOURCE_BUFF_EXPIRED_RES(0x3304, "商城buff到期"),

    OUTER_MALL_GIFT_ITEM_RES(0X3311, "外购商品"),


    // 0x34 每日奖励系统
    DAILY_AWARD_TYPE_RES(0x3401, "每日奖励状态"),
    DAILY_AWARD_INTERFACE_RES(0x3402, "获取每日奖励信息"),
    DAILY_AWARD_REWARD_RES(0x3403, "获取每日奖励"),
    DAILY_EXCHANGE_TYPE_RES(0x3404, "每日交换状态"),
    DAILY_EXCHANGE_INTERFACE_RES(0x3405, "获取每日交换信息"),
    DAILY_EXCHANGE_REWARD_RES(0x3406, "获取每日交换"),

    DAILY_EXCHANGE_REFRESH_INFO_RES(0x3407, "物资船刷新信息"),
    DAILY_EXCHANGE_REFRESH_RES(0x3408, "刷新物资船"),

    // 活动
    DAILY_SIGN_REWARD_RES(0x3411, "领取签到奖励"),
    DAILY_SIGN_LIST_RES(0x3412, "查询签到列表"),

    ACTIVITY_CREATE_LIST_RES(0x3413, "7天签到"),
    ACTIVITY_CREATE_REWARD_RES(0x3414, "领取7天签到奖励"),

    ACTIVITY_COPY_LIST_RES(0x3415, "副本活动"),
    ACTIVITY_COPY_REWARD_RES(0x3416, "领取副本活动奖励"),

    ACTIVITY_BUILDING_LIST_RES(0x3417, "等级活动"),
    ACTIVITY_BUILDING_REWARD_RES(0x3418, "领取等级活动奖励"),

    ACTIVITY_ONLINE_LIST_RES(0x3419, "等级活动"),
    ACTIVITY_ONLINE_REWARD_RES(0x3410, "领取等级活动奖励"),

    ACTIVITY_ACTIVE_LIST_RES(0x341a, "活跃度活动"),
    ACTIVITY_ACTIVE_REWARD_RES(0x341b, "领取活跃度活动奖励"),

    ACTIVITY_SEND_TIP_RES(0x341c, "下发活动提示"),

    ACTIVITY_SLOT_INFO_RES(0x341d, "老虎机信息"),
    ACTIVITY_SLOT_USE_RES(0x341e, "使用老虎机"),

    ACTIVITY_STRENGTH_INFO_RES(0x3421, "体力活动信息"),
    ACTIVITY_STRENGTH_REWADR_RES(0x3422, "领取体力活动奖励"),

    ACTIVITY_LOGIN_INFO_RES(0x3427, "登陆累计信息"),
    ACTIVITY_LOGIN_REWARD_RES(0x3428, "登陆累计奖励领取"),

    SALE_DAY_INFO_RES(0x3423, "7天限购"),
    SALE_DAY_BUY_RES(0x3424, "7天限购购买"),
    SALE_LIMIT_INFO_RES(0x3425, "限时购"),
    SALE_LIMIT_BUY_RES(0x3426, "限时购购买"),

    // 0x35 新手引导系统
    GUIDE_FINSH_RES(0x3501, "完成新手引导"),
    GUIDE_STATUS_RES(0x3502, "新手引导状态"),

    GUIDE_PROMPT_FINSH_RES(0x3503, "完成新手引导"),
    GUIDE_PROMPT_STATUS_RES(0x3504, "新手引导状态"),

    PROMPT_INFO_RES(0x3505, "指示状态"),


    // 0x37 排行榜系统
    RANKING_LEVEL_RES(0x3701, "等级排行榜"),
    RANKING_RANK_RES(0x3702, "军衔排行榜"),
    RANKING_SEMIAUTOMATIC_RES(0x3703, "王牌指挥排行榜"),
    RANKING_COPY_RES(0x3704, "副本排名"),
    RANKING_REWARD_RES(0x3705, "领取排行榜奖励"),
    RANKING_REWARD_TYPE_RES(0x3706, "排行榜奖励状态"),
    RANKING_HONNOR_RES(0x3707, "荣誉排行榜"),
    RANKING_COPY_JAPAN_RES(0x3708, "日本副本排行榜"),
    RANKING_GRADE_RES(0x3709, "评分排行榜"),
    RANKING_LADDER_RES(0x370a, "天梯排行榜"),
    RANKING_FACTION_SCORE_RES(0x370B, "公会积分排行榜"),


    // 0x4F 跨服相关功能
    CR_LOGIN_RES(0x4F01, "游戏服务器登入返回"),
    CR_PING_RES(0x4F02, "游戏服务器ping包返回"),
    CR_RECONNECT_RES(0x4F03, "游戏服务器重新登入返回"),

    CR_ADD_HONOR_SEND(0x4F06, "跨服增加玩家荣誉"),
    CR_ROLE_INFO_SEND(0x4F07, "跨服获取用户信息"),
    CR_ADD_TOKEN_SEND(0x4F08, "跨服增加玩家代币"),
    CR_ADD_TASK_FINISH_SEND(0x4F09, "跨服完成任务"),

    CR_ROLE_INFO_GET_NOTIFY_RES(0x4F10, "跨服用户信息成功获取下发"),

    CR_ADD_RESOURCE_SEND(0x4F11, "跨服增加玩家资源"),
    CR_SUB_RESOURCE_SEND(0x4F12, "跨服减少玩家资源"),
    CR_REDUCE_BATTLE_UNIT_SEND(0x4F13, "跨服减少玩家作战单位"),
    CR_MINE_NUM_NOTIFY_SEND(0x4F14, "跨服资源点占领广播"),
    CR_GUIDE_STEP_FINISH_SEND(0x4F15, "跨服发送新手引导步骤完成"),
    CR_REDUCE_PLUNDER_RESOURCE_SEND(0x4F16, "跨服发送减少该玩家主城建筑被掠夺资源数"),
    CR_MINE_PRODUCTIVITY_SEND(0x4F17, "跨服获取资源点产出资源数"),

    CR_MINE_INFO_GET_NOTIFY_RES(0x4F18, "跨服资源点信息成功获取下发");

    private short opCode;
    private String desc;

    private SmOpCode(int opCode, String desc) {
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
