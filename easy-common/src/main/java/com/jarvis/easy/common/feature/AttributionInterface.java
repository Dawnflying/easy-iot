package com.jarvis.easy.common.feature;

import java.util.Map;

/**
 * @author lixiaofei
 */
public interface AttributionInterface {

    /**
     * @param key
     * @param value
     */
    void addAttribution(String key, String value);

    /**
     * @param key
     */
    void removeAttribution(String key);

    String getAttribution(String key);

    /**
     * @return
     */
    Map<String, Object> getAttributions();

}
