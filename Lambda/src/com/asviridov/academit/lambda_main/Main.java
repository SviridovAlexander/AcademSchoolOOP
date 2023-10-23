package com.asviridov.academit.lambda_main;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<Person> persons = Arrays.asList(
                new Person("Ivan", 25),
                new Person("Sergey", 18),
                new Person("Peter", 15),
                new Person("Ivan", 30),
                new Person("Anna", 22),
                new Person("Igor", 13)
        );

        List<String> uniqueNames = persons.stream()
                .map(Person::name)
                .distinct()
                .toList();

        System.out.println(uniqueNames.stream().collect(Collectors.joining(", ", "Names: ", ".")));
        System.out.println();

        List<Person> personsUnder18 = persons.stream()
                .filter(person -> person.age() < 18)
                .toList();

        personsUnder18.stream()
                .mapToInt(Person::age)
                .average()
                .ifPresentOrElse(averageAge -> System.out.println("Average age of persons under 18: " + averageAge),
                        () -> System.out.println("There are no people over 18"));
        System.out.println();

        Map<String, Double> nameToAverageAgeMap = persons.stream()
                .collect(Collectors.groupingBy(
                        Person::name,
                        Collectors.averagingInt(Person::age)
                ));

        System.out.println("Average age by name: " + nameToAverageAgeMap);
        System.out.println();

        List<Person> personsFrom20To45 = persons.stream()
                .filter(person -> person.age() >= 20 && person.age() <= 45)
                .sorted((person1, person2) -> Integer.compare(person2.age(), person1.age()))
                .toList();

        personsFrom20To45.forEach(person -> System.out.println(person.name() + " - " + person.age()));
        System.out.println();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of elements to calculate roots: ");
        int elementsCount = scanner.nextInt();

        DoubleStream squareRoots = DoubleStream.iterate(0, n -> n + 1)
                .map(Math::sqrt)
                .limit(elementsCount);

        System.out.println("Number Roots:");
        squareRoots.forEach(System.out::println);
        System.out.println();

        System.out.println("Generating a stream of Fibonacci numbers:");
        Stream.iterate(new int[]{0, 1}, f -> new int[]{f[1], f[0] + f[1]})
                .map(f -> f[0])
                .limit(elementsCount)
                .forEach(System.out::println);
    }
}
