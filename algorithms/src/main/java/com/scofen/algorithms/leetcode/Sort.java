package com.scofen.algorithms.leetcode;

import com.google.common.collect.Lists;
import com.scofen.algorithms.utils.BitUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Create by  GF  in  22:04 2020/10/14
 * Description:
 * Modified  By:
 */
public class Sort {

    public static void main(String[] args) {
            //1002
/*        String[] param1002 = new String[]{"bella","label","roller"};
        System.out.println(commonChars(param1002));*/

            //1370
//        String a = "art";
//        System.out.println(sortString(a));
        //941
//        System.out.println(validMountainArray(new int[]{0,3,2,1}));

//        System.out.println(count(1232333, 3));

        //406
//        int[][] param = {{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}};
//        System.out.println(reconstructQueue(param));

    }



    /**
     * 1002
     * 给定仅有小写字母组成的字符串数组 A，返回列表中的每个字符串中都显示的全部字符（包括重复字符）组成的列表。例如，如果一个字符在每个字符串中出现 3 次，但不是 4 次，则需要在最终答案中包含该字符 3 次。
     * 你可以按任意顺序返回答案。
     * <p>
     * 示例 1：
     * 输入：["bella","label","roller"]
     * 输出：["e","l","l"]
     * 示例 2：
     * 输入：["cool","lock","cook"]
     * 输出：["c","o"]
     */
    public static List<String> commonChars(String[] A) {
        List<Map<String, Integer>> list = new ArrayList<>();
        List<String> result = new ArrayList<>();
        int size = A.length;
        for (int i = 0; i < size; i++) {
            char[] elements = A[i].toCharArray();
            Map<String, Integer> map = new HashMap<>();
            for (int j = 0; j < elements.length; j++) {
                String key = String.valueOf(elements[j]);
                Integer count = map.get(key);
                if (null == count) {
                    map.put(key, 1);
                } else {
                    map.put(key, count + 1);
                }
            }
            list.add(map);
        }

        for (int i = 97; i < 123; i++) {
            Integer min = 10;
            for (Map<String, Integer> map : list) {
                Integer count = map.get(String.valueOf((char) i));
                if (count == null) {
                    count = 0;
                }
                if (count < min) {
                    min = count;
                }
            }
            if (min != 0) {
                for (int j = 0; j < min; j++) {
                    result.add(String.valueOf((char) i));
                }
            }
        }
        return result;
    }


    /**
     * 1528
     * 给你一个字符串 s 和一个 长度相同 的整数数组 indices 。
     * 请你重新排列字符串 s ，其中第 i 个字符需要移动到 indices[i] 指示的位置。
     * 返回重新排列后的字符串。
     **/
    public String restoreString(String s, int[] indices) {
        char[] chars = s.toCharArray();
        char[] result = new char[chars.length];
        for (int i = 0; i < indices.length; i++) {
            char value = chars[i];
            int postion = indices[i];
            result[postion] = value;
        }
        return new String(result);
    }


