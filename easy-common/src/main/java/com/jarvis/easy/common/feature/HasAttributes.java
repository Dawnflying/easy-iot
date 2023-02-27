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

    /**
     * 获取属性值
     *
     * @param key
     * @return
     */
    String getAttribute(String key);

    /**
     * @return
     */
    Map<String, Object> getAttributions();

    /**
     * 是否是只读属性，只允许通过设备上传
     *
     * @return
     */
    default boolean isReadOnly() {
        return false;
    }

}
