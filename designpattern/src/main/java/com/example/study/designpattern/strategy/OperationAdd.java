package com.scofen.study.designpattern.strategy;

/**
 * Create by  GF  in  14:57 2019/3/14
 * Description:
 * Modified  By:
 */
public class OperationAdd implements StrategyInterface{
    @Override
    public int doOperation(int num1, int num2) {
        return num1 + num2;
    }
}
