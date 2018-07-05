package com.processexample.process.utils;


import com.processexample.process.bean.response.Result;

/**
 * Create by  GF  in  15:24 2017/9/22
 * Description:
 * Modified  By:
 */
public class ResultUtil {
    public static Result success(Object object){
        Result result = new Result();
        result.setErrorCode(0);
        result.setMsg("success");
        result.setData(object);
        return result;
    }
    public static Result success(){
        return success(null);
    }


    public static Result error(Integer code,String msg){
        Result result = new Result();
        result.setErrorCode(code);
        result.setMsg(msg);
        return result;
    }


}
