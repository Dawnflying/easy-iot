package com.jarvis.easy.common.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class GsonUtils {

    public static <T> T parseObject(String jsonStr, Class<T> tClass) {
        Gson gson = new Gson();
        Type type = TypeToken.get(tClass).getType();
        return gson.fromJson(jsonStr, type);
    }

    public static <T> String toString(Object obj) {
        Gson gson = new Gson();
        return gson.toJson(obj);
    }
}
