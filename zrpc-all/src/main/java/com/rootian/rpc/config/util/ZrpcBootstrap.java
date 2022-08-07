package com.rootian.rpc.config.util;

import com.rootian.rpc.common.tools.SpiUtils;
import com.rootian.rpc.config.ProtocolConfig;
import com.rootian.rpc.config.ServiceConfig;
import com.rootian.rpc.core.Invoker;
import com.rootian.rpc.core.protocol.Protocol;
import com.rootian.rpc.core.proxy.ProxyFactory;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URI;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * @Description:
 * @Author Rootian
 * @Date 2022-08-07
 */
public class ZrpcBootstrap {

    /**
     * @Description 暴露service服务
     * @Author Rootian
     * @Date 2022-08-07
     * @param: serviceConfig
     * @return void
     */
    public static void export(ServiceConfig serviceConfig) {
        // 获取invoker实例
        Invoker invoker = ProxyFactory.getInvoker(serviceConfig.getReference(), serviceConfig.getService());
        for (ProtocolConfig protocolConfig : serviceConfig.getProtocolConfigs()) {
            // 对每个协议配置构建URI并暴露
            try {
                // 构建URI
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(protocolConfig.getName() + "://");
                // 此处可选择具体网卡设备 -
//                String hostAddress = NetworkInterface.getNetworkInterfaces().
//                        nextElement().getInetAddresses().nextElement().getHostAddress();
                String hostAddress = getNetworkAddress().get(0);
                stringBuilder.append(hostAddress + ":");
                stringBuilder.append(protocolConfig.getPort() + "/");
                stringBuilder.append(serviceConfig.getService().getName() + "?");
                // ....版本号啥的的不写了，意思一下吧
                stringBuilder.append("transporter=" + protocolConfig.getTransporter());
                stringBuilder.append("&serialization=" + protocolConfig.getSerialization());

                String uri = stringBuilder.toString();

                URI exportUri = new URI(stringBuilder.toString());
                System.out.println("准备暴露服务：" + exportUri);

                // 创建服务
                Protocol protocol = (Protocol) SpiUtils.getServiceImpl(protocolConfig.getName(), Protocol.class);
                protocol.export(exportUri, invoker);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }


        // 构建服务URI

    }

    public static List<String> getNetworkAddress() {
        List<String> result = new ArrayList<String>();
        Enumeration<NetworkInterface> netInterfaces;
        try {
            netInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip;
            while (netInterfaces.hasMoreElements()) {
                NetworkInterface ni = netInterfaces.nextElement();
                Enumeration<InetAddress> addresses = ni.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    ip = addresses.nextElement();
                    if (!ip.isLoopbackAddress() && ip.getHostAddress().indexOf(':') == -1) {
                        result.add(ip.getHostAddress());
                    }
                }
            }
            return result;
        } catch (Exception e) {
            return null;
        }
    }

}
