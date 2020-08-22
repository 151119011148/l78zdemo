package com.scofen.study.designpattern.singleton;

/**
 * Create by  GF  in  14:25 2019/3/13
 * Description:线程安全；延迟加载；效率较高
 * 双重检查[推荐用]
 *
 * Modified  By:
 */
public class LazyFour {

    private static volatile LazyFour lazyFour;

    private LazyFour() {}

    public static LazyFour getInstance() {
        if (lazyFour == null) {
            synchronized (LazyFour.class) {
                if (lazyFour == null) {
                    lazyFour = new LazyFour();
                }
            }
        }
        return lazyFour;
    }
}
