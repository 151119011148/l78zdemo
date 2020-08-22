package com.scofen.jdk.threads.threadPool;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Min;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Create by  GF  in  17:33 2019/2/17
 * Description:
 * Modified  By:
 */
@Data
public class SimpleHttpServer {

    static ThreadPool<HttpRequestHandle> threadPool = new DefaultThreadPool<>(1);
    //SimpleHttpServer的跟路径
    //C:\Users\GF\Desktop\sourceCode\demo\study\util\src\main\resources\static\index.html
    static String basePath = "static/index.html";
    static ServerSocket serverSocket;
    //服务监听端口
    @Min(value = 1)
    static int port = 8080;

    private static void setBasePath(String basePath){
        if (basePath != null && new File(basePath).exists() && new File(basePath).isDirectory()){
            SimpleHttpServer.basePath = basePath;
        }
    }

    //启动SimpleHttpServer
    public static void start()throws Exception{
        serverSocket = new ServerSocket(port);
        Socket socket  = null;
        while ((socket = serverSocket.accept()) != null){
            threadPool.execute(new HttpRequestHandle(socket));
        }
        socket.close();
    }

    public static void main(String[] args) throws Exception {
        start();
    }


    @AllArgsConstructor
    @Data
    static class HttpRequestHandle implements Runnable{

        private Socket socket;

        @Override
        public void run() {

            String line = null;
            BufferedReader bufferedReader = null;
            BufferedReader reader = null;
            PrintWriter out = null;
            InputStream inputStream = null;

            try {
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String header = reader.readLine();
                //由相对路径计算出绝对路径
                String filePath = basePath + header.split(" ")[1];
                out = new PrintWriter(socket.getOutputStream());

                //如果请求资源后缀为jpg或者ico，则读取资源并输出
                if (filePath.endsWith("jpg") || filePath.endsWith("ico")){
                    inputStream = new FileInputStream(filePath);
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    int i = 0;
                    while ((i = inputStream.read()) != 0){
                        byteArrayOutputStream.write(i);
                    }
                    byte[] array = byteArrayOutputStream.toByteArray();
                    out.println("HTTP/1.1 200 OK");
                    out.println("Server: Scofen");
                    out.println("Content-Type: image/jpeg");
                    out.println("Content-Length: " + array.length);
                    out.println("");
                    socket.getOutputStream().write(array, 0, array.length);

                }else {
                    bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
                    out = new PrintWriter(socket.getOutputStream());
                    out.println("HTTP/1.1 200 OK");
                    out.println("Server: Scofen");
                    out.println("Content-Type: text/html;charset=UTF-8");
                    out.println("");
                    while ((line = bufferedReader.readLine()) != null){
                        out.println(line);
                    }
                }
                out.flush();
            } catch (Exception e) {
                out.println("HTTP/1.1 500");
                out.println("");
                out.flush();
            }finally {
                close(bufferedReader, inputStream, reader, out, socket);
            }

        }

        private static void close(Closeable... closeables) {
            if (closeables != null){
                for (Closeable closeable : closeables){
                    try {
                        closeable.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }


}
