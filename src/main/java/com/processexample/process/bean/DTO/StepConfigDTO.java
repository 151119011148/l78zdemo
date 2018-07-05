package com.processexample.process.bean.DTO;

import com.processexample.process.bean.InterfaceDTO.StepInterfaceDTO;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

/**
 * Create by  GF  in  14:11 2018/6/13
 * Description:
 * Modified  By:
 */
@Data
@Builder
public class StepConfigDTO implements StepInterfaceDTO{

    private String stepId;

    private String stepDescription;

    private Map resultCodeDescriptionMap;

    private Long expectTimeSpend;

    private String handleClass;

    private String[] inputParams;

    private String[] outputParams;


}
