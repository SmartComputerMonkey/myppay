package com.properties;

import java.util.ResourceBundle;

/**
 * 读配置文件
 */
public class ConfigInfo {

    private static ResourceBundle cache = null;
    static{
        try {
            cache = ResourceBundle.getBundle("merchantInfo");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 获取指定key的值
     * @param key
     * @return
     */
    public static String getValue(String key){
        return cache.getString(key);
    }

}
