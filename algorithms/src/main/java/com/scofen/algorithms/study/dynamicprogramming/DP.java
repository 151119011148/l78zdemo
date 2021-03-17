/*
 * Copyright (c) 2001-2020 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.scofen.algorithms.study.dynamicprogramming;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 动规五部曲
 * 1. 确定dp数组（dp table）以及下标的含义
 * 2. 确定递推公式
 * 3. dp数组如何初始化
 * 4. 确定遍历顺序
 * 5. 举例推导dp数组
 *
 * @author weiyi
 * @version V1.0
 * @since 2020-12-17 10:03
 */
public class DP {

    public static void main(String[] args) {
        fib(0);
    }


    /**
     * 714. 买卖股票的最佳时机含手续费
     * @link "https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/"
     * 给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；
     * 非负整数 fee 代表了交易股票的手续费用。
     *
     * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。
     * 如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
     *
     * 返回获得利润的最大值。
     *
     * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，
     * 每笔交易你只需要为支付一次手续费。
     * @param prices
     * @param fee
     * @return
     */
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < n; ++i) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[n - 1][0];
    }

    /**
     *746. 使用最小花费爬楼梯
     * "https://leetcode-cn.com/problems/min-cost-climbing-stairs/"
     * 数组的每个索引作为一个阶梯，第 i个阶梯对应着一个非负数的
     * 体力花费值 cost[i](索引从0开始)。
     *
     * 每当你爬上一个阶梯你都要花费对应的体力花费值，然后你可以选择
     * 继续爬一个阶梯或者爬两个阶梯。
     *
     * 您需要找到达到楼层顶部的最低花费。在开始时，你可以选择
     * 从索引为 0 或 1 的元素作为初始阶梯。
     * @param cost
     * @return
     */
    public int minCostClimbingStairs(int[] cost) {
//        dp[i]=min(dp[i−1]+cost[i−1],dp[i−2]+cost[i−2])
        int n = cost.length;
        int[] dp = new int[n + 1];
        dp[0] = dp[1] = 0;
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[n];

    }

    /**
     *70. 爬楼梯
     *"https://leetcode-cn.com/problems/climbing-stairs/"
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     * 注意：给定 n 是一个正整数。
     * @param n
     * @return
     */
    public int climbStairs(int n) {
//        f(x)=f(x−1)+f(x−2)

        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];

    }

    /**
     *121. 买卖股票的最佳时机
     * "https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/"
     * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
     *
     * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），
     * 设计一个算法来计算你所能获取的最大利润。
     *
     * 注意：你不能在买入股票前卖出股票。
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int last = 0, profit = 0;
        for (int i = 0; i < prices.length - 1; ++ i) {
            last = Math.max(0, last + prices[i+1] - prices[i]);
            profit = Math.max(profit, last);
        }
        return profit;
    }

    /**
     * 53. 最大子序和
     * "https://leetcode-cn.com/problems/maximum-subarray/"
     * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），
     * 返回其最大和。
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int result;
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        result = dp[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            result = Math.max(result, dp[i]);
        }
        return result;

    }


    /**
     * 53. 最大子序和
     * "https://leetcode-cn.com/problems/maximum-subarray/"
     * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），
     * 返回其最大和。
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
/*        用 dp[i]dp[i] 表示前 ii 间房屋能偷窃到的最高总金额，那么就有如下的状态转移方程：
        dp[i]=max(dp[i−2]+nums[i],dp[i−1])
        边界条件为：
             {dp}[0] = {nums}[0]  只有一间房屋，则偷窃该房屋
             {dp}[1] = max({nums}[0], {nums}[1])  只有两间房屋，选择其中金额较高的房屋进行偷窃
*/

        if (nums == null || nums.length == 0) {
            return 0;
        }
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }
        int[] dp = new int[length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[length - 1];


    }

    /**
     * 188. 买卖股票的最佳时机 IV
     * ”https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv/“
     * 给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格。
     *
     * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
     *
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * @param k
     * @param prices
     * @return
     */
    public int maxProfit(int k, int[] prices) {
        if (prices.length == 0) {
            return 0;
        }

        int n = prices.length;
        k = Math.min(k, n / 2);
        int[][] buy = new int[n][k + 1];
        int[][] sell = new int[n][k + 1];

        buy[0][0] = -prices[0];
        sell[0][0] = 0;
        for (int i = 1; i <= k; ++i) {
            buy[0][i] = sell[0][i] = Integer.MIN_VALUE / 2;
        }

        for (int i = 1; i < n; ++i) {
            buy[i][0] = Math.max(buy[i - 1][0], sell[i - 1][0] - prices[i]);
            for (int j = 1; j <= k; ++j) {
                buy[i][j] = Math.max(buy[i - 1][j], sell[i - 1][j] - prices[i]);
                sell[i][j] = Math.max(sell[i - 1][j], buy[i - 1][j - 1] + prices[i]);
            }
        }

        return Arrays.stream(sell[n - 1]).max().getAsInt();
    }

    /**
     *509. 斐波那契数
     * "https://leetcode-cn.com/problems/fibonacci-number/"
     * 斐波那契数，通常用 F(n) 表示，形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，
     * 后面的每一项数字都是前面两项数字的和。也就是：
     *
     * F(0) = 0，F(1) = 1
     * F(n) = F(n - 1) + F(n - 2)，其中 n > 1
     */
    public static int fib(int n) {
        int[] dp = new int[n + 3];
        dp[0] = 0;
        dp[1] = dp[2] = 1;
        for (int i = 3; i <= n; i++){
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * 123. 买卖股票的最佳时机 III
     * “https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/”
     * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
     *
     * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
     *
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

     * @param prices
     * @return
     */
    public int maxProfit3(int[] prices) {
        int n = prices.length;
        int buy1 = -prices[0], sell1 = 0;
        int buy2 = -prices[0], sell2 = 0;
        for (int i = 1; i < n; ++i) {
            buy1 = Math.max(buy1, -prices[i]);
            sell1 = Math.max(sell1, buy1 + prices[i]);
            buy2 = Math.max(buy2, sell1 - prices[i]);
            sell2 = Math.max(sell2, buy2 + prices[i]);
        }
        return sell2;
    }

    /**
     * 646. 最长数对链
     * ”https://leetcode-cn.com/problems/maximum-length-of-pair-chain/“
     * 给出 n 个数对。 在每一个数对中，第一个数字总是比第二个数字小。
     *
     * 现在，我们定义一种跟随关系，当且仅当 b < c 时，数对(c, d) 才可以跟在 (a, b) 后面。我们用这种形式来构造一个数对链。
     *
     * 给定一个数对集合，找出能够形成的最长数对链的长度。你不需要用到所有的数对，你可以以任何顺序选择其中的一些数对来构造。

     * 示例：
     *
     * 输入：[[1,2], [2,3], [3,4]]
     * 输出：2
     * 解释：最长的数对链是 [1,2] -> [3,4]

     * @param pairs
     * @return
     */
    public int findLongestChain(int[][] pairs) {
        if (pairs == null || pairs.length <= 0 || pairs[0].length <= 0) {
            return 0;
        }
        // 1.定义排序：按照每个数对的start从小到大排序
        Arrays.sort(pairs, Comparator.comparingInt(pair -> pair[0]));
        int length = pairs.length;
        int[] dp = new int[length];
        // 2.初始状态
        Arrays.fill(dp, 1);
        dp[0] = 1;
        int allMax = Integer.MIN_VALUE;
        for (int i = 1; i < length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (pairs[i][0] > pairs[j][1]) {
                // 3.转移方程
                // 若dp[j]+1较大 ,则说明在dp[j]之后接上一个dp[i]数对能使得链更长
                // 若dp[i]较大,这说明在dp[j]之后接上一个dp[i]数对反而使数对更短
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            allMax = Math.max(allMax, dp[i]);
        }
        return allMax;
    }

    /**
     * leetcode：115. 不同的子序列
     *
     *"https://leetcode-cn.com/problems/distinct-subsequences/"
     * 输入：s = "rabbbit", t = "rabbit"
     * 输出：3
     * 解释：
     * 如下图所示, 有 3 种可以从 s 中得到 "rabbit" 的方案。
     * (上箭头符号 ^ 表示选取的字母)
     * rabbbit
     * ^^^^ ^^
     * rabbbit
     * ^^ ^^^^
     * rabbbit
     * ^^^ ^^^
     */
    public int numDistinct(String s, String t) {
        //1. 确定dp数组（dp table）以及下标的含义
        //dp[i][j]：以i-1为结尾的s子序列中出现以j-1为结尾的t的个数为dp[i][j]。
        int[][] dp = new int[s.length() + 1][t.length() + 1];
        //2. 确定递推公式
        //s[i - 1] 与 t[j - 1]相等,当s[i - 1] 与 t[j - 1]相等时，dp[i][j]可以有两部分组成。
        //一部分是用s[i - 1]来匹配，那么个数为dp[i - 1][j - 1]。
        //一部分是不用s[i - 1]来匹配，个数为dp[i - 1][j]。
        //dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];

        //s[i - 1] 与 t[j - 1] 不相等
        //dp[i][j] = dp[i - 1][j];


        //3. dp数组如何初始化
        //从递推公式dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j]; 和 dp[i][j] = dp[i - 1][j]; 中可以看出dp[i][0] 和dp[0][j]是一定要初始化的。
        //dp[i][0] 以i-1为结尾的s可以随便删除元素，出现空字符串的个数。
        //那么dp[i][0]一定都是1，因为也就是把以i-1为结尾的s，删除所有元素，出现空字符串的个数就是1。

        //再来看dp[0][j]，dp[0][j]：空字符串s可以随便删除元素，出现以j-1为结尾的字符串t的个数。
        //那么dp[0][j]一定都是0，s如论如何也变成不了t。

        //最后就要看一个特殊位置了，即：dp[0][0] 应该是多少。
        //dp[0][0]应该是1，空字符串s，可以删除0个元素，变成空字符串t。
        for (int i = 0; i <= s.length(); i++){
            dp[i][0] = 1;
        }
        for (int j = 1; j <= t.length(); j++){
            dp[0][j] = 0;
        }

        //4.确定遍历顺序
        //从递推公式dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j]; 和 dp[i][j] = dp[i - 1][j]; 中可以看出dp[i][j]都是根据左上方和正上方推出来的。
        //所以遍历的时候一定是从上到下，从左到右，这样保证dp[i][j]可以根据之前计算出来的数值进行计算。

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= t.length(); j++) {
                if (s.charAt(i - 1)==t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[s.length()][t.length()];

        //5. 举例推导dp数组.如果写出来的代码怎么改都通过不了，不妨把dp数组打印出来，看一看，是不是这样的

    }

}
