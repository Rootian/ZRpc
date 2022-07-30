package com.rootian.rpc.config;

import lombok.Data;

/**
 * @Description: 协议配置类
 * @Author Rootian
 * @Date 2022-07-30
 */
@Data
public class ProtocolConfig {

    public String name;

    public String host;

    public String port;

    public String serialization;

    public String transporter;
}
