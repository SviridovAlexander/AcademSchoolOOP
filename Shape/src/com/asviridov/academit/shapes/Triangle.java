package com.asviridov.academit.shapes;

public class Triangle implements Shape {
    private final double x1;
    private final double y1;
    private final double x2;
    private final double y2;
    private final double x3;
    private final double y3;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    public double getX1() {
        return x1;
    }

    public double getY1() {
        return y1;
    }

    public double getX2() {
        return x2;
    }

    public double getY2() {
        return y2;
    }

    public double getX3() {
        return x3;
    }

    public double getY3() {
        return y3;
    }

    @Override
    public double getWidth() {
        return Math.max(x1, Math.max(x2, x3)) - Math.min(x1, Math.min(x2, x3));
    }

    @Override
    public double getHeight() {
        return Math.max(y1, Math.max(y2, y3)) - Math.min(y1, Math.min(y2, y3));
    }

    @Override
    public double getArea() {
        double side1Length = calculateSideLength(x1, y1, x2, y2);
        double side2Length = calculateSideLength(x2, y2, x3, y3);
        double side3Length = calculateSideLength(x3, y3, x1, y1);

        double perimeterHalf = (side1Length + side2Length + side3Length) / 2;

        return Math.sqrt(perimeterHalf
                * (perimeterHalf - side1Length)
                * (perimeterHalf - side2Length)
                * (perimeterHalf - side3Length));
    }

    @Override
    public double getPerimeter() {
        double side1Length = calculateSideLength(x1, y1, x2, y2);
        double side2Length = calculateSideLength(x2, y2, x3, y3);
        double side3Length = calculateSideLength(x3, y3, x1, y1);
        return side1Length + side2Length + side3Length;
    }

    @Override
    public String toString() {
        return "Triangle [(" + x1 + "; " + y1 + "), (" + x2 + "; " + y2 + "), (" + x3 + "; " + y3 + ")]";
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + Double.hashCode(x1);
        hash = prime * hash + Double.hashCode(y1);
        hash = prime * hash + Double.hashCode(x2);
        hash = prime * hash + Double.hashCode(y2);
        hash = prime * hash + Double.hashCode(x3);
        hash = prime * hash + Double.hashCode(y3);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Triangle triangle = (Triangle) obj;
        return triangle.x1 == x1 && triangle.y1 == y1
                && triangle.x2 == x2 && triangle.y2 == y2
                && triangle.x3 == x3 && triangle.y3 == y3;
    }

    private static double calculateSideLength(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
}
