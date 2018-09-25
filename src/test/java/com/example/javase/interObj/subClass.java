package com.example.javase.interObj;

interface SuperClass<T> {
    T method(T param);
}
public class subClass implements SuperClass<String> {
    @Override
    public String method(String param) {
        return null;
    }
}
