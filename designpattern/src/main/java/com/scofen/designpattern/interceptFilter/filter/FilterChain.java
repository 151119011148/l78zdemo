package com.scofen.designpattern.interceptFilter.filter;

/**
 * @Description: TODO
 * @Author gaofeng
 * @Date 7/9/22 7:50 PM
 **/
public class FilterChain {

    private Target target;

    public void setTarget(Target target) {
        this.target = target;
    }

    public Target getTarget() {
        return target;
    }

    public void addFilter(Filter filter) {

    }

    public void execute(String request) {

    }
}
