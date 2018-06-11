package com.tmdaq.district.util;

import com.tmdaq.JsonHolder;
import com.tmdaq.jsonwapper.Json;

import java.util.List;
import java.util.Map;

public class JsonUtil {
    private static Json json = new JsonHolder().getJson();

    @SuppressWarnings("unchecked")
    public static Map<String, Object> json2Map(String str) {
        return json.readValue(str);
    }

    @SuppressWarnings("unchecked")
    public static List<Object> json2List(String str) {
        return json.readValueFromList(str);
    }

    public static String toJsonString(Object o) {
        return json.toJsonString(o);
    }
}
