package com.scofen.designpattern.mvc;

import lombok.Getter;

/**
 * @Description: TODO
 * @Author gaofeng
 * @Date 7/9/22 7:52 PM
 **/
@Getter
public class StudentModel {
    private Object name;

    public void setName(Object name) {
        this.name = name;
    }

    public Object getRollNo() {
        return null;
    }
}
