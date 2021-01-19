/*
 * Copyright (c) 2001-2020 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.scofen.algorithms.study.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author weiyi
 * @version V1.0
 * @since 2020-12-14 11:06
 */
public class Strings {
    public static void main(String[] args) {
//        wordPattern("abba", "dog cat cat dog");

//        reverseString(new char[]{ 'h','e','l','l','o'});
//        reverse(9646321);

//        countAndSay(10);
        largeGroupPositions("abbxxxxzzy");

    }





    /**
     * "https://leetcode-cn.com/problems/group-anagrams/"
     *49. 字母异位词分组
     * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
     *
     * 示例:
     *
     * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
     * 输出:
     * [
     *   ["ate","eat","tea"],
     *   ["nat","tan"],
     *   ["bat"]
     * ]
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> ret = new HashMap<>();
        for (String item : strs){
            char[] chars = item.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            List list = ret.get(key);
            if (list == null || list.size() == 0){
                list = new ArrayList();
            }
                list.add(item);
                ret.put(key, list);
        }
        return new ArrayList<>(ret.values());
    }

    /**
     * "https://leetcode-cn.com/problems/word-pattern/"
     *290. 单词规律
     *
     */
    public static boolean wordPattern(String pattern, String s) {
        char[] keys = pattern.toCharArray();
        String[] values = s.split(" ");
        Map<Character, String> result1 = new HashMap<>();
        Map<String, Character> result2 = new HashMap<>();
        if (keys.length != values.length){
            return false;
        }
        for (int i = 0; i < keys.length; i ++){
            Character c = keys[i];
            String sv = values[i];
            if (!result1.containsKey(c)){
                result1.put(c, sv);
            }else {
                if (!result1.get(c).equals(sv)){
                    return false;
                }
            }
            if (!result2.containsKey(sv)){
                result2.put(sv, c);
            }else {
                if (!result2.get(sv).equals(c)){
                    return false;
                }
            }

        }
        return true;
    }

    /**
     *"https://leetcode-cn.com/problems/reverse-string/"
     * 344. 反转字符串
     *编写一个函数，其作用是将输入的字符串反转过来。
     * 输入字符串以字符数组 char[] 的形式给出。
     *
     * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间
     * 解决这一问题。
     *
     * 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。

     */
    public static void reverseString(char[] s) {
        int length = s.length;
        char temp;
        for (int i = 0; i < s.length / 2; i ++){
            temp = s[i];
            s[i] = s[length - i - 1];
            s[length - i - 1] = temp;
        }
    }

