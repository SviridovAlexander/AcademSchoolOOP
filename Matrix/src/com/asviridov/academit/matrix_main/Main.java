package com.asviridov.academit.matrix_main;

import com.asviridov.academit.matrix.Matrix;
import com.asviridov.academit.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Matrix matrix1 = new Matrix(new double[][]{{1, 2, 3}, {4, 5, 6}});
        Matrix matrix2 = new Matrix(new double[][]{{7, 8}, {9, 10}, {11, 12}});
        Vector vector1 = new Vector(new double[]{2, 7, 4});
        Matrix matrix3 = new Matrix(new Vector[]{vector1, vector1});
        Matrix matrixForDeterminantTest = new Matrix(new double[][]{{1, 2, 4, 5}, {3, 44, 1, -4}, {0, 4, -3, 8}, {5, 6, 5, 3}});

        System.out.println("matrix1:" + System.lineSeparator() + matrix1);
        System.out.println("matrix2:" + System.lineSeparator() + matrix2);
        System.out.println("m3:" + System.lineSeparator() + matrix3);

        matrix1.transpose();
        System.out.println("matrix1 transposed:" + System.lineSeparator() + matrix1);

        matrix1.multiply(2);
        System.out.println("matrix1 after multiplication:" + System.lineSeparator() + matrix1);

        System.out.println("Determinant of matrixForDeterminantTest: " + matrixForDeterminantTest.getDeterminant());

        System.out.println("matrix3 after matrix-vector multiplication:" + System.lineSeparator() + matrix3.multiplyByVector(vector1));

        matrix3.multiplyByVector(vector1);
        matrix1.add(matrix2);
        System.out.println("matrix1 after addition with matrix2:" + System.lineSeparator() + matrix1);

        matrix2.subtract(matrix1);
        System.out.println("matrix2 after subtraction of matrix1:" + System.lineSeparator() + matrix3);

        Matrix matrix4 = Matrix.getSum(matrix1, matrix2);
        System.out.println("matrix4 = matrix1 + matrix2:" + System.lineSeparator() + matrix4);

        Matrix matrix5 = Matrix.getDifference(matrix2, matrix2);
        System.out.println("matrix5 = matrix2 - matrix2:" + System.lineSeparator() + matrix5);

        Matrix matrix6 = Matrix.getMatrixMultiplication(matrix2, matrix3);
        System.out.println("matrix6 = matrix2 * matrix3:" + System.lineSeparator() + matrix6);
    }
}
