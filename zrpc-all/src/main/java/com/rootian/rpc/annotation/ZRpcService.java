package com.rootian.rpc.annotation;


import org.springframework.stereotype.Service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description 标记该类提供ZRpc服务
 * @Author Rootian
 * @Date 2022-07-30
 * @param: null
 * @return
 */
@Target(value = ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Service
public @interface ZRpcService {
    Class<?> interfaceClass() default void.class;
}
