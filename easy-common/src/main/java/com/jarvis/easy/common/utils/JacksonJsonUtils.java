package com.jarvis.easy.common.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class JacksonJsonUtils {
    public static String toJSONString(Map map) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(map);
        } catch (Exception ex) {
            LogUtils.DEFAULT_LOGGER.info("JacksonJsonUtils.toJSONString exception", ex);
            return null;
        }
    }

    public static JsonNode parseJson(String jsonStr) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readTree(jsonStr);
        } catch (Exception ex) {
            LogUtils.DEFAULT_LOGGER.info("JacksonJsonUtils.parseJson exception", ex);
            return null;
        }
    }
}
