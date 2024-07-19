package com.example.serious.demo.mq.core.stream;

import com.example.serious.demo.entity.FileEntity;
import com.example.serious.demo.mq.core.RedisMQTemplate;
import com.example.serious.demo.util.RedisDelayDeleteAspect;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
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
        //testPrivate();
        redisMQTemplate.send(message);
    }

    /**
     *
     * 其他类调用testPrivate()或redisMQTemplate.testPrivate()时，会触发切面
     * 但sendMailSendMessage方法内调用testPrivate()时，不会触发切面
     * 原因：aop基于MailProducer类实现了一个代理类MailProducerProxy，外部方法调用时是用MailProducerProxy调用，可以走到切面方法；内部调用时不会走MailProducerProxy，也就不会走到切面方法。
     *
     * 解决办法：将需要切面方法的部分新建成一个外部类MailProducerAspectService.class，并调用其方法
     */
    @RedisDelayDeleteAspect
    public void testPrivate(){
        log.info("私有方法");
    }
}
