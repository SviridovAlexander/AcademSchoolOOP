package com.asviridov.academit.vector;

import java.util.Arrays;

public class Vector {
    private double[] components;

    public Vector(int dimension) {
        if (dimension <= 0) {
            throw new IllegalArgumentException("Vector dimension must be greater than 0. " + "Current value: " + dimension);
        }

        components = new double[dimension];
    }

    public Vector(Vector vector) {
        components = Arrays.copyOf(vector.components, vector.components.length);
    }

    public Vector(double[] values) {
        if (values.length == 0) {
            throw new IllegalArgumentException("Vector dimension must be greater than 0. " + "Current value: " + values.length);
        }

        components = Arrays.copyOf(values, values.length);
    }

    public Vector(int dimension, double[] values) {
        if (dimension <= 0) {
            throw new IllegalArgumentException("Vector dimension must be greater than 0. " + "Current value: " + dimension);
        }

        components = new double[dimension];
        System.arraycopy(values, 0, components, 0, Math.min(dimension, values.length));
    }

    public int getSize() {
        return components.length;
    }

    public double getComponent(int index) {
        return components[index];
    }

    public void setComponent(int index, double value) {
        components[index] = value;
    }

    public double getLength() {
        double sum = 0;

        for (double component : components) {
            sum += component * component;
        }

        return Math.sqrt(sum);
    }

    public void add(Vector vector) {
        int newLength = Math.max(components.length, vector.components.length);
        double[] result = new double[newLength];

        for (int i = 0; i < newLength; i++) {
            double component1 = (i < components.length) ? components[i] : 0;
            double component2 = (i < vector.components.length) ? vector.components[i] : 0;
            result[i] = component1 + component2;
        }

        components = result;
    }

    public void subtract(Vector vector) {
        int newLength = Math.max(components.length, vector.components.length);
        double[] result = new double[newLength];

        for (int i = 0; i < newLength; i++) {
            double component1 = (i < components.length) ? components[i] : 0;
            double component2 = (i < vector.components.length) ? vector.components[i] : 0;
            result[i] = component1 - component2;
        }

        components = result;
    }

    public void multiply(double scalar) {
        for (int i = 0; i < components.length; i++) {
            components[i] *= scalar;
        }
    }

    public void reverse() {
        multiply(-1);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if ((obj == null) || (getClass() != obj.getClass())) {
            return false;
        }

        Vector vector = (Vector) obj;
        return Arrays.equals(components, vector.components);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(components);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('{');

        for (double component : components) {
            sb.append(component);
            sb.append(", ");
        }

        sb.setLength(sb.length() - 2);
        sb.append('}');

        return sb.toString();
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        int maxSize = Math.max(vector1.components.length, vector2.components.length);

        Vector result = new Vector(maxSize);

        result.add(vector1);
        result.add(vector2);

        return result;
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        int maxSize = Math.max(vector1.components.length, vector2.components.length);

        Vector result = new Vector(maxSize);

        result.add(vector1);
        result.subtract(vector2);

        return result;
    }

    public static double getDotProduct(Vector vector1, Vector vector2) {
        int minSize = Math.min(vector1.components.length, vector2.components.length);
        double result = 0;

        for (int i = 0; i < minSize; i++) {
            result += vector1.components[i] * vector2.components[i];
        }

        return result;
    }
}
