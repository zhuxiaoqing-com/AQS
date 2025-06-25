package com.youxi.dataStructure.box2d3_2.BHVaabbTree;

import com.youxi.dataStructure.box2d3_2.entity.b2TreeStats;
import com.youxi.math.box2dGJK.B2Math;
import com.youxi.math.box2dGJK.shape.B2AABB;
import com.youxi.math.box2dGJK.shape.B2Vec2;


public class b2DynamicTree implements IB3_2DynamicTree{
    public static final int B2_HUGE = 100000; // 最大的坐标范围 -B2_HUGE 到 B2_HUGE
    public static final int B2_NULL_INDEX = -1;
    public static final int NULL = 0;
    public static final int B2_TREE_STACK_SIZE = 1024;


    public static final int b2_allocatedNode = 0x0001; // 已经分配
    public static final int b2_enlargedNode = 0x0002; // 是否移动过
    public static final int b2_leafNode = 0x0004; // 叶子节点


    enum b2RotateType {
        b2_rotateNone,
        b2_rotateBF,
        b2_rotateBG,
        b2_rotateCD,
        b2_rotateCE
    }

    ;

    private b2TreeNode[] nodes;
    /// The root index
    int root;
    // The number of nodes
    int nodeCount;

    /// The allocated node space
    int nodeCapacity;

    /// Node free list
    int freeList;

    /// Number of proxies created
    int proxyCount;

    /// Leaf indices for rebuild
    int[] leafIndices;// 所有需要重新调整的叶子节点index

    /// Leaf bounding boxes for rebuild
    B2AABB leafBoxes;

    /// Leaf bounding box centers for rebuild
    B2Vec2[] leafCenters; // 所有需要重新调整的叶子节点的中心点

    /// Bins for sorting during rebuild
    int binIndices;

    /// Allocated space for rebuilding
    int rebuildCapacity;

    private b2DynamicTree() {
    }

    public b2TreeStats b2DynamicTree_Query(B2AABB aabb, long maskBits, B2TreeQueryCallbackFcn callback, Object context) {
        b2TreeStats result = new b2TreeStats();

        if (nodeCount == 0) {
            return result;
        }

        int stack[] = new int[B2_TREE_STACK_SIZE];
        int stackCount = 0;
        stack[stackCount++] = root;

        while (stackCount > 0) {
            int nodeId = stack[--stackCount];
            if (nodeId == B2_NULL_INDEX) {
                // todo huh?
                B2_ASSERT(false);
                continue;
            }

            b2TreeNode node = nodes[nodeId];
            result.nodeVisits += 1;

            if (B2Math.B2AABB_Overlaps(aabb, aabb) && (node.categoryBits & maskBits) != 0) {
                if (b2IsLeaf(node)) {
                    // callback to user code with proxy id
                    boolean proceed = callback.callback(nodeId, node.userData, context);
                    result.leafVisits += 1;

                    if (proceed == false) {
                        return result;
                    }
                } else {
                    if (stackCount < B2_TREE_STACK_SIZE - 1) {
                        stack[stackCount++] = node.child1;
                        stack[stackCount++] = node.child2;
                    } else {
                        B2_ASSERT(stackCount < B2_TREE_STACK_SIZE - 1);
                    }
                }
            }
        }

        return result;
    }


    public b2TreeStats b2DynamicTree_RayCast(b2RayCastInput input, long maskBits,
                                      B2TreeRayCastCallbackFcn callback, Object context) {
        b2TreeStats result = new b2TreeStats();

        if (nodeCount == 0) {
            return result;
        }

        B2Vec2 p1 = input.origin;
        B2Vec2 d = input.translation;

        B2Vec2 r = B2Math.b2Normalize(d);

        // v is perpendicular to the segment.
        B2Vec2 v = B2Math.b2CrossSV(1.0f, r);
        B2Vec2 abs_v = B2Math.b2Abs(v);

        // Separating axis for segment (Gino, p80).
        // |dot(v, p1 - c)| > dot(|v|, h)

        float maxFraction = input.maxFraction;

        B2Vec2 p2 = B2Math.b2MulAdd(p1, maxFraction, d);

        // Build a bounding box for the segment.
        B2AABB segmentAABB = new B2AABB(B2Math.b2Min(p1, p2), B2Math.b2Max(p1, p2));

        int stack[] = new int[B2_TREE_STACK_SIZE];
        int stackCount = 0;
        stack[stackCount++] = root;


        b2RayCastInput subInput = input;

        while (stackCount > 0) {
            // 先把最里面的弹出来
            int nodeId = stack[--stackCount];
            if (nodeId == B2_NULL_INDEX) {
                // todo is this possible?
                B2_ASSERT(false);
                continue;
            }

            b2TreeNode node = nodes[nodeId];
            result.nodeVisits += 1;

            B2AABB nodeAABB = node.aabb;

            if ((node.categoryBits & maskBits) == 0 || B2Math.B2AABB_Overlaps(nodeAABB, segmentAABB) == false) {
                continue;
            }

            // Separating axis for segment (Gino, p80).
            // |dot(v, p1 - c)| > dot(|v|, h)
            // radius extension is added to the node in this case
            B2Vec2 c = B2Math.B2AABB_Center(nodeAABB);
            B2Vec2 h = B2Math.B2AABB_Extents(nodeAABB);
            float term1 = B2Math.b2AbsFloat(B2Math.b2Dot(v, B2Math.b2Sub(p1, c)));
            float term2 = B2Math.b2Dot(abs_v, h);
            if (term2 < term1) {
                continue;
            }

            if (b2IsLeaf(node)) {
                subInput.maxFraction = maxFraction;

                float value = callback.callback(subInput, nodeId, node.userData, context);
                result.leafVisits += 1;

                // The user may return -1 to indicate this shape should be skipped

                if (value == 0.0f) {
                    // The client has terminated the ray cast.
                    return result;
                }

                if (0.0f < value && value <= maxFraction) {
                    // Update segment bounding box.
                    maxFraction = value;
                    p2 = B2Math.b2MulAdd(p1, maxFraction, d);
                    segmentAABB.lowerBound = B2Math.b2Min(p1, p2);
                    segmentAABB.upperBound = B2Math.b2Max(p1, p2);
                }
            } else {
                if (stackCount < B2_TREE_STACK_SIZE - 1) {
                    B2Vec2 c1 = B2Math.B2AABB_Center(nodes[node.child1].aabb);
                    B2Vec2 c2 = B2Math.B2AABB_Center(nodes[node.child2].aabb);
                    if (B2Math.b2DistanceSquared(c1, p1) < B2Math.b2DistanceSquared(c2, p1)) {
                        //   int nodeId = stack[--stackCount]; 因为是先把最里面的那个弹出来 所以 这里的话是先检测 child1 在检测child2
                        stack[stackCount++] = node.child2;
                        stack[stackCount++] = node.child1;
                    } else {
                        stack[stackCount++] = node.child1;
                        stack[stackCount++] = node.child2;
                    }
                } else {
                    B2_ASSERT(stackCount < B2_TREE_STACK_SIZE - 1);
                }
            }
        }

        return result;
    }


