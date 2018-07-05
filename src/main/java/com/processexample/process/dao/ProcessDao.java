package com.processexample.process.dao;

import com.processexample.process.bean.DTO.StepConfigDTO;
import com.processexample.process.bean.InterfaceDTO.StepInterfaceDTO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
public interface ProcessDao {
    /**
     * 通过systemId查processConfigList
     * @param systemId
     * @return
     */
    public abstract List listProcessBySystemId(String systemId);

    /**
     * 通过processId查processStepConfigList
     * @param processId
     * @return
     */
    public abstract List listProcessStepConfigByProcessId(String processId);


    /**
     * 通过stepId查stepConfig
     * @param stepId
     * @return
     */
    public abstract StepConfigDTO getStepById(String stepId);


}
