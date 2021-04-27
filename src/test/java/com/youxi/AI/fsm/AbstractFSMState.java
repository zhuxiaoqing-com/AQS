package com.youxi.AI.fsm;

import com.youxi.AI.entity.GameMap;
import com.youxi.AI.entity.Performer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 状态机状态
 *
 * @author SilenceSu
 * @Email Silence.Sx@Gmail.com
 * Created by Silence on 2018/7/19.
 */
public abstract class AbstractFSMState<S extends Enum, T extends Performer> {

    protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractFSMState.class);


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
     * 进入状态
     *
     * @param map
     */
    public abstract void enter(GameMap map);

    /**
     * 退出状态
     *
     * @param map
     */
    public abstract void exit(GameMap map);

    /**
     * 更新状态
     *
     * @param map
     * @param delta
     */
    public abstract void update(GameMap map, int delta);

    /**
     * 检查状态转换
     *
     * @param map
     * @return
     */
    public abstract S checkTransition(GameMap map);


    /**
     * 拷贝原状态机休眠时间到当前状态机
     *
     * @param sleepTime
     */
    public void copyTime(int sleepTime) {

    }

    /**
     * 获取当前状态机休眠时间
     *
     * @return
     */
    public int getTime() {
        return 0;
    }

}
