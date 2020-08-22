package com.scofen.jvm.stackOverFlow;

/**
 * Create by  GF  in  21:41 2019/12/8
 * Description:栈溢出
 * Modified  By:
 */
public class StackOverFlow {
    public static void main(String[] args) {
        method(1);
    }

    private static void method(int i){
        i ++;
        System.out.println(i);
        method(i);
    }

}
