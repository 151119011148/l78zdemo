package com.scofen.designpattern.singleton;

/**
 * Create by  GF  in  10:43 2019/3/13
 * Description:
 * 饿汉式
 * （静态常量）[可用]
 * Modified  By:
 */
public class HungryOne {

    private HungryOne(){}

    private static HungryOne hungryOne = new HungryOne();

    public static HungryOne getInstance(){
        System.out.println(System.currentTimeMillis() + ":" + hungryOne);
        return hungryOne;
    }



}
