package com.rootian.rpc.remoting.netty;

import com.rootian.rpc.remoting.Handler;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;

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

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        System.out.println("经过process管道");
        super.write(ctx, msg, promise);
    }
}
