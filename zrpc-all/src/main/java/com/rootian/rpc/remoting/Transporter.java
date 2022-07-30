package com.rootian.rpc.remoting;

import java.net.URI;

/**
 * @Description 底层网络传输统一接口
 * @Author Rootian
 * @Date 2022-07-30
 * @param: null
 * @return
 */
public interface Transporter {
    Server start(URI uri);
}
