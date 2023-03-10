package com.jarvis.easy.common.feature;

/**
 * @author lixiaofei
 */
public interface IsTraceable {
    /**
     * 获取traceId
     *
     * @return
     */
    String getTraceId();

    long getCreateTs();

    long getModifiedTs();
}
