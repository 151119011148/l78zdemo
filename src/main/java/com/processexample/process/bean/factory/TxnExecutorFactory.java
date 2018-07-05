package com.processexample.process.bean.factory;

import com.processexample.process.bean.InterfaceDTO.ProcessConfigInterface;
import com.processexample.process.bean.context.AppContext;
import com.processexample.process.bean.context.SpringAppContext;
import com.processexample.process.bean.resource.CommonProcessConfigHandler;
import com.processexample.process.dao.ProcessDao;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Create by  GF  in  17:30 2018/6/4
 * Description:
 * Modified  By:
 */
@Component
public class TxnExecutorFactory extends BaseProcessExecutorFactory{

    @Resource
    SpringAppContext springAppContext;

    @Resource
    private ProcessDao processDao;

    private String systemId = "201";

    private ProcessConfigInterface processConfigInterface;


    @Override
    public AppContext getAppContext() {
        return springAppContext;
    }

    @Override
    public ProcessConfigInterface getProcessConfigInterface() {
        if(this.processConfigInterface == null){
            CommonProcessConfigHandler commonProcessConfigHandler = new CommonProcessConfigHandler(processDao);
            try {
                this.processConfigInterface = commonProcessConfigHandler.getProcessConfigInterface(systemId);
            }catch (Exception e){
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        return this.processConfigInterface;
    }


}
