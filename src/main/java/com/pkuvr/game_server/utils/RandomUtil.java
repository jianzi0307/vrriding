package com.pkuvr.game_server.utils;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 随机工具
 *
 * @author EvilHades
 */
public class RandomUtil {

    /**
     * 根据权重获取随机对象
     *
     * @param weightMap
     * @return
     */

    public static <T> T getRandomWeights(Map<T, Integer> weightMap) {

        T t = null;
        int sum = 0;

        for (Map.Entry<T, Integer> entry : weightMap.entrySet()) {
            sum += entry.getValue();
        }

        double rnd = Math.random() * sum;
        for (Map.Entry<T, Integer> entry : weightMap.entrySet()) {
            if (rnd < entry.getValue()) {
                t = entry.getKey();
                break;
            }


            rnd -= entry.getValue();
        }

        return t;
    }

    /**
     * 根据概率值计算是否被选择
     *
     * @param d
     * @return
     */
    public static boolean getIsSelected(double d) {
        boolean result = false;
        float random = (float) Math.random();
        if (random <= d) {
            result = true;
        }
        return result;
    }

    public static void main(String[] args) {
        Map<Integer, Integer> map = Maps.newHashMap();
        map.put(1, 200);
        map.put(2, 1000);
        map.put(3, 800);

        int num = 0;
        for (int i = 0; i < 1000; i++) {
            Integer randomWeights = getRandomWeights(map);
            if (randomWeights == 1) {
                num++;
            }
        }

        System.out.println(num);

    }

}
