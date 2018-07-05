package com.processexample.process.bean;

import com.processexample.process.enums.ParamTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Create by  GF  in  11:31 2018/5/30
 * Description:
 * Modified  By:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParamKey {

    private String key;
    private String description;
    private ParamTypeEnum paramTypeEnum;

}