    /**
     * 和打射线一样 也将boxCast 扩大成了一个特别大的aabb盒子
     */
    b2TreeStats b2DynamicTree_ShapeCast(b2ShapeCastInput input, long maskBits,
                                        B2TreeShapeCastCallbackFcn callback, Object context) {
        b2TreeStats stats = new b2TreeStats();

        if (nodeCount == 0 || input.proxy.count == 0) {
            return stats;
        }

        // 找到proxy的aabb包围盒
        B2AABB originAABB = new B2AABB(input.proxy.points[0], input.proxy.points[0]);
        for (int i = 1; i < input.proxy.count; ++i) {
            originAABB.lowerBound = B2Math.b2Min(originAABB.lowerBound, input.proxy.points[i]);
            originAABB.upperBound = B2Math.b2Max(originAABB.upperBound, input.proxy.points[i]);
        }

        B2Vec2 radius = new B2Vec2(input.proxy.radius, input.proxy.radius);

        // 包围盒应用半径
        originAABB.lowerBound = B2Math.b2Sub(originAABB.lowerBound, radius);
        originAABB.upperBound = B2Math.b2Add(originAABB.upperBound, radius);

        B2Vec2 p1 = B2Math.B2AABB_Center(originAABB);
        B2Vec2 extension = B2Math.B2AABB_Extents(originAABB);

        // v is perpendicular to the segment.
        B2Vec2 r = input.translation;
        B2Vec2 v = B2Math.b2CrossSV(1.0f, r);
        B2Vec2 abs_v = B2Math.b2Abs(v);

        // Separating axis for segment (Gino, p80).
        // |dot(v, p1 - c)| > dot(|v|, h)

        float maxFraction = input.maxFraction;

        // 将盒子和盒子可以前往的最远终点 合成一个最大的aabb盒子
        // Build total box for the shape cast
        B2Vec2 t = B2Math.b2MulSV(maxFraction, input.translation);
        B2AABB totalAABB = new B2AABB(
                B2Math.b2Min(originAABB.lowerBound, B2Math.b2Add(originAABB.lowerBound, t)),
                B2Math.b2Max(originAABB.upperBound, B2Math.b2Add(originAABB.upperBound, t))
        );

        b2ShapeCastInput subInput = input;

        int stack[] = new int[B2_TREE_STACK_SIZE];
        int stackCount = 0;
        stack[stackCount++] = root;

        while (stackCount > 0) {
            int nodeId = stack[--stackCount];
            if (nodeId == B2_NULL_INDEX) {
                // todo is this possible?
                B2_ASSERT(false);
                continue;
            }

            b2TreeNode node = nodes[nodeId];
            stats.nodeVisits += 1;

            if ((node.categoryBits & maskBits) == 0 || B2Math.B2AABB_Overlaps(node.aabb, totalAABB) == false) {
                continue;
            }

            // Separating axis for segment (Gino, p80).
            // |dot(v, p1 - c)| > dot(|v|, h)
            // radius extension is added to the node in this case
            B2Vec2 c = B2Math.B2AABB_Center(node.aabb);
            B2Vec2 h = B2Math.b2Add(B2Math.B2AABB_Extents(node.aabb), extension);
            float term1 = B2Math.b2AbsFloat(B2Math.b2Dot(v, B2Math.b2Sub(p1, c)));
            float term2 = B2Math.b2Dot(abs_v, h);
            if (term2 < term1) {
                continue;
            }

            if (b2IsLeaf(node)) {
                subInput.maxFraction = maxFraction;

                float value = callback.callback(subInput, nodeId, node.userData, context);
                stats.leafVisits += 1;

                if (value == 0.0f) {
                    // The client has terminated the ray cast.
                    return stats;
                }

                if (0.0f < value && value < maxFraction) {
                    // Update segment bounding box.
                    maxFraction = value;
                    t = B2Math.b2MulSV(maxFraction, input.translation);
                    totalAABB.lowerBound = B2Math.b2Min(originAABB.lowerBound, B2Math.b2Add(originAABB.lowerBound, t));
                    totalAABB.upperBound = B2Math.b2Max(originAABB.upperBound, B2Math.b2Add(originAABB.upperBound, t));
                }
            } else {
                if (stackCount < B2_TREE_STACK_SIZE - 1) {
                    B2Vec2 c1 = B2Math.B2AABB_Center(nodes[node.child1].aabb);
                    B2Vec2 c2 = B2Math.B2AABB_Center(nodes[node.child2].aabb);
                    if (B2Math.b2DistanceSquared(c1, p1) < B2Math.b2DistanceSquared(c2, p1)) {
                        stack[stackCount++] = node.child2;
                        stack[stackCount++] = node.child1;
                    } else {
                        stack[stackCount++] = node.child1;
                        stack[stackCount++] = node.child2;
                    }
                } else {
                    B2_ASSERT(stackCount < B2_TREE_STACK_SIZE - 1);
                }
            }
        }

        return stats;
    }


    boolean b2IsLeaf(b2TreeNode node) {
        return (node.flags & b2_leafNode) != 0;
    }

    boolean b2IsAllocated(b2TreeNode node) {
        int i = node.flags & b2_allocatedNode;
        return i != 0;
    }

    int b2MaxUInt16(int a, int b) {
        return a > b ? a : b;
    }

    public static b2DynamicTree b2DynamicTree_Create() {
        b2DynamicTree tree = new b2DynamicTree();
        tree.root = B2_NULL_INDEX;

        tree.nodeCapacity = 16;
        tree.nodeCount = 0;
        tree.nodes = new b2TreeNode[tree.nodeCapacity];
        for (int i = 0; i < tree.nodeCapacity; i++) {
            tree.nodes[i] = new b2TreeNode();
        }

        // Build a linked list for the free list.
        for (int i = 0; i < tree.nodeCapacity - 1; ++i) {
            tree.nodes[i].next = i + 1;
        }

        tree.nodes[tree.nodeCapacity - 1].next = B2_NULL_INDEX;
        tree.freeList = 0;

        tree.proxyCount = 0;
        tree.rebuildCapacity = 0;

        return tree;
    }

    public void b2DynamicTree_Destroy() {

    }

    int b2AllocateNode() {
        // Expand the node pool as needed.
        if (freeList == B2_NULL_INDEX) {
            B2_ASSERT(nodeCount == nodeCapacity);

            // The free list is empty. Rebuild a bigger pool.
            b2TreeNode[] oldNodes = nodes;
            int oldCapacity = nodeCapacity;
            nodeCapacity += oldCapacity >> 1;
            nodes = new b2TreeNode[nodeCapacity];
            B2_ASSERT(oldNodes != null);

            for (int i = 0; i < oldNodes.length; i++) {
                nodes[i] = oldNodes[i];
            }

            for (int i = oldCapacity; i < nodes.length; i++) {
                nodes[i] = new b2TreeNode();
            }

            // Build a linked list for the free list. The parent pointer becomes the "next" pointer.
            // todo avoid building freelist?
            for (int i = nodeCount; i < nodeCapacity - 1; ++i) {
                nodes[i].next = i + 1;
            }

            nodes[nodeCapacity - 1].next = B2_NULL_INDEX;
            freeList = nodeCount;
        }

        // Peel a node off the free list.
        int nodeIndex = freeList;
        b2TreeNode node = nodes[nodeIndex];
        freeList = node.next;
        node.init();
        ++nodeCount;
        return nodeIndex;
    }

    void b2FreeNode(int nodeId) {
        B2_ASSERT(0 <= nodeId && nodeId < nodeCapacity);
        B2_ASSERT(0 < nodeCount);
        nodes[nodeId].next = freeList;
        nodes[nodeId].flags = 0;
        freeList = nodeId;
        --nodeCount;
    }


    // Greedy algorithm for sibling selection using the SAH
// We have three nodes A-(B,C) and want to add a leaf D, there are three choices.
// 1: make a new parent for A and D : E-(A-(B,C), D)
// 2: associate D with B
//   a: B is a leaf : A-(E-(B,D), C)
//   b: B is an internal node: A-(B{D},C)
// 3: associate D with C
//   a: C is a leaf : A-(B, E-(C,D))
//   b: C is an internal node: A-(B, C{D})
// All of these have a clear cost except when B or C is an internal node. Hence we need to be greedy.

// The cost for cases 1, 2a, and 3a can be computed using the sibling cost formula.
// cost of sibling H = area(union(H, D)) + increased area of ancestors

    // Suppose B (or C) is an internal node, then the lowest cost would be one of two cases:
// case1: D becomes a sibling of B
// case2: D becomes a descendant of B along with a new internal node of area(D).

    //使用SAH进行兄弟选择的贪心算法
//我们有三个节点A-(B，C)，想要添加一个叶子D，有三种选择。
// 1：为A和D创建新的父级：E-（A -(B，C)， D）
// 2：将D与B关联
// a: B是一片叶子：A -（E-(B，D)， C）
// b: B是内部节点：A-（B {D}，C）
// 3：将D与C关联
// a: C是一片叶子：a -（B, E-(C，D)）
// b: C是一个内部节点：A-（B, C{D}）
//所有这些都有明确的代价，除非B或C是内部节点。因此，我们需要贪婪。

//情况1、2a和3a的成本可以使用兄弟成本公式计算。
//同胞H的代价= area(union(H， D)) +祖先的增加面积

