package com.mdb.bean;

public enum ModifyType {
    ADD(Object.class),
    REMOVE(Object.class),
    REORDER(Void.class);

    public final Class<?> valueType;
    ModifyType(Class<?> valueType) {
        this.valueType = valueType;
    }
}
