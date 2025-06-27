package com.youxi.dataStructure.松散四叉树;

import java.util.ArrayList;
import java.util.List;

/**
 * 松散网络型四叉树
 *
 * @param <T>
 */
public class QuadTree<T> {
	public int maxDepth;
	public float gridMinWidth;
	public float gridMinHeight;
	public List<QPoint> depthSize = new ArrayList<>();
	protected QuadTreeNode<T> quadTreeNode;


	public QPoint getSizeByDepth(int dep) {
		return this.depthSize.get(dep);
	}

	public boolean isMaxDepth(int depth) {
		return depth >= this.maxDepth - 1;
	}


	private int disDepth(float width,float height){
		double dw = Math.max(0,Math.floor(Math.log(width / this.gridMinWidth + 1)));
		double dh = Math.max(0,Math.floor(Math.log(height/ this.gridMinHeight + 1)));
		return (int) Math.max(dw,dh);
	}

	public int getDepthBySize(float width, float height) {
		int disDepth = this.disDepth(width, height);
		return this.maxDepth - disDepth - 1;
	}
	/**
	 *
	 * @param originX 左下角x
	 * @param originY 左下角y
	 * @param conWidth 宽度
	 * @param conHeight 高度
	 * @param checkMinWidth 检测的物体的最小宽度
	 */
	QuadTree(float originX,float originY,float conWidth,float conHeight,float checkMinWidth){
		this.gridMinWidth = checkMinWidth;
		this.gridMinHeight = checkMinWidth;
		this.maxDepth = this.disDepth(conWidth,conHeight);
		int tmp = (int) Math.pow(2,this.maxDepth-1);
		this.gridMinWidth = conWidth / tmp;
		this.gridMinHeight = conHeight / tmp;
		//初始化层级size
		for(int i=0; i < this.maxDepth; i++){
			this.depthSize.add(new QPoint((float) (conWidth/Math.pow(2,i)), (float) (conHeight/Math.pow(2,i))));
		}
		this.quadTreeNode = new QuadTreeNode(originX,originY,0,this);
	}

	/**
	 * 更新四叉树
	 * @param item
	 * @param rect
	 */
	void update(T item,QRect rect){
		this.quadTreeNode.update(item,rect);
	}

	/**
	 * 查询碰撞节点
	 * @param rect
	 * @param outItms 接收数组
	 * @param outVisItms 访问过的节点
	 */
	void quary(QRect rect, List<T> outItms,List<T> outVisItms){
		this.quadTreeNode.quary(rect,outItms,outVisItms);
	}

}
