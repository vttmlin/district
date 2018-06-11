package com.tmdaq.district.bean;

import com.tmdaq.district.util.RefUtil;

import java.util.Map;

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
        if (key != null && !"".equals(key)) {
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

    public String getKey() {
        return key;
    }

    public RequestBean setKey(String key) {
        this.key = key;
        return this;
    }

    public String getKeywords() {
        return keywords;
    }

    public RequestBean setKeywords(String keywords) {
        this.keywords = keywords;
        return this;
    }

    public Integer getSubdistrict() {
        return subdistrict;
    }

    public RequestBean setSubdistrict(Integer subdistrict) {
        this.subdistrict = subdistrict;
        return this;
    }

    public Integer getPage() {
        return page;
    }

    public RequestBean setPage(Integer page) {
        this.page = page;
        return this;
    }

    public Integer getOffset() {
        return offset;
    }

    public RequestBean setOffset(Integer offset) {
        this.offset = offset;
        return this;
    }

    public Extensions getExtensions() {
        return extensions;
    }

    public RequestBean setExtensions(Extensions extensions) {
        this.extensions = extensions;
        return this;
    }

    public String getFilter() {
        return filter;
    }

    public RequestBean setFilter(String filter) {
        this.filter = filter;
        return this;
    }

    public String getCallback() {
        return callback;
    }

    public RequestBean setCallback(String callback) {
        this.callback = callback;
        return this;
    }

    public Output getOutput() {
        return output;
    }

    public RequestBean setOutput(Output output) {
        this.output = output;
        return this;
    }

    public enum Output {
        JSON, XMl
    }

    public enum Extensions {
        base, all
    }


}
