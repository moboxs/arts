package com.github.lhh.nettybox.nettyheartbeatdemo.client;

import com.github.lhh.nettybox.nettyheartbeatdemo.model.RequestInfo;
import com.github.lhh.nettybox.nettyheartbeatdemo.utils.SigarUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ClientHandler extends SimpleChannelInboundHandler {

    private static final String SUCCESS_KEY = "auth_success_key";
    //开启一个线程池，处理心跳包
    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    //定时任务
    private ScheduledFuture<?> heartBeat;
    //主动向服务器发送的认证信息
    private InetAddress address;

    //注册通道
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws UnknownHostException {
        address =InetAddress.getLocalHost();
        System.out.println("address=" + address);
        String ip = "192.168.87.78";
        String key = "123456";
        String auth = ip + ":" + key;
        //发送证书
        ctx.writeAndFlush(auth);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            if (msg instanceof String) {
                String ret =(String) msg;
                if (SUCCESS_KEY.equals(ret)) {
                    this.heartBeat = this.scheduler.scheduleWithFixedDelay(new HeartbeatTask(ctx), 0, 5, TimeUnit.SECONDS);
                    System.out.println("接收消息：" + msg);
                } else {
                    System.out.println("接收信息：" + msg);
                }
            }
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,
                                Throwable cause) {
        //记录错误日志并关闭 channel
        cause.printStackTrace();
        ctx.close();
    }

    private class HeartbeatTask implements Runnable{

        private final ChannelHandlerContext ctx;
        private Integer times = 0;

        public HeartbeatTask(final ChannelHandlerContext ctx) {
            this.ctx = ctx;
        }

        @Override
        public void run() {
            try {
                if (times++ > 10) {
                    closeHeartbeat();
                    return;
                }
                System.out.println("第" + times + "次请求......");
                RequestInfo info = new RequestInfo();
                info.setIp(address.getHostAddress());
                Sigar sigar = SigarUtils.getInstance();
                CpuPerc cpuPerc = sigar.getCpuPerc();
                HashMap<String, Object> cpuPercMap = new HashMap<>();
                cpuPercMap.put("combined", cpuPerc.getCombined());
                cpuPercMap.put("user", cpuPerc.getUser());
                cpuPercMap.put("sys", cpuPerc.getSys());
                cpuPercMap.put("wait", cpuPerc.getWait());
                cpuPercMap.put("idle", cpuPerc.getIdle());

                Mem mem = sigar.getMem();
                HashMap<String, Object> memoryMap = new HashMap<>();
                memoryMap.put("total", mem.getTotal() / 1024L / 1024L);
                memoryMap.put("used", mem.getUsed() / 1024L / 1024L);
                memoryMap.put("free", mem.getFree() / 1024L / 1024L);
                info.setCpuPercMap(cpuPercMap);
                info.setMemoryMap(memoryMap);

                ctx.writeAndFlush(info);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            cause.printStackTrace();
            // 取消定时发送心跳包的任务
            if (heartBeat != null) {
                heartBeat.cancel(true);
                heartBeat = null;
            }
            ctx.fireExceptionCaught(cause);
        }

        public void closeHeartbeat() {
            //取消定时发送心跳包的任务
            if (heartBeat != null) {
                heartBeat.cancel(true);
                heartBeat = null;
            }
        }
    }
}
