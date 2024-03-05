package com.scofen.algorithms.leetcode.dp;

import java.util.ArrayDeque;
import java.util.Deque;

public class DP {


    /**
     * 1691. 跳跃游戏 VI
     *      给你一个下标从 0 开始的整数数组 nums 和一个整数 k 。
     *      一开始你在下标 0 处。每一步，你最多可以往前跳 k 步，但你不能跳出数组的边界。也就是说，你可以从下标 i 跳到 [i + 1， min(n - 1,
     *     i + k)] 包含 两个端点的任意位置。
     *      你的目标是到达数组最后一个位置（下标为 n - 1 ），你的 得分 为经过的所有数字之和。
     *      请你返回你能得到的 最大得分 。
     *
     *      示例 1：
     *     输入：nums = [1,-1,-2,4,-7,3], k = 2
     *     输出：7
     *     解释：你可以选择子序列 [1,-1,4,3] （上面加粗的数字），和为 7 。
     *
     *      示例 2：
     *     输入：nums = [10,-5,-2,4,0,3], k = 3
     *     输出：17
     *     解释：你可以选择子序列 [10,4,3] （上面加粗数字），和为 17 。
     *
     *      示例 3：
     *     输入：nums = [1,-5,-20,4,-1,3,-6,-3], k = 2
     *     输出：0
     */
    public int maxResult(int[] nums, int k) {

//        当 i<k 时，我们计算的是 f[0],f[1],⋯,f[i−1] 的最大值
//        当 i≥k时，我们计算的是 f[i−k],f[i−k+1],⋯,f[i−1] 的最大值

//        我们维护一个 f 值从左到右严格递减的单调队列（双端队列）。在计算 f[i] 时，需要保证队首就是转移来源最大值的下标
//        出：如果队首小于 i−k，则弹出队首。注意单调队列只需保存下标。
//        转移：f[i]=f[q[0]]+nums[i]。其中 q[0] 表示单调队列 q 的队首，此时队首就是转移来源最大值的下标。
//        入：不断弹出队尾，直到队列为空，或者 f[i]小于队尾对应的 f 值为止。然后把 i 加到队尾。

        int n = nums.length;
        int[] f = new int[n];
        f[0] = nums[0];
        Deque<Integer> q = new ArrayDeque<>();
        q.add(0);
        for (int i = 1; i < n; i++) {
            // 1. 出
            if (q.peekFirst() < i - k) {
                q.pollFirst();
            }
            // 2. 转移
            f[i] = f[q.peekFirst()] + nums[i];
            // 3. 入
            while (!q.isEmpty() && f[i] >= f[q.peekLast()]) {
                q.pollLast();
            }
            q.add(i);
        }
        return f[n - 1];

    }
}
