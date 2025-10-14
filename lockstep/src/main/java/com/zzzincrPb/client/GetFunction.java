package com.zzzincrPb.client;

/**
 * @author zhuxiaoqing
 * @Description: GetFunction
 * @Date 2025/10/11 17:22
 **/
// ---------------- 泛型获取对象方法 ----------------
    @FunctionalInterface
public interface GetFunction<T> {
    T get();
}
