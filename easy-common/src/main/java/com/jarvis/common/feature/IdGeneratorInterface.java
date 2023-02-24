package com.jarvis.common.feature;

/**
 * @author lixiaofei
 */
public interface IdGeneratorInterface {
    default String generateId() {
        return "";
    }
}
