package com.youxi.dataStructure.aabbTree.zeroGC;


import com.youxi.dataStructure.aabbTree.bean.B2AABB;

public class B2TreeNode {
    public final static int b2_nullNode = -1;

    /// Enlarged AABB
    //增大aabb变量
    B2AABB aabb = new B2AABB();
    //用户数据
    Object userData;
    //父节点指针(索引)或孩子节点指针(索引)
    //因为此动态树是申请一块大的连续的空间，即含有n个元素的动态数组罢了
    //故用动态数组的索引值来模拟指针。为了统一，我们一下均使用指针

    int parent;
    int next;

    //左右孩子指针
    int child1;
    int child2;
    //高度 叶子高度为0，空闲节点高度为-1
    int height;

    //是否是叶子节点
   public boolean IsLeaf() {
        return child1 == b2_nullNode;
    }

}
