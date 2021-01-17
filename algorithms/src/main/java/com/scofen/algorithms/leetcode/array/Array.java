package com.scofen.algorithms.leetcode.array;

import java.util.Arrays;

/**
 * Create by  GF  in  11:27 2020/12/12
 * Description:
 * Modified  By:
 */
public class Array {

    public static void main(String[] args) {

    }


    /**
     * 376. 摆动序列
     * @link {https://leetcode-cn.com/problems/wiggle-subsequence/}
     * 如果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为摆动序列。
     * 第一个差（如果存在的话）可能是正数或负数。少于两个元素的序列也是摆动序列。
     * <p>
     * 例如， [1,7,4,9,2,5] 是一个摆动序列，因为差值 (6,-3,5,-7,3) 是正负交替出现的。相反, [1,4,7,2,5] 和 [1,7,4,5,5]
     * 不是摆动序列，第一个序列是因为它的前两个差值都是正数，第二个序列是因为它的最后一个
     * 差值为零。
     * <p>
     * 给定一个整数序列，返回作为摆动序列的最长子序列的长度。 通过从原始序列中删除一些
     * （也可以不删除）元素来获得子序列，剩下的元素保持其原始顺序。
     *
     * @param nums
     * @return
     */
    public int wiggleMaxLength(int[] nums) {
        if (nums.length == 0) return 0;
        int count = 1, up = 0, down = 0;
        for (int i = 1; i < nums.length; i++) {
            int sub = nums[i] - nums[i - 1];
            if (sub > 0 && up == 0) {
                count++;
                up = 1;
                down = 0;
            }
            if (sub < 0 && down == 0) {
                count++;
                down = 1;
                up = 0;
            }
        }
        return count;
    }


    /**
     * 217. 存在重复元素
     *
     给定一个整数数组，判断是否存在重复元素。
     如果任意一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，
     则返回 false 。
     * @param nums
     * @return
     */
    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }
        return false;
    }


}
