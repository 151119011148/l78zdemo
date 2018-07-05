package com.processexample.process.step;

import com.processexample.process.bean.Param;
import com.processexample.process.bean.StepInterface;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Create by  GF  in  9:59 2018/6/1
 * Description:
 * Modified  By:
 */
@Slf4j
@AllArgsConstructor
public class Step implements StepInterface {

    private Object handleBean;

    private Method method;

    private Param param;


    @Override
    public String execute() {
        String result;
        try {
            log.debug(new StringBuilder()
                    .append(handleBean.getClass().getName())
                    .append("default method invoke start ...").toString());
            result = (String) method.invoke(handleBean, param);
            log.debug(new StringBuilder()
                    .append(handleBean.getClass().getName())
                    .append("default method invoke end ...").toString());
        }catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            throw  new RuntimeException();
        }
        return result;
    }
}
