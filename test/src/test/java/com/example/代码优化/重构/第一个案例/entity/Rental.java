package com.example.代码优化.重构.第一个案例.entity;

/**
 * 租赁 被顾客租走的影片类
 */
public class Rental {
    private Movie movie;
    private int daysRented; // 租赁时间

    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public int getDaysRented() {
        return daysRented;
    }

    public void setDaysRented(int daysRented) {
        this.daysRented = daysRented;
    }

    public double getCharge() {
        return movie.getCharge(daysRented);
    }

    public int getFrequentRenterPoints() {
        return getMovie().getFrequentRenterPoints(daysRented);
    }
}
