package com.pkuvr.game_server.enumerate;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * <p>类说明:消费点枚举</p>
 * <p>文件名： OperateTypeEnum.java</p>
 * <p>创建人及时间：	宋士龙 2012-6-8</p>
 * <p>
 * <p>修改人：</p>
 * <p>修改时间：</p>
 * <p>修改描述：</p>
 **/
public enum ConsumptionTypeEnum {
    Unknown(0, "未知"),// 未知

    BUILDING(1, "建筑"), //建筑
    BUILDING_ACCELERATE(2, "建筑升级"), //建筑升级加速
    BUILDING_CANCEL(3, "建筑取消"), //建筑取消升级
    BUILDING_RESOURCE_HARVEST(4, "建筑资源"), //建筑资源收取
    DIAMOND_EXC_RESOURCE(5, "钻石换资源"), //钻石换资源
    DAILY_AWARD(6, "每日奖励"),
    SIGN_AWARD(7, "签到奖励"),
    FILL_SIGN(8, "补签"),
    INVESTMENT(9, "投资计划"),
    SLOT(10, "老虎机"),
    TECHUPDATING(11, "科技升级"), //科技升级
    TECHUPDATING_ACCELERATE(12, "科技升级加速"), //科技升级加速
    TECHUPDATING_CANCEL(13, "科技取消"), //科技取消升级
    TREASURE_HUNT(14, "寻宝"),
    STRENGTH_BUY(16, "体力购买"),
    ITEM_DROW_ONE(17, "装备抽取单次"),
    ITEM_DROW_TEN(18, "装备抽取十次"),
    LADDER(19, "天梯"),
    CHANGE_NAME(20, "改名"),
    TACTICSUPDATING(21, "战术升级"), //战术升级
    TACTICSUPDATING_ACCELERATE(22, "战术加速"), //战术升级加速
    TACTICSUPDATING_CANCEL(23, "战术取消"), //战术取消升级
    FACTION_DONATE(24, "军团捐献"),
    FACTION_TECH(25, "军团科技"),
    FACTION_MALL(26, "军团商店"),
    FACTION(27, "军团"),
    BOSS(28, "BOSS战"),
    SKILL(29, "单位技能"),
    SALE(30, "折扣商品"),
    BATTLEUNITUPDATING(31, "兵种单位建造"), //兵种单位建造
    BATTLEUNIT_ACCELERATE(32, "兵种单位建造加速"), //兵种单位建造加速
    BATTLEUNIT_CANCEL(33, "兵种单位取消建造"), //兵种单位取消建造
    UNITUPDATING(34, "兵种单位升级"), //兵种单位升级
    UNIT_UPDATE_ACCELERATE(35, "兵种单位升级加速"), //兵种单位升级加速
    UNIT_UPDATE_CANCEL(36, "兵种单位取消升级"), //兵种单位取消升级
    BATTLE_UNIT_QUICK_BUILD(37, "快速补充兵力"),
    MALL_MEDAL(38, "商城勋章"),
    CLASS_UP(39, "兵种进阶"),
    MATERIAL(40, "装备材料"),
    ENTER_COPY(41, "进入副本"),   //进入副本
    END_COPY(42, "完成副本"),     //完成副本
    SUPPORT(43, "战斗请求支援"),   //战斗请求支援
    CEQUIPMENTINFO(44, "指挥官装备"),
    WORLD_CHAT(45, "世界聊天"),   //世界聊天

    TASK_FINSH(50, "任务"), // 任务
    RANKING_REWARD(51, "排行榜奖励"),// 排行榜奖励

    MEDAL(60, "勋章"), // 勋章
    MEDAL_CANCEL(61, "勋章取消"), // 勋章取消

    EMPTY_STRATEGIC_LIST(71, "战略"), // 战略
    EXCHANGE(72, "物资交换"), // 物资交换

    SEMIAUTOMATIC_PVP(81, "王牌指挥"), // 王牌指挥
    GUIDE(82, "新手引导"), // 新手引导
    MALL_RESOURCE_GOLD(83, "商城资源黄金"),
    MALL_RESOURCE_OIL(84, "商城资源石油"),
    MALL_RESOURCE_STONE(85, "商城资源石头"),
    MALL_RESOURCE_IRON(86, "商城资源钢材"),
    MALL_ITEM_RAID_TOKEN(87, "商城扫荡军令"),
    GM(88, "GM操作"),
    MALL_WORKER(89, "商城工人"),
    MALL(90, "商城"), // 主城商店 和 作战单位buff
    MAIL(91, "邮件"),//邮件
    MALL_REFRESH(93, "商城刷新"),
    MALL_WORKER_GUIDE(94, "新手引导工人"),
    MALL_RESOURCE(95, "商城资源"),
    MALL_ITEM(96, "商城物品"),
    MALL_BUFF(97, "商城BUFF"), // 商城资源buff
    MALL_VIP_GIFT(98, "商城VIP礼包"),
    PARTS_DRAW_ONE(99, "抽取单个零件"),
    PARTS_DRAW_TEN(100, "抽取十个零件"),

    DAILY_SIGN(101, "补签"),
    BATTLE_DISPATCH(102, "兵种遣散"),

    COPY_BLOOD(105, "血战到底"),

    VIP_REFRESH(106, "刷新"),


    MINE_BATTLE_REWARD(110, "资源点争夺获得"),//资源点争夺获得
    MINE_RESOURCE_HARVEST(111, "资源点资源收取"), //资源点资源收取
    MINE_RESOURCE_UNLOCK(112, "资源点解锁"), //资源点解锁
    AIRDROP_GOODS_HARVEST(113, "空降资源收取"), //空降资源收取
    ZONE_RESOURCE_PLUNDER(124, "玩家资源抢夺"), //玩家资源抢夺
    MINE_COLLECT_REWARD(125, "资源点收集获得"),//资源点收集获得

    CREATE_FACTION(114, "建立军团"), //建立军团
    FACTION_BATTLE(115, "军团战"),  //军团战

    CITY_INVEST(116, "城市投资"),  //城市投资
    FACTION_BATTLE_INSPIRE(117, "阵营战鼓舞"),  //阵营战鼓舞

    PAY_ADD_INGOT(211, "支付");// 支付

    private short value;
    private String desc;
    private Map<Integer, String> map = Maps.newHashMap();

    private ConsumptionTypeEnum(int value, String desc) {
        this.value = (short) value;
        this.desc = desc;

        map.put(value, desc);
    }

    public short getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public String get(int value) {
        return map.get(value);
    }
}
