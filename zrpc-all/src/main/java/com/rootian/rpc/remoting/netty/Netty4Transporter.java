package com.rootian.rpc.remoting.netty;

import com.rootian.rpc.remoting.Server;
import com.rootian.rpc.remoting.Transporter;

import java.net.URI;

/**
 * @Description:
 * @Author Rootian
 * @Date 2022-07-30
 */
public class Netty4Transporter implements Transporter {
    public Server start(URI uri) {
        NettyServer server = new NettyServer();
        server.start(uri);
        return server;
    }
}
