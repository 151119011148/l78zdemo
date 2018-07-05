package com.processexample.process.annotation;

import java.lang.annotation.Annotation;

public @interface SOA {

    public String name();

    public String version() default "0.1";

    public String  group() default "";

    public int timeout() default 100;

    public String value() default "";






}
