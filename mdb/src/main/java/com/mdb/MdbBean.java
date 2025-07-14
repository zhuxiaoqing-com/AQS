package com.mdb;

public class MdbBean {
    public volatile boolean modify;

    private MdbBean _parent;

    public MdbBean() {
        modify = true;
    }

    public MdbBean(MdbBean _parent) {
        this._parent = _parent;
        makeModify();
    }

    /**
     * 谁变化就谁调用该方法
     */
    public void makeModify() {
        modify = true;
        if (_parent != null && _parent != this) {
            _parent.makeModify();
        }
    }

    public void setParent(MdbBean parent) {
        this._parent = parent;
    }
}
