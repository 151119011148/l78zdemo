package com.scofen.jvm.object;

import org.junit.Test;

import java.util.Random;

/**
 * Create by  GF  in  15:53 2019/3/2
 * Description:
 * VM  Args: -XX:+PrintGCDateStamps -XX:+PrintGCDetails
 -XX:+PrintGC 输出GC日志
 -XX:+PrintGCDetails 输出GC的详细日志
 -XX:+PrintGCTimeStamps 输出GC的时间戳（以基准时间的形式）
 -XX:+PrintGCDateStamps 输出GC的时间戳（以日期的形式，如 2013-05-04T21:53:59.234+0800）
 -XX:+PrintHeapAtGC 在进行GC的前后打印出堆的信息
 -Xloggc:../logs/gc.log 日志文件的输出路径
 */

public class ReferenceCountingGC {

    public Object instance = null;
    private static final  int _1MB = 1024 * 1024;
    private byte[] bigSize = new byte[2 * _1MB];

    @Test
    public void referenceCountingGC(){

        ReferenceCountingGC object1 = new ReferenceCountingGC();
        ReferenceCountingGC object2 = new ReferenceCountingGC();

        object1.instance = object2;
        object2.instance = object1;

        object1 = null;
        object2 = null;

        System.gc();
        Random random = new Random();
        random.nextInt(10);
    }
}
