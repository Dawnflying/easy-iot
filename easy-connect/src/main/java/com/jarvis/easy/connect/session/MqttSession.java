package com.jarvis.easy.connect.session;

import com.jarvis.easy.common.feature.HasIdGenerator;
import io.vertx.mqtt.MqttEndpoint;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MqttSession implements BaseSession, HasIdGenerator {

    private MqttEndpoint endpoint;


}
