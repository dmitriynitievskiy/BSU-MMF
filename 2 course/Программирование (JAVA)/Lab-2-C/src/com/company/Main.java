//Ввести с консоли n-размерность матрицы a[n][n].Задать значения элементов матрицы
//в интервале значений от -n до n c помощью датчика случайных чисел.
//Выполнить циклический сдвиг заданной матрицы на k позиций вправо(влево, вверх, вниз)
package com.company;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.print("Enter dimension: ");
        Scanner dimension = new Scanner(System.in);
        int n = dimension.nextInt();
        System.out.println("--------Result of program--------");
        System.out.println("------Original Matrix-----");
        int[][] matrix = new int[n][n];
        Random rand = new Random ();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = -n + rand.nextInt(2*n + 1);
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.print("Enter shift of matrix: ");
        Scanner shift = new Scanner(System.in);
        int k = shift.nextInt();
        System.out.println("Where do you want to shift?: ");
        System.out.println("1 - Right");
        System.out.println("2 - Left");
        System.out.println("3 - Up");
        System.out.println("4 - Down");
        Scanner c = new Scanner (System.in);
        int z  = c.nextInt();
        switch (z) {
            case (1):
                for (int i = 0; i < n; i++) {
                for (int j = 0; j < k; j++) {
                    int tmp = matrix[i][n-1];
                    for (int m = n - 1; m > 0; m--) matrix[i][m] = matrix[i][m-1];
                    matrix[i][0] = tmp;
                }
            }
                break;
            case (2):
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < k; j++) {
                        int tmp = matrix[i][0];
                        for (int m = 0; m < n - 1; m++) matrix[i][m] = matrix[i][m+1];
                        matrix[i][n-1] = tmp;
                    }
                }
                break;
            case (3):
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < k; j++) {
                        int tmp = matrix[0][i];
                        for (int m = 0; m < n - 1; m++) matrix[m][i] = matrix[m+1][i];
                        matrix[n-1][i] = tmp;
                    }
                }
                break;
            case (4):
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < k; j++) {
                        int tmp = matrix[n-1][i];
                        for (int m = n - 1; m > 0; m--) matrix[m][i] = matrix[m-1][i];
                        matrix[0][i] = tmp;
                    }
                }
                break;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
