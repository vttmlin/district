package com.tmdaq.district.abs;

import com.tmdaq.district.bean.ResBean;

import java.util.Map;

/**
 * 具体的操作 怎么把字符串写入到json中 怎么转换到bean 完全是这个接口控制的
 * 只需要修改 start.properties 就行了 并实现这个类就行了
 */
public interface DoSomeThing {
    /**
     * 通过转换成bean 然后进行下一步的拼装操作 把树形数据映射成一个bean
     */
    ResBean readMap2Bean(Map<String, Object> map, ResBean resBean);

    /**
     * 调用的 common-io 写入文件 懒得自己去写
     */
    boolean writetoFile(String file, String content);
}
