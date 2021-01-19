/*
 * Copyright (c) 2001-2020 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.scofen.algorithms.study.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
//        rotate(new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } });
        prefixesDivBy5(new int[] { 0, 1, 1, 1, 1 });
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

    /**
     * 20. 有效的括号
     * "https://leetcode-cn.com/problems/valid-parentheses/"
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
     * <p>
     * 有效字符串需满足：
     * <p>
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     * 注意空字符串可被认为是有效字符串。
     *
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        int n = s.length();
        if (n % 2 == 1) {
            return false;
        }

        Map<Character, Character> pairs = new HashMap<Character, Character>() {{
            put(')', '(');
            put(']', '[');
            put('}', '{');
        }};
        Deque<Character> stack = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (pairs.containsKey(ch)) {
                if (stack.isEmpty() || !stack.peek().equals(pairs.get(ch))) {
                    return false;
                }
                stack.pop();
            } else {
                stack.push(ch);
            }
        }
        return stack.isEmpty();
    }

    /**
     * 268. 丢失的数字
     * "https://leetcode-cn.com/problems/missing-number/"
     * 给定一个包含 [0, n] 中 n 个数的数组 nums ，找出 [0, n] 这个范围内没有出现在数组中的那个数。
     *
     * @param nums
     * @return
     */
    public int missingNumber(int[] nums) {
        //        我们知道数组中有 n 个数，并且缺失的数在 [0..n] 中。
        //        因此我们可以先得到 [0..n]的异或值，再将结果对数组中的每一个数
        //        进行一次异或运算。未缺失的数在 [0..n]和数组中各出现一次，
        //        因此异或后得到 0。而缺失的数字只在 [0..n] 中出现了一次，在数组中没有出现，
        //        因此最终的异或结果即为这个缺失的数字。

        int missing = nums.length;
        for (int i = 0; i < nums.length; i++) {
            missing ^= i ^ nums[i];
        }
        return missing;

    }

    /**
     * 455. 分发饼干
     * 假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，
     * 每个孩子最多只能给一块饼干。
     * <p>
     * 对每个孩子 i，都有一个胃口值 g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；
     * 并且每块饼干 j，都有一个尺寸 s[j] 。如果 s[j] >= g[i]，我们可以将这个饼干 j 分配给孩子 i ，
     * 这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
     *
     * @param g
     * @param s
     * @return
     */
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int i = 0, j = 0, result = 0;
        while (i < g.length && j < s.length) {
            if (g[i] <= s[j]) {
                result++;
                i++;
            }
            j++;

        }
        return result;

    }

    /**
     * 330. 按要求补齐数组
     * ”https://leetcode-cn.com/problems/patching-array/“
     * 给定一个已排序的正整数数组 nums，和一个正整数 n 。从 [1, n] 区间内选取任意个数字补充到 nums 中，使得 [1, n] 区间内的任何数字都可以用 nums 中某几个数字的和来表示。请输出满足上述要求的最少需要补充的数字个数。
     * <p>
     * 示例 1:
     * <p>
     * 输入: nums = [1,3], n = 6
     * 输出: 1
     * 解释:
     * 根据 nums 里现有的组合 [1], [3], [1,3]，可以得出 1, 3, 4。
     * 现在如果我们将 2 添加到 nums 中， 组合变为: [1], [2], [3], [1,3], [2,3], [1,2,3]。
     * 其和可以表示数字 1, 2, 3, 4, 5, 6，能够覆盖 [1, 6] 区间里所有的数。
     * 所以我们最少需要添加一个数字。
     *
     * @param nums
     * @param n
     * @return
     */
    public int minPatches(int[] nums, int n) {
        /*对于正整数 x，如果区间 [1,x-1]内的所有数字都已经被覆盖，且 xx 在数组中，
        则区间 [1,2x-1]内的所有数字也都被覆盖。证明如下。

        对于任意 1≤y<x，y 已经被覆盖，x 在数组中，因此 y+x 也被覆盖，
        区间 [x+1,2x-1]（即区间 [1,x-1] 内的每个数字加上 xx 之后得到的区间）内的所有数字
        也被覆盖，由此可得区间 [1,2x-1][1,2x−1] 内的所有数字都被覆盖。

        假设数字 x 缺失，则至少需要在数组中补充一个小于或等于 x 的数，才能覆盖到 x，
        否则无法覆盖到 x。

        如果区间 [1,x-1]内的所有数字都已经被覆盖，则从贪心的角度考虑，补充 x 之后即
        可覆盖到 x，且满足补充的数字个数最少。在补充 x 之后，区间 [1,2x-1] 内的所有数字
        都被覆盖，下一个缺失的数字一定不会小于 2x。

        由此可以提出一个贪心的方案。每次找到未被数组 nums 覆盖的最小的整数 x，
        在数组中补充 x，然后寻找下一个未被覆盖的最小的整数，重复上述步骤直到区间
        [1,n] 中的所有数字都被覆盖。

        具体实现方面，任何时候都应满足区间[1,x−1] 内的所有数字都被覆盖。令 x 的初始值为 1，
        数组下标index 的初始值为 0，则初始状态下区间[1,x−1] 为空区间，满足区间内的所有数字
        都被覆盖。进行如下操作。

        如果index 在数组nums 的下标范围内且nums[index]≤x，则将 nums[index] 的值加给 x，并将
         index 的值加 11。

        被覆盖的区间从 [1,x-1] 扩展到 [1,x+nums[index]−1]，对 x 的值更新以后，被覆盖的区间为 [1,x−1]。
        否则，x 没有被覆盖，因此需要在数组中补充 x，然后将 x 的值乘以 2。

        在数组中补充 x 之后，被覆盖的区间从 [1,x−1] 扩展到 [1,2x-1]，对 x 的值更新以后，
        被覆盖的区间为 [1,x−1]。
        重复上述操作，直到 x 的值大于 n。

        由于任何时候都满足区间 [1,x−1] 内的所有数字都被覆盖，因此上述操作可以保证区间[1,n]
        内的所有数字都被覆盖。

        又由于上述操作只在 x 不被覆盖时才在数组中补充 x，如果不补充 x 则 x 无法被覆盖，
        因此可以保证补充的数字个数最少。如果减少补充的数字个数，则无法覆盖区间 [1,n] 内的所有数字。
*/
        int patches = 0;
        long x = 1;
        int length = nums.length, index = 0;
        while (x <= n) {
            if (index < length && nums[index] <= x) {
                x += nums[index];
                index++;
            } else {
                x *= 2;
                patches++;
            }
        }
        return patches;
    }

    /**
     * 228. 汇总区间
     * “https://leetcode-cn.com/problems/summary-ranges/”
     * 给定一个无重复元素的有序整数数组 nums 。
     * <p>
     * 返回 恰好覆盖数组中所有数字 的 最小有序 区间范围列表。也就是说，nums 的每个元素都恰好被某个区间范围所覆盖，并且不存在属于某个范围但不属于 nums 的数字 x 。
     * <p>
     * 列表中的每个区间范围 [a,b] 应该按如下格式输出：
     * 输入：nums = [0,1,2,4,5,7]
     * 输出：["0->2","4->5","7"]
     *
     * @param nums
     * @return
     */
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        int n = nums.length;
        if (n == 0) {
            return res;
        }
        //用两个整数记录窗口大小
        int pre = 0, later = 0;
        for (int i = 1; i < n; i++) {
            //当连续时，窗口右边界往后移动
            if (nums[i] - nums[i - 1] == 1) {
                later++;
            } else {  //当不连续时将当前窗口加入结果集，并开始新窗口
                if (pre == later) {
                    res.add(nums[pre] + "");
                } else {
                    res.add(nums[pre] + "->" + nums[later]);
                }
                pre = i;
                later = i;
            }
        }
        //处理最后边界
        if (pre == n - 1) {
            res.add(nums[pre] + "");
        }
        if (pre < n - 1) {
            res.add(nums[pre] + "->" + nums[later]);
        }
        return res;
    }

    /**
     * 1018. 可被 5 整除的二进制前缀
     *"https://leetcode-cn.com/problems/binary-prefix-divisible-by-5/"
     * 输入：[0,1,1]
     * 输出：[true,false,false]
     * 解释：
     * 输入数字为 0, 01, 011；也就是十进制中的 0, 1, 3 。只有第一个数可以被 5 整除，因此 answer[0] 为真。

     * @param A
     * @return
     */
    public static List<Boolean> prefixesDivBy5(int[] A) {
        List<Boolean> list = new ArrayList<Boolean>();
        int prefix = 0;
        int length = A.length;
        for (int i = 0; i < length; i++) {
            prefix = ((prefix << 1) + A[i]) % 5;
            list.add(prefix == 0);
        }
        return list;

    }

}