package com.asviridov.academit.shape_main;

import com.asviridov.academit.shape.*;

import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Shape[] shapes = new Shape[8];
        shapes[0] = new Square(5); // Area: 25, Perimeter: 20
        shapes[1] = new Triangle(0, 0, 0, 4, 3, 0); // Area: 6.0, Perimeter: 12.0
        shapes[2] = new Rectangle(4, 6); // Area: 24, Perimeter: 20
        shapes[3] = new Circle(3); // Area: 28.27, Perimeter: 18.85
        shapes[4] = new Circle(5); // Area: 78.54, Perimeter: 31.42
        shapes[5] = new Square(3); // Area: 9, Perimeter: 12
        shapes[6] = new Triangle(0, 0, 0, 5, 4, 0); // Area: 10.0, Perimeter: 15.0
        shapes[7] = new Rectangle(5, 8); // Area: 40, Perimeter: 26

        findFigureWithMaxArea(shapes);
        findFigureWithSecondLargestPerimeter(shapes);
    }

    public static void findFigureWithMaxArea(Shape[] shapes) {
        Arrays.sort(shapes, new AreaComparator());
        Shape maxAreaShape = shapes[shapes.length - 1];
        System.out.println("Shape with maximum area:");
        printShapeInfo(maxAreaShape);
    }

    public static void findFigureWithSecondLargestPerimeter(Shape[] shapes) {
        Arrays.sort(shapes, new PerimeterComparator());
        Shape secondLargestPerimeterShape = shapes[shapes.length - 2];
        System.out.println("Shape with second largest perimeter:");
        printShapeInfo(secondLargestPerimeterShape);
    }

    public static void printShapeInfo(Shape shape) {
        System.out.println(shape);
        System.out.println("Width: " + shape.getWidth());
        System.out.println("Height: " + shape.getHeight());
        System.out.println("Area: " + shape.getArea());
        System.out.println("Perimeter: " + shape.getPerimeter());
        System.out.println();
    }

    static class AreaComparator implements Comparator<Shape> {
        @Override
        public int compare(Shape shape1, Shape shape2) {
            return Double.compare(shape1.getArea(), shape2.getArea());
        }
    }

    static class PerimeterComparator implements Comparator<Shape> {
        @Override
        public int compare(Shape shape1, Shape shape2) {
            return Double.compare(shape1.getPerimeter(), shape2.getPerimeter());
        }
    }
}
