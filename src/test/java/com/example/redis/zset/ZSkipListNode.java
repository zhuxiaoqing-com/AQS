package com.example.redis.zset;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/18 17:13
 * @Description:
 */
public class ZSkipListNode {
	// 成员对象
	private Object obj;
	// 分值
	private double score;
	// 后退指针
	private ZSkipListNode backward;

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
}
