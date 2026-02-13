package com.mdb;

import com.mdb.exception.MdbError;

import java.util.concurrent.atomic.AtomicLong;

public abstract class XBean {
    private transient AtomicLong version = new AtomicLong(0); // 来个Long 省的每次checkpoint的时候都有变化,导致没法重置为0,integer溢出；

    private transient XBean _parent;

    public XBean() {
      makeModify();
    }

    public XBean(XBean _parent) {
        this._parent = _parent;
        makeModify();
    }


    /**
     * 谁变化就谁调用该方法
     */
    public void makeModify() {
        long checkPointCount = Mdb.getInstance().getCheckPointCount();
        parentMakeModify(checkPointCount);
      /*  setVersion(checkPointCount);
        if (_parent != null && _parent != this) {
            _parent.parentMakeModify(checkPointCount);
        }
        if (_parent == null) {
            // 设置新版本号
            version.set(version.get() + 1);
        }*/

    }

    private void parentMakeModify(long checkPointCount) {
        if (_parent != null && _parent != this) {
            _parent.parentMakeModify(checkPointCount);
        }
        if(_parent == null) {
            // 设置新版本号
            version.incrementAndGet();
        }
    }


    public boolean checkModify(long checkVersion) {
        if (version.get() > 0) {
            return true;
        }
        /*if (checkVersion(checkVersion)) {
            return true;
        }*/
        if(Mdb.getInstance().isxBeanDetailCheck()) {
            boolean fieldAndModify = checkFieldVersion(checkVersion);
            if (fieldAndModify) {
                Mdb.logger.error("top checkFieldVersion class {} ", getClass().getName());
                return true;
            }
        }
        return false;
    }




    public void setParent(XBean parent) {
        if (_parent != null && parent != null && _parent != parent) {
            throw new MdbError("MdbBean already has a parent _oldParentName : " + _parent.getClass().getName() + "newParentName : " + parent.getClass().getName());
        }
        this._parent = parent;
    }

    public XBean findParent() {
        return _parent;
    }

    public AtomicLong findVersionAtomic() {
        return version;
    }

    /**
     * 继承version
     */
    public void inheritVersion(AtomicLong version) {
        this.version = version;
    }

    /**
     *  废弃 xbean_2的方法
     */
    public boolean checkFieldVersion(long checkPointCount){
        return false;
    }


    /**
     * 废弃 xbean_2的方法
     */
    @Deprecated
    public boolean checkVersion(long checkPointCount) {
        return false;
    }

    /**
     * 废弃 xbean_2的方法
     */
    @Deprecated
    public long findVersion(long checkPointCount) {
        return 0;
    }
}
