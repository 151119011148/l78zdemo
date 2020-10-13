package com.scofen.designpattern.proxy.testPerformance;

/**
 * Create by  GF  in  13:48 2019/3/14
 * Description:
 * Modified  By:
 */
public class TestImpl implements Test {
    @Override
    public int test(int i) {
        return i+1;
    }
}
