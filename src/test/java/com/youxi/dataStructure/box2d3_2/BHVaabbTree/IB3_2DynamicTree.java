package com.youxi.dataStructure.box2d3_2.BHVaabbTree;

import com.youxi.dataStructure.box2d3_2.entity.b2TreeStats;
import com.youxi.math.box2dGJK.shape.B2AABB;

/**
 *
 */
public interface IB3_2DynamicTree {

    //根据代理id获取userData
    public Object b2DynamicTree_GetUserData(int proxyId);


    //根据代理id获得宽大的AABB
    public B2AABB b2DynamicTree_GetAABB(int proxyId);

    //查询一个aabb重叠代理，每个重叠提供AABB的代理都将回调回调类
    public b2TreeStats b2DynamicTree_Query(B2AABB aabb, long maskBits, B2TreeQueryCallbackFcn callback, Object context);

    //光线投射
    b2TreeStats b2DynamicTree_RayCast(b2RayCastInput input, long maskBits,
                                      B2TreeRayCastCallbackFcn callback, Object context);


    /**************************************************************************
     * 功能描述：在树上创建一个叶子节点代理
     * 参数说明：aabb    ：aabb变量
     user    : 数据
     * 返 回 值：节点的索引来替代指针,来增长我们的节点池
     ***************************************************************************/
    public int b2DynamicTree_CreateProxy(B2AABB aabb, long categoryBits, Object userData);

    /**************************************************************************
     * 功能描述：销毁叶子代理
     * 参数说明：proxyId     ：叶子代理id
     * 返 回 值：(void)
     ***************************************************************************/
    public void b2DynamicTree_DestroyProxy(int proxyId);

    /**************************************************************************
     * 功能描述：移动叶子代理
     * 参数说明：proxyId     ：叶子代理id
     aabb        : aabb变量 移动后aabb所在的位置
     displacement：移动坐标向量 当次移动的方向和距离
     * 返 回 值： (void)
     ***************************************************************************/
    public void b2DynamicTree_MoveProxy(int proxyId, B2AABB aabb);

    /**
     * 增大aabb
     * <p>
     * 移动时调用该方法 和Box2d移动时候调用moveproxy不一样;
     * box2d3.2是移动时调用该方法,然后统一定时重建移动的Proxy
     */
    void b2DynamicTree_EnlargeProxy(int proxyId, B2AABB aabb);

    /**************************************************************************
     * 功能描述：验证这棵树，用于测试
     * 参数说明：(void)
     * 返 回 值：(void)
     ***************************************************************************/
    public void b2DynamicTree_Validate();

    public int b2DynamicTree_Rebuild(boolean fullBuild);

    public int b2DynamicTree_GetHeight();

    public int calculateMinHeight();

    public void printTree();

    public String printTreeToString();
}
