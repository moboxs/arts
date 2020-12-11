package com.github.lhh.nettybox.nettyheartbeatdemo.codec;

import com.github.lhh.nettybox.nettyserializationdemo.codec.FstSerializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class TinyDecoder extends ByteToMessageDecoder {
    private Class<?> genericClass;

    public TinyDecoder(Class<?> genericClass) {
        this.genericClass = genericClass;
    }

    /**
     * 解码
     * @param ctx
     * @param in
     * @param out
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int size = in.readableBytes();
        byte[] data = new byte[size];
        in.readBytes(data);
        Object obj = FstSerializer.deserialise(data, genericClass);
        out.add(obj);
    }
}