    /**
     *"https://leetcode-cn.com/problems/reverse-integer/"
     *
     * @param x
     * @return
     */
    public static int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) {
                return 0;
            }
            if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) {
                return 0;
            }
            rev = rev * 10 + pop;
        }
        return rev;
    }

    /**
     * 387. 字符串中的第一个唯一字符
     * "https://leetcode-cn.com/problems/first-unique-character-in-a-string/"
     * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。

     * 示例：
     *
     * s = "leetcode"
     * 返回 0
     *
     * s = "loveleetcode"
     * 返回 2

     * @param s
     * @return
     */
    public int firstUniqChar(String s) {
        HashMap<Character, Integer> count = new HashMap<Character, Integer>();
        int n = s.length();
        // build hash map : character and how often it appears
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            count.put(c, count.getOrDefault(c, 0) + 1);
        }

        // find the index
        for (int i = 0; i < n; i++) {
            if (count.get(s.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 389. 找不同
     * 给定两个字符串 s 和 t，它们只包含小写字母。
     * 字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。
     * 请找出在 t 中被添加的字母。
     * @param s
     * @param t
     * @return
     */
    public char findTheDifference(String s, String t) {
//        如果将两个字符串拼接成一个字符串，则问题转换成求字符串中出现奇数次的字符。
//        类似于「136. 只出现一次的数字」，我们使用位运算的技巧解决本题。
        int ret = 0;
        for (int i = 0; i < s.length(); ++i) {
            ret ^= s.charAt(i);
        }
        for (int i = 0; i < t.length(); ++i) {
            ret ^= t.charAt(i);
        }
        return (char) ret;
    }

    /**
     * "https://leetcode-cn.com/problems/valid-palindrome/"
     * 125. 验证回文串
     * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
     * 说明：本题中，我们将空字符串定义为有效的回文串。
     * 输入: "A man, a plan, a canal: Panama"
     * 输出: true
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
//        使用语言中的字符串翻转 API 得到sgood 的逆序字符串sgood_rev，
//        只要这两个字符串相同，那么 sgood 就是回文串。

        StringBuffer sgood = new StringBuffer();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char ch = s.charAt(i);
            if (Character.isLetterOrDigit(ch)) {
                sgood.append(Character.toLowerCase(ch));
            }
        }
        StringBuffer sgood_rev = new StringBuffer(sgood).reverse();
        return sgood.toString().equals(sgood_rev.toString());

    }


    /**
     * 8. 字符串转换整数 (atoi)
     * 请你来实现一个 atoi 函数，使其能将字符串转换成整数。
     *
     * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。接下来的转化规则如下：
     *
     * 如果第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字字符组合起来，形成一个有符号整数。
     * 假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成一个整数。
     * 该字符串在有效的整数部分之后也可能会存在多余的字符，那么这些字符可以被忽略，它们对函数不应该造成影响。
     * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换，即无法进行有效转换。
     *
     * 在任何情况下，若函数不能进行有效的转换时，请返回 0 。
     * @param str
     * @return
     */
    public int myAtoi(String str) {
        /*    例如，官方的str例子：" -42",就会进行如下的转换流程（请结合表格认真阅读，这对你理解“自动机”的概念很有帮助）：

    一开始，必然是开始阶段，所以从第0行，即[0, ?]
    第一个字符是空格，找到空格所在的列，即[?, 0]
    结合行、列，即[0, 0],发现将为下一个字符执行start阶段
    所以第二个字符还是从第0行开始，即[0, ?]
    第二个字符是空格，空格的列数是[?, 0]
    所以第三个字符的还是执行start阶段（[0, 0]）
        ...（空格的分析不再赘述）
    发现字符是负号-,而此时是在第0行（之前空格的原因），所以坐标是[0, 1]，
    那么可以下一个字符的执行阶段是signed，即第1列（[1, ?]）
    接下来的字符是字符型的4，则列数是[?, 2]
    所以坐标确定为[1, 2]，则下一个字符的执行阶段是in_number,即[2, ?]
    这次的字符还是字符型（2），则依旧定位到[?, 2]，则下一个字符执行in_number阶段
    没有字符了，遍历结束
    依据负号和数值,得出转换结果为-42*/
        Automaton automaton = new Automaton();
        int length = str.length();
        for (int i = 0; i < length; ++i) {
            automaton.get(str.charAt(i));
        }
        return (int) (automaton.sign * automaton.ans);

    }
    class Automaton {
        public int sign = 1;
        public long ans = 0;
        private String state = "start";
        private Map<String, String[]> table = new HashMap<String, String[]>() {{
            put("start", new String[]{"start", "signed", "in_number", "end"});
            put("signed", new String[]{"end", "end", "in_number", "end"});
            put("in_number", new String[]{"end", "end", "in_number", "end"});
            put("end", new String[]{"end", "end", "end", "end"});
        }};

        public void get(char c) {
            state = table.get(state)[get_col(c)];
            if ("in_number".equals(state)) {
                ans = ans * 10 + c - '0';
                ans = sign == 1 ? Math.min(ans, (long) Integer.MAX_VALUE) : Math.min(ans, -(long) Integer.MIN_VALUE);
            } else if ("signed".equals(state)) {
                sign = c == '+' ? 1 : -1;
            }
        }

        private int get_col(char c) {
            if (c == ' ') {
                return 0;
            }
            if (c == '+' || c == '-') {
                return 1;
            }
            if (Character.isDigit(c)) {
                return 2;
            }
            return 3;
        }
    }

    /**
     *"https://leetcode-cn.com/problems/implement-strstr/"
     * 28. 实现 strStr()
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        char[] hayArr = haystack.toCharArray();
        char[] needArr = needle.toCharArray();

        int i = 0; //主串(haystack)的位置
        int j = 0; //模式串(needle)的位置
        while (i < hayArr.length && j < needArr.length) {
            if (hayArr[i] == needArr[j]) { // 当两个字符相等则比较下一个
                i++;
                j++;
            } else {
                i = i - j + 1; // 一旦不匹配，i后退
                j = 0; // j归零
            }
        }
        if (j == needArr.length) { //说明完全匹配
            return i - j;
        } else {
            return -1;
        }
    }

    /**
     *
     * 1.     1
     * 2.     11
     * 3.     21
     * 4.     1211
     * 5.     111221
     * 第一项是数字 1
     * 描述前一项，这个数是 1 即 “ 一 个 1 ”，记作 "11"
     * 描述前一项，这个数是 11 即 “ 二 个 1 ” ，记作 "21"
     * 描述前一项，这个数是 21 即 “ 一 个 2 + 一 个 1 ” ，记作 "1211"
     * 描述前一项，这个数是 1211 即 “ 一 个 1 + 一 个 2 + 二 个 1 ” ，记作 "111221"
     * @param n
     * @return
     */
    public static String countAndSay(int n) {
        if (n <= 0) {
            return "";
        }
        if (n == 1) {
            return "1";
        }
        String[] dp = new String[n + 1];
        dp[1] = "1";
        dp[2] = "11";
        for (int i = 3; i <= n; i++) {
            dp[i] = describe(dp[i - 1]);
        }
        return dp[n];
    }

    private static String describe(String pre) {
        StringBuilder ans = new StringBuilder();
        int len = pre.length();
        int num = pre.charAt(0) - '0';
        int count = 1;
        for (int i = 1; i < len; i++) {
            if (pre.charAt(i) == pre.charAt(i - 1)) {
                count++;
            } else {
                ans.append(count);
                ans.append(num);
                num = pre.charAt(i) - '0';
                count = 1;
            }
            if (i == len - 1) {
                ans.append(count);
                ans.append(num);
            }
        }
        return ans.toString();
    }

    /**
     *”https://leetcode-cn.com/problems/longest-common-prefix/“
     * 14. 最长公共前缀
     * 编写一个函数来查找字符串数组中的最长公共前缀。
     * 如果不存在公共前缀，返回空字符串 ""。
     * 输入: ["flower","flow","flight"]
     * 输出: "fl"
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String prefix = strs[0];
        int count = strs.length;
        for (int i = 1; i < count; i++) {
            prefix = longestCommonPrefix(prefix, strs[i]);
            if (prefix.length() == 0) {
                break;
            }
        }
        return prefix;
    }

    public String longestCommonPrefix(String str1, String str2) {
        int length = Math.min(str1.length(), str2.length());
        int index = 0;
        while (index < length && str1.charAt(index) == str2.charAt(index)) {
            index++;
        }
        return str1.substring(0, index);
    }

    /**
     *316. 去除重复字母
     * "https://leetcode-cn.com/problems/remove-duplicate-letters/"
     *给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。
     * 需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
     * @param s
     * @return
     */
    public String removeDuplicateLetters(String s) {
/*
        // 声明变量

        for{    // 遍历字符串s
            // 初始化字母出现次数哈希表
        }

        for{    // 遍历字符串s
            if(该位置字母没有在栈中出现过){
                while(栈不为空 && 栈顶元素字典序列靠后 && 栈顶元素还有剩余出现次数){
                    // 修改出现状态
                    // 栈顶元素弹出
                }
                // 该位置的字母压栈
                // 修改出现状态
            }
            // 遍历过的字母出现次数减一
        }

        // 返回栈中元素
*/

        LinkedList<Character> deque = new LinkedList<>();
        int[] count = new int[26];
        boolean[] exists = new boolean[26];
        // 初始化
        for(char ch : s.toCharArray()){
            count[ch - 'a']++;
        }
        // 遍历s并入栈
        for (int i = 0; i < s.length(); i++) {
            char temp = s.charAt(i);
            if (!exists[temp - 'a']){
                while (!deque.isEmpty() && deque.getLast() > temp && count[deque.getLast() - 'a'] > 0){
                    exists[deque.getLast() - 'a'] = false;
                    deque.removeLast();
                }
                deque.add(temp);
                exists[temp - 'a'] = true;
            }
            count[temp - 'a']--;
        }
        //返回
        StringBuilder res = new StringBuilder();
        for(char ch : deque){
            res.append(ch);
        }
        return res.toString();
    }

    /**
     * 830. 较大分组的位置
     * "https://leetcode-cn.com/problems/positions-of-large-groups/"
     * 在一个由小写字母构成的字符串 s 中，包含由一些连续的相同字符所构成的分组。
     * 我们称所有包含大于或等于三个连续字符的分组为 较大分组 。
     * 找到每一个 较大分组 的区间，按起始位置下标递增顺序排序后，返回结果。
     * @param s
     * @return
     */
    public static List<List<Integer>> largeGroupPositions(String s) {
        List<List<Integer>> ret = new ArrayList<>();
        int length = s.length();
        int num = 1;
        for (int i = 0; i < length; i++) {
            if (i == length - 1 || s.charAt(i) != s.charAt(i + 1)) {
                if (num >= 3) {
                    ret.add(Arrays.asList(i - num + 1, i));
                }
                num = 1;
            } else {
                num++;
            }
        }
        return ret;
    }


}
