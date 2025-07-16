package com.mdb;

import com.mdb.exception.MdbError;

public abstract class XBean_modify {
    private boolean modifyTrue;// 自己以及子类变化
    private boolean modifyFalse;

    /**
     * 在 Java 中，当一个字段被声明为 volatile 时，它会提供以下内存可见性保证：
     * <p>
     * ‌写入可见性保证‌：
     * <p>
     * 当一个线程写入 volatile 变量时，该写入操作之前的所有其他内存修改（包括非 volatile 字段）都会‌强制刷新到主内存‌
     * JVM 会确保在 volatile 写入完成前，所有先前的操作结果对其他线程可见
     * ‌读取可见性保证‌：
     * <p>
     * 当一个线程读取 volatile 变量时，它会‌刷新整个线程内存视图‌，看到所有其他线程最新写入的 volatile 变量值
     * 同时也能看到写入该 volatile 变量之前的所有其他内存修改
     * ‌指令重排序限制‌：
     * <p>
     * JVM 不得将写入 volatile 变量之前的操作重排序到写入之后
     * 不得将读取 volatile 变量之后的操作重排序到读取之前
     */
    private XBean_modify _parent;

    public XBean_modify() {
        modifyTrue = true;
        modifyFalse = true;
        version = Mdb.getInstance().getCheckPointCount();
    }

    public XBean_modify(XBean_modify _parent) {
        this._parent = _parent;
        makeModify();
    }


    /**
     * 谁变化就谁调用该方法
     */
    public void makeModify() {
        boolean isModifyTrue = Mdb.getInstance().isModifyTrue();
        if (_parent != null && _parent != this) {
            _parent.parentMakeModify(isModifyTrue);
        }
        setModifyValue(isModifyTrue, true);
    }

    private void parentMakeModify(boolean isModifyTrue) {
        if (_parent != null && _parent != this) {
            _parent.parentMakeModify(isModifyTrue);
        }
        setModifyValue(isModifyTrue, true);
    }

    private void setModifyValue(boolean isModifyTrue, boolean modifyTrue) {
        if (isModifyTrue) {
            this.modifyTrue = modifyTrue;
        } else {
            this.modifyFalse = modifyTrue;
        }
    }

    public boolean getModifyTrue(boolean isModifyTrue) {
        if (isModifyTrue) {
            return this.modifyTrue;
        } else {
            return this.modifyFalse;
        }
    }


    /**
     * clear 这里是不是可以stw一下 加个锁;
     * 有可能 我这边再清理 业务线程在设置;
     * 可能会导致modify对不上;比如parent的modify被清理了,但是child的modify又被设置了,  用两个modify?
     * 有一个地方做开关;判断以后就进行清理新的Modify,然后设置切换modify
     * 后面设置的modify全部是设置在新的modify,就不会有问题了
     * <p>
     * checkpoint 流程：清理老的modify; 切换;  等待1s让业务逻辑都设置完毕;进行modify检测
     *     还有一种流程  切换; 找到老的modify然后进行清理; 序列化; 同步到数据库; 这样有个问题就是_modify会乱掉;
     * flush： 直接进行modify检测, 然后删除
     * <p>
     * 为什么要等待1s,如果不等待的话,可能另一个线程马上要设置modify,然后我切换modify了 导致我设置的那个modify失效,就丢数据了
     */
    public void clearModify(boolean isModifyTrue) {
        setModifyValue(isModifyTrue, false);
        // 这里还要把所有的field都清理下 这里很麻烦 额
    }

    public boolean only_isModify() {
        boolean isModifyTrue = Mdb.getInstance().isModifyTrue();
        return getModifyTrue(isModifyTrue);
    }

    public boolean isModify() {
        if(only_isModify()) {
            return true;
        }
        if(Mdb.getInstance().isxBeanDetailCheck()) {
            boolean fieldAndModify = fieldAnyModify();
            if (fieldAndModify) {
                Mdb.logger.error("parentModify false fieldModify true class {} ", getClass().getName());
                return true;
            }
        }
        return false;
    }


    /**
     * 当前字段是否有modify的
     * <p>
     * 想有一个这个功能 不开的话,默认就比较顶级父类的 modify;
     * 开启以后可以判断modify传播是否有问题，_parent设置是否有问题;
     * <p>
     * 判断modify传播是否有问题
     * 一种是父类有变化,子类没变化
     * 父类没变化,子类有变化
     * <p>
     * 判断_parent设置的是否有问题;
     * <p>
     * 而且还有一种可能就是基础字段(int,long)还没变化的时候,modify字段就已经变换了;这个在多线程是有可能的,
     * 这个时候modify被清除了,但是字段变化没有被同步到数据库怎么办,
     * 解决方案   这个问题理解volatile就解决了,因为在写入和读取被volatile字段修饰的filed的时候会加上写屏障和读屏障;
     */
    public abstract boolean fieldAnyModify();

    public void setParent(XBean_modify parent) {
        if (_parent != null && _parent != parent) {
            throw new MdbError("MdbBean already has a parent _oldParentName : " + _parent.getClass().getName() + "newParentName : " + parent.getClass().getName());
        }
        this._parent = parent;
    }

    public XBean_modify getParent() {
        return _parent;
    }


    /**
     * 版本号 用来替代modify
     *
     * 每次checkpoint将版本号+1; 版本号怎么弄呢? checkpoint可以统一共用每次开始的时候将 版本号+1;
     * 然后sleep(1000) 将等待异步全部修改完成,防止我再执行清理的时候,有线程异步版本号还没设置进去导致出问题;
     * 然后对比版本号 版本号>=之前的就设置,防止有数据很久没同步,如果就==的时候设置的话,会有数据每次都在checkpoint临界值的时候修改,
     *     导致一直没有同步到数据库;
     */
    private volatile long version;
    


}
