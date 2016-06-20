package com.pkuvr.game_server.constant;

/**
 * <p>类说明:</p>
 * <p>文件名： AppConfig.java</p>
 * <p>创建人及时间：	宋士龙 2012-6-15</p>
 * <p>
 * <p>修改人：</p>
 * <p>修改时间：</p>
 * <p>修改描述：</p>
 **/
public class AppConfig {

    public static final String SYSTEM_TEST = "system.test";

    public static final String GAME_ID = "game.id";
    public static final String GAMESERVER_SECRET_KEY = "gameserver.secret.key";
    public static final String GM_SECRET_KEY = "gm.secret.key";

    // 实时pvp次数限制
    public static final String PVP_PLAYER_MAX_TIMES = "pvp.player.max.times";
    public static final String PVP_NPC_MAX_TIMES = "pvp.npc.max.times";
    public static final int PVP_WIN_HONOR_TOKEN = 100;
    public static final int PVP_FAIL_HONOR_TOKEN = 50;
    public static final int PVP_TOKEN_TIMES = 5;

    public static final long ZONE_CHANGE_PLAYER_CD = 8640000L;

    public static final long BASE_PLUNDER_CACULATE_TIME = 7200000L;
    public static final long MINE_PLUNDER_CACULATE_TIME = 18000000L;

    //战斗胜利
    public static final int BATTLE_SUCCESS_RESULT = 1001;
    //战斗失败
    public static final int BATTLE_FAILED_RESULT = 1002;
    //战斗超时
    public static final int BATTLE_TIMEOUT_RESULT = 1003;

    public static final int MINE_MAX_LEVEl = 20; // 资源点最高级别
    public static final double MINE_REFRESH_RATE = 0.4285; // 资源点刷新比例
    public static final double MINE_GRAB_PLAYER_RATE = 0.5; // 资源点抢夺时获取玩家库存比例
    public static final double MINE_GRAB_NPC_RATE = 0.05; // 资源点抢夺时获取NPC库存比例

    //消CD花费钻石数,向上取整,240秒1个钻石
    public static final int ROLE_CD_CONSUME_DIAMOND = 60;
    //资源花费钻石数,向上取整,20000资源1个钻石
    public static final int ROLE_RESOURCE_COST_DIAMOND = 1500;

    //搜集资源间隔时间，以秒为单位
    public static final int ROLE_RESOURCE_COLLECT_TIME = 45;
    //指挥部建筑functionid
    public static final int HEADERQUARTERS_FUNCTION_ID = 1;
    //金矿场建筑functionid
    public static final int GOLD_FUNCTION_ID = 2;
    //炼钢场建筑functionid
    public static final int IRON_FUNCTION_ID = 3;
    //炼油场建筑functionid
    public static final int OIL_FUNCTION_ID = 4;
    //采石场建筑functionid
    public static final int STONE_FUNCTION_ID = 5;

}
