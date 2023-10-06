package com.scofen.xiaochuan.wuliu.controller;

import com.alibaba.fastjson.JSONObject;
import com.scofen.xiaochuan.common.model.Result;
import com.scofen.xiaochuan.wuliu.service.WuliuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

import static com.scofen.xiaochuan.common.constant.ConnectorServiceEnum.KUAI_DI_100;

/**
 * @Description: TODO
 * @Author gaofeng
 * @Date 2023/9/3 9:54 AM
 **/
@RestController
@RequestMapping("/wuliu")
public class WuliuController {

    @Resource
    WuliuService wuliuService;

    /**
     * 通过物流信息查询物流轨迹
     *
     * @param
     * @return
     */
    @GetMapping("/trace/get")
    public Result<List<?>> get(@RequestParam WuLiuParam param) {
        if (StringUtils.isEmpty(param.getConnectorKey())){
            param.setConnectorKey(KUAI_DI_100.getConnectorId());
            // TODO: 2023/9/4
            param.setAccessKey("");
        }
        List<JSONObject> guijis = wuliuService.trace(param);
        return new Result<>().withData(guijis);
    }



}
