package com.scofen.study.designpattern.interceptFilter.filter;

import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by  GF  in  16:41 2019/3/14
 * Description:
 * Modified  By:
 */
public class FilterChain {

    private List<Filter> filters = new ArrayList<>();

    @Setter
    private Target target;

    void addFilter(Filter filter){
        filters.add(filter);
    }

    public void execute(String request){
        System.out.println("before~");
        for (Filter filter : filters) {
            filter.execute(request);
        }
        target.execute(request);
        for (Filter filter : filters) {
            filter.execute(request);
        }
        System.out.println("after~");
    }


}
