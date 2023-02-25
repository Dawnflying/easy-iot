package com.jarvis.easy.common.entity;

import com.jarvis.easy.common.feature.TraceableInterface;
import lombok.Data;

import java.util.Map;

/**
 * @author lixiaofei
 */
@Data
public class TimeSeriesSnapshotData implements TraceableInterface {

    /**
     * 快照数据
     */
    private Map<String, Object> snapshot;

    /**
     * 主键时间戳
     */
    private long timestamp;

    /**
     * 创建时间
     */
    private long createTs;

    /**
     * 修改时间
     */
    private long modifiedTs;

    /**
     * 跟踪traceId
     */
    private String traceId;

    @Override
    public String getTraceId() {
        return traceId;
    }

    @Override
    public long getCreateTs() {
        return 0;
    }

    @Override
    public long getModifiedTs() {
        return 0;
    }
}
