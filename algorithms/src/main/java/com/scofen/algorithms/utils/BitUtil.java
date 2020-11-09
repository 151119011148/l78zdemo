package com.scofen.algorithms.utils;

/**
 * @author 高锋
 * @className: BitUtil
 * @description: TODO
 * @date 2020/11/610:21
 */
public class BitUtil {

    /**
     * @author 高锋
     * @description 计算给定数字中二进制中1的个数
     * @Date 10:22 2020/11/6
     * @Param [n]
     * @return int
     **/
    public static int countBit1(int n) {
        int count = 0; // 计数器
        while (n > 0) {
            if((n & 1) == 1)  count++;  // 当前位是1，count++
            n >>= 1 ; // n向右移位
        }
        return count;
    }


}
