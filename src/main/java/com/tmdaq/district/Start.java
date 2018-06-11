package com.tmdaq.district;

import com.tmdaq.district.bean.RequestBean;
import com.tmdaq.district.bean.ResBean;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.tmdaq.district.bean.RequestBean.Extensions.base;
import static com.tmdaq.district.bean.RequestBean.Output.JSON;
import static com.tmdaq.district.util.HttpUtil.get;
import static com.tmdaq.district.util.JsonUtil.json2Map;
import static com.tmdaq.district.util.JsonUtil.toJsonString;

public class Start {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {
        if (args == null || args.length <= 0) {
            return;
        }
        String output = null;
        String key = null;
        for (String arg : args) {
            String[] split = arg.split("=");
            if ("key".equals(split[0])) {
                key = split[1];
            }
            if ("output".equals(split[0])) {
                output = split[1];
            }
        }
        assert key != null && !"".equals(key) : "key值无效 清申请高德web端的key";
        assert output != null && !"".equals(output) : "请设置output的值 output为要输出的文件名";
        RequestBean requestBean = new RequestBean();
        String url = requestBean.setKey(key)
                .setOutput(JSON).setExtensions(base).setSubdistrict(10).getUrl();
        System.out.println("发送请求中..");
        Map<String, Object> map = json2Map(get(url));
        System.out.println("从高德数据接口获取数据成功 准备写入文件");
        Map<String, Object> districts = (Map<String, Object>) ((List) map.get("districts")).get(0);
        ResBean digui = digui(districts, new ResBean());
        FileUtils.write(new File(output), toJsonString(digui), "utf8", false);
        System.out.println("行政区划已写到 " + output);
    }

    @SuppressWarnings("unchecked")
    private static ResBean digui(Map<String, Object> districts, ResBean resBean) {
        resBean.setAdcode(String.valueOf(districts.get("adcode")));
        resBean.setLevel(String.valueOf(districts.get("level")));
        resBean.setName(String.valueOf(districts.get("name")));
        List<ResBean> list = new ArrayList<>();
        if (districts.get("districts") != null) {
            List districts1 = (List) districts.get("districts");
            if (districts != null && districts.size() > 0) {
                for (Object o : districts1) {
                    list.add(digui(((Map<String, Object>) o), new ResBean()));
                }
            }
        }
        resBean.setDistricts(list);
        return resBean;
    }
}
