package com.scofen.jdk.io.bio;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Create by  GF  in  17:03 2019/1/21
 * Description:客户端线程,用于处理一个客户端的Socket链路
 * Modified  By:
 */

public class BioServer {

    private static int SERVER_PORT = 5125;

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");

    public static void main(String[] args) {

        BufferedReader in = null;
        PrintWriter out = null;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(SERVER_PORT);
            System.out.println(simpleDateFormat.format(new Date()) + "-------------------------服务端已启动。。。");
            Socket socket = serverSocket.accept();//阻塞
            //当第一个客户端连接进来过后就会执行这句话,
            System.out.println("accept方法通过了....");

            //这个程序如果没有第二个客户端连进,那以下的代码不会执行到的.
            Socket socket2 = serverSocket.accept();
            System.out.println("accept2方法通过了....");

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(),true);

            String result = "";
            while (true){
                if((result=in.readLine()) == null) break;
                System.out.println(simpleDateFormat.format(new Date()) + "-------------------------客户端1:"+result);

                System.out.print(simpleDateFormat.format(new Date()) + "-------------------------服务端,我：");
                Scanner sc = new Scanner(System.in);
                String info = sc.nextLine();
                out.println(info);
            }
            /**
             * 程序有一种情况可以执行到这里,就是socket客户端口断开.不是socket2断开
             * 第一个连接断开后line=reader.readLine();就不会在阻塞了.流中的数据读
             * 完过后跳就跳出了while向下执行了.
             */
            System.out.println("程序结束了");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(in!=null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                in = null;
            }
            if(out!=null){
                out.close();
                out = null;
            }
        }
    }


}
