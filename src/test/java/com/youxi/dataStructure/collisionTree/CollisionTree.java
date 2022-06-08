package com.youxi.dataStructure.collisionTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2022/6/8 16:08
 * @Description: 四叉树
 */
public class CollisionTree {
	private Rect rect;
	private int level;
	private CollisionTree parent;
	List<CollisionTree> nodes;
	List<Object> objects;
	List<Object> invalid_objects;

	public static final int MAX_OBJECTS = 10;
	public static final int MAX_LEVELS = 5;

	public CollisionTree(Rect rect, int level, CollisionTree parent) {
		this.rect = rect;
		this.level = level;
		this.parent = parent;

		this.nodes = new ArrayList<>();
		this.objects = new ArrayList<>();
		this.invalid_objects = new ArrayList<>();
	}

	public boolean isRoot() {
		return parent == null;
	}

	/**
	 * 分裂
	 */
	public boolean split() {
		if (this.nodes.size() <= 0) {
			return false;
		}

		int x = this.rect.getX();
		int y = this.rect.getY();
		int w = this.rect.getWidth() / 2;
		int h = this.rect.getHeight() / 2;
		int newLevel = this.level + 1;

		this.nodes.add(new CollisionTree(new Rect(x + w, y + h, w, h), newLevel, this));
		this.nodes.add(new CollisionTree(new Rect(x, y + h, w, h), newLevel, this));
		this.nodes.add(new CollisionTree(new Rect(x, y, w, h), newLevel, this));
		this.nodes.add(new CollisionTree(new Rect(x + w, y, w, h), newLevel, this));

		return true;
	}

	/**
	 * 获取树子节点的大小
	 */
	public int getSize() {
		int n = 0;
		for (CollisionTree node : nodes) {
			n += node.getSize();
		}
		return n + objects.size();
	}

	public List<Integer> getIndexs(Rect rect) {
		if (!rect.intersect(rect)) {
			return Collections.emptyList();
		}

		boolean rigth = rect.getMinX() >= this.rect.getMidX();
		boolean left = rect.getMaxX() <= this.rect.getMidX();
		boolean top = rect.getMinY() >= this.rect.getMidY();
		boolean bottom = rect.getMaxY() <= this.rect.getMidY();

		ArrayList<Integer> result = new ArrayList<>(Arrays.asList(0, 1, 2, 3));

		if (!left) {
			//result.splice(1, 2);
			result.remove(1);
			result.remove(2);
		}
		if (!rigth) {
			//result.splice(3, 1);
			//result.splice(0, 1);
			result.remove(0);
			result.remove(2);
		}
		if (!top) {
			//result.splice(0, 2);
			result.remove(0);
			result.remove(1);
		}
		if (!bottom) {
			//result.splice(2, 2);
			result.remove(2);
			result.remove(3);
		}

		return result;
	}


	public int getIndex(Rect rect, boolean checkIsInner) {
		if (checkIsInner && !this.rect.contain(rect)) {
			if (!this.rect.intersect(rect)) {
				/**
				 * 不相交
				 */
				return -4;
			}
			/**
			 * 相交
			 */
			return -3;
		}

		boolean rigth = rect.getMinX() >= this.rect.getMidX();
		boolean left = rect.getMaxX() <= this.rect.getMidX();
		boolean top = rect.getMinY() >= this.rect.getMidY();
		boolean bottom = rect.getMaxY() <= this.rect.getMidY();

		if (this.nodes.size() <= 0) {
			/**
			 * 自身没有孩子节点，rect属于自身
			 */
			return -1;
		}
		if (top) {
			if (rigth) {
				return 0;
			} else if (left) {
				return 1;
			}
		} else if (bottom) {
			if (left) {
				return 2;
			} else if (rigth) {
				return 3;
			}
		}
		/**
		 * 不单单属于某一个象限
		 */
		return -2;
	}

	/*****************以下为外部调用***************/

	/*
	 * 插入功能：
	 *   - 如果当前节点[ 存在 ]子节点，则检查物体到底属于哪个子节点，如果能匹配到子节点，则将该物体插入到该子节点中
	 *   - 如果当前节点[ 不存在 ]子节点，将该物体存储在当前节点。随后，检查当前节点的存储数量，如果超过了最大存储数量，则对当前节点进行划分，划分完成后，将当前节点存储的物体重新分配到四个子节点中。
	 */

	public void insert(Rect rect) {
		int index = getIndex(rect, true);

		/**
		 * index为-4、-3、-2、-1、0、1、2、3时
		 *     当index为-3或-4时，并且parent为真，则调用parent的insert方法
		 *     当index为-4时，并且parent为假，则将rect添加到invalid_objects里面
		 *     当index>=0时，则调用index对象象限的insert方法
		 *     当index为-2或-1时，并且parent为假，则将rect添加到objs里面
		 */
	}
}
