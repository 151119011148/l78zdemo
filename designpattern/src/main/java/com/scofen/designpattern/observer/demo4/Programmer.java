package com.scofen.designpattern.observer.demo4;

import lombok.Data;

import java.util.Observable;
import java.util.Observer;

/**
 * @Description: TODO
 * @Author gaofeng
 * @Date 7/9/22 8:51 PM
 **/
@Data
public class Programmer implements Observer {

    private String name;

    public Programmer(String name){
        this.name = name;
    }

    //自己的加班状态
    private String WorkOvertimeState;

    //接到加班通知，然后更新自己的加班状态
    @Override
    public void update(Observable observable, Object o) {
        //拉模式对应的更新方法
        WorkOvertimeState = ((Li_Boss)observable).getIsWorkOvertime();
        System.out.println(name+WorkOvertimeState);

        //推模式对应的更新方法
//        WorkOvertimeState = o.toString() ;
//        System.out.println(name+WorkOvertimeState);
    }
}
