package com.tmdaq.district.util;

import com.tmdaq.component.json.JsonHolder;
import com.tmdaq.component.json.jsonwapper.Json;


import java.util.List;
import java.util.Map;

public class JsonUtil {
    private static Json json = JsonHolder.getInstance();

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
