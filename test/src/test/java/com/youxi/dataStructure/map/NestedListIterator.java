package com.youxi.dataStructure.map;

import java.util.*;

public class NestedListIterator<T> implements Iterator<T> {

    private final Stack<Iterator<? extends T>> stack;

    public NestedListIterator(List<? extends T> nestedList) {
        stack = new Stack<>();
        stack.push(nestedList.iterator());
    }

    @Override
    public boolean hasNext() {
        while (!stack.isEmpty()) {
            Iterator<? extends T> top = stack.peek();
            if (top.hasNext()) {
                return true;
            } else {
                stack.pop();
            }
        }
        return false;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        Iterator<? extends T> top = stack.peek();
        T nextElement = top.next();

        if (nextElement instanceof List) {
            stack.push((Iterator<? extends T>) ((List<?>) nextElement).iterator());
        }

        return nextElement;
    }

    public static void main(String[] args) {
        List<Object> nestedList = Arrays.asList(
                1,
                Arrays.asList(2, 3),
                Arrays.asList(4, Arrays.asList(5, 6))
        );

        NestedListIterator<Object> iterator = new NestedListIterator<>(nestedList);

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
