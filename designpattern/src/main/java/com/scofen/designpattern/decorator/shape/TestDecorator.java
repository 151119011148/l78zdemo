package com.scofen.designpattern.decorator.shape;

import org.junit.Test;

/**
 * Create by  GF  in  19:36 2019/3/13
 * Description:
 * Modified  By:
 */
public class TestDecorator {

    @Test
    public  void testDecorator() {

        Shape circle = new Circle();

        Shape redCircle = new RedShapeDecorator(new Circle());

        Shape redRectangle = new RedShapeDecorator(new Rectangle());

        System.out.println("\nCircle with normal border");
        circle.draw();

        System.out.println("\nCircle of red border");
        redCircle.draw();

        System.out.println("\nRectangle of red border");
        redRectangle.draw();

    }
}
