package com.example.datastruture.a_43_topological_sorting;

import java.util.HashSet;

/**
 * 图的环的检测
 */
public class LoopInspect {

    /**
     * 拓扑排序Kahn 还能检测图中环的存在。对于 Kahn 算法来说，
     * 如果最后输出出来的顶点个数，少于图中顶点个数,图中还有入度不是
     * 0 的顶点，说明，图中存在环。因为如果不存在环的话，会全部输出。
     * 存在环说明入度互相引用了。无法输出。
     *
     */

    /**
     * 实际上，因为我们每次都只是查找一个用户的最终推荐人，所以，我们并不需要动用
     * 复杂的拓扑排序算法，而只需要记录已经访问过的用户的 id, 当用户 ID 第二次被
     * 访问的时候，就说明存在环，也就说明存在脏数据。
     */

    HashSet<Object> hashTable = new HashSet<>();// 保存已经访问过的 actorId
    public long findRootReferrerId(long actorId) {
        if(hashTable.contains(actorId)) {
            //  存在环
            return -1;
        }
        hashTable.add(actorId);
        // 从某个地方查询推荐人
        Long referrerId = (Long)(Object)"select referrer_id from [table] where actor_id = actorId";
        if(referrerId == null) return actorId;
        return findRootReferrerId(referrerId);

}
    /**
     * 如果把这个问题改一下，我们想要知道，数据库中的所有用户之间的推荐关系了，
     * 有没有存在环的情况。这个问题，就需要用到拓扑排序算法了。
     * 我们把用户之间的推荐关系，从数据库中加载到内存中，
     * 然后构建成今天讲的这种有向图数据结构，再利用拓扑排序，就可以快速检测出是否存在环了。
     */

}
