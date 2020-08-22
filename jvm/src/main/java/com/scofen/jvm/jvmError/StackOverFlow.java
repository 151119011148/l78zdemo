package com.scofen.jvm.jvmError;

import org.junit.Test;

/**
 * Create by  GF  in  11:28 2019/3/3
 * Description:栈溢出
 * VM  Args: -Xss128K
 */
public class StackOverFlow {
    private  int stackLength = 1;
    public  void stackLeak(){
        stackLength ++;
        stackLeak();
    }
    @Test
    public void stackOverFlow(){
        StackOverFlow test = new StackOverFlow();
        try {
            test.stackLeak();
        }catch (Throwable e){
            System.out.println("stack length:" + test.stackLength);
            throw e;
        }
    }
}
