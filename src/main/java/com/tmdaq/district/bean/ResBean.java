package com.tmdaq.district.bean;

import java.util.List;

public class ResBean {
    private String adcode;
    private String level;
    private String name;
    private List<ResBean> districts;

    public String getAdcode() {
        return adcode;
    }

    public ResBean setAdcode(String adcode) {
        this.adcode = adcode;
        return this;
    }

    public String getLevel() {
        return level;
    }

    public ResBean setLevel(String level) {
        this.level = level;
        return this;
    }

    public String getName() {
        return name;
    }

    public ResBean setName(String name) {
        this.name = name;
        return this;
    }

    public List<ResBean> getDistricts() {
        return districts;
    }

    public ResBean setDistricts(List<ResBean> districts) {
        this.districts = districts;
        return this;
    }

    @Override
    public String toString() {
        String s = "ResBean{" +
                "adcode='" + adcode + '\'' +
                ", level='" + level + '\'' +
                ", name='" + name + '\'';
        if (districts != null && districts.size() > 0) {
            s += ", districts=" + districts;
        }
        s += "}";
        return s;
    }
}
