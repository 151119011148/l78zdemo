package com.scofen.designpattern.interceptFilter.filter;

/**
 * Create by  GF  in  16:43 2019/3/14
 * Description:
 * Modified  By:
 */
public class TestInterceptFilter {


    public static void main(String[] args) {
        FilterManager filterManager = new FilterManager(new Target());
        filterManager.setFilter(new AuthenticationFilter());
        filterManager.setFilter(new DebugFilter());

        Client client = new Client();
        client.setFilterManager(filterManager);
        client.sendRequest("HOME");
    }

}
