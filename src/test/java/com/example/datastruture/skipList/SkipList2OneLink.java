package com.example.datastruture.skipList;

import java.util.Random;

public class SkipList2OneLink {
    public int level = 0;
    public SkipListNode top = null;
    private int initLevel;
    private int nodes;

    public SkipList2OneLink() {
        this(4);
    }

    // 跳跃表的初始化
    public SkipList2OneLink(int level) {
        this.initLevel = level;
        this.level = level;
        SkipListNode skipListNode = null;
        SkipListNode temp = top;
        SkipListNode tempDown = null;
        SkipListNode tempNextDown = null;
        int tempLevel = level;

        while (tempLevel-- != 0) {
            /**
             * 从第一层开始初始化
             */
            skipListNode = createNode(Integer.MIN_VALUE);
            temp = skipListNode;
            skipListNode = createNode(Integer.MAX_VALUE);
            temp.setNext(skipListNode);

            temp.setDownNext(tempDown);
            temp.getNext().setDownNext(tempNextDown);

            // 将这一层的数据保存起来给上一层使用
            tempDown = temp;
            tempNextDown = temp.getNext();
        }
        top = temp;
    }

    // 创建一个节点
    private SkipListNode createNode(int value) {
        SkipListNode node = new SkipListNode();
        node.setValue(value);
        return node;
    }

    //随机产生数k，k层下的都需要将值插入
    public int randomLevel() {
        int k = 1;
        while (new Random().nextInt() % 2 == 0) {
            k++;
        }
        return k;
    }


    /**
     * 查找
     */
    public SkipListNode find(int value) {
        SkipListNode skipListNode = doFind(value);
        if (skipListNode.getValue() == value) {
            return skipListNode;
        }
        return null;
    }

    /**
     * 查找
     */
    private SkipListNode doFind(int value) {
        SkipListNode node = this.top;

        while (true) {
            while (node.getNext() != null & node.getNext().getValue() <= value) {
                node = node.getNext();
            }
            if (node.getDownNext() != null) {
                node = node.getDownNext();
            } else {
                return node;
            }
        }
    }


    /**
     * 插入一个节点
     */
    public void insert(int value) {
        SkipListNode skipListNode1 = doFind(value);
        if (skipListNode1.getValue() == value) {
            return;
        }
        SkipListNode skipListNode = null;
        int k = randomLevel();
        SkipListNode temp = this.top;
        int tempLevel = level;
        SkipListNode tempNode = null;
        /*
         当在第 n 行插入后，在第 n - 1 行插入时需要将
         第n行 backTempNode 的 DownNext 域指向第n - 1的域
          */
        SkipListNode backTempNode = null;

        // 扩容
        reLevel(k);

        int flag = 1;
        while (tempLevel-- != k) {
            temp = temp.getDownNext();
        }

        tempLevel++;
        tempNode = temp;

        // 小于 k 层的都需要进行插入
        while (tempLevel-- != 0) {
            // 在第 k 层寻找要插入的位置
            while (tempNode.getNext().getValue() < value) {
                tempNode = tempNode.getNext();
            }
            skipListNode = createNode(value);
            // 如果是顶层
            if (flag != 1) {
                backTempNode.setDownNext(skipListNode);
            }
            backTempNode = skipListNode;
            skipListNode.setNext(tempNode.getNext());
            tempNode.setNext(skipListNode);
            flag = 0;
            tempNode = tempNode.getDownNext();
        }
    }

    /**
     * 插入一个节点
     */
    public void insert02(int value) {
        SkipListNode skipListNode1 = doFind(value);
        if (skipListNode1.getValue() == value) {
            return;
        }
        nodes++;

        int k = randomLevel();
        // 扩容
        reLevel(k);
        SkipListNode skipListNode = null;
        // 当前层的 head
        SkipListNode tempHeadNode = top;
        // 即将要插入的数据
        SkipListNode tempDownNode = null;

        // 检查 k 的高度 让其到达要插入数据的高度
        int tempLevel = level;
        while (tempLevel != k) {
            tempHeadNode = tempHeadNode.getDownNext();
            tempLevel--;
        }
        // 顶层不需要被设置为 down
        int flag = 1;
        // 小于 k 层的都需要进行插入 第一行不插入
        while (tempLevel-- != 0) {
            while (tempHeadNode.getNext().getValue() < value) {
                tempHeadNode = tempHeadNode.getNext();
            }
            // 如果是第一次需要插入数据 key - value
            if (tempLevel == 1) {
                skipListNode = createNode(value);
            } else {
                skipListNode = createNode(value);
            }
            //如果是顶层
            if(flag != 1) {
                tempDownNode.setDownNext(skipListNode);
            }
            skipListNode.setNext(tempHeadNode.getNext());
            tempHeadNode.setNext(skipListNode);
            tempDownNode = skipListNode;
            flag = 0;
            tempHeadNode = tempHeadNode.getDownNext();
        }
    }

    /**
     * 扩容
     *
     * @param k 随机到的层
     */
    private void reLevel(int k) {
        if (k > level) {
            SkipListNode skipListNode = null;
            SkipListNode downNode = top;
            SkipListNode whileNode = downNode;
            // 找到最边上的那个
            while (whileNode.getNext() != null) {
                whileNode = whileNode.getNext();
            }
            SkipListNode nestDownNode = whileNode;
            SkipListNode temp = top;


            int i = k - level;
            while (i-- > 0) {
                skipListNode = createNode(Integer.MIN_VALUE);
                temp = skipListNode;
                skipListNode = createNode(Integer.MAX_VALUE);
                temp.setNext(skipListNode);
                temp.setDownNext(downNode);
                skipListNode.setDownNext(nestDownNode);
                downNode = temp;
                nestDownNode = skipListNode;
            }
            top = temp;
            level = k;
        }
    }


    /**
     * 删除
     */
    public int remove(int value) {
        SkipListNode skipListNode = this.top;
        SkipListNode flag = null;
       /* while (tempLevel-- != 0) {
            while (temp.getNext().getValue() < value) {
                temp = temp.getNext();
            }
            if (temp.getNext().getValue() == value) {
                temp.setNext(temp.getNext().getNext());
                flag = temp.getNext();
            }
            temp = skipListNode.getDownNext();
        }*/


        while (true) {
            while (skipListNode.getNext() != null && skipListNode.getNext().getValue() < value) {
                skipListNode = skipListNode.getNext();
            }
            if(skipListNode.getNext().getValue() == value) {
                flag = skipListNode.getNext();
                skipListNode.setNext(skipListNode.getNext().getNext());
            }
            if(skipListNode.getDownNext() != null) {
                skipListNode = skipListNode.downNext;
            } else {
                break;
            }
        }

        // 降高度
        while (level > initLevel && top.getDownNext().getNext().getNext() == null) {
            top = top.getDownNext();
            level--;
        }

        if(flag != null) {
            nodes --;
            return flag.getValue();
        }
        return -1;
    }

    public int size() {
        return this.nodes;
    }

    //////////////////////////////////////////

    public class SkipListNode implements Comparable {
        private int value;
        private SkipListNode next = null;
        // 下一个 down
        private SkipListNode downNext = null;

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.printf("123");
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public SkipListNode getNext() {
            return next;
        }

        public void setNext(SkipListNode next) {
            this.next = next;
        }

        public SkipListNode getDownNext() {
            return downNext;
        }

        public void setDownNext(SkipListNode downNext) {
            this.downNext = downNext;
        }

        @Override
        public int compareTo(Object o) {
            return this.value > ((SkipListNode) o).value ? 1 : -1;
        }
    }
}
