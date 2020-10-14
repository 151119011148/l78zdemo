package com.scofen.algorithms.leetcode;

import java.util.ArrayList;
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
        String[] param1002 = new String[]{"bella","label","roller"};
        System.out.println(commonChars(param1002));
    }
    /**
     * 1002
     * 给定仅有小写字母组成的字符串数组 A，返回列表中的每个字符串中都显示的全部字符（包括重复字符）组成的列表。例如，如果一个字符在每个字符串中出现 3 次，但不是 4 次，则需要在最终答案中包含该字符 3 次。
     你可以按任意顺序返回答案。
      
     示例 1：

     输入：["bella","label","roller"]
     输出：["e","l","l"]
     示例 2：

     输入：["cool","lock","cook"]
     输出：["c","o"]

     */
    public static List<String> commonChars(String[] A) {
        List<Map<String, Integer>> list = new ArrayList<>();
        List<String> result = new ArrayList<>();
        int size = A.length;
        for (int i = 0; i < size; i ++){
            char[] elements = A[i].toCharArray();
            Map<String, Integer> map = new HashMap<>();
            for (int j = 0; j < elements.length; j ++){
                String key = String.valueOf(elements[j]);
                Integer count = map.get(key);
                if (null == count){
                    map.put(key, 1);
                }else {
                    map.put(key, count + 1);
                }
            }
            list.add(map);
        }

        for(int i = 97; i < 123; i ++){
            Integer min = 10;
            for(Map<String, Integer> map : list){
                Integer count = map.get(String.valueOf((char)i));
                if (count == null){
                    count = 0;
                }
                if (count < min){
                    min = count;
                }
            }
            if (min != 0){
                for(int j = 0; j < min; j ++){
                    result.add(String.valueOf((char)i));
                }
            }
        }
        return result;
    }




}
