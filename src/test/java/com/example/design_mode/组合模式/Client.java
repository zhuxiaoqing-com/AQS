package com.example.design_mode.组合模式;

import com.example.design_mode.组合模式.impl.Branch;
import com.example.design_mode.组合模式.impl.Root;

public class Client {
    public static void main(String[] args) {
        // 首先产生了一个根节点
        Root ceo = new Root("王大麻子", "总经理", 100_000);
        // 产生三个部门经理，也就是树枝节点
        Branch developDep = new Branch("刘大瘸子", "研发部门经理", 10_000);
        Branch salesDep = new Branch("马二拐子", "销售部门经理", 20_000);
        Branch financeDep = new Branch("赵三驼子", "财务部门经理", 30_000);
    }
}
