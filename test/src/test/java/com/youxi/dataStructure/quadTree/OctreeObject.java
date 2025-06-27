package com.youxi.dataStructure.quadTree;

public class OctreeObject {
	public ColliderProxy Obj;
	public LRect Bounds;

	public OctreeObject(ColliderProxy obj, LRect bounds) {
		Obj = obj;
		Bounds = bounds;
	}
}
