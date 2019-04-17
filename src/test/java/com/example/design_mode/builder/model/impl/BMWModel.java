package com.example.design_mode.builder.model.impl;

import com.example.design_mode.builder.model.abs.CarModel;

public class BMWModel extends CarModel {

    @Override
    protected void start() {
        System.out.println("BMWstart");
    }

    @Override
    protected void stop() {
        System.out.println("BMWstop");

    }

    @Override
    protected void alarm() {
        System.out.println("BMWalarm");
    }
}
