package com.scofen.io.demo.nio;

import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * Create by  GF  in  14:08 2019/3/20
 * Description:
 * Modified  By:
 */
public class TimeServer {

    public static void main(String[] args) {
        int port = 8080;

        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException ignored) {

            }
        }
        MutiplexerTimeServer timeServer = new MutiplexerTimeServer(port);
        new Thread(timeServer, "NIO-MutipleTimeServer-001").start();

    }


    private static class MutiplexerTimeServer implements Runnable {

        private Selector selector;
        private ServerSocketChannel serverSocketChannel;
        private volatile boolean stop;

        public MutiplexerTimeServer(int port) {
            try {

                //步骤一：打开ServerSocketChannel，监听客户端的连接，它是所有客户端连接的父管道
                serverSocketChannel = ServerSocketChannel.open();
                //步骤二：绑定监听端口，设为非阻塞模式
                serverSocketChannel.configureBlocking(false);
                serverSocketChannel.bind(new InetSocketAddress(port), 1024);
                //步骤三：创建Reactor线程，创建多路复用器
                selector = Selector.open();
                //步骤四：将ServerSocketChannel注册到Selector，监听ACCEPT操作事件
                serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
                System.out.println("the timeserver is start in port : " + port);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void run() {
            //步骤五：循环遍历selector，轮训就绪的key
            while (!stop) {
                try {

                    //无论是否有读写事件发生，selector每隔1s被唤醒一次
                    selector.select(1000);
                    //阻塞,只有当至少一个注册的事件发生的时候才会继续.
                    //	selector.select();
                    Set<SelectionKey> keys = selector.selectedKeys();
                    Iterator<SelectionKey> it = keys.iterator();
                    SelectionKey key = null;
                    while (it.hasNext()) {
                        key = it.next();
                        it.remove();
                        try {
                            handleInput(key);
                        } catch (Exception e) {
                            if (key != null) {
                                key.cancel();
                                if (key.channel() != null) {
                                    key.channel().close();
                                }
                            }
                        }
                    }
                } catch (Throwable t) {
                    t.printStackTrace();
                }
            }
            //多路复用器selector关闭后，所有注册在上面的channel和pipe等资源
            // 会自动释放里面管理的资源，不需要重复释放资源
            if (selector != null)
                try {
                    selector.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }

        private void handleInput(SelectionKey key) throws IOException {
            if (key.isValid()) {
                //处理新接入的请求消息
                if (key.isAcceptable()) {
                    ServerSocketChannel ssc = (ServerSocketChannel) key.channel();

                    //通过ServerSocketChannel的accept创建SocketChannel实例
                    //步骤六：多路复用器监听到新的客户端接入，
                    // 处理新的接入请求，完成TCP三次握手，TCP物理链路正式建立
                    SocketChannel sc = ssc.accept();

                    //步骤七：设为非阻塞模式
                    sc.configureBlocking(false);

                    //步骤八：将新接入的客户端连接注册到Reactor线程的多路复用器上，
                    // 监听读操作，读取客户端发送的网络消息
                    sc.register(selector, SelectionKey.OP_READ);
                }
                //读消息
                if(key.isReadable()){

                    SocketChannel sc = (SocketChannel) key.channel();

                    //创建ByteBuffer，并开辟一个1M的缓冲区
                    ByteBuffer buffer = ByteBuffer.allocate(1024);

                    //步骤九：异步读取客户端请求消息到缓冲区
                    int readBytes = sc.read(buffer);

                    /**
                     * >0  读到了字节，对字节进行编解码
                     * =0  没有读到字节，正常情景，忽略
                     * -1   链路已经关闭，需要关闭socketchannel，释放资源
                     */
                    //读取到字节，对字节进行编解码
                    if(readBytes>0){

                        //将缓冲区当前的limit设置为position=0，用于后续对缓冲区的读取操作
                        buffer.flip();

                        //根据缓冲区可读字节数创建字节数组
                        byte[] bytes = new byte[buffer.remaining()];

                        //将缓冲区可读字节数组复制到新建的数组中
                        buffer.get(bytes);
                        String body = new String(bytes,"UTF-8");
                        System.out.println("the timeserver receive order : " + body);
                        String currentTime = "query time order".equalsIgnoreCase(body) ?
                                new Date(System.currentTimeMillis()).toString():
                                "bad order";
                        //发送应答消息
                        doWrite(sc,currentTime);
                    }
                    //没有读取到字节 忽略
                    //	else if(readBytes==0);
                    //链路已经关闭，释放资源
                    else if(readBytes<0){
                        key.cancel();
                        sc.close();
                    }
                }
            }
        }

        //异步发送应答消息
        private void doWrite(SocketChannel channel,String response) throws IOException{

            if(!StringUtils.isEmpty(response)){
                //将消息编码为字节数组
                byte[] bytes = response.getBytes();

                //根据数组容量创建ByteBuffer
                ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);

                //将字节数组复制到缓冲区
                writeBuffer.put(bytes);

                //flip操作
                //将缓冲区当前的limit设置为position，position设为0，用于后续读取
                writeBuffer.flip();

                //步骤十一：将pojo对象encode成ByteBuffer,调用SocketChannel的异步write接口，
                // 将消息异步发送给客户端
                /**
                 * 由于SocketChannel是异步非阻塞的，不保证一次能把所需要发送的字节数组发送完
                 * 会出现写半包的问题
                 * 我们需要注册写操作，不断轮询Selector将没有发送完的ByteBuffer发送完毕，
                 * 最后通过ByteBuffer的hasRemain（）方法判断消息是否发送完成；
                 */
                channel.write(writeBuffer);

                //步骤十：对ByteBuffer进行编解码，如果有半包消息指针reset，
                // 继续读取后续的报文，将解码成功的消息封装成Task，
                // 投递到业务线程池中，进行业务逻辑编排
/*                Object message = null;
                while (writeBuffer.hasRemaining()){
                    writeBuffer.mark();
                     message = decode(writeBuffer);
                     if(message == null){
                         writeBuffer.reset();
                         break;
                     }
                    messageList.add(message);
                }
                if(!writeBuffer.hasRemaining()){
                    writeBuffer.clear();
                }else {
                    writeBuffer.compact();
                }
                if(messageList != null & !messageList.isEmpty()){
                    for (Object messageE : messageList){
                        handlerTask(messageE);
                    }
                }*/

            }

        }

    }


}
