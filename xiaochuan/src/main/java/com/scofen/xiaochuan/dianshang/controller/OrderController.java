package com.scofen.xiaochuan.dianshang.controller;

import com.alibaba.fastjson.JSONObject;
import com.scofen.xiaochuan.common.model.Result;
import com.scofen.xiaochuan.dianshang.service.OrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import static com.scofen.xiaochuan.common.constant.ConnectorServiceEnum.WANG_DIAN_TONG;

/**
 * @Description: TODO
 * @Author gaofeng
 * @Date 2023/9/3 9:54 AM
 **/
@RestController
@RequestMapping("/dianshang/order")
public class OrderController extends BaseController {

    @Resource
    OrderService orderService;

    /**
     * 通过订单查询物流信息
     *
     * @param
     * @return
     */
    @PostMapping("/get")
    public Result<List<?>> get(@RequestBody OrderParam param) {
        if (StringUtils.isEmpty(param.getConnectorKey())){
            param.setConnectorKey(WANG_DIAN_TONG.getConnectorId());
            // TODO: 2023/9/4
            param.setAccessKey("");
        }
        List<JSONObject> orders = orderService.detailOrder(param);
        return new Result<>().withData(orders);
    }



}
