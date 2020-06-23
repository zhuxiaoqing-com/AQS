package com.example.redis.zset;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/22 16:40
 * @Description:
 */
public class ZRangeSpec {
	double min;
	double max;
	// 指示最小值和最大值是否*不*包含在范围之内
	// 值为 1 表示不包含，值为 0 表示包含
	boolean minex, maxex; /* are min or max exclusive? */

	public ZRangeSpec(double min, double max, boolean minex, boolean maxex) {
		this.min = min;
		this.max = max;
		this.minex = minex;
		this.maxex = maxex;
	}

	public ZRangeSpec(double min, double max) {
		this.min = min;
		this.max = max;
		this.minex = false;
		this.maxex = false;
	}

	//T = O(1)
	public boolean zslValueGteMin(double value) {
		return minex ? value > min : value >= min;
	}

	//T = O(1)
	public boolean zslValueLteMax(double value) {
		return minex ? value > max : value >= max;
	}

	/**
	 * 如果给定的分值范围包含在跳跃表的分值范围之内，
	 * * 那么返回 1 ，否则返回 0 。
	 * <p>
	 * 给定一个分值范围，如果跳跃表有至少一个节点的分值在这个范围内返回 true 否则返回 false
	 * <p>
	 * T = O(1)
	 *
	 * @param list list
	 * @return
	 */
	public boolean zslIsInRange(ZSkipList list) {
		// 这里 min==max 并且minex && maxex  是可以通过的
		// 就是说如果包含 min 也包含 max，那么是允许 min==max 的
		if (min > max || (min == max && (minex || maxex))) {
			return false;
		}
		// 检查最大分值
		ZSkipListNode tail = list.getTail();
		if (tail == null || !zslValueGteMin(tail.getScore())) {
			return false;
		}

		// 检查最小分值
		ZSkipListNode forward = list.getHeader().getLevels()[0].getForward();
		if (forward == null || !zslValueLteMax(forward.getScore())) {
			return false;
		}
		return true;
	}

	/**
	 * Find the first node that is contained in the specified range.
	 * <p>
	 * 返回 zsl 中第一个分值符合 range 中指定范围的节点。
	 * Returns NULL when no element is contained in the range.
	 * <p>
	 * 如果 zsl 中没有符合范围的节点，返回 NULL 。
	 * <p>
	 * T_wrost = O(N), T_avg = O(log N)
	 */
	public ZSkipListNode zslFirstInRange(ZSkipList list) {
		// 先确保跳跃表中至少有一个节点符合 range 指定的范围，
		// 否则直接失败
		// T = O(1)
		if (!zslIsInRange(list)) {
			return null;
		}
		ZSkipListNode first = null;
		// 遍历跳跃表，查找符合范围 min 项的节点
		// T_wrost = O(N), T_avg = O(log N)
		first = list.getHeader();
		for (int i = list.getLevel() - 1; i >= 0; i--) {
			while (first.getLevels()[i].getForward() != null && !zslValueGteMin(first.getLevels()[i].getForward().getScore())) {
				first = first.getLevels()[i].getForward();
			}
		}
		first = first.getLevels()[0].getForward();
		if (first == null) {
			return null;
		}
		// 检查节点是否符合返回的 max 项
		// T = O(1)
		if (!zslValueLteMax(first.getScore())) {
			return null;
		}

		return first;
	}


	/* Find the last node that is contained in the specified range.
	 * Returns NULL when no element is contained in the range.
	 *
	 * 返回 zsl 中最后一个分值符合 range 中指定范围的节点。
	 *
	 * 如果 zsl 中没有符合范围的节点，返回 NULL 。
	 * 第一个 > range.max 的值 的 forward 就是最后一个符合的值
	 *
	 * T_wrost = O(N), T_avg = O(log N)
	 */
	public ZSkipListNode zslLastInRange(ZSkipList list) {
		// 先确保跳跃表中至少有一个节点符合 range 指定的范围，
		// 否则直接失败
		// T = O(1)
		if (!zslIsInRange(list)) {
			return null;
		}
		ZSkipListNode last = null;

		last = list.getHeader();
		// 遍历跳跃表，查找符合范围 max 项的节点
		// T_wrost = O(N), T_avg = O(log N)
		for (int i = list.getLevel() - 1; i >= 0; i--) {
			while (last.getLevels()[i].getForward() != null && zslValueLteMax(last.getLevels()[i].getForward().getScore())) {
				last = last.getLevels()[i].getForward();
			}
		}

		if (last == null) {
			return null;
		}
		/* Check if score >= min. */
		// 检查节点是否符合范围的 min 项
		// T = O(1)
		if (!zslValueGteMin(last.getScore())) {
			return null;
		}
		return last;
	}


	/* Populate the rangespec according to the objects min and max.
	 *
	 * 对 min 和 max 进行分析，并将区间的值保存在 spec 中。
	 *
	 * 分析成功返回 REDIS_OK ，分析出错导致失败返回 REDIS_ERR 。
	 *
	 * T = O(N)
	 */
	public static ZRangeSpec zslParseRange(Object min, Object max) {
		return new ZRangeSpec(Double.parseDouble(min.toString()), Double.parseDouble(max.toString()));
	}


	/* Populate the rangespec according to the objects min and max.
	 *
	 * 对 min 和 max 进行分析，并将区间的值保存在 spec 中。
	 *
	 * 分析成功返回 REDIS_OK ，分析出错导致失败返回 REDIS_ERR 。
	 *
	 * T = O(N)
	 */
/*	static int zslParseRange(robj *min, robj *max, zrangespec *spec) {
		char *eptr;

		// 默认为闭区间
		spec->minex = spec->maxex = 0;

		*//* Parse the min-max interval. If one of the values is prefixed
		 * by the "(" character, it's considered "open". For instance
		 * ZRANGEBYSCORE zset (1.5 (2.5 will match min < x < max
		 * ZRANGEBYSCORE zset 1.5 2.5 will instead match min <= x <= max *//*
		if (min->encoding == REDIS_ENCODING_INT) {
			// min 的值为整数，开区间
			spec->min = (long)min->ptr;
		} else {
			// min 对象为字符串，分析 min 的值并决定区间
			if (((char*)min->ptr)[0] == '(') {
				// T = O(N)
				spec->min = strtod((char*)min->ptr+1,&eptr);
				if (eptr[0] != '\0' || isnan(spec->min)) return REDIS_ERR;
				spec->minex = 1;
			} else {
				// T = O(N)
				spec->min = strtod((char*)min->ptr,&eptr);
				if (eptr[0] != '\0' || isnan(spec->min)) return REDIS_ERR;
			}
		}

		if (max->encoding == REDIS_ENCODING_INT) {
			// max 的值为整数，开区间
			spec->max = (long)max->ptr;
		} else {
			// max 对象为字符串，分析 max 的值并决定区间
			if (((char*)max->ptr)[0] == '(') {
				// T = O(N)
				spec->max = strtod((char*)max->ptr+1,&eptr);
				if (eptr[0] != '\0' || isnan(spec->max)) return REDIS_ERR;
				spec->maxex = 1;
			} else {
				// T = O(N)
				spec->max = strtod((char*)max->ptr,&eptr);
				if (eptr[0] != '\0' || isnan(spec->max)) return REDIS_ERR;
			}
		}

		return REDIS_OK;
	}*/
}
