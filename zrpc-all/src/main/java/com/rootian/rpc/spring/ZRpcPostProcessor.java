package com.rootian.rpc.spring;

import com.rootian.rpc.annotation.ZRpcService;
import com.rootian.rpc.common.tools.SpiUtils;
import com.rootian.rpc.config.ProtocolConfig;
import com.rootian.rpc.config.RegistryConfig;
import com.rootian.rpc.remoting.Transporter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @Description: spring启动时扫描其中带有ZRpcservice的类，并进行服务注册
 * @Author Rootian
 * @Date 2022-07-30
 */
public class ZRpcPostProcessor implements ApplicationContextAware, InstantiationAwareBeanPostProcessor {

    private ApplicationContext context;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(ZRpcService.class)) {
            // 发现ZRpc服务提供者, 构建服务配置
            System.out.println("找到需要提供服务的bean，开始启动网络服务，接受请求");

            ProtocolConfig protocolConfig = context.getBean(ProtocolConfig.class);
            Transporter transporter = (Transporter) SpiUtils.getServiceImpl(protocolConfig.getTransporter(), Transporter.class);

//            try {
//                transporter.start(new URI("xxx://127.0.0.1:8080/"));
//            } catch (URISyntaxException e) {
//                e.printStackTrace();
//            }

        }

        if (bean.getClass().equals(RegistryConfig.class)) {
            RegistryConfig config = (RegistryConfig)bean;
            System.out.println("找到registry config ： " + config.getAddress());
        }
        return bean;
    }
}
