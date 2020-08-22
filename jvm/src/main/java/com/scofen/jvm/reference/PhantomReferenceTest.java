package com.scofen.jvm.reference;

import com.google.common.collect.Lists;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.List;

/**
 * Create by  GF  in  20:27 2020/4/12
 * Description:虚引用
 * 管理堆外内存，通过虚引用队列queue删除堆外内存
 * Modified  By:
 */
public class PhantomReferenceTest {

    private static final List<Object> list = Lists.newArrayList();
    private static final ReferenceQueue queue = new ReferenceQueue();

    public static void main(String[] args) {

        PhantomReference<String> phantomReference = new PhantomReference<>(new String(), queue);
        System.out.println(phantomReference);
        new Thread(() -> {
            while (true){
                list.add("0");
            }
        }).start();
        System.gc();
        System.out.println(phantomReference);
        new Thread(() -> {
            while (true){
                Reference<Object> poll = queue.poll();
                if (poll != null){
                    System.out.println("虚引用对象被JVM回收了" + poll);
                }
            }
        }).start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}
