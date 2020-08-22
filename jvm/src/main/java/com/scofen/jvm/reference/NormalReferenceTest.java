package com.scofen.jvm.reference;

/**
 * Create by  GF  in  20:40 2020/4/12
 * Description:只要某个对象有强引用与之关联，JVM必定不会回收这个对象，
 * 即使在内存不足的情况下，JVM宁愿抛出OutOfMemory错误也不会回收这种对象
 * 如果想中断强引用和某个对象之间的关联，可以显示地将引用赋值为null，
 * 这样一来的话，JVM在合适的时间就会回收该对象。
 * Modified  By:
 */
public class NormalReferenceTest {

    public static void main(String[] args) {
        Object o = new Object();
        System.out.println(o);
        System.gc();
        System.out.println(o);
        o = null;
        System.out.println(o);
    }

}
