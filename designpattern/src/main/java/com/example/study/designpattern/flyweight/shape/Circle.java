package com.scofen.study.designpattern.flyweight.shape;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Create by  GF  in  16:00 2019/3/14
 * Description:
 * Modified  By:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Circle implements Shape {

    private String color;
    private int x;
    private int y;
    private int radius;

    public Circle(String color){
        this.color = color;
    }

    @Override
    public void draw() {
        System.out.println("Circle: Draw() [Color : " + color  +", x : " + x +", y :" + y +", radius :" + radius);
    }
}
