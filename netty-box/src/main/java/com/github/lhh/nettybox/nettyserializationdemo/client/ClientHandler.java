package com.github.lhh.nettybox.nettyserializationdemo.client;

import com.github.lhh.nettybox.nettyserializationdemo.model.User;
import com.github.lhh.nettybox.nettyserializationdemo.protocol.Request;
import com.github.lhh.nettybox.nettyserializationdemo.protocol.Response;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ClientHandler extends SimpleChannelInboundHandler<Response> {

    //注册通道
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
    }

    /**
     *服务器连接被建立后调用
     *
     * @param ctx
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        Request request = new Request();
        request.setRequestId(3L);
        User user = new User();
        user.setId(100);
        user.setName("张三");
        request.setParameters(user);

        ctx.writeAndFlush(request);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Response msg) throws Exception {
        System.out.println("接收服务器消息："+msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
