package com.example.design_mode.strategy;

public enum EnumStrategy {
    ADD("+") {
        @Override
        public int exec(int a, int b) {
            return a + b;
        }
    },

    SUB("-") {
        @Override
        public int exec(int a, int b) {
            return a - b;
        }
    },;
    String value = "";

    // 定义成员值类型
    EnumStrategy(String _value) {
        this.value = _value;
    }

    // 获得枚举成员的值
    public String getValue() {
        return this.value;
    }

    public abstract int exec(int a, int b);
}
