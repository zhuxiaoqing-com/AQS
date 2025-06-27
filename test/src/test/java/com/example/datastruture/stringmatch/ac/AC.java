package com.example.datastruture.stringmatch.ac;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class AC {
    AcNode root = new AcNode('/');

    /**
     * 绑定失败指针
     */
    public void buildFailurePointer() {
        Queue<AcNode> queue = new LinkedList<>();
        root.fail = null; // 根节点的失败节点为 null;
        /**
         * 加 queue 里面为每一个节点构造失败节点
         * 构造过的就删除了。
         */
        queue.add(root);
        while (!queue.isEmpty()) {
            AcNode p = queue.remove();
            HashMap<Character, AcNode> children = p.children;
            Collection<AcNode> values = children.values();
            for (AcNode acNode : values) {
                /**
                 * 如果 p 也就是 父节点是 root,因为第一层的失败节点只能是上一层。从 root 开始查找
                 */
                if (p == root) {
                    acNode.fail = root;
                } else {
                    AcNode fail = p.fail;
                    /**
                     * fail != root 是错误的
                     * 因为 失败节点是 root 就结束的话，第一层的还没有搜索.
                     * 我们需要root.children 搜索有没有相同的有的话就将失败节点置为第一层
                     */
                    //while (fail != root) {
                    while (fail != null) {
                        if (fail.children.get(acNode) != null) {
                            acNode.fail = fail;
                            break;
                        }
                        fail = acNode.fail;
                    }
                    if (fail == null) {
                        acNode.fail = root;
                    }
                }
                queue.add(acNode);
            }
        }
    }

    /**
     * 匹配 str 是主串
     */
    public boolean match(char[] str) {
        int n = str.length;
        AcNode p = root;
        for (int i = 0; i < str.length; i++) {
            char c = str[i];
            while (p.children.get(c) == null && p != root) {
                p = p.fail;
            }
            p = p.children.get(c);
            if (p == null) {
                return false;
            }
        }
        return p.isEndingChar;
    }


    /**
     * 匹配 str 是主串
     */
    public void match01(char[] str) {
        int n = str.length;
        AcNode p = root;
        for (int i = 0; i < str.length; i++) {
            char c = str[i];
            while (p.children.get(c) == null && p != root) {
                p = p.fail;
            }
            p = p.children.get(c);
            // 如果没有匹配的，从 root 开始重新匹配
            if (p == null) p = root;
            AcNode tmp = p;
            while (tmp != root) {
                // 打印出可以匹配的模式串
                if (tmp.isEndingChar == true) {
                    int pos = i - tmp.length + 1;
                    System.out.println(" 匹配起始下标 " + pos + "; 长度 " + tmp.length);
                }
                tmp = tmp.fail;
            }
        }
    }


    static class AcNode {
        public char data;
        public HashMap<Character, AcNode> children = new HashMap();
        public boolean isEndingChar = false;
        public int length = -1; // 当 isEndingChar = true 时,记录模式串长度
        public AcNode fail; // 失败指针

        public AcNode(char data) {
            this.data = data;
        }
    }
}
