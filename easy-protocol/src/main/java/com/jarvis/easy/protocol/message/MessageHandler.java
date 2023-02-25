package com.jarvis.easy.protocol.message;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.jarvis.easy.common.entity.MessageData;
import com.jarvis.easy.common.entity.TimeSeriesData;
import com.jarvis.easy.common.utils.GsonUtils;
import com.jarvis.easy.data.timeseries.TimeSeriesPersistentor;
import com.jarvis.easy.data.transform.TimeSeriesTransformer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.Executor;

@Component
public class MessageHandler {

    @Resource
    private EventBus messageEventBus;

    @Resource
    private MessageProcessor messageProcessor;

    @Resource
    private TimeSeriesTransformer timeSeriesTransformer;

    @Resource
    private TimeSeriesPersistentor timeSeriesPersistentor;

    @PostConstruct
    public void init() {
        messageEventBus.register(this);
    }


    @Subscribe
    public void handleEvent(MessageData messageData) {
        System.out.println("Received event: " + GsonUtils.toString(messageData));
        Object result = messageProcessor.process(messageData);
        TimeSeriesData timeSeriesData = timeSeriesTransformer.transform(result);
        timeSeriesPersistentor.process(timeSeriesData);
    }
}
