package com.rootian.rpc.common.serialize;

/**
 * @Description 序列化工具
 * @Author Rootian
 * @Date 2022-08-02
 */
public interface Serialization {

    byte[] serialize(Object o) throws Exception;

    Object deserialize(byte[] input, Class clazz) throws Exception;
}
