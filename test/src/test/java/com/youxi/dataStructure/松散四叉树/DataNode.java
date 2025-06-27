package com.youxi.dataStructure.松散四叉树;

public class DataNode<T> {
	public T data;
	public QRect rect;
	public int depth;
	public LinkQuadTreeNode<T> lastLinkTreeInfo = new LinkQuadTreeNode<>();
	public LinkQuadTreeNode<T> curLinkTreeInfo = new LinkQuadTreeNode<>();


	public DataNode(T data, int depth, QRect rect) {
		this.data = data;
		this.rect = rect;
		this.depth = depth;
	}

	public boolean isLinkChange() {
		LinkQuadTreeNode<T> t1 = this.lastLinkTreeInfo.next;
		LinkQuadTreeNode<T> t2 = this.curLinkTreeInfo.next;
		while (t2 != null) {
			if (t1 != t2) {
				return true;
			}
			t1 = t1.next;
			t2 = t2.next;
		}
		return false;
	}

}
