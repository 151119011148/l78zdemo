package com.scofen.io.bio2;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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
            TimeServerHandlerExecutePool singleExecutor = new TimeServerHandlerExecutePool(50, 1000);
            while (true){
                socket = server.accept();
                singleExecutor.execute(new TimeServerHandler(socket));
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


    static class TimeServerHandlerExecutePool{

        private ExecutorService executorService;

         TimeServerHandlerExecutePool(int maxPoolSize, int queueSize){
            executorService = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
                    maxPoolSize, 120L, TimeUnit.SECONDS,
                    new ArrayBlockingQueue<>(queueSize));
        }

         void execute(Runnable task){
            executorService.execute(task);
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
