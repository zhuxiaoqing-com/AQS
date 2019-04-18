package com.example.design_mode.builder;

import com.example.design_mode.builder.builder.impl.BMWBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * 导演类
 */
public class Director {
    private List<String> sequence = new ArrayList<>();
    private BMWBuilder bmwBuilder = new BMWBuilder();

    public BMWBuilder getABmwBuilder() {
        // A 型号车
        return new BMWBuilder();
    }
    public BMWBuilder getBBmwBuilder() {
        // B 型号车
        return new BMWBuilder();
    }
    public BMWBuilder getCBmwBuilder() {
        // C 型号车
        return new BMWBuilder();
    }
    public BMWBuilder getDBmwBuilder() {
        // D 型号车
        return new BMWBuilder();
    }

}
