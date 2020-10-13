package com.scofen.designpattern.bridge.shape;

/**
 * Create by  GF  in  10:06 2019/3/14
 * Description:
 * 使用 DrawAPI 接口创建抽象类 Shape
 * Modified  By:
 */
public abstract class Shape {
    protected DrawAPI drawAPI;
    protected Shape(DrawAPI drawAPI){
        this.drawAPI = drawAPI;
    }
    public abstract void draw();
}
