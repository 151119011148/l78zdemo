package com.scofen.study.designpattern.factory.method;



/**
 * Create by  GF  in  15:53 2018/8/2
 * Description:
 * Modified  By:
 */
public class FactoryTest {


     public static void main(String[] args){

          //工厂方法模式
          //各个产品的生产商，拥有各自的工厂
          //生产工艺，生产的高科技程度不一样
          Factory factory = new AudiFactory();
          System.out.println(factory.getCar());
          //需要用户关心产品的生产商
          factory = new BenzFactory();
          System.out.println(factory.getCar());
          //增加代码的复杂度


     }
}
