package com.scofen.designpattern.interceptFilter.filter;

/**
 * Create by  GF  in  16:40 2019/3/14
 * Description:
 * 创建实体过滤器
 * Modified  By:
 */
public class AuthenticationFilter implements Filter {
    public void execute(String request){
        System.out.println("Authenticating request: " + request);
    }
}
