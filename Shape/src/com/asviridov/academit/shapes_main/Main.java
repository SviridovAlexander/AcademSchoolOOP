package com.asviridov.academit.shapes_main;

import com.asviridov.academit.shapes_comparators.ShapeAreaComparator;
import com.asviridov.academit.shapes_comparators.ShapePerimeterComparator;
import com.asviridov.academit.shapes.*;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Shape[] shapes = {
                new Square(5),
                new Triangle(0, 0, 0, 4, 3, 0),
                new Rectangle(4, 6),
                new Circle(3),
                new Circle(5),
                new Square(3),
                new Triangle(0, 0, 0, 5, 4, 0),
                new Rectangle(5, 8),
        };

        for (Shape shape : shapes) {
            printShapeInfo(shape);
        }

        System.out.println("Shape with the largest area:");

        if (findShapeWithMaxArea(shapes) != null){
            printShapeInfo(shapes[shapes.length - 1]);
        } else {
            System.out.println("No shapes to find maximum area.");
        }

        System.out.println("Shape with the second largest perimeter:");

        if (findShapeWithSecondLargestPerimeter(shapes) != null){
            printShapeInfo(shapes[shapes.length - 2]);
        } else {
            System.out.println("No shapes to find maximum area.");
        }
    }

    public static Shape findShapeWithMaxArea(Shape[] shapes) {
        if (shapes.length == 0) {
            return null;
        }

        Arrays.sort(shapes, new ShapeAreaComparator());
        return shapes[shapes.length - 1];
    }

    public static Shape findShapeWithSecondLargestPerimeter(Shape[] shapes) {
        if (shapes.length <= 1) {
            return null;
        }

        Arrays.sort(shapes, new ShapePerimeterComparator());
        return shapes[shapes.length - 2];
    }

    public static void printShapeInfo(Shape shape) {
        System.out.println(shape);
        System.out.println("Width: " + shape.getWidth());
        System.out.println("Height: " + shape.getHeight());
        System.out.println("Area: " + shape.getArea());
        System.out.println("Perimeter: " + shape.getPerimeter());
        System.out.println();
    }
}