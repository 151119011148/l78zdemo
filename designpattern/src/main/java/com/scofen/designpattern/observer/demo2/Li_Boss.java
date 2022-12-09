package com.scofen.designpattern.observer.demo2;

/**
 * @Description: TODO
 * @Author gaofeng
 * @Date 7/9/22 8:20 PM
 **/
public class Li_Boss extends Boss {

    //是否加班的通知
    private boolean isWorkOvertime;

    public boolean getIsWorkOvertime() {
        return isWorkOvertime;
    }

    //当加班状态发生变化时，要通知程序员最新的加班状态
    public void setIsWorkOvertime(boolean isWorkOvertime) {
        this.isWorkOvertime = isWorkOvertime;
        this.notifyProgrammer(isWorkOvertime);
    }
}