    //假设B（或C）是一个内部节点，那么最低成本将是以下两种情况之一：
// case1: D成为B的兄弟姐妹
// case2: D成为B的后代，同时有一个新的区域(D)的内部节点。
    int b2FindBestSibling(B2AABB boxD) {
        B2Vec2 centerD = B2Math.B2AABB_Center(boxD);
        float areaD = B2Math.b2Perimeter(boxD);

        int rootIndex = root;
        B2AABB rootBox = nodes[rootIndex].aabb;
        // Area of current node 当前节点面积
        float areaBase = B2Math.b2Perimeter(rootBox);
        // Area of inflated node 膨胀节点面积
        float directCost = B2Math.b2Perimeter(B2Math.B2AABB_Union(rootBox, boxD));
        float inheritedCost = 0.0f; // 继承消耗

        int bestSibling = rootIndex;
        float bestCost = directCost;

        // Descend the tree from root, following a single greedy path. 从根树开始，沿着一条贪心路径向下。

        int index = rootIndex;

        while (nodes[index].height > 0) {
            int child1 = nodes[index].child1;
            int child2 = nodes[index].child2;

            // 为该节点和新叶节点创建新父节点的成本
            // Cost of creating a new parent for this node and the new leaf
            float cost = directCost + inheritedCost;

            // Sometimes there are multiple identical costs within tolerance. 有时在公差范围内存在多个相同的成本。
            // This breaks the ties using the centroid distance. 这打破了使用质心距离的联系。
            if (cost < bestCost) {
                bestSibling = index;
                bestCost = cost;
            }

            // Inheritance cost seen by children 子女看到的继承成本
            inheritedCost += directCost - areaBase;

            boolean leaf1 = nodes[child1].height == 0;
            boolean leaf2 = nodes[child2].height == 0;

            // Cost of descending into child 1   下降到子节点1的代价
            float lowerCost1 = Float.MAX_VALUE;
            B2AABB box1 = nodes[child1].aabb;
            float directCost1 = B2Math.b2Perimeter(B2Math.B2AABB_Union(box1, boxD));
            float area1 = 0.0f;
            if (leaf1) {
                // Child 1 is a leaf
                // Cost of creating new node and increasing area of node P
                float cost1 = directCost1 + inheritedCost;

                // Need this here due to while condition above
                if (cost1 < bestCost) {
                    bestSibling = child1;
                    bestCost = cost1;
                }
            } else {
                // Child 1 is an internal node  子节点1是一个内部节点
                area1 = B2Math.b2Perimeter(box1);

                // Lower bound cost of inserting under child 1. The minimum accounts for two possibilities:
                // 1. Child1 could be the sibling with cost1 = inheritedCost + directCost1
                // 2. A descendent of child1 could be the sibling with the lower bound cost of
                //       cost1 = inheritedCost + (directCost1 - area1) + areaD
                // This minimum here leads to the minimum of these two costs.

                //子节点1下插入的下界代价。最小值有两种可能：
                // 1。Child1可以是cost1 = inheritedCost + directCost1的兄弟节点
                // 2。child - 1的后代可以是代价的下限为的兄弟姐妹
                // cost1 = inheritedCost + (directCost1 - area1) + areaD
                //这里的最小值导致这两个代价的最小值。
                lowerCost1 = inheritedCost + directCost1 + B2Math.b2MinFloat(areaD - area1, 0.0f);
            }

            // Cost of descending into child 2
            float lowerCost2 = Float.MAX_VALUE;
            B2AABB box2 = nodes[child2].aabb;
            float directCost2 = B2Math.b2Perimeter(B2Math.B2AABB_Union(box2, boxD));
            float area2 = 0.0f;
            if (leaf2) {
                float cost2 = directCost2 + inheritedCost;

                if (cost2 < bestCost) {
                    bestSibling = child2;
                    bestCost = cost2;
                }
            } else {
                area2 = B2Math.b2Perimeter(box2);
                lowerCost2 = inheritedCost + directCost2 + B2Math.b2MinFloat(areaD - area2, 0.0f);
            }

            if (leaf1 && leaf2) {
                break;
            }

            // Can the cost possibly be decreased? 成本有可能降低吗？
            if (bestCost <= lowerCost1 && bestCost <= lowerCost2) {
                break;
            }

            if (lowerCost1 == lowerCost2 && leaf1 == false) {
                B2_ASSERT(lowerCost1 < Float.MAX_VALUE);
                B2_ASSERT(lowerCost2 < Float.MAX_VALUE);

                ///没有基于下界表面积的明确选择。当两个子节点都完全包含d时，就会发生这种情况。
                // No clear choice based on lower bound surface area. This can happen when both
                // children fully contain D. Fall back to node distance.
                B2Vec2 d1 = B2Math.b2Sub(B2Math.B2AABB_Center(box1), centerD);
                B2Vec2 d2 = B2Math.b2Sub(B2Math.B2AABB_Center(box2), centerD);
                lowerCost1 = B2Math.b2LengthSquared(d1);
                lowerCost2 = B2Math.b2LengthSquared(d2);
            }

            // Descend
            if (lowerCost1 < lowerCost2 && leaf1 == false) {
                index = child1;
                areaBase = area1;
                directCost = directCost1;
            } else {
                index = child2;
                areaBase = area2;
                directCost = directCost2;
            }

            B2_ASSERT(nodes[index].height > 0);
        }

        return bestSibling;
    }


