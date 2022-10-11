package com.example.serious.demo.mq.core;

import com.example.serious.demo.json.JsonUtils;
import com.example.serious.demo.mq.core.message.AbstractRedisMessage;
import com.example.serious.demo.mq.core.stream.AbstractStreamMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.redis.connection.stream.RecordId;
import org.springframework.data.redis.connection.stream.StreamRecords;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Redis MQ 操作模板类
 *
 * @author 芋道源码
 */
@AllArgsConstructor
public class RedisMQTemplate {

    @Getter
    private final RedisTemplate<String, ?> redisTemplate;

    /**
     * 发送 Redis 消息，基于 Redis Stream 实现
     *
     * @param message 消息
     * @return 消息记录的编号对象
     */
    public <T extends AbstractStreamMessage> RecordId send(T message) {

        // 发送消息
        return redisTemplate.opsForStream().add(StreamRecords.newRecord()
                .ofObject(JsonUtils.toJsonString(message)) // 设置内容
                .withStreamKey(message.getStreamKey())); // 设置 stream key

    }


}
