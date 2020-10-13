package com.scofen.designpattern.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Create by  GF  in  16:04 2018/7/24
 * Description:线程池工具类
 * Modified  By:
 */
public class ThreadUtil {
    //固定
    private static final String NEWFIXEDTHREADPOOL = "newFixedThreadPool";
    //单一
    private static final String NEWSINGLETHREADEXECUTOR= "newSingleThreadExecutor";
    //缓冲
    private static final String NEWCACHEDTHREADPOOL = "newCachedThreadPool";

    public static ExecutorService getThreadPool(String type){

        switch (type){
            case "newFixedThreadPool": return Executors.newFixedThreadPool(10);
            case "newSingleThreadExecutor": return Executors.newSingleThreadExecutor();
            case "newCachedThreadPool": return Executors.newCachedThreadPool();
            default:return Executors.newFixedThreadPool(10);
        }



    }


    public static ExecutorService getThreadPool() {
        return getThreadPool(null);
    }

}
