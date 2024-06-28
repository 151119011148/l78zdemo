package com.scofen.util.mdesign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.scofen.util.translation.youdao.utils.HttpUtil;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MDesignUtil {

    public static JSONArray listChildren (String projectId, String modelId){
        Map<String, List<String>> params = new HashMap<String, List<String>>() {{
//            put("projectId", Lists.newArrayList("129qu38t-6w8-622"));
            put("projectId", Lists.newArrayList(projectId));
//            put("modelId", Lists.newArrayList("76254024350487552"));
            put("modelId", Lists.newArrayList(modelId));
        }};
        byte[] result = HttpUtil.doGet("http://localhost:8237/node/children", null, params,  "application/json");
        // 打印返回结果
        if (result != null) {
            JSONObject jsonObject = JSON.parseObject(new String(result, StandardCharsets.UTF_8));
            if (jsonObject.containsKey("data")){
                return jsonObject.getJSONArray("data");
            }
            throw new RuntimeException(jsonObject.getString("message"));
        }
        throw new RuntimeException("listChildren failed!");
    }

}
