package com.youxi.dataStructure.box2d3_2.aabbTree;

import com.youxi.dataStructure.box2d3_2.entity.b2AABB;
import com.youxi.dataStructure.box2d3_2.entity.b2Math;
import com.youxi.dataStructure.box2d3_2.entity.b2Vec2;

import java.util.ArrayList;
import java.util.List;

public class b2DynamicTree {
    public static final int B2_HUGE = 100000; // 最大的坐标范围 -B2_HUGE 到 B2_HUGE
    public static final int B2_NULL_INDEX = -1;
    public static final int NULL = 0;

    public static final int b2_allocatedNode = 0x0001; // 已经分配
    public static final int b2_enlargedNode = 0x0002;
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
    int leafIndices;

    /// Leaf bounding boxes for rebuild
    b2AABB leafBoxes;

    /// Leaf bounding box centers for rebuild
    b2Vec2 leafCenters;

    /// Bins for sorting during rebuild
    int binIndices;

    /// Allocated space for rebuilding
    int rebuildCapacity;


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
    int b2FindBestSibling(b2AABB boxD) {
        b2Vec2 centerD = b2Math.b2AABB_Center(boxD);
        float areaD = b2Math.b2Perimeter(boxD);

        int rootIndex = root;
        b2AABB rootBox = nodes[rootIndex].aabb;
        // Area of current node 当前节点面积
        float areaBase = b2Math.b2Perimeter(rootBox);
        // Area of inflated node 膨胀节点面积
        float directCost = b2Math.b2Perimeter(b2Math.b2AABB_Union(rootBox, boxD));
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
            b2AABB box1 = nodes[child1].aabb;
            float directCost1 = b2Math.b2Perimeter(b2Math.b2AABB_Union(box1, boxD));
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
                area1 = b2Math.b2Perimeter(box1);

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
                lowerCost1 = inheritedCost + directCost1 + b2Math.b2MinFloat(areaD - area1, 0.0f);
            }

            // Cost of descending into child 2
            float lowerCost2 = Float.MAX_VALUE;
            b2AABB box2 = nodes[child2].aabb;
            float directCost2 = b2Math.b2Perimeter(b2Math.b2AABB_Union(box2, boxD));
            float area2 = 0.0f;
            if (leaf2) {
                float cost2 = directCost2 + inheritedCost;

                if (cost2 < bestCost) {
                    bestSibling = child2;
                    bestCost = cost2;
                }
            } else {
                area2 = b2Math.b2Perimeter(box2);
                lowerCost2 = inheritedCost + directCost2 + b2Math.b2MinFloat(areaD - area2, 0.0f);
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
                b2Vec2 d1 = b2Math.b2Sub(b2Math.b2AABB_Center(box1), centerD);
                b2Vec2 d2 = b2Math.b2Sub(b2Math.b2AABB_Center(box2), centerD);
                lowerCost1 = b2Math.b2LengthSquared(d1);
                lowerCost2 = b2Math.b2LengthSquared(d2);
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
            float costBase = b2Math.b2Perimeter(C.aabb);

            // Cost of swapping B and F
            b2AABB aabbBG = b2Math.b2AABB_Union(B.aabb, G.aabb);
            float costBF = b2Math.b2Perimeter(aabbBG);

            // Cost of swapping B and G
            b2AABB aabbBF = b2Math.b2AABB_Union(B.aabb, F.aabb);
            float costBG = b2Math.b2Perimeter(aabbBF);

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
            float costBase = b2Math.b2Perimeter(B.aabb);

            // Cost of swapping C and D
            b2AABB aabbCE = b2Math.b2AABB_Union(C.aabb, E.aabb);
            float costCD = b2Math.b2Perimeter(aabbCE);

            // Cost of swapping C and E
            b2AABB aabbCD = b2Math.b2AABB_Union(C.aabb, D.aabb);
            float costCE = b2Math.b2Perimeter(aabbCD);

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
            float areaB = b2Math.b2Perimeter(B.aabb);
            float areaC = b2Math.b2Perimeter(C.aabb);
            float costBase = areaB + areaC;
            b2RotateType bestRotation = b2RotateType.b2_rotateNone;
            float bestCost = costBase;

            // Cost of swapping B and F
            b2AABB aabbBG = b2Math.b2AABB_Union(B.aabb, G.aabb);
            float costBF = areaB + b2Math.b2Perimeter(aabbBG);
            if (costBF < bestCost) {
                bestRotation = b2RotateType.b2_rotateBF;
                bestCost = costBF;
            }

            // Cost of swapping B and G
            b2AABB aabbBF = b2Math.b2AABB_Union(B.aabb, F.aabb);
            float costBG = areaB + b2Math.b2Perimeter(aabbBF);
            if (costBG < bestCost) {
                bestRotation = b2RotateType.b2_rotateBG;
                bestCost = costBG;
            }

            // Cost of swapping C and D
            b2AABB aabbCE = b2Math.b2AABB_Union(C.aabb, E.aabb);
            float costCD = areaC + b2Math.b2Perimeter(aabbCE);
            if (costCD < bestCost) {
                bestRotation = b2RotateType.b2_rotateCD;
                bestCost = costCD;
            }

            // Cost of swapping C and E
            b2AABB aabbCD = b2Math.b2AABB_Union(C.aabb, D.aabb);
            float costCE = areaC + b2Math.b2Perimeter(aabbCD);
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
        b2AABB leafAABB = nodes[leaf].aabb;
        int sibling = b2FindBestSibling(leafAABB);

        // Stage 2: create a new parent for the leaf and sibling
        int oldParent = nodes[sibling].parent;
        int newParent = b2AllocateNode();

        // warning: node pointer can change after allocation
        nodes[newParent].parent = oldParent;
        nodes[newParent].userData = Long.MAX_VALUE;
        nodes[newParent].aabb = b2Math.b2AABB_Union(leafAABB, nodes[sibling].aabb);
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

            nodes[index].aabb = b2Math.b2AABB_Union(nodes[child1].aabb, nodes[child2].aabb);
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

                node.aabb = b2Math.b2AABB_Union(child1.aabb, child2.aabb);
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
    int b2DynamicTree_CreateProxy(b2AABB aabb, long categoryBits, long userData) {
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

    void b2DynamicTree_DestroyProxy(int proxyId) {
        B2_ASSERT(0 <= proxyId && proxyId < nodeCapacity);
        B2_ASSERT(b2IsLeaf(nodes[proxyId]));

        b2RemoveLeaf(proxyId);
        b2FreeNode(proxyId);

        B2_ASSERT(proxyCount > 0);
        proxyCount -= 1;
    }

    int b2DynamicTree_GetProxyCount() {
        return proxyCount;
    }

    private void B2_ASSERT(boolean b) {
        if (!b) {
            throw new RuntimeException("b2Assert");
        }
    }
}
