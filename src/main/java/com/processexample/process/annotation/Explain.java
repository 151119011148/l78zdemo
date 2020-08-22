package com.processexample.process.annotation;

import org.springframework.boot.logging.LogLevel;

public @interface Explain {

    public String name();

    public LogLevel logLv();

    public String messageType() default "";

    public int logParamNum() default 0;

    //public String[] logParamsExplains() default null;




}
