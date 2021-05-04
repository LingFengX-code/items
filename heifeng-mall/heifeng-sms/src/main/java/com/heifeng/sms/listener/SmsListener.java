package com.heifeng.sms.listener;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.heifeng.sms.config.SmsProperties;
import com.heifeng.sms.utils.SmsUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;


import java.util.Map;

@Component
@EnableConfigurationProperties(SmsProperties.class)
public class SmsListener {

    /**
     * 核心功能实现的类
     */
    @Autowired
    private SmsUtils smsUtils;

    @Autowired
    private SmsProperties prop;

    static final Logger logger = LoggerFactory.getLogger(SmsListener.class);
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "HEIFENG.SMS.QUEUE", durable = "true"),
            exchange = @Exchange(value = "HEIFENG.SMS.EXCHANGE",
                    ignoreDeclarationExceptions = "true"),
            key = {"sms.verify.code"}))
    public void listenSms(Map<String, String> msg) throws Exception {
        if (msg == null || msg.size() <= 0) {
            // 放弃处理
            return;
        }
        String phone = msg.get("phone");    //要发送的手机号
        String code = msg.get("code");  //要发送的验证码
        logger.info("接收到了消息，手机号为："+phone+"验证码为："+code);
        if (StringUtils.isBlank(phone) || StringUtils.isBlank(code)) {
            // 放弃处理
            return;
        }
        // 发送消息
        SendSmsResponse resp = this.smsUtils.sendSms(phone, code,
                prop.getSignName(),
                prop.getVerifyCodeTemplate());

    }
}

