package com.tmdaq.district.bean;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class ResBean {
    private String adcode;
    private String level;
    private String name;
    private List<ResBean> districts;
}