    // Perform a left or right rotation if node A is imbalanced.
// Returns the new root index.
    //如果节点a不平衡，执行向左或向右旋转。
//返回新的根索引。
    void b2RotateNodes(int iA) {
        B2_ASSERT(iA != B2_NULL_INDEX);


        b2TreeNode A = nodes[iA];
        if (A.height < 2) {
            return;
        }

        int iB = A.child1;
        int iC = A.child2;
        B2_ASSERT(0 <= iB && iB < nodeCapacity);
        B2_ASSERT(0 <= iC && iC < nodeCapacity);

        b2TreeNode B = nodes[iB];
        b2TreeNode C = nodes[iC];

        if (B.height == 0) {
            // B is a leaf and C is internal
            B2_ASSERT(C.height > 0);

            int iF = C.child1;
            int iG = C.child2;
            b2TreeNode F = nodes[iF];
            b2TreeNode G = nodes[iG];
            B2_ASSERT(0 <= iF && iF < nodeCapacity);
            B2_ASSERT(0 <= iG && iG < nodeCapacity);

            // Base cost
            float costBase = B2Math.b2Perimeter(C.aabb);

            // Cost of swapping B and F
            B2AABB aabbBG = B2Math.B2AABB_Union(B.aabb, G.aabb);
            float costBF = B2Math.b2Perimeter(aabbBG);

            // Cost of swapping B and G
            B2AABB aabbBF = B2Math.B2AABB_Union(B.aabb, F.aabb);
            float costBG = B2Math.b2Perimeter(aabbBF);

            if (costBase < costBF && costBase < costBG) {
                // Rotation does not improve cost
                return;
            }

            if (costBF < costBG) {
                // Swap B and F
                A.child1 = iF;
                C.child1 = iB;

                B.parent = iC;
                F.parent = iA;

                C.aabb = aabbBG;

                C.height = 1 + b2MaxUInt16(B.height, G.height);
                A.height = 1 + b2MaxUInt16(C.height, F.height);
                C.categoryBits = B.categoryBits | G.categoryBits;
                A.categoryBits = C.categoryBits | F.categoryBits;
                C.flags |= (B.flags | G.flags) & b2_enlargedNode;
                A.flags |= (C.flags | F.flags) & b2_enlargedNode;
            } else {
                // Swap B and G
                A.child1 = iG;
                C.child2 = iB;

                B.parent = iC;
                G.parent = iA;

                C.aabb = aabbBF;

                C.height = 1 + b2MaxUInt16(B.height, F.height);
                A.height = 1 + b2MaxUInt16(C.height, G.height);
                C.categoryBits = B.categoryBits | F.categoryBits;
                A.categoryBits = C.categoryBits | G.categoryBits;
                C.flags |= (B.flags | F.flags) & b2_enlargedNode;
                A.flags |= (C.flags | G.flags) & b2_enlargedNode;
            }
        } else if (C.height == 0) {
            // C is a leaf and B is internal
            B2_ASSERT(B.height > 0);

            int iD = B.child1;
            int iE = B.child2;
            b2TreeNode D = nodes[iD];
            b2TreeNode E = nodes[iE];
            B2_ASSERT(0 <= iD && iD < nodeCapacity);
            B2_ASSERT(0 <= iE && iE < nodeCapacity);

            // Base cost
            float costBase = B2Math.b2Perimeter(B.aabb);

            // Cost of swapping C and D
            B2AABB aabbCE = B2Math.B2AABB_Union(C.aabb, E.aabb);
            float costCD = B2Math.b2Perimeter(aabbCE);

            // Cost of swapping C and E
            B2AABB aabbCD = B2Math.B2AABB_Union(C.aabb, D.aabb);
            float costCE = B2Math.b2Perimeter(aabbCD);

            if (costBase < costCD && costBase < costCE) {
                // Rotation does not improve cost
                return;
            }

            if (costCD < costCE) {
                // Swap C and D
                A.child2 = iD;
                B.child1 = iC;

                C.parent = iB;
                D.parent = iA;

                B.aabb = aabbCE;

                B.height = 1 + b2MaxUInt16(C.height, E.height);
                A.height = 1 + b2MaxUInt16(B.height, D.height);
                B.categoryBits = C.categoryBits | E.categoryBits;
                A.categoryBits = B.categoryBits | D.categoryBits;
                B.flags |= (C.flags | E.flags) & b2_enlargedNode;
                A.flags |= (B.flags | D.flags) & b2_enlargedNode;
            } else {
                // Swap C and E
                A.child2 = iE;
                B.child2 = iC;

                C.parent = iB;
                E.parent = iA;

                B.aabb = aabbCD;
                B.height = 1 + b2MaxUInt16(C.height, D.height);
                A.height = 1 + b2MaxUInt16(B.height, E.height);
                B.categoryBits = C.categoryBits | D.categoryBits;
                A.categoryBits = B.categoryBits | E.categoryBits;
                B.flags |= (C.flags | D.flags) & b2_enlargedNode;
                A.flags |= (B.flags | E.flags) & b2_enlargedNode;
            }
        } else {
            int iD = B.child1;
            int iE = B.child2;
            int iF = C.child1;
            int iG = C.child2;

            b2TreeNode D = nodes[iD];
            b2TreeNode E = nodes[iE];
            b2TreeNode F = nodes[iF];
            b2TreeNode G = nodes[iG];

            B2_ASSERT(0 <= iD && iD < nodeCapacity);
            B2_ASSERT(0 <= iE && iE < nodeCapacity);
            B2_ASSERT(0 <= iF && iF < nodeCapacity);
            B2_ASSERT(0 <= iG && iG < nodeCapacity);

            // Base cost
            float areaB = B2Math.b2Perimeter(B.aabb);
            float areaC = B2Math.b2Perimeter(C.aabb);
            float costBase = areaB + areaC;
            b2RotateType bestRotation = b2RotateType.b2_rotateNone;
            float bestCost = costBase;

            // Cost of swapping B and F
            B2AABB aabbBG = B2Math.B2AABB_Union(B.aabb, G.aabb);
            float costBF = areaB + B2Math.b2Perimeter(aabbBG);
            if (costBF < bestCost) {
                bestRotation = b2RotateType.b2_rotateBF;
                bestCost = costBF;
            }

            // Cost of swapping B and G
            B2AABB aabbBF = B2Math.B2AABB_Union(B.aabb, F.aabb);
            float costBG = areaB + B2Math.b2Perimeter(aabbBF);
            if (costBG < bestCost) {
                bestRotation = b2RotateType.b2_rotateBG;
                bestCost = costBG;
            }

            // Cost of swapping C and D
            B2AABB aabbCE = B2Math.B2AABB_Union(C.aabb, E.aabb);
            float costCD = areaC + B2Math.b2Perimeter(aabbCE);
            if (costCD < bestCost) {
                bestRotation = b2RotateType.b2_rotateCD;
                bestCost = costCD;
            }

            // Cost of swapping C and E
            B2AABB aabbCD = B2Math.B2AABB_Union(C.aabb, D.aabb);
            float costCE = areaC + B2Math.b2Perimeter(aabbCD);
            if (costCE < bestCost) {
                bestRotation = b2RotateType.b2_rotateCE;
                // bestCost = costCE;
            }

            switch (bestRotation) {
                case b2_rotateNone:
                    break;

                case b2_rotateBF:
                    A.child1 = iF;
                    C.child1 = iB;

                    B.parent = iC;
                    F.parent = iA;

                    C.aabb = aabbBG;
                    C.height = 1 + b2MaxUInt16(B.height, G.height);
                    A.height = 1 + b2MaxUInt16(C.height, F.height);
                    C.categoryBits = B.categoryBits | G.categoryBits;
                    A.categoryBits = C.categoryBits | F.categoryBits;
                    C.flags |= (B.flags | G.flags) & b2_enlargedNode;
                    A.flags |= (C.flags | F.flags) & b2_enlargedNode;
                    break;

                case b2_rotateBG:
                    A.child1 = iG;
                    C.child2 = iB;

                    B.parent = iC;
                    G.parent = iA;

                    C.aabb = aabbBF;
                    C.height = 1 + b2MaxUInt16(B.height, F.height);
                    A.height = 1 + b2MaxUInt16(C.height, G.height);
                    C.categoryBits = B.categoryBits | F.categoryBits;
                    A.categoryBits = C.categoryBits | G.categoryBits;
                    C.flags |= (B.flags | F.flags) & b2_enlargedNode;
                    A.flags |= (C.flags | G.flags) & b2_enlargedNode;
                    break;

                case b2_rotateCD:
                    A.child2 = iD;
                    B.child1 = iC;

                    C.parent = iB;
                    D.parent = iA;

                    B.aabb = aabbCE;
                    B.height = 1 + b2MaxUInt16(C.height, E.height);
                    A.height = 1 + b2MaxUInt16(B.height, D.height);
                    B.categoryBits = C.categoryBits | E.categoryBits;
                    A.categoryBits = B.categoryBits | D.categoryBits;
                    B.flags |= (C.flags | E.flags) & b2_enlargedNode;
                    A.flags |= (B.flags | D.flags) & b2_enlargedNode;
                    break;

                case b2_rotateCE:
                    A.child2 = iE;
                    B.child2 = iC;

                    C.parent = iB;
                    E.parent = iA;

                    B.aabb = aabbCD;
                    B.height = 1 + b2MaxUInt16(C.height, D.height);
                    A.height = 1 + b2MaxUInt16(B.height, E.height);
                    B.categoryBits = C.categoryBits | D.categoryBits;
                    A.categoryBits = B.categoryBits | E.categoryBits;
                    B.flags |= (C.flags | D.flags) & b2_enlargedNode;
                    A.flags |= (B.flags | E.flags) & b2_enlargedNode;
                    break;

                default:
                    B2_ASSERT(false);
                    break;
            }
        }
    }


