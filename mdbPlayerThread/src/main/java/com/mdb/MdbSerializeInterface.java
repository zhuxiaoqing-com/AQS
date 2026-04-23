package com.mdb;

import java.util.Map;
import java.util.function.Consumer;

@FunctionalInterface
public interface MdbSerializeInterface {
    public void onFlushOver(Map<Object, TRecord> records,
                            Consumer<Map<Object, TRecord>> handler, Consumer<Map<Object, String>> callback);
}
