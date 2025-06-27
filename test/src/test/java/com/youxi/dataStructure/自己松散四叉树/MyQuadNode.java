package com.youxi.dataStructure.自己松散四叉树;

import com.youxi.entity.Position;

import java.util.ArrayList;
import java.util.List;

public class MyQuadNode {
	public MyQuadTree tree;
	public MyQuadNode parent;
	// 该节点的界限
	public MyLRect bounds;
	// 坐标
	public Position center;
	// 基础配置的长度
	public int baseLength;
	public int adjustLength;
	// 该节点内的碰撞
	public List<ColliderProxy> objects = new ArrayList<>();
	// 子节点
	/**
	 * 3(-,+)	|  4(+,+)
	 * ----|-----
	 * 1 (-,-)	|  2(+,-)
	 */
	public MyQuadNode[] children;


	public MyQuadNode(MyQuadNode parent, MyQuadTree tree, Position center, int baseLength) {
		this.parent = parent;
		this.tree = tree;
		this.center = center;
		this.baseLength = baseLength;

		this.adjustLength = (int) (baseLength * tree.looseValue);

		Position size = new Position(adjustLength, adjustLength);
		this.bounds = new MyLRect(center.sub(size.multiply(0.5f)), size);
	}


	// 添加
	public boolean add(ColliderProxy proxy) {
		// 判断该节点是否包含该 lrect
		if (!contain(proxy.myLRect)) {
			return false;
		}
		subAdd(proxy);
		return true;
	}

	// 删除
	public boolean remove(ColliderProxy proxy) {
		MyQuadNode myQuadNode = tree.map.remove(proxy);
		if (myQuadNode == null) {
			return false;
		}

		if (!myQuadNode.objects.remove(proxy)) {
			System.out.println("error!!! map里面有这个proxy, 但是node里面又没有找到！！！");
			return false;
		}
		onRemove();

		return true;
	}


	private void subAdd(ColliderProxy proxy) {
		/**
		 * 1、判断是否需要分割
		 * 	需要分割 分割;分割完了以后再将当前节点的Obj分散到分割的各个节点里面去
		 *
		 * 2、根据 中心点获取最合适的子节点,查看该子节点是否包含其;包含就调用子节点的subAdd(),不包含就加入obj;
		 */
		MyLRect lRect = proxy.myLRect;
		if (!hasChildren()) {
			// 如果不能扩容 就直接加入当前节点
			if (objects.size() < MyQuadTree.SPLIT_NUM || baseLength / 2 < tree.minSize) {
				objects.add(proxy);
				tree.map.put(proxy, this);
			}

			// 进行扩容
			split();

			// 将当前节点的obj分散到扩容好的各个节点里面去
			// 这里为啥要反着遍历;因为要在遍历中删除list里面的值
			for (int i = objects.size() - 1; i >= 0; i--) {
				ColliderProxy object = objects.get(i);
				MyQuadNode child = children[bestFitChild(object.myLRect.getCenter())];
				if (child.contain(object.myLRect)) {
					child.subAdd(object);
					objects.remove(object);
				}
			}
			return;
		}

		// 如果已经有子节点了
		MyQuadNode child = children[bestFitChild(lRect.getCenter())];
		if (child.contain(lRect)) {
			child.subAdd(proxy);
		} else {
			objects.add(proxy);
			tree.map.put(proxy, this);
		}
	}


	/**
	 * 判断是否能合并节点
	 */
	private void onRemove() {
		if (shouldMerge()) {
			merge();
			// 判断父节点是否需要合并 这里子节点不能合并那么父节点肯定也不能合并;因为子节点的obj数量就大于可以需要合并的数量;那么父节点就更加大了更加不需要检测了;
			if (parent != null) {
				parent.onRemove();
			}
		}
	}

	/**
	 * 其子节点没有子节点 并且 自己和子节点的碰撞数量小于  NUM_OBJECTS_ALLOWED 个
	 */
	private boolean shouldMerge() {
		int size = objects.size();
		for (MyQuadNode child : children) {
			// 如果子节点还有子节点不能合并
			if (child.children != null) {
				return false;
			}
			size += child.objects.size();
		}
		return size <= MyQuadTree.MERGE_NUM;
	}


	private void merge() {
		if (children == null) {
			return;
		}
		/*
		 * 合并怎么合并;
		 * 将其子节点删除;然后将其Obj都加入到当前节点中;
		 */
		for (MyQuadNode child : children) {
			for (ColliderProxy object : child.objects) {
				objects.add(object);
				tree.map.put(object, this);
			}
		}
		children = null;
	}


	private void split() {
		int newLength = baseLength / 2;
		int quad = baseLength / 4;

		children = new MyQuadNode[MyQuadTree.CHILD_NODE_NUM];

		children[0] = new MyQuadNode(this, tree, center.sub(new Position(-quad, -quad)), newLength);
		children[1] = new MyQuadNode(this, tree, center.sub(new Position(quad, -quad)), newLength);
		children[2] = new MyQuadNode(this, tree, center.sub(new Position(-quad, quad)), newLength);
		children[3] = new MyQuadNode(this, tree, center.sub(new Position(quad, quad)), newLength);
	}


	/**
	 * 是否包含该aabb
	 */
	public boolean contain(MyLRect lRect) {
		return bounds.contain(lRect);
	}

	/**
	 * 最合适的子节点
	 */
	/**
	 * 2(-,+)	|  3(+,+)
	 * ----|-----
	 * 0 (-,-)	|  1(+,-)
	 */
	public int bestFitChild(Position position) {
		return position.getX() < center.getX() ? 0 : 1 + position.getZ() < center.getZ() ? 0 : 2;
	}

	public boolean hasChildren() {
		return children != null;
	}


	/**
	 * 获取有重叠的对象
	 */
	public void getColliding(MyLRect checkBounds, List<ColliderProxy> result){
		// 如果该node不包含其aabb;
		if (!bounds.overlaps(checkBounds)) {
			return;
		}

		for (ColliderProxy object : objects) {
			if (object.myLRect.overlaps(checkBounds)) {
				result.add(object);
			}
		}

		// Check children
		if (children != null) {
			for (MyQuadNode child : children) {
				child.getColliding(checkBounds, result);
			}
		}
	}

}
