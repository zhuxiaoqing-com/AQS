package com.example.代码优化.重构.第一个案例.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 顾客类
 */
public class Customer {

    private String name;

    private List<Rental> rentals = new ArrayList();

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public void addRentals(Rental rental) {
        rentals.add(rental);
    }

    public String statement() {
        String result = "Rental Record for " + getName() + "\\n";

        for (Rental rental : rentals) {
            // show figures for this rental
            result += "\\t" + rental.getMovie().getTitle() + "\\t" +
                    String.valueOf(amountFor(rental)) + "\\n";
        }

        //add footer lines
        result += "Amount owed is " + String.valueOf(getTotalCharge()) + "\\n";
        result += "You earned " + String.valueOf(getFrequentRenterPoints()) +
                "frequent renter points";
        return result;
    }

    private double amountFor(Rental rental) {
        return rental.getCharge();
    }

    private double getTotalCharge() {
        double result = 0;
        for (Rental rental : rentals) {
            result += amountFor(rental);
        }
        return result;
    }

    private double getFrequentRenterPoints() {
        double result = 0;
        for (Rental rental : rentals) {
            result += rental.getFrequentRenterPoints();
        }
        return result;
    }

}