    /**
     * 1370
     * <p>
     * 给你一个字符串 s ，请你根据下面的算法重新构造字符串：
     * 从 s 中选出 最小 的字符，将它 接在 结果字符串的后面。
     * 从 s 剩余字符中选出 最小 的字符，且该字符比上一个添加的字符大，将它 接在 结果字符串后面。
     * 重复步骤 2 ，直到你没法从 s 中选择字符。
     * 从 s 中选出 最大 的字符，将它 接在 结果字符串的后面。
     * 从 s 剩余字符中选出 最大 的字符，且该字符比上一个添加的字符小，将它 接在 结果字符串后面。
     * 重复步骤 5 ，直到你没法从 s 中选择字符。
     * 重复步骤 1 到 6 ，直到 s 中所有字符都已经被选过。
     * 在任何一步中，如果最小或者最大字符不止一个 ，你可以选择其中任意一个，并将其添加到结果字符串。
     * 请你返回将 s 中字符重新排序后的 结果字符串 。
     * <p>
     * 示例 1：
     * <p>
     * 输入：s = "aaaabbbbcccc"
     * 输出："abccbaabccba"
     * 解释：第一轮的步骤 1，2，3 后，结果字符串为 result = "abc"
     * 第一轮的步骤 4，5，6 后，结果字符串为 result = "abccba"
     * 第一轮结束，现在 s = "aabbcc" ，我们再次回到步骤 1
     * 第二轮的步骤 1，2，3 后，结果字符串为 result = "abccbaabc"
     * 第二轮的步骤 4，5，6 后，结果字符串为 result = "abccbaabccba"
     **/
    private static int[] arrays = new int[26];
    private static StringBuffer buffer = new StringBuffer();
    public static String sortString(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            arrays[chars[i] - 'a']++;
        }
        while (true) {
            if (!haveChar()){
                break;
            }
            for (int i = 0; i < 26; i++) {
                appendChar(i);
            }
            for (int i = 25; i >= 0; i--) {
                appendChar(i);
            }
        }
        return buffer.toString();

    }

    public static boolean haveChar() {
        for (int i = 0; i < 26; i++) {
            if (arrays[i] > 0) {
                return true;
            }
        }
        return false;
    }

    public static void appendChar(int p) {
        if (arrays[p] > 0) {
            arrays[p]--;
            buffer.append((char) (p + 'a'));
        }
    }

    public static char[] delAnyPosition(char[] arr, int position) {
        //判断是否合法
        if (position >= arr.length || position < 0) {
            return null;
        }
        char[] res = new char[arr.length - 1];
        for (int i = 0; i < res.length; i++) {
            if (i < position) {
                res[i] = arr[i];
            } else {
                res[i] = arr[i + 1];
            }
        }
        return res;
    }


    /**
     * 1480
     * 给你一个数组 nums 。数组「动态和」的计算公式为：runningSum[i] = sum(nums[0]…nums[i]) 。
     * 请返回 nums 的动态和。
     * 示例 1：
     * <p>
     * 输入：nums = [1,2,3,4]
     * 输出：[1,3,6,10]
     * 解释：动态和计算过程为 [1, 1+2, 1+2+3, 1+2+3+4] 。
     **/
    public int[] runningSum(int[] nums) {
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                result[i] = nums[i];
            } else {
                result[i] = result[i - 1] + nums[i];
            }

        }
        return result;
    }

    /**
     * 941. 有效的山脉数组
     * 让我们回顾一下，如果 A 满足下述条件，那么它是一个山脉数组：
     *
     * A.length >= 3
     * 在 0 < i < A.length - 1 条件下，存在 i 使得：
     * A[0] < A[1] < ... A[i-1] < A[i]
     * A[i] > A[i+1] > ... > A[A.length - 1]
     *
     **/
    public static boolean validMountainArray(int[] A) {
        int length = A.length;
        if(length < 3){
            return false;
        }
        int position = 0;
        for(int i = 1; i < length; i ++){
            int compare = A[i - 1] - A[i];
                if(compare < 0){
                    continue;
                }else{
                    position = i - 1;
                    break;
                }
            }
        if (A[0] >= A[position]) {
            return false;
        }
        for(int i = position + 1; i < length; i ++){
            int compare = A[i - 1] - A[i];
            if(compare > 0){
                continue;
            }else {
                return false;
            }
        }
        return true;

    }


    /**
     * 350. 两个数组的交集 II
     * 给定两个数组，编写一个函数来计算它们的交集。
     *
     输入：nums1 = [1,2,2,1], nums2 = [2,2]
     输出：[2,2]

     输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
     输出：[4,9]
     *
     **/
    public static int[] intersect(int[] nums1, int[] nums2) {
        if(nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0){
            return new int[0];
        }
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0, j = 0;
        Set<Integer> temp = new HashSet<>();
        while(i < nums1.length && j < nums2.length) {
            if(nums1[i] > nums2[j]){
                j++;
            } else if(nums1[i] < nums2[j]){
                i++;
            } else {
                temp.add(nums1[i]);
                i++;
                j++;
            }
        }
        int[] res = new int[temp.size()];
        int idx = 0;
        for(int x : temp) {
            res[idx++] = x;
        }
        return res;
    }


    /**
     * 1356. 根据数字二进制下 1 的数目排序
     * 给你一个整数数组 arr 。请你将数组中的元素按照其二进制表示中数字 1 的数目升序排序。
     * 如果存在多个数字二进制中 1 的数目相同，则必须将它们按照数值大小升序排列。
     * 请你返回排序后的数组。
     *
     输入：arr = [0,1,2,3,4,5,6,7,8]
     输出：[0,1,2,4,8,3,5,6,7]
     解释：[0] 是唯一一个有 0 个 1 的数。
     [1,2,4,8] 都有 1 个 1 。
     [3,5,6] 有 2 个 1 。
     [7] 有 3 个 1 。
     按照 1 的个数排序得到的结果数组为 [0,1,2,4,8,3,5,6,7]
    */
    public Integer[] sortByBits(int[] arr) {
        return Arrays.stream(arr).boxed()
                .sorted((o1, o2) -> {
                    if (BitUtil.countBit1(o1) < BitUtil.countBit1(o2)) {
                        return -1;
                    } else if (BitUtil.countBit1(o1) == BitUtil.countBit1(o1)) {
                        if (o1 < o2) {
                            return -1;
                        } else if (o1.equals(o2)) {
                            return 0;
                        } else {
                            return 1;
                        }
                    } else {
                        return 1;
                    }
                }).toArray(Integer[]::new);
    }



    /**
     * 122. 买卖股票的最佳时机 II
     * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
     设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
     注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

     输入: [7,1,5,3,6,4]
     输出: 7
     解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
          随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。


     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int result = 0;
        for(int i = 1; i <= prices.length - 1; i ++){
            if (prices[i] > prices[i -1]){
                result = result + prices[i] - prices[i -1];
            }
        }
        return result;
    }


    /**
     * @author 高锋
     * @description
     * 我们有一个由平面上的点组成的列表 points。需要从中找出 K 个距离原点 (0, 0) 最近的点。
     * （这里，平面上两点之间的距离是欧几里德距离。）
     * 你可以按任何顺序返回答案。除了点坐标的顺序之外，答案确保是唯一的。
     *
     * 输入：points = [[1,3],[-2,2]], K = 1
     * 输出：[[-2,2]]
     * 解释：
     * (1, 3) 和原点之间的距离为 sqrt(10)，
     * (-2, 2) 和原点之间的距离为 sqrt(8)，
     * 由于 sqrt(8) < sqrt(10)，(-2, 2) 离原点更近。
     * 我们只需要距离原点最近的 K = 1 个点，所以答案就是 [[-2,2]]。
     * @Date 11:22 2020/11/9
     * @Param [points, K]
     * @return int[][]
     **/
    public int[][] kClosest(int[][] points, int K) {
        Arrays.sort(points, Comparator.comparingInt(point -> (point[0] * point[0] + point[1] * point[1])));
        return Arrays.copyOfRange(points, 0, K);
    }


    /**
     * @author 高锋
     * @description
    给你一个数字数组 arr 。
    如果一个数列中，任意相邻两项的差总等于同一个常数，那么这个数列就称为 等差数列 。
    如果可以重新排列数组形成等差数列，请返回 true ；否则，返回 false 。
     **/
    public boolean canMakeArithmeticProgression(int[] arr) {
        if (arr.length < 3){
            return false;
        }
       Arrays.sort(arr);
       boolean result = false;
        for(int i = 1; i < arr.length - 1; i ++){
            if((arr[i] - arr[i - 1]) != (arr[i + 1] - arr[i])){
                return false;
            }else {
                result = true;
            }
        }
        return result;
    }



    /**
     * @author 高锋
     * @description
     * 给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。
     * 对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。
     * 你可以返回任何满足上述条件的数组作为答案。
     * @Date 10:12 2020/11/12
     * @Param [A]
     * @return int[]
     **/
    public int[] sortArrayByParityII(int[] A) {
        int[] result = new int[A.length];
        int evenIndex = 0;  // 偶数下标
        int oddIndex = 1;   // 奇数下标
        for (int i = 0; i < A.length; i++) {
            if (A[i] % 2 == 0) {
                result[evenIndex] = A[i];
                evenIndex += 2;
            }
            else {
                result[oddIndex] = A[i];
                oddIndex += 2;
            }
        }
        return result;

    }

    private static  int count(int number, int counter){
        int result = 0;
        List<Integer> numbers = Lists.newArrayList();
        int current = number;
        while(current > 0){
            numbers.add(current%10);
            current = current/10;
        }
        for(int i = 0; i < numbers.size(); i ++){
            if(numbers.get(i) == counter){
                result  = result + 1;
            }
        }
        return result;
    }

    Map<String, Long> validMap = new ConcurrentHashMap();
    private boolean valid(String ip){
        long now = System.currentTimeMillis();
        if(Objects.isNull(validMap.get(ip))){
            validMap.put(ip, now);
        }else{
            long oldTime = validMap.get(ip);
            if(oldTime - now > 30 * 60 * 1000 * 1000){
                validMap.put(ip, now);
            }else{
                return false;
            }
        }
        return true;
    }


    /**
     * 406. 根据身高重建队列
     * 假设有打乱顺序的一群人站成一个队列。
     * 每个人由一个整数对(h, k)表示，其中h是这个人的身高，
     * k是排在这个人前面且身高大于或等于h的人数。
     * 编写一个算法来重建这个队列。
     *
     *输入:
     * [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
     * 输出:
     * [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
     **/
    public static int[][] reconstructQueue(int[][] people) {
        if (people.length <= 1){
            return people;
        }
        //身高降序，k升序
        Arrays.sort(people, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0]);
        List<int[]> result = new ArrayList<>();
        //K值定义为 排在h前面且身高大于或等于h的人数
        //因为从身高降序开始插入，此时所有人身高都大于等于h
        //因此K值即为需要插入的位置
        for (int[] i : people) {
            int position = i[1];
            result.add(position, i);
        }
        return result.toArray(new int[result.size()][]);
    }



    
    
}
