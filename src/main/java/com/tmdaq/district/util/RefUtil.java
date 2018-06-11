package com.tmdaq.district.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class RefUtil {
    public static Map<String, Object> getNotNullVal(Object o) {
        Map<String, Object> map = new HashMap<>();
        Field[] declaredFields = o.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            try {
                if (declaredField.get(o) != null) {
                    map.put(declaredField.getName(), declaredField.get(o));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }
}
