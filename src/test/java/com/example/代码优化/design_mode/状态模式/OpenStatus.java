package com.example.代码优化.design_mode.状态模式;

public class OpenStatus extends AbsState {

    @Override
    public void open() {
        System.out.println("电梯门开了......");
    }

    @Override
    public void close() {
        getContext().setState(Context.CLOSE);
        getContext().close();
    }

    // 门开着时电梯就运行跑，这电梯，吓死你！
    @Override
    public void run() {
    }

    // 开门了还不停止？
    @Override
    public void stop() {
    }
}
