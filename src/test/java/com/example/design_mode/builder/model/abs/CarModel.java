package com.example.design_mode.builder.model.abs;

import java.util.ArrayList;

public abstract class CarModel {
    private ArrayList<String> sequence = new ArrayList();

    protected abstract void start();

    protected abstract void stop();

    protected abstract void alarm();

    /**
     * 模板模式的模板方法最好是 final 的
     */
    final public void run() {
        for (int i = 0; i < sequence.size(); i++) {
            String actionName = this.sequence.get(i);
            switch (actionName) {
                case "start":
                    start();
                    break;
                case "stop":
                    stop();
                    break;
                case "alarm":
                    alarm();
                    break;
                default:
            }
        }
    }

    final public void setSequence(ArrayList<String> sequence) {
        this.sequence = sequence;
    }
}
