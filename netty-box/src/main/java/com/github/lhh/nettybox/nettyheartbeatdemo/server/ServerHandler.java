package com.github.lhh.nettybox.nettyheartbeatdemo.server;

import com.github.lhh.nettybox.nettyheartbeatdemo.model.RequestInfo;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.internal.StringUtil;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ServerHandler extends SimpleChannelInboundHandler {

    private static final String SUCCESS_KEY = "auth_success_key";
    private static HashMap<String, String> AUTH_IP_MAP = new HashMap<>();
    private static Set<String> AUTH_IP_SET = new HashSet<>();

    static {
        AUTH_IP_MAP.put("192.168.87.78", "123456");
    }

    private boolean auth(ChannelHandlerContext ctx, Object msg) {
        String[] ret = ((String) msg).split(":");
        String auth = AUTH_IP_MAP.get(ret[0]);
        if (!StringUtil.isNullOrEmpty(auth) && auth.equals(ret[1])) {
            ctx.writeAndFlush(SUCCESS_KEY);
            AUTH_IP_SET.add(auth);
            return true;
        } else {
            ctx.writeAndFlush("auth failure ").addListener(ChannelFutureListener.CLOSE);
            return false;
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("server get msg= " + msg);
        if (msg instanceof String) {
            auth(ctx, msg);
        } else if (msg instanceof RequestInfo){
            System.out.println("###################"+System.currentTimeMillis()+"#######################");
            RequestInfo info = (RequestInfo) msg;
            if (!AUTH_IP_SET.contains(info.getIp())) {
                ctx.writeAndFlush("尚未认证的主机！ " + info.getIp());
                return;
            }
            System.out.println("当前主机IP=" + info.getIp());
            System.out.println("当前主机cpu情况：");
            HashMap<String, Object> cpu = info.getCpuPercMap();
            System.out.println("总使用率：" + cpu.get("combined"));
            System.out.println("用户使用率：" + cpu.get("user"));
            System.out.println("系统使用率：" + cpu.get("sys"));
            System.out.println("等待率: " + cpu.get("wait"));
            System.out.println("空闲率: " + cpu.get("idle"));

            System.out.println("当前主机memory情况: ");
            HashMap<String, Object> memory = info.getMemoryMap();
            System.out.println("内存总量: " + memory.get("total"));
            System.out.println("当前内存使用量: " + memory.get("used"));
            System.out.println("当前内存剩余量: " + memory.get("free"));
            System.out.println("--------------------------------------------");

            ctx.writeAndFlush("success");
        } else {
            ctx.writeAndFlush("error").addListener(ChannelFutureListener.CLOSE);
        }
    }
}
