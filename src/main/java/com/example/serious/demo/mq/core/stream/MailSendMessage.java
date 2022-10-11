package com.example.serious.demo.mq.core.stream;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 邮箱发送消息
 *
 * @author 芋道源码
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MailSendMessage extends AbstractStreamMessage {
    @NotNull(message = "id不能为空")
    private String id;
    @NotNull(message = "fileName不能为空")
    private String fileName;
    @NotNull(message = "content不能为空")
    private String content;
    @NotNull(message = "creaTime不能为空")
    private Date creaTime;

    @Override
    public String getStreamKey() {
        return "send.index";
    }

}
