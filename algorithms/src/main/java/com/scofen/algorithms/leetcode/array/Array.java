package com.scofen.algorithms.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Create by  GF  in  11:27 2020/12/12
 * Description:
 * Modified  By:
 */
public class Array {

    public static void main(String[] args) {

    }


    /**
     * 41. 缺失的第一个正数
     * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
     * 请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
     * 示例 1：
     * 输入：nums = [1,2,0]
     * 输出：3
     * <p>
     * 示例 2：
     * 输入：nums = [3,4,-1,1]
     * 输出：2
     * <p>
     * 示例 3：
     * 输入：nums = [7,8,9,11,12]
     * 输出：1
     */
    public int firstMissingPositive(int[] nums) {
        /**
         * 我们将数组中所有小于等于 0 的数修改为 N+1；
         * 我们遍历数组中的每一个数 x，它可能已经被打了标记，因此原本对应的数为|x|。如果 ∣x∣∈[1,N]，那么我们给数组中的第 ∣x∣−1 个位置的数添加一个负号。注意如果它已经有负号，不需要重复添加；
         * 在遍历完成之后，如果数组中的每一个数都是负数，那么答案是 N+1，否则答案是第一个正数的位置加 1。
         */
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            if (nums[i] <= 0) {
                nums[i] = n + 1;
            }
        }
        for (int i = 0; i < n; ++i) {
            int num = Math.abs(nums[i]);
            if (num <= n) {
                nums[num - 1] = -Math.abs(nums[num - 1]);
            }
        }
        for (int i = 0; i < n; ++i) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return n + 1;
    }

    /**
     * 118. 杨辉三角
     *
     */
    public List<List<Integer>> generate(int numRows) {
        // 初始化动态规划数组
        Integer[][] dp = new Integer[numRows][];
        // 遍历每一行
        for (int i = 0; i < numRows; i++) {
            // 初始化当前行
            dp[i] = new Integer[i + 1];
            // 每一行的第一个和最后一个元素总是 1
            dp[i][0] = dp[i][i] = 1;
            // 计算中间元素
            for (int j = 1; j < i; j++) {
                // 中间元素等于上一行的相邻两个元素之和
                dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
            }
        }

        // 将动态规划数组转换为结果列表
        List<List<Integer>> result = new ArrayList<>();
        for (Integer[] row : dp) {
            result.add(Arrays.asList(row));
        }
        // 返回结果列表
        return result;
    }

    /**
     * 119. 杨辉三角 II
     * "https://leetcode-cn.com/problems/pascals-triangle-ii/"
     * 输入: 3
     * 输出: [1,3,3,1]
     *
     * @param rowIndex
     * @return
     */
    public List<Integer> getRow(int rowIndex) {
        // 初始化动态规划数组
        Integer[][] dp = new Integer[rowIndex + 1][];
        // 遍历每一行
        for (int i = 0; i < rowIndex + 1; i++) {
            // 初始化当前行
            dp[i] = new Integer[i + 1];
            // 每一行的第一个和最后一个元素总是 1
            dp[i][0] = dp[i][i] = 1;
            // 计算中间元素，重复了一半，只遍历一半即可
            for (int j = 1; j < i/2 + 1; j++) {
                // 中间元素等于上一行的相邻两个元素之和
                dp[i][j] = dp[i][i - j] = dp[i - 1][j - 1] + dp[i - 1][j];
            }
        }
        return Arrays.asList(dp[rowIndex]);
    }

    /**
     * 189. 旋转数组
     * 给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
     * <p>
     * 示例 1:
     * 输入: nums = [1,2,3,4,5,6,7], k = 3
     * 输出: [5,6,7,1,2,3,4]
     * 解释:
     * 向右轮转 1 步: [7,1,2,3,4,5,6]
     * 向右轮转 2 步: [6,7,1,2,3,4,5]
     * 向右轮转 3 步: [5,6,7,1,2,3,4]
     * <p>
     * 示例 2:
     * 输入：nums = [-1,-100,3,99], k = 2
     * 输出：[3,99,-1,-100]
     * 解释:
     * 向右轮转 1 步: [99,-1,-100,3]
     * 向右轮转 2 步: [3,99,-1,-100]
     */
    public void rotate(int[] nums, int k) {
        //数组翻转，k=3
        //1 2 3 4 5 6 7
        //7 6 5 4 3 2 1
        //5 6 7 4 3 2 1
        //5 6 7 1 2 3 4
        k %= nums.length;
        reverseArray(nums, 0, nums.length - 1);
        reverseArray(nums, 0, k - 1);
        reverseArray(nums, k, nums.length - 1);
    }
    public void reverseArray(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }


    /**
     * 217. 存在重复元素
     * <p>
     * 给定一个整数数组，判断是否存在重复元素。
     * 如果任意一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，
     * 则返回 false 。
     *
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

    /**
     * 283. 移动0
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     * <p>
     * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
     * 示例 1:
     * 输入: nums = [0,1,0,3,12]
     * 输出: [1,3,12,0,0]
     * <p>
     * 示例 2:
     * 输入: nums = [0]
     * 输出: [0]
     */
    public void moveZeroes(int[] nums) {
        int n = nums.length, left = 0, right = 0;
        while (right < n) {
            if (nums[right] != 0) {
                swap(nums, left, right);
                left++;
            }
            right++;
        }
    }
    public void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

    /**
     * 376. 摆动序列
     *
     * @param nums
     * @return
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
     * 396. 旋转函数
     * 给定一个长度为 n 的整数数组 nums 。
     * 假设 arrk 是数组 nums 顺时针旋转 k 个位置后的数组，我们定义 nums 的 旋转函数  F 为：
     * F(k) = 0 * arrk[0] + 1 * arrk[1] + ... + (n - 1) * arrk[n - 1]
     * 返回 F(0), F(1), ..., F(n-1)中的最大值 。
     * 生成的测试用例让答案符合 32 位 整数。
     * 示例 1:
     * <p>
     * 输入: nums = [4,3,2,6]
     * 输出: 26
     * 解释:
     * F(0) = (0 * 4) + (1 * 3) + (2 * 2) + (3 * 6) = 0 + 3 + 4 + 18 = 25
     * F(1) = (0 * 6) + (1 * 4) + (2 * 3) + (3 * 2) = 0 + 4 + 6 + 6 = 16
     * F(2) = (0 * 2) + (1 * 6) + (2 * 4) + (3 * 3) = 0 + 6 + 8 + 9 = 23
     * F(3) = (0 * 3) + (1 * 2) + (2 * 6) + (3 * 4) = 0 + 2 + 12 + 12 = 26
     * 所以 F(0), F(1), F(2), F(3) 中的最大值是 F(3) = 26 。
     * 示例 2:
     * <p>
     * 输入: nums = [100]
     * 输出: 0
     */
    public int maxRotateFunction(int[] nums) {
//        F(0) = sum(num*idx for idx, num in enumerate(nums))
//        F(1) = F(0) + sum(nums) - nums[-1]*n
//        F(2) = F(1) + sum(nums) - nums[-2]*n
//        F(i) = F(i-1) + sum(nums) - nums[-i]*n 状态转移方程都出来了，那就 dp 安排一下
        int length = nums.length;
        int numSum = Arrays.stream(nums).sum();
        int[] dp = new int[length];
        dp[0] = 0;
        for (int i = 0; i < length; i++) {
            dp[0] += i * nums[i];
        }
        int result = dp[0];
        for (int i = 1; i < length; i++) {
            dp[i] = dp[i - 1] + numSum - nums[length - i] * length;
            result = Math.max(result, dp[i]);
        }
        return result;
    }

    /**
     * 414. 第三大的数
     * 给你一个非空数组，返回此数组中 第三大的数 。如果不存在，则返回数组中最大的数。
     *
     * 示例 1：
     * 输入：[3, 2, 1]
     * 输出：1
     * 解释：第三大的数是 1 。
     *
     * 示例 2：
     * 输入：[1, 2]
     * 输出：2
     * 解释：第三大的数不存在, 所以返回最大的数 2 。
     *
     * 示例 3：
     * 输入：[2, 2, 3, 1]
     * 输出：1
     * 解释：注意，要求返回第三大的数，是指在所有不同数字中排第三大的数。
     * 此例中存在两个值为 2 的数，它们都排第二。在所有不同数字中排第三大的数为 1 。
     *
     */
    public int thirdMax(int[] nums) {
        /**
         * 我们可以遍历数组，并用三个变量 a、b 和 c 来维护数组中的最大值、次大值和第三大值，以模拟方法二中的插入和删除操作。为方便编程实现，我们将其均初始化为小于数组最小值的元素，视作「无穷小」，比如 -2^{63}等。
         *
         * 遍历数组，对于数组中的元素 num：
         *
         * 若 num>a，我们将 c 替换为 b，bb 替换为 a，a 替换为 num，这模拟了将 num 插入有序集合，并删除有序集合中的最小值的过程；
         * 若 a>num>b，类似地，我们将 c 替换为 b，b 替换为 num，a 保持不变；
         * 若 b>num>c，类似地，我们将 c 替换为 num，a 和 b 保持不变；
         * 其余情况不做处理。
         * 遍历结束后，若 c 仍然为 −263-2^{63}−2
         * 63
         *  ，则说明数组中不存在三个或三个以上的不同元素，即第三大的数不存在，返回 a，否则返回 c。
         */
        long max = Long.MIN_VALUE, middle = Long.MIN_VALUE, min = Long.MIN_VALUE;
        for (long num : nums) {
            if (num > max) {
                min = middle;
                middle = max;
                max = num;
            } else if (max > num && num > middle) {
                min = middle;
                middle = num;
            } else if (middle > num && num > min) {
                min = num;
            }
        }
        return min == Long.MIN_VALUE ? (int) max : (int) min;
    }

    /**
     * 442. 数组中重复的数据
     * 给你一个长度为 n 的整数数组 nums ，其中 nums 的所有整数都在范围 [1, n] 内，且每个整数出现 一次 或 两次 。请你找出所有出现 两次 的整数，并以数组形式返回。
     * 你必须设计并实现一个时间复杂度为 O(n) 且仅使用常量额外空间的算法解决此问题。
     * 示例 1：
     * 输入：nums = [4,3,2,7,8,2,3,1]
     * 输出：[2,3]
     * <p>
     * 示例 2：
     * 输入：nums = [1,1,2]
     * 输出：[1]
     * <p>
     * 示例 3：
     * 输入：nums = [1]
     * 输出：[]
     */
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> list = new ArrayList<>();
        // 标记每个数字出现的次数，使用桶排序思想
        int[] bucket = new int[nums.length];
        for (int num : nums) {
            bucket[num - 1]++; // 将出现的数字对应的桶标记为出现过
        }

        // 找到没有被标记的桶，其编号对应的数字就是缺失的数字
        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] == 2) { // 如果桶未被标记
                list.add(i + 1); // 将桶的编号加一添加到结果列表中，即为缺失的数字
            }
        }
        return list;
    }

    /**
     * 448. 找到数组中消失的数字
     * <p>
     * 给你一个含 n 个整数的数组 nums ，其中 nums[i] 在区间 [1, n] 内。请你找出所有在 [1, n] 范围内但没有出现在 nums 中的数字，并以数组的形式返回结果。
     * 示例 1：
     * 输入：nums = [4,3,2,7,8,2,3,1]
     * 输出：[5,6]
     * <p>
     * 示例 2：
     * 输入：nums = [1,1]
     * 输出：[2]
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> list = new ArrayList<>();
        // 标记每个数字出现的次数，使用桶排序思想
        int[] bucket = new int[nums.length];
        for (int num : nums) {
            bucket[num - 1]++; // 将出现的数字对应的桶标记为出现过
        }

        // 找到没有被标记的桶，其编号对应的数字就是缺失的数字
        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] == 0) { // 如果桶未被标记
                list.add(i + 1); // 将桶的编号加一添加到结果列表中，即为缺失的数字
            }
        }
        return list;
    }

    /**
     * 453. 最小操作次数使数组元素相等
     * <p>
     * 给你一个长度为 n 的整数数组，每次操作将会使 n - 1 个元素增加 1 。返回让数组所有元素相等的最小操作次数。
     * 示例 1：
     * 输入：nums = [1,2,3]
     * 输出：3
     * 解释：
     * 只需要3次操作（注意每次操作会增加两个元素的值）：
     * [1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
     * <p>
     * 示例 2：
     * 输入：nums = [1,1,1]
     * 输出：0
     */
    public int minMoves(int[] nums) {
        //每次操作既可以理解为使 n−1 个元素增加 1，也可以理解使 1个元素减少 1
        int min = Arrays.stream(nums).min().getAsInt();
        int result = 0;
        for (int item : nums) {
            int count = item - min;
            result = result + count;
        }
        return result;

    }

    /**
     * 485. 最大连续1的个数
     * 给定一个二进制数组 nums ， 计算其中最大连续 1 的个数。
     * 示例 1：
     * 输入：nums = [1,1,0,1,1,1]
     * 输出：3
     * 解释：开头的两位和最后的三位都是连续 1 ，所以最大连续 1 的个数是 3.
     * <p>
     * 示例 2:
     * 输入：nums = [1,0,1,1,0,1]
     * 输出：2
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        int result = 0;
        int left = 0;
        int right = 0;
        int index = 0; //记录窗口中1的个数
        int length = nums.length;
        while(right < length){
            if (nums[left] == 1 && nums[right] == 1) {
                right ++; //扩大窗口
                index ++; //窗口个数加1
            }else {
                index = 0; //更新窗口中1的个数
                left = right; //左窗口移到右窗口的位置
                //按照不同的情况来判断右窗口是否移动
                if(nums[right] == 1){
                    right = right;
                }else{
                    right ++;
                }

            }
            result = Math.max(result, index);
        }
        return result;
    }

    /**
     * 495. 提莫攻击
     * 给你一个 非递减 的整数数组 timeSeries ，其中 timeSeries[i] 表示提莫在 timeSeries[i] 秒时对艾希发起攻击，以及一个表示中毒持续时间的整数 duration 。
     * 返回艾希处于中毒状态的 总 秒数。
     * 示例 1：
     *
     * 输入：timeSeries = [1,4], duration = 2
     * 输出：4
     * 解释：提莫攻击对艾希的影响如下：
     * - 第 1 秒，提莫攻击艾希并使其立即中毒。中毒状态会维持 2 秒，即第 1 秒和第 2 秒。
     * - 第 4 秒，提莫再次攻击艾希，艾希中毒状态又持续 2 秒，即第 4 秒和第 5 秒。
     * 艾希在第 1、2、4、5 秒处于中毒状态，所以总中毒秒数是 4 。
     *
     * 示例 2：
     * 输入：timeSeries = [1,2], duration = 2
     * 输出：3
     * 解释：提莫攻击对艾希的影响如下：
     * - 第 1 秒，提莫攻击艾希并使其立即中毒。中毒状态会维持 2 秒，即第 1 秒和第 2 秒。
     * - 第 2 秒，提莫再次攻击艾希，并重置中毒计时器，艾希中毒状态需要持续 2 秒，即第 2 秒和第 3 秒。
     * 艾希在第 1、2、3 秒处于中毒状态，所以总中毒秒数是 3 。
     */
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        /**
         * 我们记录艾希恢复为未中毒的起始时间 expired，设艾希遭遇第 ii 次的攻击的时间为 timeSeries[i]。
         * 当艾希遭遇第 i 攻击时：
         * 如果当前他正处于未中毒状态，则此时他的中毒持续时间应增加 duration，同时更新本次中毒结束时间 expired 等于 timeSeries[i]+duration；
         * 如果当前他正处于中毒状态，由于中毒状态不可叠加，我们知道上次中毒后结束时间为 expired，本次中毒后结束时间为 timeSeries[i]+duration，因此本次中毒增加的持续中毒时间为 timeSeries[i]+duration−expired；
         * 我们将每次中毒后增加的持续中毒时间相加即为总的持续中毒时间。
         */
        int result = 0;
        int expired = 0;
        for (int i = 0; i < timeSeries.length; ++i) {
            if (timeSeries[i] >= expired) {
                result += duration;
            } else {
                result += timeSeries[i] + duration - expired;
            }
            expired = timeSeries[i] + duration;
        }
        return result;
    }


    /**
     * 665. 非递减数列
     * <p>
     * 给你一个长度为 n 的整数数组 nums ，请你判断在 最多 改变 1 个元素的情况下，该数组能否变成一个非递减数列。
     * 我们是这样定义一个非递减数列的： 对于数组中任意的 i (0 <= i <= n-2)，总满足 nums[i] <= nums[i + 1]。
     * 示例 1:
     * 输入: nums = [4,2,3]
     * 输出: true
     * 解释: 你可以通过把第一个 4 变成 1 来使得它成为一个非递减数列。
     * <p>
     * 示例 2:
     * 输入: nums = [4,2,1]
     * 输出: false
     * 解释: 你不能在只改变一个元素的情况下将其变为非递减数列。
     */
    public boolean checkPossibility(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n - 1; ++i) {
            int x = nums[i], y = nums[i + 1];
            if (x > y) {
                nums[i] = y;
                if (isSorted(nums)) {
                    return true;
                }
                nums[i] = x; // 复原
                nums[i + 1] = x;
                return isSorted(nums);
            }
        }
        return true;
    }
    public boolean isSorted(int[] nums) {
        int n = nums.length;
        for (int i = 1; i < n; ++i) {
            if (nums[i - 1] > nums[i]) {
                return false;
            }
        }
        return true;
    }


    /**
     * 628. 三个数的最大乘积
     * 实际上只要求出数组中最大的三个数以及最小的两个数，因此我们可以不用排序，用线性扫描直接得出这五个数。
     */
    public int maximumProduct(int[] nums) {
        // 最小的和第二小的
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        // 最大的、第二大的和第三大的
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;

        for (int item : nums) {
            if (item < min1) {
                min2 = min1;
                min1 = item;
            } else if (item < min2) {
                min2 = item;
            }

            if (item > max1) {
                max3 = max2;
                max2 = max1;
                max1 = item;
            } else if (item > max2) {
                max3 = max2;
                max2 = item;
            } else if (item > max3) {
                max3 = item;
            }
        }

        return Math.max(min1 * min2 * max1, max1 * max2 * max3);
    }


}
