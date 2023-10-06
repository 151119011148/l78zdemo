package com.scofen.designpattern.proxy.myselfProxy;

import java.lang.reflect.Method;

public interface MyselfInvocationHandler {


    Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable;

}
