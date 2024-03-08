package com.scofen.l78z.xiaochuan.dianshang.controller;

import com.scofen.l78z.xiaochuan.common.model.AccessParam;
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
