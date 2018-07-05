package com.processexample.process.exception;

import com.processexample.process.bean.InterfaceDTO.StepInterfaceDTO;

/**
 * Create by  GF  in  18:18 2018/5/31
 * Description:
 * Modified  By:
 */
public class StepExcutingRuntimeException extends RuntimeException {

    public StepExcutingRuntimeException(StepInterfaceDTO stepInterfaceDTO, String errorMessage){
        super(
                new StringBuilder().append(stepInterfaceDTO.getStepDescription()).
                        append(":")
                        .append(stepInterfaceDTO.getStepId())
                        .append(errorMessage)
                        .toString()
        );
    }
}
