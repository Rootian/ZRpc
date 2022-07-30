package com.rootian.rpc.annotation;

import org.springframework.context.annotation.Import;
import com.rootian.rpc.spring.ZRpcConfiguration;
import com.rootian.rpc.spring.ZRpcPostProcessor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description 启动ZRpc功能
 * @Author Rootian
 * @Date 2022-07-30
 * @param: null
 * @return
 */
@Target(value = ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({ZRpcPostProcessor.class, ZRpcConfiguration.class})
public @interface EnableZRpc {

}
