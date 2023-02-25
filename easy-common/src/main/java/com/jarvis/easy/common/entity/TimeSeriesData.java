package com.jarvis.easy.common.entity;

import lombok.Data;

import java.util.List;

/**
 * @author lixiaofei
 */
@Data
public class TimeSeriesData {

    /**
     * 开始时间
     */
    private long startTs;

    /**
     * 结束时间
     */
    private long endTs;

    /**
     * 快照数据列表
     */
    private List<TimeSeriesSnapshotData> data;
}
