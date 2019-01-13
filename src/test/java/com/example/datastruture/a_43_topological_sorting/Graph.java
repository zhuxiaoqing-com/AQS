package com.example.datastruture.a_43_topological_sorting;

import java.util.LinkedList;

public class Graph {
    private int v; // 顶点的个数
    private LinkedList<Integer> adj[];// 邻接表

    public Graph(int v){
        this.v = v;
        adj = new LinkedList[v];
        for(int i= 0;i<v;++i){
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int s,int t){
        //s 先于 t,边 s->t
        adj[s].add(t);
    }
}

