package com.scofen.study.designpattern.observer.subject;


import com.scofen.study.designpattern.observer.core.EventListener;

/**
 * Create by  GF  in  15:01 2018/11/20
 * Description:
 * Modified  By:
 *
 */
public class Subject extends EventListener {


    public void add(){
        System.out.println("调用添加的方法~");
        trigger(SubjectEventTypeEnum.ADD);
    }


    public void remove(){
        System.out.println("调用删除的方法~");
        trigger(SubjectEventTypeEnum.REMOVE);
    }







}
