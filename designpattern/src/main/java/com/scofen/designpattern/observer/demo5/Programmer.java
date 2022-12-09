package com.scofen.designpattern.observer.demo5;

/**
 * @Description: TODO
 * @Author gaofeng
 * @Date 7/10/22 10:37 AM
 **/
public class Programmer implements BugObserver{

    private String name;

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void update(BugSubject subject) {
        System.out.println(name+"获取到通知，当前bug级别为"+subject.getBugLevel());
    }

}