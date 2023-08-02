package com.asviridov.academit.range_main;

import com.asviridov.academit.range.Range;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Range range1 = new Range(5.0, 10.0);
        System.out.println("Range from " + range1.getFrom() + " to " + range1.getTo());
        System.out.println("Length of the range: " + range1.getLength());

        double number = 7.5;
        System.out.println(number + " is inside the range: " + range1.isInside(number));

        range1.setFrom(-10);
        range1.setTo(0);
        System.out.println("Range from " + range1.getFrom() + " to " + range1.getTo());
        System.out.println("Length of the range: " + range1.getLength());

        System.out.println(number + " is inside the range: " + range1.isInside(number));

        Range range2 = new Range(1, 10);
        Range range3 = new Range(5, 7);

        Range intersection = range2.getIntersection(range3);
        System.out.println("Intersection:");

        System.out.println(intersection != null ? intersection : "[]");

        Range[] union = range2.getUnion(range3);
        System.out.println("Union:");

        System.out.println(Arrays.toString(union));

        Range[] difference = range2.getDifference(range3);
        System.out.println("Difference:");

        System.out.println(Arrays.toString(difference));
    }
}
