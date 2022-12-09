package com.scofen.designpattern.factory.method;

import com.scofen.designpattern.factory.Audi;
import com.scofen.designpattern.factory.Benz;
import com.scofen.designpattern.factory.abstractFactory.Car;

/**
 * @Description: TODO
 * @Author gaofeng
 * @Date 7/9/22 7:59 PM
 **/
public class BenzFactory implements Factory {

    @Override
    public Car getCar() {
        return new Benz();
    }
}
