package com.scofen.study.designpattern.delegate.execute;

/**
 * Create by  GF  in  11:00 2018/8/12
 * Description:
 * Modified  By:
 */
public class ExecutorA implements IExecutor {
    @Override
    public void doing() {
        System.out.println("员工A开始执行任务！");
    }
}
