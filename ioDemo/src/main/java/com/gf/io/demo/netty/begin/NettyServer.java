package com.gf.io.demo.netty.begin;

import io.netty.bootstrap.ServerBootstrap;

import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 * Create by  GF  in  21:45 2019/4/13
 * Description:
 * Modified  By:
 */
public class NettyServer {
    private static final int nettyPort = 6666;

    public void start() throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        //默认启动CPU 核心数乘以2个线程，线程什么时候启动：ServerBootstrap.bind的时候启动？
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap()
                    .group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childOption(ChannelOption.SO_KEEPALIVE,true)
                    .childHandler(new ChannelInitializer<Channel>() {
                        @Override
                        protected void initChannel(Channel channel) throws Exception {
                            ChannelPipeline pipeline = channel.pipeline();
                            pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                            //自定义编码器
                            pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
                            //实参处理
                            pipeline.addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
                            pipeline.addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));

                            pipeline.addLast("serverHandler", new TcpServerHandler());
                        }
                    });//业务处理

            //正式启动服务，相当于一个死循环开始轮询
            ChannelFuture future = serverBootstrap.bind("127.0.0.1", nettyPort).sync();
            System.out.println("netty服务器启动成功！");
            future.channel().closeFuture().sync();//sync() TCP三次握手
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        NettyServer server = new NettyServer();
        try {
            server.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("服务器启动报错：" + e.getLocalizedMessage());
        }
    }

    /**
     *
     */
    public class TcpServerHandler extends SimpleChannelInboundHandler<Object> {

        @Override
        protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
            System.out.println("SERVER接收到消息:" + o);
            channelHandlerContext.channel().writeAndFlush("yes, server is accepted you ,nice !" + o);
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx,
                                    Throwable cause) throws Exception {
            System.out.println("Unexpected exception：" + cause.getLocalizedMessage());
            ctx.close();
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            System.out.println("channel is active " + Thread.currentThread().getName());
            super.channelActive(ctx);
        }

        @Override
        public void channelInactive(ChannelHandlerContext ctx) throws Exception {
            System.out.println("channel is inactive " + Thread.currentThread().getName());
            super.channelInactive(ctx);
        }
    }

}
