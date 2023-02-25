package com.jarvis.easy.message.eventbus;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

@Configuration
public class EventBusConfig {

    private static final int coreSize = 8;

    private static final int maxSize = 16;

    private static final int keepAlive = 60000;

    private static final BlockingQueue<Runnable> BLOCKING_QUEUE = new ArrayBlockingQueue<>(100000);

    @Bean
    public Executor eventBusExecutor() {
        return new ThreadPoolExecutor(coreSize, maxSize, keepAlive, TimeUnit.SECONDS, BLOCKING_QUEUE);
    }

    @Bean
    public EventBus messageEventBus(Executor eventBusExecutor) {
        return new AsyncEventBus("event-bus-message", eventBusExecutor);
    }

}
