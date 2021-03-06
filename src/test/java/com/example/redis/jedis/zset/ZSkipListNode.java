package com.example.redis.jedis.zset;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/18 17:13
 * @Description: 该node 零层节点的数据
 */
public class ZSkipListNode {
	// 成员对象
	private Object obj;
	// 分值
	private double score;
	// 后退指针
	private ZSkipListNode backward;
	// 层
	private ZSkipListLevel[] levels;

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public ZSkipListNode getBackward() {
		return backward;
	}

	public void setBackward(ZSkipListNode backward) {
		this.backward = backward;
	}

	public ZSkipListLevel[] getLevels() {
		return levels;
	}

	public void setLevels(ZSkipListLevel[] levels) {
		this.levels = levels;
	}
}
