package com.rootian.rpc.core.protocol;

import java.net.URI;

/**
 * @Description RPC协议
 * @Author Rootian
 * @Date 2022-08-02
 */
public interface Protocol {

    public void export(URI exportUri);
}
