package com.jarvis.easy.common.feature;

import java.util.Map;

/**
 * @author lixiaofei
 */
public interface HasAttributes {

    /**
     * @param key
     * @param value
     */
    void addAttribute(String key, String value);

    /**
     * @param key
     */
    void removeAttribute(String key);

    String getAttribute(String key);

    /**
     * @return
     */
    Map<String, Object> getAttributions();

}
