package com.pkuvr.game_server.enumerate;

public enum ResourcesEnum {

    GOLD(1, "黄金"),
    STONE(2, "石头"),
    OIL(3, "石油"),
    IRON(4, "钢材"),
    DIAMOND(5, "钻石"),
    UNIT(6, "作战单位"),
    BUFF(7, "人物相关的buff"),
    PARTS(8, "根据品质随机出的零件"),
    PARTS_ASSIGN(9, "指定的零件（未做）"),
    RANK(10, "军功"),
    EQUIPMENT(11, "装备"),
    EQUIPMENT_PART(12, "装备碎片"),
    GIFT(13, "礼包"),
    KEY(14, "钥匙"),
    RAID_TOKEN(15, "副本扫荡令牌"),
    FLAG_TOKEN(16, "旗帜代币"),
    HONOR_TOKEN(17, "荣誉代币"),
    RANDOM_BOX(18, "宝箱"),
    SIMPLE_ITEM(19, "黑科技"),
    UNIT_PIC_PART(20, "兵种图纸碎片"),
    TREASURE_MAP(21, "藏宝图"),
    STAR_MATERIAL(22, "升星材料"),
    UNIT_PIC(23, "兵种图纸"),
    RESOURCE(24, "资源"),
    STRENGTH(25, "体力药剂"),
    LADDER_TOKEN(26, "競技代幣"),
    ACCELERATE(27, "加速道具"),
    CORP_TOKEN(28, "军团代币"),

    ITEM(50, "物品");

    private int typeID;
    private String desc;

    private ResourcesEnum(int typeID, String desc) {
        this.typeID = typeID;
        this.desc = desc;
    }

    /**
     * 获取资源枚举
     *
     * @param value
     * @return
     */
    public static ResourcesEnum getResource(int value) {
        ResourcesEnum[] values = ResourcesEnum.values();
        for (ResourcesEnum enumValue : values) {
            if (value == enumValue.typeID)
                return enumValue;
        }
        return null;
    }

    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


}
