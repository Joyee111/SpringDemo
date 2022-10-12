package com.example.serious.demo.controller;

import com.example.serious.demo.entity.FileEntity;
import com.example.serious.demo.json.JsonUtils;
import com.example.serious.demo.mq.core.RedisMQTemplate;
import com.example.serious.demo.mq.core.stream.MailProducer;
import com.example.serious.demo.mq.core.stream.MailSendMessage;
import com.example.serious.demo.service.DemoService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.stream.ByteRecord;
import org.springframework.data.redis.connection.stream.PendingMessagesSummary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;


@RestController
@Slf4j
public class indexPage {
    @Autowired
    DemoService demoService;

    @Autowired
    private MailProducer mailProducer;

    @Autowired
    private RedisTemplate<String, ?> redisTemplate;

    @Resource
    private RedisMQTemplate redisMQTemplate;

    @RequestMapping("/index")
    public String index(@RequestBody @Valid FileEntity fileEntity) {

        byte[] bytes = new String("send.index").getBytes();
        PendingMessagesSummary testRedis = redisTemplate.getConnectionFactory().getConnection().xPending(bytes, "testRedis");
        if (testRedis.getTotalPendingMessages() > 0) {
            List<ByteRecord> byteRecords = redisTemplate.getConnectionFactory().getConnection().xRange(bytes, testRedis.getIdRange());
            byteRecords.forEach(record -> {
                redisTemplate.opsForStream().acknowledge("send.index", "testRedis", record.getId());
                Map<byte[], byte[]> value = record.getValue();

                value.values().stream().forEach(v -> {
                    String b = new String(v);
                    log.info("pending信息消费：" + b);
                    redisMQTemplate.send(JsonUtils.parseObject(b, MailSendMessage.class));
                });

            });
        }
        mailProducer.sendMailSendMessage(fileEntity);
        return "index";
    }
}
