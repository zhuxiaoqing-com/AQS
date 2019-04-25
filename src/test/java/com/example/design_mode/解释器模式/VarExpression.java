package com.example.design_mode.解释器模式;

import java.util.HashMap;

// 变量解析器
public class VarExpression extends Expression {
    private String key;

    public VarExpression(String key) {
        this.key = key;
    }


    @Override
    public int interpreter(HashMap<String, Integer> var) {
        return var.get(this.key);
    }
}
