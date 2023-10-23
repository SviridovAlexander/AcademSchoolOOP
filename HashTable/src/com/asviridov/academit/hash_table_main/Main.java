package com.asviridov.academit.hash_table_main;


import com.asviridov.academit.hash_table.HashTable;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> numbers1 = new ArrayList<>(Arrays.asList(6, -9, 100, 8));

        HashTable<Integer> hashTable = new HashTable<>();
        hashTable.add(25);
        hashTable.add(null);
        hashTable.addAll(numbers1);

        System.out.println("hashTable:");
        System.out.println(hashTable);

        System.out.println("hashTable size: " + hashTable.size());

        System.out.println("Check if hashTable is empty: " + hashTable.isEmpty());

        int searchNumber = 15;

        if (hashTable.contains(searchNumber)) {
            System.out.println("The hash table contains the element " + searchNumber);
        } else {
            System.out.println("The element " + searchNumber + " is not found in the hash table");
        }

        System.out.print("Iterator check: ");
        for (Integer number : hashTable) {
            System.out.print(number + " ");
        }
        System.out.println();

        System.out.println("hashTable using the toArray() method: " + Arrays.toString(hashTable.toArray()));
        System.out.println("hashTable using the toArray(T[] a) method: " + Arrays.toString(hashTable.toArray(new Integer[10])));

        Integer removedNumber = 25;
        if (hashTable.remove(removedNumber)) {
            System.out.println("The hash table after removing the element " + removedNumber + ":");
            System.out.println(hashTable);
        } else {
            System.out.println("Removal is not possible. The element " + removedNumber + " is not found");
        }

        if (hashTable.containsAll(numbers1)) {
            System.out.println("The hash table contains the numbers1 list");
        } else {
            System.out.println("The hash table does not contain the numbers1 list");
        }

        ArrayList<Integer> numbers2 = new ArrayList<>(Arrays.asList(null, 100, 67));

        if (hashTable.removeAll(numbers2)) {
            System.out.println("The hash table after removing the numbers2 list:");
            System.out.println(hashTable);
        } else {
            System.out.println("Removal of the list is not possible");
        }

        ArrayList<Integer> numbers3 = new ArrayList<>(Arrays.asList(12, 15, 90, 8, 0));

        if (hashTable.retainAll(numbers3)) {
            System.out.println("Intersection of the hash table with the numbers3 list:");
            System.out.println(hashTable);
        } else {
            System.out.println("No intersection of the hash table with the numbers3 list was found");
        }

        hashTable.clear();
        System.out.println("The hash table after clearing:");
        System.out.println(hashTable);
    }
}