package com.youxi.dataStructure.quadTree;

import com.example.demo1.util.vo.Vector2f;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2022/6/14 18:11
 * @Description:
 */
public class BoundsQuadTreeNode {

	// 父节点
	public BoundsQuadTreeNode parent;
	// 中心点
	public Vector2f center;
	// 基础长度
	public float baseLength;
	// 扩大倍数(听说2倍最好)
	/**
	 * 当出口范围是入口范围2倍的时候，松散四叉树会多一个神奇的特性：
	 * 如果一个AABB大小小于一个四叉树节点的入口范围，那么只要这个AABB的中心点在入口范围内，那么这个AABB一定包含在出口范围内。
	 */
	public float losseness;
	// 最小大小
	public float minSize;
	// baseLength * losseness
	public float adjLength;
	// 当前节点AABB大小;经过扩大的
	LRect bounds;
	// 节点数据
	List<OctreeObject> objects = new ArrayList<OctreeObject>();
	// 子节点
	/**
	 *    	  3	|  4
	 *    	----|-----
	 *	  	1	|  2
	 *
	 */
	BoundsQuadTreeNode[] children = null;


	// 子节点的aabb
	/**
	 *    	  3	|  4
	 *    	----|-----
	 *	  	1	|  2
	 *
	 */
	LRect[] childBounds;

	// 碰撞->节点 碰撞是否在四叉树里
	public static Map<ColliderProxy, BoundsQuadTreeNode> obj2Node = new HashMap<>();


	// 碰撞数量 >= 该数字以后可以扩容 <= 该数字就缩容;
	int NUM_OBJECTS_ALLOWED = 6;
	int NUM_CHILDREN = BoundsQuadTree.NUM_CHILDREN;


	/**
	 * 是否有子节点
	 */
	boolean HasChildren() {
		return children != null;
	}

	public BoundsQuadTreeNode(BoundsQuadTreeNode parent, float baseLengthVal, float minSizeVal,
							  float loosenessVal, Vector2f centerVal) {

		this.parent = parent;
		SetValues(baseLengthVal, minSizeVal, loosenessVal, centerVal);
	}

	public boolean Add(ColliderProxy obj, LRect objBounds) {
		// 是否完全包含其aabb
		if (!Encapsulates(bounds, objBounds)) {
			return false;
		}

		SubAdd(obj, objBounds);
		return true;
	}

	public boolean Remove(ColliderProxy obj) {
		//
		BoundsQuadTreeNode val = obj2Node.get(obj);
		if (val != null) {
			obj2Node.remove(obj);
			boolean removed = false;
			for (int i = 0; i < val.objects.size(); i++) {
				if (val.objects.get(i).Obj == obj) {
					val.objects.remove(i);
					removed = true;
					break;
				}
			}
			if (removed) {
				val.OnRemoved();
			}

			return removed;
		}

		return false;
	}

	public boolean ContainBound(LRect bound) {
		return Encapsulates(bounds, bound);
	}

	public void UpdateObj(ColliderProxy obj, LRect bound) {
		for (int i = 0; i < objects.size(); i++) {
			if (ReferenceEquals(objects.get(i).Obj, obj)) {
				objects.set(i, new OctreeObject(obj, bound));
			}
		}
	}

	public void OnRemoved() {
		if (ShouldMerge()) {
			Merge();
		}
		if (parent != null) {
			// 再调回到父类;父类也检测是否需要合并
			parent.OnRemoved();
		}
	}


	/**
	 * 检测是否包含这个 aabb, 包含的话再遍历4个子节点;判断是否包含;
	 */
	public boolean IsColliding(ColliderProxy obj, LRect checkBounds) {
		// Are the input bounds at least partially in this node?
		if (!bounds.Overlaps(checkBounds)) {
			return false;
		}

		// Check against any objects in this node
		for (int i = 0; i < objects.size(); i++) {
			OctreeObject o = objects.get(i);
			if (!ReferenceEquals(o.Obj, obj) && o.Bounds.Overlaps(checkBounds)) {
				return true;
			}
		}

		// Check children
		if (children != null) {
			for (int i = 0; i < NUM_CHILDREN; i++) {
				if (children[i].IsColliding(obj, checkBounds)) {
					return true;
				}
			}
		}

		return false;
	}



	public boolean CheckCollision(LRect checkBounds, FuncCollision callback) {
		// Are the input bounds at least partially in this node?
		if (!bounds.Overlaps(checkBounds)) {
			return false;
		}

		// Check against any objects in this node
		for (int i = 0; i < objects.size(); i++) {
			OctreeObject o = objects.get(i);
			if (o.Bounds.Overlaps(checkBounds)) {
				//callback(o.Obj);
			}
		}
		// Check children
		if (children != null) {
			for (int i = 0; i < NUM_CHILDREN; i++) {
				if (children[i].CheckCollision(checkBounds, callback)) {
					return true;
				}
			}
		}
		return false;
	}

