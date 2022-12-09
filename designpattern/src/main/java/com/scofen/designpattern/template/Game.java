package com.scofen.designpattern.template;

/**
 * @Description: TODO
 * @Author gaofeng
 * @Date 7/9/22 7:57 PM
 **/
public abstract class Game {

    void endPlay() {
        System.out.println("Football Game Finished!");
    }

    void initialize() {
        System.out.println("Football Game Initialized! Start playing.");
    }

    void startPlay() {
        System.out.println("Football Game Started. Enjoy the game!");
    }
}
