package com.example.demo1.demo;


import java.security.MessageDigest;

/**
 * @author HSimon
 */
public class SignUtil {

    private static final String KEY_FOR_GM = "AS*DF!@#$%^as)_df3f4uiiu*(%&^GM";

    /**
     * 生成GMWeb的MD5码
     *
     * @param params 参数
     * @return MD5码
     */
    public static String getMD5ForGM(String... params) {
        return getMD5Sign(KEY_FOR_GM, params);
    }

    /**
     * 生成MD5码
     *
     * @param key    指定秘钥
     * @param params 参数列表
     * @return MD5码
     */
    private static String getMD5Sign(String key, String... params) {
        StringBuilder in = new StringBuilder();
        for (String param : params) {
            in.append(param);
        }
        int second = TimeUtil.getNow_int();
        //in.append(String.valueOf(second));
        in.append(key);
        System.out.println("second: " + second);
        System.out.println("in: " + in);

        return MD5(in.toString());
    }

    /**
     * 生成MD5码
     *
     * @param key 要加密字符串
     * @return MD5码
     */
    private static String MD5(String key) {
        char hexDigits[] = {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
        };
        try {
            byte[] btInput = key.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (byte byte0 : md) {
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str).toLowerCase();
        } catch (Exception e) {
            return null;
        }
    }
}
