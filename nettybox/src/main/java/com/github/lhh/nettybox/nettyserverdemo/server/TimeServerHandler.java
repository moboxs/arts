package com.github.lhh.nettybox.nettyserverdemo.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class TimeServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //将msg转化成bytebuf
        ByteBuf buf = (ByteBuf)msg;
        //获取缓存区可读字节数,创建字节数组
        byte[] reg = new byte[buf.readableBytes()];

        buf.readBytes(reg);
        String body = new String(reg, "UTF-8");
        System.out.println(Thread.currentThread().getName()+", The server receive order:"+body);

        String respMsg = "I ma Server, 消息接受成功！";
        ByteBuf respByteBuf = Unpooled.copiedBuffer(respMsg.getBytes());
        ctx.write(respByteBuf);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //将消息发送队列中的消息写入到SocketChannel发送给对方
        ctx.flush();
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //当发生异常时，关闭ChannelHandlerContext，释放和它关联的句柄等资源
        cause.printStackTrace();
        ctx.close();
    }
}
