package com.zzzincrPb.client;

import com.google.protobuf.DescriptorProtos;
import com.google.protobuf.Descriptors;
import com.google.protobuf.DynamicMessage;
import com.google.protobuf.Message;
import com.pb.*;

import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Consumer;

/**
 * @author zhuxiaoqing
 * @Description: DeltaListenerManager
 * @Date 2025/10/11 13:37
 **/

// ---------------- 字段监听树 ----------------
class ListenerNode {
    Map<Integer, ListenerNode> children = new HashMap<>();
    List<Consumer<Message.Builder>> callbacks = new ArrayList<>();
}


public class DeltaListenerManager {
    private final Map<Integer, GetFunction<Message.Builder>> dataGetters = new HashMap<>();
    private final Map<Integer, ListenerNode> listenerTreeMap = new HashMap<>();

    public void addGetDataFunction(int msgId, GetFunction<Message.Builder> getFunction) {
        dataGetters.put(msgId, getFunction);
    }

    public void addListen(int msgId, List<Integer> pathList, Consumer<Message.Builder> callback) {
        ListenerNode root = listenerTreeMap.computeIfAbsent(msgId, k -> new ListenerNode());
        ListenerNode node = root;
        for (Integer path : pathList) {
            node = node.children.computeIfAbsent(path, k -> new ListenerNode());
        }
        node.callbacks.add(callback); // 回调挂在叶子节点
    }


    private void addPath(ListenerNode root, Integer path, Consumer<Message.Builder> callback) {
        ListenerNode node = root;
        node = node.children.computeIfAbsent(path, k -> new ListenerNode());
        node.callbacks.add(callback);
    }

    public void handleDelta(MsgDeltaField msg) {
        int msgId = msg.getMsgId();
        Message.Builder target = dataGetters.get(msgId).get();
        ListenerNode root = listenerTreeMap.get(msgId);

        Set<ListenerNode> triggerNodes = new HashSet<>();

        for (DeltaField delta : msg.getDeltaList()) {
            for (PBFieldPath path : delta.getFieldPathList()) {
                try {
                    applyDelta(target, path, delta.getModify(), root, triggerNodes);
                } catch (Exception e) {
                    throw new RuntimeException("Delta apply failed: " + e.getMessage(), e);
                }
            }
        }

        for (ListenerNode node : triggerNodes) {
            for (Consumer<Message.Builder> cb : node.callbacks) {
                cb.accept(target);
            }
        }
    }

    private void traverseListeners(ListenerNode node, List<ListenerNode> triggerList) {
        for (ListenerNode child : node.children.values()) {
            traverseListeners(child, triggerList); // 先递归子节点
        }
        if (!node.callbacks.isEmpty()) {
            triggerList.add(node); // 再加自己（保证叶子先触发）
        }
    }


    private void applyDelta(Message.Builder msg, PBFieldPath path, PBFieldModify modify, ListenerNode node, Set<ListenerNode> triggerNodes) throws Exception {
        traverseAndApply(msg, path, modify, node, triggerNodes);
    }

    private void traverseAndApply(Message.Builder msg, PBFieldPath path, PBFieldModify modify, ListenerNode node, Set<ListenerNode> triggerNodes) throws Exception {

        int fieldIndex = path.hasFieldIndex() ? path.getFieldIndex() : -1;

        if (fieldIndex != -1 && !fieldExists(msg, fieldIndex)) {
            throw new RuntimeException("FieldIndex " + fieldIndex + " does not exist on " + msg.getClass().getSimpleName());
        }

        applyFieldChange(msg, fieldIndex, modify);

        if(node != null) {
            ListenerNode childNode = (fieldIndex != -1) ? node.children.get(fieldIndex) : null;
            if (childNode != null) triggerNodes.add(childNode);
        }
    }

    private boolean fieldExists(Message.Builder msg, int fieldIndex) {
        Descriptors.Descriptor descriptor = msg.getDescriptorForType();
        return descriptor.findFieldByNumber(fieldIndex) != null;
    }

    private void applyFieldChange(Message.Builder builder, int fieldIndex, PBFieldModify modify) throws Exception {
        Descriptors.FieldDescriptor fd = builder.getDescriptorForType().findFieldByNumber(fieldIndex);


        switch (modify.getType()) {
            case FIELD:
                builder.setField(fd, convertValue(fd, modify.getValue(), modify.getType()));
                break;
            case LIST:
                for (PBListOperation op : modify.getListOpsList()) {
                    Object val = convertValue(fd, op.getValue(), modify.getType());
                    switch (op.getOp()) {
                        case ADD:
                            PBFieldOperator.addRepeatedField(builder, fieldIndex, val);
                            break;
                        case REMOVE:
                            PBFieldOperator.removeRepeatedField(builder, fieldIndex, op.getIndex());
                            break;
                        case SET:
                            PBFieldOperator.setRepeatedField(builder, fieldIndex, op.getIndex(), val);
                            break;
                        case CLEAR:
                            PBFieldOperator.clearField(builder, fieldIndex);
                            break;
                    }
                }
                break;
            case MAP:
                for (PBMapOperation op : modify.getMapOpsList()) {
                    switch (op.getOp()) {
                        case PUT:
                            PBFieldOperator.putMapField(builder, fieldIndex, convertValue(fd, op.getKey(), modify.getType(), true),
                                    convertValue(fd, op.getValue(), modify.getType()));
                            break;
                        case REMOVE:
                            PBFieldOperator.removeMapField(builder, fieldIndex, convertValue(fd, op.getKey(), modify.getType()));
                            break;
                        case CLEAR:
                            PBFieldOperator.clearField(builder, fieldIndex);
                            break;
                    }
                }
                break;
        }
    }

