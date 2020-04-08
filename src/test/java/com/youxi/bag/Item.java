package com.youxi.bag;

import lombok.Getter;
import lombok.Setter;

/**
 * @Auther: Administrator
 * @Date: 2020/4/7 15:53
 * @Description:
 */
@Setter
@Getter
public class Item {
    /**
     * 道具全局id
     */
    private long itemGlobalId;

    /**
     * 道具配置id
     */
    private int itemCfgId;

    /**
     * 道具数量
     */
    private int itemCount;
}
