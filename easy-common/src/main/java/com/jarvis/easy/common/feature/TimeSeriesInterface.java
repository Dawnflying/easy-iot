package com.jarvis.easy.common.feature;

import com.jarvis.easy.common.entity.TimeSeriesData;
import com.jarvis.easy.common.entity.TimeSeriesSnapshotData;

import java.util.List;

/**
 * @author lixiaofei
 */
public interface TimeSeriesInterface {

    /**
     * 获取时序历史数据
     *
     * @return
     */
    TimeSeriesData getData();

    /**
     * 获取时序数据快照
     *
     * @return
     */
    TimeSeriesSnapshotData getSnapshot();


    /**
     * 获取时序历史数据
     *
     * @return
     */
    TimeSeriesData getDataSelectedColumn(List<String> keys);

    /**
     * 获取时序数据快照
     *
     * @return
     */
    TimeSeriesSnapshotData getSnapshotSelectedColumn(List<String> keys);
}
