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
import java.util.concurrent.ConcurrentHashMap;

@Component
public class BootStrap {

    private Map<String, ServerInterface> serverInterfaceMap = new ConcurrentHashMap<>();

    @Resource
    private DeviceGatewayMetaRepository deviceGatewayMetaRepository;

    @PostConstruct
    public void init() {

        List<DeviceGatewayMetaEntity> entityList = deviceGatewayMetaRepository.findAll();

        if (!CollectionUtils.isEmpty(entityList)) {
            entityList.forEach(entity -> {
                String attributes = entity.getAttributes();
                Gson gson = new Gson();
                Map properties = gson.fromJson(attributes, Map.class);
                String type = (String) properties.get("protocol");
                String name = entity.getName();

                if ("mqtt".equals(type)) {
                    DefaultMqttServer server = new DefaultMqttServer(entity.getId() + "", entity.getName(), properties);
                    server.start();
                    serverInterfaceMap.put(name, server);
                }
            });

        }
    }

    public Map<String, ServerInterface> getServerInterfaceMap() {
        return serverInterfaceMap;
    }
}
