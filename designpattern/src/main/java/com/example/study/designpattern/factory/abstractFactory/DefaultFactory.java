package com.scofen.study.designpattern.factory.abstractFactory;


import com.scofen.study.designpattern.factory.Car;

/**
 * Create by  GF  in  17:31 2018/8/2
 * Description:
 * Modified  By:
 */
public class DefaultFactory extends AbstractFactory {

    private AudiFactory defaultFactory = new AudiFactory();



    @Override
    protected Car getCar() {
        return defaultFactory.getCar();
    }
}
