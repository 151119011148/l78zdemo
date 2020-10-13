package com.scofen.designpattern.utils;

import lombok.Data;

/**
 * Create by  GF  in  15:15 2017/9/22
 * Description:Http请求返回的最外层对象
 * Modified  By:
 */
@Data
public class Result<T> extends ResultUtil {
    /**
     * 错误码
     */
    private Integer errorCode;
    /**
     * 提示信息
     */
    private String Msg;
    /**
     * 具体内容
     */
    private T data;


}