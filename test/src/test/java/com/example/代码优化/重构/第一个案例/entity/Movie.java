package com.example.代码优化.重构.第一个案例.entity;

import com.example.代码优化.重构.第一个案例.entity.price.ChildrensPrice;
import com.example.代码优化.重构.第一个案例.entity.price.NewReleasePrice;
import com.example.代码优化.重构.第一个案例.entity.price.Price;
import com.example.代码优化.重构.第一个案例.entity.price.RegularPrice;

/**
 * 影片
 */
public class Movie {
    public static final int CHILDRENS = 2;  // 儿童片
    public static final int REGULAR = 0;    // 普通片
    public static final int NEW_RELEASE = 1; // 新发布的片

    private Price price;

    private String title; // 标题

    public Movie(String title, int priceCode) {
        this.title = title;
        setPrice(priceCode);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(int priceCode) {
        switch (priceCode) {
            case Movie.REGULAR:
                price = new RegularPrice();
                break;
            case Movie.NEW_RELEASE:
                price = new NewReleasePrice();
                break;
            case Movie.CHILDRENS:
                price = new ChildrensPrice();
                break;
            default:
                throw new IllegalArgumentException("Incorrect Price Code");
        }
    }

    /**
     * @param daysRented
     * @return
     */
    public double getCharge(int daysRented) {
        return price.getCharge(daysRented);
    }


    public int getFrequentRenterPoints(int daysRented) {
        return price.getFrequentRenterPoints(daysRented);
    }
}
