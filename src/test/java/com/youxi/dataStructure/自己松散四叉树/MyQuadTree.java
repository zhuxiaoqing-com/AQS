package com.youxi.dataStructure.自己松散四叉树;

import com.example.demo1.util.vo.Vector2f;
import com.youxi.entity.Position;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyQuadTree {
	// 子节点数量
	public final static int CHILD_NODE_NUM = 4;
	// 合并子节点阈值
	public final static int MERGE_NUM = 5;
	// 创建子节点阈值
	public final static int SPLIT_NUM = 10;
	// 松散值
	public float looseValue;
	// node的最小长度
	public int minSize;

	public Map<ColliderProxy, MyQuadNode> map = new HashMap<>();

	// 根节点
	public MyQuadNode root;

	public MyQuadTree(int initialWorldSize, Position initialWorldPos, float looseValue, int minSize) {
		this.looseValue = looseValue;
		this.minSize = minSize;
		this.root = new MyQuadNode(null, this, initialWorldPos, initialWorldSize);
	}

	// 添加
	public boolean add(ColliderProxy proxy) {
		return root.add(proxy);
	}

	// 删除
	public boolean remove(ColliderProxy proxy) {
		return root.remove(proxy);
	}

	// 更新
	public void update(ColliderProxy proxy) {
		MyQuadNode node = getNode(proxy);
		if (node == null) {
			add(proxy);
			return;
		}

		if (!node.contain(proxy.myLRect)) {
			// 先删除旧的;再往新的添加
			remove(proxy);
			add(proxy);
		}
	}

	public MyQuadNode getNode(ColliderProxy proxy) {
		return map.get(proxy);
	}

	public void getColliding(List<ColliderProxy> collidingWith, MyLRect checkBounds) {
		root.getColliding(checkBounds, collidingWith);
	}
}



