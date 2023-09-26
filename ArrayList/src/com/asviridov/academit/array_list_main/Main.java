package com.asviridov.academit.array_list_main;

import com.asviridov.academit.array_list.ArrayList;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> ArrayList1 = new ArrayList<>(5);

        ArrayList1.add(1);
        ArrayList1.add(2);
        ArrayList1.add(3);
        ArrayList1.add(4);
        ArrayList1.add(10);
        ArrayList1.add(3);
        ArrayList1.add(2);
        ArrayList1.add(6, 7);

        System.out.println("ArrayList1: " + ArrayList1);

        System.out.println("ArrayList1 size: " + ArrayList1.size());

        System.out.println("Check if ArrayList1 is empty: " + ArrayList1.isEmpty());

        System.out.println("Check if ArrayList1 contains '4': " + ArrayList1.contains(4));
        System.out.println("Check if ArrayList1 contains '111': " + ArrayList1.contains(111));

        System.out.println("ArrayList1 to array: " + Arrays.toString(ArrayList1.toArray()));

        System.out.println("Deleted element at index '0': " + ArrayList1.remove(0));
        System.out.println("ArrayList1 after removal: " + ArrayList1);

        if (ArrayList1.indexOf(3) != -1) {
            System.out.println("Index of the first element '3' found: " + ArrayList1.indexOf(3));
        } else {
            System.out.println("Element '3' not found");
        }

        if (ArrayList1.lastIndexOf(2) != -1) {
            System.out.println("Index of the last element '2' found: " + ArrayList1.lastIndexOf(2));
        } else {
            System.out.println("Element '2' not found");
        }

        ArrayList1.set(0, 10);
        System.out.println("Set '10' at index '0': " + ArrayList1);

        System.out.println("Element at index '2': " + ArrayList1.get(2));

        ArrayList<Integer> ArrayList2 = new ArrayList<>();
        ArrayList2.add(3);
        ArrayList2.add(2);
        ArrayList2.add(5);
        System.out.println("ArrayList2 - " + ArrayList2);

        if (ArrayList1.retainAll(ArrayList2)) {
            System.out.println("ArrayList1 after retaining ArrayList2: " + ArrayList1);
        } else {
            System.out.println("Lists don't intersect");
        }

        if (ArrayList1.addAll(1, ArrayList2)) {
            System.out.println("Add all elements from ArrayList2 to ArrayList1 at index '1': " + ArrayList1);
        } else {
            System.out.println("Added list is empty");
        }

        System.out.println("ArrayList1 contains all elements from ArrayList2: " + ArrayList1.containsAll(ArrayList2));

        ArrayList1.trimToSize();

        ArrayList1.clear();

        System.out.println("ArrayList1 after removing all elements: " + ArrayList1);
    }
}