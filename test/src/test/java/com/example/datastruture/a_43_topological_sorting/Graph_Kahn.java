package com.example.datastruture.a_43_topological_sorting;

import java.util.LinkedList;

public class Graph_Kahn {
    private int v; // 顶点的个数
    private LinkedList<Integer> adj[];// 邻接表

    public Graph_Kahn(int v) {
        this.v = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i) {
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int s, int t) {
        //s 先于 t,边 s->t
        adj[s].add(t);
    }

    /**
     * 入度：指向该顶点的边的数量
     * 出度：以该顶点为起点指向其他顶点的数量
     * <p>
     * 如果 s 需要先于 t 执行，
     * 就添加一条 s 指向 t 的边
     * t 依赖 s   s->t  t的入度为 1
     * 如果如果 t的入度为 0 就说明 t 可以直接运行了 因为 t 不需要依赖别人了
     */
    public void topoSortByKahn() {
        /**
         * i -> w  说明 w 需要依赖于 i
         *
         * 这里循环查询所有需要依赖 别的顶点 的 顶点
         * 吧不需要依赖别的顶点的顶点筛选出来
         */
        /**
         * 因为 数组里面的list 是指向别的顶点的 链表
         */
        int[] inDegree = new int[v]; // 统计每个顶点的入度
        for (int i = 0; i < v; ++i) {
            for (int j = 0; j < adj[i].size(); ++j) {
                /**
                 * w 依赖于 i
                 */
                int w = adj[i].get(j);// i->w
                inDegree[w]++;
            }
        }

        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < v; ++i) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }
        while (!queue.isEmpty()) {
            int i = queue.remove();
            System.out.println("->" + i);
            for (int j = 0; j < adj[i].size(); ++j) {
                int k = adj[i].get(j);
                inDegree[k]--;
                if (inDegree[k] == 0) queue.add(k);
            }
        }
    }

    /**
     * 拓扑排序
     * 顶点指向某个定点记为
     * int[顶点]-> linkedList(被指向的顶点)
     * 要计算某个顶点(a)的入度 就要遍历所有 int[] 查看哪几个顶点指向了 a
     */
    public void topoSortByKahn02() {
        int[] inDegree = new int[v];
        for (int i = 0; i < v; ++i) {
            for (int j = 0; j < adj[i].size(); ++j) {
                Integer peak = adj[i].get(j);
                inDegree[peak]++;
            }
        }

        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            Integer pop = queue.pop();
            System.out.println("->" + pop);

            /**
             * 将指向的顶点的 顶点的入度减 1
             */
            for (int i = 0; i < adj[pop].size(); i++) {
                Integer integer = adj[pop].get(i);
                inDegree[integer]--;
                if (inDegree[integer] == 0) {
                    queue.add(integer);
                }
            }
        }
    }


    /**
     * Kahn 算法
     * 本质使用了贪心算法的思想
     * 看不太懂为什么是使用了贪心算法的思想。难道是因为每次都选择了最好的?(每次都选择了inDegree 最小的 vertex)
     *
     *
     * <p>
     * 将依赖链接起来，如果 a 依赖 b 那么就 a -> b
     * <p>
     * 用 邻接表 存储 graph
     * 顶点里面的 linkedList 存储指向的顶点
     */
    public void kahn() {
        // 存储顶点入度的个数
        int[] inDegree = new int[v];
        for (int i = 0; i < adj.length; i++) {
            for (int j = 0; j < adj[i].size(); j++) {
                // 统计入度 顶点
                Integer vertex = adj[i].get(j);
                inDegree[vertex]++;
            }
        }

        LinkedList<Integer> queue = new LinkedList<>();
        // 将入度为 0 的顶点加入 队列，最先输出
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }


        while (!queue.isEmpty()) {
            Integer vertex = queue.remove();
            System.out.println("->" + vertex);

            // 然后遍历 vertex 指向的顶点，将这些顶点的入度减 1
            for (int i = 0; i < adj[vertex].size(); i++) {
                // 指向的顶点
                Integer pointToVertex = adj[vertex].get(i);
                inDegree[pointToVertex]--;
                if (inDegree[pointToVertex] == 0) {
                    queue.add(pointToVertex);
                }
            }
        }
    }

    /**
     * kahn 算法
     *
     * 定义数据结构的时候，如果 s 需要先于 t 执行，那就添加一条 s 指向 t 的边。所以，如果某个顶点的入度为 0，
     * 也就表示，没有任何顶点必须先于这个顶点执行，那么这个顶点就可以执行了。
     *
     */

}





























