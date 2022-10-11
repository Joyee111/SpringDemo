package com.example.serious.demo.mq.core.stream;

import cn.hutool.core.date.DateUtil;
import com.example.serious.demo.entity.FileEntity;
import com.example.serious.demo.mq.core.RedisMQTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Mail 邮件相关消息的 Producer
 *
 * @author wangjingyi
 * @date 2021/4/19 13:33
 */
@Slf4j
@Component
public class MailProducer {

    @Resource
    private RedisMQTemplate redisMQTemplate;


    /**
     * 发送 {@link MailSendMessage} 消息
     */
    public void sendMailSendMessage(FileEntity mailAccountDO) {
        MailSendMessage message = new MailSendMessage();
        Map<String, String> stringStringMap = new HashMap<>();
        message.setContent(mailAccountDO.getContent());
        message.setCreaTime(new Date());
        message.setFileName(mailAccountDO.getFileName());
        message.setId(mailAccountDO.getId());
        message.setHeaders(stringStringMap);

        redisMQTemplate.send(message);
    }
}
