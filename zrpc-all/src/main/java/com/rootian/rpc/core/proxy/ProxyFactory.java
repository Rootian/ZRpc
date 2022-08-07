package com.rootian.rpc.core.proxy;

import com.rootian.rpc.core.Invoker;
import com.rootian.rpc.core.RpcInvocation;

import java.lang.reflect.Method;

/**
 * @Description:
 * @Author Rootian
 * @Date 2022-08-07
 */
public class ProxyFactory {

    public static Invoker getInvoker(Object proxy, Class type) {
        // 构建静态代理对象并返回
        return new Invoker() {
            @Override
            public Class getInterface() {
                return type;
            }

            @Override
            public Object invoke(RpcInvocation rpcInvocation) throws Exception {
                // 反射调用方法
                Method method = proxy.getClass().getMethod(rpcInvocation.getMethodName(), rpcInvocation.getParameterTypes());
                return method.invoke(proxy, rpcInvocation.getArguments());
            }
        };
    }
}
