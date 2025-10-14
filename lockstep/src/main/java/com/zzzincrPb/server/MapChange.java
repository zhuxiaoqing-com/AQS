package com.zzzincrPb.server;

import com.pb.PBFieldValue;

/**
 * @author zhuxiaoqing
 * @Description: MapChange
 * @Date 2025/10/11 10:47
 **/
public class MapChange {
    private final int op;
    private final PBFieldValue key;
    private final PBFieldValue value;

    public MapChange(int op, PBFieldValue key, PBFieldValue value) {
        this.op = op;
        this.key = key;
        this.value = value;
    }

    public int getOp() {
        return op;
    }

    public PBFieldValue getKey() {
        return key;
    }

    public PBFieldValue getValue() {
        return value;
    }
}
