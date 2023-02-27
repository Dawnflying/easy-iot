package com.jarvis.easy.connect.servers;

import com.google.gson.Gson;
import com.jarvis.easy.connect.http.DefaultHttpAsServer;
import com.jarvis.easy.connect.mqtt.DefaultMqttAsServer;
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

    private Map<String, AsServerInterface> SERVER_MAP = new ConcurrentHashMap<>();

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
                    DefaultMqttAsServer server = new DefaultMqttAsServer(entity.getId() + "", entity.getName(), properties);
                    server.start();
                    SERVER_MAP.put(name, server);
                }

                if ("http".equals(type)) {
                    DefaultHttpAsServer server = new DefaultHttpAsServer();
                    server.start();
                    SERVER_MAP.put(name, server);
                }
            });
        }
    }

    public Map<String, AsServerInterface> getServerMap() {
        return SERVER_MAP;
    }
}
