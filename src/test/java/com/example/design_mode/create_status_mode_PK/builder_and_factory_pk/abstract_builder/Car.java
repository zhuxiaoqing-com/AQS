package com.example.design_mode.create_status_mode_PK.builder_and_factory_pk.abstract_builder;

public class Car implements ICar {
    // 汽车引擎
    private String engine;
    // 汽车车轮
    private String wheel;

    public Car(String engine, String wheel) {
        this.engine = engine;
        this.wheel = wheel;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public void setWheel(String wheel) {
        this.wheel = wheel;
    }

    @Override
    public String getWheel() {
        return null;
    }

    @Override
    public String getEngine() {
        return null;
    }

    @Override
    public String
    toString() {
        return "Car{" +
                "engine='" + engine + '\'' +
                ", wheel='" + wheel + '\'' +
                '}';
    }
}
