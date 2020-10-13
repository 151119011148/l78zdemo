package com.scofen.designpattern.observer.core;

import lombok.Data;


import java.lang.reflect.Method;

/**
 * Create by  GF  in  15:05 2018/11/20
 * Description:
 * Modified  By:
 */
@Data
public class Event {

    //事件源

    private Object source;

    //目标
    private Object target;

    //回调
    private Method callback;

    //触发器
    private String trigger;

    public Event setTrigger(String trigger){
        this.trigger = trigger;
        return this;
    }

    private long time;

    public Event(Object target, Method callback) {
        this.target = target;
        this.callback = callback;
    }

    @Override
    public String toString() {
        return "Event{" +
                "\tsource=" + source + ",\n" +
                "\ttarget=" + target + ",\n" +
                "\tcallback=" + callback + ",\n" +
                "\ttrigger='" + trigger + ",\n" +
                "\ttime=" + time + ",\n" +
                '}';
    }
}
