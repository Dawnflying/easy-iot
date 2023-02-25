package com.jarvis.easy.connect.servers;

import java.util.Map;

/**
 * 服务器接口
 */
public interface ServerInterface {

    /**
     * 获取id
     *
     * @return
     */
    String getId();

    /**
     * 获取名称
     * @return
     */
    String getName();

    /**
     * 获取服务器属性
     *
     * @return
     */
    Map<String, Object> getProperties();
}
