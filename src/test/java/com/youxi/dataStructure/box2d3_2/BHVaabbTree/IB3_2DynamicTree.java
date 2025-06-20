package com.youxi.dataStructure.box2d3_2.BHVaabbTree;

import com.youxi.dataStructure.aabbTree.bean.B2RayCastInput;
import com.youxi.dataStructure.aabbTree.bean.QueryCallback;
import com.youxi.dataStructure.aabbTree.bean.RayCastCallback;
import com.youxi.math.box2dGJK.shape.B2AABB;
import com.youxi.math.box2dGJK.shape.B2Vec2;

/**
 * https://blog.csdn.net/cg0206/article/details/8293049
 * https://www.cnblogs.com/duzib/p/4590900.html
 */
public interface IB3_2DynamicTree {

    //根据代理id获取userData
    public Object b2DynamicTree_GetUserData(int proxyId);


    //根据代理id获得宽大的AABB
   public B2AABB b2DynamicTree_GetAABB(int proxyId);

    //查询一个aabb重叠代理，每个重叠提供AABB的代理都将回调回调类
    public void query(QueryCallback callback, B2AABB aabb);
    //光线投射
    public void rayCast(RayCastCallback callback, B2RayCastInput input);



    /**************************************************************************
     * 功能描述：在树上创建一个叶子节点代理
     * 参数说明：aabb    ：aabb变量
     user    : 数据
     * 返 回 值：节点的索引来替代指针,来增长我们的节点池
     ***************************************************************************/
    public int createProxy(B2AABB aabb, Object userData);

    /**************************************************************************
     * 功能描述：销毁叶子代理
     * 参数说明：proxyId     ：叶子代理id
     * 返 回 值：(void)
     ***************************************************************************/
    public void destroyProxy(int proxyId);
    /**************************************************************************
     * 功能描述：移动叶子代理
     * 参数说明：proxyId     ：叶子代理id
     aabb        : aabb变量 移动后aabb所在的位置
     displacement：移动坐标向量 当次移动的方向和距离
     * 返 回 值： (void)
     ***************************************************************************/
    public boolean moveProxy(int proxyId, B2AABB aabb, B2Vec2 displacement);


    /**************************************************************************
     * 功能描述：验证这棵树，用于测试
     * 参数说明：(void)
     * 返 回 值：(void)
     ***************************************************************************/
    public void Validate();

    /**************************************************************************
     * 功能描述：构建一个最优的树，非常昂贵，用于测试
     * 参数说明：(void)
     * 返 回 值：(void)
     ***************************************************************************/
    public void rebuildBottomUp();


    public void printTree();
    public String printTreeToString();
}
