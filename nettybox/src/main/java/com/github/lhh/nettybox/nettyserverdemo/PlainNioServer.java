package com.github.lhh.nettybox.nettyserverdemo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class PlainNioServer {

    public void server(int port) throws IOException {
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        ServerSocket serverSocket = serverChannel.socket();
        InetSocketAddress address = new InetSocketAddress(port);
        serverSocket.bind(address);
        Selector selector = Selector.open();
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        final ByteBuffer msg = ByteBuffer.wrap("Hi!\r\n".getBytes());
        for (;;) {
            try {
                selector.select();

            } catch (IOException e) {
                e.printStackTrace();
                break;
            }

            Set<SelectionKey> readyKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = readyKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                iterator.remove();
                try {
                    if (selectionKey.isAcceptable()) {
                        ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
                        //将客户端注册到选择器
                        SocketChannel client = server.accept();
                        client.configureBlocking(false);
                        client.register(selector, SelectionKey.OP_WRITE | SelectionKey.OP_READ, msg.duplicate());
                        System.out.println("Accepted connection from "+client);
                        //检查套接字是否已经准备好写数据
                        if (selectionKey.isWritable()) {
                            SocketChannel channel = (SocketChannel) selectionKey.channel();
                            ByteBuffer buffer = (ByteBuffer) selectionKey.attachment();
                            while (buffer.hasRemaining()) {
                                if (channel.write(buffer) == 0) {
                                    break;
                                }
                            }
                            channel.close();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    selectionKey.cancel();

                    try {
                        selectionKey.channel().close();
                    } catch (IOException ex) {

                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        PlainNioServer server = new PlainNioServer();
        int port = 8201;
        try {
            server.server(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
