package com.scofen.study.designpattern.template;

/**
 * Create by  GF  in  15:53 2019/3/14
 * Description:
 * Modified  By:
 */
public class Football extends Game {

    @Override
    void endPlay() {
        System.out.println("Football Game Finished!");
    }

    @Override
    void initialize() {
        System.out.println("Football Game Initialized! Start playing.");
    }

    @Override
    void startPlay() {
        System.out.println("Football Game Started. Enjoy the game!");
    }
}
