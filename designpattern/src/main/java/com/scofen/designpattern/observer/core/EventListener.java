package com.scofen.designpattern.observer.core;


import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Create by  GF  in  15:57 2018/11/20
 * Description:
 * Modified  By:
 */
public class EventListener {

    protected Map<Enum, Event> eventMap = new HashMap<>();

    public void addListener(Enum eventType, Object target, Method callback){
        //注册事件
        //用反射调用这个方法
        eventMap.put(eventType, new Event(target, callback));

    }

    private void trigger(Event event){
        event.setSource(this);
        event.setTime(System.currentTimeMillis());
        try {
            event.getCallback().invoke(event.getTarget(), event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void trigger(Enum call){
        if(! this.eventMap.containsKey(call)){
            return;
        }
        trigger(this.eventMap.get(call).setTrigger(call.toString()));
    }


}
