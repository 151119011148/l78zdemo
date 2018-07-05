package com.processexample.process.bean.InterfaceDTO;

import java.util.Map;

/**
 * Create by  GF  in  15:49 2018/5/30
 * Description:
 * Modified  By:
 */
public interface StepInterfaceDTO {

    public abstract String getStepId();

    public abstract String getStepDescription();

    public abstract Map getResultCodeDescriptionMap();

    public abstract String getHandleClass();

    public abstract Long getExpectTimeSpend();

    public abstract String[] getInputParams();

    public abstract String[] getOutputParams();



}
