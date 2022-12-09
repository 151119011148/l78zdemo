package com.scofen.designpattern.observer.demo2;


import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * 老板买来几个小喇叭，放在程序员的办公室里，而按钮放在老板的办公室里，
 * 如果有新的通知，就按喇叭，程序员们听到之后就前往老板的办公室领取具体的通知内容，
 * 这样就不需要程序员每天去老板办公室询问是否有新的通知了，而且还节约了成本，所谓一举两得。
 * @Author gaofeng
 * @Date 7/9/22 8:19 PM
 **/
public class Boss {

    /**
     * 定义一个列表，用来保存程序员对象
     */
    private List<Programmer> programmers = new ArrayList<Programmer>();

    /**
     * 添加一个程序员到列表中
     */

    public void attach(Programmer programmer){
        programmers.add(programmer);
    }

    /**
     * 从列表中删除某个程序员对象
     * @param programmer
     */
    public void detach(Programmer programmer){
        programmers.remove(programmer);
    }

    /**
     * 通知所有程序员加班
     * @param isWorkOvertime
     */
    public void notifyProgrammer(boolean isWorkOvertime) {
        //遍历程序员列表
        for(Programmer programmer:programmers) {
            programmer.workOvertime(isWorkOvertime);
        }
    }
}
