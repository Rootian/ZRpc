package protocol;

import com.rootian.rpc.common.serialize.Serialization;
import com.rootian.rpc.common.tools.ByteUtil;
import com.rootian.rpc.common.tools.SpiUtils;
import com.rootian.rpc.core.RpcInvocation;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.sql.Time;

/**
 * @Description:
 * @Author Rootian
 * @Date 2022-08-02
 */
public class ClientMock {
    public static void main(String[] args) throws Exception{
        // 模拟client 发送请求， 测试Rpc protocol能否正确解析
        RpcInvocation rpcInvocation = new RpcInvocation();
        rpcInvocation.setId(1);
        rpcInvocation.setServiceName("com.work.sms.api.SmsService");
        rpcInvocation.setParameterTypes(new Class[]{String.class, String.class});
        rpcInvocation.setMethodName("send");
        rpcInvocation.setArguments(new String[]{"10086", "短信"});

        Serialization serialization = (Serialization) SpiUtils.getServiceImpl("JsonSerialization", Serialization.class);

        byte[] body = serialization.serialize(rpcInvocation);

        // 构建header
        ByteBuf buffer = Unpooled.buffer();
        buffer.writeByte(0xda);
        buffer.writeByte(0xbb);
        buffer.writeBytes(ByteUtil.int2bytes(body.length));


        SocketChannel channel = SocketChannel.open();
        channel.connect(new InetSocketAddress("127.0.0.1", 8080));
//        ByteBuffer out = ByteBuffer.wrap(buffer.array());
        ByteBuffer out = buffer.nioBuffer();
        channel.write(out);
        Thread.sleep(5000);
        buffer.clear();
        buffer.writeBytes(body);
        channel.write(ByteBuffer.wrap(buffer.array()));

        ByteBuffer response = ByteBuffer.allocate(1025);
        channel.read(response);
        System.out.println("响应内容");
        System.out.println(new String(response.array()));
    }



}
