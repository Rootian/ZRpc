package com.rootian.rpc.remoting.netty;

import com.rootian.rpc.remoting.Codec;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;

import java.util.List;

/**
 * @Description:
 * @Author Rootian
 * @Date 2022-08-01
 */
public class NettyCodecHandler extends ChannelDuplexHandler {

    private Codec codec;

    public NettyCodecHandler(Codec codec) {
        this.codec = codec;
    }

    /**
     * @Description  只负责接收网络数据包并将解码完成的数据传输到下一个handler
     * @Author Rootian
     * @Date 2022-08-01
     * @param: ctx
     * @param: msg
     * @return void
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        // 读取数据
        ByteBuf data = (ByteBuf)msg;
        byte[] dataBytes = new byte[data.readableBytes()];
        data.readBytes(dataBytes);

        //解码
        List<Object> out = codec.decode(dataBytes);
        for (Object o : out) {
            ctx.fireChannelRead(o);
        }
        System.out.println("NettyCodecHandler 接受数据请求 : " + msg);


    }
}
