package com.zzzincrPb.server;

import com.pb.PBFieldValue;

/**
 * @author zhuxiaoqing
 * @Description: ListChange
 * @Date 2025/10/11 10:46
 **/
public class ListChange {
    private final int op;
    private final PBFieldValue value;

    public ListChange(int op, PBFieldValue value) {
        this.op = op;
        this.value = value;
    }

    public int getOp() {
        return op;
    }

    public PBFieldValue getValue() {
        return value;
    }
}
