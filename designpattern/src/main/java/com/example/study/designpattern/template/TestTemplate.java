package com.scofen.study.designpattern.template;

/**
 * Create by  GF  in  15:54 2019/3/14
 * Description:
 * Modified  By:
 */
public class TestTemplate {

    public static void main(String[] args) {

        Game game = new Cricket();
        game.play();
        System.out.println();
        game = new Football();
        game.play();
    }

}
