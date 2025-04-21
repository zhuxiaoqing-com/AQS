package com.youxi.dataStructure.aabbTree;

import com.youxi.dataStructure.aabbTree.bean.B2Collision;
import com.youxi.dataStructure.aabbTree.bean.B2RayCastInput;
import com.youxi.dataStructure.aabbTree.bean.QueryCallback;
import com.youxi.dataStructure.aabbTree.bean.RayCastCallback;
import com.youxi.math.box2dGJK.B2Math;
import com.youxi.math.box2dGJK.shape.B2Vec2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class B2DynamicTree {
    //树的根指针(也是索引)
    int m_root;
    //树的真正的头指针，也是一块连续的内存池的首地址
    //B2TreeNode m_nodes;
    List<B2TreeNode> m_nodes = new ArrayList<>();
    //树节点的个数
    int m_nodeCount;
    //内存池中节点的总个数
    int m_nodeCapacity;
    //空闲链表指针
    int m_freeList;
    //  用于增量遍历树的调整
    int m_path;
    //记录插入节点总数量
    int m_insertionCount;

    private boolean checkProxy(int proxyId) {
        return 0 <= proxyId && proxyId < m_nodeCapacity;
    }

    //根据代理id获取userData
    public Object getUserData(int proxyId) {
        if (!checkProxy(proxyId)) {
            return null;
        }
        return m_nodes.get(proxyId).userData;
    }


    //根据代理id获得宽大的AABB
    B2AABB getFatAABB(int proxyId) {
        //验证代理id的有效性
        if (!checkProxy(proxyId)) {
            return null;
        }
        return m_nodes.get(proxyId).aabb;
    }

    //查询一个aabb重叠代理，每个重叠提供AABB的代理都将回调回调类
    public void query(QueryCallback callback, B2AABB aabb) {
        //申请临时栈，根节点进栈
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.addLast(m_root);
        //判断栈的个数
        while (stack.size() > 0) {
            //获取节点id
            int nodeId = stack.poll();
            if (nodeId == B2TreeNode.b2_nullNode) {
                //节点内存池中的空闲节点
                continue;
            }
            //获取节点
            B2TreeNode node = m_nodes.get(nodeId);
            //测试重叠
            if (B2Collision.b2TestOverlap(node.aabb, aabb)) {
                //是否是叶子节点
                if (node.IsLeaf()) {
                    //是否成功
                    boolean proceed = callback.queryCallback(nodeId);
                    if (proceed == false) {
                        return;
                    }
                } else {
                    //左右孩子节点进栈
                    stack.addLast(node.child1);
                    stack.addLast(node.child2);
                }
            }
        }
    }

    //光线投射
    public void rayCast(RayCastCallback callback, B2RayCastInput input) {
        B2Vec2 p1 = input.p1;
        B2Vec2 p2 = input.p2;
        B2Vec2 r = p2.sub(p1);
        // 丢失射线方向 两个点一样 射线没有方向
        if (r.LengthSquared() <= 0) {
            return;
        }
        r.Normalize();
        //v垂直于向量r
        B2Vec2 v = B2Math.b2Cross(1.0f, r);
        B2Vec2 abs_v = B2Math.b2Abs(v);
        //分离轴 下面的书籍 p80
        // 《Collision Detection in Interactive 3D Environments》 by Gino van den Bergen
        // 【从 http://download.csdn.net/detail/cg0206/4875309 下载】
        // 计算公式：|dot(v, p1 - c)| > dot(|v|, h)
        float maxFraction = input.maxFraction;
        // 构建一个绑定的盒子用于该线段
        B2AABB segmentAABB = new B2AABB();
        //B2Vec2 t = p1 + maxFraction * (p2 - p1);
        // 获取射线终点
        B2Vec2 t = p2.sub(p1).multiply(maxFraction).add(p1);
        // 获取射线的aabb盒子 将射线封装成一个aabb盒子
        segmentAABB.lowerBound = B2Math.b2Min(p1, t);
        segmentAABB.upperBound = B2Math.b2Max(p1, t);
        //创建一个临时栈，并将根节点进栈
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.addLast(m_root);
        //栈不为空
        while (stack.size() > 0) {
            //出栈
            int nodeId = stack.poll();
            if (nodeId == B2TreeNode.b2_nullNode) {
                //节点内存池中的空闲节点
                continue;
            }
            //获取节点
            B2TreeNode node = m_nodes.get(nodeId);
            //判断AABB
            if (B2Collision.b2TestOverlap(node.aabb, segmentAABB) == false) {
                continue;
            }

            //分离轴  p80
            // |dot(v, p1 - c)| > dot(|v|, h)
            B2Vec2 c = node.aabb.GetCenter();
            B2Vec2 h = node.aabb.GetExtents();

            float separation = Math.abs(B2Math.b2Dot(v, p1.sub(c))) - B2Math.b2Dot(abs_v, h);
            if (separation > 0.0f) {
                continue;
            }
            //是否是叶子节点
            if (node -> IsLeaf()) {
                b2RayCastInput subInput;
                subInput.p1 = input.p1;
                subInput.p2 = input.p2;
                subInput.maxFraction = maxFraction;

                float32 value = callback -> RayCastCallback(subInput, nodeId);

                if (value == 0.0f) {
                    //客户端终止了光线投射
                    return;
                }

                if (value > 0.0f) {
                    // 更新线段的盒子边界
                    maxFraction = value;
                    b2Vec2 t = p1 + maxFraction * (p2 - p1);
                    segmentAABB.lowerBound = b2Min(p1, t);
                    segmentAABB.upperBound = b2Max(p1, t);
                }
            } else {
                //将两个孩子进栈
                stack.Push(node -> child1);
                stack.Push(node -> child2);
            }
        }
    }
}
