package com.example.design_mode.状态模式;

public class Context {
    public final static AbsState OPEN = new OpenStatus();
    public final static AbsState CLOSE = new CloseStatus();
    public final static AbsState RUN = new RunStatus();
    public final static AbsState STOP = new StopStatus();

    private AbsState state;

    public Context() {

    }

    public void setState(AbsState state) {
        this.state = state;
        this.state.setContext(this);
    }

    public void open() {
        state.open();
    }

    public void close() {
        state.close();
    }

    public void run() {
        state.run();
    }

    public void stop() {
        state.stop();
    }
}
