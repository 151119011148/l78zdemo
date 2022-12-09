package com.scofen.designpattern.observer.demo4;

/**
 * @Description:
 * 不需要定义观察者和目标的接口，JDK已经定义好了
 *
 * 在目标实现类中不需要维护观察者的注册信息，这个Observable类中帮忙实现好了。
 *
 * 触发方式需要先调用setChanged方法
 *
 * 具体的观察者实现中，update方法同时支持推模型和拉模型。
 *
 * 不久老板又陷入了苦恼，因为这种方式每次都会同时通知小华和小强，但是老板想只通知小强而不通知小华，想要区别对待观察者，
 * 例如当有用户反应公司的软件产品出现bug时，需要小华和小强去找bug并修复。
 * 但当出现不是特别紧急的bug时只需要小华一个人修改就可以了，
 * 如果出现了紧急的bug，需要马上修复的，就需要小华和小强同时去修复。
 * 该如何使用观察者实现上面的场景呢？
 *
 * @Author gaofeng
 * @Date 7/9/22 8:55 PM
 **/
public class Client {

    public static void main(String[] args){
        Li_Boss boss = new Li_Boss();
        Programmer programmer1 = new Programmer("小强");
        Programmer programmer2 = new Programmer("小华");
        //将上面两个程序员添加到老板类里的程序员列表中
        boss.addObserver(programmer1);
        boss.addObserver(programmer2);
        boss.setIsWorkOvertime("今晚加班");

    }
}
