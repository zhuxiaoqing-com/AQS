package com.zzzincrPb;

import com.pb.*;
import com.zzzincrPb.client.DeltaListenerManager;
import com.zzzincrPb.server.DeltaBuilder;
import com.zzzincrPb.server.FieldPathList;
import com.zzzincrPb.server.MsgBuilder;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author zhuxiaoqing
 * @Description: Test
 * @Date 2025/10/10 13:37
 **/
public class Test {
    public static void main(String[] args) {
        PBTestMapValue2 build = PBTestMapValue2.newBuilder().setA1(1).setA2(2).build();
        PBTestMapValue build1 = PBTestMapValue.newBuilder().setV(build).setA2(2).build();
        PBTest1 pbTest1 = PBTest1.newBuilder()
                .putMapInt32Obj(32, build1)
                .putMapStrObj("str", build1)
                .putMapInt64Obj(64000_000_000_000_000L, build1)
                .putMapInt32Int(32, 32)
                .putMapStrInt("str", 32)
                .putMapInt64Int(64, "str")
                .addList(1)
                .addV(build1)
                .setString("str")
                .build();

        PBTest1.Builder builder = pbTest1.toBuilder();
        DeltaListenerManager manager = new DeltaListenerManager();
// 注册获取对象方法
        manager.addGetDataFunction(1001, () -> builder);

// 监听 Item 的变化
        manager.addListen(1001, Arrays.asList(PBTest1.MAPINT32OBJ_FIELD_NUMBER, PBTestMapValue.A2_FIELD_NUMBER), msg -> {
            System.out.println("Arrays.asList(PBTest1.MAPINT32OBJ_FIELD_NUMBER, PBTestMapValue.A2_FIELD_NUMBER) " );
        });

        manager.addListen(1001, Arrays.asList(PBTest1.MAPINT32OBJ_FIELD_NUMBER), msg -> {
            System.out.println("Arrays.asList(PBTest1.MAPINT32OBJ_FIELD_NUMBER) " );
        });




        FieldPathList path = FieldPathList
                .newBuilder(PBFieldPath.FieldType.MAP).field(PBTest1.MAPINT32OBJ_FIELD_NUMBER);
        DeltaBuilder deltaBuilder = new DeltaBuilder(path, PBFieldModify.newBuilder().setType(PBFieldModify.ModifyType.MAP))
                .mapPut(33, build1.toBuilder().setA2(222222).build());

        MsgBuilder msgBuilder = new MsgBuilder(1001, Collections.singletonList(deltaBuilder));


        manager.handleDelta(msgBuilder.build());

    }
}
