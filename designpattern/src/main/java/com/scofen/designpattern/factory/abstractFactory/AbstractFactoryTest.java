package com.scofen.designpattern.factory.abstractFactory;


/**
 * Create by  GF  in  17:20 2018/8/2
 * Description:
 * Modified  By:
 */
public  class AbstractFactoryTest {

     public static void main(String[] args ){
         //设计模式的经典之处，在于解决了编写代码的人和调用代码的人双方的痛楚
         //解放了我们的双手
         DefaultFactory defaultFactory = new DefaultFactory();
         System.out.println(defaultFactory.getCar());
         System.out.println(defaultFactory.getCar("BMW"));
     }

}
