package com.example.design_mode.create_status_mode_PK.builder_and_factory_pk.abstract_builder;

public abstract class CarBuilder {
    // 待建造的汽车
    private ICar car;
    // 设计蓝图
    private Blueprint blueprint;

    public Car builderCar() {
        //
        return null;
    }

    public void receiveBlueprint(Blueprint _bp) {
        this.blueprint = _bp;
    }

    // 查看蓝图，只有真正的建造者才可以查看蓝图
    protected Blueprint getBlueprint(){
        return blueprint;
    }

    protected abstract String buildWheel();

    protected abstract String buildEngine();

}
