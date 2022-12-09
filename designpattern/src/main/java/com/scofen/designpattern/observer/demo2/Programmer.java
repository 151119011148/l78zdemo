package com.scofen.designpattern.observer.demo2;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Description: TODO
 * @Author gaofeng
 * @Date 7/9/22 8:21 PM
 **/
@Data
@AllArgsConstructor
public class Programmer implements IProgrammer{

    private String name;

    public Programmer(String name){
        this.name = name;
    }

    //自己的加班状态
    private boolean WorkOvertimeState;

    //接到加班通知，然后更新自己的加班状态
    @Override
    public void workOvertime(boolean isWorkOvertime) {
        WorkOvertimeState = isWorkOvertime;
        System.out.println(name+"是否要加班："+WorkOvertimeState);
    }
}