package com.scofen.jvm.reference;

import java.lang.ref.SoftReference;

/**
 * Create by  GF  in  20:22 2020/4/12
 * Description:
 * 只有在内存不足的时候JVM才会回收该对象。因此，这一点可以很好地用来解决OOM的问题，
 * 并且这个特性很适合用来实现缓存：比如网页缓存、图片缓存等。
 * Modified  By:
 */
public class SoftReferenceTest {


    public static void main(String[] args) {

        SoftReference<byte[]> m = new java.lang.ref.SoftReference<>(new byte[1024*1024*1024]);



    }

}
