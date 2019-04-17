package com.example.design_mode.builder.builder.abs;

import com.example.design_mode.builder.model.abs.CarModel;

import java.util.ArrayList;

public abstract class CarBuilder {
    // 建造一个模型，你要给我一个顺序，就是组装顺序
    public abstract void setSequence(ArrayList<String> sequence);
    // 设置完毕顺序后，就可以直接拿到这个车辆模型
    public abstract CarModel getCarModel();
}
