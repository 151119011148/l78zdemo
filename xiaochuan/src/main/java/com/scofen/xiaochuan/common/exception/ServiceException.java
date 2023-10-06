package com.scofen.xiaochuan.common.exception;

import lombok.Data;

/**
 * @Description: TODO
 * @Author gaofeng
 * @Date 2023/9/27 8:53 PM
 **/
@Data
public class ServiceException extends RuntimeException {

    private String code;

    public ServiceException(String code, String message) {
        super(message);
        this.code = code;
    }
    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