    void b2InsertLeaf(int leaf, boolean shouldRotate) {
        if (root == B2_NULL_INDEX) {
            root = leaf;
            nodes[root].parent = B2_NULL_INDEX;
            return;
        }

        // Stage 1: find the best sibling for this node
        B2AABB leafAABB = nodes[leaf].aabb;
        int sibling = b2FindBestSibling(leafAABB);

        // Stage 2: create a new parent for the leaf and sibling
        int oldParent = nodes[sibling].parent;
        int newParent = b2AllocateNode();

        // warning: node pointer can change after allocation
        nodes[newParent].parent = oldParent;
        nodes[newParent].userData = Long.MAX_VALUE;
        nodes[newParent].aabb = B2Math.B2AABB_Union(leafAABB, nodes[sibling].aabb);
        nodes[newParent].categoryBits = nodes[leaf].categoryBits | nodes[sibling].categoryBits;
        nodes[newParent].height = nodes[sibling].height + 1;

        if (oldParent != B2_NULL_INDEX) {
            // The sibling was not the root.
            if (nodes[oldParent].child1 == sibling) {
                nodes[oldParent].child1 = newParent;
            } else {
                nodes[oldParent].child2 = newParent;
            }

            nodes[newParent].child1 = sibling;
            nodes[newParent].child2 = leaf;
            nodes[sibling].parent = newParent;
            nodes[leaf].parent = newParent;
        } else {
            // The sibling was the root.
            nodes[newParent].child1 = sibling;
            nodes[newParent].child2 = leaf;
            nodes[sibling].parent = newParent;
            nodes[leaf].parent = newParent;
            root = newParent;
        }

        // Stage 3: walk back up the tree fixing heights and AABBs
        int index = nodes[leaf].parent;
        while (index != B2_NULL_INDEX) {
            int child1 = nodes[index].child1;
            int child2 = nodes[index].child2;

            B2_ASSERT(child1 != B2_NULL_INDEX);
            B2_ASSERT(child2 != B2_NULL_INDEX);

            nodes[index].aabb = B2Math.B2AABB_Union(nodes[child1].aabb, nodes[child2].aabb);
            nodes[index].categoryBits = nodes[child1].categoryBits | nodes[child2].categoryBits;
            nodes[index].height = 1 + b2MaxUInt16(nodes[child1].height, nodes[child2].height);
            nodes[index].flags |= (nodes[child1].flags | nodes[child2].flags) & b2_enlargedNode;

            if (shouldRotate) {
                b2RotateNodes(index);
            }

            index = nodes[index].parent;
        }
    }


    void b2RemoveLeaf(int leaf) {
        if (leaf == root) {
            root = B2_NULL_INDEX;
            return;
        }

        int parent = nodes[leaf].parent;
        int grandParent = nodes[parent].parent;
        int sibling;
        if (nodes[parent].child1 == leaf) {
            sibling = nodes[parent].child2;
        } else {
            sibling = nodes[parent].child1;
        }

        if (grandParent != B2_NULL_INDEX) {
            // Destroy parent and connect sibling to grandParent.
            if (nodes[grandParent].child1 == parent) {
                nodes[grandParent].child1 = sibling;
            } else {
                nodes[grandParent].child2 = sibling;
            }
            nodes[sibling].parent = grandParent;
            b2FreeNode(parent);

            // 这里为什么从祖父开始 不从父类开始,
            // 因为子节点已经被删除了，父节点下只有一个子节点了，那么该子节点可以代替夫节点成为祖父节点的子节点
            // Adjust ancestor bounds.
            int index = grandParent;
            while (index != B2_NULL_INDEX) {
                b2TreeNode node = nodes[index];
                b2TreeNode child1 = nodes[node.child1];
                b2TreeNode child2 = nodes[node.child2];

                // Fast union using SSE
                //__m128 aabb1 = _mm_load_ps(&child1.aabb.lowerBound.x);
                //__m128 aabb2 = _mm_load_ps(&child2.aabb.lowerBound.x);
                //__m128 lower = _mm_min_ps(aabb1, aabb2);
                //__m128 upper = _mm_max_ps(aabb1, aabb2);
                //__m128 aabb = _mm_shuffle_ps(lower, upper, _MM_SHUFFLE(3, 2, 1, 0));
                //_mm_store_ps(&node.aabb.lowerBound.x, aabb);

                node.aabb = B2Math.B2AABB_Union(child1.aabb, child2.aabb);
                node.categoryBits = child1.categoryBits | child2.categoryBits;
                node.height = 1 + b2MaxUInt16(child1.height, child2.height);

                index = node.parent;
            }
        } else {
            root = sibling;
            nodes[sibling].parent = B2_NULL_INDEX;
            b2FreeNode(parent);
        }
    }


    // Create a proxy in the tree as a leaf node. We return the index of the node instead of a pointer so that we can grow
// the node pool.
    public int b2DynamicTree_CreateProxy(B2AABB aabb, long categoryBits, Object userData) {
        B2_ASSERT(-B2_HUGE < aabb.lowerBound.x && aabb.lowerBound.x < B2_HUGE);
        B2_ASSERT(-B2_HUGE < aabb.lowerBound.y && aabb.lowerBound.y < B2_HUGE);
        B2_ASSERT(-B2_HUGE < aabb.upperBound.x && aabb.upperBound.x < B2_HUGE);
        B2_ASSERT(-B2_HUGE < aabb.upperBound.y && aabb.upperBound.y < B2_HUGE);

        int proxyId = b2AllocateNode();
        b2TreeNode node = nodes[proxyId];

        node.aabb = aabb;
        node.userData = userData;
        node.categoryBits = categoryBits;
        node.height = 0;
        node.flags = b2_allocatedNode | b2_leafNode;

        boolean shouldRotate = true;
        b2InsertLeaf(proxyId, shouldRotate);

        proxyCount += 1;

        return proxyId;
    }

    public void b2DynamicTree_DestroyProxy(int proxyId) {
        B2_ASSERT(0 <= proxyId && proxyId < nodeCapacity);
        B2_ASSERT(b2IsLeaf(nodes[proxyId]));

        b2RemoveLeaf(proxyId);
        b2FreeNode(proxyId);

        B2_ASSERT(proxyCount > 0);
        B2_ASSERT(proxyCount > 0);
        proxyCount -= 1;
    }

    int b2DynamicTree_GetProxyCount() {
        return proxyCount;
    }


    /**
     * 如果移动速度特别快的 感觉应该用这个 不然的话 会导致tree膨胀
     */
    public void b2DynamicTree_MoveProxy(int proxyId, B2AABB aabb) {
        B2_ASSERT(B2Math.b2IsValidAABB(aabb));
        B2_ASSERT(aabb.upperBound.x - aabb.lowerBound.x < B2_HUGE);
        B2_ASSERT(aabb.upperBound.y - aabb.lowerBound.y < B2_HUGE);
        B2_ASSERT(0 <= proxyId && proxyId < nodeCapacity);
        B2_ASSERT(b2IsLeaf(nodes[proxyId]));

        b2RemoveLeaf(proxyId);

        nodes[proxyId].aabb = aabb;

        boolean shouldRotate = false;
        b2InsertLeaf(proxyId, shouldRotate);
    }

    /**
     * 增大aabb
     * <p>
     * 移动时调用该方法 和Box2d移动时候调用moveproxy不一样;
     * box2d3.2是移动时调用该方法,然后统一定时重建移动的Proxy
     */
    public void b2DynamicTree_EnlargeProxy(int proxyId, B2AABB aabb) {
        B2_ASSERT(B2Math.b2IsValidAABB(aabb));
        B2_ASSERT(aabb.upperBound.x - aabb.lowerBound.x < B2_HUGE);
        B2_ASSERT(aabb.upperBound.y - aabb.lowerBound.y < B2_HUGE);
        B2_ASSERT(0 <= proxyId && proxyId < nodeCapacity);
        B2_ASSERT(b2IsLeaf(nodes[proxyId]));

        // Caller must ensure this
        //B2_ASSERT(B2Math.B2AABB_Contains(nodes[proxyId].aabb, aabb) == false);

        nodes[proxyId].aabb = aabb;

        int parentIndex = nodes[proxyId].parent;
        while (parentIndex != B2_NULL_INDEX) {
            boolean changed = B2Math.b2EnlargeAABB(nodes[parentIndex].aabb, aabb);
            nodes[parentIndex].flags |= b2_enlargedNode;
            parentIndex = nodes[parentIndex].parent;

            if (changed == false) {
                break;
            }
        }

        while (parentIndex != B2_NULL_INDEX) {
            if ((nodes[parentIndex].flags & b2_enlargedNode) != 0) {
                // early out because this ancestor was previously ascended and marked as enlarged
                break;
            }

            nodes[parentIndex].flags |= b2_enlargedNode;
            parentIndex = nodes[parentIndex].parent;
        }
    }

