package com.example.代码优化.design_mode.组合模式.inf;

import java.util.ArrayList;

public interface IBranch {
    //得到基本的信息
    String getInfo();
    // 添加分支
    void add(IBranch branch);
    // 添加树叶节点
    void add(ILeaf leaf);
    // 既然能增加，那还要能够遍历，不可能总经理不知道他手下有哪些人
    ArrayList getSubordinateInfo();
}
