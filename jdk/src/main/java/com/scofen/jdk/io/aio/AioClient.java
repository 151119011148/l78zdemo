package com.scofen.jdk.io.aio;

import java.util.Scanner;

/**
 * Create by  GF  in  14:38 2019/1/23
 * Description:
 * Modified  By:
 */
public class AioClient {
    private static String DEFAULT_HOST = "127.0.0.1";

    private static int DEFAULT_PORT = 12345;

    private static AsyncClientHandler clientHandle;

    public static void start(){

        start(DEFAULT_HOST,DEFAULT_PORT);

    }

    public static synchronized void start(String ip,int port){

        if(clientHandle!=null)

            return;

        clientHandle = new AsyncClientHandler(ip,port);

        new Thread(clientHandle,"Client").start();

    }

//向服务器发送消息

    public static boolean sendMsg(String msg) throws Exception{

        if(msg.equals("q")) return false;

        clientHandle.sendMsg(msg);

        return true;

    }

    @SuppressWarnings("resource")

    public static void main(String[] args) throws Exception{

        start();

        System.out.println("请输入请求消息：");

        Scanner scanner = new Scanner(System.in);

        while(sendMsg(scanner.nextLine()));

    }
}
