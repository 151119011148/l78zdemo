package com.scofen.designpattern.observer.demo2;

/**
 * @Description:
 * 公司这样执行了一段时间，新的问题又出现了，因为老板并不只是通知加班这么简单，还会通知其他的事情，
 * 可能每个程序员想得到的通知不一样，比如小强去出差，小华去休假。
 * 这个时候该怎么通知他们呢？
 *
 * 这就涉及观察者的两种模型，拉模型和推模型。下面的代码就是推模型实现的。
 *
 * 推模型：目标对象主动向观察者推送目标的详细信息，不管观察者是否需要，推送的信息通常是目标对象的全部或部分数据。
 * 如上面就是推送的是否加班的状态。
 *
 * 拉模型：目标对象在通知观察者的时候，只会传递少量信息，如果不知道观察者具体需要什么数据，
 * 就把目标对象自身传递给观察者，让观察者自己按需去取信息。
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
