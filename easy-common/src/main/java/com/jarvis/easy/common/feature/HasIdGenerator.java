package com.jarvis.easy.common.feature;

/**
 * @author lixiaofei
 */
public interface HasIdGenerator {
    default String generateId() {
        return "";
    }

    default String getId() {
        return "";
    }

    ;
}
