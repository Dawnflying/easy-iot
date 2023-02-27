package com.jarvis.easy.connect.session;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class SessionManager {

    private static Map<String, BaseSession> SESSION_MAP = new ConcurrentHashMap<>();

    public static void registerSession(String iotId, BaseSession baseSession) {
        SESSION_MAP.put(iotId, baseSession);
    }

    public static BaseSession getSession(String iotId) {
        return SESSION_MAP.get(iotId);
    }

    public static Optional<MqttSession> getMqttSession(String iotId) {
        BaseSession baseSession = getSession(iotId);
        if (baseSession instanceof MqttSession) {
            return Optional.ofNullable((MqttSession) baseSession);
        }
        return Optional.empty();
    }
}
