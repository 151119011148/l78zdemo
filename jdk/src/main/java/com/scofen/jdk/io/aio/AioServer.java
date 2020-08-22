package com.scofen.jdk.io.aio;

/**
 * Create by  GF  in  14:31 2019/1/23
 * Description:
 * Modified  By:
 */
public class AioServer {
    private static int DEFAULT_PORT = 12345;

    private static AsyncServerHandler serverHandle;

    public volatile static long clientCount = 0;

    public static void start(){

        start(DEFAULT_PORT);

    }

    public static synchronized void start(int port){

        if(serverHandle!=null)

            return;

        serverHandle = new AsyncServerHandler(port);

        new Thread(serverHandle,"Server").start();

    }

    public static void main(String[] args){

        start();

    }
}
