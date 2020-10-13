package com.scofen.io.bio;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * Create by  GF  in  9:03 2019/3/20
 * Description:
 * Modified  By:
 */
public class TimeServer {


    public static void main(String[] args) throws IOException {
        int port = 8080;

        if(args != null && args.length > 0){
            try {
                port = Integer.valueOf(args[0]);
            }catch (NumberFormatException e){

            }
        }

        ServerSocket server = null;

        try {
            server = new ServerSocket(port);
            System.out.println("the timeserver is start in port:" + port);
            Socket socket = null;
            while (true){
                socket = server.accept();
                new Thread(new TimeServerHandler(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (server != null){
                System.out.println("the timeserver is close");
                server.close();
                server = null;
            }
        }


    }

    @NoArgsConstructor
    @AllArgsConstructor
    static class TimeServerHandler implements Runnable{
        private Socket socket;


        @Override
        public void run() {
            BufferedReader in = null;
            PrintWriter out = null;

            try {
                in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
                out = new PrintWriter(this.socket.getOutputStream(), true);
                String currentTime = null;
                String body = null;
                while (true){
                    body = in.readLine();
                    if(body == null) break;
                    System.out.println("the timeserver receive order : " + body);
                    currentTime = "query time order".equalsIgnoreCase(body) ?
                            new Date(System.currentTimeMillis()).toString() :
                            "bad order";
                    out.println(currentTime);
                }
            } catch (Exception e) {
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
                if (this.socket != null){
                    try {
                        this.socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    this.socket = null;
                }
            }

        }
    }

}
