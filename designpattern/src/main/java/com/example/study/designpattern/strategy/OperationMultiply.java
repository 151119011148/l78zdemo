package com.scofen.study.designpattern.strategy;

/**
 * Create by  GF  in  14:58 2019/3/14
 * Description:
 * Modified  By:
 */
public class OperationMultiply implements StrategyInterface{
    @Override
    public int doOperation(int num1, int num2) {
        return num1 * num2;
    }
}
