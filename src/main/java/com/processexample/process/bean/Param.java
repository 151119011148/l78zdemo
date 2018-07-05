package com.processexample.process.bean;

import com.processexample.process.enums.ParamTypeEnum;
import com.processexample.process.exception.ParamIsnotExistException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Iterator;
import java.util.Map;

/**
 * Create by  GF  in  11:28 2018/5/30
 * Description:
 * Modified  By:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Param {

    private Map<String, Object> map;

    public Object getValue(ParamKey paramKey)throws ParamIsnotExistException {
        if(!map.containsKey(paramKey.getKey()))
            throw new ParamIsnotExistException(paramKey);
        else
            return map.get(paramKey.getKey());
    }

    public String getValueIgnoreIsnotContainsKey(ParamKey paramKey)throws ParamIsnotExistException {
        if(!map.containsKey(paramKey.getKey()))
            throw new ParamIsnotExistException(paramKey);
        else {
            Object result = map.get(paramKey.getKey());
            return result != null ? result.toString() : null;
        }
    }

    public Object getStringValue(ParamKey paramKey)throws ParamIsnotExistException {
        return map.get(paramKey.getKey());
    }

    public String getStringValueIgnoreIsnotContainsKey(ParamKey paramKey)throws ParamIsnotExistException {
            Object result = map.get(paramKey.getKey());
            return result != null ? result.toString() : null;
    }

    public void putValue(ParamKey paramKey,Object value){
        if(paramKey.getParamTypeEnum().equals(ParamTypeEnum.UNOVERWITEABLE )
                && map.containsKey(paramKey.getKey())){
            throw new RuntimeException("UNOVERWITEABLE");
        }
        map.put(paramKey.getKey(), value);
    }

    public Iterable keys(){
        return (Iterable) map.keySet().iterator();
    }

    public void putAll(String names[], Object values[]){
        int i = 0;
        for(int len = Math.min(names.length, values.length); i < len; i ++){
            map.put(names[i], values[i]);
        }
    }

    public void putAll(Map newMap){
        Map.Entry entry;
        for(Iterator tar = newMap.entrySet().iterator(); tar.hasNext(); ){
            entry = (Map.Entry) tar.next();
            map.put(String.valueOf(entry.getKey()),entry.getValue());
        }
    }
    public Map getParamMap(){
        return map;
    }

}
