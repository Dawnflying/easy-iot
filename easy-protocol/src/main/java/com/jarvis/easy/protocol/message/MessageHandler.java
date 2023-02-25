package com.jarvis.easy.protocol.message;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.jarvis.easy.common.entity.MessageData;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.Executor;

@Component
public class MessageHandler {

    @Resource
    private EventBus messageEventBus;


    @PostConstruct
    public void init() {
        messageEventBus.register(this);
    }


    @Subscribe
    public void handleEvent(MessageData messageData) {
        System.out.println("Received event: " + messageData.getMessageId());
    }
}
