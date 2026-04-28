package com.mdb.collection;

import com.mdb.XBean;
import com.mdb.exception.MdbError;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.RandomAccess;
import java.util.Spliterator;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.UnaryOperator;

/**
 * Tracked list with RW lock.
 * Optimized for write-heavy workloads where CopyOnWriteArrayList is too expensive.
 */
public class TrackedLockList<E> extends XBean implements List<E>, RandomAccess {
    private final List<E> delegate;
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
    private final ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

    public TrackedLockList(List<E> delegate, com.mdb.XBean _xp_) {
        this.delegate = Objects.requireNonNull(delegate);
        setParent(_xp_);
    }

/*    @Override
    public boolean checkFieldVersion(long checkPointCount) {
        return false;
    }*/

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

    @Override
    public boolean add(E e) {
        writeLock.lock();
        try {
            boolean result = delegate.add(e);
            if (result) {
                markModified(ModifyType.ADD, e);
            }
            return result;
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public void add(int index, E element) {
        writeLock.lock();
        try {
            delegate.add(index, element);
            markModified(ModifyType.ADD, element);
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        writeLock.lock();
        try {
            boolean result = delegate.addAll(c);
            if (result) {
                for (E e : c) {
                    markModified(ModifyType.ADD, e);
                }
            }
            return result;
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        writeLock.lock();
        try {
            boolean result = delegate.addAll(index, c);
            if (result) {
                for (E e : c) {
                    markModified(ModifyType.ADD, e);
                }
            }
            return result;
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public void clear() {
        writeLock.lock();
        try {
            if (!delegate.isEmpty()) {
                for (E e : delegate) {
                    markModified(ModifyType.REMOVE, e);
                }
                delegate.clear();
            }
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public E remove(int index) {
        writeLock.lock();
        try {
            E removed = delegate.remove(index);
            markModified(ModifyType.REMOVE, removed);
            return removed;
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public boolean remove(Object o) {
        writeLock.lock();
        try {
            boolean changed = delegate.remove(o);
            if (changed) {
                markModified(ModifyType.REMOVE, o);
            }
            return changed;
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        writeLock.lock();
        try {
            for (Object o : c) {
                markModified(ModifyType.REMOVE, o);
            }
            return delegate.removeAll(c);
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        writeLock.lock();
        try {
            boolean changed = delegate.retainAll(c);
            if (!changed) {
                return false;
            }
            markModified(ModifyType.REMOVE, null);
            return true;
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public E set(int index, E element) {
        writeLock.lock();
        try {
            E old = delegate.set(index, element);
            if (!Objects.equals(old, element)) {
                markModified(ModifyType.REMOVE, old);
                markModified(ModifyType.ADD, element);
            }
            return old;
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public void replaceAll(UnaryOperator<E> operator) {
        writeLock.lock();
        try {
            delegate.replaceAll(operator);
            markModified(ModifyType.CHANGE, null);
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public void sort(Comparator<? super E> c) {
        writeLock.lock();
        try {
            delegate.sort(c);
            markModified(ModifyType.CHANGE, null);
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public E get(int index) {
        readLock.lock();
        try {
            return delegate.get(index);
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public int size() {
        readLock.lock();
        try {
            return delegate.size();
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public boolean isEmpty() {
        readLock.lock();
        try {
            return delegate.isEmpty();
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public boolean contains(Object o) {
        readLock.lock();
        try {
            return delegate.contains(o);
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public Iterator<E> iterator() {
        return Collections.unmodifiableList(snapshot()).iterator();
    }

    @Override
    public ListIterator<E> listIterator() {
        return Collections.unmodifiableList(snapshot()).listIterator();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return Collections.unmodifiableList(snapshot()).listIterator(index);
    }

    @Override
    public Object[] toArray() {
        readLock.lock();
        try {
            return delegate.toArray();
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public <T> T[] toArray(T[] a) {
        readLock.lock();
        try {
            return delegate.toArray(a);
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        readLock.lock();
        try {
            return delegate.containsAll(c);
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public int indexOf(Object o) {
        readLock.lock();
        try {
            return delegate.indexOf(o);
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public int lastIndexOf(Object o) {
        readLock.lock();
        try {
            return delegate.lastIndexOf(o);
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        readLock.lock();
        try {
            return Collections.unmodifiableList(new ArrayList<>(delegate.subList(fromIndex, toIndex)));
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public Spliterator<E> spliterator() {
        throw new MdbError("Not implemented spliterator");
    }

    private ArrayList<E> snapshot() {
        readLock.lock();
        try {
            return new ArrayList<>(delegate);
        } finally {
            readLock.unlock();
        }
    }

    /***
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        List<?> target;
        if (o instanceof TrackedLockList<?>) {
            target = ((TrackedLockList<?>) o).snapshot();
        } else if (o instanceof List<?>) {
            target = (List<?>) o;
        } else {
            return false;
        }
        ArrayList<E> current = snapshot();
        if (current.size() != target.size()) {
            return false;
        }
        Iterator<E> currentIterator = current.iterator();
        Iterator<?> targetIterator = target.iterator();
        while (currentIterator.hasNext()) {
            if (!Objects.equals(currentIterator.next(), targetIterator.next())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hashCode = 1;
        for (E e : snapshot()) {
            hashCode = 31 * hashCode + Objects.hashCode(e);
        }
        return hashCode;
    }

    @Override
    public String toString() {
        return String.valueOf(snapshot());
    }
    ***/
}
