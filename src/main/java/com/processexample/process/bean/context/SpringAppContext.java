package com.processexample.process.bean.context;

import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.awt.*;

/**
 * Create by  GF  in  17:23 2018/5/31
 * Description:
 * Modified  By:
 */
@Component
@NoArgsConstructor
public class SpringAppContext implements AppContext,ApplicationContextAware {

    public ApplicationContext applicationContext;

    @Override
    public Object getBean(String className) throws Exception {
        Object object = ((Component)AnnotationUtils.getAnnotation(Class.forName(className),org.springframework.stereotype.Component.class));
        String beanId = ((Component)AnnotationUtils.getAnnotation(Class.forName(className),org.springframework.stereotype.Component.class))
                .value();
        if(StringUtils.isBlank(beanId)){
            throw new Exception("beanId 初始化失败~");
        }
        return applicationContext.getBean(beanId);
    }

    @Override
    public void setApplicationContext(ApplicationContext innerApplicationContext) throws BeansException {
        applicationContext = innerApplicationContext;
    }
}
