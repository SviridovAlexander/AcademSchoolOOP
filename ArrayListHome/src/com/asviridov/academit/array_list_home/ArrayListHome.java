package com.asviridov.academit.array_list_home;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class ArrayListHome {
    public static ArrayList<String> getLinesFromFile(String fileName) throws IOException {
        ArrayList<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }

        return lines;
    }

    public static void removeEvenNumbers(ArrayList<Integer> numbers) {
        for (int i = numbers.size() - 1; i >= 0; --i) {
            if (numbers.get(i) % 2 == 0) {
                numbers.remove(i);
            }
        }
    }

    public static ArrayList<Integer> getListWithoutDuplicates(ArrayList<Integer> originalList) {
        ArrayList<Integer> uniqueElementsList = new ArrayList<>(originalList.size());

        for (Integer number : originalList) {
            if (!uniqueElementsList.contains(number)) {
                uniqueElementsList.add(number);
            }
        }

        return uniqueElementsList;
    }

    public static void main(String[] args) {
        ArrayList<String> lines;

        try {
            lines = getLinesFromFile("ArrayListHome/src/input.txt");
        } catch (FileNotFoundException e) {
            System.out.println("The specified file cannot be found");
            return;
        } catch (IOException e) {
            System.out.println("An error occurred while reading file: " + e.getMessage());
            return;
        }

        System.out.println("Task 1: Lines from file - " + lines);

        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        System.out.println("Task 2: Remove odd numbers. List before removing - " + numbers);
        removeEvenNumbers(numbers);
        System.out.println("List after removing - " + numbers);

        ArrayList<Integer> originalList = new ArrayList<>(Arrays.asList(1, 5, 2, 1, 3, 5));
        System.out.println("Task 3: Unique list. List before changes - " + originalList);
        ArrayList<Integer> uniqueList = getListWithoutDuplicates(originalList);
        System.out.println("List after changes - " + uniqueList);
    }
}