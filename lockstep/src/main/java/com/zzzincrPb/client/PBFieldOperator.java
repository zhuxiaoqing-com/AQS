package com.zzzincrPb.client;

import com.google.protobuf.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author zhuxiaoqing
 * @Description: PBFieldOperator
 * @Date 2025/10/11 15:54
 **/
public class PBFieldOperator {

    // ===== 普通字段 =====
    public static void setField(Message.Builder builder, int fieldNumber, Object value) {
        Descriptors.FieldDescriptor fd = builder.getDescriptorForType().findFieldByNumber(fieldNumber);
        if (fd.isRepeated()) throw new IllegalArgumentException("Field is repeated, use repeated methods");
        if (fd.isMapField()) throw new IllegalArgumentException("Field is a map, use map methods");
        builder.setField(fd, value);
    }

    public static Object getField(Message.Builder builder, int fieldNumber) {
        Descriptors.FieldDescriptor fd = builder.getDescriptorForType().findFieldByNumber(fieldNumber);
        return builder.getField(fd);
    }

    public static Object clearField(Message.Builder builder, int fieldNumber) {
        Descriptors.FieldDescriptor fd = builder.getDescriptorForType().findFieldByNumber(fieldNumber);
        return builder.clearField(fd);
    }

    // ===== repeated list =====
    public static void addRepeatedField(Message.Builder builder, int fieldNumber, Object value) {
        Descriptors.FieldDescriptor fd = builder.getDescriptorForType().findFieldByNumber(fieldNumber);
        if (!fd.isRepeated() || fd.isMapField())
            throw new IllegalArgumentException("Field is not a repeated field");
        builder.addRepeatedField(fd, value);
    }

    public static void setRepeatedField(Message.Builder builder, int fieldNumber, int index, Object value) {
        Descriptors.FieldDescriptor fd = builder.getDescriptorForType().findFieldByNumber(fieldNumber);
        if (!fd.isRepeated() || fd.isMapField())
            throw new IllegalArgumentException("Field is not a repeated field");
        builder.setRepeatedField(fd, index, value);
    }

    public static void removeRepeatedField(Message.Builder builder, int fieldNumber, int index) {
        Descriptors.FieldDescriptor fd = builder.getDescriptorForType().findFieldByNumber(fieldNumber);
        if (!fd.isRepeated() || fd.isMapField())
            throw new IllegalArgumentException("Field is not a repeated field");

        List<?> oldList = new ArrayList<>((List<?>) builder.getField(fd));
        builder.clearField(fd);
        for (int i = 0; i < oldList.size(); i++) {
            if (i != index) builder.addRepeatedField(fd, oldList.get(i));
        }
    }

    public static List<?> getRepeatedField(Message.Builder builder, int fieldNumber) {
        Descriptors.FieldDescriptor fd = builder.getDescriptorForType().findFieldByNumber(fieldNumber);
        if (!fd.isRepeated() || fd.isMapField())
            throw new IllegalArgumentException("Field is not a repeated field");
        return (List<?>) builder.getField(fd);
    }


    // ===== put map field =====
    public static void putMapField(Message.Builder builder, int fieldNumber, Object key, Object value) {
        Descriptors.FieldDescriptor fd = builder.getDescriptorForType().findFieldByNumber(fieldNumber);
        if (!fd.isMapField()) throw new IllegalArgumentException("Field is not a map");

        // 生成方法名：put + FieldName
        String javaName = fd.getName(); // proto 字段名
        String methodName = "put" + capitalize(javaName);

        try {
            for (Method method : builder.getClass().getMethods()) {
                if(method.getName().equals(methodName)) {
                    method.invoke(builder, key, value);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to put map field: " + javaName, e);
        }
    }

    // ===== remove map field =====
    public static void removeMapField(Message.Builder builder, int fieldNumber, Object key) {
        Descriptors.FieldDescriptor fd = builder.getDescriptorForType().findFieldByNumber(fieldNumber);
        if (!fd.isMapField()) throw new IllegalArgumentException("Field is not a map");

        // 生成方法名：remove + FieldName
        String javaName = fd.getName();
        String methodName = "remove" + capitalize(javaName);

        try {
            Method removeMethod = builder.getClass().getMethod(methodName, Object.class);
            removeMethod.invoke(builder, key);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Builder does not have method: " + methodName, e);
        } catch (Exception e) {
            throw new RuntimeException("Failed to remove map field: " + javaName, e);
        }
    }

    // ===== capitalize first letter =====
    private static String capitalize(String s) {
        if (s == null || s.isEmpty()) return s;
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }
    public static Map<?, ?> getMapField(Message.Builder builder, int fieldNumber) {
        Descriptors.FieldDescriptor fd = builder.getDescriptorForType().findFieldByNumber(fieldNumber);
        if (!fd.isMapField()) throw new IllegalArgumentException("Field is not a map");

        @SuppressWarnings("unchecked")
        Map<Object, Object> map = (Map<Object, Object>) builder.getField(fd);
        return map;
    }
}
