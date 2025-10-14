package com.zzzincrPb.server;

import com.google.protobuf.AbstractMessage;
import com.google.protobuf.ByteString;
import com.pb.*;

/**
 * @author zhuxiaoqing
 * @Description: DeltaBuilder
 * @Date 2025/10/11 10:41
 **/
public class DeltaBuilder {
    private  FieldPathList fieldPath;
    PBFieldModify.Builder modify;

    public DeltaBuilder( FieldPathList fieldPath, PBFieldModify.Builder builder) {
        this.fieldPath = fieldPath;
        this.modify = builder;
    }


    public DeltaBuilder setField(Object value) {
        checkType(PBFieldModify.ModifyType.FIELD);
        modify.setValue(toPBFieldValue(value));
        return this;
    }


    public DeltaBuilder listAdd(Object value) {
        checkType(PBFieldModify.ModifyType.LIST);
        PBListOperation op = PBListOperation.newBuilder()
                .setOp(PBListOperation.ListOpType.ADD)
                .setValue(toPBFieldValue(value))
                .build();
        modify.addListOps(op);
        return this;
    }



    public DeltaBuilder listSet(int index, Object value) {
        checkType(PBFieldModify.ModifyType.LIST);
        PBListOperation op = PBListOperation.newBuilder()
                .setOp(PBListOperation.ListOpType.SET)
                .setIndex(index)
                .setValue(toPBFieldValue(value))
                .build();
        modify.addListOps(op);
        return this;
    }

    public DeltaBuilder listRemove(int index) {
        checkType(PBFieldModify.ModifyType.LIST);
        PBListOperation op = PBListOperation.newBuilder()
                .setOp(PBListOperation.ListOpType.REMOVE)
                .setIndex(index)
                .build();
        modify.addListOps(op);
        return this;
    }

    public DeltaBuilder listClear() {
        checkType(PBFieldModify.ModifyType.LIST);
        PBListOperation op = PBListOperation.newBuilder()
                .setOp(PBListOperation.ListOpType.CLEAR)
                .build();
        modify.addListOps(op);
        return this;
    }


    public DeltaBuilder mapPut(Object key, Object value) {
        checkType(PBFieldModify.ModifyType.MAP);
        PBMapOperation op = PBMapOperation.newBuilder()
                .setOp(PBMapOperation.MapOpType.PUT)
                .setKey(toPBFieldValue(key))
                .setValue(toPBFieldValue(value))
                .build();
        modify.addMapOps(op);
        return this;
    }

    public DeltaBuilder mapRemove(Object key) {
        checkType(PBFieldModify.ModifyType.MAP);
        PBMapOperation op = PBMapOperation.newBuilder()
                .setOp(PBMapOperation.MapOpType.REMOVE)
                .setKey(toPBFieldValue(key))
                .build();
        modify.addMapOps(op);
        return this;
    }

    public DeltaBuilder mapClear() {
        checkType(PBFieldModify.ModifyType.MAP);
        PBMapOperation op = PBMapOperation.newBuilder()
                .setOp(PBMapOperation.MapOpType.CLEAR)
                .build();
        modify.addMapOps(op);
        return this;
    }

    public DeltaField build() {
        DeltaField.Builder builder = DeltaField.newBuilder();
        builder.addAllFieldPath(fieldPath.build());
        builder.setModify(modify.build());
        return builder.build();
    }




    private void checkType(PBFieldModify.ModifyType expected) {
        if (modify.getType() != expected) {
            throw new IllegalStateException("Modify type mismatch. Expected " + expected + " but got " + modify.getType());
        }
    }

    private PBFieldValue toPBFieldValue(Object value) {
        PBFieldValue.Builder builder = PBFieldValue.newBuilder();
        if (value instanceof Integer) {
            builder.setType(PBFieldValue.PBFieldType.PB_INT32)
                    .setInt32Value(((Number) value).intValue());
        }  else if (value instanceof Long) {
            builder.setType(PBFieldValue.PBFieldType.PB_INT64)
                    .setInt64Value(((Number) value).longValue());
        } else if (value instanceof Float) {
            builder.setType(PBFieldValue.PBFieldType.PB_FLOAT)
                    .setFloatValue(((Number) value).floatValue());
        } else if (value instanceof Double) {
            builder.setType(PBFieldValue.PBFieldType.PB_DOUBLE)
                    .setDoubleValue(((Number) value).doubleValue());
        } else if (value instanceof String) {
            builder.setType(PBFieldValue.PBFieldType.PB_STRING)
                    .setStringValue((String) value);
        } else if (value instanceof Boolean) {
            builder.setType(PBFieldValue.PBFieldType.PB_BOOL)
                    .setBoolValue((Boolean) value);
        } else if (value instanceof byte[]) {
            builder.setType(PBFieldValue.PBFieldType.PB_BYTES)
                    .setBytesValue(ByteString.copyFrom((byte[]) value));
        } else if (value instanceof AbstractMessage) { // 任何 protobuf message
            builder.setType(PBFieldValue.PBFieldType.PB_MESSAGE)
                    .setBytesValue(((AbstractMessage) value).toByteString());
        } else {
            throw new IllegalArgumentException("Unsupported type: " + value.getClass());
        }
        return builder.build();
    }


    public static void main(String[] args) {
        FieldPathList score = FieldPathList
                .newBuilder(PBFieldPath.FieldType.MAP).field(1).map("score")
                .next(PBFieldPath.FieldType.FIELD).field(3);
        DeltaField delta = new DeltaBuilder(score, PBFieldModify.newBuilder().setType(PBFieldModify.ModifyType.LIST))
                .listAdd(123)
                .listSet(0, 456)
                .listRemove(2)
                .listClear()
                .build();

        DeltaField deltaField = new DeltaBuilder(score, PBFieldModify.newBuilder().setType(PBFieldModify.ModifyType.FIELD))
                .setField("hello world")
                .build();

        DeltaField deltaMap = new DeltaBuilder(score, PBFieldModify.newBuilder().setType(PBFieldModify.ModifyType.MAP))
                .mapPut("key1", 123)
                .mapRemove("key2")
                .mapClear()
                .build();

    }
}