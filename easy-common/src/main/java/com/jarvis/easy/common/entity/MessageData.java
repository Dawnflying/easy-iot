package com.jarvis.easy.common.entity;

import com.jarvis.easy.common.feature.AttributionInterface;
import com.jarvis.easy.common.feature.TraceableInterface;
import lombok.Data;

import java.nio.Buffer;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lixiaofei
 */
@Data
public class MessageData implements TraceableInterface, AttributionInterface {

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

    private Map<String, Object> attributes = new HashMap<>();

    @Override
    public void addAttribution(String key, String value) {
        attributes.put(key, value);
    }

    @Override
    public void removeAttribution(String key) {
        attributes.remove(key);
    }

    @Override
    public String getAttribution(String key) {
        return (String) attributes.get(key);
    }

    @Override
    public Map<String, Object> getAttributions() {
        return attributes;
    }
}
