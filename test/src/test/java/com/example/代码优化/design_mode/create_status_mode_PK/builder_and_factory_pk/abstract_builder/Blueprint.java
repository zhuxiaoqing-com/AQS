package com.example.代码优化.design_mode.create_status_mode_PK.builder_and_factory_pk.abstract_builder;

/**
 * 生产蓝图
 */
public class Blueprint {
    // 车轮的要求
    private String wheel;
    // 引擎的要求
    private String engine;

    public String getWheel() {
        return wheel;
    }

    public void setWheel(String wheel) {
        this.wheel = wheel;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }
}
