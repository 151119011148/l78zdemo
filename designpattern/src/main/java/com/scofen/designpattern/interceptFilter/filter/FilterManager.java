package com.scofen.designpattern.interceptFilter.filter;



/**
 * Create by  GF  in  16:42 2019/3/14
 * Description:
 * 创建过滤管理器
 * Modified  By:
 */
public class FilterManager {
    FilterChain filterChain;

    public FilterManager(Target target){
        filterChain = new FilterChain();
        filterChain.setTarget(target);
    }
    public void setFilter(Filter filter){
        filterChain.addFilter(filter);
    }

    public void filterRequest(String request){
        filterChain.execute(request);
    }
}
