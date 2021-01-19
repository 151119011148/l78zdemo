package com.scofen.algorithms.study.heap;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author 高锋
 * @className: HeapSort
 * @description: TODO
 * @date 2020/11/2716:26
 */
public class HeapSort {

    public static void main(String[] args) {
        lastStoneWeight(new int[]{2,7,4,1,8,1});
    }



    /**
     * 451. 根据字符出现频率排序
     给定一个字符串，请将字符串里的字符按照出现的频率降序排列。

     输入:
     "binarytree"

     输出:
     "eert"

     解释:
     'e'出现两次，'r'和't'都只出现一次。
     因此'e'必须出现在'r'和't'之前。此外，"eetr"也是一个有效的答案。
     */
    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (Character c : s.toCharArray()){
            map.put(c, map.getOrDefault(c,0) + 1);
        }
        ArrayList<Map.Entry<Character, Integer>> entries = new ArrayList<>(map.entrySet());
        entries.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Character, Integer> entry : entries) {
            for (Integer i = 0; i < entry.getValue(); i++) {
                sb.append(entry.getKey());
            }
        }
        return sb.toString();

    }

    /**
     * 剑指 Offer 40. 最小的k个数
     输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，
     则最小的4个数字是1、2、3、4。

     输入：arr = [3,2,1], k = 2
     输出：[1,2] 或者 [2,1]
     */
    public int[] getLeastNumbers(int[] arr, int k) {
        Arrays.sort(arr);
        return Arrays.copyOf(arr, k);
    }



    /**
     * 1439. 有序矩阵中的第 k 个最小数组和
     给你一个 m * n 的矩阵 mat，以及一个整数 k ，矩阵中的每一行都以非递减的顺序排列。
     你可以从每一行中选出 1 个元素形成一个数组。返回所有可能数组中的第 k 个 最小 数组和。

     输入：mat = [[1,3,11],[2,4,6]], k = 5
     输出：7
     解释：从每一行中选出一个元素，前 k 个和最小的数组分别是：
     [1,2], [1,4], [3,2], [3,4], [1,6]。其中第 5 个的和是 7 。
     */
