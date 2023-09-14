package com.asviridov.academit.array_list_home_main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class ArrayListHomeMain {
    public static ArrayList<String> getLinesFromFile(String filename) {
        ArrayList<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading file: " + e.getMessage());
            e.printStackTrace();
        }

        return lines;
    }

    public static void removeEvenNumbers(ArrayList<Integer> numbers) {
        numbers.removeIf(num -> num % 2 == 0);
    }

    public static ArrayList<Integer> getListWithoutDuplicates(ArrayList<Integer> originalList) {
        ArrayList<Integer> uniqueList = new ArrayList<>();

        for (Integer num : originalList) {
            if (!uniqueList.contains(num)) {
                uniqueList.add(num);
            }
        }

        return uniqueList;
    }

    public static void main(String[] args) {
        ArrayList<String> lines = getLinesFromFile("ArrayListHome/src/input.txt");

        System.out.println("Task 1: Lines from file");
        for (String line : lines) {
            System.out.println(line);
        }

        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        removeEvenNumbers(numbers);

        System.out.println("Task 2: Remove odd numbers");
        for (Integer num : numbers) {
            System.out.println(num);
        }

        ArrayList<Integer> originalList = new ArrayList<>(Arrays.asList(1, 5, 2, 1, 3, 5));
        ArrayList<Integer> uniqueList = getListWithoutDuplicates(originalList);

        System.out.println("Task 3: Unique list");
        for (Integer num : uniqueList) {
            System.out.println(num);
        }
    }
}