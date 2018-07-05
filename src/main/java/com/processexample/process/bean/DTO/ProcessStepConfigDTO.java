package com.processexample.process.bean.DTO;


import com.processexample.process.bean.InterfaceDTO.ProcessStepsConfigInterfaceDTO;
import lombok.Builder;
import lombok.Data;


import java.util.Map;

/**
 * Create by  GF  in  17:38 2018/6/12
 * Description:
 * Modified  By:
 */
@Data
@Builder
public class ProcessStepConfigDTO  implements ProcessStepsConfigInterfaceDTO {

    private String processId;

    private String stepId;

    private Short stepIndex;

    private Long maxExecuteTime;

    private Map<String, Short> resultToIndexMap;

}
