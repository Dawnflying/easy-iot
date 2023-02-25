package com.jarvis.easy.message.queue;


import com.jarvis.easy.common.entity.MessageData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Component
@Slf4j
public class MessageDataPersistentQueueManager {

    @Resource
    private MessageDataQueueWorker messageDataQueueWorker;

    public static MessageDataPersistentQueueManager INSTANCE;


    @PostConstruct
    public void init() {
        INSTANCE = this;
    }


    public void save(String topic, String tags, MessageData data) {
        messageDataQueueWorker.addQueue(topic, tags, data);
    }

}
