package remoting;

import com.rootian.rpc.remoting.Codec;
import com.rootian.rpc.remoting.Handler;
import com.rootian.rpc.remoting.ZRpcChannel;
import com.rootian.rpc.remoting.netty.Netty4Transporter;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author Rootian
 * @Date 2022-08-01
 */
public class NettyTransporterTest {

    public static void main(String[] args) throws URISyntaxException {
        new Netty4Transporter().start(new URI("http://127.0.0.1:8080"),
                new Codec() {
                    @Override
                    public byte[] encode(Object msg) throws Exception {
                        return new byte[0];
                    }

                    @Override
                    public List<Object> decode(byte[] msg) throws Exception {
                        System.out.println("打印请求的内容: " + new String(msg));
                        List<Object> objects = new ArrayList<>();
                        objects.add("1" + new String(msg));
                        objects.add("2" + new String(msg));
                        objects.add("3" + new String(msg));
                        return objects;

                    }
                },
                new Handler() {
                    @Override
                    public void OnReceive(ZRpcChannel zRpcChannel, Object message) throws Exception {
                        System.out.println(message);
                    }

                    @Override
                    public void OnWrite(ZRpcChannel zRpcChannel, Object message) throws Exception {

                    }
                });
    }
}
