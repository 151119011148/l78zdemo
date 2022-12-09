package com.scofen.designpattern.observer.demo5;

/**
 * @Description: TODO
 * @Author gaofeng
 * @Date 7/10/22 10:37 AM
 **/
public class Bug extends BugSubject {

    private int bugLevel = 0;

    @Override
    public int getBugLevel(){
        return bugLevel;
    }

    public void setBugLevel(int bugLevel){
        this.bugLevel = bugLevel;
        this.notifyProgrammer();
    }

    @Override
    public void notifyProgrammer(){
        for(BugObserver programmer:observers){
            if(this.bugLevel>=1){
                if("小华".equals(programmer.getName())){
                    programmer.update(this);
                }
            }
            if(this.bugLevel>=2){
                if("小强".equals(programmer.getName())){
                    programmer.update(this);
                }
            }
        }
    }
}
