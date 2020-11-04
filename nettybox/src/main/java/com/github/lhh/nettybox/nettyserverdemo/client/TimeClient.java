package com.github.lhh.nettybox.nettyserverdemo.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class TimeClient {

    public static void main(String[] args) {
        for (int i=0; i < 3; i++) {
            new Thread(new MyThread()).start();
        }
    }

    static class MyThread implements Runnable {

        @Override
        public void run() {
            connect("localhost", 8201);
        }

        public void connect(String host, int port) {
            //配置客户端NIO线程组
            EventLoopGroup group = new NioEventLoopGroup();
            try {
                Bootstrap b = new Bootstrap();
                b.group(group).channel(NioSocketChannel.class)
                        .option(ChannelOption.TCP_NODELAY, true)
                        .handler(new ChannelInitializer<SocketChannel>() {
                            protected void initChannel(SocketChannel ch) throws Exception {
                                ch.pipeline().addLast(new TimeClientHandler());
                            }
                        });
                ChannelFuture future = b.connect(host, port).sync();
                System.out.println(Thread.currentThread().getName()+",客户端发起异步连接....");
                future.channel().closeFuture().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                group.shutdownGracefully();
            }
        }

    }
}
