/*
 * Copyright (c) 2001-2020 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.scofen.algorithms.study.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * TODO
 *
 * @author weiyi
 * @version V1.0
 * @since 2020-12-15 10:19
 */
public class Array {

    public static void main(String[] args) {
//        singleNumber(new int[] { 2, 2, 1, 1, 3 });
        rotate(new int[][]{{1,2,3},{4,5,6},{7,8,9}});
    }

    /**
     * @param nums
     * @return
     * @link "https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/"
     * 26. 删除排序数组中的重复项
     * 给定 nums = [0,0,1,1,1,2,2,3,3,4],
     * 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
     */
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return 1;
        }
        Arrays.sort(nums);
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }

    /**
     * @param prices
     * @return
     * @link "https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/"
     * 122. 买卖股票的最佳时机 II
     * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
     * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * <p>
     * 输入: [7,1,5,3,6,4]
     * 输出: 7
     * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
     */
    public int maxProfit(int[] prices) {
        int result = 0;
        for (int i = 1; i <= prices.length - 1; i++) {
            if (prices[i] > prices[i - 1]) {
                result = result + prices[i] - prices[i - 1];
            }
        }
        return result;
    }

    /**
     * @link "https://leetcode-cn.com/problems/rotate-array/"
     * 189. 旋转数组
     * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
     */
    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        int count = 0;
        for (int start = 0; count < nums.length; start++) {
            int current = start;
            int prev = nums[start];
            do {
                int next = (current + k) % nums.length;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                current = next;
                count++;
            } while (start != current);
        }
    }

    /**
     * @param nums
     * @return
     * @link "https://leetcode-cn.com/problems/single-number/"
     * 136. 只出现一次的数字
     * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。
     * 找出那个只出现了一次的元素。
     */
    public static int singleNumber(int[] nums) {
/*        int single = 0;
        for (int num : nums) {
            single ^= num;
        }
        return single;*/
        Arrays.sort(nums);
        int end = 1;
        for (int i = 0; i < nums.length - 1; i = i + 2) {
            if (nums[i] != nums[end]) {
                return nums[i];
            } else {
                end = end + 2;
            }
        }
        return nums[nums.length - 1];
    }

    /**
     * @link "https://leetcode-cn.com/problems/plus-one/"
     * 66. 加一
     * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
     * <p>
     * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
     * <p>
     * 你可以假设除了整数 0 之外，这个整数不会以零开头。
     */
    public int[] plusOne(int[] digits) {
        boolean addOne = false;
        for (int j = digits.length - 1; j >= 0; j--) {
            int value;
            if (j == digits.length - 1) {
                value = digits[j] + 1;
                addOne = value == 10;
                value = addOne ? 0 : value;
                digits[j] = value;
            } else {
                value = addOne ? digits[j] + 1 : digits[j];
                addOne = value == 10;
                value = addOne ? 0 : value;
                digits[j] = value;
            }
        }
        if (addOne) {
            digits = new int[digits.length + 1];
            digits[0] = 1;
            return digits;
        } else {
            return digits;
        }

    }

    /**
     * @param board
     * @return
     * @link "https://leetcode-cn.com/problems/valid-sudoku/"
     * 36. 有效的数独
     * 判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。
     * <p>
     * 数字 1-9 在每一行只能出现一次。
     * 数字 1-9 在每一列只能出现一次。
     * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
     */
    public boolean isValidSudoku(char[][] board) {
        // init data
        HashMap<Integer, Integer>[] rows = new HashMap[9];
        HashMap<Integer, Integer>[] columns = new HashMap[9];
        HashMap<Integer, Integer>[] boxes = new HashMap[9];
        for (int i = 0; i < 9; i++) {
            rows[i] = new HashMap<>();
            columns[i] = new HashMap<>();
            boxes[i] = new HashMap<>();
        }

        // validate a board
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char num = board[i][j];
                if (num != '.') {
                    int n = (int) num;
                    int box_index = (i / 3) * 3 + j / 3;

                    // keep the current cell value
                    rows[i].put(n, rows[i].getOrDefault(n, 0) + 1);
                    columns[j].put(n, columns[j].getOrDefault(n, 0) + 1);
                    boxes[box_index].put(n, boxes[box_index].getOrDefault(n, 0) + 1);

                    // check if this value has been already seen before
                    if (rows[i].get(n) > 1 || columns[j].get(n) > 1 || boxes[box_index].get(n) > 1) {
                        return false;
                    }
                }
            }
        }

        return true;

    }

    /**
     * @param matrix
     * @link "https://leetcode-cn.com/problems/rotate-image/"
     * 给定一个 n × n 的二维矩阵表示一个图像。
     * <p>
     * 将图像顺时针旋转 90 度。
     * <p>
     * 说明：
     * 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。
     * 请不要使用另一个矩阵来旋转图像。
     * <p>
     * 给定 matrix =
     * [
     * [ 5, 1, 9,11],
     * [ 2, 4, 8,10],
     * [13, 3, 6, 7],
     * [15,14,12,16]
     * ],
     * <p>
     * 原地旋转输入矩阵，使其变为:
     * [
     * [15,13, 2, 5],
     * [14, 3, 4, 1],
     * [12, 6, 8, 9],
     * [16, 7,10,11]
     * ]
     */
    public static void rotate(int[][] matrix) {
        int temp;
        for (int x = 0, y = matrix[0].length - 1; x < y; x++, y--) {
            for (int s = x, e = y; s < y; s++, e--) {
                temp = matrix[x][s];
                matrix[x][s] = matrix[e][x];
                matrix[e][x] = matrix[y][e];
                matrix[y][e] = matrix[s][y];
                matrix[s][y] = temp;
            }

        }

    }
}