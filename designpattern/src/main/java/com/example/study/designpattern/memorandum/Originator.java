package com.scofen.study.designpattern.memorandum;

import lombok.Data;

/**
 * Create by  GF  in  13:44 2019/3/15
 * Description:
 * Modified  By:
 */
@Data
public class Originator {

    private String state;


    public Memento saveStateToMemento(){
        return new Memento(state);
    }

    public void getStateFromMemento(Memento Memento){
        state = Memento.getState();
    }

}
