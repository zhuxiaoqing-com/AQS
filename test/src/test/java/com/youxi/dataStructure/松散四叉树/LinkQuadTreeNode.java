package com.youxi.dataStructure.松散四叉树;

public class LinkQuadTreeNode<T> {
	public LinkQuadTreeNode<T> next;
	public QuadTreeNode<T> treeNode;

	public LinkQuadTreeNode() {
	}

	public LinkQuadTreeNode(QuadTreeNode<T> treeNode) {
		this.treeNode = treeNode;
	}
	public void copy(LinkQuadTreeNode<T> other){
		this.next = other.next;
		this.treeNode = other.treeNode;
	}

}
