package com.rootian.rpc.spring;

import com.rootian.rpc.config.ProtocolConfig;
import com.rootian.rpc.config.RegistryConfig;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.type.AnnotationMetadata;

import java.lang.reflect.Field;

/**
 * @Description:
 * @Author Rootian
 * @Date 2022-07-30
 */
public class ZRpcConfiguration implements ImportBeanDefinitionRegistrar {
    StandardEnvironment environment;

    public ZRpcConfiguration(Environment env) {
        this.environment = (StandardEnvironment)env;
    }

    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry, BeanNameGenerator importBeanNameGenerator) {
        // 注册各种配置文件到spring 容器

        BeanDefinitionBuilder builder = null;

        // 读取protocol com.rootian.rpc.config 配置
        builder = BeanDefinitionBuilder.genericBeanDefinition(ProtocolConfig.class);
        for (Field field : ProtocolConfig.class.getDeclaredFields()) {
            String value = environment.getProperty("zrpc.protocol." + field.getName());
            builder.addPropertyValue(field.getName(), value);
        }
        registry.registerBeanDefinition("protocolConfig", builder.getBeanDefinition());

        // 读取registry com.rootian.rpc.config 配置
        builder = BeanDefinitionBuilder.genericBeanDefinition(RegistryConfig.class);
        for (Field field : RegistryConfig.class.getDeclaredFields()) {
            String value = environment.getProperty("zrpc.registry." + field.getName());
            builder.addPropertyValue(field.getName(), value);
        }
        registry.registerBeanDefinition("registryConfig", builder.getBeanDefinition());

    }
}
