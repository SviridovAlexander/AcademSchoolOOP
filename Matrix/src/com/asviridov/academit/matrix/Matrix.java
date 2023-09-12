package com.asviridov.academit.matrix;

import com.asviridov.academit.vector.Vector;

public class Matrix {
    private Vector[] rows;

    public Matrix(int numberRows, int numberColumns) {
        if (numberRows <= 0) {
            throw new IllegalArgumentException("Number of rows must be greater than 0. Current value " + numberRows);
        }
        if (numberColumns <= 0) {
            throw new IllegalArgumentException("Number of columns must be greater than 0. Current value: " + numberColumns);
        }

        rows = new Vector[numberRows];

        for (int i = 0; i < numberRows; i++) {
            rows[i] = new Vector(numberColumns);
        }
    }

    public Matrix(Matrix matrix) {
        rows = new Vector[matrix.rows.length];

        for (int i = 0; i < matrix.rows.length; i++) {
            rows[i] = new Vector(matrix.rows[i]);
        }
    }

    public Matrix(double[][] values) {
        if (values.length == 0) {
            throw new IllegalArgumentException("Number of rows must be greater than 0. Current value: " + values.length);
        }

        int columnsMaxCount = 0;

        for (double[] row : values) {
            if (row.length > columnsMaxCount) {
                columnsMaxCount = row.length;
            }
        }

        if (columnsMaxCount == 0) {
            throw new IllegalArgumentException("Number of columns must be greater than 0. Current value: " + columnsMaxCount);
        }

        rows = new Vector[values.length];

        for (int i = 0; i < values.length; i++) {
            rows[i] = new Vector(columnsMaxCount, values[i]);
        }
    }

    public Matrix(Vector[] vectors) {
        if (vectors.length == 0 || vectors[0].getSize() == 0) {
            throw new IllegalArgumentException("Vector dimensions must be greater than 0. Current values: " + vectors.length);
        }

        int elementsInVectorMaxCount = 0;

        for (Vector vector : vectors) {
            if (vector.getSize() > elementsInVectorMaxCount) {
                elementsInVectorMaxCount = vector.getSize();
            }
        }

        rows = new Vector[vectors.length];

        for (int i = 0; i < vectors.length; i++) {
            rows[i] = new Vector(elementsInVectorMaxCount);
            rows[i].add(vectors[i]);
        }
    }

    public int getRowsCount() {
        return rows.length;
    }

    public int getColumnsCount() {
        return rows[0].getSize();
    }

    public Vector getRow(int index) {
        if (index < 0 || index >= getRowsCount()) {
            throw new IndexOutOfBoundsException("Row index " + index +
                    " is out of bounds. It must be within [0, " + (getRowsCount() - 1) + "]");
        }

        return new Vector(rows[index]);
    }

    public void setRow(int index, Vector row) {
        if (index < 0 || index >= getRowsCount()) {
            throw new IndexOutOfBoundsException("Row index " + index +
                    " is out of bounds. It must be within [0, " + (getRowsCount() - 1) + "]");
        }

        if (row.getSize() != getColumnsCount()) {
            throw new IllegalArgumentException("Row size (" + row.getSize() +
                    ") must match matrix columns count (" + getColumnsCount() + ")");
        }

        rows[index] = new Vector(row);
    }


    public Vector getColumn(int index) {
        if (index < 0 || index >= getColumnsCount()) {
            throw new IndexOutOfBoundsException("Column index " + index +
                    " is out of bounds. It must be within [0, " + (getColumnsCount() - 1) + "]");
        }

        Vector column = new Vector(rows.length);

        for (int i = 0; i < rows.length; i++) {
            column.setComponent(i, rows[i].getComponent(index));
        }

        return column;
    }

    public void transpose() {
        Vector[] transposedRows = new Vector[getColumnsCount()];

        for (int i = 0; i < getColumnsCount(); ++i) {
            transposedRows[i] = getColumn(i);
        }

        rows = transposedRows;
    }

    public void multiply(double scalar) {
        for (Vector row : rows) {
            row.multiply(scalar);
        }
    }

