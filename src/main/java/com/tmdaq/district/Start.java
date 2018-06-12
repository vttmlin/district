package com.tmdaq.district;

import com.tmdaq.district.abs.DoSomeThing;
import com.tmdaq.district.bean.RequestBean;
import com.tmdaq.district.bean.ResBean;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import static com.tmdaq.district.bean.RequestBean.Extensions.base;
import static com.tmdaq.district.bean.RequestBean.Output.JSON;
import static com.tmdaq.district.util.HttpUtil.get;
import static com.tmdaq.district.util.JsonUtil.json2Map;
import static com.tmdaq.district.util.JsonUtil.toJsonString;

public class Start {
    private static DoSomeThing doSomeThing;

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
        init();
        RequestBean requestBean = new RequestBean();
        String url = requestBean.setKey(key)
                .setOutput(JSON).setExtensions(base).setSubdistrict(10).getUrl();
        System.out.println("发送请求中..");
        /*原始的map*/
        Map<String, Object> map = json2Map(get(url));
        System.out.println("从高德数据接口获取数据成功 准备写入文件");
        ResBean resBean = doSomeThing.readMap2Bean(map, null);
        doSomeThing.writetoFile(output, toJsonString(resBean));
        System.out.println("行政区划已写到 " + output);
    }

    private static void init() {
        if (doSomeThing == null) {
            doSomeThing = invoke();
        }
    }

    private static DoSomeThing invoke() {
        DoSomeThing doSomeThing = null;
        Properties properties = new Properties();
        try {
            properties.load(Start.class.getResourceAsStream("/start.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String doSomeThingClass = properties.getProperty("doSomeThing");
        if (doSomeThingClass != null && !"".equals(doSomeThingClass)) {
            try {
                Class<?> aClass = Class.forName(doSomeThingClass);
                if (aClass != null) {
                    doSomeThing = ((DoSomeThing) aClass.newInstance());
                }
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }
        return doSomeThing;
    }
}