/*    public int kthSmallest(int[][] mat, int k) {
        List<Integer> result = new ArrayList<>();
        int sum = 0;
        for (int i = 0; i < mat.length; i ++){
            int num    
        }

    }*/


    /**
     * 767. 重构字符串
     给定一个字符串S，检查是否能重新排布其中的字母，使得两相邻的字符不同。

     若可行，输出任意可行的结果。若不可行，返回空字符串。

     输入: S = "aab"
     输出: "aba"

     输入: S = "aaab"
     输出: ""
     **/
    public static String reorganizeString(String S) {

//        return solution1(S);
        return solution2(S);

    }

    private static String solution1(String S) {
//把字符串S转化为字符数组
        char[] alphabetArr = S.toCharArray();
        //记录每个字符出现的次数
        int[] alphabetCount = new int[26];
        //字符串的长度
        int length = S.length();
        //统计每个字符出现的次数
        for (int i = 0; i < length; i++) {
            alphabetCount[alphabetArr[i] - 'a']++;
        }
        int max = 0, alphabet = 0, threshold = (length + 1) >> 1;
        //找出出现次数最多的那个字符
        for (int i = 0; i < alphabetCount.length; i++) {
            if (alphabetCount[i] > max) {
                max = alphabetCount[i];
                alphabet = i;
                //如果出现次数最多的那个字符的数量大于阈值，说明他不能使得
                // 两相邻的字符不同，直接返回空字符串即可
                if (max > threshold) {
                    return "";
                }
            }
        }
        //到这一步说明他可以使得两相邻的字符不同，我们随便返回一个结果，res就是返回
        //结果的数组形式，最后会再转化为字符串的
        char[] res = new char[length];
        int index = 0;
        //先把出现次数最多的字符存储在数组下标为偶数的位置上
        while (alphabetCount[alphabet]-- > 0) {
            res[index] = (char) (alphabet + 'a');
            index += 2;
        }
        //然后再把剩下的字符存储在其他位置上
        for (int i = 0; i < alphabetCount.length; i++) {
            while (alphabetCount[i]-- > 0) {
                if (index >= res.length) {
                    index = 1;
                }
                res[index] = (char) (i + 'a');
                index += 2;
            }
        }
        return new String(res);
    }

    private static String solution2(String S) {
        /*        这道题是典型的贪心算法的题。重构字符串时，需要根据每个字母在字符串中出现的次数
            处理每个字母放置的位置。如果出现次数最多的字母可以在重新排布之后不相邻，
            则可以重新排布字母使得相邻的字母都不相同。如果出现次数最多的字母过多，
            则无法重新排布字母使得相邻的字母都不相同。

        假设字符串的长度为 nn，如果可以重新排布成相邻的字母都不相同的字符串，
        每个字母最多出现多少次？

        当 nn 是偶数时，有 n/2n/2 个偶数下标和 n/2n/2 个奇数下标，因此每个字母的出现次数都
        不能超过 n/2n/2 次，否则出现次数最多的字母一定会出现相邻。

        当 nn 是奇数时，由于共有 (n+1)/2(n+1)/2 个偶数下标，因此每个字母的出现次数都不能
        超过 (n+1)/2(n+1)/2 次，否则出现次数最多的字母一定会出现相邻。

        由于当 nn 是偶数时，在整数除法下满足 n/2n/2 和 (n+1)/2(n+1)/2 相等，因此可以合并 nn
        是偶数与 nn 是奇数的情况：如果可以重新排布成相邻的字母都不相同的字符串，
        每个字母最多出现 (n+1)/2(n+1)/2 次。

        因此首先遍历字符串并统计每个字母的出现次数，如果存在一个字母的出现次数大于
         (n+1)/2(n+1)/2，则无法重新排布字母使得相邻的字母都不相同，返回空字符串。
         如果所有字母的出现次数都不超过 (n+1)/2(n+1)/2，则考虑如何重新排布字母。

        以下提供两种使用贪心算法的方法，分别基于最大堆和计数。

        方法一：基于最大堆的贪心算法
        维护最大堆存储字母，堆顶元素为出现次数最多的字母。首先统计每个字母的出现次数，
        然后将出现次数大于 00 的字母加入最大堆。

        当最大堆的元素个数大于 11 时，每次从最大堆取出两个字母，拼接到重构的字符串，
        然后将两个字母的出现次数分别减 11，并将剩余出现次数大于 00 的字母重新加入最大堆。
        由于最大堆中的元素都是不同的，因此取出的两个字母一定也是不同的，将两个不同的
        字母拼接到重构的字符串，可以确保相邻的字母都不相同。

        如果最大堆变成空，则已经完成字符串的重构。如果最大堆剩下 11 个元素，则取出最后一个
        字母，拼接到重构的字符串。

        对于长度为 nn 的字符串，共有 n/2n/2 次每次从最大堆取出两个字母的操作，当 nn 是奇数时，
        还有一次从最大堆取出一个字母的操作，因此重构的字符串的长度一定是 nn。

        当 nn 是奇数时，是否可能出现重构的字符串的最后两个字母相同的情况？如果最后一个
        字母在整个字符串中至少出现了 22 次，则在最后一次从最大堆取出两个字母时，该字母
        会先被选出，因此不会成为重构的字符串的倒数第二个字母，也不可能出现重构的
        字符串最后两个字母相同的情况。

        因此，在重构字符串可行的情况下，基于最大堆的贪心算法可以确保得到正确答案。*/
        if (S.length() < 2) {
            return S;
        }
        int[] counts = new int[26];
        int maxCount = 0;
        int length = S.length();
        for (int i = 0; i < length; i++) {
            char c = S.charAt(i);
            counts[c - 'a']++;
            maxCount = Math.max(maxCount, counts[c - 'a']);
        }
        if (maxCount > (length + 1) / 2) {
            return "";
        }
        PriorityQueue<Character> queue = new PriorityQueue<>((letter1, letter2) -> counts[letter2 - 'a'] - counts[letter1 - 'a']);
        for (char c = 'a'; c <= 'z'; c++) {
            if (counts[c - 'a'] > 0) {
                queue.offer(c);
            }
        }
        StringBuffer sb = new StringBuffer();
        while (queue.size() > 1) {
            char letter1 = queue.poll();
            char letter2 = queue.poll();
            sb.append(letter1);
            sb.append(letter2);
            int index1 = letter1 - 'a', index2 = letter2 - 'a';
            counts[index1]--;
            counts[index2]--;
            if (counts[index1] > 0) {
                queue.offer(letter1);
            }
            if (counts[index2] > 0) {
                queue.offer(letter2);
            }
        }
        if (queue.size() > 0) {
            sb.append(queue.poll());
        }
        return sb.toString();

    }

    /**
     * 1046. 最后一块石头的重量
     * ”https://leetcode-cn.com/problems/last-stone-weight/“
     *有一堆石头，每块石头的重量都是正整数。
     *
     * 每一回合，从中选出两块 最重的 石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
     *
     * 如果 x == y，那么两块石头都会被完全粉碎；
     * 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
     * 最后，最多只会剩下一块石头。返回此石头的重量。如果没有石头剩下，就返回 0。
     * @param stones
     * @return
     */
    public static int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int stone : stones) {
            pq.offer(stone);
        }
        System.out.println(JSON.toJSONString(pq));
        while (pq.size() > 1) {
            int a = pq.poll();
            int b = pq.poll();
            if (a > b) {
                pq.offer(a - b);
            }
        }
        return pq.isEmpty() ? 0 : pq.poll();
    }




}
