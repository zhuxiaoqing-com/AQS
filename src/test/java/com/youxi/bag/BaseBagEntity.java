package com.youxi.bag;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/4/7 15:52
 * @Description:
 */
public class BaseBagEntity<T extends Item> {
    /**
     * 角色id
     */
    protected long id;

    /**
     * key: 格子
     * value：道具
     */
    Map<Integer, T> itemMap = new HashMap<>();
}
