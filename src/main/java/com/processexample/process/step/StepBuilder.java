package com.processexample.process.step;

import com.processexample.process.bean.Param;
import com.processexample.process.bean.context.AppContext;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;

/**
 * Create by  GF  in  8:50 2018/6/1
 * Description:
 * Modified  By:
 */
@NoArgsConstructor
public class StepBuilder {

    private Object handleBean;

    private Method handleMethod;

    private AppContext appContext;

    private Param param;

    private static final String PARAM_GENERATRO_MENTHOD_NAME = "generateParam";

    private static final String STEP_EXECUTE_METHOD_NAME = "stepExecute";


    public static StepBuilder newStep(String handleClass, AppContext appContext)throws Exception{
        StepBuilder stepBuilder = new StepBuilder();
        stepBuilder.handleBean = appContext.getBean(handleClass);
        stepBuilder.appContext = appContext;
        stepBuilder.handleMethod = stepBuilder.handleBean.getClass().getMethod("stepExecute", Param.class);
        return stepBuilder;
    }

    public StepBuilder withParam(Param innerParam)throws Exception{
        param  = innerParam;
        return this;
    }

    public Step build(){
        return new Step(handleBean , handleMethod, param);
    }


}
