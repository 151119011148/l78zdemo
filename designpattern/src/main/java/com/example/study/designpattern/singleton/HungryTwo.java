package com.scofen.study.designpattern.singleton;

/**
 * Create by  GF  in  14:14 2019/3/13
 * Description:
 * 饿汉式
 * （静态代码块）[可用]
 * Modified  By:
 */
public class HungryTwo {

    private static HungryTwo instance;

    static {
        instance = new HungryTwo();
    }

    private HungryTwo() {}

    public static HungryTwo getInstance() {
        return instance;
    }
}
