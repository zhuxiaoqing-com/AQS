package com.example.代码优化.design_mode.状态模式;

public class StopStatus extends AbsState {
    @Override
    public void open() {
        super.getContext().setState(Context.OPEN);
        super.getContext().open();
    }

    // 停止状态关门？电梯的门本来就是关着的
    @Override
    public void close() {

    }

    @Override
    public void run() {
        super.getContext().setState(Context.RUN);
        super.getContext().run();
    }

    @Override
    public void stop() {
        System.out.println("电梯停止了......");
    }
}
