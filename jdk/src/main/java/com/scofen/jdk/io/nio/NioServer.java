package com.scofen.jdk.io.nio;

/**
 * Create by  GF  in  9:15 2019/1/23
 * Description:
 * Modified  By:
 */
public class NioServer {

    private static ServerHandle serverHandle;

    public static void start(){
        int DEFAULT_PORT = 12345;
        start(DEFAULT_PORT);
    }

    public static synchronized void start(int port){
        if(serverHandle!=null)
            serverHandle.stop();
        serverHandle = new ServerHandle(port);
        new Thread(serverHandle,"Server").start();
    }

    public static void main(String[] args){
        start();
    }

}
