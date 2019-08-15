package com.tmdaq.district.abs.impl;

import com.tmdaq.district.abs.DoSomeThing;
import com.tmdaq.district.bean.ResBean;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.tmdaq.district.util.JsonUtil.toJsonString;

/**
 * @author vttmlin
 */
public class DoSomeThingImpl implements DoSomeThing {

    @Override
    @SuppressWarnings("unchecked")
    public ResBean readMap2Bean(Map<String, Object> map, ResBean resBean) {
        Map<String, Object> districts = (Map<String, Object>) ((List) map.get("districts")).get(0);
        return digui(districts, Optional.ofNullable(resBean).orElse(new ResBean()));
    }

    @Override
    public boolean writetoFile(String file, String content) {
        try {
            FileUtils.write(new File(file), toJsonString(content), "utf8", false);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    private ResBean digui(Map<String, Object> districts, ResBean resBean) {
        resBean.setAdcode(String.valueOf(districts.get("adcode")));
        resBean.setLevel(String.valueOf(districts.get("level")));
        resBean.setName(String.valueOf(districts.get("name")));
        List<ResBean> list = new ArrayList<>();
        if (districts.get("districts") != null) {
            List districts1 = (List) districts.get("districts");
            if (!districts.isEmpty()) {
                for (Object o : districts1) {
                    list.add(digui(((Map<String, Object>) o), new ResBean()));
                }
            }
        }
        return resBean.setDistricts(list);
    }
}
