package com.tmdaq.district.util;

import java.math.BigDecimal;

public class TypeUtil {
    private TypeUtil() {
        throw new UnsupportedOperationException();
    }

    public static String castToString(Object o) {
        if (o == null) return "";
        if (o instanceof String) {
            return ((String) o);
        } else if (o instanceof Number) {
            return o.toString();
        }
        throw new UnsupportedOperationException();
    }
}
