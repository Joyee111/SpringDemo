package com.example.serious.demo.mq.core.stream;

import lombok.extern.slf4j.Slf4j;
import org.redisson.client.RedisConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class MailSendConsumer extends AbstractStreamMessageListener<MailSendMessage> {


    @Override
    public void onMessage(MailSendMessage message) {

        log.info("[onMessage][消息内容({})]", message);

    }
}
