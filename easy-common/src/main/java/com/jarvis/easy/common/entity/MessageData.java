package com.jarvis.easy.common.entity;

import com.jarvis.easy.common.feature.HasAttributes;
import com.jarvis.easy.common.feature.IsTraceable;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lixiaofei
 */
@Data
public class MessageData implements IsTraceable, HasAttributes {

    /**
     * device unique id;
     */
    private String iot;
    /**
     * message unique id;
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
    public void addAttribute(String key, String value) {
        attributes.put(key, value);
    }

    @Override
    public void removeAttribute(String key) {
        attributes.remove(key);
    }

    @Override
    public String getAttribute(String key) {
        return (String) attributes.get(key);
    }

    @Override
    public Map<String, Object> getAttributions() {
        return attributes;
    }
}
