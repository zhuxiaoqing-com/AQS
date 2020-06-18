package com.example.redis.zset;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/18 17:03
 * @Description:
 */
public class ZSet {
	private static final int ZSKIPLIST_MAXLEVEL = 32; /* Should be enough for 2^32 elements */
	private static final double ZSKIPLIST_P = 0.25;      /* Skiplist P = 1/4 */ // 应该是有 0.25的几率建立下一层

	/**
	 * zset 同时使用两种数据结构来持有同一个元素
	 * 从而提供 O(log(N)) 复杂度的有序数据结构的插入和移除操作
	 * <p>
	 * <p>
	 * <p>
	 * 哈希表将 redis 对象映射到分值上
	 * 而跳跃表则将 分值映射到 Redis 对象上
	 * 以跳跃表的视角来看，可以说 redis 对象是根据分值来排序的。
	 * <p>
	 * <p>
	 * redis的跳跃表实现和 William Pugh
	 * 在《Skip Lists: A Probabilistic Alternative to Balanced Trees》论文中
	 * 描述的跳跃表基本相同，不过在以下三个地方做了修改：
	 * 1、这个实现允许重复的分值
	 * 2、对元素的比对不仅要比对他们的分值，还要比对他们的对象
	 * 3、每个跳跃表节点都带有一个后退指针，它允许程序在执行像 ZREVRANGE 这样的命令时，从表尾像表头遍历跳跃表
	 */

	/*
	 * 创建一个层数为 level 的跳跃表节点，
	 * 并将节点的成员对象设置为 obj ，分值设置为 score 。
	 *
	 * 返回值为新创建的跳跃表节点
	 *
	 * T = O(1)
	 */
	private ZSkipListNode zslCreateNode(int level, double score, Object obj) {
		ZSkipListNode node = new ZSkipListNode();
		node.setObj(obj);
		node.setScore(score);
		return node;
	}

	/*
	 * 创建并返回一个新的跳跃表
	 *
	 * T = O(1)
	 */
	ZSkipList zslCreate() {
		ZSkipList skipList = new ZSkipList();
		skipList.setLevel(1);
		skipList.setLength(0);

		// 初始化表头
		// T = O(1)
		skipList.setHeader(zslCreateNode());
	}


}
