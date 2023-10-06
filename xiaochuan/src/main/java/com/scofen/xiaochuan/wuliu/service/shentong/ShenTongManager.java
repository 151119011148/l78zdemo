package com.scofen.xiaochuan.wuliu.service.shentong;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.scofen.xiaochuan.dianshang.manager.Manager;
import com.scofen.xiaochuan.util.Md5Util;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 申通快递
 * @Author gaofeng
 * @Date 2023/9/3 9:49 AM
 **/

@Service
public class ShenTongManager implements Manager {


    @Override
    public List<JSONObject> handle(String method, Object param, JSONObject access) {
        if (StringUtils.equals(method, "trace")) {
            return trace((Map<String, String>) param, access);
        }
        return null;
    }

    private List<JSONObject> trace(Map<String, String> map, JSONObject access) {
        Map<String, String> params = new HashMap<>();
        // TODO: 2023/9/4  logistics_name -> bianma¬
        params.put("com", map.get("logistics_name"));
        params.put("num", map.get("logistics_no"));

        OkHttpClient client = new OkHttpClient();
        // 构建请求体
        String param = JSON.toJSONString(params);
        String key = access.getString("key");
        String customer = access.getString("customer");
        RequestBody requestBody = new FormBody.Builder()
                .add("param", param)
                .add("customer", access.getString("customer"))
                .add("sign", getSign(param, key, customer))
                .build();

        // 构建请求对象
        Request request = new Request.Builder()
                .url("https://poll.kuaidi100.com/poll/query.do")
                .post(requestBody)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();

        JSONObject result = new JSONObject();
        // 发送请求并获取响应
        try (Response response = client.newCall(request).execute()) {
            // 处理响应
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                result = JSON.parseObject(responseBody);
            } else {
                System.out.println("Request failed: " + response.code());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Lists.newArrayList(result);
    }

    private String getSign(String param, String key, String customer) {
        return Md5Util.md5(param + key + customer, null)
                .toUpperCase();
    }


}
