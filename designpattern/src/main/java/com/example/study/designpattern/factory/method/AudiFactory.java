package com.scofen.study.designpattern.factory.method;


import com.scofen.study.designpattern.factory.Audi;
import com.scofen.study.designpattern.factory.Car;

/**
 * Create by  GF  in  15:19 2018/8/2
 * Description:
 * Modified  By:
 */
public class AudiFactory implements Factory {

    @Override
    public Car getCar() {
        return new Audi();
    }
}
