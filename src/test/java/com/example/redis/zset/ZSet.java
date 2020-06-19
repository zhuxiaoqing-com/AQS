package com.example.redis.zset;

import java.util.concurrent.ThreadLocalRandom;

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
		node.setLevels(new ZSkipListLevel[level]);
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
		skipList.setHeader(zslCreateNode(ZSKIPLIST_MAXLEVEL, 0, null));
		int j = 0;
		for (j = 0; j < ZSKIPLIST_MAXLEVEL; j++) {
			ZSkipListLevel zSkipListLevel = skipList.getHeader().getLevels()[j];
			zSkipListLevel.setForward(null);
			zSkipListLevel.setSpan(0);
		}
		skipList.getHeader().setBackward(null);
		// 设置表尾
		skipList.setTail(null);
		return skipList;
	}

	/*
	 * 释放给定的跳跃表节点
	 *
	 * T = O(1)
	 */
	void zslFreeNode(ZSkipListNode node) {
		// 减少 Redis 对象引用。特别的，引用为零的时候会销毁对象 这里是不是跳表代码
		//decrRefCount(node->obj);
		// 释放内存
		//zfree(node);
	}

	/*
	 * 释放给定跳跃表，以及表中的所有节点
	 *
	 * T = O(N)
	 */

	private void zfree(ZSkipListNode node) {

	}

	/*
	 * 释放给定跳跃表，以及表中的所有节点
	 *
	 * T = O(N)
	 */
	private void zSkipListFree(ZSkipList list) {
		/*zskiplistNode *node = zsl->header->level[0].forward, *next;

		// 释放表头
		zfree(zsl->header);

		// 释放表中所有节点
		// T = O(N)
		while(node) {

			next = node->level[0].forward;

			zslFreeNode(node);

			node = next;
		}

		// 释放跳跃表结构
		zfree(zsl);*/
	}

	/**
	 * 返回一个随机值，用作跳跃表节点的层数
	 * 返回值介于 1 和  ZSKIPLIST_MAXLEVEL 之间（包含 ZSKIPLIST_MAXLEVEL），
	 * 根据随机算法所使用的幂次定律，越大的值生成的几率越小。
	 * <p>
	 * T = O(N)
	 */
	private int zslRandomLevel() {
		/*
		 C 语言的  random()&0xFFFF) < (ZSKIPLIST_P * 0xFFFF
		 其实就是指 random()&0xFFFF 取低16位的值; ZSKIPLIST_P * 0xFFFF  低16位的百分比;(现在是0.25 就是 65535*0.25)
		  */
	/*	int level = 1;

		while ((random()&0xFFFF) < (ZSKIPLIST_P * 0xFFFF))
			level += 1;

		return (level<ZSKIPLIST_MAXLEVEL) ? level : ZSKIPLIST_MAXLEVEL;*/
		int level = 1;

		while (ThreadLocalRandom.current().nextInt(100) < ZSKIPLIST_P * 100) {
			level += 1;
		}
		return level;
	}

	/*
	 * 创建一个成员为 obj ，分值为 score 的新节点，
	 * 并将这个新节点插入到跳跃表 zsl 中。
	 *
	 * 函数的返回值为新节点。
	 *
	 * T_wrost = O(N^2), T_avg = O(N log N)
	 */
	ZSkipListNode zslInsert(ZSkipList list, double score, Object obj) {
		return null;
	}
}
