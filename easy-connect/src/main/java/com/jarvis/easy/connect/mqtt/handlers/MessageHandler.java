package com.jarvis.easy.connect.mqtt.handlers;

public interface MessageHandler<T> {
    public void handle(String iotId, T message);

    String getHandlerId();
}
