package com.jarvis.easy.connect.constant;

import io.vertx.mqtt.messages.MqttPublishMessage;
import io.vertx.mqtt.messages.MqttSubAckMessage;
import io.vertx.mqtt.messages.MqttSubscribeMessage;

import java.util.stream.Stream;

public enum MessageTypeEnum {

    PUBLISH_MESSAGE("MqttPublish", MqttPublishMessage.class, ""),
    SUBSCRIBE_MESSAGE("MqttSubscribe", MqttSubscribeMessage.class, ""),
    SUB_ACK_MESSAGE("MqttSubAck", MqttSubAckMessage.class, "");

    MessageTypeEnum(String typeName, Class<?> clazz, String description) {
        this.typeName = typeName;
        this.clazz = clazz;
        this.description = description;
    }

    private String typeName;

    private Class<?> clazz;

    private String description;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MessageTypeEnum of(String typeName) {
        return Stream.of(MessageTypeEnum.values()).
                filter(x -> x.getTypeName().equals(typeName)).findAny().orElse(MessageTypeEnum.PUBLISH_MESSAGE);
    }
}
