package com.scofen.designpattern.proxy.testPerformance;

/**
 * Create by  GF  in  13:48 2019/3/14
 * Description:
 * 装饰者模式实现的代理(decorator)
 * Modified  By:
 */
public class DecoratorTest implements Test{
    private Test target;

    public DecoratorTest(Test target) {
        this.target = target;
    }

    public int test(int i) {
        return target.test(i);
    }
}
