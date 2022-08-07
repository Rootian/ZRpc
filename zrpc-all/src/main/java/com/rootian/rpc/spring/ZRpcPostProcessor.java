package com.rootian.rpc.spring;

import com.rootian.rpc.annotation.ZRpcService;
import com.rootian.rpc.common.tools.SpiUtils;
import com.rootian.rpc.config.ProtocolConfig;
import com.rootian.rpc.config.RegistryConfig;
import com.rootian.rpc.config.ServiceConfig;
import com.rootian.rpc.config.util.ZrpcBootstrap;
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

            ServiceConfig serviceConfig = new ServiceConfig();
            serviceConfig.addProtocolConfig(context.getBean(ProtocolConfig.class));
            serviceConfig.addRegistryConfig(context.getBean(RegistryConfig.class));
            serviceConfig.setReference(bean);

            ZRpcService zRpcService = bean.getClass().getAnnotation(ZRpcService.class);
            if (void.class == zRpcService.interfaceClass()) {
                serviceConfig.setService(bean.getClass().getInterfaces()[0]);
            } else {
                serviceConfig.setService(zRpcService.interfaceClass());
            }

            ZrpcBootstrap.export(serviceConfig);

        }

        if (bean.getClass().equals(RegistryConfig.class)) {
            RegistryConfig config = (RegistryConfig)bean;
            System.out.println("找到registry config ： " + config.getAddress());
        }
        return bean;
    }
}
