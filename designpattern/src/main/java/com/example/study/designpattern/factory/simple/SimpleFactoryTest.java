package com.scofen.study.designpattern.factory.simple;


import com.scofen.study.designpattern.factory.Car;

/**
 * Create by  GF  in  15:23 2018/8/2
 * Description:
 * Modified  By:
 */
public class SimpleFactoryTest {
    public static void main(String[] args){

        Car myCar = SimpleFactory.getCar("BMW");
        System.out.println(myCar.getName());

    }
}
