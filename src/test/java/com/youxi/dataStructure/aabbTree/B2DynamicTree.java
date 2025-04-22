package com.youxi.dataStructure.aabbTree;

import com.youxi.dataStructure.aabbTree.bean.B2Collision;
import com.youxi.dataStructure.aabbTree.bean.B2RayCastInput;
import com.youxi.dataStructure.aabbTree.bean.QueryCallback;
import com.youxi.dataStructure.aabbTree.bean.RayCastCallback;
import com.youxi.entity.MapHelper;
import com.youxi.math.box2dGJK.B2Math;
import com.youxi.math.box2dGJK.shape.B2Vec2;
import io.swagger.models.auth.In;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

import static com.youxi.dataStructure.aabbTree.B2TreeNode.b2_nullNode;

/**
 * https://blog.csdn.net/cg0206/article/details/8293049
 * https://www.cnblogs.com/duzib/p/4590900.html
 */
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
            if (nodeId == b2_nullNode) {
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
                    stack.addFirst(node.child1);
                    stack.addFirst(node.child2);
                }
            }
        }
    }

    /**
     * // |dot(v, p1 - c)| > dot(|v|, h)
     * float separation = Math.abs(B2Math.b2Dot(v, p1.sub(c))) - B2Math.b2Dot(abs_v, h);
     * <p>
     * 为什么这样就能检测射线和AABB相交？
     * <p>
     * 首先理论上来说 射线和AABB相交使用轴分离算法 需要计算 x轴法线  y轴法线 和射线的法线.
     * 1.AABB的x轴和y轴（隐式通过AABB重叠测试完成）。
     * 2.射线防线的垂直轴 代码中的v 还没测试完成
     * <p>
     * 那正常应该取出射线在法线上的投影线段 和AABB在法线上的投影长度。
     * 首先我们取出射线在法线上的投影线段,因为该法线是射线的法线(也就是垂直于射线)，那射线的线段就只有一个点 就是起点.
     * 然后就是AABB在法线上的投影长度。
     * 这里采用了取AABB在法线上的投影半长.
     * 这里采用了其他的方式：
     * - **射线起点`p1`在`v`方向上的投影位置**：即`b2Dot(v, p1 - c)`，再取绝对值，表示p1距离中心`c`在`v`方向上的绝对距离。(也就是p1和中心点c的投影距离)
     * <p>
     * **AABB在`v`方向上的投影范围**：即`b2Dot(abs_v, h)`。这里`abs_v`的每个分量是`v`的绝对值，`h`是AABB的半长。
     * 点积的结果实际上是将AABB在`v`方向上的各个分量进行投影后的总和，也就是AABB在`v`方向上的投影半长。
     * <p>
     * 那其实只要p1点距离中心点c的abs距离小于 aabb在v方向上的投影半长，就说明 p点的投影点 在aabb的投影线段里面.
     * <p>
     * 本质也是 射线投影线段 和aabb投影线段相比较. 只是射线的投影线段只是一个点，所以只需要对比射线的点是否在aabb的投影线段里面就行了，
     * 上面通过取出射线的投影点和aabb中心点的投影距离和 aabb的投影线段的长度, aabb的投影线段的长度一半就是 aabb投影线段距离aabb中心点的投影点的距离.
     * 如果射线投影点和aabb中心点投影点的距离 比 aabb投影线段长度的一半小 就说明 在该法线上射线和aabb相交
     * <p>
     * 所以，`separation`实际上是射线起点在`v`方向上与AABB中心的最大可能距离（考虑绝对值）减去AABB在该方向上的投影范围。
     * 如果这个值大于0，说明射线起点在该方向上超出了AABB的投影范围，因此两者在`v`方向上分离，进而推断出射线与AABB不相交。
     * <p>
     * <p>
     * 为何 dot(|v|, h) =投影半长 是正确的？
     * <p>
     * 具体可以看投影半长.png推导
     */
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
            if (nodeId == b2_nullNode) {
                //节点内存池中的空闲节点
                continue;
            }
            //获取节点
            B2TreeNode node = m_nodes.get(nodeId);
            //判断AABB
            if (B2Collision.b2TestOverlap(node.aabb, segmentAABB) == false) {
                continue;
            }

            // 前面的AABB重叠已经检查了 AABB的x的法线轴 和y的法线轴， 就差射线的法线值了 这里用SAT检测射线的法线值
            //分离轴  p80
            // |dot(v, p1 - c)| > dot(|v|, h)
            B2Vec2 c = node.aabb.GetCenter();
            B2Vec2 h = node.aabb.GetExtents();

            /**
             * 这里正常来说 应该是要将AABB的4个点都投影上去 然后看 射线的起点在不在里面.
             */
            float separation = Math.abs(B2Math.b2Dot(v, p1.sub(c))) - B2Math.b2Dot(abs_v, h);
            if (separation > 0.0f) {
                continue;
            }
            //是否是叶子节点
            if (node.IsLeaf()) {
                B2RayCastInput subInput = new B2RayCastInput();
                subInput.p1 = input.p1;
                subInput.p2 = input.p2;
                subInput.maxFraction = maxFraction;

                float value = callback.rayCastCallback(subInput, nodeId);

                if (value == 0.0f) {
                    //客户端终止了光线投射
                    return;
                }

                /**
                 * 这里根据源码里的使用 应该是如果击中了该目标的话 就将射线的击中点t返回回来。
                 * 没有击中的话 就还将B2RayCastInput.maxFraction返回;
                 * 这里取t作为射线新的maxFraction 是因为射线本质上是要找一个打中的最近的形状，
                 * 既然已经打中该形状了 那么更远的已经被遮挡住了 打不中了,更新maxFraction会让射线和aabb的粗筛阶段 筛除掉更多的aabb。
                 */
                if (value > 0.0f) {
                    // 更新线段的盒子边界
                    maxFraction = value;
                    //B2Vec2 t = p1 + maxFraction * (p2 - p1);
                    B2Vec2 t1 = p2.sub(p1).multiply(maxFraction).add(p1);
                    segmentAABB.lowerBound = B2Math.b2Min(p1, t1);
                    segmentAABB.upperBound = B2Math.b2Max(p1, t1);
                }
            } else {
                //将两个孩子进栈
                stack.addFirst(node.child1);
                stack.addFirst(node.child2);
            }
        }
    }


    public void init() {
        m_root = b2_nullNode;

        m_nodeCapacity = 16;
        m_nodeCount = 0;
        //申请一块内存,创建m_nodeCapacity子节点,并清空内存中的内容
        m_nodes = new ArrayList<>(m_nodeCapacity);
        for (int i = 0; i < m_nodeCapacity; i++) {
            m_nodes.add(new B2TreeNode());
        }
        // 创建一个空闲链表
        for (int i = 0; i < m_nodeCapacity - 1; ++i) {
            m_nodes.get(i).next = i + 1;
            m_nodes.get(i).height = -1;
        }
        //链表的最后一个子节点的孩子指针、高度都置为初始值
        m_nodes.get(m_nodeCapacity - 1).next = b2_nullNode;
        m_nodes.get(m_nodeCapacity - 1).height = -1;
        m_freeList = 0;

        m_path = 0;

        m_insertionCount = 0;
    }

    /**************************************************************************
     * 功能描述：从内存池中申请一个节点.如果必要增大内存池
     * 参数说明：(void)
     * 返 回 值：节点指针
     ***************************************************************************/
    public int allocateNode() {
// 如果需要扩大节点内存池
        if (m_freeList == b2_nullNode) {
            //空闲链表为空，重新创建一个更大的内存池
            List<B2TreeNode> oldNodes = m_nodes;
            m_nodeCapacity *= 2;
            m_nodes = new ArrayList<>(m_nodeCapacity);
            for (int i = m_nodeCount; i < m_nodeCapacity; i++) {
                m_nodes.add(new B2TreeNode());
            }
            // 创建一个空闲链表。父节点成为下一个指针
            // 注意:这次是从m_nodeCount开始的
            for (int i = m_nodeCount; i < m_nodeCapacity - 1; ++i) {
                m_nodes.get(i).next = i + 1;
                m_nodes.get(i).height = -1;
            }

            m_nodes.get(m_nodeCapacity - 1).next = b2_nullNode;
            m_nodes.get(m_nodeCapacity - 1).height = -1;
            m_freeList = m_nodeCount;
        }
        //从空闲链表中去下一个节点，初始化该节点，
        //同时将空闲链表头指针m_freeList指向下一个
        int nodeId = m_freeList;
        m_freeList = m_nodes.get(nodeId).next;
        m_nodes.get(nodeId).parent = b2_nullNode;
        m_nodes.get(nodeId).child1 = b2_nullNode;
        m_nodes.get(nodeId).child2 = b2_nullNode;
        m_nodes.get(nodeId).height = 0;
        m_nodes.get(nodeId).userData = null;
        //增加节点数量
        ++m_nodeCount;
        //返回节点id
        return nodeId;
    }

    //

    /**************************************************************************
     * 功能描述：从内存池中申请一个节点.根据nodeid将一个节点内存返回到节点池内
     * 参数说明：nodeId：节点指针
     * 返 回 值：(void)
     ***************************************************************************/
    void freeNode(int nodeId) {
        //验证nodeid的有效性
        if (!checkProxy(nodeId)) {
            return;
        }
        if (m_nodeCount <= 0) {
            return;
        }
        //将当前节点以头插入的方式放到空闲链表中
        m_nodes.get(nodeId).next = m_freeList;
        m_nodes.get(nodeId).height = -1;
        m_freeList = nodeId;
        //将节点个数减1
        //注意此处并没有释放
        --m_nodeCount;
    }

    /**************************************************************************
     * 功能描述：在树上创建一个叶子节点代理
     * 参数说明：aabb    ：aabb变量
     user    : 数据
     * 返 回 值：节点的索引来替代指针,来增长我们的节点池
     ***************************************************************************/
    int createProxy(B2AABB aabb, Object userData) {
        //申请代理节点id
        int proxyId = allocateNode();

        // Fatten the aabb.
        //填充aabb，为节点赋值
        B2Vec2 r = new B2Vec2(0.1f, 0.1f);
        m_nodes.get(proxyId).aabb.lowerBound = aabb.lowerBound.sub(r);
        m_nodes.get(proxyId).aabb.upperBound = aabb.upperBound.add(r);
        m_nodes.get(proxyId).userData = userData;
        m_nodes.get(proxyId).height = 0;
        //插入叶子节点
        insertLeaf(proxyId);

        return proxyId;
    }

    /**************************************************************************
     * 功能描述：销毁叶子代理
     * 参数说明：proxyId     ：叶子代理id
     * 返 回 值：(void)
     ***************************************************************************/
    void DestroyProxy(int proxyId) {
        //验证proxyid的有效性
        if (!checkProxy(proxyId)) {
            return;
        }
        //验证是否是叶子节点
        if (!m_nodes.get(proxyId).IsLeaf()) {
            return;
        }
        //删除叶子节点
        removeLeaf(proxyId);
        //释放子节点
        freeNode(proxyId);
    }

    /**************************************************************************
     * 功能描述：移动叶子代理
     * 参数说明：proxyId     ：叶子代理id
     aabb        : aabb变量
     displacement：移动坐标向量
     * 返 回 值： (void)
     ***************************************************************************/
    boolean moveProxy(int proxyId, B2AABB aabb, B2Vec2 displacement) {
        //验证proxyid的有效性
        if (!checkProxy(proxyId)) {
            return false;
        }
        //验证是否是叶子节点
        if (!m_nodes.get(proxyId).IsLeaf()) {
            return false;
        }
        //检验aabb是否互相包含
        if (m_nodes.get(proxyId).aabb.Contains(aabb)) {
            return false;
        }
        //根据proxyId移除叶子
        removeLeaf(proxyId);
        //扩大AABB
        B2AABB b = aabb;
        B2Vec2 r = new B2Vec2(0.1f, 0.1f);
        b.lowerBound = b.lowerBound.sub(r);
        b.upperBound = b.upperBound.add(r);
        //预测aabb的位移
        B2Vec2 d = displacement.multiply(4);
        // 看往哪边移动 来判断加哪边的边界
        //扩大下限
        if (d.x < 0.0f) {
            b.lowerBound.x += d.x;
        } else {
            b.upperBound.x += d.x;
        }

        if (d.y < 0.0f) {
            b.lowerBound.y += d.y;
        } else {
            b.upperBound.y += d.y;
        }
        //重新设置aabb
        m_nodes.get(proxyId).aabb = b;
        //插入叶子节点
        insertLeaf(proxyId);
        return true;
    }

    /**************************************************************************
     * 功能描述：插入叶子节点
     * 参数说明：leaf    ：叶子节点指针
     * 返 回 值： (void)
     * https://www.cnblogs.com/duzib/p/4590900.html
     ***************************************************************************/
    void insertLeaf(int leaf) {
        //插入节点总数量自增
        ++m_insertionCount;
        //判断该树是否为空
        if (m_root == b2_nullNode) {
            m_root = leaf;
            m_nodes.get(m_root).parent = b2_nullNode;
            return;
        }
        //为该节点找到最好的兄弟（姐妹）节点
        //获取leaf的aabb
        B2AABB leafAABB = m_nodes.get(leaf).aabb;
        //获取根节点
        int index = m_root;
        //不是叶子节点
        while (m_nodes.get(index).IsLeaf() == false) {
            int child1 = m_nodes.get(index).child1;
            int child2 = m_nodes.get(index).child2;
            //获取aabb的周长
            float area = m_nodes.get(index).aabb.GetPerimeter();
            //获取
            B2AABB combinedAABB = new B2AABB();
            combinedAABB.Combine(m_nodes.get(index).aabb, leafAABB);
            float combinedArea = combinedAABB.GetPerimeter();
            //为该节点创建一个新的父节点【一个新叶子节点】
            /**
             * 合并AABB的成本加权（cost = 2.0 * combinedArea）
             * 平衡树的层级：直接插入到当前节点需要创建一个新的父节点，而该父节点的AABB会包含新叶子和原节点的AABB。
             * 乘以2.0可能是为了体现“创建新父节点”的额外开销，鼓励算法优先选择更低层级的插入（如子节点），从而避免树的高度过快增长。
             *
             * 经验性调优：通过实验发现，2.0的系数能更准确地反映插入操作对树结构的实际影响，避免生成过于松散的AABB。
             */
            float cost = 2.0f * combinedArea;
            // 最小cost推动叶子进一步下降的树的下一层
            /**
             * 2. 继承成本（inheritanceCost = 2.0 * (combinedArea - area)）
             * 当新叶子被下推到子节点时，父节点的AABB会扩展（combinedArea - area为扩展量）。乘以2.0的原因：
             * 多层影响预估：父节点AABB的扩展可能进一步导致其父节点的AABB扩展（连锁反应）。2.0的系数近似估计了这种向上传递的成本，避免低估高层节点的调整代价。
             * 路径选择优化：较高的继承成本会促使算法优先选择对父节点影响更小的路径，从而减少整体AABB的膨胀。
             */
            /**
             * 核心目标
             * 最小化AABB的总体积：通过加权成本计算，算法倾向于选择使合并后AABB增长最小的路径。
             * 平衡树的深度：避免树过深导致查询效率下降，同时避免过浅导致节点AABB过于松散。
             */
            float inheritanceCost = 2.0f * (combinedArea - area);
            //降低cost到child1
            float cost1 = 0;
            //左孩子是叶子节点
            if (m_nodes.get(child1).IsLeaf()) {
                //获取cost1
                B2AABB aabb = new B2AABB();
                aabb.Combine(leafAABB, m_nodes.get(child1).aabb);
                cost1 = aabb.GetPerimeter() + inheritanceCost;
            } else {
                //获取cost1
                B2AABB aabb = new B2AABB();
                aabb.Combine(leafAABB, m_nodes.get(child1).aabb);
                float oldArea = m_nodes.get(child1).aabb.GetPerimeter();
                float newArea = aabb.GetPerimeter();
                cost1 = (newArea - oldArea) + inheritanceCost;
            }
            //降低cost到child2
            float cost2;
            if (m_nodes.get(child2).IsLeaf()) {
                //获取cost2
                B2AABB aabb = new B2AABB();
                aabb.Combine(leafAABB, m_nodes.get(child2).aabb);
                cost2 = aabb.GetPerimeter() + inheritanceCost;
            } else {
                //获取cost2
                B2AABB aabb = new B2AABB();
                aabb.Combine(leafAABB, m_nodes.get(child2).aabb);
                float oldArea = m_nodes.get(child2).aabb.GetPerimeter();
                float newArea = aabb.GetPerimeter();
                cost2 = newArea - oldArea + inheritanceCost;
            }
            /**
             * 找到一个相对于子节点 自己节点的成本最小
             */
            // 获取最小成本
            if (cost < cost1 && cost < cost2) {
                break;
            }
            //下降到最小cost
            if (cost1 < cost2) {
                index = child1;
            } else {
                index = child2;
            }
        }
        int sibling = index;
        //创建一个新的父节点
        //初始化
        int oldParent = m_nodes.get(sibling).parent;
        int newParent = allocateNode();
        m_nodes.get(newParent).parent = oldParent;
        m_nodes.get(newParent).userData = null;
        m_nodes.get(newParent).aabb.Combine(leafAABB, m_nodes.get(sibling).aabb);
        m_nodes.get(newParent).height = m_nodes.get(sibling).height + 1;

        if (oldParent != b2_nullNode) {
            // 兄弟节点不是根节点
            if (m_nodes.get(oldParent).child1 == sibling) {
                m_nodes.get(oldParent).child1 = newParent;
            } else {
                m_nodes.get(oldParent).child2 = newParent;
            }

            m_nodes.get(newParent).child1 = sibling;
            m_nodes.get(newParent).child2 = leaf;
            m_nodes.get(sibling).parent = newParent;
            m_nodes.get(leaf).parent = newParent;
        } else {
            // 兄弟节点是根节点
            m_nodes.get(newParent).child1 = sibling;
            m_nodes.get(newParent).child2 = leaf;
            m_nodes.get(sibling).parent = newParent;
            m_nodes.get(leaf).parent = newParent;
            m_root = newParent;
        }
        // 将父节点的aabb重新修复
        // 向后走修复树的高度和aabb
        index = m_nodes.get(leaf).parent;
        while (index != b2_nullNode) {
            //平衡
            index = balance(index);
            //左右孩子节点
            int child1 = m_nodes.get(index).child1;
            int child2 = m_nodes.get(index).child2;

            //b2Assert(child1 != b2_nullNode);
            //b2Assert(child2 != b2_nullNode);
            if (child1 == b2_nullNode) {
                return;
            }
            if (child2 == b2_nullNode) {
                return;
            }
            //获取高度和aabb
            m_nodes.get(index).height = 1 + Math.max(m_nodes.get(child1).height, m_nodes.get(child2).height);
            m_nodes.get(index).aabb.Combine(m_nodes.get(child1).aabb, m_nodes.get(child2).aabb);
            //获取parent节点
            index = m_nodes.get(index).parent;
        }

        //Validate();
    }

    /**************************************************************************
     * 功能描述：删除叶子节点
     * 参数说明：leaf    ：叶子节点指针
     * 返 回 值： (void)
     ***************************************************************************/
    void removeLeaf(int leaf) {
        //只有一个节点
        if (leaf == m_root) {
            m_root = b2_nullNode;
            return;
        }
        //获取父节点和祖父节点
        int parent = m_nodes.get(leaf).parent;
        int grandParent = m_nodes.get(parent).parent;
        //选找兄弟节点
        int sibling;
        if (m_nodes.get(parent).child1 == leaf) {
            sibling = m_nodes.get(parent).child2;
        } else {
            sibling = m_nodes.get(parent).child1;
        }
        // 祖父节点不为空，即父节点不是根节点
        if (grandParent != b2_nullNode) {
            // Destroy parent and connect sibling to grandParent.
            // 销毁父节点和将兄弟节点链接到祖父节点中
            if (m_nodes.get(grandParent).child1 == parent) {
                m_nodes.get(grandParent).child1 = sibling;
            } else {
                m_nodes.get(grandParent).child2 = sibling;
            }
            m_nodes.get(sibling).parent = grandParent;
            //释放节点到内存池中
            freeNode(parent);
            // 调整祖先界限
            int index = grandParent;
            while (index != b2_nullNode) {
                //平衡
                index = balance(index);
                //获取左右孩子
                int child1 = m_nodes.get(index).child1;
                int child2 = m_nodes.get(index).child2;
                //合并aabb
                //高度
                m_nodes.get(index).aabb.Combine(m_nodes.get(child1).aabb, m_nodes.get(child2).aabb);
                m_nodes.get(index).height = 1 + Math.max(m_nodes.get(child1).height, m_nodes.get(child2).height);
                //更新index
                index = m_nodes.get(index).parent;
            }
        } else {
            //获取根节点
            m_root = sibling;
            m_nodes.get(sibling).parent = b2_nullNode;
            //释放父节点
            freeNode(parent);
        }

        //Validate();
    }

    /**************************************************************************
     * 功能描述：如果子树A不平衡，则执行一个向左或向右旋转
     * 参数说明：iA   ：子树A
     * 返 回 值： (void)
     ***************************************************************************/
    int balance(int iA) {
        //iA不是根节点
        //b2Assert(iA != b2_nullNode);
        if (iA == b2_nullNode) {
            return 0;
        }
        //获取子树A
        B2TreeNode A = m_nodes.get(iA);
        //已是平衡树，不需要调整
        if (A.IsLeaf() || A.height < 2) {
            return iA;
        }
        // 获取A的左右孩子
        int iB = A.child1;
        int iC = A.child2;
        // iB、iC是否有效
        //b2Assert(0 <= iB && iB < m_nodeCapacity);
        //b2Assert(0 <= iC && iC < m_nodeCapacity);
        // 获取子树B、C
        B2TreeNode B = m_nodes.get(iB);
        B2TreeNode C = m_nodes.get(iC);
        // 获得平衡值
        int balance = C.height - B.height;

        // 上旋C
        if (balance > 1) {
            //获取C的左右孩子iF、iG和子树F、G
            int iF = C.child1;
            int iG = C.child2;
            B2TreeNode F = m_nodes.get(iF);
            B2TreeNode G = m_nodes.get(iG);
            // 验证iF、iG是否有效
            //b2Assert(0 <= iF && iF < m_nodeCapacity);
            //b2Assert(0 <= iG && iG < m_nodeCapacity);
            // 交换A和C
            C.child1 = iA;
            C.parent = A.parent;
            A.parent = iC;
            // A的父指针应该指向c
            // A不是头节点
            if (C.parent != b2_nullNode) {
                if (m_nodes.get(C.parent).child1 == iA) {
                    m_nodes.get(C.parent).child1 = iC;
                } else {
                    //b2Assert(m_nodes[C.parent).child2 == iA);
                    m_nodes.get(C.parent).child2 = iC;
                }
            } else {
                //A是头节点
                m_root = iC;
            }
            // 旋转
            // 如果f的高度大，则旋转F
            if (F.height > G.height) {
                C.child2 = iF;
                A.child2 = iG;
                G.parent = iA;
                A.aabb.Combine(B.aabb, G.aabb);
                C.aabb.Combine(A.aabb, F.aabb);

                A.height = 1 + Math.max(B.height, G.height);
                C.height = 1 + Math.max(A.height, F.height);
            } else {
                // 旋转G
                C.child2 = iG;
                A.child2 = iF;
                F.parent = iA;
                A.aabb.Combine(B.aabb, F.aabb);
                C.aabb.Combine(A.aabb, G.aabb);

                A.height = 1 + Math.max(B.height, F.height);
                C.height = 1 + Math.max(A.height, G.height);
            }

            return iC;
        }

        // 上旋B
        if (balance < -1) {
            int iD = B.child1;
            int iE = B.child2;
            B2TreeNode D = m_nodes.get(iD);
            B2TreeNode E = m_nodes.get(iE);
            // b2Assert(0 <= iD && iD < m_nodeCapacity);
            // b2Assert(0 <= iE && iE < m_nodeCapacity);

            //交换A和B
            B.child1 = iA;
            B.parent = A.parent;
            A.parent = iB;
            // A的旧父指针指向B
            if (B.parent != b2_nullNode) {
                if (m_nodes.get(B.parent).child1 == iA) {
                    m_nodes.get(B.parent).child1 = iB;
                } else {
                    //b2Assert(m_nodes[B.parent).child2 == iA);
                    m_nodes.get(B.parent).child2 = iB;
                }
            } else {
                m_root = iB;
            }

            //旋转
            if (D.height > E.height) {
                // 旋转D
                B.child2 = iD;
                A.child1 = iE;
                E.parent = iA;
                A.aabb.Combine(C.aabb, E.aabb);
                B.aabb.Combine(A.aabb, D.aabb);

                A.height = 1 + Math.max(C.height, E.height);
                B.height = 1 + Math.max(A.height, D.height);
            } else {
                // 旋转E
                B.child2 = iE;
                A.child1 = iD;
                D.parent = iA;
                A.aabb.Combine(C.aabb, D.aabb);
                B.aabb.Combine(A.aabb, E.aabb);

                A.height = 1 + Math.max(C.height, D.height);
                B.height = 1 + Math.max(A.height, E.height);
            }

            return iB;
        }

        return iA;
    }

    /**************************************************************************
     * 功能描述：获得树的高度
     * 参数说明：(void)
     * 返 回 值：树的高度
     ***************************************************************************/
    int getHeight() {
        if (m_root == b2_nullNode) {
            return 0;
        }

        return m_nodes.get(m_root).height;
    }

    /**************************************************************************
     * 功能描述：获得节点总数“面积”之和根“面积”的比
     * 参数说明：(void)
     * 返 回 值：树的高度
     ***************************************************************************/
    float getAreaRatio() {
        //空树
        if (m_root == b2_nullNode) {
            return 0.0f;
        }
        //获取根子树
        B2TreeNode root = m_nodes.get(m_root);
        float rootArea = root.aabb.GetPerimeter();
        //获取所有节点的总“面积”，其实是周长
        float totalArea = 0.0f;
        for (int i = 0; i < m_nodeCapacity; ++i) {
            B2TreeNode node = m_nodes.get(i);
            if (node.height < 0) {
                //内存池内的空闲节点
                continue;
            }

            totalArea += node.aabb.GetPerimeter();
        }
        //获取比率
        return totalArea / rootArea;
    }

    /**************************************************************************
     * 功能描述：计算子树的高度
     * 参数说明：nodeid：子树的头指针索引
     * 返 回 值：子树的高度
     ***************************************************************************/
    int computeHeight(int nodeId) {
        //验证子树的头指针索引
        if (!checkProxy(nodeId)) {
            return 0;
        }
        //b2Assert(0 <= nodeId && nodeId < m_nodeCapacity);
        //获取子树头节点
        B2TreeNode node = m_nodes.get(nodeId);
        // 是否是叶子
        if (node.IsLeaf()) {
            return 0;
        }
        //递给调用，返回高度
        int height1 = computeHeight(node.child1);
        int height2 = computeHeight(node.child2);
        return 1 + Math.max(height1, height2);
    }

    /**************************************************************************
     * 功能描述：计算树的高度
     * 参数说明：(void)
     * 返 回 值：树的高度
     ***************************************************************************/
    int computeHeight() {
        int height = computeHeight(m_root);
        return height;
    }

    /**************************************************************************
     * 功能描述：验证子树的结构
     * 参数说明：index:子树的索引值
     * 返 回 值：(void)
     ***************************************************************************/
    void validateStructure(int index) {
        //空树
        if (index == b2_nullNode) {
            return;
        }
        //子树是整个树
        if (index == m_root) {
            //验证有效性
            //b2Assert(m_nodes[index).parent == b2_nullNode);
        }
        //获取子树的头指针
        B2TreeNode node = m_nodes.get(index);
        //获取左右孩子
        int child1 = node.child1;
        int child2 = node.child2;
        //node是否是叶子节点
        if (node.IsLeaf()) {
            //验证左右孩子和高度
            //b2Assert(child1 == b2_nullNode);
            //b2Assert(child2 == b2_nullNode);
            //b2Assert(node.height == 0);
            return;
        }
        //验证左右孩子
        //b2Assert(0 <= child1 && child1 < m_nodeCapacity);
        //b2Assert(0 <= child2 && child2 < m_nodeCapacity);
        //验证左右孩子的父节点
        //b2Assert(m_nodes[child1).parent == index);
        //b2Assert(m_nodes[child2).parent == index);
        //递归验证左右孩子的结构
        validateStructure(child1);
        validateStructure(child2);
    }

    /**************************************************************************
     * 功能描述：验证子树的度量值
     * 参数说明：index:子树的索引值
     * 返 回 值：(void)
     ***************************************************************************/
    void validateMetrics(int index) {
        //空子树
        if (index == b2_nullNode) {
            return;
        }
        //子树的头指针
        B2TreeNode node = m_nodes.get(index);
        //获取左右孩子
        int child1 = node.child1;
        int child2 = node.child2;
        //子树是否是叶子节点
        if (node.IsLeaf()) {
            //验证左右孩子和高度
            //b2Assert(child1 == b2_nullNode);
            //b2Assert(child2 == b2_nullNode);
            //b2Assert(node.height == 0);
            return;
        }
        //验证左右孩子
        // b2Assert(0 <= child1 && child1 < m_nodeCapacity);
        // b2Assert(0 <= child2 && child2 < m_nodeCapacity);
        //验证高度
        int height1 = m_nodes.get(child1).height;
        int height2 = m_nodes.get(child2).height;
        int height;
        height = 1 + Math.max(height1, height2);
        //b2Assert(node.height == height);
        //验证aabb
        B2AABB aabb = new B2AABB();
        aabb.Combine(m_nodes.get(child1).aabb, m_nodes.get(child2).aabb);

        // b2Assert(aabb.lowerBound == node.aabb.lowerBound);
        // b2Assert(aabb.upperBound == node.aabb.upperBound);
        //递归验证左右孩子子树
        validateMetrics(child1);
        validateMetrics(child2);
    }

    /**************************************************************************
     * 功能描述：验证这棵树，用于测试
     * 参数说明：(void)
     * 返 回 值：(void)
     ***************************************************************************/
    void Validate() {
        //验证这棵树的结构和度量
        validateStructure(m_root);
        validateMetrics(m_root);
        //遍历空闲链表，计算空闲节点的个数
        int freeCount = 0;
        int freeIndex = m_freeList;
        while (freeIndex != b2_nullNode) {
            //b2Assert(0 <= freeIndex && freeIndex < m_nodeCapacity);
            freeIndex = m_nodes.get(freeIndex).next;
            ++freeCount;
        }
        //验证高度
        //b2Assert(GetHeight() == ComputeHeight());
        //验证内存池中的节点总容量
        //b2Assert(m_nodeCount + freeCount == m_nodeCapacity);
    }

    /**************************************************************************
     * 功能描述：获取平衡值
     * 参数说明：获取最大的平衡值
     * 返 回 值：(void)
     ***************************************************************************/
    int getMaxBalance() {
        //
        int maxBalance = 0;
        for (int i = 0; i < m_nodeCapacity; ++i) {
            B2TreeNode node = m_nodes.get(i);
            // 内存池中的空闲节点
            if (node.height <= 1) {
                continue;
            }

            //b2Assert(node.IsLeaf() == false);
            //获取最大平衡值
            int child1 = node.child1;
            int child2 = node.child2;
            int balance = Math.abs(m_nodes.get(child2).height - m_nodes.get(child1).height);
            maxBalance = Math.max(maxBalance, balance);
        }

        return maxBalance;
    }

    /**************************************************************************
     * 功能描述：构建一个最优的树，非常昂贵，用于测试
     * 参数说明：(void)
     * 返 回 值：(void)
     ***************************************************************************/
    void rebuildBottomUp() {
        //从系统堆中申请一段内存
        //int*nodes = ( int*)b2Alloc(m_nodeCount * sizeof( int));
        List<Integer> nodes = new ArrayList<>(m_nodeCount);
        for (int i = 0; i < m_nodeCount; i++) {
            nodes.set(i, b2_nullNode);
        }

        int count = 0;
        //创建空闲的叶子数组。其余是空闲的
        for (int i = 0; i < m_nodeCapacity; ++i) {
            if (m_nodes.get(i).height < 0) {
                // 内存池中空闲的节点
                continue;
            }
            // 是否是叶子节点
            if (m_nodes.get(i).IsLeaf()) {

                m_nodes.get(i).parent = b2_nullNode;
                nodes.set(count, i);
                ++count;
            } else {
                // 不是则释放到内存池中
                freeNode(i);
            }
        }
        // 叶子节点的个数
        while (count > 1) {
            //最小cost
            float minCost = Float.MAX_VALUE;
            int iMin = -1, jMin = -1;
            //获取最小(j)和第二小(i)的cost
            for (int i = 0; i < count; ++i) {
                B2AABB aabbi = m_nodes.get(nodes.get(i)).aabb;
                for (int j = i + 1; j < count; ++j) {
                    B2AABB aabbj = m_nodes.get(nodes.get(j)).aabb;
                    B2AABB b = new B2AABB();
                    b.Combine(aabbi, aabbj);
                    float cost = b.GetPerimeter();
                    //获取最小的cost
                    if (cost < minCost) {
                        iMin = i;
                        jMin = j;
                        minCost = cost;
                    }
                }
            }
            //获取左右孩子节点和左右子树
            int index1 = nodes.get(iMin);
            int index2 = nodes.get(jMin);
            B2TreeNode child1 = m_nodes.get(index1);
            B2TreeNode child2 = m_nodes.get(index2);
            //申请父子树索引
            int parentIndex = allocateNode();
            //获取父子树节点
            B2TreeNode parent = m_nodes.get(parentIndex);
            parent.child1 = index1;
            parent.child2 = index2;
            parent.height = 1 + Math.max(child1.height, child2.height);
            parent.aabb.Combine(child1.aabb, child2.aabb);
            parent.parent = b2_nullNode;
            //
            child1.parent = parentIndex;
            child2.parent = parentIndex;
            //覆盖最小aabb节点
            nodes.set(jMin, nodes.get(count - 1));
            //将第二小aabb节点用父节点覆盖
            nodes.set(iMin, parentIndex);
            --count;
        }
        //获取跟节点
        m_root = nodes.get(0);
        //释放节点
        //b2Free(nodes);
        //验证这棵树，用于测试
        Validate();
    }

}
