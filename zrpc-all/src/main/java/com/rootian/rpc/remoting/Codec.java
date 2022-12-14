package com.rootian.rpc.remoting;

import java.util.List;

public interface Codec {

    byte[] encode(Object msg) throws Exception;

    List<Object> decode(byte[] msg) throws Exception;
}
