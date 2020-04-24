package com.example.gameNote.bag;

import com.example.gameNote.bag.entity.Item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/4/24 15:10
 * @Description:
 */
public class BagUtil {
    Map<Long, Item> bag = new HashMap<>();

    public boolean checkIsCanAddItem(List<Item> willAddItem) {

        // 先判断叠加将可叠加的  从 willAddItem.copy() 中删除
        // 然后剩下的就是 需要额外占格子的
        // 然后看还有几个空余格子  空余格子不够就是不能放 够就是可以放
        return true;
    }

    public boolean checkIsCanAddItem(List<Item> willDelItem, List<Item> willAddItem) {
        // 取两个的交集 看结果是要删 还是要添加
        // 要删就走 看道具是否足够逻辑
        // 要添加就走道具是否能够添加逻辑
        // 上面的想法不行 因为可能最后有删又有添加

        // 最简单的就是先删 然后后判断是否能添加; 然后再把删了的再添加回去


        // 或者记录需要占的格子
        // 先两个要删的 要添加的互相抵消
        // 先判断够删嘛 不够删直接false  够删 看空出来几个格子
        // 然后判断添加 添加看要占几个格子;
        // 然后 delNeedCount + addNeedCount <= currentCount 就可以添加
        return true;
    }
}
