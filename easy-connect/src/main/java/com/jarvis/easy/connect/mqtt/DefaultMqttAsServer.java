package com.jarvis.easy.connect.mqtt;

import com.jarvis.easy.common.entity.MessageData;
import com.jarvis.easy.connect.servers.AsServerInterface;
import com.jarvis.easy.connect.session.MqttSession;
import com.jarvis.easy.connect.session.SessionManager;
import com.jarvis.easy.message.queue.MessageDataPersistentQueueManager;
import io.netty.handler.codec.mqtt.MqttQoS;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.mqtt.MqttServer;
import io.vertx.mqtt.MqttServerOptions;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.Map;

/**
 * @author lixiaofei
 */
@Data
@AllArgsConstructor
public class DefaultMqttAsServer implements AsServerInterface {

    private String id;

    private String name;
    private Map<String, Object> properties;

    public void start() {
        Vertx vertx = Vertx.vertx();

        MqttServerOptions options = new MqttServerOptions().setPort(1883).setHost("0.0.0.0");

        MqttServer mqttServer = MqttServer.create(vertx, options);

        mqttServer.endpointHandler(endpoint -> {
            SessionManager.registerSession(endpoint.clientIdentifier(), new MqttSession(endpoint));
            endpoint.publishAutoAck(true);
            System.out.println("MQTT client [" + endpoint.clientIdentifier() + "] request to connect, clean session = " + endpoint.isCleanSession());

            if (endpoint.auth() != null) {
                System.out.println("[username = " + endpoint.auth().getUsername() + ", password = " + endpoint.auth().getPassword() + "]");
            }
            if (endpoint.will() != null && endpoint.will().isWillFlag()) {
                System.out.println("[will flag = " + endpoint.will().isWillFlag() + " topic = " + endpoint.will().getWillTopic() + " message = " + endpoint.will().getWillMessage() + " QoS = " + endpoint.will().getWillQos() + "]");
            }

            endpoint.accept(false);

            System.out.println("is it endpoint auto keep alive" + endpoint.isAutoKeepAlive());

            endpoint.publishHandler(message -> {


            });

            endpoint.subscribeHandler(subscribe -> {
                System.out.println("Client [" + endpoint.clientIdentifier() + "] subscribed with QoS levels: ");
                for (int i = 0; i < subscribe.topicSubscriptions().size(); i++) {
                    System.out.println("    " + subscribe.topicSubscriptions().get(i).topicName() + " " + subscribe.topicSubscriptions().get(i).qualityOfService());
                }
                endpoint.subscribeAcknowledge(subscribe.messageId(), Arrays.asList());
            });

            endpoint.unsubscribeHandler(unsubscribe -> {
                System.out.println("Client [" + endpoint.clientIdentifier() + "] unsubscribed from topics: ");
                for (int i = 0; i < unsubscribe.topics().size(); i++) {
                    System.out.println("    " + unsubscribe.topics().get(i));
                }
                endpoint.unsubscribeAcknowledge(unsubscribe.messageId());
            });

            endpoint.disconnectHandler(h -> {
                System.out.println("MQTT client [" + endpoint.clientIdentifier() + "] disconnected");
            });

        }).listen();
    }


    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Map<String, Object> getProperties() {
        return properties;
    }
}

