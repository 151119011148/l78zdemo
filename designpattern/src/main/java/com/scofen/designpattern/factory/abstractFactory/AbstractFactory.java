package com.scofen.designpattern.factory.abstractFactory;


/**
 * Create by  GF  in  17:20 2018/8/2
 * Description:
 * 1.方便写公共逻辑
 * 2.方便统一管理
 * 3.易于扩展
 * Modified  By:
 */
public abstract class AbstractFactory {

    protected abstract Car getCar();

    public Car getCar(String name){
        if("BMW".equals(name)){
            return new BMWFactory().getCar();//方法工厂
        }else if("Benz".equals(name)){
            return new BenzFactory().getCar();//方法工厂
        }else if("Audi".equals(name)){
        }
        System.out.println("这个车我搞不了");
        return null;
    };

}
