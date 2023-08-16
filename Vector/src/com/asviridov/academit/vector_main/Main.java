package com.asviridov.academit.vector_main;

import com.asviridov.academit.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Vector v0 = new Vector(3);
        Vector v1 = new Vector(3);
        Vector v2 = new Vector(new double[]{1, 2, 3});
        Vector v3 = new Vector(5, new double[]{4, 5});
        Vector v4 = new Vector(v3);

        System.out.println("v1: " + v1);
        System.out.println("v2: " + v2);
        System.out.println("v3: " + v3);
        System.out.println("v4: " + v4);

        System.out.println("v2 length: " + v2.getLength());

        v1.add(v2);
        System.out.println("v1 after adding v2: " + v1);

        v2.subtract(v3);
        System.out.println("v2 after subtracting v3: " + v2);

        v3.multiply(2);
        System.out.println("v3 after multiplication: " + v3);

        v4.reverse();
        System.out.println("v4 after reversal: " + v4);

        for (int i = 0; i < v0.getSize(); i++) {
            v0.setComponent(i, i + 1);
        }

        System.out.println("v0 equals v1: " + v0.equals(v1));
        System.out.println("v3 equals v4: " + v3.equals(v4));

        System.out.println("Dot product of v2 and v3: " + Vector.dotProduct(v2, v3));

        Vector v5 = Vector.add(v1, v3);
        System.out.println("v5 = v1 + v3: " + v5);

        Vector v6 = Vector.subtract(v4, v2);
        System.out.println("v6 = v4 - v2: " + v6);
    }
}
