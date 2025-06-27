package com.youxi.dataStructure.quadTree;

import com.example.demo1.util.vo.Vector2f;
import com.youxi.util.MathExt;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2022/6/14 17:02
 * @Description:
 */
public class BoundsQuadTree {

	// 子节点个数
	public static final int NUM_CHILDREN = 4;
	// 里面的阻挡个数
	public int count;

	// 根节点
	BoundsQuadTreeNode rootNode;
	// Should be a value between 1 and 2. A multiplier for the base size of a node.
	// 1.0 is a "normal" octree, while values > 1 have overlap
	// 松散四叉树  节点扩大倍数
	float looseness;
	// 初始大小
	float initialSize;
	// 节点的最小边长-本质上是最大深度的另一种选择 最小的节点长度
	float minSize;



	public BoundsQuadTree(float initialWorldSize, Vector2f initialWorldPos, float minNodeSize, float loosenessVal){
		if (minNodeSize > initialWorldSize) {
			System.out.println("Minimum node size must be at least as big as the initial world size. Was: " +
					minNodeSize + " Adjusted to: " + initialWorldSize);
			minNodeSize = initialWorldSize;
		}

		count = 0;
		initialSize = initialWorldSize;
		minSize = minNodeSize;
		looseness = MathExt.clamp(loosenessVal, 1f, 2f);
		rootNode = new BoundsQuadTreeNode(null, initialSize, minSize, looseness, initialWorldPos);
	}

	public void UpdateObj(ColliderProxy obj, LRect bound){
		BoundsQuadTreeNode node = GetNode(obj);
		if (node == null) {
			Add(obj, bound);
		}
		else {
			if (!node.ContainBound(bound)) {
				Remove(obj);
				Add(obj, bound);
			}
			else {
				// 这里更新有啥用吗 好像没啥用 有用的;这里更新坐标;
				node.UpdateObj(obj, bound);
			}
		}
	}

	public BoundsQuadTreeNode GetNode(ColliderProxy obj){
		return BoundsQuadTreeNode.obj2Node.get(obj);
	}

	public void Add(ColliderProxy obj, LRect objBounds){
		// Add object or expand the octree until it can be added
		int count = 0; // Safety check against infinite/excessive growth
		while (!rootNode.Add(obj, objBounds)) {
			if (++count > 20) {
				System.out.println("Aborted Add operation as it seemed to be going on forever (" + (count - 1) +
						") attempts at growing the octree.");
				return;
			}
		}

		count++;
	}

	public boolean Remove(ColliderProxy obj){
		boolean removed = rootNode.Remove(obj);

		// See if we can shrink the octree down now that we've removed the item
		if (removed) {
			count--;
			Shrink();
		}

		return removed;
	}


	void Shrink(){
		rootNode = rootNode.ShrinkIfPossible(initialSize);
	}

	public static boolean FuncCanCollide(ColliderProxy obj, ColliderProxy obj1) {
		return true;
	}

	public static void funcOnCollide(ColliderProxy obj, ColliderProxy obj1) {
	}

	/*public void UpdateObj(ColliderProxy obj, LRect bound){
		var node = GetNode(obj);
		if (node == null) {
			Add(obj, bound);
		}
		else {
			if (!node.ContainBound(bound)) {
				Remove(obj);
				Add(obj, bound);
			}
			else {
				node.UpdateObj(obj, bound);
			}
		}
	}*/

}
