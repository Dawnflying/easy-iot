package com.jarvis.easy.common.entity;

import com.jarvis.easy.common.feature.TraceableInterface;
import lombok.Data;

import java.nio.Buffer;

/**
 * @author lixiaofei
 */
@Data
public class MessageData implements TraceableInterface {

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
    private byte[] payload;

    private long createTs;

    private long modifiedTs;

    private String traceId;
}
