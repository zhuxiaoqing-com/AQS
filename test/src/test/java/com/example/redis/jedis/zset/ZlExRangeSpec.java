package com.example.redis.jedis.zset;

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

	public ZlExRangeSpec(Object min, Object max) {
		this.min = min;
		this.max = max;
		this.minex = false;
		this.maxex = false;
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


	/* Populate the rangespec according to the objects min and max.
	 *
	 * Return REDIS_OK on success. On error REDIS_ERR is returned.
	 * When OK is returned the structure must be freed with zslFreeLexRange(),
	 * otherwise no release is needed. */
	public static ZlExRangeSpec zslParseLexRange(Object min, Object max) {
		return new ZlExRangeSpec(min, max);
	}



	/* Parse max or min argument of ZRANGEBYLEX.
	 * (foo means foo (open interval)
	 * [foo means foo (closed interval)
	 * - means the min string possible
	 * + means the max string possible
	 *
	 * If the string is valid the *dest pointer is set to the redis object
	 * that will be used for the comparision, and ex will be set to 0 or 1
	 * respectively if the item is exclusive or inclusive. REDIS_OK will be
	 * returned.
	 *
	 * If the string is not a valid range REDIS_ERR is returned, and the value
	 * of *dest and *ex is undefined. */


	/* Parse max or min argument of ZRANGEBYLEX.
	 * (foo means foo (open interval)
	 * [foo means foo (closed interval)
	 * - means the min string possible
	 * + means the max string possible
	 *
	 * If the string is valid the *dest pointer is set to the redis object
	 * that will be used for the comparision, and ex will be set to 0 or 1
	 * respectively if the item is exclusive or inclusive. REDIS_OK will be
	 * returned.
	 *
	 * If the string is not a valid range REDIS_ERR is returned, and the value
	 * of *dest and *ex is undefined. */
/*	int zslParseLexRangeItem(robj *item, robj **dest, int *ex) {
		char *c = item->ptr;

		switch(c[0]) {
			case '+':
				if (c[1] != '\0') return REDIS_ERR;
        *ex = 0;
        *dest = shared.maxstring;
				incrRefCount(shared.maxstring);
				return REDIS_OK;
			case '-':
				if (c[1] != '\0') return REDIS_ERR;
        *ex = 0;
        *dest = shared.minstring;
				incrRefCount(shared.minstring);
				return REDIS_OK;
			case '(':
        *ex = 1;
        *dest = createStringObject(c+1,sdslen(c)-1);
				return REDIS_OK;
			case '[':
        *ex = 0;
        *dest = createStringObject(c+1,sdslen(c)-1);
				return REDIS_OK;
			default:
				return REDIS_ERR;
		}
	}*/

	/* Populate the rangespec according to the objects min and max.
	 *
	 * Return REDIS_OK on success. On error REDIS_ERR is returned.
	 * When OK is returned the structure must be freed with zslFreeLexRange(),
	 * otherwise no release is needed. */
	/*	static int zslParseLexRange(robj *min, robj *max, zlexrangespec *spec) {
	 *//* The range can't be valid if objects are integer encoded.
	 * Every item must start with ( or [. *//*
		if (min->encoding == REDIS_ENCODING_INT ||
				max->encoding == REDIS_ENCODING_INT) return REDIS_ERR;

		spec->min = spec->max = NULL;
		if (zslParseLexRangeItem(min, &spec->min, &spec->minex) == REDIS_ERR ||
				zslParseLexRangeItem(max, &spec->max, &spec->maxex) == REDIS_ERR) {
			if (spec->min) decrRefCount(spec->min);
			if (spec->max) decrRefCount(spec->max);
			return REDIS_ERR;
		} else {
			return REDIS_OK;
		}
	}*/

	/* Free a lex range structure, must be called only after zelParseLexRange()
	 * populated the structure with success (REDIS_OK returned). */
	/*void zslFreeLexRange(zlexrangespec *spec) {
		decrRefCount(spec->min);
		decrRefCount(spec->max);
	}*/
}
