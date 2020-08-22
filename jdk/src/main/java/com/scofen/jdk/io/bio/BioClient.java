package com.scofen.jdk.io.bio;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Create by  GF  in  17:05 2019/1/21
 * Description:阻塞式I/O创建的客户端
 * Modified  By:
 */


public class BioClient {

    private static int CLIENT_PORT = 5125;

    private static String CLIENT_ADDRESS = "127.0.0.1";

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");

    public static void main(String[] args) {

        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            while (true){
                socket = new Socket(CLIENT_ADDRESS,CLIENT_PORT);
                out = new PrintWriter(socket.getOutputStream(),true);
                System.out.print(simpleDateFormat.format(new Date()) + "-------------------------客户端,我：");
                Scanner sc = new Scanner(System.in);
                String info = sc.nextLine();

                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out.println(info);

                String serverStr = in.readLine();
                System.out.println(simpleDateFormat.format(new Date()) + "-------------------------服务端："+serverStr);
            }
        }catch (Exception e){
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
            if(socket!=null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                socket = null;
            }
        }
    }


}
