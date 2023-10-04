package com.asviridov.academit.array_list_main;

import com.asviridov.academit.array_list.ArrayList;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList1 = new ArrayList<>(5);

        arrayList1.add(1);
        arrayList1.add(2);
        arrayList1.add(3);
        arrayList1.add(4);
        arrayList1.add(10);
        arrayList1.add(3);
        arrayList1.add(2);
        arrayList1.add(6, 7);

        System.out.println("arrayList1: " + arrayList1);

        System.out.println("arrayList1 size: " + arrayList1.size());

        System.out.println("Check if arrayList1 is empty: " + arrayList1.isEmpty());

        System.out.println("Check if arrayList1 contains '4': " + arrayList1.contains(4));
        System.out.println("Check if arrayList1 contains '111': " + arrayList1.contains(111));

        System.out.println("arrayList1 to array: " + Arrays.toString(arrayList1.toArray()));

        System.out.println("Deleted element at index '0': " + arrayList1.remove(0));
        System.out.println("arrayList1 after removal: " + arrayList1);

        int indexFirstElement = arrayList1.indexOf(3);

        if (indexFirstElement != -1) {
            System.out.println("Index of the first element '3' found: " + indexFirstElement);
        } else {
            System.out.println("Element '3' not found");
        }

        int indexLastElement = arrayList1.lastIndexOf(2);

        if (indexLastElement != -1) {
            System.out.println("Index of the last element '2' found: " + indexLastElement);
        } else {
            System.out.println("Element '2' not found");
        }

        arrayList1.set(0, 10);
        System.out.println("Set '10' at index '0': " + arrayList1);

        System.out.println("Element at index '2': " + arrayList1.get(2));

        ArrayList<Integer> arrayList2 = new ArrayList<>();
        arrayList2.add(3);
        arrayList2.add(2);
        arrayList2.add(5);
        System.out.println("arrayList2 - " + arrayList2);

        if (arrayList1.retainAll(arrayList2)) {
            System.out.println("arrayList1 after retaining arrayList2: " + arrayList1);
        } else {
            System.out.println("Lists don't intersect");
        }

        if (arrayList1.addAll(1, arrayList2)) {
            System.out.println("Add all elements from arrayList2 to arrayList1 at index '1': " + arrayList1);
        } else {
            System.out.println("Added list is empty");
        }

        System.out.println("arrayList1 contains all elements from arrayList2: " + arrayList1.containsAll(arrayList2));

        arrayList1.trimToSize();
        arrayList1.clear();

        System.out.println("arrayList1 after removing all elements: " + arrayList1);
    }
}