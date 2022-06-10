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
	private int level; //  当前层数
	private CollisionTree parent;
	List<CollisionTree> nodes; //  4个子节点队列。
	List<Rect> objects; // 当前节点内的待检测的对象。
	List<Rect> invalid_objects;

	public static final int MAX_OBJECTS = 10; // 是每个节点能容纳的最多对象超过 则分割4个节点, 我们并不是事先就分好格子， 而是在插入对象的时候才进行划分。
	public static final int MAX_LEVELS = 5; // 是 四叉树的最大层数 超过 则不再划分 从根节点开始 最多6 层。

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
		if (this.nodes.size() > 0) {
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

	/**
	 * 获取 rect 在哪几个象限中
	 */
	public List<Integer> getIndexs(Rect rect) {
		if (!rect.intersect(rect)) {
			return Collections.emptyList();
		}

		boolean rigth = rect.getMinX() >= this.rect.getMidX();
		boolean left = rect.getMaxX() <= this.rect.getMidX();
		boolean top = rect.getMinY() >= this.rect.getMidY();
		boolean bottom = rect.getMaxY() <= this.rect.getMidY();

		/**
		 * 0123 是四个象限
		 *
		 * 象限：
		 * 2   1
		 * 3   4
		 */
		ArrayList<Integer> result = new ArrayList<>(Arrays.asList(0, 1, 2, 3));

		if (!left) {
			//result.splice(1, 2);
			// 不在左边 删除象限 2,3
			result.remove(new Integer(2));
			result.remove(new Integer(3));
		}
		if (!rigth) {
			// 不在右边 删除象限 1,4
			//result.splice(3, 1);
			//result.splice(0, 1);
			result.remove(new Integer(1));
			result.remove(new Integer(4));
		}
		if (!top) {
			// 不在上边 删除象限 1,2
			//result.splice(0, 2);
			result.remove(new Integer(1));
			result.remove(new Integer(2));
		}
		if (!bottom) {
			// 不在下边 删除象限 3,4
			//result.splice(2, 2);
			result.remove(new Integer(3));
			result.remove(new Integer(4));
		}

		return result;
	}


	public int getIndex(Rect rect) {
		return getIndex(rect, false);
	}

	/**
	 * 0123 是四个象限
	 * <p>
	 * 象限：
	 * 2   1
	 * 3   4
	 */
	public int getIndex(Rect rect, boolean checkIsInner) {
		if (/*checkIsInner &&*/ !this.rect.contain(rect)) {
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
			/*
			  自身没有孩子节点，rect属于自身
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
		/*
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
		if (index <= -3 && this.parent != null) {
			this.parent.insert(rect);
			return;
		} else if (index == -4) {
			this.invalid_objects.add(rect);
			return;
		} else if (index >= 0) {
			this.nodes.get(index).insert(rect);
			return;
		}

		List<Rect> objs = this.objects;
		List<CollisionTree> nodes = this.nodes;

		// 存储rect
		objs.add(rect);

		// 判断当前rect数量是否超过阈值
		if (nodes.size() <= 0 && objs.size() > MAX_OBJECTS && this.level < MAX_LEVELS) {
			this.split();
			this.objects = new ArrayList<>();
			for (int i = objs.size() - 1; i >= 0; i--) {
				index = this.getIndex(objs.get(i));
				if (index >= 0) {
					nodes.get(index).insert(objs.get(i));
				} else {
					this.objects.add(objs.get(i));
				}
			}
		}
	}


	/**
	 * 刷新
	 */
	public void refresh() {
		List<Rect> objs = this.objects;
		List<Rect> iobjs = this.invalid_objects;
		List<CollisionTree> nodes = this.nodes;

		int i;
		// 只有root节点才有 iobjs
		if (this.isRoot() && iobjs.size() > 0) {
			this.invalid_objects = new ArrayList<>();
			for (Rect iobj : iobjs) {
				if (iobj.isValid()) {
					this.insert(iobj);
				}
			}
		}

		this.objects = new ArrayList<>();
		for (Rect obj : objs) {
			if (obj.isValid()) {
				this.insert(obj);
			}
		}

		// 递归刷新子树
		for (i = 0; i < nodes.size(); i++) {
			nodes.get(i).refresh();
		}
	}

	/**
	 * 检索功能：
	 * 给出一个物体对象，该函数负责将该物体可能发生碰撞的所有物体选取出来。该函数先查找物体所属的象限，该象限下的物体都是有可能发生碰撞的，然后再递归地查找子象限...
	 */
	public List<Rect> getCollisions(Rect rect) {
		List<Rect> result = new ArrayList<>();
		int i, index;
		List<Integer> indexs;

		index = this.getIndex(rect);
		// rect与自身不相交
		if (index == -4) {
			return result;
			// rect与自身相交
			// rect不单单属于某一个象限
		} else if (index == -3 || index == -2) {
			indexs = this.getIndexs(rect);
			for (Integer nodeIndex : indexs) {
				result.addAll(this.nodes.get(nodeIndex).getCollisions(rect));
			}
			// 自己没有子节点，rect属于自己
		} else if (index == -1) {
			// rect属于某一象限
		} else {
			result.addAll(this.nodes.get(index).getCollisions(rect));
		}

		result.addAll(this.objects);
		return result;
	}

}
