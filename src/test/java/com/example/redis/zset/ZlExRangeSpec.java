package com.example.redis.zset;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/22 19:26
 * @Description: Struct to hold an inclusive/exclusive range spec by lexicographic comparison.
 */
public class ZlExRangeSpec {
	// 通过复用来减少内存碎片，以及减少操作耗时的共享对象
	private static final Object shared = null;


	Object min, max;// May be set to shared.(minstring|maxstring)
	boolean minex, maxex; /* are min or max exclusive? */


	public ZlExRangeSpec(Object min, Object max, boolean minex, boolean maxex) {
		this.min = min;
		this.max = max;
		this.minex = minex;
		this.maxex = maxex;
	}

	/* This is just a wrapper to compareStringObjects() that is able to
	 * handle shared.minstring and shared.maxstring as the equivalent of
	 * -inf and +inf for strings */
	public int compareStringObjectsForLexRange(Object obj1, Object obj2) {
		 /* This makes sure that we handle inf,inf and
                             -inf,-inf ASAP. One special case less. */
		if (obj1 == obj2) {
			return 0;
		}
		//if (obj1 == shared.minstring || obj2 == shared.maxstring) return -1;
		//if (obj1 == shared.maxstring || obj2 == shared.minstring) return 1;
		return compareStringObjects(obj1, obj2);
	}

	public boolean zslLexValueGteMin(Object value) {
		return minex ? compareStringObjectsForLexRange(value, min) > 0 : compareStringObjectsForLexRange(value, min) >= 0;
	}

	public boolean zslLexValueLteMax(Object value) {
		return maxex ? compareStringObjectsForLexRange(value, max) < 0 : compareStringObjectsForLexRange(value, max) <= 0;
	}

	/**
	 * Returns if there is a part of the zset is in the lex range.
	 * 给定一个范围，如果跳跃表有至少一个节点在这个范围内返回 true 否则返回 false
	 */
	public boolean zslIsInLexRange(ZSkipList list) {
		ZSkipListNode node;
		/* Test for ranges that will always be empty. */
		if (compareStringObjectsForLexRange(min, max) == 0 ||
				(compareStringObjects(min, max) == 0 && (minex || maxex))) {
			return false;
		}
		node = list.getTail();
		if (node == null || !zslLexValueGteMin(node.getObj())) {
			return false;
		}

		node = list.getHeader().getLevels()[0].getForward();
		if (node == null || !zslLexValueLteMax(node.getObj())) {
			return false;
		}
		return true;
	}

	/**
	 * Find the first node that is contained in the specified lex range.
	 * Returns NULL when no element is contained in the range.
	 * 获取第一个满足range的node
	 */
	ZSkipListNode zslFirstInLexRange(ZSkipList list) {
		if (!zslIsInLexRange(list)) {
			return null;
		}
		ZSkipListNode node = null;

		node = list.getHeader();
		for (int i = list.getLevel() - 1; i >= 0; i--) {
			/* Go forward while *OUT* of range. */
			while (node.getLevels()[i].getForward() != null &&
					(zslLexValueGteMin(node.getLevels()[i].getForward().getObj()))) {
				node = node.getLevels()[i].getForward();
			}
		}

		ZSkipListNode forward = node.getLevels()[0].getForward();
		if (forward == null) {
			return null;
		}
		if (!zslLexValueLteMax(forward.getObj())) {
			return null;
		}
		return node;
	}

	/* Find the last node that is contained in the specified range.
	 * Returns NULL when no element is contained in the range. */
	ZSkipListNode zslLastInLexRange(ZSkipList list) {
		if (!zslIsInLexRange(list)) {
			return null;
		}
		ZSkipListNode node = null;

		node = list.getHeader();
		for (int i = list.getLevel() - 1; i >= 0; i--) {
			/* Go forward while *OUT* of range. */
			while (node.getLevels()[i].getForward() != null &&
					(zslLexValueLteMax(node.getLevels()[i].getForward().getObj()))) {
				node = node.getLevels()[i].getForward();
			}
		}

		ZSkipListNode forward = node.getLevels()[0].getForward();
		if (forward == null) {
			return null;
		}
		if (!zslLexValueGteMin(forward.getObj())) {
			return null;
		}
		return node;
	}


	/**
	 * 根据字典排序 位置小的排在前面
	 */
	private int compareStringObjects(Object obj1, Object obj2) {
		return ZSetUtil.compareStringObjects(obj1, obj2);
	}

}
