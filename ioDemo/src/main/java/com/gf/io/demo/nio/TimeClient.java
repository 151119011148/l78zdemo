package com.gf.io.demo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Create by  GF  in  9:55 2019/3/21
 * Description:
 * Modified  By:
 */
public class TimeClient {

    public static void main(String[] args) {
        int port = 8080;

        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {

            }
        }
        //步骤六：创建Reactor线程，构建多路复用器并启动线程
        new Thread(new TimeClientHandle("127.0.0.1", port), "TimeClient-001").start();
    }


    static class TimeClientHandle implements Runnable {

        private String host;
        private int port;
        private Selector selector;
        private SocketChannel socketChannel;
        private volatile boolean stop;


        TimeClientHandle(String host, int port) {
            this.host = host == null ? "127.0.0.1" : host;
            this.port = port;

            try {

                //步骤一：打开ServerSocketChannel，绑定客户端本地地址
                // （可选，默认系统随机分配一个可用的本地地址）
                socketChannel = SocketChannel.open();
                //步骤二：设为非阻塞模式
                socketChannel.configureBlocking(false);

                selector = Selector.open();

            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }


        }

        @Override
        public void run() {
            try {
                doConnect();
            } catch (IOException e) {
                e.printStackTrace();
            }

            while (!stop) {
                try {
                    //步骤七：多路复用器轮训就绪的key
                    selector.select(1000);
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    SelectionKey key = null;
                    while (iterator.hasNext()) {
                        key = iterator.next();
                        iterator.remove();
                        try {
                            handleInput(key);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
            }

            if(selector != null){
                try {
                    selector.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        private void handleInput(SelectionKey key) throws Exception{

            if (key.isValid()){
                SocketChannel sc = (SocketChannel) key.channel();
                //步骤八：接收connect事件进行处理
                if (key.isConnectable()){

                    //步骤九：判断连接结果，如果连接成功，注册读事件到多路复用器
                    if(sc.finishConnect()){
                        //步骤十：注册读事件到多路复用器
                        sc.register(selector, SelectionKey.OP_READ);

                        doWrite(sc);
                    }else {
                        System.exit(1);
                    }
                    //读消息
                    if (key.isReadable()) {
                        //创建ByteBuffer，并开辟一个1M的缓冲区
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        //读取请求码流，返回读取到的字节数
                        //步骤十一：异步读服务端消息到缓冲区
                        int readBytes = sc.read(buffer);
                        //读取到字节，对字节进行编解码
                        if (readBytes > 0) {
                            //将缓冲区当前的limit设置为position=0，用于后续对缓冲区的读取操作
                            buffer.flip();
                            //根据缓冲区可读字节数创建字节数组
                            byte[] bytes = new byte[buffer.remaining()];
                            //将缓冲区可读字节数组复制到新建的数组中
                            buffer.get(bytes);
                            String body = new String(bytes, "UTF-8");
                            System.out.println("客户端收到消息：now is  " + body);
                        }
                        //没有读取到字节 忽略
                        //	else if(readBytes==0);
                        //链路已经关闭，释放资源
                        else if (readBytes < 0) {
                            key.cancel();
                            sc.close();
                        }
                    }
                }

            }
        }

        private void doConnect()throws IOException{
            //步骤三：异步连接客户端

            if (socketChannel.connect(new InetSocketAddress(host, port))){
                //步骤四：判断连接是否成功，如果连接成功，则直接注册读状态位到多路复用器中
                //否则连接没有成功（异步连接，返回false，说明客户端已经发送sync包，服务端没有
                // 返回ack包，物理链路还没有建立）
                socketChannel.register(selector, SelectionKey.OP_READ);
                doWrite(socketChannel);
            }else {
                //步骤五：向Reactor线程的多路复用器注册OP_CONNECT状态位，监听服务端的TCP ACK应答
                socketChannel.register(selector, SelectionKey.OP_CONNECT);
            }
        }

        private void doWrite(SocketChannel socketChannel) throws IOException {
            byte[] bytes = "query time order".getBytes();
            //根据数组容量创建ByteBuffer
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            //将字节数组复制到缓冲区
            writeBuffer.put(bytes);
            //flip操作
            writeBuffer.flip();
            //发送缓冲区的字节数组
            //步骤十三：调用socketChannel的异步write接口，将消息异步发送给服务端
            socketChannel.write(writeBuffer);
            //步骤十二：对Buffer进行解析，如果有半包消息接收缓冲区Reset，继续读取后续的报文
            // 将解码后的消息封装成task，投递到业务线程池中，进行业务逻辑编排
            if (!writeBuffer.hasRemaining()){
                System.out.println("send order 2 server succeed");
            }
        }

    }

}
