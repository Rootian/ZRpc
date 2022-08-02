package com.rootian.rpc.remoting.netty;

import com.rootian.rpc.remoting.Handler;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;

/**
 * @Description: 处理网络传输内容
 * @Author Rootian
 * @Date 2022-07-30
 */
public class NettyProcessHandler extends ChannelDuplexHandler {

    private Handler handler;

    public NettyProcessHandler(Handler handler) {
        this.handler = handler;
    }
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        handler.OnReceive(new NettyChannel(ctx.channel()), msg);
    }
}
