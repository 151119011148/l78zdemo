package com.scofen.designpattern.observer.demo4;

import java.util.Observable;

/**
 * @Description: TODO
 * @Author gaofeng
 * @Date 7/9/22 8:50 PM
 **/
//具体的老板-李老板
public class Li_Boss extends Observable {

    //是否加班的通知
    private String isWorkOvertime;

    public String getIsWorkOvertime(){
        return isWorkOvertime;
    }

    //当加班状态发生变化时，要通知程序员来领取新通知
    public void setIsWorkOvertime(String isWorkOvertime){
        this.isWorkOvertime = isWorkOvertime;
        //使用java中的Observable，这句话是必须的。
        this.setChanged();
        //拉模式
        this.notifyObservers();
        //推模式
        //this.notifyObservers(isWorkOvertime);
    }
}