    public double getDeterminant() {
        if (getRowsCount() != getColumnsCount()) {
            throw new UnsupportedOperationException("Matrix must be square for determinant calculation. Current rows count: " + getRowsCount() +
                    ", and current columns count: " + getColumnsCount());
        }

        int rowCount = getRowsCount();

        if (rowCount == 1) {
            return rows[0].getComponent(0);
        }

        if (rowCount == 2) {
            return rows[0].getComponent(0) * rows[1].getComponent(1)
                    - rows[0].getComponent(1) * rows[1].getComponent(0);
        }

        double determinant = 0;

        for (int i = 0; i < getColumnsCount(); i++) {
            determinant += Math.pow(-1, i) * rows[0].getComponent(i) * getSubmatrix(i).getDeterminant();
        }

        return determinant;
    }

    private Matrix getSubmatrix(int colToRemove) {
        int numberRows = getRowsCount() - 1;
        int numberColumns = getColumnsCount() - 1;
        Matrix submatrix = new Matrix(numberRows, numberColumns);
        int submatrixRow = 0;

        for (int i = 0; i < getRowsCount(); i++) {
            if (i == 0) {
                continue;
            }

            int submatrixColumn  = 0;

            for (int j = 0; j < getColumnsCount(); j++) {
                if (j == colToRemove) {
                    continue;
                }

                submatrix.rows[submatrixRow].setComponent(submatrixColumn, rows[i].getComponent(j));
                submatrixColumn ++;
            }

            submatrixRow++;
        }

        return submatrix;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('{');

        for (Vector row : rows) {
            sb.append(row).append(", ");
        }

        sb.setLength(sb.length() - 2);
        sb.append('}');

        return sb.toString();
    }

    public Vector multiplyByVector(Vector vector) {
        if (getColumnsCount() != vector.getSize()) {
            throw new IllegalArgumentException("Matrix column count (" +
                    getColumnsCount() + ") must match vector size (" + vector.getSize() + ")");
        }

        Vector result = new Vector(getRowsCount());

        for (int i = 0; i < getRowsCount(); i++) {
            result.setComponent(i, Vector.getDotProduct(rows[i], vector));
        }

        return result;
    }

    public void add(Matrix matrix) {
        checkMatrixDimension(this, matrix);

        for (int i = 0; i < rows.length; i++) {
            rows[i].add(matrix.rows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        checkMatrixDimension(this, matrix);

        for (int i = 0; i < rows.length; i++) {
            rows[i].subtract(matrix.rows[i]);
        }
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        checkMatrixDimension(matrix1, matrix2);

        Matrix result = new Matrix(matrix1);
        result.add(matrix2);
        return result;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        checkMatrixDimension(matrix1, matrix2);

        Matrix result = new Matrix(matrix1);
        result.subtract(matrix2);
        return result;
    }

    public static Matrix getMatrixMultiplication(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsCount() != matrix2.getRowsCount()) {
            throw new IllegalArgumentException("Matrix 1 columns count (" +
                    matrix1.getColumnsCount() + ") must match Matrix 2 rows count (" +
                    matrix2.getRowsCount() + ")");
        }

        Matrix result = new Matrix(matrix1.getRowsCount(), matrix2.getColumnsCount());

        for (int i = 0; i < matrix1.getRowsCount(); ++i) {
            Vector multiply = new Vector(matrix2.getColumnsCount());

            for (int j = 0; j < matrix2.getColumnsCount(); ++j) {
                multiply.setComponent(j, Vector.getDotProduct(matrix1.getRow(i), matrix2.getColumn(j)));
            }

            result.setRow(i, multiply);
        }

        return result;
    }

    private static void checkMatrixDimension(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsCount() != matrix2.getColumnsCount() || matrix1.getRowsCount() != matrix2.getRowsCount()) {
            throw new IllegalArgumentException("Matrix dimensions must match for the operation. Matrix1: " +
                    matrix1.getRowsCount() + "x" + matrix1.getColumnsCount() + ", Matrix2: " +
                    matrix2.getRowsCount() + "x" + matrix2.getColumnsCount());
        }
    }
}
