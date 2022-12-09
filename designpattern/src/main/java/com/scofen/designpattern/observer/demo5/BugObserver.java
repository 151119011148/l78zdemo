package com.scofen.designpattern.observer.demo5;

/**
 * @Description: TODO
 * @Author gaofeng
 * @Date 7/10/22 10:35 AM
 **/
public interface BugObserver {

    //传入被观察的目标对象
    public void update(BugSubject subject);

    //观察人员职务
    public void setName(String name);

    public String getName();

}
