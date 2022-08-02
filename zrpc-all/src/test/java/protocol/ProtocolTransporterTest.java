package protocol;

import com.rootian.rpc.common.serialize.json.JsonSerialization;
import com.rootian.rpc.core.RpcInvocation;
import com.rootian.rpc.core.protocol.zrpc.codec.ZRpcCodec;
import com.rootian.rpc.core.protocol.zrpc.handler.ZrpcServerHandler;
import com.rootian.rpc.remoting.netty.Netty4Transporter;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @Description:
 * @Author Rootian
 * @Date 2022-08-01
 */
public class ProtocolTransporterTest {

    public static void main(String[] args) throws URISyntaxException {
        ZRpcCodec zRpcCodec = new ZRpcCodec();
        zRpcCodec.setSerialization(new JsonSerialization());
        zRpcCodec.setDecodeTypee(RpcInvocation.class);
        ZrpcServerHandler handler = new ZrpcServerHandler();
        new Netty4Transporter().start(new URI("http://127.0.0.1:8080"),
                zRpcCodec, handler
                );
    }
}
