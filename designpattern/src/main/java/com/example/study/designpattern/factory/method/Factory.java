package com.scofen.study.designpattern.factory.method;


import com.scofen.study.designpattern.factory.Car;

/**
 * 工厂接口，定义了所有工厂的执行标准
 * Create by  GF  in  15:53 2018/8/2
 * Description:
 * Modified  By:
 */
public interface Factory {

     /**
      * 符合汽车上路标准
      * 尾气排放标准
      * 电子设备安全技术
      * 安全囊安全带
      * 轮带耐磨等等
      * @return
      */
     Car getCar();
}
