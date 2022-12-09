package com.scofen.designpattern.factory.abstractFactory;


import com.scofen.designpattern.factory.Audi;

/**
 * Create by  GF  in  15:19 2018/8/2
 * Description:
 * Modified  By:
 */
public class AudiFactory extends AbstractFactory {

    @Override
    public Car getCar() {
        return new Audi();
    }
}
