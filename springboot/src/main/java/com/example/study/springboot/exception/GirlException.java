package com.scofen.study.springboot.exception;


import com.scofen.study.springboot.enums.ResultEnum;
import lombok.Data;

/**
 * Create by  GF  in  16:01 2017/9/22
 * Description:
 * Modified  By:
 */
@Data
public class GirlException extends RuntimeException {
    private Integer code;

    public GirlException(ResultEnum resultEnum){
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }
}
