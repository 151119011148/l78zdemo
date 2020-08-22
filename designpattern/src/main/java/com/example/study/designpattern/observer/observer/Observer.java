package com.scofen.study.designpattern.observer.observer;


import com.scofen.study.designpattern.observer.core.Event;

/**
 * Create by  GF  in  15:03 2018/11/20
 * Description:
 * Modified  By:
 */
public class Observer {


    public void advice(Event event){
        System.out.println("==================出发事件，打印日志==================" );
        System.out.println(event.toString());
    }




}
