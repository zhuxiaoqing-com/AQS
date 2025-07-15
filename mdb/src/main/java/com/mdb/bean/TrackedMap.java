package com.mdb.bean;

import com.mdb.Mdb;
import com.mdb.XBean;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class TrackedMap<K,V> extends XBean implements Map<K,V> {
    private final Map<K,V> delegate;


    public TrackedMap(Map<K,V> delegate) {
        this.delegate = Objects.requireNonNull(delegate);
    }

    @Override
    public boolean fieldAnyModify() {
        if (delegate.isEmpty()) {
            return false;
        }
        V e1 = delegate.values().iterator().next();
        if (!(e1 instanceof XBean)) {
            return false;
        }
        for (V e : delegate.values()) {
            if (((XBean) e).only_isModify()) {
                Mdb.logger.error("fieldAnyModify selfModify false fieldModify true class {} ", getClass().getName(), new RuntimeException());
                return true;
            }
        }
        return false;
    }

    /**
     * @param type  type
     * @param value value有个准则就是如果是外面传入进来的，或者需要传出去的需要显示调用setParent; 原本就在里面的可以不调用
     */
    private void markModified(ModifyType type, Object value) {
        makeModify();
        if (value instanceof XBean) {
            switch (type) {
                case ADD:
                    ((XBean) value).setParent(this);
                    break;
                case REMOVE:
                    ((XBean) value).setParent(null);
                    break;
                case CHANGE:
                    break;
            }
        }
    }

    @Override public V put(K key, V value) {
        V old = delegate.put(key, value);
        if (old != null) {
            markModified(ModifyType.REMOVE, old);
        }
        markModified(ModifyType.ADD, value);
        return old;
    }

    @Override public void putAll(Map<? extends K, ? extends V> m) {
        delegate.putAll(m);
        for (V value : m.values()) {
            markModified(ModifyType.ADD, value);
        }
    }

    @Override public V remove(Object key) {
        V remove = delegate.remove(key);
        if (remove != null) {
            markModified(ModifyType.REMOVE, remove);
        }
        return null;
    }

    @Override public void clear() {
        delegate.clear();
        markModified(ModifyType.REMOVE, null);
    }

    @Override public V putIfAbsent(K key, V value) {
        markModified(ModifyType.ADD, value);
        return delegate.putIfAbsent(key, value);
    }

    @Override public boolean remove(Object key, Object value) {
        boolean changed = delegate.remove(key, value);
        if (changed) markModified(ModifyType.REMOVE, value);
        return changed;
    }

    @Override public boolean replace(K key, V oldValue, V newValue) {
        boolean changed = delegate.replace(key, oldValue, newValue);
        if(changed) {
            markModified(ModifyType.REMOVE, oldValue);
            markModified(ModifyType.ADD, newValue);
        }
        return changed;
    }

    @Override public V replace(K key, V value) {
        if(containsKey(key)) {
            V old = delegate.replace(key, value);
            if(!Objects.equals(old, value)) {
                markModified(ModifyType.REMOVE, old);
                markModified(ModifyType.ADD, value);
            }
            return old;
        }
        return null;
    }

    @Override public V compute(K key, BiFunction<? super K, ? super V, ? extends V> remapping) {
        V newValue = delegate.compute(key, remapping);
        if (newValue != null) {
            markModified(ModifyType.ADD, newValue);
        }
        return newValue;
    }

    @Override public V computeIfAbsent(K key, Function<? super K, ? extends V> mapping) {
        V oldValue = delegate.get(key);

        V value = delegate.computeIfAbsent(key, mapping);
        if (value != null && value != oldValue) {
            markModified(ModifyType.ADD, value);
        }

       return value;
    }

    @Override public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remapping) {
        V newValue = delegate.computeIfPresent(key, remapping);
        markModified(ModifyType.ADD, null);
        return newValue;
    }

    @Override public V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remapping) {
        markModified(ModifyType.CHANGE, value);
        return delegate.merge(key, value, remapping);
    }

    @Override
    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
        delegate.replaceAll(function);
        markModified(ModifyType.ADD, null);
    }

    // 只读方法委托
    @Override public int size() { return delegate.size(); }
    @Override public boolean isEmpty() { return delegate.isEmpty(); }
    @Override public boolean containsKey(Object key) { return delegate.containsKey(key); }
    @Override public boolean containsValue(Object value) { return delegate.containsValue(value); }
    @Override public V get(Object key) { return delegate.get(key); }

    @Override public Set<K> keySet() {
        return new TrackedKeySet();
    }

    @Override public Collection<V> values() {
        return new TrackedValues();
    }

    @Override public Set<Entry<K,V>> entrySet() {
        return new TrackedEntrySet();
    }

    @Override
    public boolean equals(Object o) {
        return delegate.equals(o);
    }

    @Override
    public int hashCode() {
        return delegate.hashCode();
    }

    @Override
    public V getOrDefault(Object key, V defaultValue) {
        return delegate.getOrDefault(key, defaultValue);
    }

    @Override
    public void forEach(BiConsumer<? super K, ? super V> action) {
        delegate.forEach(action);
    }


    private class TrackedKeySet extends AbstractSet<K> {
        @Override public Iterator<K> iterator() {
            return new TrackedKeyIterator(delegate.keySet().iterator());
        }
        @Override public int size() { return delegate.size(); }
        @Override public boolean contains(Object o) { return delegate.containsKey(o); }
        @Override public boolean remove(Object o) {
            markModified(ModifyType.REMOVE, null);
            V value = delegate.remove(o);
            return value != null;
        }
        @Override public void clear() {
            delegate.clear();
            markModified(ModifyType.REMOVE, null);
        }
    }

    private class TrackedValues extends AbstractCollection<V> {
        @Override public Iterator<V> iterator() {
            return new TrackedValueIterator(delegate.values().iterator());
        }
        @Override public int size() { return delegate.size(); }
        @Override public boolean contains(Object o) { return delegate.containsValue(o); }
        @Override public void clear() {
            delegate.clear();
            markModified(ModifyType.REMOVE, null);
        }
    }
    private class TrackedEntrySet extends AbstractSet<Entry<K,V>> {
        @Override public Iterator<Entry<K,V>> iterator() {
            return new TrackedEntryIterator(delegate.entrySet().iterator());
        }
        @Override public int size() { return delegate.size(); }
        @Override public boolean contains(Object o) { return delegate.entrySet().contains(o); }
        @Override public boolean remove(Object o) {
            boolean changed = delegate.entrySet().remove(o);
            markModified(ModifyType.REMOVE, null);
            return changed;
        }
        @Override public void clear() {
            if (!isEmpty()) {
                delegate.clear();
                markModified(ModifyType.REMOVE, null);
            }
        }
    }



    private class TrackedKeyIterator implements Iterator<K> {
        private final Iterator<K> it;
        private K lastKey;
        TrackedKeyIterator(Iterator<K> it) { this.it = it; }
        @Override public boolean hasNext() { return it.hasNext(); }
        @Override public K next() { return lastKey = it.next(); }
        @Override public void remove() {
            it.remove();
            markModified(ModifyType.REMOVE, null);
        }
    }

    private class TrackedValueIterator implements Iterator<V> {
        private final Iterator<V> it;
        TrackedValueIterator(Iterator<V> it) { this.it = it; }
        @Override public boolean hasNext() { return it.hasNext(); }
        @Override public V next() { return it.next(); }
        @Override public void remove() { throw new UnsupportedOperationException(); }
    }


    private class TrackedEntryIterator implements Iterator<Entry<K,V>> {
        private final Iterator<Entry<K,V>> it;
        private Entry<K,V> lastReturned;
        TrackedEntryIterator(Iterator<Entry<K,V>> it) { this.it = it; }
        @Override public boolean hasNext() { return it.hasNext(); }
        @Override public Entry<K,V> next() { return lastReturned = it.next(); }
        @Override public void remove() {
            it.remove();
            markModified(ModifyType.REMOVE, lastReturned);
        }
    }
}