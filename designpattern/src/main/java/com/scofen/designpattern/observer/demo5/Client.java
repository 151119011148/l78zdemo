package com.scofen.designpattern.observer.demo5;

/**
 * @Description: TODO
 * @Author gaofeng
 * @Date 7/10/22 10:38 AM
 **/
public class Client {

    public static void main(String[] args){
        //创建目标对象
        Bug bug = new Bug();
        //创建观察者
        BugObserver bugObserver1 = new Programmer();
        bugObserver1.setName("小华");
        BugObserver bugObserver2 = new Programmer();
        bugObserver2.setName("小强");
        //注册观察者
        bug.attach(bugObserver1);
        bug.attach(bugObserver2);
        bug.setBugLevel(1);
        System.out.println("---------------------");
        bug.setBugLevel(2);
    }

}
