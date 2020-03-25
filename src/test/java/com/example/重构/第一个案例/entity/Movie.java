package com.example.重构.第一个案例.entity;

/**
 * 影片
 */
public class Movie {
    public static final int CHILDRENS = 2;  // 儿童片
    public static final int REGULAR = 0;    // 普通片
    public static final int NEW_RELEASE = 1; // 新发布的片

    private String title; // 标题
    private int priceCode;// 影片类型

    public Movie(String title, int priceCode) {
        this.title = title;
        this.priceCode = priceCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPriceCode() {
        return priceCode;
    }

    public void setPriceCode(int priceCode) {
        this.priceCode = priceCode;
    }
}
