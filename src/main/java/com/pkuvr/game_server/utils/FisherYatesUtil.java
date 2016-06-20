package com.pkuvr.game_server.utils;


/**
 * <p>类说明:</p>
 * <p>文件名： FisherYatesUtil.java</p>
 * <p>创建人及时间：	宋士龙 2013-2-17</p>
 * <p>
 * <p>修改人：</p>
 * <p>修改时间：</p>
 * <p>修改描述：</p>
 **/
public class FisherYatesUtil {

    public static void shuffleArray(Object[] datas) {
        if (datas == null || datas.length == 0) {
            return;
        }

        int size = datas.length;
        int randomIndex = 0;

        while (size > 1) {
            randomIndex = (int) Math.floor(Math.random() * (size));
            Object temp = datas[size - 1];
            datas[size - 1] = datas[randomIndex];
            datas[randomIndex] = temp;
            size--;
        }
    }


    public static void main(String[] args) {
        Integer[] datas = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        Integer[][] countResult = new Integer[10][10];

        int testNumber = 1000000;
        for (int i = 0; i < testNumber; i++) {
            FisherYatesUtil.shuffleArray(datas);
            for (int j = 0; j < 10; j++) {
                int data = datas[j];
                if (countResult[j][data] == null) {
                    countResult[j][data] = 1;
                } else {
                    countResult[j][data] += 1;
                }
            }
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(countResult[i][j]);
                if (j < 9) {
                    System.out.print(",");
                } else {
                    System.out.println();
                }
            }
        }
    }
}
