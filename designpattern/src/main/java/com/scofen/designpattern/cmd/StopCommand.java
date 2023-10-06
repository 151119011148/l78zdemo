package com.scofen.designpattern.cmd;

/**
 * @Description: TODO
 * @Author gaofeng
 * @Date 7/11/22 9:45 AM
 **/
public class StopCommand implements Command {
    private final AudioPlayer myAudio;

    public StopCommand(AudioPlayer audioPlayer){
        myAudio = audioPlayer;
    }
    @Override
    public void execute() {
        myAudio.stop();
    }

}
