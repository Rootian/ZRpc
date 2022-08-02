package com.rootian.rpc.core.protocol.zrpc.handler;

import com.rootian.rpc.core.RpcInvocation;
import com.rootian.rpc.remoting.Handler;
import com.rootian.rpc.remoting.ZRpcChannel;

/**
 * @Description:
 * @Author Rootian
 * @Date 2022-08-02
 */
public class ZrpcServerHandler implements Handler {
    @Override
    public void OnReceive(ZRpcChannel zRpcChannel, Object message) throws Exception {
        RpcInvocation rpcInvocation = (RpcInvocation) message;
        System.out.println("收到RpcInvocation消息: " + message);
    }

    @Override
    public void OnWrite(ZRpcChannel zRpcChannel, Object message) throws Exception {

    }
}
