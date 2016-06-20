package com.pkuvr.game_server.utils;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Set;

public class ConfigUtil {
    private int buyOilPriceNumMax = 0;                 // 购买次数超过次值的,都按次值处理
    private Set<Integer> useTreasureBoxSendNotifySet;    // 开宝箱物品播报，需要将以下物品添加到触发播报列表
    private Map<Integer, Integer> buyOilPriceMap;        // 购买油料的价格,增加梯度。（第一次20,2-5次40,6-13次80,14次以后200）

    @PostConstruct
    public void start() {
        // 初始化购买油料的价格,增加梯度。（第一次20,2-5次40,6-13次80,14次以后200）
        Set<Integer> buyOilPriceKey = buyOilPriceMap.keySet();
        for (Integer buyOilPriceNum : buyOilPriceKey) {
            if (buyOilPriceNum > buyOilPriceNumMax)
                buyOilPriceNumMax = buyOilPriceNum;
        }
    }

    public Set<Integer> getUseTreasureBoxSendNotifySet() {
        return useTreasureBoxSendNotifySet;
    }

    public void setUseTreasureBoxSendNotifySet(Set<Integer> useTreasureBoxSendNotifySet) {
        this.useTreasureBoxSendNotifySet = useTreasureBoxSendNotifySet;
    }

    public void setBuyOilPriceMap(Map<Integer, Integer> buyOilPriceMap) {
        this.buyOilPriceMap = buyOilPriceMap;
    }

    public Integer getBuyOilPrice(int buyOilPriceNum) {
        if (buyOilPriceNum > buyOilPriceNumMax)
            buyOilPriceNum = buyOilPriceNumMax;

        Integer rtn = buyOilPriceMap.get(buyOilPriceNum);
        if (rtn != null)
            return rtn;
        return buyOilPriceMap.get(buyOilPriceNumMax);
    }
}
