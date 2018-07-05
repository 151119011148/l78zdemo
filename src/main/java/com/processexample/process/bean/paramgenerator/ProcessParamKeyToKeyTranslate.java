package com.processexample.process.bean.paramgenerator;

import com.google.common.collect.Maps;
import com.processexample.process.bean.Param;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Iterator;
import java.util.Map;

/**
 * Create by  GF  in  11:06 2018/6/4
 * Description:
 * Modified  By:
 */
@AllArgsConstructor
@Builder
public class ProcessParamKeyToKeyTranslate implements ParamGenerator{

    public Map<String, Object> transLateMap;

    @Override
    public Param generateParam(Param param) {

        Map newMap = param.getParamMap();
        Map resultMap = Maps.newHashMap();

        transLateMap.entrySet().iterator().forEachRemaining(item ->
            resultMap.put(item.getKey(), newMap.get(item.getKey()))
        );
        return Param.builder()
                .map(resultMap)
                .build();

    }



}
