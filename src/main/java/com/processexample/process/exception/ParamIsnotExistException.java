package com.processexample.process.exception;

import com.processexample.process.bean.ParamKey;

/**
 * Create by  GF  in  14:32 2018/5/30
 * Description:
 * Modified  By:
 */
public class ParamIsnotExistException extends Exception {

    public ParamIsnotExistException(ParamKey paramKey){
        super(
                new StringBuilder()
                        .append("[")
                        .append(paramKey.getKey()).
                        append("];")
                        .append("isNotExist").toString()
        );
    }

}
