package com.example.demo1.util;


/**
 * 序列化接口
 *
 * @author LinJ
 * @Email linjie1030208@126.com
 * Created by LinJ on @2019/5/20 .
 */
public interface SerializerProvider {

    /**
     * 对象转字节数组
     *
     * @param object
     * @param clazz
     * @param serializerProvider
     * @param <T>
     * @return
     */
    static <T> byte[] encode(T object, Class<T> clazz, SerializerProvider serializerProvider) {
        return serializerProvider.encode(object, clazz);
    }

    /**
     * 字节数组转对象
     *
     * @param bytes
     * @param clazz
     * @param <T>
     * @return
     */
    static <T> T decode(byte[] bytes, Class<T> clazz, SerializerProvider serializerProvider) {
        return serializerProvider.decode(bytes, clazz);
    }


    /**
     * 对象转字节数组
     *
     * @param object
     * @param clazz
     * @param <T>
     * @return
     */
    <T> byte[] encode(T object, Class<T> clazz);


    /**
     * 字节数组转对象
     *
     * @param bytes
     * @param clazz
     * @param <T>
     * @return
     */
    <T> T decode(byte[] bytes, Class<T> clazz);
}
