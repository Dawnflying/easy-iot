package com.jarvis.easy.data.timeseries;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jarvis.easy.common.annotation.EasyPipeline;
import com.jarvis.easy.common.entity.TimeSeriesData;
import com.jarvis.easy.common.utils.JacksonJsonUtils;
import com.jarvis.easy.data.repo.TimeSeriesDataRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Component
@EasyPipeline(name = "timeseries-processor")
public class TimeSeriesPersistProcessor {

    @Resource
    private TimeSeriesDataRepository timeSeriesDataRepository;


    public void process(TimeSeriesData timeSeriesData) {
        //insert into timescaleDB
        if (CollectionUtils.isEmpty(timeSeriesData.getData())) {
            return;
        }

        List<TimeSeriesDataEntity> entities = timeSeriesData.getData().stream().map(snapshot -> {
            TimeSeriesDataEntity entity = new TimeSeriesDataEntity();
            entity.setTimestamp(Instant.ofEpochMilli(snapshot.getTimestamp()));
            String jsonStr = JacksonJsonUtils.toJSONString(snapshot.getSnapshot());
            entity.setData(JacksonJsonUtils.parseJson(jsonStr));
            return entity;
        }).collect(Collectors.toList());
        timeSeriesDataRepository.saveAllAndFlush(entities);
    }
}
