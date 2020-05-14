package com.example.代码优化.design_mode.状态机;

import java.util.HashMap;
import java.util.Map;

public class FSMMachine<S extends Enum, T extends AbstractFSMState> {

    /**
     * 当前状态机集合
     */
    protected Map<S, T> states = new HashMap<>();

    /**
     * 当前状态机
     */
    protected T state;

    /**
     * 是否正在运行（这里只至少update一次以后才算运行）
     */
    private boolean running = false;

    public FSMMachine(Map<S, T> states, T init) {
        this.states.putAll(states);
        this.state = init;
    }

    /**
     * @param delta 距离上一次更新的时间间隔
     */
    public void updateMachine(int delta) {

        if (states.isEmpty()) {
            return;
        }

        if (this.state == null) {
            return;
        }
        Enum type = state.getType();
        Enum nextType = state.checkTransition();

        if (nextType != type) {
            T nextState = getStateByType(nextType);
            if (nextState != null) {
                state.exit();
                this.state = nextState;
                nextState.enter();
                return;
            }
        }

        long start = System.currentTimeMillis();
        state.update();

        long end = System.currentTimeMillis();
        if (end - start > 10) {
            System.out.println("超时");
        }
        this.running = true;
    }


    public T getStateByType(Enum goal) {
        return states.get(goal);
    }

}



























