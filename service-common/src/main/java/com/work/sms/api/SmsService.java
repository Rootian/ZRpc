package com.work.sms.api;

public interface SmsService {

    /**
     * @Description 短信发送功能
     * @Author Rootian
     * @Date 2022-08-02
     * @param: phone
     * @param: content
     * @return java.lang.Object
     */
    Object send(String phone, String content);
}
