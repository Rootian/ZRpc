package com.rootian.rpc.config;

import lombok.Data;

/**
 * @Description: 注册中心配置类
 * @Author Rootian
 * @Date 2022-07-30
 */
@Data
public class RegistryConfig {

    public String address;

    public String username;

    public String password;
}
