package com.processexample.process.bean.DO;

import com.alibaba.fastjson.JSON;
import com.processexample.process.bean.DTO.StepConfigDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Map;

/**
 * Create by  GF  in  23:29 2018/6/7
 * Description:
 * Modified  By:
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="demo_process_step_config")
public class StepConfig {

    @Id
    private Integer stepId;

    private String stepDescription;

    private String resultDescription;

    private String handleClass;

    public static StepConfigDTO convert2DTO(StepConfig DO){

        return StepConfigDTO.builder()
                .stepId(DO.stepId.toString())
                .stepDescription(DO.stepDescription)
                .resultCodeDescriptionMap(JSON.parseObject(DO.resultDescription))
                .handleClass(DO.handleClass)
                .build();
    }


}
