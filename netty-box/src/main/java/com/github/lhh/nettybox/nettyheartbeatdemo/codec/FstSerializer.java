package com.github.lhh.nettybox.nettyheartbeatdemo.codec;

import org.nustaq.serialization.FSTConfiguration;

public class FstSerializer {
    private static FSTConfiguration configuration = FSTConfiguration.createDefaultConfiguration();

    /**
     * 序列化
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> byte[] serialize(T obj) {
        return configuration.asByteArray(obj);
    }

    /**
     * 反序列化
     * @param data
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T deserialise(byte[] data, Class<T> clazz) {
        return (T) configuration.asObject(data);
    }
}
