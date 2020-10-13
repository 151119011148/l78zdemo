package com.scofen.designpattern.bridge.shape;

/**
 * Create by  GF  in  10:06 2019/3/14
 * Description:
 * 创建实现了 Shape 接口的实体类
 * Modified  By:
 */
public class Circle extends Shape {
    private int x, y, radius;

    public Circle(int x, int y, int radius, DrawAPI drawAPI) {
        super(drawAPI);
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public void draw() {
        drawAPI.drawCircle(radius,x,y);
    }
}
