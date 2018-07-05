package com.processexample.process.step;

import com.processexample.process.bean.Param;

/**
 * Create by  GF  in  17:16 2018/6/14
 * Description:
 * Modified  By:
 */
public interface StepInterfaceHandle {

     String stepExecute(Param param);

     String[] getResults();


}
