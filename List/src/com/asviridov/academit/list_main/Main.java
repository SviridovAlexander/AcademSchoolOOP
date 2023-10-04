package com.asviridov.academit.list_main;

import com.asviridov.academit.list.SingleLinkedList;

public class Main {
    public static void main(String[] args) {
        SingleLinkedList<Integer> list = new SingleLinkedList<>();

        list.insertFirst(1);
        list.insertFirst(2);
        list.insertFirst(3);
        list.insertFirst(4);
        list.insertFirst(5);

        System.out.println("Original List elements: " + list);
        System.out.println("List size: " + list.getSize());
        System.out.println("First element: " + list.getFirst());

        int indexToGet = 1;
        System.out.println("Element at index " + indexToGet + ": " + list.get(indexToGet));

        int newValue = 99;
        int oldValue = list.set(indexToGet, newValue);
        System.out.println("Old value at index " + indexToGet + ": " + oldValue);
        System.out.println("New value at index " + indexToGet + ": " + list.get(indexToGet));
        System.out.println("Current List elements: " + list);

        int deletedValue = list.deleteAtIndex(2);
        System.out.println("Deleted value at index 2: " + deletedValue);
        System.out.println("Current List elements: " + list);

        int indexToInsert = 1;
        int valueToInsert = 42;
        list.insertAtIndex(indexToInsert, valueToInsert);
        System.out.println("Inserted " + valueToInsert + " at index " + indexToInsert + ": " + list);

        int valueToDelete = 2;

        if (list.deleteByValue(valueToDelete)) {
            System.out.println("Deleted value " + valueToDelete);
        } else {
            System.out.println("Value " + valueToDelete + " not found");
        }

        System.out.println("Current List elements: " + list);

        list.reverse();
        System.out.println("List elements after reverse: " + list);

        SingleLinkedList<Integer> listCopy = list.copy();
        System.out.println("Copied List elements: " + listCopy);
    }
}