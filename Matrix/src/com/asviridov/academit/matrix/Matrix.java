package com.asviridov.academit.matrix;

import com.asviridov.academit.vector.Vector;

public class Matrix {
    private Vector[] rows;

    public Matrix(int n, int m) {
        if (n <= 0 || m <= 0) {
            throw new IllegalArgumentException("Vector dimensions must be greater than 0. " + "Current values: " + n + ", " + m);
        }

        rows = new Vector[n];

        for (int i = 0; i < n; i++) {
            rows[i] = new Vector(m);
        }
    }

    public Matrix(Matrix matrix) {
        rows = new Vector[matrix.rows.length];

        for (int i = 0; i < matrix.rows.length; i++) {
            rows[i] = new Vector(matrix.rows[i]);
        }
    }

    public Matrix(double[][] values) {
        if (values.length == 0 || values[0].length == 0) {
            throw new IllegalArgumentException("Vector dimensions must be greater than 0. " + "Current values: " + values.length + ", " + values[0].length);
        }

        rows = new Vector[values.length];
        int colLength = values[0].length;

        for (int i = 0; i < values.length; i++) {
            rows[i] = new Vector(colLength, values[i]);
        }
    }

    public Matrix(Vector[] vectors) {
        if (vectors.length == 0 || vectors[0].getSize() == 0) {
            throw new IllegalArgumentException("Vector dimensions must be greater than 0. " + "Current values: " + vectors.length + ", " + vectors[0].getSize());
        }

        rows = new Vector[vectors.length];
        int colLength = vectors[0].getSize();

        for (int i = 0; i < vectors.length; i++) {
            rows[i] = new Vector(colLength);
            rows[i].add(vectors[i]);
        }
    }

    public int getRowCount() {
        return rows.length;
    }

    public int getColumnCount() {
        return rows[0].getSize();
    }

    public Vector getRow(int index) {
        return rows[index];
    }

    public void setRow(int index, Vector row) {
        if (row.getSize() != getColumnCount()) {
            throw new IllegalArgumentException("Row size must match matrix column count");
        }

        rows[index] = new Vector(row);
    }

    public Vector getColumn(int index) {
        Vector column = new Vector(rows.length);

        for (int i = 0; i < rows.length; i++) {
            column.setComponent(i, rows[i].getComponent(index));
        }

        return column;
    }

    public Matrix transpose() {
        int n = getColumnCount();
        int m = getRowCount();
        Matrix transposed = new Matrix(n, m);

        for (int i = 0; i < n; i++) {
            transposed.setRow(i, getColumn(i));
        }

        return transposed;
    }

    public void multiply(double scalar) {
        for (Vector row : rows) {
            row.multiply(scalar);
        }
    }

    public double determinant() {
        if (getRowCount() != getColumnCount()) {
            throw new UnsupportedOperationException("Matrix must be square for determinant calculation");
        }

        int rowCount = getRowCount();

        if (rowCount == 1) {
            return rows[0].getComponent(0);
        }

        if (rowCount == 2) {
            return rows[0].getComponent(0) * rows[1].getComponent(1)
                    - rows[0].getComponent(1) * rows[1].getComponent(0);
        }

        double det = 0;

        for (int i = 0; i < getColumnCount(); i++) {
            det += Math.pow(-1, i) * rows[0].getComponent(i) * submatrix(i).determinant();
        }

        return det;
    }

    private Matrix submatrix(int colToRemove) {
        int n = getRowCount() - 1;
        int m = getColumnCount() - 1;
        Matrix sub = new Matrix(n, m);
        int subRow = 0;
        int subCol;

        for (int i = 0; i < getRowCount(); i++) {
            if (i == 0) {
                continue;
            }

            subCol = 0;

            for (int j = 0; j < getColumnCount(); j++) {
                if (j == colToRemove) {
                    continue;
                }

                sub.getRow(subRow).setComponent(subCol, rows[i].getComponent(j));
                subCol++;
            }

            subRow++;
        }

        return sub;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('{');

        for (Vector row : rows) {
            sb.append(row);
            sb.append(", ");
        }

        sb.setLength(sb.length() - 2);
        sb.append('}');

        return sb.toString();
    }

    public void matrixVectorMultiply(Vector vector) {
        if (getColumnCount() != vector.getSize()) {
            throw new IllegalArgumentException("Matrix column count must match vector size");
        }

        Vector[] result = new Vector[getRowCount()];

        for (int i = 0; i < getRowCount(); i++) {
            double dotProduct = Vector.getDotProduct(rows[i], vector);
            result[i] = new Vector(new double[]{dotProduct});
        }

        rows = result;
    }

    public void add(Matrix other) {
        if (getRowCount() != other.getRowCount() || getColumnCount() != other.getColumnCount()) {
            throw new IllegalArgumentException("Matrix dimensions must match for addition");
        }

        for (int i = 0; i < rows.length; i++) {
            rows[i].add(other.rows[i]);
        }
    }

    public void subtract(Matrix other) {
        if (getRowCount() != other.getRowCount() || getColumnCount() != other.getColumnCount()) {
            throw new IllegalArgumentException("Matrix dimensions must match for subtraction");
        }

        for (int i = 0; i < rows.length; i++) {
            rows[i].subtract(other.rows[i]);
        }
    }

    public static Matrix add(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowCount() != matrix2.getRowCount() ||
                matrix1.getColumnCount() != matrix2.getColumnCount()) {
            throw new IllegalArgumentException("Matrix dimensions must match for addition");
        }

        Matrix result = new Matrix(matrix1);
        result.add(matrix2);
        return result;
    }

    public static Matrix subtract(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowCount() != matrix2.getRowCount() ||
                matrix1.getColumnCount() != matrix2.getColumnCount()) {
            throw new IllegalArgumentException("Matrix dimensions must match for subtraction");
        }

        Matrix result = new Matrix(matrix1);
        result.subtract(matrix2);
        return result;
    }

    public static Matrix multiply(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnCount() != matrix2.getRowCount()) {
            throw new IllegalArgumentException("Matrix dimensions are not compatible for multiplication");
        }

        int n = matrix1.getRowCount();
        int m = matrix2.getColumnCount();
        Matrix result = new Matrix(n, m);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Vector row = matrix1.getRow(i);
                Vector column = matrix2.getColumn(j);
                double dotProduct = Vector.getDotProduct(row, column);
                result.rows[i].setComponent(j, dotProduct);
            }
        }

        return result;
    }
}
