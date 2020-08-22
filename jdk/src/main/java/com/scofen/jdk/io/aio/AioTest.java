package com.scofen.jdk.io.aio;

import java.util.Scanner;

/**
 * Create by  GF  in  14:41 2019/1/23
 * Description:
 * Modified  By:
 */
public class AioTest {

    //测试主方法
    public static void main(String[] args) throws Exception{

        //运行服务器

        AioServer.start();

        //避免客户端先于服务器启动前执行代码

        Thread.sleep(100);

        //运行客户端

        AioClient.start();

        System.out.println("请输入请求消息：");

        Scanner scanner = new Scanner(System.in);

        while(AioClient.sendMsg(scanner.nextLine()));

    }
}
