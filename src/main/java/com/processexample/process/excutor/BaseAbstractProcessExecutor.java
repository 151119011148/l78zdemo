package com.processexample.process.excutor;

import com.processexample.process.bean.InterfaceDTO.ProcessConfigInterface;
import com.processexample.process.bean.InterfaceDTO.ProcessStepsConfigInterfaceDTO;
import com.processexample.process.bean.InterfaceDTO.StepInterfaceDTO;
import com.processexample.process.bean.Param;
import com.processexample.process.bean.StepInterface;
import com.processexample.process.bean.context.AppContext;
import com.processexample.process.enums.CommonContextEnum;
import com.processexample.process.exception.StepExcutingRuntimeException;
import com.processexample.process.step.StepBuilder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;
import java.util.Map;

/**
 * Create by  GF  in  18:26 2018/5/31
 * Description:
 * Modified  By:
 */
@Slf4j
public abstract class BaseAbstractProcessExecutor implements ProcessExecutorHandler {

    private AppContext appContext;

    public ProcessConfigInterface processConfigInterface;

    protected Param param;

    protected String processId;

    private ProcessStepsConfigInterfaceDTO processStepsConfigInterfaceDTO;

    public BaseAbstractProcessExecutor(AppContext appContext, ProcessConfigInterface processConfigInterface, Param param, String processId){
        this.appContext = appContext;
        this.processConfigInterface = processConfigInterface;
        this.param = param;
        this.processId = processId;

    }

    public String handleFirstStep(){
        return executeByIndex(CommonContextEnum.START.getIndex());
    }

    @Override
    public String executeByIndex(Short index){
        processStepsConfigInterfaceDTO = (ProcessStepsConfigInterfaceDTO) (processConfigInterface.getProcessConfigMap(processId).get(index));
        if(processStepsConfigInterfaceDTO == null){
            throw new RuntimeException(MessageFormat.format( "processId=[{0}]初始化失败~", processId, index));
        }
        StepInterfaceDTO stepInterfaceDTO = processConfigInterface.getStep(processStepsConfigInterfaceDTO.getStepId());
        if(stepInterfaceDTO == null){
            throw new RuntimeException(MessageFormat.format( "stepId=[{0}]初始化失败~", processStepsConfigInterfaceDTO.getStepId()));
        }
        try{
            StepInterface stepInterface = StepBuilder.newStep(stepInterfaceDTO.getHandleClass(), appContext)
                    .withParam(param).build();
            return stepInterface.execute();
        }catch (Exception e){
            e.printStackTrace();
            throw new StepExcutingRuntimeException(stepInterfaceDTO, e.getMessage());
        }

    }

    public Short getNextIndex(String result){
        if(StringUtils.isBlank(result)){
            throw new RuntimeException("innerIndex is null~");
        }
        Short index = (Short) processStepsConfigInterfaceDTO.getResultToIndexMap().get(result);
        if(index == null)
            throw new RuntimeException("outIndex is null~");
        else
            return index;
    }


}