    private Object convertValue(Descriptors.FieldDescriptor fd, PBFieldValue value, PBFieldModify.ModifyType type)  {
        return convertValue(fd, value, type, false);
    }
    private Object convertValue(Descriptors.FieldDescriptor fd, PBFieldValue value, PBFieldModify.ModifyType type, boolean key)  {
        switch (value.getType()) {
            case PB_INT32:
                return value.getInt32Value();
            case PB_INT64:
                return value.getInt64Value();
            case PB_FLOAT:
                return value.getFloatValue();
            case PB_DOUBLE:
                return value.getDoubleValue();
            case PB_STRING:
                return value.getStringValue();
            case PB_BOOL:
                return value.getBoolValue();
            case PB_BYTES:
                if (fd.getJavaType() != Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                    return value.getBytesValue();
                }
            case PB_MESSAGE:
                try {
                    String fullName = "";
                    switch (type) {
                        case FIELD:
                            Descriptors.Descriptor msgDesc = fd.getMessageType();
                            fullName = msgDesc.getFullName();
                            break;
                        case LIST:
                            msgDesc = fd.getMessageType();
                            fullName = msgDesc.getFullName();
                            break;
                        case MAP:
                            Descriptors.Descriptor entryDescriptor = fd.getMessageType();
                            Descriptors.FieldDescriptor valueFd = entryDescriptor.findFieldByNumber(key?1:2); // value field number 2
                            Descriptors.Descriptor valueMessageDesc = valueFd.getMessageType();
                            fullName = valueMessageDesc.getFullName();
                            break;
                    }
                    Descriptors.FileDescriptor fileDesc = fd.getFile();

                    String javaPackage = fileDesc.getOptions().getJavaPackage();
                    Class<?> msgClass = Class.forName(javaPackage+"."+fullName);
                    Method parseFromMethod = msgClass.getMethod("parseFrom", byte[].class);
                    Message msg = (Message) parseFromMethod.invoke(null, value.getBytesValue().toByteArray());
                    return msg;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            default:
                return null;
        }
    }


    public  void deepSet(Message.Builder builder, List<Integer> fieldPath, Object value) {
        if (fieldPath == null || fieldPath.isEmpty()) {
            throw new IllegalArgumentException("fieldPath cannot be empty");
        }

        Descriptors.FieldDescriptor field = builder.getDescriptorForType().findFieldByNumber(fieldPath.get(0));
        if (field == null) {
            throw new IllegalArgumentException("Field number " + fieldPath.get(0) + " not found in " + builder.getDescriptorForType().getFullName());
        }

        if (fieldPath.size() == 1) {
            // 最终字段，直接设置
            setFieldValue(builder, field, value);
            return;
        }

        // 剩余路径 -> 递归深入
        Message.Builder subBuilder;

        if (field.isRepeated() && !field.isMapField()) {
            throw new IllegalArgumentException("Cannot deep set into repeated field directly: " + field.getFullName());
        } else if (field.isMapField()) {
            // map 需要特殊处理
            Descriptors.Descriptor entryDesc = field.getMessageType();
            Descriptors.FieldDescriptor keyFd = entryDesc.findFieldByNumber(1);
            Descriptors.FieldDescriptor valueFd = entryDesc.findFieldByNumber(2);

            Object key = fieldPath.get(1); // 你可以根据协议格式自定义 key 提取方式
            Object existing = getMapValue(builder, field, key, keyFd);
            if (existing == null) {
                Message.Builder entryBuilder = builder.newBuilderForField(field);
                entryBuilder.setField(keyFd, key);
                subBuilder = (Message.Builder) entryBuilder.newBuilderForField(valueFd);
            } else {
                subBuilder = ((Message) existing).toBuilder();
            }

            deepSet(subBuilder, fieldPath.subList(2, fieldPath.size()), value);
            //builder.putMapField(field, key, subBuilder.build());
            return;
        } else {
            // 普通 message
            if (!builder.hasField(field)) {
                subBuilder = builder.newBuilderForField(field);
            } else {
                subBuilder = ((Message) builder.getField(field)).toBuilder();
            }
        }

        deepSet(subBuilder, fieldPath.subList(1, fieldPath.size()), value);
        builder.setField(field, subBuilder.build());
    }

    private  void setFieldValue(Message.Builder builder, Descriptors.FieldDescriptor field, Object value) {
        if (field.isMapField()) {
            if (!(value instanceof Map)) {
                throw new IllegalArgumentException("Expected Map for field " + field.getFullName());
            }
            builder.clearField(field);
            for (Map.Entry<?, ?> e : ((Map<?, ?>) value).entrySet()) {
              //  builder.putMapField(field, e.getKey(), e.getValue());
            }
        } else if (field.isRepeated()) {
            if (!(value instanceof Collection)) {
                throw new IllegalArgumentException("Expected Collection for repeated field " + field.getFullName());
            }
            builder.clearField(field);
            //builder.addAllRepeatedField(field, (Collection<?>) value);
        } else {
            builder.setField(field, value);
        }
    }

    private  Object getMapValue(Message.Builder builder, Descriptors.FieldDescriptor field, Object key, Descriptors.FieldDescriptor keyFd) {
        @SuppressWarnings("unchecked")
        Map<Object, Object> map = (Map<Object, Object>) builder.getField(field);
        return map.get(key);
    }


    public static void main(String[] args) {
        DeltaListenerManager manager = new DeltaListenerManager();

// 注册获取对象方法
        manager.addGetDataFunction(1001, () -> null);

// 监听 Item 的变化
        manager.addListen(1001, Arrays.asList(1, 2), msg -> {
            System.out.println("Item changed: " + msg);
        });

// 处理增量消息
        // manager.handleDelta(deltaMessage);
    }
}
