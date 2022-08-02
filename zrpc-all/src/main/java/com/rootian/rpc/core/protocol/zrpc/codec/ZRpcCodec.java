package com.rootian.rpc.core.protocol.zrpc.codec;

import com.rootian.rpc.common.serialize.Serialization;
import com.rootian.rpc.common.tools.ByteUtil;
import com.rootian.rpc.remoting.Codec;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: ZRpc 协议解码器
 * @Author Rootian
 * @Date 2022-08-02
 */
public class ZRpcCodec implements Codec {

    /*
     * 协议标识符，魔数
     */
    private final static byte[] MAGIC =  new byte[]{(byte)0xda, (byte)0xbb};

    /*
     * 协议头部长度
     */
    private final static int HEADLEN = 6;


    /*
     * 用来保存没有接收完整的报文
     */
    private ByteBuf tempMsg = Unpooled.buffer();

    /*
     * 序列化工具
     */
    private Serialization serialization;

    /*
     *
     */
    private Class decodeType;


    @Override
    public byte[] encode(Object msg) throws Exception {
        return new byte[0];
    }

    /**
     * @Description ZRpc 解码
     * @Author Rootian
     * @Date 2022-08-02
     * @param: msg
     * @return 解码完成后的RpcInvocation对象数组
     */
    @Override
    public List<Object> decode(byte[] msg) throws Exception {
        List<Object> out = new ArrayList<>();

        ByteBuf message = Unpooled.buffer();
        int tmpMsgSize = tempMsg.readableBytes();

        // 如果有上次未传输完整的报文
        // 则合并报文
        if (tmpMsgSize > 0) {
            message.writeBytes(tempMsg);
            message.writeBytes(msg);
            System.out.println("合并报文");
            System.out.println("合并前报文长度 : " + tmpMsgSize + "  合并后报文长度 : " + message.readableBytes());
        } else {
            message.writeBytes(msg);
        }

        // 循环读取多个请求
        for (;;) {
            // 判断报文是否长度超过一个报头的长度
            // 如果少于，则暂存起来返回
            if (HEADLEN > message.readableBytes()) {
                tempMsg.clear();
                tempMsg.writeBytes(message);
                return out;
            }

            // 解析数据报文
            // 首先循环检查标识符
            byte[] magic = new byte[2];
            message.readBytes(magic);
            for (;;) {
                if (magic[0] != MAGIC[0] || magic[1] != MAGIC[1]) {
                    if (message.readableBytes() == 0) {
                        // 读完了也没读到有效标志符，直接返回
                        tempMsg.clear();
                        tempMsg.writeByte(magic[1]);
                        return out;
                    }
                    // 魔数不符，继续查看下一个Byte
                    magic[0] = magic[1];
                    magic[1] = message.readByte();
                } else {
                    break;
                }
            }

            // 读取请求头
            byte[] lengthBytes = new byte[4];
            message.readBytes(lengthBytes);
            int length = ByteUtil.Bytes2Int_BE(lengthBytes);

            // 判断如果message内容不足length
            // 则说明这个报文没有接收完整，暂存起来返回
            if (length > message.readableBytes()) {
                tempMsg.clear();
                tempMsg.writeBytes(magic);
                tempMsg.writeBytes(lengthBytes);
                tempMsg.writeBytes(message);
                return out;
            }

            // 读取协议内容，并构建RpcInvocation对象
            byte[] body = new byte[length];
            message.readBytes(body);
            Object o = getSerialization().deserialize(body, decodeType);
            out.add(o);
        }
    }

    public Serialization getSerialization() {
        return serialization;
    }

    public Class getDecodeType() {
        return decodeType;
    }

    public void setSerialization(Serialization serialization) {
        this.serialization = serialization;
    }

    public void setDecodeTypee(Class decodeType) {
        this.decodeType = decodeType;
    }
}
