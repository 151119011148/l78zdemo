package com.scofen.study.designpattern.emptyObject;

/**
 * Create by  GF  in  16:14 2019/3/14
 * Description:
 * Modified  By:
 */
public abstract class AbstractCustomer {
    protected String name;
    public abstract boolean isNil();
    public abstract String getName();
    public AbstractCustomer(){}
    public AbstractCustomer(String name){
        this.name = name;
    }
}
