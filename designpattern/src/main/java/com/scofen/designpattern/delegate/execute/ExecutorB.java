package com.scofen.designpattern.delegate.execute;

/**
 * Create by  GF  in  11:02 2018/8/12
 * Description:
 * Modified  By:
 */
public class ExecutorB implements IExecutor {


    @Override
    public void doing() {
        System.out.println("员工B开始执行任务！");
    }
}