    void b2DynamicTree_SetCategoryBits(int proxyId, long categoryBits) {
        B2_ASSERT(nodes[proxyId].child1 == B2_NULL_INDEX);
        B2_ASSERT(nodes[proxyId].child2 == B2_NULL_INDEX);
        B2_ASSERT((nodes[proxyId].flags & b2_leafNode) == b2_leafNode);

        nodes[proxyId].categoryBits = categoryBits;

        // Fix up category bits in ancestor internal nodes
        int nodeIndex = nodes[proxyId].parent;
        while (nodeIndex != B2_NULL_INDEX) {
            b2TreeNode node = nodes[nodeIndex];
            int child1 = node.child1;
            B2_ASSERT(child1 != B2_NULL_INDEX);
            int child2 = node.child2;
            B2_ASSERT(child2 != B2_NULL_INDEX);
            node.categoryBits = nodes[child1].categoryBits | nodes[child2].categoryBits;

            nodeIndex = node.parent;
        }
    }

    long b2DynamicTree_GetCategoryBits(int proxyId) {
        B2_ASSERT(0 <= proxyId && proxyId < nodeCapacity);
        return nodes[proxyId].categoryBits;
    }

    /**
     * 当前层高
     * @return
     */
    public int b2DynamicTree_GetHeight() {
        if (root == B2_NULL_INDEX) {
            return 0;
        }

        return nodes[root].height;
    }


    /**
     * 理论上的最小层高
     * @return
     */
    public int calculateMinHeight() {
        return calculateMinHeight(proxyCount);
    }

    //得到节点面积和根面积的比值。
    float b2DynamicTree_GetAreaRatio() {
        if (root == B2_NULL_INDEX) {
            return 0.0f;
        }

        b2TreeNode root = nodes[this.root];
        float rootArea = B2Math.b2Perimeter(root.aabb);

        float totalArea = 0.0f;
        for (int i = 0; i < nodeCapacity; ++i) {
            b2TreeNode node = nodes[i];
            if (b2IsAllocated(node) == false || b2IsLeaf(node) || i == this.root) {
                continue;
            }

            totalArea += B2Math.b2Perimeter(node.aabb);
        }

        return totalArea / rootArea;
    }

    public B2AABB b2DynamicTree_GetRootBounds() {
        if (root != B2_NULL_INDEX) {
            return nodes[root].aabb;
        }

        return new B2AABB();
    }


    /**
     * 当前最高层高
     * @param nodeId
     * @return
     */
    // Compute the height of a sub-tree.
    int b2ComputeHeight(int nodeId) {
        B2_ASSERT(0 <= nodeId && nodeId < nodeCapacity);
        b2TreeNode node = nodes[nodeId];

        if (b2IsLeaf(node)) {
            return 0;
        }

        int height1 = b2ComputeHeight(node.child1);
        int height2 = b2ComputeHeight(node.child2);
        return 1 + B2Math.b2MaxInt(height1, height2);
    }

    /**
     * 理论上的最小层高
     * @return
     */
    public  int calculateMinHeight(int proxyCount) {
        return (int) Math.ceil(Math.log(proxyCount) / Math.log(2) - 1);
    }

    /**
     * ‌动态树重构流程‌
     *
     * 该结构体在动态树执行批量插入/删除后的平衡操作时使用
     * 通过splitIndex将叶子节点数组划分为左右子树，实现类似KD树的空间划分35
     * childCount与索引区间配合实现自底向上的树重构策略3
     */
    class b2RebuildItem {
        int nodeIndex; // 当前处理的动态树节点索引，用于定位树中的特定节点
        int childCount; // 记录该节点的子节点数量，用于判断节点类型（叶子节点/内部节点）

        // Leaf indices
        int startIndex; // 当前操作批次的起始叶子索引，标识重构操作的区间起点
        int splitIndex; // 分割点索引，用于划分左右子树处理范围（类似快速排序的分区点）
        int endIndex; // 当前操作批次的结束叶子索引，与startIndex共同确定操作区间
    }

    ;


    public int b2DynamicTree_Rebuild(boolean fullBuild) {
        if (proxyCount == 0) {
            return 0;
        }

        // Ensure capacity for rebuild space
        if (proxyCount > rebuildCapacity) {
            int newCapacity = proxyCount + proxyCount / 2;

            //b2Free(tree . leafIndices, tree . rebuildCapacity * sizeof( int ) );
            leafIndices = new int[newCapacity];

            leafCenters = new B2Vec2[newCapacity];
            rebuildCapacity = newCapacity;
        }

        int leafCount = 0;
        int[] stack = new int[B2_TREE_STACK_SIZE];
        int stackCount = 0;

        int nodeIndex = root;
        b2TreeNode node = nodes[nodeIndex];

        // These are the nodes that get sorted to rebuild the tree.
        // I'm using indices because the node pool may grow during the build.

// Gather all proxy nodes that have grown and all internal nodes that haven't grown. Both are
        // considered leaves in the tree rebuild.
        // Free all internal nodes that have grown.
        // todo use a node growth metric instead of simply enlarged to reduce rebuild size and frequency
        // this should be weighed against B2_AABB_MARGIN
 // 收集所有发生扩展的代理节点（proxy nodes）以及未扩展的内部节点（internal nodes）。
// 在树重构过程中，这两类节点均被视为叶子节点。
// 释放所有已扩展的内部节点。
// 待优化：应使用节点扩展度量值（growth metric）而非简单的大小变化判断，
//         以减少重构范围和频率（需与B2_AABB_MARGIN阈值权衡）
        while (true) {
            // 条件1：叶子节点直接收集
            // 条件2：非完全重建时收集未扩张的内部节点
            // node.height == 0叶子节点 如果没有 移动过的aabb 和也不要求全部build
            if (node.height == 0 || ((node.flags & b2_enlargedNode) == 0 && fullBuild == false)) {
                leafIndices[leafCount] = nodeIndex;

                leafCenters[leafCount] = B2Math.b2AABB_Center(node.aabb);
                leafCount += 1;

                // Detach
                node.parent = B2_NULL_INDEX; // 解除父节点关联
            } else { // 需要销毁的扩张节点
                int doomedNodeIndex = nodeIndex;  // 标记待销毁节点

                // Handle children // 深度优先遍历：将第一个子节点设为当前节点
                nodeIndex = node.child1;

                // 将子节点2加入stack // 将第二个子节点压栈
                if (stackCount < B2_TREE_STACK_SIZE) {
                    stack[stackCount++] = node.child2;
                } else {
                    // 栈溢出保护（实际应永不触发）
                    B2_ASSERT(stackCount < B2_TREE_STACK_SIZE);
                }
                // 移动到子节点
                node = nodes[nodeIndex];

                // Remove doomed node // 销毁原节点（非叶子且需要重建）
                b2FreeNode(doomedNodeIndex);

                continue; // 跳过栈处理逻辑
            }

            // 栈为空时结束遍历
            if (stackCount == 0) {
                break;
            }
            // 弹栈处理下一个节点
            nodeIndex = stack[--stackCount];
            node = nodes[nodeIndex];
        }

        B2_ASSERT(leafCount <= proxyCount);
        // 执行树重建（核心算法）
        root = b2BuildTree(leafCount);

        b2DynamicTree_Validate();
        return leafCount;
    }

