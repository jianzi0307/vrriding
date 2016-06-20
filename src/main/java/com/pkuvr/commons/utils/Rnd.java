/*
 * This file is part of aion-emu <aion-emu.com>.
 *
 * aion-emu is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * aion-emu is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with aion-emu.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.pkuvr.commons.utils;

/**
 * 随机数辅助类
 *
 * @author Balancer
 */
public class Rnd {
    private static final MTRandom rnd = new MTRandom();

    /**
     * 获取随机数 0~1
     * get random number from 0 to 1
     *
     * @return rnd
     */
    public static float get() {
        return rnd.nextFloat();
    }

    /**
     * 获取随机数 0~(n-1)
     * Gets a random number from 0(inclusive) to n(exclusive)
     *
     * @param n The superior limit (exclusive)
     * @return A number from 0 to n-1
     */
    public static int get(int n) {
        return (int) Math.floor(rnd.nextDouble() * n);
    }

    /**
     * 获取随机数 min~max (注意,这里不是到 max-1)
     * get random number from min to max (not max-1 !)
     *
     * @param min
     * @param max
     * @return value
     */
    public static int get(int min, int max) {
        return min + (int) Math.floor(rnd.nextDouble() * (max - min + 1));
    }

    /**
     * 获取随机数 0~(n-1)
     *
     * @param n
     * @return n
     */
    public static int nextInt(int n) {
        return (int) Math.floor(rnd.nextDouble() * n);
    }

    /**
     * 获取随机数 -inf ~ +inf
     *
     * @return int
     */
    public static int nextInt() {
        return rnd.nextInt();
    }

    /**
     * 获取随机数 0~1
     *
     * @return double
     */
    public static double nextDouble() {
        return rnd.nextDouble();
    }

    /**
     * @return double
     */
    public static double nextGaussian() {
        return rnd.nextGaussian();
    }

    /**
     * @return double
     */
    public static boolean nextBoolean() {
        return rnd.nextBoolean();
    }
}
