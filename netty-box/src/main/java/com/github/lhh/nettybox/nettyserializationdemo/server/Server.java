package com.github.lhh.nettybox.nettyserializationdemo.server;

import com.github.lhh.nettybox.nettyserializationdemo.protocol.Request;
import com.github.lhh.nettybox.nettyserializationdemo.protocol.Response;
import com.github.lhh.nettybox.nettyserializationdemo.protocol.TinyDecoder;
import com.github.lhh.nettybox.nettyserializationdemo.protocol.TinyEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class Server {

    private int port;

    public Server(int port) {
        this.port = port;
    }

    public static void main(String[] args) {
        try {
            new Server(8801).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() throws Exception {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline()
                            .addLast(new TinyDecoder(Request.class))
                            .addLast(new TinyEncoder(Response.class))
                            .addLast(new ServerHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .option(ChannelOption.SO_KEEPALIVE, true);


            ChannelFuture f = b.bind(port).sync();
            System.out.println(Server.class.getName() + ": start and listen on "+ f.channel().localAddress());
            f.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }

}
