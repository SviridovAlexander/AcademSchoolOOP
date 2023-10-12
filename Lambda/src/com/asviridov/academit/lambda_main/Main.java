package com.asviridov.academit.lambda_main;

import com.asviridov.academit.Person.Person;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        people.add(new Person("Ivan", 25));
        people.add(new Person("Sergey", 18));
        people.add(new Person("Peter", 15));
        people.add(new Person("Ivan", 30));
        people.add(new Person("Anna", 22));
        people.add(new Person("Igor", 13));

        List<String> uniqueNames = people.stream()
                .map(Person::name)
                .distinct()
                .toList();

        System.out.println(uniqueNames.stream().collect(Collectors.joining(", ", "Names: ", ".")));
        System.out.println();

        List<Person> personsUnder18 = people.stream()
                .filter(person -> person.age() < 18)
                .toList();

        personsUnder18.stream()
                .mapToInt(Person::age)
                .average()
                .ifPresent(averageAge -> System.out.println("Average age of people under 18: " + averageAge));
        System.out.println();

        Map<String, Double> nameToAverageAgeMap = people.stream()
                .collect(Collectors.groupingBy(
                        Person::name,
                        Collectors.averagingInt(Person::age)
                ));

        System.out.println("Average age by name: " + nameToAverageAgeMap);
        System.out.println();

        List<Person> ageBetween20And45 = people.stream()
                .filter(person -> person.age() >= 20 && person.age() <= 45)
                .sorted((person1, person2) -> Integer.compare(person2.age(), person1.age()))
                .toList();

        ageBetween20And45.forEach(person -> System.out.println(person.name() + " - " + person.age()));
        System.out.println();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of elements to calculate roots: ");
        int numElements = scanner.nextInt();
        Stream<Double> squareRoots = Stream.iterate(0, n -> n + 1)
                .map(Math::sqrt)
                .limit(numElements);

        System.out.println("Number Roots:");
        squareRoots.forEach(System.out::println);
        System.out.println();

        System.out.println("Generating a stream of Fibonacci numbers:");
        Stream.iterate(new int[]{0, 1}, f -> new int[]{f[1], f[0] + f[1]})
                .map(f -> f[0])
                .limit(numElements)
                .forEach(System.out::println);
    }
}
