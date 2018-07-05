package com.processexample.process.bean.InterfaceDTO;

import com.processexample.process.bean.InterfaceDTO.ProcessInterfaceDTO;
import com.processexample.process.bean.InterfaceDTO.StepInterfaceDTO;

import java.util.Map;

/**
 * Create by  GF  in  15:46 2018/5/30
 * Description:
 * Modified  By:
 */
public interface ProcessConfigInterface {

    public abstract Map getProcessConfigMap(String key);

    public abstract StepInterfaceDTO getStep(String key);

    public abstract ProcessInterfaceDTO getProcess(String key);
}
