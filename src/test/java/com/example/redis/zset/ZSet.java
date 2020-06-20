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

		for (int j = 0; j < ZSKIPLIST_MAXLEVEL; j++) {
			ZSkipListLevel zSkipListLevel = skipList.getHeader().getLevels()[j];
			zSkipListLevel.setForward(null);
			zSkipListLevel.setSpan(0);
		}
		skipList.getHeader().setBackward(null);
		// 设置表尾
		skipList.setTail(null);
		return skipList;
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
		// 在各个层查找节点的插入位置
		// T_wrost = O(N^2), T_avg = O(N log N)

		// 当前插入的节点的 某一层的上一个节点
		ZSkipListNode[] update = new ZSkipListNode[ZSKIPLIST_MAXLEVEL];
		// 当前要插入的节点在该层的  上一个节点的 rank;
		int[] rank = new int[ZSKIPLIST_MAXLEVEL];

		ZSkipListNode x = list.getHeader();

		for (int i = list.getLevel() - 1; i >= 0; i--) {
			/*
			交叉到插入位置的存储顺序
			如果 i 不是 ZSkipList->level-1 层
			那么 i 层的起始 rank 值为 i+1 层的 rank 值
			各个层的 rank 值一层层累积
			最终 rank[0] 的值加一就是新节点的前置节点的rank
			rank[0]会在后面成为计算 span 值和 rank 值的基础
			 */
			rank[i] = i == list.getLevel() - 1 ? 0 : rank[i + 1];

			// 沿着前进指针遍历跳跃表
			// T_wrost = O(N^2), T_avg = O(N log N)
			ZSkipListNode forward = x.getLevels()[i].getForward();
			while (forward != null &&
					(forward.getScore() < score ||
							(forward.getScore() == score &&
									compareStringObjects(forward.getObj(), obj) < 0))
					) {

				// 记录沿途跨越了多少个节点
				rank[i] += x.getLevels()[i].getSpan();
				// 移动到下一个指针
				x = forward;
			}
			// 记录将要和新节点相连接的节点
			update[i] = x;
		}

		/*
		 * zslInsert() 的调用者会确保同分值且同成员的元素不会出现，
		 * 所以这里不需要进一步进行检查，可以直接创建新元素。
		 */

		// 获取一个随机值作为新节点的层数
		// T = O(N)
		int level = zslRandomLevel();
		/*
		如果新节点的层数比表中其他节点的层数都要大
		那么初始化表头节点中未使用的层，并将它们记录到 update 数组中
		将来也指向新节点
		 */
		if (level > list.getLevel()) {
			// 初始化未使用的层
			// T = O(1)
			for (int i = list.getLevel(); i < level; i++) {
				rank[i] = 0;
				update[i] = list.getHeader();
				update[i].getLevels()[i].setSpan(list.getLength());
			}
			// 更新表中节点最大层数
			list.setLevel(level);
		}

		// 创建新节点
		ZSkipListNode newNode = zslCreateNode(level, score, obj);

		// 将前面记录的指针指向新节点，并做相应的设置
		// T = O(1)
		for (int i = 0; i < level; i++) {
			// 设置新节点的 forward 指针
			ZSkipListLevel updateLevel = update[i].getLevels()[i];
			newNode.getLevels()[i].setForward(updateLevel.getForward());
			// 将沿途记录的各个节点的 forward 指针指向新节点
			updateLevel.setForward(newNode);
			// 计算新节点跨越的节点数量
			// 该层上一个节点和该层新节点之间的span = 该更新节点的0层rank - 该层的上一个节点的rank
			// 该节点和下一个节点之间 有几个基础节点 =  该层新节点的上一个节点的span - 该层上一个节点和该层新节点之间的span
			newNode.getLevels()[i].setSpan(updateLevel.getSpan() - (rank[0] - rank[i]));

			// 更新新节点插入之后，沿途节点的 span 值
			// 其中的 +1 计算的是新节点
			updateLevel.setSpan(rank[0] - rank[i] + 1);
		}

		return null;
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
	 * 根据字典排序 位置小的排在前面
	 */
	private int compareStringObjects(Object obj1, Object obj2) {
		return 1;
	}

}
