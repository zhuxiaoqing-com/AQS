package com.mdb.bean;

import com.mdb.Mdb;
import com.mdb.XBean;
import com.mdb.exception.MdbException;

import java.util.*;

public class TrackedSet <E> extends XBean implements Set<E> {
    private final Set<E> delegate;

    public TrackedSet(Set<E> delegate) {
        this.delegate = Objects.requireNonNull(delegate);
    }
/*

    @Override public boolean isModified() { return !modifications.isEmpty(); }
    @Override public boolean isModified(ModifyType type) { return modifications.containsKey(type); }
    @Override public Object getModifiedValue(ModifyType type) { return modifications.get(type); }
    @Override public void clearModified() { modifications.clear(); }
    @Override public void clearModified(ModifyType type) { modifications.remove(type); }
*/

    @Override
    public boolean fieldAnyModify() {
        // 自己有变化
        if(only_isModify()) {
            return true;
        }
        if (delegate.isEmpty()) {
            return false;
        }
        E e1 = delegate.iterator().next();
        if (!(e1 instanceof XBean)) {
            return false;
        }
        for (E e : delegate) {
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

    @Override public boolean add(E e) {
        boolean changed = delegate.add(e);
        markModified(ModifyType.ADD, e);
        return changed;
    }

    @Override public boolean addAll(Collection<? extends E> c) {
        for (E e : c) {
            markModified(ModifyType.ADD, e);
        }
        return delegate.addAll(c);
    }

    @Override public boolean remove(Object o) {
        boolean changed = delegate.remove(o);
        markModified(ModifyType.REMOVE, o);
        return changed;
    }

    @Override public boolean removeAll(Collection<?> c) {
        for (Object o : c) {
            markModified(ModifyType.REMOVE, o);
        }
        return delegate.removeAll(c);
    }

    @Override public boolean retainAll(Collection<?> c) {
        markModified(ModifyType.REMOVE, null);
        return delegate.retainAll(c);
    }

    @Override public void clear() {
        if (!isEmpty()) {
            markModified(ModifyType.REMOVE, null);
            delegate.clear();
        }
    }

    // 只读方法委托
    @Override public Iterator<E> iterator() { return new TrackedIterator(delegate.iterator()); }
    @Override public int size() { return delegate.size(); }
    @Override public boolean isEmpty() { return delegate.isEmpty(); }
    @Override public boolean contains(Object o) { return delegate.contains(o); }

    @Override
    public Object[] toArray() {
        return delegate.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return delegate.toArray(a);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return delegate.containsAll(c);
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
    public Spliterator<E> spliterator() {
        throw new MdbException("Not implemented spliterator");
    }

    private class TrackedIterator implements Iterator<E> {
        private final Iterator<E> it;
        private E lastReturned;
        TrackedIterator(Iterator<E> it) { this.it = it; }
        @Override public boolean hasNext() { return it.hasNext(); }
        @Override public E next() { return lastReturned = it.next(); }
        @Override public void remove() {
            it.remove();
            markModified(ModifyType.REMOVE, lastReturned);
        }
    }
}
