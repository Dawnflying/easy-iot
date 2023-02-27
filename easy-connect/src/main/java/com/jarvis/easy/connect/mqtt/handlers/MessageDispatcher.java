package com.jarvis.easy.connect.mqtt.handlers;

import com.google.common.collect.Maps;
import io.vertx.mqtt.messages.MqttMessage;
import io.vertx.mqtt.messages.MqttPublishMessage;
import io.vertx.mqtt.messages.MqttSubAckMessage;
import io.vertx.mqtt.messages.MqttSubscribeMessage;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MessageDispatcher implements BeanPostProcessor {

    private static final Map<String, MessageHandler> HANDLER_MAP = Maps.newConcurrentMap();

    public void dispatch(String iotId, MqttMessage msg) {
        if (msg instanceof MqttPublishMessage) {
            HANDLER_MAP.get("MqttPublish").handle(iotId, msg);
        }
        if (msg instanceof MqttSubscribeMessage) {
            HANDLER_MAP.get("MqttSubscribe").handle(iotId, msg);
        }
        if (msg instanceof MqttSubAckMessage) {
            HANDLER_MAP.get("MqttSubAck").handle(iotId, msg);
        }
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        if (bean instanceof MessageHandler) {
            MessageHandler handler = (MessageHandler) bean;
            HANDLER_MAP.put(handler.getHandlerId(), handler);
        }

        return bean;
    }
}
