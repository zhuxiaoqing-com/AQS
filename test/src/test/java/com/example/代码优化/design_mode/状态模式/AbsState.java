package com.example.代码优化.design_mode.状态模式;

public abstract class AbsState {
    private Context context;


    public void setContext(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    // 开门
    public abstract void open();

    // 关门
    public abstract void close();

    // 上下移动 运行
    public abstract void run();

    // 停止
    public abstract void stop();
}
