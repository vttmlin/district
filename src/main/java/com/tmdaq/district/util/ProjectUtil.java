package com.tmdaq.district.util;

public class ProjectUtil {
    private ProjectUtil(){
        throw new UnsupportedOperationException();
    }

    public static boolean isNotBlank(String str){
        return !isBlank(str);
    }

    public static boolean isBlank(String str){
        return str == null || str.trim().isEmpty();
    }
}
