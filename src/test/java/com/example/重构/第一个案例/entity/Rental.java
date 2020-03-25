package com.example.重构.第一个案例.entity;

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
        double result = 0;
        // determine amounts for each line
        switch (getMovie().getPriceCode()) {
            case Movie.REGULAR:
                result += 2;
                if (getDaysRented() > 2) {
                    result += (getDaysRented() - 2) * 1.5;
                }
                break;

            case Movie.NEW_RELEASE:
                result += getDaysRented() * 3;
                break;

            case Movie.CHILDRENS:
                result += 1.5;
                if (getDaysRented() > 3) {
                    result += (getDaysRented() - 3) * 1.5;
                }
                break;
        }
        return result;
    }

    public int getFrequentRenterPoints() {
        int frequentRenterPoints = 1;
        if (getMovie().getPriceCode() == Movie.NEW_RELEASE && getDaysRented() > 1) {
            frequentRenterPoints++;
        }
        return frequentRenterPoints;
    }
}
