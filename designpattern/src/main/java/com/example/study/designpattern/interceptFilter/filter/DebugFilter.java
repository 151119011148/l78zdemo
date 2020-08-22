package com.scofen.study.designpattern.interceptFilter.filter;

/**
 * Create by  GF  in  16:40 2019/3/14
 * Description:
 * Modified  By:
 */
public class DebugFilter implements Filter {
    public void execute(String request){
        System.out.println("request log: " + request);
    }
}
