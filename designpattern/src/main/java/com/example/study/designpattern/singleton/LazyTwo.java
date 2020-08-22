package com.scofen.study.designpattern.singleton;

/**
 * Create by  GF  in  13:55 2019/3/13
 * Description:
 * 懒汉式
 * (线程安全，同步方法)[不推荐用]
 * Modified  By:
 */
public class LazyTwo {

    private LazyTwo(){}

    private static LazyTwo lazyTwo = null;

    public static synchronized LazyTwo getInstance(){
        if(lazyTwo == null){
            lazyTwo = new LazyTwo();
        }
        return lazyTwo;
    }
}
