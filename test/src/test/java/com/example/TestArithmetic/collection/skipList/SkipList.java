package com.example.TestArithmetic.collection.skipList;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 跳跃表的节点，包括 key-value 和上下左右4个节点
 */
public class SkipList<T> {
    private SkipListNode<T> head;
    private int nodes; // 节点总数
    private int listLevel; // 层数
    private static final double PROBABILITY = 0.5;
    public static int HEAD_KEY = 0x80000000; // 负无穷
    //public static int TAIL_KEY = 0x7FFFFFFF; // 正无穷

    public SkipList() {
        clear();
    }

    /**
     * 清空跳跃表
     */
    public void clear() {
        head = new SkipListNode<T>(HEAD_KEY, null, null, null);
        listLevel = 0;
        nodes = 0;
    }

    public boolean isEmpty() {
        return nodes == 0;
    }

    public int size() {
        return nodes;
    }

    /**
     * 在最下面一层，找到要插入的位置前面的那个 key
     */
    private SkipListNode<T> findNode(int key) {
        SkipListNode<T> node = this.head;
        while (true) {
            // 如果 node.right > key 就进入 下一层
            // 如果 node.right.key <= key 不符合 说明 node <= key 因为 node <= key 会进入循环
            while (node.right != null && node.right.key <= key) {
                node = node.right;
            }
            if (node.down != null) {
                node = node.down;
            } else {
                break;
            }
        }
        return node;
       /* SkipListNode<T> n = this.head;
        while (true) {
            while (n.key != SkipListNode.TAIL_KEY && n.right.key <= key) {
                n = n.right;
            }
            if (n.down != null) {
                n = n.down;
            } else {
                break;
            }
        }
        return n;*/
    }

    /**
     * 查找是否存储 Key, 存在则返回该节点，否则返回 Null
     */
    public SkipListNode<T> search(int key) {
        SkipListNode<T> node = findNode(key);
        if (node.getKey() == key) {
            return node;
        } else {
            return null;
        }
    }


    /**
     * 向跳跃表中添加元素 key-value
     */
  /*  public void put(int k, T v) {
        // 尝试查找链表里面是否有该数据
        // 没有的话那么返回的就是即将要插入的数据的 left
        // 因为 findNode 是找到其 <= key 的值
        SkipListNode<T> p = findNode(k);
        if (p.getKey() == k) {
            p.value = v;
            return;
        }
        SkipListNode<T> q = new SkipListNode<>(k, v);
        backLink(p, q);
        int currentLevel = 0; // 当前所在的层数是 0
        // 抛硬币
        while (ThreadLocalRandom.current().nextDouble() < PROBABILITY) {
            // 如果超出了高度，需要重新建一个顶层
            if (currentLevel >= listLevel) {
                listLevel++;
                SkipListNode<T> p1 = new SkipListNode<>(HEAD_KEY, null);
                SkipListNode<T> p2 = new SkipListNode<>(TAIL_KEY, null);
                horizontalLink(p1, p2);
                verticalLink(p1, head);
                verticalLink(p2, tail);
                head = p1;
                tail = p2;
            }
            // 将 q 移动到上一层
            // 找到 q 的上一层的 Left
            while (p.up == null) {
                p = p.left;
            }
            p = p.up;

            SkipListNode<T> e = new SkipListNode<>(k, null);// 只保存 Key 就可以了
            backLink(p, e); // 将 e 插入到 p 的后面
            verticalLink(e, q); // 将 e 和 q 上下链接
            q = e;
            currentLevel++;
        }
        // 总 nodes 加 1
        nodes++;

    }*/
    public void put2(int k, T v) {
        SkipListNode<T> search = findNode(k);
        if (search.key == k) {
            search.value = v;
        }
        nodes++;

        // 插入 0 层数据
        SkipListNode<T> nowNode = new SkipListNode(k, v, search.right, null);
        search.right = nowNode;

        // 随机层数
        int randomLevel = randomLevel();
        SkipListNode<T> idx = null;
        if (randomLevel <= listLevel) {
            for (int i = 1; i <= randomLevel; i++) {
                idx = new SkipListNode<T>(k, null, idx);
            }
        } else {
            // 超过了原有层数
            SkipListNode<T>[] idxs = new SkipListNode[randomLevel + 1];
            for (int i = 1; i <= randomLevel; i++) {
                idx = idxs[i] = new SkipListNode<T>(k, idx);
            }
            for (int i = listLevel + 1; i <= randomLevel; i++) {
                head = new SkipListNode<T>(HEAD_KEY, idxs[i], head);
            }
            int temp = randomLevel;
            randomLevel = listLevel;
            listLevel = temp;
        }

        // 找到合适的地方插入

            int j = listLevel;
            SkipListNode<T> tempHead = head;
          /*  for (SkipListNode<T> t = idx, q = head, r = q.right;;) {
                if (j == randomLevel) {
                    new SkipListNode<T>(k, idx, h);
                    randomLevel--;
                }
            }*/

            for (SkipListNode<T> q = tempHead, r = q.right, t = idx;;) {
                if (q == null || t == null)
                    break;
                if (r != null) {
                    if (r.key < k) {
                        q = r;
                        r = r.right;
                        continue;
                    }
                }

                if (j == randomLevel) {
                    t.right = r;
                    q.right = t;
                    if (--randomLevel == 0)
                        break;
                }

                if (--j >= randomLevel)
                    t = t.down;
                q = q.down;
                r = q.right;
            }

    }

    private void reghtLink01(SkipListNode<T> node1, SkipListNode<T> node2) {
        node2.right = node1.right;
        node1.right = node2;
    }

    private void downLink(SkipListNode<T> node1, SkipListNode<T> node2) {
        node1.down = node2;
    }

    private int randomLevel() {
        int level = 0;
        while (ThreadLocalRandom.current().nextDouble() < PROBABILITY) {
            level++;
        }
        return level;
    }

   /* private void verticalLink(SkipListNode<T> node1, SkipListNode<T> node2) {
        node1.down = node2;
        node2.up = node1;
    }*/

    // 在 node1 后面插入 node2
    /*private void backLink(SkipListNode<T> node1, SkipListNode<T> node2) {
        node2.left = node1;
        node2.right = node1.right;
        node1.right.left = node2;
        node1.right = node2;
    }
*/
    public T remove(int key) {
        SkipListNode<T> oldNode = null;
        SkipListNode<T> headTemp = this.head;
        while (headTemp.right != null && headTemp.right.key < key) {
            // 如果该层找到了 key 将其删除
            if (headTemp.right.key == key) {
                oldNode = headTemp.right;
                reghtLink01(headTemp, headTemp.right.right);
            }
            if (headTemp.down != null) {
                headTemp = headTemp.down;
            } else {
                break;
            }
        }
        // 如果该层的下一层也没有数据的话就降层
        while (listLevel >= 1 && head.down.right == null) {
            head = head.down;
            listLevel--;
        }

        return oldNode != null ? oldNode.value : null;
    }

    /**
     * 打印出原始数据
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "跳跃表为空！";
        }
        StringBuilder builder = new StringBuilder();
        SkipListNode<T> p = this.head;
        while (p.down != null) {
            p = p.down;
        }

        // 排除 public static int HEAD_KEY = 0x80000000; // 负无穷
        if (p.right != null) {
            p = p.right;
        }
        // 排除 public static int TAIL_KEY = 0x7FFFFFFF; // 正无穷
        while (p != null) {
            builder.append(p);
            builder.append("\n");
            p = p.right;
        }
        return builder.toString();
    }


}











