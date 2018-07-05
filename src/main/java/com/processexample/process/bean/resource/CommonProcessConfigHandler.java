package com.processexample.process.bean.resource;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.processexample.process.bean.DO.ProcessConfig;
import com.processexample.process.bean.DTO.ProcessConfigDTO;
import com.processexample.process.bean.DTO.ProcessStepConfigDTO;
import com.processexample.process.bean.InterfaceDTO.ProcessConfigInterface;
import com.processexample.process.bean.DO.ProcessStepConfig;
import com.processexample.process.bean.InterfaceDTO.StepInterfaceDTO;
import com.processexample.process.dao.ProcessDao;
import com.processexample.process.enums.CommonContextEnum;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Create by  GF  in  22:52 2018/6/7
 * Description:
 * Modified  By:
 */
@AllArgsConstructor
@NoArgsConstructor
public class CommonProcessConfigHandler implements ProcessConfigInterfaceHandler {

    @Autowired
    private ProcessDao processDao;

    @Override
    public ProcessConfigInterface getProcessConfigInterface(String systemId) throws Exception {

        Map<String, ProcessConfigDTO> processDTOMap = Maps.newHashMap();
        Map<String, Map<Short, ProcessStepConfigDTO>> processStepsConfigDTOMap = Maps.newHashMap();

        Set<Integer> stepConfigIdSet = Sets.newHashSet();
        Map stepDTOMap = Maps.newHashMap();

        List<ProcessConfigDTO> processConfigDTOS = processDao.listProcessBySystemId(systemId);
        processConfigDTOS.forEach(processConfigDTO ->{
            Map<Short, ProcessStepConfigDTO> processToStepConfigMap = Maps.newHashMap();
            processDTOMap.put(processConfigDTO.getProcessId(), processConfigDTO);

            List<ProcessStepConfigDTO> processStepConfigDTOS = processDao.listProcessStepConfigByProcessId(processConfigDTO.getProcessId());
           processStepConfigDTOS.forEach(processStepConfigDTO ->{
                processToStepConfigMap.put(processStepConfigDTO.getStepIndex(),processStepConfigDTO);
                stepConfigIdSet.add(Integer.parseInt(processStepConfigDTO.getStepId()));
            });
            if(!processToStepConfigMap.containsKey(CommonContextEnum.START))
                processStepsConfigDTOMap.put(processConfigDTO.getProcessId(), processToStepConfigMap);
        });
        stepConfigIdSet.forEach(stepId ->{
            StepInterfaceDTO stepInterfaceDTO = processDao.getStepById(stepId.toString());
            stepDTOMap.put(stepId, stepInterfaceDTO);
        });
        return new CommonProcessConfig(processStepsConfigDTOMap,  stepDTOMap,  processDTOMap);
    }
}
