package com.scofen.designpattern.strategy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Create by  GF  in  14:58 2019/3/14
 * Description:
 * Modified  By:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Context {

    private StrategyInterface strategy;



    public int executeStrategy(int num1, int num2){
        return strategy.doOperation(num1, num2);
    }
}
