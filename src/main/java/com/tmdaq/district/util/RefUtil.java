package com.tmdaq.district.util;

import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RefUtil {
    public static Map<String, Object> getNotNullVal(Object o) {
        final Map<String, Object> map = new HashMap<>();
        Arrays.stream(o.getClass().getDeclaredFields()).forEach(i->{
            i.setAccessible(true);
            try {
                if (i.get(o) != null) {
                    map.put(i.getName(), i.get(o));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        return map;
    }

    @SneakyThrows
    @SuppressWarnings("unchecked")
    public static  <T> T invokeDefaultConstructor(String className,Class<T> cls){
        Class<?> classType = Class.forName(className);
        return ((T) classType.newInstance());
    }
}
