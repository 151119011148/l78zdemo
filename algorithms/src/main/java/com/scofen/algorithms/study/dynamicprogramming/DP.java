/*
 * Copyright (c) 2001-2020 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.scofen.algorithms.study.dynamicprogramming;

/**
 * TODO
 *
 * @author weiyi
 * @version V1.0
 * @since 2020-12-17 10:03
 */
public class DP {

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
        int pre = 0, maxAns = nums[0];
        for (int x : nums) {
            pre = Math.max(pre + x, x);
            maxAns = Math.max(maxAns, pre);
        }
        return maxAns;
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


}
