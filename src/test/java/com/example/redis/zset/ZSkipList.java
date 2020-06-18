package com.example.redis.zset;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/18 17:16
 * @Description:
 */
public class ZSkipList {
	// 表头节点和表尾节点
	private ZSkipListNode header, tail;
	// 表中节点的数量
	private	long length;
	// 表中层数最大的节点的层数
	private	int level;

	public ZSkipListNode getHeader() {
		return header;
	}

	public void setHeader(ZSkipListNode header) {
		this.header = header;
	}

	public ZSkipListNode getTail() {
		return tail;
	}

	public void setTail(ZSkipListNode tail) {
		this.tail = tail;
	}

	public long getLength() {
		return length;
	}

	public void setLength(long length) {
		this.length = length;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
}
