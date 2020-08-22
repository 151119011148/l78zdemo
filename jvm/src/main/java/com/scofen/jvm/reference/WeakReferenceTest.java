package com.scofen.jvm.reference;

import java.lang.ref.WeakReference;

/**
 * Create by  GF  in  20:25 2020/4/12
 * Description:
 * 弱引用，只要发生垃圾回收，就没了
 * xms=20m
 * Modified  By:
 */
public class WeakReferenceTest {

    public static void main(String[] args) {
        WeakReference<byte[]> m = new WeakReference<>(new byte[1024*1024*10]);
        System.out.println(m.get());
        System.gc();
        System.out.println(m.get());
        byte[] m2 = new byte[15*1024*1024];
        System.out.println(m.get());

    }

}
