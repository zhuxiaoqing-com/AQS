package com.youxi.AI.fsm;

import com.sh.game.system.map.scene.GameMap;
import com.sh.game.util.TimeUtil;
import com.youxi.AI.entity.GameMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 状态机
 * <p>
 * S 代表枚举状态
 * T 代表要传入的类型
 *
 * @author SilenceSu
 * @Email Silence.Sx@Gmail.com
 * Created by Silence on 2018/7/19.
 */
public class FSMMachine<T extends AbstractFSMState> {

    private static final Logger LOGGER = LoggerFactory.getLogger(FSMMachine.class);

    /**
     * 可用状态
     */
    private List<T> states = new ArrayList<>();

    /**
     * 当前状态
     */
    private T state;

    /**
     * 是否正在运行（这里只至少update一次以后才算运行）
     */
    private boolean running = false;

    private AIData aiData = new AIData();

    public FSMMachine(List<T> states, T init) {
        this.states.addAll(states);
        this.state = init;
        aiData.setBornTime(System.currentTimeMillis());

    }

    /**
     * 更新状态机
     *
     * @param map
     * @param delta
     */
    public void updateMachine(GameMap map, int delta) {

        if (states.isEmpty()) {
            return;
        }

        if (this.state == null) {
            return;
        }
        Enum curType = this.state.type;
        Enum nextType = this.state.checkTransition(map);
        if (nextType != curType) {
            T nextState = getStateByType(nextType);
            if (nextState != null) {
                // 复制时间
                int sleepTime = this.state.getTime();
                this.state.exit(map);
                this.state = nextState;
                this.state.enter(map);
                this.state.copyTime(sleepTime);
                return;
            }
        }

        long time = System.currentTimeMillis();

        this.state.update(map, delta);
        long end = System.currentTimeMillis();
        if (end - time > 10) {
            LOGGER.warn("超时：{}:{}:{}", (end - time), this.state.type.name(), this.state.performer.getClass().getSimpleName());
        }
        this.running = true;
    }

    public T getStateByType(Enum goal) {
        for (T state : states) {
            if (state.type == goal) {
                return state;
            }
        }
        return null;
    }

    public Enum getCurrentState() {
        return state.type;
    }

    public List<T> getStates() {
        return states;
    }

    public void setStates(List<T> states) {
        this.states = states;
    }

    public T getState() {
        return state;
    }

    public void setState(T state) {
        this.state = state;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public AIData getAiData() {
        return aiData;
    }

    public void setAiData(AIData aiData) {
        this.aiData = aiData;
    }
}
