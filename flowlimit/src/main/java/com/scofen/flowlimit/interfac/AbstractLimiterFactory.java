package com.scofen.flowlimit.interfac;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * @author 高锋
 * @className: AbstractHealthDataManagerFactory
 * @description: TODO
 * @date 2020/8/3110:36
 */
@Component
@Data
public class AbstractLimiterFactory implements ApplicationContextAware {

    private ApplicationContext applicationContext;


    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        applicationContext = ctx;
    }

    /**
     * 获取applicationContext
     *
     * @return
     */
    private ApplicationContext getApplicationContext() {
        return this.applicationContext;
    }

    public FlowLimiter getBean(String name) {
        return getBean(name, FlowLimiter.class);
    }

    private <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }


}