	public void CheckCollision(ColliderProxy obj, LRect checkBounds) {
		// Are the input bounds at least partially in this node?
		if (!bounds.Overlaps(checkBounds)) {
			return;
		}

		// Check against any objects in this node
		for (int i = 0; i < objects.size(); i++) {
			OctreeObject o = objects.get(i);
			if (!ReferenceEquals(o.Obj, obj)
					&& BoundsQuadTree.FuncCanCollide(o.Obj, obj)
					&& o.Bounds.Overlaps(checkBounds)
			) {
				BoundsQuadTree.funcOnCollide(obj, o.Obj);
			}
		}

		// Check children
		if (children != null) {
			for (int i = 0; i < NUM_CHILDREN; i++) {
				children[i].CheckCollision(obj, checkBounds);
			}
		}
	}


	/**
	 * 不缩小就好了！！！
	 *
	 * 只有根节点调用
	 *
	 * ///我们可以缩小八叉树，如果:
	 * /// -该节点的长度为>= double minLength
	 * /// -根节点中的所有对象都在一个八分区内
	 * /// -该节点没有子节点，或者有子节点，但有7/8个子节点为空
	 * ///如果没有对象，我们也可以缩小它!
	 * > / / / < /总结
	 * /// <param name="minLength">该八叉树中节点的最小维数
	 * /// <返回>新的根，如果没有收缩，则返回现有的根
	 */
	public BoundsQuadTreeNode ShrinkIfPossible(float minLength){
		// 当前长度 < 初始长度的2倍 ？这个不是必定成立的吗
		if (baseLength < (2 * minLength)) {
			return this;
		}

		// 没有子节点  也没有碰撞
		if (objects.size() == 0 && (children == null || children.length == 0)) {
			return this;
		}

		// Check objects in root
		int bestFit = -1;
		for (int i = 0; i < objects.size(); i++) {
			OctreeObject curObj = objects.get(i);
			int newBestFit = BestFitChild(curObj.Bounds.getCenter());
			if (i == 0 || newBestFit == bestFit) {
				// In same octant as the other(s). Does it fit completely inside that octant?
				if (Encapsulates(childBounds[newBestFit], curObj.Bounds)) {
					if (bestFit < 0) {
						bestFit = newBestFit;
					}
				}
				else {
					// Nope, so we can't reduce. Otherwise we continue
					return this;
				}
			}
			else {
				return this; // Can't reduce - objects fit in different octants
			}
		}

		// Check objects in children if there are any
		if (children != null) {
			boolean childHadContent = false;
			for (int i = 0; i < children.length; i++) {
				if (children[i].HasAnyObjects()) {
					if (childHadContent) {
						return this; // Can't shrink - another child had content already
					}

					if (bestFit >= 0 && bestFit != i) {
						return this; // Can't reduce - objects in root are in a different octant to objects in child
					}

					childHadContent = true;
					bestFit = i;
				}
			}
		}

		// Can reduce
		if (children == null) {
			// We don't have any children, so just shrink this node to the new size
			// We already know that everything will still fit in it
			SetValues(baseLength / 2, minSize, losseness, childBounds[bestFit].getCenter());
			return this;
		}

		// No objects in entire octree
		if (bestFit == -1) {
			return this;
		}

		// We have children. Use the appropriate child as the new root node
		return children[bestFit];
	}


	public int BestFitChild(Vector2f objBoundsCenter){
		return (objBoundsCenter.x <= center.x ? 0 : 1) + (objBoundsCenter.y <= center.y ? 0 : 2);
	}
	public boolean HasAnyObjects(){
		if (objects.size() > 0) return true;

		if (children != null) {
			for (int i = 0; i < NUM_CHILDREN; i++) {
				if (children[i].HasAnyObjects()) return true;
			}
		}

		return false;
	}


	void SetValues(float baseLengthVal, float minSizeVal, float loosenessVal, Vector2f centerVal){
		baseLength = baseLengthVal;
		minSize = minSizeVal;
		losseness = loosenessVal;
		center = centerVal;
		adjLength = losseness * baseLengthVal;

		// Create the bounding box.
		Vector2f size = new Vector2f(adjLength, adjLength);
		bounds = CreateLRect(center, size);

		float quarter = baseLength / 4;
		float childActualLength = (baseLength / 2) * losseness;
		Vector2f childActualSize = new Vector2f(childActualLength, childActualLength);
		childBounds = new LRect[NUM_CHILDREN];
		childBounds[0] = CreateLRect(center.add(new Vector2f(-quarter, -quarter)), childActualSize);
		childBounds[1] = CreateLRect(center.add(new Vector2f(quarter, -quarter)), childActualSize);
		childBounds[2] = CreateLRect(center.add(new Vector2f(-quarter, quarter)), childActualSize);
		childBounds[3] = CreateLRect(center.add(new Vector2f(quarter, quarter)), childActualSize);
	}

