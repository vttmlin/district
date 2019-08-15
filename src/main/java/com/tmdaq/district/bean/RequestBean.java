package com.tmdaq.district.bean;

import com.tmdaq.district.util.RefUtil;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

@Data
@Accessors(chain = true)
public class RequestBean {
    private String key;
    private String keywords;
    private Integer subdistrict;
    private Integer page;
    private Integer offset;
    private Extensions extensions;
    private String filter;
    private String callback;
    private Output output;

    public String getUrl() {
        StringBuilder stringBuilder = new StringBuilder(getBaseUrl());
        if (key != null && !key.trim().isEmpty()) {
            stringBuilder.append("?key=").append(key);
        } else {
            return "";
        }
        Map<String, Object> values = RefUtil.getNotNullVal(this);
        if (values != null && values.size() > 0) {
            for (Map.Entry<String, Object> entry : values.entrySet()) {
                stringBuilder.append("&").append(entry.getKey()).append("=").append(entry.getValue());
            }
        }
        return stringBuilder.toString();
    }

    private String getBaseUrl() {
        return "http://restapi.amap.com/v3/config/district";
    }

    public enum Output {
        JSON, XMl
    }

    public enum Extensions {
        base, all
    }

}
