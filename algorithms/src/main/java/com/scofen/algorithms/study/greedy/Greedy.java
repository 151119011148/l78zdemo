/*
 * Copyright (c) 2001-2020 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.scofen.algorithms.study.greedy;

import java.util.Arrays;

/**
 * TODO
 *
 * @author weiyi
 * @version V1.0
 * @since 2020-12-10 18:16
 */
public class Greedy {

    public static void main(String[] args) {
        //
    }

    /**
     * 860. 柠檬水找零
     * 在柠檬水摊上，每一杯柠檬水的售价为 5 美元。
     * 顾客排队购买你的产品，（按账单 bills 支付的顺序）一次购买一杯。
     * 每位顾客只买一杯柠檬水，然后向你付 5 美元、10 美元或 20 美元。你必须给每个顾客正确找零，也就是说净交易是每位顾客向你支付 5 美元。
     *
     * 注意，一开始你手头没有任何零钱。
     * 如果你能给每位顾客正确找零，返回 true ，否则返回 false 。
     * @param bills
     * @return
     */
    public boolean lemonadeChange(int[] bills) {
        int five = 0, ten = 0, twenty = 0;
        for (int bill : bills) {
            // 情况一
            if (bill == 5) {
                five++;
            }
            // 情况二
            if (bill == 10) {
                if (five <= 0) {
                    return false;
                }
                ten++;
                five--;
            }
            // 情况三
            if (bill == 20) {
                // 优先消耗10美元，因为5美元的找零用处更大，能多留着就多留着
                if (five > 0 && ten > 0) {
                    five--;
                    ten--;
                    twenty++; // 其实这行代码可以删了，因为记录20已经没有意义了，不会用20来找零
                } else if (five >= 3) {
                    five -= 3;
                    twenty++; // 同理，这行代码也可以删了
                } else {
                    return false;
                }
            }
        }
        return true;

    }


    /**
     *738. 单调递增的数字
     * 给定一个非负整数 N，找出小于或等于 N 的最大的整数，同时这个整数需要满足其各个位数上的数字是单调递增。
     *
     * （当且仅当每个相邻位数上的数字 x 和 y 满足 x <= y 时，我们称这个整数是单调递增的。）

     输入: N = 332
     输出: 299

     输入: N = 1234
     输出: 1234
     */
    public int monotoneIncreasingDigits(int N) {
        /*我们可以从高到低按位构造这个小于等于 N 的最大单调递增的数字。
        假设不考虑 N 的限制，那么对于一个长度为 n 的数字，最大单调递增的数字一定
        是每一位都为 9 的数字。

        记 strN[i] 表示数字 N 从高到低的第 i 位的数字（i 从 0 开始）。

        如果整个数字 N 本身已经是按位单调递增的，那么最大的数字即为 N。

        如果找到第一个位置 i 使得 [0,i-1] 的数位单调递增且 [i-1]>strN[i−1]，
        此时 [0,i] 的数位都与 N 的对应数位相等，仍然被 N 限制着，即我们不能随意填写
         [i+1,n-1]位置上的数字。为了得到最大的数字，我们需要解除 N 的限制，
         来让剩余的低位全部变成 9 ，即能得到小于 N 的最大整数。而从贪心的角度考虑，
         我们需要尽量让高位与 N 的对应数位相等，故尝试让 strN[i−1] 自身数位减 1。
         此时已经不再受 N 的限制，直接将 [i,n−1] 的位置上的数全部变为 99 即可。

        但这里存在一个问题：当strN[i−1] 自身数位减 1 后可能会使得 strN[i−1]
        和 strN[i−2] 不再满足递增的关系，因此我们需要从 i-1开始递减比较相邻数位的
        关系，直到找到第一个位置 j 使得 strN[j] 自身数位减 1 后
        strN[j−1] 和strN[j] 仍然保持递增关系，或者位置 j 已经到最左边（即 j 的值为 0），
        此时我们将 [j+1,n-1] 的数全部变为 9 才能得到最终正确的答案。*/

        char[] strN = Integer.toString(N).toCharArray();
        int i = 1;
        while (i < strN.length && strN[i - 1] <= strN[i]) {
            i += 1;
        }
        if (i < strN.length) {
//            当strN[i−1] 自身数位减 1 后可能会使得 strN[i−1]
//            和 strN[i−2] 不再满足递增的关系，因此我们需要从 i-1开始递减比较相邻数位的
//            关系，直到找到第一个位置 j 使得 strN[j] 自身数位减 1 后
//            strN[j−1] 和strN[j] 仍然保持递增关系，或者位置 j 已经到最左边（即 j 的值为 0）
            while (i > 0 && strN[i - 1] > strN[i]) {
                strN[i - 1] -= 1;
                i -= 1;
            }
            for (i += 1; i < strN.length; ++i) {
                strN[i] = '9';
            }
        }
        return Integer.parseInt(new String(strN));

    }

    /**
     *
     * @param ratings
     * @return
     */
    public int candy(int[] ratings) {
        int[] left = new int[ratings.length];
        int[] right = new int[ratings.length];
        Arrays.fill(left, 1);
        Arrays.fill(right, 1);
        for(int i = 1; i < ratings.length; i++) {
            if(ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            }
        }
        int count = left[ratings.length - 1];
        for(int i = ratings.length - 2; i >= 0; i--) {
            if(ratings[i] > ratings[i + 1]) {
                right[i] = right[i + 1] + 1;
            }
            count += Math.max(left[i], right[i]);
        }
        return count;
    }




}
