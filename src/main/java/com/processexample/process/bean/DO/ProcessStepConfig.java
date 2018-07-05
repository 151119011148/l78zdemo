package com.processexample.process.bean.DO;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.processexample.process.bean.DTO.ProcessConfigDTO;
import com.processexample.process.bean.DTO.ProcessStepConfigDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Map;

/**
 * Create by  GF  in  23:23 2018/6/7
 * Description:
 * Modified  By:
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="demo_process_process_step_config")
public class ProcessStepConfig {

    @Id
    private Integer id;

    private Integer stepId;

    private Integer processId;

    private Short stepIndex;

    private String resultToIndex;

    private Integer maxExecuteTime;

    public static ProcessStepConfigDTO  convert2DTO(ProcessStepConfig DO) {

        JSONObject jsonObject = JSON.parseObject(DO.resultToIndex);
        Map resultToIndexMap = Maps.newHashMap();
        jsonObject.keySet().forEach(key ->resultToIndexMap.put(key, jsonObject.getShort(key)));

        return ProcessStepConfigDTO.builder()
                .processId(DO.processId.toString())
                .stepId(DO.stepId.toString() )
                .stepIndex(DO.stepIndex)
                .maxExecuteTime(DO.maxExecuteTime.longValue())
                .resultToIndexMap(resultToIndexMap)
                .build();


    }
}
