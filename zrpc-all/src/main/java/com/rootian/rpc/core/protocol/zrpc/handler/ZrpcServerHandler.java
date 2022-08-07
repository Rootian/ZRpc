package com.rootian.rpc.core.protocol.zrpc.handler;

import com.rootian.rpc.common.serialize.Serialization;
import com.rootian.rpc.core.Invoker;
import com.rootian.rpc.core.RpcInvocation;
import com.rootian.rpc.core.RpcResponse;
import com.rootian.rpc.remoting.Handler;
import com.rootian.rpc.remoting.ZRpcChannel;


/**
 * @Description:
 * @Author Rootian
 * @Date 2022-08-02
 */
public class ZrpcServerHandler implements Handler {

    /**
     * @Description 入栈事件
     * @Author Rootian
     * @Date 2022-08-07
     * @param: zRpcChannel
     * @param: message
     * @return void
     */
    @Override
    public void OnReceive(ZRpcChannel zRpcChannel, Object message) throws Exception {
        RpcInvocation rpcInvocation = (RpcInvocation) message;
        System.out.println("收到RpcInvocation消息: " + message);

        // 执行方法
        RpcResponse response = new RpcResponse();
        try {
            Object result = getInvoker().invoke(rpcInvocation);
            response.setStatus(200);
            response.setContent(result);
            System.out.println("服务端执行结果: " + result);
        } catch (Throwable e) {
            response.setStatus(99);
            response.setContent(e.getMessage());
            e.printStackTrace();
        }

        // 序列化执行结果
        byte[] responseBody = getSerialization().serialize(response);
        zRpcChannel.send(responseBody);

    }

    @Override
    public void OnWrite(ZRpcChannel zRpcChannel, Object message) throws Exception {

    }

    private Invoker invoker;

    private Serialization serialization;

    public Serialization getSerialization() {
        return serialization;
    }

    public void setSerialization(Serialization serialization) {
        this.serialization = serialization;
    }

    public Invoker getInvoker() {
        return invoker;
    }

    public void setInvoker(Invoker invoker) {
        this.invoker = invoker;
    }
}
