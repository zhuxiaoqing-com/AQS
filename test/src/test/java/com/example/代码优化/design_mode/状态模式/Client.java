package com.example.代码优化.design_mode.状态模式;

public class Client {
    public static void main(String[] args) {
        Context context = new Context();
        context.setState(Context.STOP);
        context.run();
        context.stop();
        context.close();
        context.open();
    }
}
