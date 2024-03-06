package com.scofen.l78z.xiaochuan.util;

import com.alibaba.fastjson.JSON;
import com.google.common.base.CaseFormat;

import java.util.HashMap;
import java.util.Map;

public class FieldTransformUtil {

    /**
     * 转成下划线
     * @return
     */
    public static <T> T transform2Underscore(Map<String, Object> map, Class<T> clazz){
        Map<String, Object> result = new HashMap<>();
        map.forEach((key, value) -> {
            // 驼峰转下划线
            String newKey = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, key);
            result.put(newKey, value);
        });
        return JSON.parseObject(JSON.toJSONString(result), clazz);
    }

    /**
     * 转成驼峰
     * @return
     */
    public static <T> T  transform2LowerCamel(Map<String, Object> map, Class<T> clazz){
        Map<String, Object> result = new HashMap<>();
        map.forEach((key, value) -> {
            // 下划线转驼峰
            String newKey = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, key);
            result.put(newKey, value);
        });
        return JSON.parseObject(JSON.toJSONString(result), clazz);
    }

    public static void main(String[] args) {
        Map<String ,Object> map = new HashMap<>();
        map.put("testName", "testName");
        map.put("test_name", "test_name");
//        transform2Underscore(map, Map.class);
        transform2LowerCamel(map, Map.class);
    }


}
