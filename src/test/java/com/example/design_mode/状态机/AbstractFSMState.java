package com.example.design_mode.状态机;

public abstract class AbstractFSMState <S extends Enum, T extends Object>{
    /**
     * 当前状态机类型
     */
    protected S type;

    /**
     * 执行者
     */
    protected T performer;

    public AbstractFSMState(S type, T performer) {
        this.type = type;
        this.performer = performer;
    }

    /**
     * 进入状态机
     */
    public abstract void enter();

    /**
     * 退出状态机
     */
    public abstract void exit();
    /**
     * 更新状态机 状态机心跳
     */
    public abstract void update();

    /**
     * 检查状态转换
     */
    public abstract S checkTransition();


    public S getType() {
        return type;
    }

    public void setType(S type) {
        this.type = type;
    }

    public T getPerformer() {
        return performer;
    }

    public void setPerformer(T performer) {
        this.performer = performer;
    }
}










