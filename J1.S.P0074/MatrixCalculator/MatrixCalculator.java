/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MatrixCalculator;

import Utility.GetValidInput;

/**
 *
 * @author hoangson
 */
public class MatrixCalculator {

    public static void main(String[] args) {
        int[][] matrix1, matrix2, matrixResult;
        while (true) {
            // display menu
            displayMenu();
            // get user's choice
            int choice = getUserChoice();
            switch (choice) {
                case 1:
                    displayAdditionInterface();
                    // input value of matrix 1
                    matrix1 = inputMatrix();
                    // input value of matrix 2
                    matrix2 = inputMatrix(matrix1, false);
                    // addition matrixes
                    matrixResult = additionMatrix(matrix1, matrix2);
                    // display the result of addition
                    displayResult(matrix1, matrix2, matrixResult, '+');
                    break;
                case 2:
                    displaySubtractionInterface();
                    // input value of matrix 1
                    matrix1 = inputMatrix();
                    // input value of matrix 2
                    matrix2 = inputMatrix(matrix1, false);
                    // subtraction matrixes
                    matrixResult = subtractionMatrix(matrix1, matrix2);
                    // display the result of subtraction
                    displayResult(matrix1, matrix2, matrixResult, '-');
                    break;
                case 3:
                    displayMultiplicationInterface();
                    // input value of matrix 1
                    matrix1 = inputMatrix();
                    // input value of matrix 2
                    matrix2 = inputMatrix(matrix1, true);
                    // multiplication matrixes
                    matrixResult = multiplicationMatrix(matrix1, matrix2);
                    // display the result of multiplication
                    displayResult(matrix1, matrix2, matrixResult, '*');
                    break;
                case 4:
                    System.exit(0);
                    break;
            }
        }
    }

    public static void displayMenu() {
        System.out.println("=======Calculator program======\n"
                + "1. Addition Matrix\n"
                + "2. Subtraction Matrix\n"
                + "3. Multiplication Matrix\n"
                + "4. Quit");
    }

    public static int getUserChoice() {
        int choice = GetValidInput.getInt("Your choice: ", "Input can't be a string", 1, 4);
        return choice;
    }

    public static void displayAdditionInterface() {
        System.out.println("-------- Addition --------");
    }

    public static void displaySubtractionInterface() {
        System.out.println("----- Subtraction ------");
    }

    public static void displayMultiplicationInterface() {
        System.out.println("-------- Multiplication -------");
    }

    public static int[][] inputMatrix() {
        int row = GetValidInput.getRowMatrix("Enter Row Matrix 1:", 1, Integer.MAX_VALUE);
        int col = GetValidInput.getColMatrix("Enter Column Matrix 1:", 1, Integer.MAX_VALUE);
        int[][] matrix = new int[row][col];
        // loop to reach all the row indexes of 2d array
        for (int i = 1; i <= row; i++) {
            // for each row index, loop to reach all the column indexes of 2d array
            for (int j = 1; j <= col; j++) {
                matrix[i - 1][j - 1] = GetValidInput.getInt("Enter Matrix1[" + i + "][" + j + "]:", "Value of matrix is digit", Integer.MIN_VALUE, Integer.MAX_VALUE);
            }
        }
        return matrix;
    }
    
    public static int[][] inputMatrix(int[][] matrix1, boolean checkMultiplyCase) {
        // if not need to check multiplication condition, then check that rows and columns of matrix2 = rows and columns of matrix1
        if (checkMultiplyCase == false) {
            int row = GetValidInput.getRowMatrix("Enter Row Matrix 2:", "Row of matrix 2 must equals to row of matrix 1", matrix1.length);
            int col = GetValidInput.getColMatrix("Enter Column Matrix 2:", "Column of matrix 2 must equals to column of matrix 1", matrix1[0].length);
            int[][] matrix = new int[row][col];
            // loop to reach all the row indexes of 2d array
            for (int i = 1; i <= row; i++) {
                // for each row index, loop to reach all the column indexes of 2d array
                for (int j = 1; j <= col; j++) {
                    matrix[i - 1][j - 1] = GetValidInput.getInt("Enter Matrix2[" + i + "][" + j + "]:", "Value of matrix is digit", Integer.MIN_VALUE, Integer.MAX_VALUE);
                }
            }
            return matrix;
        } // if need to check multiplication condition, then check that rows of matrix2 = columns of matrix1
        else {
            int row = GetValidInput.getRowMatrix("Enter Row Matrix 2:", "Row of matrix 2 must equals to column of matrix 1", matrix1[0].length);
            int col = GetValidInput.getColMatrix("Enter Column Matrix 2:", 1, Integer.MAX_VALUE);
            int[][] matrix = new int[row][col];
            // loop to reach all the row indexes of 2d array
            for (int i = 1; i <= row; i++) {
                // for each row index, loop to reach all the column indexes of 2d array
                for (int j = 1; j <= col; j++) {
                    matrix[i - 1][j - 1] = GetValidInput.getInt("Enter Matrix2[" + i + "][" + j + "]:", "Value of matrix is digit", Integer.MIN_VALUE, Integer.MAX_VALUE);
                }
            }
            return matrix;
        }
    }

    public static int[][] additionMatrix(int[][] matrix1, int[][] matrix2) {
        int row = matrix1.length;
        int col = matrix1[0].length;
        int[][] res = new int[row][col];
        // loop to reach all the row indexes of 2d array
        for (int i = 0; i < row; i++) {
            // for each row index, loop to reach all the column indexes of 2d array
            for (int j = 0; j < col; j++) {
                res[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }
        return res;
    }

    public static int[][] subtractionMatrix(int[][] matrix1, int[][] matrix2) {
        int row = matrix1.length;
        int col = matrix1[0].length;
        int[][] res = new int[row][col];
        // loop to reach all the row indexes of 2d array
        for (int i = 0; i < row; i++) {
            // for each row index, loop to reach all the column indexes of 2d array
            for (int j = 0; j < col; j++) {
                res[i][j] = matrix1[i][j] - matrix2[i][j];
            }
        }
        return res;
    }

    public static int[][] multiplicationMatrix(int[][] matrix1, int[][] matrix2) {
        int row = matrix1.length;
        int col = matrix2[0].length;
        int[][] res = new int[row][col];
        // loop to reach all the row indexes of 2d array
        for (int i = 0; i < row; i++) {
            // for each row index, loop to reach all the column indexes of 2d array
            for (int j = 0; j < col; j++) {
                res[i][j] = 0;
                // loop to reach all the column indexes of the 1st matrix and all the row indexes of the 2nd matrix
                for (int k = 0; k < matrix2.length; k++) {
                    res[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        return res;
    }

    public static void displayMatrix(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        // for each row index, loop to reach all the row indexes of 2d array
        for (int i = 0; i < row; i++) {
            // loop to reach all the column indexes of 2d array
            for (int j = 0; j < col; j++) {
                System.out.print("[" + matrix[i][j] + "]");
            }
            System.out.println();
        }
    }

    public static void displayResult(int[][] matrix1, int[][] matrix2, int[][] matrixResult, char operator) {
        System.out.println("-------- Result --------");
        displayMatrix(matrix1);
        System.out.println(operator);
        displayMatrix(matrix2);
        System.out.println("=");
        displayMatrix(matrixResult);
    }
}
