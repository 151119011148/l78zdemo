package com.scofen.jvm.jvmError;

import org.junit.Test;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Create by  GF  in  12:13 2019/3/3
 * Description:本机直接内存溢出
 * 使用Unsafe分配本机内存
 * VM  Args: -Xmx20m  -XX:MaxDirectMemorySize = 10M
 */
public class DirectMemoryOOM {

    private static final int _1MB = 1024 * 1024;

    @Test
    public void directMemoryOOM(){
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        try {
            Unsafe unsafe = (Unsafe) unsafeField.get(null);
            while (true){
                unsafe.allocateMemory(_1MB);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
