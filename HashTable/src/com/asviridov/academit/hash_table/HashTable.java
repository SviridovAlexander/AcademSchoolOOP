package com.asviridov.academit.hash_table;

import java.util.*;

public class HashTable<E> implements Collection<E> {
    private static final int DEFAULT_CAPACITY = 10;

    private final ArrayList<E>[] lists;
    private int size;
    private int modCount;

    public HashTable() {
        //noinspection unchecked
        lists = (ArrayList<E>[]) new ArrayList[DEFAULT_CAPACITY];
    }

    public HashTable(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than 0. Given value: " + capacity);
        }

        //noinspection unchecked
        lists = new ArrayList[capacity];
    }

    private int getIndex(Object o) {
        if (o == null) {
            return 0;
        }

        return Math.abs(o.hashCode() % lists.length);
    }

    private class HashTableIterator implements Iterator<E> {
        private int globalIndex = -1;
        private int listIndex;
        private int arrayIndex;
        private final int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return globalIndex + 1 < size;
        }

        @Override
        public E next() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException("Collection were modified during iteration.");
            }

            if (!hasNext()) {
                throw new NoSuchElementException("No elements left");
            }

            while (arrayIndex < lists.length) {
                if (lists[arrayIndex] == null || listIndex >= lists[arrayIndex].size()) {
                    arrayIndex++;
                } else {
                    E data = lists[arrayIndex].get(listIndex);

                    globalIndex++;
                    listIndex++;

                    if (listIndex == lists[arrayIndex].size()) {
                        arrayIndex++;
                        listIndex = 0;
                    }

                    return data;
                }
            }

            throw new NoSuchElementException("No elements left");
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        int index = getIndex(o);

        return lists[index] != null && lists[index].contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return new HashTableIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];

        int i = 0;

        for (Object e : this) {
            array[i] = e;
            i++;
        }

        return array;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            //noinspection unchecked
            return (T[]) Arrays.copyOf(toArray(), size, a.getClass());
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(toArray(), 0, a, 0, size);

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    @Override
    public boolean add(E element) {
        int index = getIndex(element);

        if (lists[index] == null) {
            lists[index] = new ArrayList<>();
        }

        size++;
        modCount++;

        return lists[index].add(element);
    }

    @Override
    public boolean remove(Object o) {
        int index = getIndex(o);

        if (lists[index] != null && lists[index].remove(o)) {
            modCount++;
            size--;

            return true;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object e : c) {
            if (!contains(e)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c.isEmpty()) {
            return false;
        }

        for (E e : c) {
            add(e);
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean isCollectionChanged = false;

        for (Object element : c) {
            while (remove(element)) {
                isCollectionChanged = true;
            }
        }

        return isCollectionChanged;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean isCollectionChanged = false;
        int newSize = 0;

        for (ArrayList<E> list : lists) {
            if (list != null && !list.isEmpty()) {
                list.retainAll(c);

                newSize += list.size();
            }
        }

        if (size != newSize) {
            size = newSize;
            ++modCount;

            isCollectionChanged = true;
        }

        return isCollectionChanged;
    }

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }

        for (ArrayList<E> list : lists) {
            if (list != null) {
                list.clear();
            }
        }

        modCount++;
        size = 0;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();
        sb.append('{');
        int i = 0;

        for (ArrayList<E> list : lists) {
            sb.append(i).append('.').append(" ").append(list).append(System.lineSeparator());

            i++;
        }

        sb.setLength(sb.length() - 2);
        sb.append('}');

        return sb.toString();
    }
}
