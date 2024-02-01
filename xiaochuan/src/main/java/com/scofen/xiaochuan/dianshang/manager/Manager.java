package com.scofen.xiaochuan.dianshang.manager;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @Description: TODO
 * @Author gaofeng
 * @Date 2023/9/3 9:29 PM
 **/
public interface Manager {

    Object handle(String method, Object param, JSONObject access);

}
