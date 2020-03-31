package com.example.重构.第一个案例.entity.price;

/**
 * 状态模式 而不用多态 因为可能影片会变分类，如果使用了多态 类无法改变类型的
 */
public abstract class Price {

    public abstract int getPriceCode();

    public abstract double getCharge(int daysRented);

    public int getFrequentRenterPoints(int daysRented) {
        return 1;
    }
}
