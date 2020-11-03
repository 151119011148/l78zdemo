package com.scofen.algorithms.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        System.out.println(validMountainArray(new int[]{0,3,2,1}));
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
        List<Integer> temp = new ArrayList<>();
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

}
