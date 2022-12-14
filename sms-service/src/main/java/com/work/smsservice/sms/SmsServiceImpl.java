package com.work.smsservice.sms;

import com.rootian.rpc.annotation.ZRpcService;
import com.work.sms.api.SmsService;

import java.util.UUID;

/**
 * @Description:
 * @Author Rootian
 * @Date 2022-07-30
 */
@ZRpcService
public class SmsServiceImpl implements SmsService {
    public Object send(String phone, String content) {
        System.out.println("发送短信：" + phone + ":" + content);
        return "短信发送成功" + UUID.randomUUID().toString();
    }
}
