package com.scofen.designpattern.interpreter;

/**
 * @Description: TODO
 * @Author gaofeng
 * @Date 7/9/22 8:02 PM
 **/
public class OrExpression implements Expression {

    public OrExpression(Expression robert, Expression john) {

    }

    @Override
    public boolean interpret(String context) {
        return false;
    }
}
