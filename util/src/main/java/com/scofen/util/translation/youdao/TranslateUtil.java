package com.scofen.util.translation.youdao;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.scofen.util.translation.youdao.utils.AuthV3Util;
import com.scofen.util.translation.youdao.utils.HttpUtil;

import javax.annotation.Nullable;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 网易有道智云翻译服务api调用demo
 * api接口: https://openapi.youdao.com/api
 */
public class TranslateUtil {


    private static final String APP_KEY = "724d6fcd9da5a371";     // 您的应用ID
    private static final String APP_SECRET = "xXivBMDw1ysxVxcSKAEJ6O1CDvUBDSIb";  // 您的应用密钥


    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println(doSingleTranslate("元模型"));
    }


    public static Map<String, String> doTranslate(List<String> qs) throws NoSuchAlgorithmException{
        return null;
    }




    public @Nullable static String doSingleTranslate(String q)  {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // 添加请求参数
        Map<String, List<String>> params = createRequestParams(q);
        // 添加鉴权相关参数
        try {
            AuthV3Util.addAuthParams(APP_KEY, APP_SECRET, params);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        // 请求api服务
        byte[] result = HttpUtil.doPost("https://openapi.youdao.com/api", null, params, "application/json");
        // 打印返回结果
        if (result != null) {
            JSONObject jsonObject = JSON.parseObject(new String(result, StandardCharsets.UTF_8));
            if (jsonObject.containsKey("translation")){
                return (String) jsonObject.getJSONArray("translation").get(0);
            }
            return "";
        }
        return "";

    }

    private static Map<String, List<String>> createRequestParams(String q) {
        /*
         * note: 将下列变量替换为需要请求的参数
         * 取值参考文档: https://ai.youdao.com/DOCSIRMA/html/%E8%87%AA%E7%84%B6%E8%AF%AD%E8%A8%80%E7%BF%BB%E8%AF%91/API%E6%96%87%E6%A1%A3/%E6%96%87%E6%9C%AC%E7%BF%BB%E8%AF%91%E6%9C%8D%E5%8A%A1/%E6%96%87%E6%9C%AC%E7%BF%BB%E8%AF%91%E6%9C%8D%E5%8A%A1-API%E6%96%87%E6%A1%A3.html
         */
        String from = "Chinese";
        String to = "English";

        return new HashMap<String, List<String>>() {{
            put("q", Lists.newArrayList(q));
            put("from", Lists.newArrayList(from));
            put("to", Lists.newArrayList(to));
        }};
    }
}
