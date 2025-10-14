package com.zzzincrPb.server;

import com.pb.PBFieldPath;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhuxiaoqing
 * @Description: PBFieldPathListBuilder
 * @Date 2025/10/11 11:28
 **/
public class FieldPathList {
    private final List<PBFieldPath> paths = new ArrayList<>();
    private PBFieldPath.Builder current;

    public static FieldPathList newBuilder(PBFieldPath.FieldType type) {
        return new FieldPathList(type);
    }

    private FieldPathList(PBFieldPath.FieldType type) {
        current = PBFieldPath.newBuilder().setType(type);
    }

    // 设置字段索引
    public FieldPathList field(int index) {
        current.setFieldIndex(index);
        return this;
    }

    // 添加 list 下标
    public FieldPathList list(int index) {
        if(current.getType() != PBFieldPath.FieldType.LIST) {
            throw new IllegalStateException("not list ");
        }
        current.addListIndex(index);
        return this;
    }

    // 添加 map key
    public FieldPathList map(String key) {
        if (current.getType() != PBFieldPath.FieldType.MAP) {
            // 报错
            throw new IllegalStateException("not map");
        }
        current.addMapKey(key);
        return this;
    }


    // 提交当前 PBFieldPath，开始下一条
    public FieldPathList next(PBFieldPath.FieldType type) {
        paths.add(current.build());
        current = PBFieldPath.newBuilder().setType(type);
        return this;
    }

    // 构建最终列表
    public List<PBFieldPath> build() {
        // 如果当前还没提交，就提交一次
        if (current != null) {
            paths.add(current.build());
            current = null;
        }
        return paths;
    }

    public static void main(String[] args) {
        FieldPathList score = FieldPathList
                .newBuilder(PBFieldPath.FieldType.MAP).field(1).map("score")
                .next(PBFieldPath.FieldType.FIELD).field(3);
    }
}

