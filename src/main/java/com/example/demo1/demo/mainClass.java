package com.example.demo1.demo;

import java.util.HashMap;
import java.util.Map;

/**
 * @author HSimon
 */
public class mainClass {
    public static void main(String[] args) throws Exception {

        String url = "http://10.42.0.35:11000/message/GMEmail";

        Map<String, String> params = new HashMap<>();
        params.put("type", "1");
        params.put("title", "标题");
        params.put("subject", "主题");
        params.put("content", "邮件内容");
        params.put("items", "42001|1");
        params.put("key", "382097308461262");
        params.put("closeDay", "3");
        String sign = SignUtil.getMD5ForGM("1");
        params.put("sign", sign);
        HttpUtil.doPost(url, params);
    }

    public void fun1() {
            String url = "http://10.42.0.35:11000/message/GMEmail";

            Map<String, String> params = new HashMap<>();
            params.put("type", "1");
            params.put("title", "标题");
            params.put("subject", "主题");
            params.put("content", "邮件内容");
            params.put("items", "42001|1");
            params.put("key", "382097308461262");
            params.put("closeDay", "3");
            String sign = SignUtil.getMD5ForGM("1");
            params.put("sign", sign);
            HttpUtil.doPost(url, params);
    }

}
