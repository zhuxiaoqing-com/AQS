package com.example.代码优化.design_mode.状态模式;

public class RunStatus extends AbsState {
    // 运行着还想开启？
    @Override
    public void open() {

    }

    // 运行着本来就是关闭的
    @Override
    public void close() {

    }

    @Override
    public void run() {
        System.out.println("电梯开始运行......");
    }

    @Override
    public void stop() {
        super.getContext().setState(Context.STOP);
        super.getContext().stop();
    }
}
