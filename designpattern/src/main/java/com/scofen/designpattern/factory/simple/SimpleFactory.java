package com.scofen.designpattern.factory.simple;


import com.scofen.designpattern.factory.Audi;
import com.scofen.designpattern.factory.BMW;
import com.scofen.designpattern.factory.Benz;
import com.scofen.designpattern.factory.Car;

/**
 * Create by  GF  in  15:21 2018/8/2
 * Description:
 * Modified  By:
 */
public class SimpleFactory {
    /**
     * 实现统一管理生产
     * 统一执行标准
     * @param carName
     * @return
     */
    public static Car getCar(String carName){
        if("BMW".equals(carName)){
            return new BMW();
        }else if("Benz".equals(carName)){
            return new Benz();
        }else if("Audi".equals(carName)){
            return new Audi();
        }
        System.out.println("这个车我搞不了");
        return null;
    }
}
