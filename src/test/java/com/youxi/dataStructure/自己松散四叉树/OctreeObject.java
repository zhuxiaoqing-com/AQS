package com.youxi.dataStructure.自己松散四叉树;


public class OctreeObject {
	public ColliderProxy Obj;
	public MyLRect Bounds;

	public OctreeObject(ColliderProxy obj, MyLRect bounds) {
		Obj = obj;
		Bounds = bounds;
	}
}
