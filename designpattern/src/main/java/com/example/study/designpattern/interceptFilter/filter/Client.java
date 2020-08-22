package com.scofen.study.designpattern.interceptFilter.filter;

import lombok.Setter;

/**
 * Create by  GF  in  16:42 2019/3/14
 * Description:
 * Modified  By:
 */
public class Client {

    @Setter
    FilterManager filterManager;


    public void sendRequest(String request){
        filterManager.filterRequest(request);
    }
}
