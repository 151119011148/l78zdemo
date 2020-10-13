package com.scofen.designpattern.memorandum;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by  GF  in  13:45 2019/3/15
 * Description:
 * Modified  By:
 */
public class CareTaker {

    private List<Memento> mementoList = new ArrayList<>();

    public void add(Memento memento){
        mementoList.add(memento);
    }

    public Memento get(int index){
        return mementoList.get(index);
    }

}
