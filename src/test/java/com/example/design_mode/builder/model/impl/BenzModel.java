package com.example.design_mode.builder.model.impl;

import com.example.design_mode.builder.model.abs.CarModel;

public class BenzModel extends CarModel {

    @Override
    protected void start() {
        System.out.println("奔驰start");
    }

    @Override
    protected void stop() {
        System.out.println("奔驰stop");

    }

    @Override
    protected void alarm() {
        System.out.println("奔驰alarm");
    }
}
