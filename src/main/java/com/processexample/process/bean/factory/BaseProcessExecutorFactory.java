package com.processexample.process.bean.factory;

import com.processexample.process.bean.InterfaceDTO.ProcessConfigInterface;
import com.processexample.process.bean.Param;
import com.processexample.process.bean.context.AppContext;
import com.processexample.process.excutor.CommonProcessExecutor;
import com.processexample.process.excutor.ProcessExecutorInterface;
import lombok.NoArgsConstructor;

/**
 * Create by  GF  in  15:24 2018/5/30
 * Description:
 * Modified  By:
 */
@NoArgsConstructor
public abstract class BaseProcessExecutorFactory {

    public abstract AppContext getAppContext();

    public abstract ProcessConfigInterface getProcessConfigInterface();

    public ProcessExecutorInterface getProcessExecutor(Param param, String processId){
        return new CommonProcessExecutor(getAppContext(), getProcessConfigInterface(),param,processId);
    };


}
