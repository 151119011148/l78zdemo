package com.processexample.process.dao.impl;

import com.processexample.process.bean.DO.ProcessConfig;
import com.processexample.process.bean.DO.ProcessStepConfig;
import com.processexample.process.bean.DO.StepConfig;
import com.processexample.process.bean.DTO.ProcessConfigDTO;
import com.processexample.process.bean.DTO.ProcessStepConfigDTO;
import com.processexample.process.bean.DTO.StepConfigDTO;
import com.processexample.process.bean.InterfaceDTO.StepInterfaceDTO;
import com.processexample.process.dao.ProcessDao;
import com.processexample.process.dao.repository.ProcessConfigRepository;
import com.processexample.process.dao.repository.ProcessStepConfigRepository;
import com.processexample.process.dao.repository.StepConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Create by  GF  in  23:13 2018/6/7
 * Description:
 * Modified  By:
 */
@Component
public class ProcessDaoImpl implements ProcessDao {

    @Autowired
    private ProcessConfigRepository processConfigRepository;

    @Autowired
    private ProcessStepConfigRepository processStepConfigRepository;

    @Autowired
    private StepConfigRepository stepConfigRepository;


    @Override
    public List<ProcessConfigDTO> listProcessBySystemId(String systemId) {
        return processConfigRepository.findBySystemId(systemId).stream().map(ProcessConfig :: convert2DTO).collect(Collectors.toList());
    }

    @Override
    public List<ProcessStepConfigDTO> listProcessStepConfigByProcessId(String processId) {
        return processStepConfigRepository.findProcessStepConfigsByProcessId(Integer.valueOf(processId)).stream().map(ProcessStepConfig :: convert2DTO).collect(Collectors.toList());
    }

    @Override
    public StepConfigDTO getStepById(String stepId) {

        return StepConfig.convert2DTO(stepConfigRepository.findByStepId(Integer.parseInt(stepId)));

    }
}
