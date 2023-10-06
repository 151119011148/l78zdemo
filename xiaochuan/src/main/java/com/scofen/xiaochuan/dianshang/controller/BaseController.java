package com.scofen.xiaochuan.dianshang.controller;

import com.scofen.xiaochuan.common.exception.ServiceException;
import com.scofen.xiaochuan.common.model.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: TODO
 * @Author gaofeng
 * @Date 2023/9/27 9:15 PM
 **/
@RestController
public class BaseController {
    @ExceptionHandler(ServiceException.class)
    public Result<?> handleMyException(ServiceException e) {
        // 处理异常的逻辑
        return new Result<>().withCode(e.getCode()).withMessage(e.getMessage());
    }
}
