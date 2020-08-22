package com.scofen.study.designpattern.memorandum;

/**
 * Create by  GF  in  13:46 2019/3/15
 * Description:
 * Modified  By:
 */
public class TestMemorandum {

    public static void main(String[] args) {

        CareTaker careTaker = new CareTaker();
        Originator originator = new Originator();

        originator.setState("State #1");
        originator.setState("State #2");
        careTaker.add(originator.saveStateToMemento());

        originator.setState("State #3");
        careTaker.add(originator.saveStateToMemento());

        originator.setState("State #4");
        System.out.println("Current State: " + originator.getState());

        originator.getStateFromMemento(careTaker.get(0));
        System.out.println("First saved State: " + originator.getState());
        originator.getStateFromMemento(careTaker.get(1));
        System.out.println("Second saved State: " + originator.getState());
    }

}
