package com.jarvis.easy.connect.mqtt.handlers;

import com.jarvis.easy.common.entity.MessageData;
import com.jarvis.easy.connect.session.BaseSession;
import com.jarvis.easy.connect.session.MqttSession;
import com.jarvis.easy.connect.session.SessionManager;
import com.jarvis.easy.message.queue.MessageDataPersistentQueueManager;
import io.netty.handler.codec.mqtt.MqttQoS;
import io.vertx.core.buffer.Buffer;
import io.vertx.mqtt.messages.MqttPublishMessage;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Optional;

@Component
public class MqttPublishMessageHandler implements MessageHandler<MqttPublishMessage> {


    @PostConstruct
    public void init() {

    }

    @Override
    public void handle(String iotId, MqttPublishMessage message) {
        String topic = message.topicName();
        Buffer payload = message.payload();
        boolean isDup = message.isDup();
        MqttQoS qosLevel = message.qosLevel();
        boolean isRetain = message.isRetain();
        Optional<MqttSession> mqttSession = SessionManager.getMqttSession(iotId);
        if (!mqttSession.isPresent()) {
            return;
        }
        System.out.println("Received message from client [" + mqttSession.get().getEndpoint().clientIdentifier() + "] " + "on topic [" + topic + "] with QoS [" + qosLevel + "]");

        if (isDup) {
            System.out.println("Duplicated");
        }
        if (isRetain) {
            System.out.println("Retained");
        }

        System.out.println("Payload : " + payload.toString());

        MessageData messageData = new MessageData();
        messageData.setMessageId(message.messageId());
        messageData.setPayload(message.payload().getBytes());
        messageData.setDup(isDup);
        messageData.setRetain(isRetain);
        messageData.setTopicName(topic);
        MessageDataPersistentQueueManager.INSTANCE.save(topic, null, messageData);
        //向所有客户端广播
        mqttSession.get().getEndpoint().publish(topic, payload, qosLevel, false, isRetain);
    }

    @Override
    public String getHandlerId() {
        return "MqttPublish";
    }
}
