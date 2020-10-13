package com.scofen.designpattern.singleton;

/**
 * Create by  GF  in  14:36 2019/3/13
 * Description:
 * Modified  By:
 */
public enum LazySix {
    INSTANCE,
    ;
    public static LazySix getInstance() {
        return INSTANCE;
    }

}
