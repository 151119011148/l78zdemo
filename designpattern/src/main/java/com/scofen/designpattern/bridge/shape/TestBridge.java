package com.scofen.designpattern.bridge.shape;

/**
 * Create by  GF  in  10:07 2019/3/14
 * Description:
 * 使用 Shape 和 DrawAPI 类画出不同颜色的圆。
 * Modified  By:
 */
public class TestBridge {
    public static void main(String[] args) {
        Shape redCircle = new Circle(100,100, 10, new RedCircle());
        Shape greenCircle = new Circle(100,100, 10, new GreenCircle());

        redCircle.draw();
        greenCircle.draw();
    }
}
