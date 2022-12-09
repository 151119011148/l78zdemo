package com.scofen.designpattern.factory;

import com.scofen.designpattern.factory.abstractFactory.Car;

/**
 * Create by  GF  in  15:21 2018/8/2
 * Description:
 * Modified  By:
 */
public class Benz implements Car {
    @Override
    public String getName() {
        return "Benz";
    }
}
