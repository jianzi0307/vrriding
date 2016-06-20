package com.pkuvr.game_server.enumerate;

/**
 * 任务类别
 *
 * @author EvilHades
 */
public enum TaskTypeCode {

    ROLE_LEVEL(1, "角色升级至"),
    HEADQUARTERS_LEVEL(2, "指挥部升级至"),
    PASS_COPY(3, "通关副本次数"),
    PRODUCE_TROOP(4, "生产兵力数量"),
    RECEIVE_GOLD(5, "获得黄金数量"),
    RECEIVE_STONE(6, "获得石材数量"),
    RECEIVE_IRON(7, "获得钢材数量"),
    RECEIVE_OIL(8, "获得油料数量"),
    ATHLETICS_NUM(9, "竞技次数"),
    ALCHEMY_PLANT_NUM(10, "建造3个炼金厂"),
    STONE_PLANT_NUM(11, "建造3个石材厂"),
    STEEL_PLANT_NUM(12, "建造3个钢材厂"),
    OIL_PLANT_NUM(13, "建造3个油料厂"),
    LIMIT_TROOP(14, "提升兵力上限至"),
    MERIT_NUM(15, "获得功绩数"),
    IMPROVE_RANK(16, "提升军衔至"),
    ENHANCE_MOBILITY(17, "提升行动力至"),
    BUILDING_NUM(18, "建造建筑数"),
    PASS_ASSIGN_COPY(19, "通关某ID的副本"),
    COST_DIAMOND(20, "花费钻石数"),
    SEMIAUTOMATIC_BATTLE_NUM(21, "通关PVP次数"),
    BITE(22, "切磋"),
    LADDER(23, "竞技场"),
    FACTION_DONATE(24, "军团捐献"),
    FACTION_TECH(25, "军团科技"),
    ANY_COPY(26, "任意副本"),
    ELITE_BATTLE(27, "精英战役"),
    JOIN_FACTION(28, "加入军团"),

    MONTH_CARD(51, "月卡"),
    YEAR_CARD(52, "年卡"),
    RUNNING_CARD(53, "运营期卡");

    private short type;
    private String desc;

    private TaskTypeCode(int type, String desc) {
        this.type = (short) type;
    }

    public short getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

}