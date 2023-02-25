package com.jarvis.easy.data.timeseries;

import com.jarvis.easy.common.annotation.EasyPipeline;
import com.jarvis.easy.common.entity.TimeSeriesData;
import org.springframework.stereotype.Component;

@Component
@EasyPipeline(name = "timeseries-processor")
public class TimeSeriesPersistentor {

    public void process(TimeSeriesData timeSeriesData) {
        //insert into timescaleDB
    }
}
