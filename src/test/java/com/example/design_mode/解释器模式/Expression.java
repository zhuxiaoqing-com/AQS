package com.example.design_mode.解释器模式;

import java.util.HashMap;

public abstract class Expression {
    public abstract int interpreter(HashMap<String, Integer> var);
}
