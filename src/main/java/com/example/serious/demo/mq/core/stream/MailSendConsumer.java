package com.example.serious.demo.mq.core.stream;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class MailSendConsumer extends AbstractStreamMessageListener<MailSendMessage> {


    @Override
    public void onMessage(MailSendMessage message) throws Exception {

        log.info("[onMessage][消息内容({})]", message);
        if (message.getId().equals("111")) {
            throw new Exception("异常信息，消费失败");
        }
    }
}
