package com.scofen.study.designpattern.prototype.simple;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by  GF  in  14:45 2018/8/14
 * Description:
 * Modified  By:
 */
@Data
@Builder
public class ConcretePrototype implements Cloneable {

    private int age;

    private String name;

    public ArrayList<String> list = new ArrayList();


    protected Object clone() throws CloneNotSupportedException {

        ConcretePrototype prototype = null;
        try {
            prototype = (ConcretePrototype) super.clone();
            prototype.list = (ArrayList) list.clone();
            //克隆基于字节码
            //用反射，或者循环
        }catch(Exception e){
            e.printStackTrace();
        }

        return super.clone();
    }
}
