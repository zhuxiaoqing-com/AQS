package com.zzzincrPb.server;

import com.pb.*;

import java.util.List;

/**
 * @author zhuxiaoqing
 * @Description: DeltaBuilder
 * @Date 2025/10/11 10:41
 **/
public class MsgBuilder {
    private int msgId;
    List<DeltaBuilder> deltaBuilder;

    public MsgBuilder(int msgId, List<DeltaBuilder> deltaBuilder) {
        this.msgId = msgId;
        this.deltaBuilder = deltaBuilder;
    }


    public MsgDeltaField build() {
        MsgDeltaField.Builder builder = MsgDeltaField.newBuilder();
        builder.setMsgId(msgId);
        for (DeltaBuilder deltaBuilder1 : deltaBuilder) {
            builder.addDelta(deltaBuilder1.build());
        }
        return builder.build();
    }


}