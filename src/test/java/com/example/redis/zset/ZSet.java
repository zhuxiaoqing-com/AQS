package com.example.redis.zset;

import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/18 17:03
 * @Description: 111
 * https://github.com/huangz1990/redis-3.0-annotated/blob/unstable/src/t_zset.c
 * http://zhangtielei.com/posts/blog-redis-skiplist.html
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
	private ZSkipListNode zslInsert(ZSkipList list, double score, Object obj) {
		// 在各个层查找节点的插入位置
		// T_wrost = O(N^2), T_avg = O(N log N)

		// 当前插入的节点的 某一层的上一个节点
		ZSkipListNode[] update = new ZSkipListNode[ZSKIPLIST_MAXLEVEL];
		// 当前要插入的节点在该层的  上一个节点的 rank;
		int[] rank = new int[ZSKIPLIST_MAXLEVEL];

		ZSkipListNode newAddNode = list.getHeader();

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
			while (newAddNode.getLevels()[i].getForward() != null &&
					(newAddNode.getLevels()[i].getForward().getScore() < score ||
							(newAddNode.getLevels()[i].getForward().getScore() == score &&
									compareStringObjects(newAddNode.getLevels()[i].getForward().getObj(), obj) < 0))
					) {

				// 记录沿途跨越了多少个节点
				rank[i] += newAddNode.getLevels()[i].getSpan();
				// 移动到下一个指针
				newAddNode = newAddNode.getLevels()[i].getForward();
			}
			// 记录将要和新节点相连接的节点
			update[i] = newAddNode;
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

			// 设该新节点为 newAddNode(层数); 例 newAddNode(curLevel),newAddNode(0)
			// newAddNode(curLevel)上一个节点 和x(0)上一个节点之间的rank = newAddNode(0)上一个节点的rank - newAddNode(curLevel)上一个节点的rank
			// newAddNode(curLevel)span =  newAddNode(curLevel)上一个节点span - newAddNode(curLevel)上一个节点和x(0)上一个节点之间的rank
			// 因为该节点是新插入的节点，所以计算该节点的 span, 其实就是计算上一个节点的span

			// 计算新节点跨越的节点数量
			// (该层上一个节点)和(零层新节点的上一个节点)之间的rank = 该更新节点的上一个节点的0层rank - 该层的上一个节点的rank
			// 该节点零层的上一个节点和下一个节点之间 有几个基础节点 =  该层新节点的上一个节点的span - (该层上一个节点)和(零层新节点的上一个节点)之间的rank
			// 因为该节点是新插入的节点，所以计算该节点的 span, 其实就是计算上一个节点的span
			newNode.getLevels()[i].setSpan(updateLevel.getSpan() - (rank[0] - rank[i]));

			// 更新新节点插入之后，沿途节点的 span 值
			// 其中的 +1 计算的是新节点
			updateLevel.setSpan(rank[0] - rank[i] + 1);
		}

		/*
		 * 如果当前随机出来的 level 没有旧的总level高;
		 * 那就需要把 level....list.getLevel 之间的上一个节点的 span 加一
		 */
		// 未接触的节点的 span 值也需要增一，这些节点直接从表头指向新节点
		// T = O(1)
		for (int i = level; i < list.getLevel(); i++) {
			ZSkipListLevel tempLevelNode = update[i].getLevels()[i];
			tempLevelNode.setSpan(tempLevelNode.getSpan() + 1);
		}

		// 设置新节点的后退节点
		ZSkipListNode backward = update[0] == list.getHeader() ? null : update[0];
		newAddNode.setBackward(backward);

		if (newAddNode.getLevels()[0].getForward() != null) {
			// 设置新节点下一个节点的后退指针为该指针
			newAddNode.getLevels()[0].getForward().setBackward(newAddNode);
		} else {
			// 设置尾节点
			list.setTail(newAddNode);
		}
		list.setLength(list.getLevel() + 1);
		return newAddNode;
	}


	/**
	 * Internal function used by zslDelete, zslDeleteByScore and zslDeleteByRank
	 * <p>
	 * 内部删除函数，
	 * 被 zslDelete 、 zslDeleteRangeByScore 和 zslDeleteByRank 等函数调用。
	 * <p>
	 * T = O(1)
	 *
	 * @param updateArray 当前要删除的节点的 某一层的上一个节点;或者说是需要更新的节点数组
	 */
	private void zslDeleteNode(ZSkipList list, ZSkipListNode deleteNode, ZSkipListNode[] updateArray) {
		// 更新所有和被删除节点 x 有关的节点的指针，解除它们之间的关系
		// T = O(1)
		for (int i = 0; i < list.getLevel(); i++) {
			ZSkipListLevel zSkipListLevel = updateArray[i].getLevels()[i];
			if (zSkipListLevel.getForward() == deleteNode) {
				zSkipListLevel.setSpan(deleteNode.getLevels()[i].getSpan() - 1);
				zSkipListLevel.setForward(deleteNode.getLevels()[i].getForward());
			} else {
				zSkipListLevel.setSpan(zSkipListLevel.getSpan() - 1);
			}
		}

		// 更新被删除节点的下一个指针的后退指针
		if (deleteNode.getLevels()[0].getForward() != null) {
			deleteNode.getLevels()[0].getForward().setBackward(deleteNode.getBackward());
		} else {
			// 更新tail指针
			list.setTail(deleteNode.getBackward());
		}
		// 更新跳跃表最大层数(只在被删除节点数跳跃表中最高节点时才执行)
		// T = O(1)
		while (list.getLevel() > 1 && list.getHeader().getLevels()[list.getLevel() - 1].getForward() == null) {
			list.setLevel(list.getLevel() - 1);
		}
		// 跳跃表节点计数器减一
		list.setLength(list.getLength() - 1);
	}

	/**
	 * Delete an element with matching score/object from the skiplist.
	 * <p>
	 * 从跳跃表 zsl 中删除包含给定节点 score 并且带有指定对象 obj 的节点。
	 * <p>
	 * T_wrost = O(N^2), T_avg = O(N log N)
	 */
	public boolean zslDelete(ZSkipList list, double score, Object obj) {
		ZSkipListNode[] update = new ZSkipListNode[ZSKIPLIST_MAXLEVEL];
		ZSkipListNode tempUpdate = null;

		// 遍历跳跃表，查找目标节点，并记录所有沿途节点
		// T_wrost = O(N^2), T_avg = O(N log N)
		tempUpdate = list.getHeader();
		for (int i = list.getLevel() - 1; i >= 0; i--) {
			// 遍历跳跃表的复杂度为 T_wrost = O(N), T_avg = O(log N)


			while (tempUpdate.getLevels()[i].getForward() != null &&
					// 比对分值和 比对对象字典
					(tempUpdate.getLevels()[i].getForward().getScore() < score || (tempUpdate.getLevels()[i].getForward().getScore() == score && compareStringObjects(tempUpdate.getLevels()[i].getForward().getObj(), obj) < 0))) {
				// 沿着前进指针移动
				tempUpdate = tempUpdate.getLevels()[i].getForward();
			}
			// 记录沿途节点
			update[i] = tempUpdate;
		}

		/* We may have multiple elements with the same score, what we need
		 * is to find the element with both the right score and object.
		 *
		 * 检查找到的元素 x ，只有在它的分值和对象都相同时，才将它删除。
		 */
		ZSkipListNode deleteNode = tempUpdate.getLevels()[0].getForward();
		if (deleteNode != null && deleteNode.getScore() == score && compareStringObjects(obj, deleteNode.getObj()) == 0) {
			// T=O(1)
			zslDeleteNode(list, deleteNode, update);
			zslFreeNode(deleteNode);
			return true;
		} else {
			return false;
		}
	}


	/* Delete all the elements with score between min and max from the skiplist.
	 *
	 * 删除所有分值在给定范围之内的节点。
	 *
	 * Min and max are inclusive, so a score >= min || score <= max is deleted.
	 *
	 * min 和 max 参数都是包含在范围之内的，所以分值 >= min 或 <= max 的节点都会被删除。
	 *
	 * Note that this function takes the reference to the hash table view of the
	 * sorted set, in order to remove the elements from the hash table too.
	 *
	 * 节点不仅会从跳跃表中删除，而且会从相应的字典中删除。
	 *
	 * 返回值为被删除节点的数量
	 *
	 * T = O(N)
	 */

	public long zslDeleteRangeByScore(ZSkipList list, ZRangeSpec range, HashMap map) {
		ZSkipListNode[] update = new ZSkipListNode[ZSKIPLIST_MAXLEVEL];
		ZSkipListNode removeNode = null;
		long removedNum = 0;

		// 记录所有和被删除节点(们)有关的节点
		removeNode = list.getHeader();
		range.zslFirstInRange(list);
		for (int i = list.getLevel() - 1; i >= 0; i--) {
			// 获取第一个大于 min的值
			while (removeNode.getLevels()[i].getForward() != null &&
					!range.zslValueGteMin(removeNode.getLevels()[i].getForward().getScore())) {
				removeNode = removeNode.getLevels()[i].getForward();
			}
			update[i] = removeNode;
		}

		// 定位到给定返回开始的第一个节点
		removeNode = removeNode.getLevels()[0].getForward();
		// 删除范围中的所有节点
		// T = O(N)
		// 要删除的第一个节点不为null 并且小于max
		while (removeNode != null && range.zslValueLteMax(removeNode.getScore())) {
			// 记录下个节点的指针
			ZSkipListNode next = removeNode.getLevels()[0].getForward();
			zslDeleteNode(list, removeNode, update);
			map.remove(removeNode.getObj());
			zslFreeNode(removeNode);
			removedNum++;
			removeNode = next;
		}
		return removedNum;
	}

	public long zslDeleteRangeByLex(ZSkipList list, ZlExRangeSpec range, HashMap map) {
		ZSkipListNode[] update = new ZSkipListNode[ZSKIPLIST_MAXLEVEL];
		ZSkipListNode removeNode = null;
		long removedNum = 0;

		removeNode = list.getHeader();
		for (int i = list.getLevel() - 1; i >= 0; i--) {
			while (removeNode.getLevels()[i].getForward() != null &&
					!range.zslLexValueGteMin(removeNode.getLevels()[i].getForward().getObj())) {
				removeNode = removeNode.getLevels()[i].getForward();
			}
		}

		removeNode = removeNode.getLevels()[0].getForward();
		while (removeNode != null && range.zslLexValueLteMax(removeNode.getObj())) {
			ZSkipListNode tempNode = removeNode.getLevels()[0].getForward();
			// 从跳跃表中删除当前节点
			zslDeleteNode(list, removeNode, update);
			// 从字典中删除当前节点
			map.remove(removeNode.getObj());
			// 释放当前跳跃表节点的结构
			zslFreeNode(removeNode);
			// 增加删除计数器
			removedNum++;
			// 继续处理下一个节点
			removeNode = tempNode;
		}
		// 返回被删除节点的数量
		return removedNum;
	}

	/* Delete all the elements with rank between start and end from the skiplist.
	 *
	 * 从跳跃表中删除所有给定排位内的节点。
	 *
	 * Start and end are inclusive. Note that start and end need to be 1-based
	 *
	 * start 和 end 两个位置都是包含在内的。注意它们都是以 1 为起始值。
	 *
	 * 函数的返回值为被删除节点的数量。
	 *
	 * T = O(N)
	 */
	public long zslDeleteRangeByRank(ZSkipList list, int start, int end, HashMap map) {
		ZSkipListNode[] update = new ZSkipListNode[ZSKIPLIST_MAXLEVEL];
		ZSkipListNode removeNode = null;
		long removedNum = 0;
		long traversed = 0;

		// 沿着前进指针移动到指定rank的起始位置，并记录所有沿途指针
		// T_wrost = O(N) , T_avg = O(log N)
		removeNode = list.getHeader();
		for (int i = list.getLevel() - 1; i >= 0; i--) {
			while (removeNode.getLevels()[i].getForward() != null &&
					(traversed + removeNode.getLevels()[i].getSpan() < start)) {
				traversed += removeNode.getLevels()[i].getSpan();
				removeNode = removeNode.getLevels()[i].getForward();
			}
			update[i] = removeNode;
		}

		// 移动到rank的起始的第一个节点
		traversed++;
		removeNode = removeNode.getLevels()[0].getForward();
		// 删除所有在给定rank范围内的节点
		// T = O(N)
		while (removeNode != null && traversed <= end) {
			// 记录下一节点的指针
			ZSkipListNode next = removeNode.getLevels()[0].getForward();
			// 从字典中删除节点
			map.remove(removeNode.getObj());
			zslFreeNode(removeNode);
			removedNum++;
			// 为 rank 计数器增一
			traversed++;
			// 处理下个节点
			removeNode = next;
		}
		// 返回删除节点的数量
		return removedNum;
	}

	/* Find the rank for an element by both score and key.
	 *
	 * 查找包含给定分值和成员对象的节点在跳跃表中的排位。
	 *
	 * Returns 0 when the element cannot be found, rank otherwise.
	 *
	 * 如果没有包含给定分值和成员对象的节点，返回 0 ，否则返回排位。
	 *
	 * Note that the rank is 1-based due to the span of zsl->header to the
	 * first element.
	 *
	 * 注意，因为跳跃表的表头也被计算在内，所以返回的排位以 1 为起始值。
	 *
	 * T_wrost = O(N), T_avg = O(log N)
	 */
	public long zslGetRank(ZSkipList list, double socre, Object obj) {
		ZSkipListNode node;
		long rank = 0;

		// 遍历整个跳跃表
		node = list.getHeader();
		for (int i = list.getLevel() - 1; i >= 0; i--) {
			while (node.getLevels()[i].getForward() != null &&
					(node.getLevels()[i].getForward().getScore() < socre || (
							node.getLevels()[i].getForward().getScore() == socre
									// 注意：这里是 <= 0
									&& compareStringObjects(node.getLevels()[i].getForward().getObj(), obj) <= 0
					))) {
				// 积累跨越的节点
				rank += node.getLevels()[i].getSpan();
				node = node.getLevels()[i].getForward();
			}
		}

		/* x might be equal to zsl->header, so test if obj is non-NULL */
		// 必须确保不仅分值相等，而且成员对象也要相等
		// T = O(N)
		if (node.getObj() != null && equalStringObjects(node.getObj(), obj)) {
			return rank;
		}
		// 没找到
		return 0;
	}


	/* Finds an element by its rank. The rank argument needs to be 1-based.
	 *
	 * 根据排位在跳跃表中查找元素。排位的起始值为 1 。
	 *
	 * 成功查找返回相应的跳跃表节点，没找到则返回 NULL 。
	 *
	 * T_wrost = O(N), T_avg = O(log N)
	 */
	public ZSkipListNode zslGetElementByRank(ZSkipList list, long rank) {
		ZSkipListNode node;
		long traversed = 0;
		// T_wrost = O(N), T_avg = O(log N)

		node = list.getHeader();
		for (int i = list.getLevel() - 1; i >= 0; i--) {
			while (node.getLevels()[i].getForward() != null &&
					traversed + node.getLevels()[i].getSpan() <= rank) {
				traversed += node.getLevels()[i].getSpan();
				node = node.getLevels()[i].getForward();
			}

			// 如果越过的节点数量已经等于 rank
			// 那么说明已经到达要找的节点
			if (traversed == rank) {
				return node;
			}
		}
		// 没有找到目标节点
		return null;
	}


	/*-----------------------------------------------------------------------------
	 * Ziplist-backed sorted set API
	 *----------------------------------------------------------------------------*/


	/************************关联低的代码****************************/
	/************************关联低的代码****************************/
	/************************关联低的代码****************************/
	/************************关联低的代码****************************/
	/**
	 * 根据字典排序 位置小的排在前面
	 */
	private int compareStringObjects(Object obj1, Object obj2) {
		return ZSetUtil.compareStringObjects(obj1, obj2);
	}

	private boolean equalStringObjects(Object obj1, Object obj2) {
		return obj1.equals(obj2);
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

	/*
	 * 取出 sptr 指向节点所保存的有序集合元素的分值
	 * 应该是给个指针直接取该指针里面的 score
	 */
	double zzlGetScore(String sptr) {
		String vstr;
		int vlen;
		long vlong;
		int[] buf = new int[128];
		double score;

		if (sptr == null) {
			return -1;
		}
		return 0;
	}

	/* Return a ziplist element as a Redis string object.
	 * This simple abstraction can be used to simplifies some code at the
	 * cost of some performance. */
	Object ziplistGetObject(String sptr) {
		return new Object();
	}

	/* Compare element in sorted set with given element.
	 *
	 * 将 eptr 中的元素和 cstr 进行对比。
	 *
	 * 相等返回 0 ，
	 * 不相等并且 eptr 的字符串比 cstr 大时，返回正整数。
	 * 不相等并且 eptr 的字符串比 cstr 小时，返回负整数。
	 */
	boolean zzlCompareElements(String eptr, String cstr) {
		return eptr.equals(cstr);
	}

	/*
	 * 返回跳跃表包含的元素数量
	 */
	long zzlLength(ZSkipList list) {
		return list.getLength();
	}

	void zzlNext(String zl, String epstr) {

	}

}