	LRect CreateLRect(Vector2f center, Vector2f size) {
		return new LRect(center.sub(size.multiply(0.5f)), size);
	}

	void SubAdd(ColliderProxy obj, LRect objBounds){
		// We know it fits at this level if we've got this far

		// We always put things in the deepest possible child
		// So we can skip some checks if there are children aleady
		// 如果没有子节点
		if (!HasChildren()) {
			// Just add if few objects are here, or children would be below min size
			// 如果不能扩容 就直接添加在当前节点
			if (objects.size() < NUM_OBJECTS_ALLOWED || (baseLength / 2) < minSize) {
				OctreeObject newObj = new OctreeObject(obj, objBounds);
				objects.add(newObj);

				obj2Node.put(obj, this);
				return; // We're done. No children yet
			}

			// Fits at this level, but we can go deeper. Would it fit there?
			// Create the 8 children
			int bestFitChild;
			if (children == null) {
				// 分割
				Split();
				if (children == null) {
					return;
				}

				// 创建子节点成功;重新分配父类的这些碰撞
				// Now that we have the new children, see if this node's existing objects would fit there
				for (int i = objects.size() - 1; i >= 0; i--) {
					OctreeObject existingObj = objects.get(i);
					// Find which child the object is closest to based on where the
					// object's center is located in relation to the octree's center
					bestFitChild = BestFitChild(existingObj.Bounds.getCenter());
					// Does it fit?
					if (Encapsulates(children[bestFitChild].bounds, existingObj.Bounds)) {
						children[bestFitChild]
								.SubAdd(existingObj.Obj, existingObj.Bounds); // Go a level deeper
						objects.remove(existingObj); // Remove from here
					}
				}
			}
		}

		// Handle the new object we're adding now
		int bestFit = BestFitChild(objBounds.getCenter());
		if (Encapsulates(children[bestFit].bounds, objBounds)) {
			children[bestFit].SubAdd(obj, objBounds);
		}
		else {
			// Didn't fit in a child. We'll have to it to this node instead
			OctreeObject newObj = new OctreeObject(obj,objBounds);
			objects.add(newObj);
			obj2Node.put(obj,this);
		}
	}

	void Split(){
		// 四分之一
		float quarter = baseLength / 4;
		float newLength = baseLength / 2;
		children = new BoundsQuadTreeNode[NUM_CHILDREN];
		children[0] = new BoundsQuadTreeNode(this, newLength, minSize, losseness,
				center.add(new Vector2f(-quarter, -quarter)));
		children[1] = new BoundsQuadTreeNode(this, newLength, minSize, losseness,
				center.add(new Vector2f(quarter, -quarter)));
		children[2] = new BoundsQuadTreeNode(this, newLength, minSize, losseness,
				center.add(new Vector2f(-quarter, quarter)));
		children[3] = new BoundsQuadTreeNode(this, newLength, minSize, losseness,
				center.add(new Vector2f(quarter, quarter)));
	}

	void Merge(){
		// Note: We know children != null or we wouldn't be merging
		if (children == null) return;
		for (int i = 0; i < NUM_CHILDREN; i++) {
			BoundsQuadTreeNode curChild = children[i];
			int numObjects = curChild.objects.size();
			for (int j = numObjects - 1; j >= 0; j--) {
				OctreeObject curObj = curChild.objects.get(j);
				objects.add(curObj);
				obj2Node.put(curObj.Obj, this);
			}
		}

		// Remove the child nodes (and the objects in them - they've been added elsewhere now)
		children = null;
	}

	/**
	 * 其子节点没有子节点 并且 自己和子节点的碰撞数量小于  NUM_OBJECTS_ALLOWED 个
	 */
	boolean ShouldMerge(){
		int totalObjects = objects.size();
		if (children != null) {
			for (BoundsQuadTreeNode child : children) {
				// 如果子节点里面还有子节点就不合并
				if (child.children != null) {
					// If any of the *children* have children, there are definitely too many to merge,
					// or the child woudl have been merged already
					return false;
				}

				totalObjects += child.objects.size();
			}
		}

		return totalObjects <= NUM_OBJECTS_ALLOWED;
	}

	private boolean ReferenceEquals(ColliderProxy obj, ColliderProxy obj1) {
		return obj == obj1;
	}


	static boolean Encapsulates(LRect outerBounds, LRect innerBounds) {
		return outerBounds.Contains(innerBounds.min()) && outerBounds.Contains(innerBounds.max());
	}


}
