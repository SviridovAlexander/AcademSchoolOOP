package com.asviridov.academit.vector;

import java.util.Arrays;

public class Vector {
    private final double[] components;

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Vector dimension must be greater than 0");
        }

        components = new double[n];
    }

    public Vector(Vector other) {
        components = Arrays.copyOf(other.components, other.components.length);
    }

    public Vector(double[] values) {
        components = Arrays.copyOf(values, values.length);
    }

    public Vector(int n, double[] values) {
        if (n <= 0) {
            throw new IllegalArgumentException("Vector dimension must be greater than 0");
        }

        components = new double[n];
        for (int i = 0; i < values.length && i < n; i++) {
            components[i] = values[i];
        }
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

    public void add(Vector other) {
        for (int i = 0; i < Math.min(components.length, other.components.length); i++) {
            components[i] += other.components[i];
        }
    }

    public void subtract(Vector other) {
        for (int i = 0; i < Math.min(components.length, other.components.length); i++) {
            components[i] -= other.components[i];
        }
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
        sb.append("{");
        for (int i = 0; i < components.length; i++) {
            sb.append(components[i]);

            if (i < components.length - 1) {
                sb.append(", ");
            }
        }

        sb.append("}");
        return sb.toString();
    }

    public static Vector add(Vector v1, Vector v2) {
        int maxLength = Math.max(v1.getSize(), v2.getSize());
        double[] resultComponents = new double[maxLength];
        for (int i = 0; i < maxLength; i++) {
            double v1Component = i < v1.getSize() ? v1.getComponent(i) : 0;
            double v2Component = i < v2.getSize() ? v2.getComponent(i) : 0;
            resultComponents[i] = v1Component + v2Component;
        }

        return new Vector(resultComponents);
    }

    public static Vector subtract(Vector v1, Vector v2) {
        int maxLength = Math.max(v1.getSize(), v2.getSize());
        double[] resultComponents = new double[maxLength];
        for (int i = 0; i < maxLength; i++) {
            double v1Component = i < v1.getSize() ? v1.getComponent(i) : 0;
            double v2Component = i < v2.getSize() ? v2.getComponent(i) : 0;
            resultComponents[i] = v1Component - v2Component;
        }

        return new Vector(resultComponents);
    }

    public static double dotProduct(Vector v1, Vector v2) {
        int minLength = Math.min(v1.getSize(), v2.getSize());
        double result = 0;
        for (int i = 0; i < minLength; i++) {
            result += v1.getComponent(i) * v2.getComponent(i);
        }

        return result;
    }
}
