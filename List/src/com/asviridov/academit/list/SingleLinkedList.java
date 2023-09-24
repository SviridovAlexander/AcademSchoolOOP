package com.asviridov.academit.list;

import java.util.NoSuchElementException;

public class SingleLinkedList<T> {
    private ListItem<T> head;
    private int size;

    public int getSize() {
        return size;
    }

    private void checkListNotEmpty() {
        if (size == 0) {
            throw new NoSuchElementException("List is empty");
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("The index is less than 0 or greater than the current list size - " + size +
                    ". Given index: " + index);
        }
    }

    public T getFirstValue() {
        checkListNotEmpty();

        return head.getData();
    }

    private ListItem<T> getItemByIndex(int index) {
        ListItem<T> item = head;

        for (int i = 0; i < index; i++) {
            item = item.getNext();
        }

        return item;
    }

    public T getValueAtIndex(int index) {
        checkIndex(index);

        ListItem<T> item = getItemByIndex(index);

        return item.getData();
    }

    public T setValueAtIndex(int index, T value) {
        checkIndex(index);

        ListItem<T> item = getItemByIndex(index);

        T oldValue = item.getData();
        item.setData(value);

        return oldValue;
    }

    public T deleteAtIndex(int index) {
        checkIndex(index);

        if (index == 0) {
            return removeFirst();
        }

        ListItem<T> item = getItemByIndex(index - 1);
        T deletedValue = item.getNext().getData();

        item.setNext(item.getNext().getNext());
        size--;

        return deletedValue;
    }

    public void insertAtBeginning(T value) {
        ListItem<T> newItem = new ListItem<>(value);
        newItem.setNext(head);
        head = newItem;
        size++;
    }

    public void insertAtIndex(int index, T value) {
        checkIndex(index);

        if (index == 0) {
            insertAtBeginning(value);
            return;
        }

        ListItem<T> newItem = new ListItem<>(value);
        ListItem<T> previousItem = getItemByIndex(index - 1);

        newItem.setNext(previousItem.getNext());
        previousItem.setNext(newItem);

        size++;
    }

    public boolean deleteByValue(T value) {
        if (size == 0) {
            return false;
        }

        if (head.getData().equals(value)) {
            removeFirst();

            return true;
        }

        ListItem<T> item = head;

        while (item.getNext() != null) {
            if (item.getNext().getData().equals(value)) {
                item.setNext(item.getNext().getNext());
                size--;

                return true;
            }

            item = item.getNext();
        }

        return false;
    }

    public T removeFirst() {
        checkListNotEmpty();

        T removedValue = head.getData();

        head = head.getNext();
        size--;

        return removedValue;
    }

    public void reverse() {
        if (size <= 1) {
            return;
        }

        ListItem<T> previousItem = null;
        ListItem<T> currentItem = head;
        ListItem<T> nextItem;

        while (currentItem != null) {
            nextItem = currentItem.getNext();
            currentItem.setNext(previousItem);
            previousItem = currentItem;
            currentItem = nextItem;
        }

        head = previousItem;
    }

    public SingleLinkedList<T> copy() {
        SingleLinkedList<T> copyList = new SingleLinkedList<>();

        if (size == 0) {
            return copyList;
        }

        copyList.head = new ListItem<>(head.getData());
        copyList.size = size;

        ListItem<T> currentItem = head;
        ListItem<T> copyItem = copyList.head;

        while (currentItem.getNext() != null) {
            currentItem = currentItem.getNext();
            copyItem.setNext(new ListItem<>(currentItem.getData()));
            copyItem = copyItem.getNext();
        }

        return copyList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        ListItem<T> item = head;

        while (item != null) {
            sb.append(item.getData()).append(", ");
            item = item.getNext();
        }

        sb.setLength(sb.length() - 2);
        sb.append(']');

        return sb.toString();
    }
}