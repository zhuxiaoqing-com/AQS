package com.example.demo4;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * JsonUtils工具
 *
 * @author zhouzizhen 2019/3/26
 */
public class JsonUtils {

    /**
     * 对象转String
     */
    public static String ObjectToJson(Object o) {
        return JSON.toJSONString(o);
    }

    /**
     * String转对象
     */
    public static <T> T strToObject(String jsonData, Class<T> clazz) {
        return JSON.parseObject(jsonData, clazz);
    }

    /**
     * List 转String
     */
    public static <T> String listToString(List<T> objList) {
        return JSON.toJSONString(objList);
    }

    /**
     * String转List
     */
    public static <T> List<T> strToList(String jsonData, Class<T> clazz) {
        return JSON.parseArray(jsonData, clazz);
    }
}

