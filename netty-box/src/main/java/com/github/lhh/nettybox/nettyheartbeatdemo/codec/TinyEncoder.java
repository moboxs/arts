package com.github.lhh.nettybox.nettyheartbeatdemo.codec;

import com.github.lhh.nettybox.nettyserializationdemo.codec.FstSerializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

@ChannelHandler.Sharable
public class TinyEncoder extends MessageToByteEncoder {
    private Class<?> genericClass;

    public TinyEncoder(Class<?> genericClass) {
        this.genericClass = genericClass;
    }

    //编码
    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        if (genericClass.isInstance(msg)) {
            byte[] data = FstSerializer.serialize(msg);
            out.writeBytes(data);
        }
    }
}
