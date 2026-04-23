package com.example.rpc.client;

import com.alibaba.fastjson.JSON;

import java.nio.charset.StandardCharsets;

public final class JsonPayload {
    private JsonPayload() {
    }

    public static byte[] encode(Object value) {
        if (value == null) {
            return new byte[0];
        }
        return JSON.toJSONString(value).getBytes(StandardCharsets.UTF_8);
    }

    public static <T> T decode(byte[] payload, Class<T> type) {
        if (payload == null || payload.length == 0) {
            return null;
        }
        String json = new String(payload, StandardCharsets.UTF_8);
        return JSON.parseObject(json, type);
    }
}
