package com.asviridov.academit.matrix_main;

import com.asviridov.academit.matrix.Matrix;
import com.asviridov.academit.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Matrix m1 = new Matrix(new double[][]{{1, 2, 3}, {4, 5, 6}});
        Matrix m2 = new Matrix(new double[][]{{7, 8}, {9, 10}, {11, 12}});
        Vector v1 = new Vector(new double[]{2, 7, 4});
        Matrix m3 = new Matrix(new Vector[]{v1, v1});
        Matrix mForDeterminantTest = new Matrix(new double[][]{{1, 2, 4, 5}, {3, 44, 1, -4}, {0, 4, -3, 8}, {5, 6, 5, 3}});

        System.out.println("m1:\n" + m1);
        System.out.println("m2:\n" + m2);
        System.out.println("m3:\n" + m3);

        System.out.println("m1 transposed:\n" + m1.transpose());

        m1.multiply(2);
        System.out.println("m1 after multiplication:\n" + m1);

        System.out.println("Determinant of mForDeterminantTest: " + mForDeterminantTest.determinant());

        m1.matrixVectorMultiply(v1);
        System.out.println("m1 after matrix-vector multiplication:\n" + m1);

        m3.matrixVectorMultiply(v1);
        m1.add(m3);
        System.out.println("m1 after addition with m3:\n" + m1);

        m3.subtract(m1);
        System.out.println("m3 after subtraction of m1:\n" + m3);

        Matrix m4 = Matrix.add(m1, m3);
        System.out.println("m4 = m1 + m3:\n" + m4);

        Matrix m5 = Matrix.subtract(m2, m2);
        System.out.println("m5 = m2 - m2:\n" + m5);

        Matrix m6 = Matrix.multiply(m2, m3);
        System.out.println("m6 = m2 * m3:\n" + m6);
    }
}
