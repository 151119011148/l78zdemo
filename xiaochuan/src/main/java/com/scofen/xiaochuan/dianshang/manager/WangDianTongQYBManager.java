package com.scofen.xiaochuan.dianshang.manager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.scofen.xiaochuan.common.exception.ServiceException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.google.common.collect.Lists;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.*;

/**
 * @Description: 旺店通企业版
 * @Author gaofeng
 * @Date 2023/9/3 9:49 AM
 **/

@Service
public class WangDianTongQYBManager implements Manager {


    @Override
    public List<JSONObject> handle(String method, Object param, JSONObject access) {
        if (StringUtils.equals(method, "detailOrder")) {
            return detailOrder((String) param, access);
        }
        return null;
    }

    private List<JSONObject> detailOrder(String orderId, JSONObject access) {

        List<JSONObject> result = Lists.newArrayList();
        JSONObject wuliuxinxi = new JSONObject();
        JSONObject orderInfo = (JSONObject) sendRequest("trade_query.php", orderId, access)
                .getJSONArray("trades")
                .get(0);
        wuliuxinxi.put("logistics_name", orderInfo.get("logistics_name"));
        wuliuxinxi.put("logistics_no", orderInfo.get("logistics_no"));
        result.add(wuliuxinxi);
        return result;
    }

    private JSONObject sendRequest(String url, String orderId, Map<String, Object> access){
        HashMap<String, String> param = new HashMap<>();
        param.put("src_tid", orderId);
        param.put("sid", (String) access.get("sid"));
        param.put("appkey", (String) access.get("appkey"));
        param.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000L));
        try {
            param.put("sign", signRequest(param, (String) access.get("appsecret")));
        } catch (IOException e) {
            throw new ServiceException("signRequest failed!", e);
        }

        String result;
        try {
            result = WebUtils.doPost("https://api.wangdian.cn/openapi2/" + url, param, "UTF-8",
                    connectTimeout, readTimeout, null);
        } catch (IOException e) {
            throw new ServiceException("sendRequest failed!", e);
        }
        return JSON.parseObject(result);
    }
    private int connectTimeout = 3000;//3秒
    private int readTimeout = 15000;//15秒

    /**
     * 给TOP请求签名。
     *
     * @param params 所有字符型的TOP请求参数
     * @param appsecret 签名密钥
     * @return 签名
     * @throws IOException
     */
    public static String signRequest(Map<String, String> params, String appsecret) throws IOException {
        // 第一步：检查参数是否已经排序
        String[] keys = params.keySet().toArray(new String[0]);
        Arrays.sort(keys);

        // 第二步：把所有参数名和参数值串在一起
        StringBuilder query = new StringBuilder();
        for (String key : keys) {
            if("sign".equals(key))
                continue;

            if(query.length() > 0)
                query.append(';');

            int len = key.length();
            query.append(String.format("%02d", len)).append('-').append(key).append(':');

            String value = params.get(key);

            len = value.length();
            query.append(String.format("%04d", len)).append('-').append(value);

        }

        query.append(appsecret);

        // 第三步：使用MD5加密
        byte[] bytes = encryptMD5(query.toString());

        // 第四步：把二进制转化为大写的十六进制
        return byte2hex(bytes);
    }
    private static byte[] encryptMD5(String data) throws IOException {
        byte[] bytes = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            bytes = md.digest(data.getBytes("UTF-8"));
        } catch (GeneralSecurityException gse) {
            throw new IOException();
        }
        return bytes;
    }

    private static String byte2hex(byte[] bytes) {
        StringBuilder sign = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                //保证所有的16进制都是两位：00-ff，其中[80~ff]代表[-128,-1]
                sign.append("0");
            }
            sign.append(hex);
        }
        return sign.toString();
    }


}
