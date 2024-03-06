package com.scofen.l78z.xiaochuan.controller;

import com.alibaba.fastjson.JSON;
import com.scofen.l78z.xiaochuan.common.exception.ResultCode;
import com.scofen.l78z.xiaochuan.controller.request.BasketParam;
import com.scofen.l78z.xiaochuan.controller.request.UserParam;
import com.scofen.l78z.xiaochuan.controller.response.CategoryVO;
import com.scofen.l78z.xiaochuan.controller.response.Response;
import com.scofen.l78z.xiaochuan.dao.dataObject.BasketDO;
import com.scofen.l78z.xiaochuan.service.BasketService;
import com.scofen.l78z.xiaochuan.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: TODO
 * @Author gaofeng
 * @Date 2023/9/3 9:54 AM
 **/
@RestController
@RequestMapping("/basket")
public class BasketController extends BaseController {

    @Resource
    BasketService basketService;


    /**
     * 加入购物车
     *
     * @param
     * @return
     */
    @PostMapping("/add")
    public Response<Boolean> add(@RequestParam List<String> productIds) {
        String ip = getIp();
        BasketParam data = new BasketParam();
        data.setVisitId(ip);
        data.setProductIds(productIds);
        Boolean Response = basketService.add(data);
        return new Response<>().withData(Response);
    }

    /**
     * 从购物车 移除
     *
     * @param
     * @return
     */
    @PostMapping("/remove")
    public Response<Boolean> remove(@RequestParam List<String> productIds) {
        String ip = getIp();
        BasketParam data = new BasketParam();
        data.setVisitId(ip);
        data.setProductIds(productIds);
        Boolean Response = basketService.remove(data);
        return new Response<>().withData(Response);
    }



}
