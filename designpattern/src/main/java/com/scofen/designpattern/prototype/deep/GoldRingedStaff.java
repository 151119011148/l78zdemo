package com.scofen.designpattern.prototype.deep;

import java.io.Serializable;

/**
 * Create by  GF  in  20:04 2018/8/14
 * Description:金箍棒
 * Modified  By:
 */
public class GoldRingedStaff implements Serializable{

    private float height = 100;//长度
    private float diameter = 10;//直径

    /**
     * 金箍棒长大
     */
    public void grow(){
        this.diameter *= 2;
        this.height *= 2;
    }
    /**
     * 金箍棒缩小
     */
    public void shrink(){
        this.diameter /= 2;
        this.height /= 2;
    }



}
