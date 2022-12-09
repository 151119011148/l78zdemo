package com.scofen.designpattern.cmd;

/**
 * @Description: TODO
 * @Author gaofeng
 * @Date 7/11/22 9:44 AM
 **/
public class PlayCommand implements Command {

    private AudioPlayer myAudio;

    public PlayCommand(AudioPlayer audioPlayer){
        myAudio = audioPlayer;
    }
    /**
     * 执行方法
     */
    @Override
    public void execute() {
        myAudio.play();
    }

}





