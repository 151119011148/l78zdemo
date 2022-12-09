package com.scofen.designpattern.observer.demo1;

/**
 * @Description: 这个程序，小美使用死循环来监听，导致CPU飙升，严重浪费了公司的资源，公司面临亏损，老板决定裁员来开源节流，
 * 就这样处在程序员和老板直接的小美就被裁掉了，没了小美之后，程序员就要上班时去老板办公室签到，下班时再去询问今晚是否加班。
 * 久而久之，程序员心生怨念，老板每天被问来问去也很烦，于是他们想到了一种新的解决方案
 * @Author gaofeng
 * @Date 7/9/22 11:59 AM
 **/
public class Client {

    public static void main(String[] args) throws InterruptedException {
        Boss boss = new Boss();
        Programmer programmer = new Programmer("小强");
        GrilFriend grilFriend = new GrilFriend(boss,programmer);
        grilFriend.start();
        boss.notifyProgrammer();
        Thread.sleep(1000);
    }

}
