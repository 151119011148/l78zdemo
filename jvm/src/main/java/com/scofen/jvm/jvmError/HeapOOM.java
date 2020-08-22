package com.scofen.jvm.jvmError;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by  GF  in  21:02 2019/3/2
 * Description: java堆溢出
 * 设置堆最小值最大值20m，相等避免堆自动扩展, dump堆内存快照方便事后分析
 * VM  Args: -Xms20m  -Xmx20m  -XX:+HeapDumpOnOutOfMemoryError
 */
public class HeapOOM {

    static class OOMObject{
    }

    @Test
    public void heap(){
         List<OOMObject> userList = new ArrayList<>();
        while (true){
            userList.add(new OOMObject());
        }
    }
}
