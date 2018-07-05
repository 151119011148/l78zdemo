package com.processexample.process.bean.context;

public interface AppContext {

    public abstract Object getBean(String className)throws Exception;
}
