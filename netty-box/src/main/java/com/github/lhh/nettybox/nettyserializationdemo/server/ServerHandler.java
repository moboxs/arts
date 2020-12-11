package com.github.lhh.nettybox.nettyserializationdemo.server;

import com.github.lhh.nettybox.nettyserializationdemo.model.User;
import com.github.lhh.nettybox.nettyserializationdemo.protocol.Request;
import com.github.lhh.nettybox.nettyserializationdemo.protocol.Response;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ServerHandler extends SimpleChannelInboundHandler<Request> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Request msg) throws Exception {
        System.out.println("服务端接收到消息：" + msg);
        Response response = new Response();
        response.setRequestId(2L);
        response.setError("success");
        User user = new User();
        user.setId(200);
        user.setName("李四");
        response.setResult(user);

        ctx.writeAndFlush(response).addListener((ChannelFutureListener) channelFuture ->
            System.out.println("接口响应：" + msg.getRequestId())
        );
    }
}
