package com.processexample.process.utils;

import com.google.common.collect.Maps;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Create by  GF  in  19:23 2018/6/13
 * Description:
 * Modified  By:
 */
public class Converter {


    public static Map ObjectToMap(Object object){

        if(object instanceof Map)
            return (Map)object;

        Map map = Maps.newHashMap();
        if(object instanceof List){
            List list = ((List)object);
            for (int i = 0 ; i< list.size(); i ++){
                map.put(i, list.get(i));
            }
            return map;
        }

        Class clazz = object.getClass();
        try {
            for (Field field : clazz.getDeclaredFields()){
                field.setAccessible(true);
                String fieldName = field.getName();
                map.put(fieldName,  field.get(fieldName));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }

    public static Object MapToObject(Map map, Class beanClass){
        if (map == null)
            return null;
        Object obj = null;
        try {
            obj = beanClass.newInstance();
            final Object finalObj = obj;
            Arrays.asList(obj.getClass().getDeclaredFields()).forEach(field -> {
                int mod = field.getModifiers();
                if(!Modifier.isStatic(mod) && !Modifier.isFinal(mod)){
                    field.setAccessible(true);
                    try {
                        field.set(finalObj, map.get(field.getName()));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return obj;
    }

}
