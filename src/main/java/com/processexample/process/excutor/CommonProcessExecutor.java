package com.processexample.process.excutor;

import com.alibaba.fastjson.JSON;
import com.processexample.process.bean.DTO.ProcessConfigDTO;
import com.processexample.process.bean.InterfaceDTO.ProcessConfigInterface;
import com.processexample.process.bean.Param;
import com.processexample.process.bean.context.AppContext;
import com.processexample.process.bean.paramgenerator.ProcessParamKeyToKeyTranslate;
import com.processexample.process.enums.CommonContextEnum;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * Create by  GF  in  9:53 2018/6/4
 * Description:
 * Modified  By:
 */
@Slf4j
public class CommonProcessExecutor
        extends BaseAbstractProcessExecutor
        implements ProcessExecutorInterface, ProcessExecutorHandler{

    public CommonProcessExecutor(AppContext appContext,
                                 ProcessConfigInterface processConfigInterface,
                                 Param param, String processId) {
        super(appContext, processConfigInterface, param, processId);
}

    @Override
    public Param  execute() {
        log.debug("流程={}执行完毕:输出参数为:{}:", processId, JSON.toJSONString(param));
        paramOutToInnerTranslate();
        String result = handleFirstStep();
        int protectCount = 0;
        for (Short index = getNextIndex(result); ! index .equals(CommonContextEnum.END.getIndex()); ){
            result = executeByIndex(index);
            index = getNextIndex(result);
            if (++ protectCount > 50)
                throw new RuntimeException("protectCount is too long~ ");
        }
        paramInnerToOutTranslate();
        log.debug("paramInnerToOutTranslate to {}", JSON.toJSONString(param));
        return param;
    }



    @Override
    public void paramOutToInnerTranslate() {
        if (processConfigInterface.getProcess(processId).isOutToInnerParamTranslate()){
            param = (ProcessParamKeyToKeyTranslate
                    .builder()
                    .transLateMap(
                            processConfigInterface.getProcess(processId).getOutToInnerParamTranslateMap())
                    .build())
                    .generateParam(param);
        }

    }




    @Override
    public void paramInnerToOutTranslate() {

        if (processConfigInterface.getProcess(processId).isOutToInnerParamTranslate()){
            param = (ProcessParamKeyToKeyTranslate
                    .builder()
                    .transLateMap(
                            processConfigInterface.getProcess(processId).getInnerToOutParamTranslateMap())
                    .build())
                    .generateParam(param);
        }
    }
}
