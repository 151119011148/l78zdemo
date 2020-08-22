package com.scofen.study.designpattern.proxy.myselfProxy;

import java.lang.reflect.Method;

public interface MyselfInvocationHandler {


    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable;

}
