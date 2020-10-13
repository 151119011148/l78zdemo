package com.scofen.designpattern.delegate.execute;

/**
 * Create by  GF  in  11:08 2018/8/12
 * Description:经理
 * Modified  By:
 */
public class Dispatcher implements IExecutor {
    IExecutor executor;
    Dispatcher(IExecutor executor){
        this.executor = executor;
    }
    /**
     * 项目经理，虽然也有执行方法
     * 但是他的工作职责不一样
     */
    @Override
    public void doing() {
        this.executor.doing();
    }
}
