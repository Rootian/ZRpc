package com.rootian.rpc.core;

/**
 * 1. 消费者调用服务，通过Invoker对象
 * 2. 服务提供者调用 具体实现类，也通过Invoker对象
 */
public interface Invoker {

    /**
     * @Description 返回接口
     * @Author Rootian
     * @Date 2022-08-07
     * @param:
     * @return java.lang.Class
     */
    Class getInterface();


    /**
     * @Description 发起调用【负载均衡、容错、重连..都在这里面了】
     * @Author Rootian
     * @Date 2022-08-07
     * @param: rpcInvocation
     * @return java.lang.Object
     */
    Object invoke(RpcInvocation rpcInvocation) throws Exception;
}
