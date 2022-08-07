package com.rootian.rpc.core.protocol;

import com.rootian.rpc.core.Invoker;

import java.net.URI;

/**
 * @Description RPC协议
 * @Author Rootian
 * @Date 2022-08-02
 */
public interface Protocol {

    void export(URI exportUri, Invoker invoker);
}
