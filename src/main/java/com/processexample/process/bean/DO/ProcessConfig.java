package com.processexample.process.bean.DO;

import com.alibaba.fastjson.JSON;
import com.processexample.process.bean.DTO.ProcessConfigDTO;
import com.processexample.process.utils.Converter;
import lombok.*;
import org.springframework.beans.BeanUtils;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

/**
 * Create by  GF  in  16:39 2018/6/7
 * Description:
 * Modified  By:
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="demo_process_process_config")
public class ProcessConfig  {
    @Id
    private Integer processId;

    private String systemId;

    private String processDesc;

    private Short stepCount;

    public static ProcessConfigDTO convert2DTO(ProcessConfig DO){

        return ProcessConfigDTO.builder()
                .processId(DO.processId.toString())
                .systemId(DO.systemId)
                .processDescription(DO.processDesc)
                .stepCount(DO.stepCount)
                .inToOutTranslate(JSON.toJSONString(DO))
                .outToInTranslate(JSON.toJSONString(DO))
                .build();

    }


}
