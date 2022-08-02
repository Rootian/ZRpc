package com.rootian.rpc.remoting;

public interface Handler {
    void OnReceive(ZRpcChannel zRpcChannel, Object message) throws Exception;

    void OnWrite(ZRpcChannel zRpcChannel, Object message) throws Exception;
}
