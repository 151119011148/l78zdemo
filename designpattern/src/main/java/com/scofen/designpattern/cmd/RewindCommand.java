package com.scofen.designpattern.cmd;

/**
 * @Description: TODO
 * @Author gaofeng
 * @Date 7/11/22 9:44 AM
 **/
public class RewindCommand implements Command {

    private final AudioPlayer myAudio;

    public RewindCommand(AudioPlayer audioPlayer){
        myAudio = audioPlayer;
    }
    @Override
    public void execute() {
        myAudio.rewind();
    }

}
