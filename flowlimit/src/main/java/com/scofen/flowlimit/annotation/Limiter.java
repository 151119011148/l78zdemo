package com.scofen.flowlimit.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Limiter {

    /**
     * 限流key
     * @return
     */
    String route();

    /**
     * 限流次数
     * @return
     */
    int limit() default 10;

}
