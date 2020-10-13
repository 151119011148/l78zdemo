package com.scofen.designpattern.delegate.execute;



/**
 * Create by  GF  in  11:12 2018/8/12
 * Description:
 * Modified  By:
 */
public class Boss {
    public static void main(String[] args){
        /**
         * 看上去好像项目经理在干活
         * 但实际上干活的是普通员工
         * 这就是典型的委派模式
         */

        Dispatcher dispatcher = new Dispatcher(new ExecutorA());
        dispatcher.doing();
    }
}
