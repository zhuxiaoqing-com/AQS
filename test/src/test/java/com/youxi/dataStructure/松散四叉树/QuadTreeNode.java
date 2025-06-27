package com.youxi.dataStructure.松散四叉树;

import java.util.ArrayList;
import java.util.List;

public class QuadTreeNode<T> {
	protected int curDepth;
	protected int nodesConut;
	protected float originX;
	protected float originY;
	protected List<QuadTreeNode<T>> treeNodes = new ArrayList<>();
	protected List<DataNode<T>> children = new ArrayList<>();
	protected QuadTree<T> rootTree;
	private static QRect looseRectTmp = new QRect(new QPoint(0, 0), new QPoint(0, 0));

	QuadTreeNode(float originX, float originY, int curDepth, QuadTree<T> rootTree) {
		this.originX = originX;
		this.originY = originY;
		this.curDepth = curDepth;
		this.rootTree = rootTree;
		this.nodesConut = 0;
		if (!this.isLeaf()) {
			this.splitQuadTree();
		}
	}


	/**
	 * 仅用来读
	 */
	public QRect looseRect() {
		QPoint size = this.rootTree.getSizeByDepth(this.curDepth);
		QuadTreeNode.looseRectTmp.origin.x = this.originX - size.getX() / 2;
		QuadTreeNode.looseRectTmp.origin.y = this.originY - size.getY() / 2;
		QuadTreeNode.looseRectTmp.size.x = size.x * 2;
		QuadTreeNode.looseRectTmp.size.y = size.y * 2;
		return QuadTreeNode.looseRectTmp;
	}

	public boolean isLeaf() {
		return this.rootTree.isMaxDepth(this.curDepth);
	}

	/**
	 * 四叉树节点
	 */
	void splitQuadTree() {
		int depth = this.curDepth + 1;
		QPoint size = this.rootTree.getSizeByDepth(depth);
		//left bottom
		QuadTreeNode<T> treeNode = new QuadTreeNode<T>(this.originX, this.originY, depth, this.rootTree);
		this.treeNodes.add(treeNode);
		//right bottom
		treeNode = new QuadTreeNode<T>(this.originX + size.getX(), this.originY, depth, this.rootTree);
		this.treeNodes.add(treeNode);
		//left up
		treeNode = new QuadTreeNode<T>(this.originX, this.originY + size.getY(), depth, this.rootTree);
		this.treeNodes.add(treeNode);
		//right up
		treeNode = new QuadTreeNode<T>(this.originX + size.getX(), this.originY + size.getY(), depth, this.rootTree);
		this.treeNodes.add(treeNode);
	}


	/**
	 * 更新链接索引路径
	 *
	 * @param centerX
	 * @param centerY
	 * @param maxDepth
	 * @param linkInfo
	 */
	void updatePosInfo(float centerX, float centerY, int maxDepth, LinkQuadTreeNode<T> linkInfo) {
		if (this.curDepth + 1 > maxDepth) {
			return;
		}
		QPoint size = this.rootTree.getSizeByDepth(this.curDepth + 1);
		int row = (int) Math.min(1, Math.floor((centerY - this.originY) / size.getY()));
		row = Math.max(0, row);
		int col = (int) Math.min(1, Math.floor((centerX - this.originX) / size.getX()));
		col = Math.max(0, col);
		int idx = row * 2 + col;
		QuadTreeNode<T> treeNode = this.treeNodes.get(idx);
		LinkQuadTreeNode<T> nextInfo = new LinkQuadTreeNode<T>(treeNode);
		linkInfo.next = nextInfo;
		treeNode.updatePosInfo(centerX, centerY, maxDepth, nextInfo);
	}


	/**
	 * 查询碰撞节点
	 *
	 * @param rect
	 * @param outItms    接收数组
	 * @param outVisItms 访问过的节点
	 */
	void quary(QRect rect, List<T> outItms, List<T> outVisItms) {
		List<QuadTreeNode<T>> queue = new ArrayList<>();
		if (this.checkNeedVisit(rect)) {
			queue.add(this);
		}

		int index = 0;
		while (index < queue.size()) {
			QuadTreeNode<T> treeNode = queue.get(index);
			for (int i = 0, n = treeNode.children.size(); i < n; i++) {
				DataNode<T> dataNode = treeNode.children.get(i);
				outVisItms.add(dataNode.data);
				if (QRect.intersects(dataNode.rect, rect)) {
					outItms.add(dataNode.data);
				}
			}

			if (!treeNode.isLeaf()) {
				for (int i = 0, n = treeNode.treeNodes.size(); i < n; i++) {
					if (treeNode.treeNodes.get(i).checkNeedVisit(rect)) {
						queue.add(treeNode.treeNodes.get(i));
					}
				}
			}
			index++;
		}
	}

	/**
	 * 更新四叉树
	 *
	 * @param item
	 * @param rect
	 */
	void update(T item, QRect rect) {
		//插入
		int tmpDepth = this.rootTree.getDepthBySize(rect.size.getX(), rect.size.getY());
		DataNode<T> dataNode = new DataNode<T>(item, tmpDepth, rect);

		dataNode.curLinkTreeInfo.treeNode = this;
		dataNode.curLinkTreeInfo.next = null;
		this.updatePosInfo(dataNode.rect.getCenterX(), dataNode.rect.getCenterY(), dataNode.depth, dataNode.curLinkTreeInfo);
		if (dataNode.isLinkChange()) {
			this.remove(dataNode);
			this.insert(dataNode);
			dataNode.lastLinkTreeInfo.copy(dataNode.curLinkTreeInfo);
		}
	}

	/**
	 * 插入节点
	 *
	 * @param dataNode
	 */
	void insert(DataNode<T> dataNode) {
		LinkQuadTreeNode<T> linkInfo = dataNode.curLinkTreeInfo;
		while (linkInfo!= null && linkInfo.treeNode != null) {
			linkInfo.treeNode.nodesConut++;
			if (linkInfo.next == null) {
				linkInfo.treeNode.children.add(dataNode);
			}
			linkInfo = linkInfo.next;
		}
	}

	/**
	 * 移除节点
	 *
	 * @param dataNode
	 */
	void remove(DataNode<T> dataNode) {
		LinkQuadTreeNode<T> linkInfo = dataNode.lastLinkTreeInfo;
		while (linkInfo!= null && linkInfo.treeNode != null) {
			linkInfo.treeNode.nodesConut--;
			if (linkInfo.next == null) {
				int index = linkInfo.treeNode.children.indexOf(dataNode);
				//linkInfo.treeNode.children.splice(index, 1);
			}
			linkInfo = linkInfo.next;
		}
	}


	private boolean checkNeedVisit(QRect rect) {
		return this.nodesConut > 0 && QRect.intersects(this.looseRect(), rect);
	}

}
