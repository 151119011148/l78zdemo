package com.scofen.designpattern.chainOfResponsibility.logger;

/**
 * Create by  GF  in  19:14 2019/3/13
 * Description:
 * Modified  By:
 */
public class DebugLogger extends AbstractLogger {

    public DebugLogger(int level){
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("DebugLogger: " + message);
    }
}
