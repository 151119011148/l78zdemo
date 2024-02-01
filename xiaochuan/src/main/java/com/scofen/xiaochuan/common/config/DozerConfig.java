package com.scofen.xiaochuan.common.config;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: TODO
 * @Author gaofeng
 * @Date 2023/9/27 8:25 PM
 **/
@Configuration
public class DozerConfig {

    @Bean
    public Mapper dozerMapper() {
        return new DozerBeanMapper();
    }
}
