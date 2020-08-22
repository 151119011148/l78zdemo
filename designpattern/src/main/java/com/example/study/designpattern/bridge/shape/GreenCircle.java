package com.scofen.study.designpattern.bridge.shape;

/**
 * Create by  GF  in  10:05 2019/3/14
 * Description:
 * Modified  By:
 */
public class GreenCircle implements DrawAPI {
    @Override
    public void drawCircle(int radius, int x, int y) {
        System.out.println("Drawing Circle[ color: green, radius: "
                + radius +", x: " +x+", "+ y +"]");
    }
}
