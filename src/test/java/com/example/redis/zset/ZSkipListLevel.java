package com.example.redis.zset;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/18 17:14
 * @Description:
 */
public class ZSkipListLevel {
	// 前进指针
	private ZSkipListNode forward;

	// 跨度
	private int span;

	public ZSkipListLevel(ZSkipListNode forward, int span) {
		this.forward = forward;
		this.span = span;
	}
}
