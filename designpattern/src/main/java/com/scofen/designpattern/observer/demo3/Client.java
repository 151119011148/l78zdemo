package com.scofen.designpattern.observer.demo3;


/**
 * @Description:
 *
 * 推拉模型的比较：
 * 推模型是假定目标对象知道观察者需要什么数据，相当于精准推送。
 * 拉模型目标对象不知道观察者需要什么数据，把自身对象给观察者，让观察者自己去取。
 *
 * 推模型使观察者模型难以复用，拉模型可以复用。
 *
 * 在java中已经有了观察者模式的实现，不需要自己从头写。
 * 在java.util包里面有一个类Observable，它实现了大部分我们需要的目标功能。
 * 接口Observer中定义了update方法。来看一下自己的实现区别
 * @Author gaofeng
 * @Date 7/9/22 8:22 PM
 **/
public class Client {

    public static void main(String[] args) throws InterruptedException {
        Li_Boss boss = new Li_Boss();
        Programmer programmer1 = new Programmer("小强");
        Programmer programmer2 = new Programmer("小华");
        //将上面两个程序员添加到老板类里的程序员列表中
        boss.attach(programmer1);
        boss.attach(programmer2);
        //老板发布通知，今晚加班
        boss.setIsWorkOvertime(true);
    }

}