    // Returns root node index
    int b2BuildTree(int leafCount) {
        // 处理单叶子节点情况
        if (leafCount == 1) {
            nodes[leafIndices[0]].parent = B2_NULL_INDEX;
            return leafIndices[0];
        }

        // 定义重建栈（固定大小）
        // todo large stack item
        b2RebuildItem[] stack = new b2RebuildItem[B2_TREE_STACK_SIZE];
        for (int i = 0; i < stack.length; i++) {
            stack[i] = new b2RebuildItem();
        }

        int top = 0; // 栈顶指针

        // 初始化栈：创建根节点
        stack[0].nodeIndex = b2AllocateNode(); // 分配新节点
        stack[0].childCount = -1; // 子节点计数器（-1表示未开始）
        stack[0].startIndex = 0;// 当前节点处理的叶子起始索引
        stack[0].endIndex = leafCount; // 结束索引
        // 中点分割法
        stack[0].splitIndex = b2PartitionMid(leafIndices, leafCenters, 0, 0, leafCount);

        while (true) { // 主重建循环
             b2RebuildItem item = stack[top]; // 获取当前栈顶项

            item.childCount += 1; // 处理下一个子节点

            if (item.childCount == 2) {  // 当两个子节点都已处理完成
                // This internal node has both children established

                // 根节点处理完成
                if (top == 0) {
                    // all done
                    break;
                }

                // 处理父子关系
                 b2RebuildItem parentItem = stack[(top - 1)];
                b2TreeNode  parentNode = nodes[parentItem.nodeIndex];
                // 连接第一个子节点
                if (parentItem.childCount == 0) {
                    B2_ASSERT(parentNode.child1 == B2_NULL_INDEX);
                    parentNode.child1 = item.nodeIndex;
                } else {  // 连接第二个子节点
                    B2_ASSERT(parentItem.childCount == 1);
                    B2_ASSERT(parentNode.child2 == B2_NULL_INDEX);
                    parentNode.child2 = item.nodeIndex;
                }
                // 设置父节点和子节点关系
                b2TreeNode  node = nodes[item.nodeIndex];

                B2_ASSERT(node.parent == B2_NULL_INDEX);
                node.parent = parentItem.nodeIndex;
                // 验证子节点存在
                B2_ASSERT(node.child1 != B2_NULL_INDEX);
                B2_ASSERT(node.child2 != B2_NULL_INDEX);
                // 获取子节点
                b2TreeNode  child1 = nodes[node.child1];
                b2TreeNode  child2 = nodes[node.child2];

                // 计算当前节点的AABB/高度/类别
                node.aabb = B2Math.B2AABB_Union(child1.aabb, child2.aabb);
                node.height = 1 + b2MaxUInt16(child1.height, child2.height);
                node.categoryBits = child1.categoryBits | child2.categoryBits;

                // Pop stack   // 弹出已完成节点
                top -= 1;
            } else { // 处理子节点（0或1）
                int startIndex, endIndex;
                if (item.childCount == 0) { // 左子树范围
                    startIndex = item.startIndex;
                    endIndex = item.splitIndex;
                } else {   // 右子树范围
                    B2_ASSERT(item.childCount == 1);
                    startIndex = item.splitIndex;
                    endIndex = item.endIndex;
                }

                int count = endIndex - startIndex;  // 当前处理的叶子数

                // 单叶子节点处理
                if (count == 1) {
                    int childIndex = leafIndices[startIndex];
                    b2TreeNode node = nodes[item.nodeIndex];

                    if (item.childCount == 0) {
                        B2_ASSERT(node.child1 == B2_NULL_INDEX);
                        node.child1 = childIndex;
                    } else {
                        B2_ASSERT(item.childCount == 1);
                        B2_ASSERT(node.child2 == B2_NULL_INDEX);
                        node.child2 = childIndex;
                    }
                    // 设置父节点关系
                    b2TreeNode  childNode = nodes[childIndex];
                    B2_ASSERT(childNode.parent == B2_NULL_INDEX);
                    childNode.parent = item.nodeIndex;
                } else {  // 多叶子节点处理（创建新内部节点）
                    B2_ASSERT(count > 0);
                    B2_ASSERT(top < B2_TREE_STACK_SIZE);

                    top += 1;// 压栈新节点
                    b2RebuildItem newItem = stack[top];
                    newItem.nodeIndex = b2AllocateNode(); // 分配新节点
                    newItem.childCount = -1;
                    newItem.startIndex = startIndex;  // 重置子节点计数器
                    newItem.endIndex = endIndex;

                    // 计算子范围的分割点
                    newItem.splitIndex = b2PartitionMid(leafIndices, leafCenters, startIndex, startIndex, count);
                    newItem.splitIndex += startIndex;
                }
            }
        }

        // 后处理根节点
        b2TreeNode  rootNode = nodes[stack[0].nodeIndex];
        B2_ASSERT(rootNode.parent == B2_NULL_INDEX);
        B2_ASSERT(rootNode.child1 != B2_NULL_INDEX);
        B2_ASSERT(rootNode.child2 != B2_NULL_INDEX);

        b2TreeNode  child1 = nodes[rootNode.child1];
        b2TreeNode  child2 = nodes[rootNode.child2];

        // 计算根节点属性
        rootNode.aabb = B2Math.B2AABB_Union(child1.aabb, child2.aabb);
        rootNode.height = 1 + b2MaxUInt16(child1.height, child2.height);
        rootNode.categoryBits = child1.categoryBits | child2.categoryBits;

        return stack[0].nodeIndex; // 返回根节点索引

    }

    /**
     * 该函数实现基于‌中位数分割启发式‌的空间划分算法，用于物理引擎（如Box2D）中优化碰撞检测或空间查询。
     * 核心逻辑是通过选择最长轴进行分区，采用类似快速排序的Hoare分区方案
     *
     * 我们正在分析的是Box2D动态树重建过程中使用的中点分割函数（b2PartitionMid）。该函数用于将一组叶子节点（通过索引数组和中心点数组表示）分割成两个子集，以便构建平衡的AABB树。中点分割策略选择包围盒最长的轴，然后以该轴的中点作为分割点进行划分。
     *
     * 函数目标：返回一个分割索引，使得[0, 分割索引)的元素在分割点的一侧，[分割索引, count)的元素在另一侧。
     *
     * 步骤：
     *
     * 1. 处理简单情况（count<=2）。
     *
     * 2. 计算所有中心点的包围盒（最小值和最大值）。
     *
     * 3. 计算包围盒的中心点（c）和范围（d）。
     *
     * 4. 选择范围最大的轴（x或y）作为分割轴，以该轴的中心点（pivot）进行划分。
     *
     * 5. 使用Hoare分割方案（类似快速排序的分割）将数组分成两部分：小于pivot的在左侧，大于等于pivot的在右侧。
     *
     * 6. 如果分割有效（分割点在(0,count)之间），则返回分割索引；否则返回中点（count/2）。
     *
     * 注意：这个函数会同时交换索引数组和中心点数组的元素，使得分割后左侧的元素中心点坐标小于pivot，右侧的大于等于pivot。
     */
    // Median split heuristic
     int b2PartitionMid(int[] indices, B2Vec2[] centers, int indicesStartIndex, int centersStartIndex, int count) {
         // 处理特殊情况：元素数量≤2时直接返回中点
        // Handle trivial case
        if (count <= 2) {
            return count / 2; // 返回1或0
        }

        // 计算所有中心点的包围盒（AABB）
        B2Vec2 lowerBound = centers[centersStartIndex + 0];
        B2Vec2 upperBound = centers[centersStartIndex + 0];

        // 遍历所有点计算最小/最大值
        for (int i = 1; i < count; ++i) {
            lowerBound = B2Math.b2Min(lowerBound, centers[centersStartIndex + i]);
            upperBound = B2Math.b2Max(upperBound, centers[centersStartIndex + i]);
        }

         // 计算包围盒尺寸和中心点
        B2Vec2 d = B2Math.b2Sub(upperBound, lowerBound);
        B2Vec2 c = new B2Vec2(0.5f * (lowerBound.x + upperBound.x), 0.5f * (lowerBound.y + upperBound.y));

         // 使用Hoare分区方案（类快速排序算法）
        // Partition longest axis using the Hoare partition scheme
        // https://en.wikipedia.org/wiki/Quicksort
        // https://nicholasvadivelu.com/2021/01/11/array-partition/
        int i1 = 0, i2 = count; // 双指针：i1从左向右，i2从右向左
        if (d.x > d.y) { // 选择最长的轴进行分割 // X轴更长
            float pivot = c.x; // 使用X轴中心作为分割点

            // Hoare分区主循环
            while (i1 < i2) {
                // 移动左指针：找到 >= pivot 的元素
                while (i1 < i2 && centers[centersStartIndex + i1].x < pivot) {
                    i1 += 1;
                }
                ;
                // 移动右指针：找到 < pivot 的元素
                while (i1 < i2 && centers[centersStartIndex + i2 - 1].x >= pivot) {
                    i2 -= 1;
                }
                ;
                // 交换不符合分区规则的元素
                if (i1 < i2) {
                    // Swap indices
                    {
                        int temp = indices[indicesStartIndex + i1];
                        indices[indicesStartIndex + i1] = indices[indicesStartIndex + i2 - 1];
                        indices[indicesStartIndex + i2 - 1] = temp;
                    }

                    // Swap centers
                    {
                        B2Vec2 temp = centers[centersStartIndex + i1];
                        centers[centersStartIndex + i1] = centers[centersStartIndex + i2 - 1];
                        centers[centersStartIndex + i2 - 1] = temp;
                    }

                    // 移动指针
                    i1 += 1;
                    i2 -= 1;
                }
            }
        } else { // Y轴更长或相等
            float pivot = c.y; // 使用Y轴中心作为分割点

            while (i1 < i2) {
                while (i1 < i2 && centers[centersStartIndex + i1].y < pivot) {
                    i1 += 1;
                }
                ;

                while (i1 < i2 && centers[centersStartIndex + i2 - 1].y >= pivot) {
                    i2 -= 1;
                }
                ;

                if (i1 < i2) {
                    // Swap indices
                    {
                        int temp = indices[indicesStartIndex +i1];
                        indices[indicesStartIndex +i1] = indices[indicesStartIndex +i2 - 1];
                        indices[indicesStartIndex +i2 - 1] = temp;
                    }

                    // Swap centers
                    {
                        B2Vec2 temp = centers[centersStartIndex + i1];
                        centers[centersStartIndex + i1] = centers[centersStartIndex + i2 - 1];
                        centers[centersStartIndex + i2 - 1] = temp;
                    }

                    i1 += 1;
                    i2 -= 1;
                }
            }
        }
         // 验证指针相遇
        B2_ASSERT(i1 == i2);

         // 检查分割点是否有效
        if (i1 > 0 && i1 < count) {
            return i1;
        }

         // 分割失败时返回中点位置
        return count / 2;
    }


