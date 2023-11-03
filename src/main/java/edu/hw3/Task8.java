package edu.hw3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Task8<T> implements Iterator<T> {
    private final ListIterator<T> iterator;

    public Task8(Collection<T> collection) {
        List<T> reverseList = new ArrayList<>(collection);
        Collections.reverse(reverseList);
        iterator = reverseList.listIterator();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public T next() {
        return iterator.next();
    }
}
