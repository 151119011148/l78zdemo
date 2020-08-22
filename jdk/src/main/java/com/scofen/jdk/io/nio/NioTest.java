package com.scofen.jdk.io.nio;


import java.util.Scanner;

/**
 * Create by  GF  in  9:38 2019/1/23
 * Description:
 * Modified  By:
 */
public class NioTest {

    //测试主方法
    public static void main(String[] args) throws Exception{
//运行服务器
       NioServer.start();
//避免客户端先于服务器启动前执行代码
        Thread.sleep(1000);
//运行客户端
        NioClient.start();
        while(NioClient.sendMsg(new Scanner(System.in).nextLine()));
    }
}
