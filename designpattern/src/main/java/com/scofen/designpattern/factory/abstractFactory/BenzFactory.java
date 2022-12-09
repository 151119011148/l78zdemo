package com.scofen.designpattern.factory.abstractFactory;


import com.scofen.designpattern.factory.Benz;

/**
 * Create by  GF  in  15:19 2018/8/2
 * Description:
 * Modified  By:
 */
public class BenzFactory extends AbstractFactory {

    @Override
    public Car getCar() {
        return new Benz();
    }
}
