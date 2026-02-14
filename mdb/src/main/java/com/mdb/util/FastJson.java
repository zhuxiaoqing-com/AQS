package com.mdb.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.mdb.collection.ConcurrentArrayDequeX;
import com.mdb.collection.ConcurrentArrayListX;
import com.mdb.collection.ConcurrentCopyAndWriteListX;
import com.mdb.collection.TrackedList;
import com.mdb.collection.TrackedLockList;
import com.mdb.collection.TrackedQueue;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class FastJson {
    private static final SerializeConfig SERIALIZE_CONFIG = new SerializeConfig();

    private static final ObjectSerializer TRACKED_COLLECTION_SNAPSHOT_SERIALIZER = new ObjectSerializer() {
        @Override
        public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
            if (object == null) {
                serializer.writeNull();
                return;
            }
            if (object instanceof Collection) {
                serializer.write(new ArrayList<>((Collection<?>) object));
                return;
            }
            serializer.write(object);
        }
    };

    static {
        SerializeConfig config = SERIALIZE_CONFIG;
        config.setAsmEnable(false);
        config.put(Collection.class, TRACKED_COLLECTION_SNAPSHOT_SERIALIZER);
        config.put(List.class, TRACKED_COLLECTION_SNAPSHOT_SERIALIZER);
        config.put(Queue.class, TRACKED_COLLECTION_SNAPSHOT_SERIALIZER);

        config.put(TrackedList.class, TRACKED_COLLECTION_SNAPSHOT_SERIALIZER);
        config.put(TrackedLockList.class, TRACKED_COLLECTION_SNAPSHOT_SERIALIZER);
        config.put(TrackedQueue.class, TRACKED_COLLECTION_SNAPSHOT_SERIALIZER);

        // Keep concrete registrations to avoid any writer-cache edge cases.
        config.put(ConcurrentCopyAndWriteListX.class, TRACKED_COLLECTION_SNAPSHOT_SERIALIZER);
        config.put(ConcurrentArrayListX.class, TRACKED_COLLECTION_SNAPSHOT_SERIALIZER);
        config.put(ConcurrentArrayDequeX.class, TRACKED_COLLECTION_SNAPSHOT_SERIALIZER);
    }

    public static <T> T parseObject(Object text, Class<T> clazz) {
        if (text == null) {
            return null;
        }
        if (text.toString().length() == 0) {
            return null;
        }
        return JSON.parseObject(text.toString(), clazz);
    }

    @SuppressWarnings("unchecked")
    public static String toJSONString(Object object) {
        PropertyFilter profilter = new PropertyFilter() {
            @Override
            public boolean apply(Object object, String name, Object param) {
                if (object instanceof Map) {
                    return true;
                }
                if (param instanceof Integer) {
                    return ((Integer) param).intValue() != 0;
                } else if (param instanceof Double) {
                    return ((Double) param).doubleValue() != 0;
                } else if (param instanceof Float) {
                    return ((Float) param).floatValue() != 0;
                } else if (param instanceof Long) {
                    return ((Long) param).longValue() != 0;
                } else if (param instanceof Collection) {
                    return !((Collection<?>) param).isEmpty();
                } else if (param instanceof Map) {
                    Map<Object, Object> map = (Map<Object, Object>) param;
                    return !map.isEmpty();
                } else if (param instanceof Boolean) {
                    return ((Boolean) param).booleanValue();
                }
                return true;
            }
        };
        return JSON.toJSONString(object, SERIALIZE_CONFIG, profilter, SerializerFeature.WriteNonStringKeyAsString);
    }

    static class Ta {
        public int id;
        public String name;
    }

    public static void main(String[] args) {
        Ta t = new Ta();
        t.id = 1;
        t.name = "nnmm";
        Map<Integer, Ta> map = new HashMap<>();
        map.put(1, t);
        System.err.println(toJSONString(map));
    }
}
