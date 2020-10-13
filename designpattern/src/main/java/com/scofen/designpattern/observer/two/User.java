package com.scofen.designpattern.observer.two;

/**
 * Create by  GF  in  20:32 2019/2/20
 * Description:
 *  观察者
 * 实现了update方法
 * Modified  By:
 */

public class User implements Observer {

    private String name;
    //private String message;

    public User(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        //this.message = message;
        read(message);
    }

    public void read(String message) {
        System.out.println(name + " 收到推送消息： " + message);
    }
}