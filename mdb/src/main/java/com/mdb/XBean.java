package com.mdb;

import com.mdb.exception.MdbError;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public abstract class XBean {
    /**
     * 版本号 用来替代modify
     *
     * 每次checkpoint将版本号+1; 版本号怎么弄呢? checkpoint可以统一共用每次开始的时候将 版本号+1;
     * 然后sleep(1000) 将等待异步全部修改完成,防止我再执行清理的时候,有线程异步版本号还没设置进去导致出问题;
     * 然后对比版本号 版本号>=之前的就设置,防止有数据很久没同步,如果就==的时候设置的话,会有数据每次都在checkpoint临界值的时候修改,
     *     导致一直没有同步到数据库;
     */
    private long version0;
    private long version1;
    private AtomicLong version; // 来个Long 省的每次checkpoint的时候都有变化,导致没法重置为0,integer溢出；

    private XBean _parent;

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
        setVersion(checkPointCount);
        if (_parent != null && _parent != this) {
            _parent.parentMakeModify(checkPointCount);
        }
        if(_parent == null) {
            // 设置新版本号
            version.set(version.get() + 1);
        }
    }

    public void setVersion(long checkPointCount) {
        if ((checkPointCount & 1) == 0) {
            version0 = checkPointCount;
        }
        if ((checkPointCount & 1) == 1) {
            version1 = checkPointCount;
        }
    }

    public boolean checkVersion(long checkPointCount) {
        if ((checkPointCount & 1) == 0) {
            return version0 >= checkPointCount;
        }
        if ((checkPointCount & 1) == 1) {
            return version1 >= checkPointCount;
        }
        // 应该不可能走到这一步
        return false;
    }

    public long getVersion(long checkPointCount) {
        if ((checkPointCount & 1) == 0) {
           return version0;
        }
        if ((checkPointCount & 1) == 1) {
            return version1;
        }
        // 应该不可能走到这一步
        return 0;
    }


    public boolean isModify(long checkVersion) {
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



    /**
     * 应该先检测子类的是不是有修改过的  如果有的话就返回true 并且报错, 都没有就继续调用子类的checkFieldVersion
     * 例子：
     *      if(child.checkVersion(checkPointCount){
                Mdb.logger.error("checkFieldVersion checkVersion true checkPointCount {} currVersion {} class {} ",
                    checkPointCount,getVersion(checkPointCount), getClass().getName(), new RuntimeException());
     *          return true;
     *      }
     *      if(child.checkFieldVersion(checkVersion)){
     *          return true;
     *      }
     *
     *      return false;
     *
     * 这里检测有点问题;因为没有用双modify,
     *
     * @return 有field的版本号>=checkVersion return true  否则返回false
     */
    public abstract boolean checkFieldVersion(long checkPointCount);

    public void setParent(XBean parent) {
        if (_parent != null && _parent != parent) {
            throw new MdbError("MdbBean already has a parent _oldParentName : " + _parent.getClass().getName() + "newParentName : " + parent.getClass().getName());
        }
        this._parent = parent;
    }

    public XBean getParent() {
        return _parent;
    }


    public AtomicLong getVersion() {
        return version;
    }
}
