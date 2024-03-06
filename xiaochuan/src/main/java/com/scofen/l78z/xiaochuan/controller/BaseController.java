package com.scofen.l78z.xiaochuan.controller;

import com.scofen.l78z.xiaochuan.common.exception.ServiceException;
import com.scofen.l78z.xiaochuan.controller.response.Response;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: TODO
 * @Author gaofeng
 * @Date 2023/9/27 9:15 PM
 **/
@RestController
public class BaseController {

    @ExceptionHandler(ServiceException.class)
    public Response<?> handleMyException(ServiceException e) {
        // 处理异常的逻辑
        return new Response<>().withErrorCode(e.getCode()).withErrorMsg(e.getMessage());
    }

    //获取请求对象
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return null;
        } else {
            return requestAttributes.getRequest();
        }
    }

    //获取请求的ip地址
    public static String getIp() {
        HttpServletRequest request = getRequest();
        if (request == null) {
            return "127.0.0.1";
        } else {
            return request.getRemoteHost();
        }
    }
}
