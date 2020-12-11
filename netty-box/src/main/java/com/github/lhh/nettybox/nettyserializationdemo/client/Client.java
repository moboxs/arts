package com.github.lhh.nettybox.nettyserializationdemo.client;

import com.github.lhh.nettybox.nettyserializationdemo.protocol.Request;
import com.github.lhh.nettybox.nettyserializationdemo.protocol.Response;
import com.github.lhh.nettybox.nettyserializationdemo.protocol.TinyDecoder;
import com.github.lhh.nettybox.nettyserializationdemo.protocol.TinyEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

public class Client {
    private final String host;
    private final int port;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public static void main(String[] args) throws Exception {
        final String host = "127.0.0.1";
        final int port = 8801;
        new Client(host, port).start();
    }

    public void start() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(host, port))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline()
                                    .addLast(new TinyEncoder(Request.class))
                                    .addLast(new TinyDecoder(Response.class))
                                    .addLast(new ClientHandler());
                        }
                    });

            ChannelFuture future = b.connect().sync();
            future.channel().closeFuture().sync();

        } finally {
            group.shutdownGracefully();
        }


    }
}
