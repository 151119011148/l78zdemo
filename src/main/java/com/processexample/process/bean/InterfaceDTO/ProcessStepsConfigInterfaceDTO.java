package com.processexample.process.bean.InterfaceDTO;

import java.util.Map;

public interface ProcessStepsConfigInterfaceDTO {

    public abstract String getProcessId();

    public abstract String getStepId();

    public abstract Short getStepIndex();

    public abstract Map getResultToIndexMap();

    public abstract Long getMaxExecuteTime();



}
