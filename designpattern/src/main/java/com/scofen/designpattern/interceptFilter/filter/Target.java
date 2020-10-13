package com.scofen.designpattern.interceptFilter.filter;

/**
 * Create by  GF  in  16:40 2019/3/14
 * Description:
 * Modified  By:
 */
public class Target {
    public void execute(String request){
        System.out.println("Executing request: " + request);
    }
}
