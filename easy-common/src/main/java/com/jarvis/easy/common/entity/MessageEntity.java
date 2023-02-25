package com.jarvis.easy.common.entity;

import lombok.Data;

import java.nio.Buffer;

/**
 * @author lixiaofei
 */
@Data
public class MessageEntity {

    /**
     *
     */
    private int messageId;
    /**
     *
     */
    private boolean isDup;
    /**
     *
     */
    private boolean isRetain;
    /**
     *
     */
    private String topicName;
    /**
     *
     */
    private Buffer payload;
}
