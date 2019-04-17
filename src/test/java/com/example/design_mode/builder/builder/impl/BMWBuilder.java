package com.example.design_mode.builder.builder.impl;

import com.example.design_mode.builder.builder.abs.CarBuilder;
import com.example.design_mode.builder.model.abs.CarModel;
import com.example.design_mode.builder.model.impl.BMWModel;

import java.util.ArrayList;

public class BMWBuilder extends CarBuilder {
    private BMWModel bmw = new BMWModel();
    @Override
    public void setSequence(ArrayList<String> sequence) {
        bmw.setSequence(sequence);
    }

    @Override
    public CarModel getCarModel() {
        return bmw;
    }
}
