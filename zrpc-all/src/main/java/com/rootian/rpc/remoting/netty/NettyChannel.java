package com.rootian.rpc.remoting.netty;

import com.rootian.rpc.remoting.ZRpcChannel;
import io.netty.channel.Channel;


/**
 * @Description:
 * @Author Rootian
 * @Date 2022-08-01
 */
public class NettyChannel implements ZRpcChannel {

    Channel channel;

    public NettyChannel(Channel channel) {
        this.channel = channel;
    }
    @Override
    public void send(byte[] message) {
        channel.writeAndFlush(message);
    }
}
