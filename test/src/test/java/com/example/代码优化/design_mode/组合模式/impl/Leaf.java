package com.example.代码优化.design_mode.组合模式.impl;

import com.example.代码优化.design_mode.组合模式.inf.ILeaf;

public class Leaf implements ILeaf {
    // 名称
    private String name = "";
    // 职位
    private String position = "";
    // 薪水
    private int salary = 0;


    public Leaf(String name, String position, int salary) {
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    @Override
    public String getInfo() {
        String info = "";
        info = "名称：" + this.name;
        info = info + "\t职位：" + this.position;
        info = info + "\t薪水：" + this.salary;
        return info;
    }
}
