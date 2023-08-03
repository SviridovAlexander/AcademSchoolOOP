package com.asviridov.academit.shapes_main;

import com.asviridov.academit.shapes.Shape;

import java.util.Comparator;

class ShapeAreaComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape shape1, Shape shape2) {
        return Double.compare(shape1.getArea(), shape2.getArea());
    }
}
