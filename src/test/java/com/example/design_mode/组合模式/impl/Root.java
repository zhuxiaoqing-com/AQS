package com.example.design_mode.组合模式.impl;

import com.example.design_mode.组合模式.inf.IBranch;
import com.example.design_mode.组合模式.inf.ILeaf;
import com.example.design_mode.组合模式.inf.IRoot;

import java.util.ArrayList;

public class Root implements IRoot {
    private ArrayList subordinateList = new ArrayList();

    // 名称
    private String name = "";
    // 职位
    private String position = "";
    // 薪水
    private int salary = 0;


    public Root(String name, String position, int salary) {
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

    @Override
    public void add(IBranch branch) {
        this.subordinateList.add(branch);
    }

    @Override
    public void add(ILeaf leaf) {
        this.subordinateList.add(leaf);
    }

    @Override
    public ArrayList getSubordinateInfo() {
        return this.subordinateList;
    }
}
