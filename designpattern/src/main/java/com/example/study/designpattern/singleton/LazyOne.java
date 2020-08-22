package com.scofen.study.designpattern.singleton;

/**
 * Create by  GF  in  13:46 2019/3/13
 * Description:
 * 延时加载
 * 懒汉式
 * (线程不安全)[不可用]
 * Modified  By:
 */
public class LazyOne {

    private LazyOne(){}

    private static LazyOne lazyOne = null;

    public static LazyOne getInstance(){
        if(lazyOne == null){
            lazyOne = new LazyOne();
        }
        return lazyOne;
    }
}
