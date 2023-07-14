package com.asviridov.academit.range_main;

import com.asviridov.academit.range.Range;

public class Main {
    public static void main(String[] args) {
        Range range1 = new Range(5.0, 10.0);
        System.out.println("Range from " + range1.getFrom() + " to " + range1.getTo());
        System.out.println("Length of the range: " + range1.getLength());

        double number = 7.5;
        boolean isInsideRange = range1.isInside(number);
        System.out.println(number + " is inside the range: " + isInsideRange);

        range1.setFrom(-10);
        range1.setTo(0);
        System.out.println("Range from " + range1.getFrom() + " to " + range1.getTo());
        System.out.println("Length of the range: " + range1.getLength());

        isInsideRange = range1.isInside(number);
        System.out.println(number + " is inside the range: " + isInsideRange);

        Range range2 = new Range(1, 10);
        Range range3 = new Range(5, 15);

        Range intersection = range2.getIntersection(range3);
        if (intersection != null) {
            System.out.println("Intersection: ");
            System.out.println(intersection.getFrom() + " - " + intersection.getTo());

        } else {
            System.out.println("No intersection");
        }

        Range[] union = range2.getUnion(range3);
        System.out.println("Union:");
        for (Range e : union) {
            System.out.println(e.getFrom() + " - " + e.getTo());
        }

        Range[] difference = range2.getDifference(range3);
        System.out.println("Difference:");
        for (Range e : difference) {
            System.out.println(e.getFrom() + " - " + e.getTo());
        }
    }
}
