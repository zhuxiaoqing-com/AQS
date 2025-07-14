package com.mdb.bean;

import com.mdb.MdbBean;

import java.util.Map;
import java.util.Objects;

public class TrackedMap<K,V> extends MdbBean implements Map<K,V> {
    private final Map<K,V> delegate;


    public TrackedMap(Map<K,V> delegate) {
        this.delegate = Objects.requireNonNull(delegate);
    }

    /**
     * @param type  type
     * @param value value有个准则就是如果是外面传入进来的，或者需要传出去的需要显示调用setParent; 原本就在里面的可以不调用
     */
    private void markModified(ModifyType type, Object value) {
        makeModify();
        if (value instanceof MdbBean) {
            switch (type) {
                case ADD:
                    ((MdbBean) value).setParent(this);
                    break;
                case REMOVE:
                    ((MdbBean) value).setParent(null);
                    break;
                case REORDER:
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
        if(!containsKey(key)) {
            V result = delegate.putIfAbsent(key, value);
            markModified(ModifyType.ADD, new SimpleEntry<>(key, value));
            return result;
        }
        return get(key);
    }

    @Override public boolean remove(Object key, Object value) {
        boolean changed = delegate.remove(key, value);
        if(changed) markModified(ModifyType.REMOVE, new SimpleEntry<>(key, value));
        return changed;
    }

    @Override public boolean replace(K key, V oldValue, V newValue) {
        boolean changed = delegate.replace(key, oldValue, newValue);
        if(changed) {
            markModified(ModifyType.ADD, new SimpleEntry<>(key, newValue));
            markModified(ModifyType.REMOVE, new SimpleEntry<>(key, oldValue));
        }
        return changed;
    }

    @Override public V replace(K key, V value) {
        if(containsKey(key)) {
            V old = delegate.replace(key, value);
            if(!Objects.equals(old, value)) {
                markModified(ModifyType.ADD, new SimpleEntry<>(key, value));
                markModified(ModifyType.REMOVE, new SimpleEntry<>(key, old));
            }
            return old;
        }
        return null;
    }

    @Override public V compute(K key, BiFunction<? super K, ? super V, ? extends V> remapping) {
        V oldValue = get(key);
        V newValue = delegate.compute(key, remapping);
        if(!Objects.equals(oldValue, newValue)) {
            if(newValue != null) {
                markModified(ModifyType.ADD, new SimpleEntry<>(key, newValue));
            }
            if(oldValue != null) {
                markModified(ModifyType.REMOVE, new SimpleEntry<>(key, oldValue));
            }
        }
        return newValue;
    }

    @Override public V computeIfAbsent(K key, Function<? super K, ? extends V> mapping) {
        if(!containsKey(key)) {
            V value = delegate.computeIfAbsent(key, mapping);
            if(value != null) {
                markModified(ModifyType.ADD, new SimpleEntry<>(key, value));
            }
            return value;
        }
        return get(key);
    }

    @Override public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remapping) {
        if(containsKey(key)) {
            V oldValue = get(key);
            V newValue = delegate.computeIfPresent(key, remapping);
            if(newValue == null) {
                markModified(ModifyType.REMOVE, new SimpleEntry<>(key, oldValue));
            } else if(!Objects.equals(oldValue, newValue)) {
                markModified(ModifyType.ADD, new SimpleEntry<>(key, newValue));
                markModified(ModifyType.REMOVE, new SimpleEntry<>(key, oldValue));
            }
            return newValue;
        }
        return null;
    }

    @Override public V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remapping) {
        V oldValue = get(key);
        V newValue = delegate.merge(key, value, remapping);
        if(!Objects.equals(oldValue, newValue)) {
            if(newValue != null) {
                markModified(ModifyType.ADD, new SimpleEntry<>(key, newValue));
            }
            if(oldValue != null) {
                markModified(ModifyType.REMOVE, new SimpleEntry<>(key, oldValue));
            }
        }
        return newValue;
    }

    // 只读方法委托
    @Override public Set<Entry<K,V>> entrySet() { return new TrackedEntrySet(delegate.entrySet()); }
    @Override public int size() { return delegate.size(); }
    @Override public boolean isEmpty() { return delegate.isEmpty(); }
    @Override public boolean containsKey(Object key) { return delegate.containsKey(key); }
    @Override public boolean containsValue(Object value) { return delegate.containsValue(value); }
    @Override public V get(Object key) { return delegate.get(key); }

    private class TrackedEntrySet extends AbstractSet<Entry<K,V>> {
        private final Set<Entry<K,V>> es;
        TrackedEntrySet(Set<Entry<K,V>> es) { this.es = es; }
        @Override public Iterator<Entry<K,V>> iterator() { return new TrackedEntryIterator(es.iterator()); }
        @Override public int size() { return es.size(); }
        @Override public boolean contains(Object o) { return es.contains(o); }
        @Override public boolean remove(Object o) {
            boolean changed = es.remove(o);
            if(changed && o instanceof Entry) {
                Entry<?,?> e = (Entry<?,?>) o;
                markModified(ModifyType.REMOVE, new SimpleEntry<>(e.getKey(), e.getValue()));
            }
            return changed;
        }
        @Override public void clear() {
            if(!isEmpty()) {
                Set<Entry<K,V>> removed = new HashSet<>(es);
                es.clear();
                markModified(ModifyType.REMOVE, removed);
            }
        }
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