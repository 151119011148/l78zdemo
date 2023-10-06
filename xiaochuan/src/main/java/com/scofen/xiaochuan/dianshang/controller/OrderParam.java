package com.scofen.xiaochuan.dianshang.controller;

import com.scofen.xiaochuan.common.model.AccessParam;
import lombok.Data;

/**
 * @Description: TODO
 * @Author gaofeng
 * @Date 2023/9/3 11:05 AM
 **/
@Data
public class OrderParam extends AccessParam {

    /**
     * 订单id
     */
    private String orderId;

}
