package com.scofen.designpattern.observer.demo3;


import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Description: TODO
 * @Author gaofeng
 * @Date 7/9/22 8:40 PM
 **/
@AllArgsConstructor
@Data
public class Programmer implements IProgrammer{

    private String name;

    public Programmer(String name){
        this.name = name;
    }

    //自己的加班状态
    private boolean WorkOvertimeState;

    //接到加班通知，然后更新自己的加班状态
    @Override
    public void workOvertime(Boss boss) {
        Li_Boss li_boss = (Li_Boss)boss;
        WorkOvertimeState = li_boss.getIsWorkOvertime();
        System.out.println(name+"是否要加班："+WorkOvertimeState);
    }
}
