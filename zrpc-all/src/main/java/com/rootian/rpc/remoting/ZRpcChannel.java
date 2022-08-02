package com.rootian.rpc.remoting;

/**
 * @Description 代表一个客户端连接
 * @Author Rootian
 * @Date 2022-08-01
 * @param: null
 * @return
 */
public interface ZRpcChannel {

    void send(byte[] message);
}
