package com.scofen.study.designpattern.decorator.shape;

/**
 * Create by  GF  in  19:35 2019/3/13
 * Description:
 * Modified  By:
 */
public abstract class ShapeDecorator implements Shape {

    protected Shape decoratedShape;

    public ShapeDecorator(Shape decoratedShape){
        this.decoratedShape = decoratedShape;
    }

    public void draw(){
        decoratedShape.draw();
    }
}
