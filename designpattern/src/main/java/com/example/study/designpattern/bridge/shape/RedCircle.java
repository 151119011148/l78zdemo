package com.scofen.study.designpattern.bridge.shape;

/**
 * Create by  GF  in  10:04 2019/3/14
 * Description:
 * 创建实现了 DrawAPI 接口的实体桥接实现类。
 * Modified  By:
 */
public class RedCircle implements DrawAPI {
    @Override
    public void drawCircle(int radius, int x, int y) {
        System.out.println("Drawing Circle[ color: red, radius: "
                + radius +", x: " +x+", "+ y +"]");
    }
}
