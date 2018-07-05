package com.processexample.process.bean.InterfaceDTO;

import java.util.Map;

public interface ProcessInterfaceDTO {

    public abstract String getProcessId();

    public abstract String getProcessDescription();

    public abstract Short getStepCount();

    public abstract boolean isOutToInnerParamTranslate();

    public abstract Map getOutToInnerParamTranslateMap();

    public abstract boolean isInnerToOutParamTranslate();

    public abstract Map getInnerToOutParamTranslateMap();


}
