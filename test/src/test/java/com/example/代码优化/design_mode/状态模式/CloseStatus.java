package com.example.代码优化.design_mode.状态模式;

public class CloseStatus extends AbsState {
    @Override
    public void open() {
        super.getContext().setState(Context.OPEN);
        super.getContext().open();
    }

    @Override
    public void close() {
        System.out.println("电梯关闭了......");
    }

    @Override
    public void run() {
        super.getContext().setState(Context.RUN);
        super.getContext().run();
    }

    @Override
    public void stop() {
        super.getContext().setState(Context.STOP);
        super.getContext().stop();
    }
}
