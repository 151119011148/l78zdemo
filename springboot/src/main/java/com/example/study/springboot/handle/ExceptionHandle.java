package com.scofen.study.springboot.handle;


import com.scofen.study.springboot.utils.Result;
import com.scofen.study.springboot.utils.ResultUtil;
import com.scofen.study.springboot.exception.GirlException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Create by  GF  in  15:55 2017/9/22
 * Description:
 * Modified  By:
 */

@ControllerAdvice
@Slf4j
public class ExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e){
        if (e instanceof GirlException){
            GirlException girlException = (GirlException) e;
            return ResultUtil.error(girlException.getCode(),girlException.getMessage());
        }else {
            log.error("【系统异常】:{}",e);
            return ResultUtil.error(-1, "未知错误!");
        }
    }

}
