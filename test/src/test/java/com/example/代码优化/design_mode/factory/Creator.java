package com.example.代码优化.design_mode.factory;

public abstract class Creator {
    public abstract <T extends Product> T createProduct(Class<T> c);
}
