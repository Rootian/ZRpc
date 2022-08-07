package com.rootian.rpc.core.protocol.zrpc;

import com.rootian.rpc.common.serialize.Serialization;
import com.rootian.rpc.common.serialize.json.JsonSerialization;
import com.rootian.rpc.common.tools.SpiUtils;
import com.rootian.rpc.common.tools.URIUtils;
import com.rootian.rpc.core.Invoker;
import com.rootian.rpc.core.RpcInvocation;
import com.rootian.rpc.core.protocol.Protocol;
import com.rootian.rpc.core.protocol.zrpc.codec.ZRpcCodec;
import com.rootian.rpc.core.protocol.zrpc.handler.ZrpcServerHandler;
import com.rootian.rpc.remoting.Transporter;

import java.net.URI;

/**
 * @Description: ZRpc 协议
 * @Author Rootian
 * @Date 2022-08-02
 */
public class ZRpcProtocol implements Protocol {

    /**
     * @Description 导出服务
     * @Author Rootian
     * @Date 2022-08-07
     * @param: exportUri
     * @param: invoker
     * @return void
     */
    @Override
    public void export(URI exportUri, Invoker invoker) {

        String serializationName = URIUtils.getParam(exportUri, "serialization");
        Serialization serialization = (Serialization) SpiUtils.getServiceImpl(serializationName, Serialization.class);

        // 设置编解码器
        ZRpcCodec zRpcCodec = new ZRpcCodec();
        zRpcCodec.setSerialization(serialization);
        zRpcCodec.setDecodeTypee(RpcInvocation.class);

        // 设置请求处理器
        ZrpcServerHandler handler = new ZrpcServerHandler();
        handler.setInvoker(invoker);
        handler.setSerialization(serialization);

        //底层网络框架
        String transporterName = URIUtils.getParam(exportUri, "transporter");
        Transporter transporter = (Transporter) SpiUtils.getServiceImpl(transporterName, Transporter.class);

        // 服务启动
        transporter.start(exportUri, zRpcCodec, handler);
    }
}
