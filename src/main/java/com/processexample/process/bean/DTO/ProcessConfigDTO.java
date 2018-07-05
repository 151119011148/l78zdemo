package com.processexample.process.bean.DTO;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.processexample.process.bean.InterfaceDTO.ProcessInterfaceDTO;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * Create by  GF  in  16:39 2018/6/7
 * Description:
 * Modified  By:
 */

@Builder
@Data
public class ProcessConfigDTO   implements ProcessInterfaceDTO {

    private String  processId;

    private String systemId;

    private String processDescription;

    private Short stepCount;

    private String processStatus;

    private String outToInTranslate;

    private String inToOutTranslate;

    private Map<String, String> outToInnerParamTranslateMap;

    private Map<String, String> innerToOutParamTranslateMap;

    private boolean isOutToInnerParamTranslate;

    private boolean isInnerToOutParamTranslate;

    @Override
    public Map getOutToInnerParamTranslateMap() {
        if(! isOutToInnerParamTranslate()){
            return null;
        }
        if (this.outToInnerParamTranslateMap == null){
            JSONObject jsonObject = JSON.parseObject(outToInTranslate);
            jsonObject.keySet().forEach(key ->{
                this.outToInnerParamTranslateMap.put(key, jsonObject.getString(key));
            });
        }
        return this.outToInnerParamTranslateMap;
    }

    @Override
    public boolean isInnerToOutParamTranslate() {
        return StringUtils.isEmpty(inToOutTranslate);
    }

    @Override
    public Map getInnerToOutParamTranslateMap() {
        if(! isInnerToOutParamTranslate()){
            return null;
        }
        if (innerToOutParamTranslateMap == null){
            JSONObject jsonObject = JSON.parseObject(inToOutTranslate);
            jsonObject.keySet().forEach(key ->{
                innerToOutParamTranslateMap.put(key, jsonObject.getString(key));
            });
        }
        return innerToOutParamTranslateMap;
    }

}
