package com.asviridov.academit.vector_main;

import com.asviridov.academit.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Vector v1 = new Vector(3);
        Vector v2 = new Vector(3);
        Vector v3 = new Vector(new double[]{1, 2, 3});
        Vector v4 = new Vector(5, new double[]{4, 5});
        Vector v5 = new Vector(v4);

        System.out.println("v1: " + v2);
        System.out.println("v2: " + v3);
        System.out.println("v3: " + v4);
        System.out.println("v4: " + v5);

        System.out.println("v2 length: " + v3.getLength());

        v2.add(v3);
        System.out.println("v1 after adding v2: " + v2);

        v3.subtract(v4);
        System.out.println("v2 after subtracting v3: " + v3);

        v4.multiply(2);
        System.out.println("v3 after multiplication: " + v4);

        v5.reverse();
        System.out.println("v4 after reversal: " + v5);

        for (int i = 0; i < v1.getSize(); i++) {
            v1.setComponent(i, i + 1);
        }

        System.out.println("v0 equals v1: " + v1.equals(v2));
        System.out.println("v3 equals v4: " + v4.equals(v5));

        System.out.println("Dot product of v2 and v3: " + Vector.getDotProduct(v3, v4));

        Vector v6 = Vector.getSum(v2, v4);
        System.out.println("v5 = v1 + v3: " + v6);

        Vector v7 = Vector.getDifference(v5, v3);
        System.out.println("v6 = v4 - v2: " + v7);
    }
}
