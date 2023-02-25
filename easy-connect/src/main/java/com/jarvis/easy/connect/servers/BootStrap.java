package com.jarvis.easy.connect.servers;

import com.google.gson.Gson;
import com.jarvis.easy.connect.mqtt.DefaultMqttServer;
import com.jarvis.easy.data.entity.DeviceGatewayMetaEntity;
import com.jarvis.easy.data.repo.DeviceGatewayMetaRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Component
public class BootStrap {

    private Map<String, ServerInterface> serverInterfaceMap;

    @Resource
    private DeviceGatewayMetaRepository deviceGatewayMetaRepository;

    @PostConstruct
    public void init() {

        List<DeviceGatewayMetaEntity> entityList = deviceGatewayMetaRepository.findAll();

        if (!CollectionUtils.isEmpty(entityList)) {
            entityList.forEach(entity -> {
                String attributes = entity.getAttributes();
                DefaultMqttServer server = new DefaultMqttServer();
                Gson gson = new Gson();
                Map propeties = gson.fromJson(attributes, Map.class);
                server.start(propeties);
            });

        }
    }
}
