package com.scofen.study.designpattern.chainOfResponsibility.logger;

/**
 * Create by  GF  in  19:14 2019/3/13
 * Description:
 * Modified  By:
 */
public class InfoLogger extends AbstractLogger {

    public InfoLogger(int level){
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("InfoLogger: " + message);
        System.out.println();
    }
}
