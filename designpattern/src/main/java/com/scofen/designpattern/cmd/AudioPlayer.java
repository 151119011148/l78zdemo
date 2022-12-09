package com.scofen.designpattern.cmd;

/**
 * @Description: TODO
 * @Author gaofeng
 * @Date 7/11/22 9:43 AM
 **/
public class AudioPlayer {

    public void play(){
        System.out.println("播放...");
    }

    public void rewind(){
        System.out.println("倒带...");
    }

    public void stop(){
        System.out.println("停止...");
    }

}
