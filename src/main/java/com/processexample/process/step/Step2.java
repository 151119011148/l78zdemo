package com.processexample.process.step;


import com.processexample.process.bean.Param;
import org.springframework.stereotype.Component;

/**
 * Create by  GF  in  17:10 2018/6/14
 * Description:
 * Modified  By:
 */
@Component("Step2")
public class Step2 implements StepInterfaceHandle{

    private static final String result_success = "suc";
    private static final String result_failed= "fail";

    @Override
    public String stepExecute(Param param) {
        return result_success;
    }

    @Override
    public String[] getResults() {
        return new String[0];
    }
}
