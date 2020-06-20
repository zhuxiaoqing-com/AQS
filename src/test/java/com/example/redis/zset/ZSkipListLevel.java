package com.example.redis.zset;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/18 17:14
 * @Description:
 * @url http://zhangtielei.com/posts/blog-redis-skiplist.html
 */
public class ZSkipListLevel {
	// 前进指针
	private ZSkipListNode forward;

	// 跨度 当前同层的 节点和下一个节点之间 有几个基础节点
	private long span;

	public ZSkipListLevel(ZSkipListNode forward, int span) {
		this.forward = forward;
		this.span = span;
	}

	public ZSkipListNode getForward() {
		return forward;
	}

	public void setForward(ZSkipListNode forward) {
		this.forward = forward;
	}

	public long getSpan() {
		return span;
	}

	public void setSpan(long span) {
		this.span = span;
	}
}
