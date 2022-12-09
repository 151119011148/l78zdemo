package com.scofen.designpattern.observer.demo3;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: TODO
 * @Author gaofeng
 * @Date 7/9/22 8:39 PM
 **/
@Data
public class Boss {

    /**
     * 定义一个列表，用来保存程序员对象
     */
    private List<Programmer> programmers = new ArrayList<Programmer>();

    /**
     *
     * 添加一个程序员到列表中
     */
    public void attach(Programmer programmer){
        programmers.add(programmer);
    }

    /**
     *
     * 从列表中删除某个程序员对象
     */
    public void detach(Programmer programmer){
        programmers.remove(programmer);
    }

    /**
     * 通知所有程序员加班
     */
    public void notifyProgrammer() {
        //遍历程序员列表
        for(Programmer programmer:programmers) {
            programmer.workOvertime(this);
        }


    }
}
