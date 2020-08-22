package com.scofen.study.designpattern.interpreter;

/**
 * Create by  GF  in  16:27 2019/3/14
 * Description:
 * Modified  By:
 */
public class TerminalExpression implements Expression {

    private String data;

    public TerminalExpression(String data){
        this.data = data;
    }

    @Override
    public boolean interpret(String context) {
        if(context.contains(data)){
            return true;
        }
        return false;
    }
}
