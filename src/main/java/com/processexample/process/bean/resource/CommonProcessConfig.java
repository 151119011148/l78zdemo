package com.processexample.process.bean.resource;

import com.processexample.process.bean.InterfaceDTO.ProcessConfigInterface;
import com.processexample.process.bean.InterfaceDTO.ProcessInterfaceDTO;
import com.processexample.process.bean.InterfaceDTO.StepInterfaceDTO;
import com.processexample.process.utils.Converter;
import lombok.AllArgsConstructor;

import java.util.Map;

/**
 * Create by  GF  in  14:22 2018/6/8
 * Description:
 * Modified  By:
 */
@AllArgsConstructor
public class CommonProcessConfig implements ProcessConfigInterface {

    private Map processesConfigMap;

    private Map stepsMap;

    private Map processMap;


    @Override
    public Map getProcessConfigMap(String processId) {
        return Converter.ObjectToMap(processesConfigMap.get(processId));
        //return (Map)processesConfigMap.get(processId);
    }

    @Override
    public StepInterfaceDTO getStep(String stepId) {
        return (StepInterfaceDTO)stepsMap.get(Integer.valueOf(stepId));
    }

    @Override
    public ProcessInterfaceDTO getProcess(String processId) {
        return (ProcessInterfaceDTO)processMap.get(processId);
    }
}
