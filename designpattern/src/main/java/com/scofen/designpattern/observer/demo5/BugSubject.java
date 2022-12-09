package com.scofen.designpattern.observer.demo5;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: TODO
 * @Author gaofeng
 * @Date 7/10/22 10:36 AM
 **/
public abstract class BugSubject {

    //保存注册的观察者
    protected List<BugObserver> observers = new ArrayList<BugObserver>();

    public void attach(BugObserver observer){
        observers.add(observer);
    }
    public void detach(BugObserver observer){
        observers.remove(observer);
    }

    //通知具体的观察者程序员
    public abstract void notifyProgrammer();

    //获取当前bug级别
    public abstract int getBugLevel();

}
