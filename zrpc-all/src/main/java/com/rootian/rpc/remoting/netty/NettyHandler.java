package com.rootian.rpc.remoting.netty;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;

/**
 * @Description: 处理网络传输内容
 * @Author Rootian
 * @Date 2022-07-30
 */
public class NettyHandler extends ChannelDuplexHandler {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("传输内容: " + msg);
    }
}
