package com.asviridov.academit.list;

import java.util.NoSuchElementException;
import java.util.Objects;

public class SingleLinkedList<E> {
    private ListItem<E> head;
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
            throw new IndexOutOfBoundsException("The index is out of valid range (0, " + size + "]. Given index: " + index);
        }
    }

    public E getFirst() {
        checkListNotEmpty();

        return head.getValue();
    }

    private ListItem<E> getItemByIndex(int index) {
        ListItem<E> item = head;

        for (int i = 0; i < index; i++) {
            item = item.getNext();
        }

        return item;
    }

    public E get(int index) {
        checkIndex(index);

        return getItemByIndex(index).getValue();
    }

    public E set(int index, E value) {
        checkIndex(index);

        ListItem<E> item = getItemByIndex(index);

        E oldValue = item.getValue();
        item.setValue(value);

        return oldValue;
    }

    public E deleteAtIndex(int index) {
        checkIndex(index);

        if (index == 0) {
            return deleteFirst();
        }

        ListItem<E> previousItem = getItemByIndex(index - 1);
        E deletedValue = previousItem.getNext().getValue();

        previousItem.setNext(previousItem.getNext().getNext());
        size--;

        return deletedValue;
    }

    public void insertFirst(E value) {
        head = new ListItem<>(value, head);
        size++;
    }

    public void insertAtIndex(int index, E value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("The index is out of valid range (0, " + size + "). Given index: " + index);
        }

        if (index == 0) {
            insertFirst(value);
            return;
        }


        ListItem<E> previousItem = getItemByIndex(index - 1);

        previousItem.setNext(new ListItem<>(value, previousItem.getNext()));
        size++;
    }

    public boolean deleteByValue(E value) {
        if (size == 0) {
            return false;
        }

        if (head.getValue().equals(value)) {
            deleteFirst();

            return true;
        }

        ListItem<E> previousItem = head;
        ListItem<E> currentItem = head.getNext();

        while (currentItem != null) {
            if (Objects.equals(currentItem.getValue(), value)) {
                previousItem.setNext(currentItem.getNext());
                size--;

                return true;
            }

            previousItem = currentItem;
            currentItem = currentItem.getNext();
        }

        return false;
    }

    public E deleteFirst() {
        checkListNotEmpty();

        E deletedValue = head.getValue();

        head = head.getNext();
        size--;

        return deletedValue;
    }

    public void reverse() {
        if (size <= 1) {
            return;
        }

        ListItem<E> previousItem = null;
        ListItem<E> currentItem = head;

        while (currentItem != null) {
            ListItem<E> nextItem = currentItem.getNext();
            currentItem.setNext(previousItem);
            previousItem = currentItem;
            currentItem = nextItem;
        }

        head = previousItem;
    }

    public SingleLinkedList<E> copy() {
        SingleLinkedList<E> listCopy = new SingleLinkedList<>();

        if (size == 0) {
            return listCopy;
        }

        listCopy.head = new ListItem<>(head.getValue());
        listCopy.size = size;

        ListItem<E> currentItem = head.getNext();
        ListItem<E> itemCopy = listCopy.head;

        while (currentItem != null) {
            itemCopy.setNext(new ListItem<>(currentItem.getValue()));
            itemCopy = itemCopy.getNext();
            currentItem = currentItem.getNext();
        }

        return listCopy;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();
        sb.append('[');

        ListItem<E> item = head;

        while (item != null) {
            sb.append(item.getValue()).append(", ");
            item = item.getNext();
        }

        sb.setLength(sb.length() - 2);
        sb.append(']');

        return sb.toString();
    }
}