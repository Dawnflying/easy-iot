package com.jarvis.easy.common.feature;

/**
 * @author lixiaofei
 */
public interface IdGeneratorInterface {
    default String generateId() {
        return "";
    }
}
