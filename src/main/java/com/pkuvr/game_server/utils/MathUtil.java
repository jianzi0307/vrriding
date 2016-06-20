package com.pkuvr.game_server.utils;

import java.util.*;

public class MathUtil {

    /**
     * Returns distance between two sets of coords
     *
     * @param x1 first x coord
     * @param y1 first y coord
     * @param x2 second x coord
     * @param y2 second y coord
     * @return distance between sets of coords
     */
    public static double getDistance(int x1, int y1, int x2, int y2) {
        // using long to avoid possible overflows when multiplying
        int dx = x2 - x1;
        int dy = y2 - y1;

        // return Math.hypot(x2 - x1, y2 - y1); // Extremely slow
        // return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)); // 20 times faster than hypot
        return Math.sqrt(dx * dx + dy * dy); // 10 times faster then previous line
    }

    /**
     * 取得多个随机数(结果集 0开始  0,1,2,3,4...)
     *
     * @param number 个数
     * @return
     */
    public static List<Integer> getRandomNumbers(int number, int maxNumber) {

        Set<Integer> rs = new HashSet<Integer>();
        List<Integer> result = new ArrayList<Integer>();

        while (rs.size() < number) {
            int num = (int) (Math.random() * maxNumber);
            if (!rs.contains(num)) {
                rs.add(num);
                result.add(num);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        List<Integer> randomNumbers = getRandomNumbers(3, 5);
        Iterator<Integer> iterator = randomNumbers.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
