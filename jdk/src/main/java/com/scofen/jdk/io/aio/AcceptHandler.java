package com.scofen.jdk.io.aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * Create by  GF  in  14:35 2019/1/23
 * Description:
 * Modified  By:
 */
//作为handler接收客户端连接
public class AcceptHandler implements CompletionHandler<AsynchronousSocketChannel, AsyncServerHandler> {

    @Override

    public void completed(AsynchronousSocketChannel channel,AsyncServerHandler serverHandler) {

//继续接受其他客户端的请求

        AioServer.clientCount++;

        System.out.println("连接的客户端数：" + AioServer.clientCount);

        serverHandler.channel.accept(serverHandler, this);

//创建新的Buffer

        ByteBuffer buffer = ByteBuffer.allocate(1024);

//异步读 第三个参数为接收消息回调的业务Handler

        channel.read(buffer, buffer, new ReadHandler2(channel));

    }

    @Override

    public void failed(Throwable exc, AsyncServerHandler serverHandler) {

        exc.printStackTrace();

        serverHandler.latch.countDown();

    }

}