    void b2ValidateStructure(int index) {
        if (index == B2_NULL_INDEX) {
            return;
        }

        if (index == root) {
            B2_ASSERT(nodes[index].parent == B2_NULL_INDEX);
        }

        b2TreeNode node = nodes[index];

        B2_ASSERT(node.flags == 0 || (node.flags & b2_allocatedNode) != 0);

        if (b2IsLeaf(node)) {
            B2_ASSERT(node.height == 0);
            return;
        }

        int child1 = node.child1;
        int child2 = node.child2;

        B2_ASSERT(0 <= child1 && child1 < nodeCapacity);
        B2_ASSERT(0 <= child2 && child2 < nodeCapacity);

        B2_ASSERT(nodes[child1].parent == index);
        B2_ASSERT(nodes[child2].parent == index);

        if (((nodes[child1].flags | nodes[child2].flags) & b2_enlargedNode) != 0) {
            B2_ASSERT((node.flags & b2_enlargedNode) != 0);
        }

        b2ValidateStructure(child1);
        b2ValidateStructure(child2);
    }

    void b2ValidateMetrics(int index) {
        if (index == B2_NULL_INDEX) {
            return;
        }

        b2TreeNode node = nodes[index];

        if (b2IsLeaf(node)) {
            B2_ASSERT(node.height == 0);
            return;
        }

        int child1 = node.child1;
        int child2 = node.child2;

        B2_ASSERT(0 <= child1 && child1 < nodeCapacity);
        B2_ASSERT(0 <= child2 && child2 < nodeCapacity);

        int height1 = nodes[child1].height;
        int height2 = nodes[child2].height;
        int height = 1 + B2Math.b2MaxInt(height1, height2);
        B2_ASSERT(node.height == height);

        // B2AABB aabb = B2AABB_Union(tree.nodes[child1].aabb, tree.nodes[child2].aabb);

        B2_ASSERT(B2Math.B2AABB_Contains(node.aabb, nodes[child1].aabb));
        B2_ASSERT(B2Math.B2AABB_Contains(node.aabb, nodes[child2].aabb));

        // B2_ASSERT(aabb.lowerBound.x == node.aabb.lowerBound.x);
        // B2_ASSERT(aabb.lowerBound.y == node.aabb.lowerBound.y);
        // B2_ASSERT(aabb.upperBound.x == node.aabb.upperBound.x);
        // B2_ASSERT(aabb.upperBound.y == node.aabb.upperBound.y);

        long categoryBits = nodes[child1].categoryBits | nodes[child2].categoryBits;
        B2_ASSERT(node.categoryBits == categoryBits);

        b2ValidateMetrics(child1);
        b2ValidateMetrics(child2);
    }


    public void b2DynamicTree_Validate() {
        if (root == B2_NULL_INDEX) {
            return;
        }

        b2ValidateStructure(root);
        b2ValidateMetrics(root);

        int freeCount = 0;
        int freeIndex = freeList;
        while (freeIndex != B2_NULL_INDEX) {
            B2_ASSERT(0 <= freeIndex && freeIndex < nodeCapacity);
            freeIndex = nodes[freeIndex].next;
            ++freeCount;
        }

        int height = b2DynamicTree_GetHeight();
        int computedHeight = b2ComputeHeight(root);
        B2_ASSERT(height == computedHeight);

        B2_ASSERT(nodeCount + freeCount == nodeCapacity);

        //B2_UNUSED(tree);
    }

    void b2DynamicTree_ValidateNoEnlarged() {
        int capacity = nodeCapacity;
        for (int i = 0; i < capacity; ++i) {
            b2TreeNode node = nodes[i];
            if ((node.flags & b2_allocatedNode) != 0) {
                B2_ASSERT((node.flags & b2_enlargedNode) == 0);
            }
        }
    }

    /*   int b2DynamicTree_GetByteCount( ) {
           size_t size = sizeof(b2DynamicTree) + sizeof(b2TreeNode) * tree.nodeCapacity +
                   tree.rebuildCapacity * (sizeof( int )+sizeof(B2AABB) + sizeof(B2Vec2) + sizeof( int ) );

           return (int) size;
       }*/
    public Object b2DynamicTree_GetUserData(int proxyId) {
        B2_ASSERT(0 <= proxyId && proxyId < nodeCapacity);
        return nodes[proxyId].userData;
    }

    public B2AABB b2DynamicTree_GetAABB(int proxyId) {
        B2_ASSERT(0 <= proxyId && proxyId < nodeCapacity);
        return nodes[proxyId].aabb;
    }


    private void B2_ASSERT(boolean b) {
        if (!b) {
            throw new RuntimeException("b2Assert");
        }
    }

    public void printTree() {
        System.out.println(printTreeToString());
    }

    public String printTreeToString() {
        StringBuilder sb = new StringBuilder();
        printTree(sb, root, "", false);
        return sb.toString();
    }

    void printTree(StringBuilder sb, int nodeId, String indent, boolean last) {
        boolean valid = 0 <= nodeId && nodeId < nodeCapacity;
        if (!valid) { // 只处理子节点
            return;
        }
        b2TreeNode node = nodes[nodeId];
        if (node != null) {
            /*System.out.print(indent);
            System.out.print(last ? "└─ " : "├─ ");
            System.out.println(node.aabb);*/
            sb.append(indent);
            sb.append(last ? "└─ " : "├─ ");
            if (node.userData != null) {
                sb.append(node.userData.toString());
            }
            sb.append(node.aabb + System.lineSeparator());
            String childIndent = indent + (last ? "    " : "│   ");
            printTree(sb, node.child1, childIndent, false);
            printTree(sb, node.child2, childIndent, true);
        }
    }

}
