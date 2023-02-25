package com.jarvis.easy.data.transform;

import com.jarvis.easy.common.annotation.EasyPipeline;
import com.jarvis.easy.common.entity.TimeSeriesData;
import org.springframework.stereotype.Component;

@Component
@EasyPipeline(name = "time-series-transformer")
public class TimeSeriesTransformer {

    public TimeSeriesData transform(Object obj) {
        return new TimeSeriesData();
    }

}
