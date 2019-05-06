//Упорядочить и вывести числа в порядке возрастания (убывания) значений их длины
package com.company;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.util.Date;

/**
 * @author Nitievsky
 */
public class Main {

    public static void main(String[] args) {

        ArrayList<Integer> arrayNumbers = new ArrayList<Integer>();
        ArrayList<Integer> arrayLength = new ArrayList<>();
        System.out.print("Enter the number of numbers: ");
        int size;
        Scanner array_size = new Scanner(System.in);
        size = array_size.nextInt();
        System.out.print("Enter numbers: ");
        Scanner number = new Scanner(System.in);
        for (int i = 0; i < size; i++) {
            arrayNumbers.add(number.nextInt());
        }
        System.out.println("---------Result of program-----------");
        System.out.print("Your numbers: ");
        for (int i = 0; i < size; i++) {
            System.out.print(arrayNumbers.get(i) + " ");
        }
        for (int i = 0; i < size; i++) {
            int tmp_2 = arrayNumbers.get(i);
            String tmp = String.valueOf(tmp_2);
            int length = tmp.length();
            arrayLength.add(length);
        }

        int temp;
        for (int i = size - 1; i > 0; i--) {
            for (int j = 0; j < i; j++ ) {
                if (arrayLength.get(j) > arrayLength.get(j+1)) {
                    temp = arrayNumbers.get(j);
                    arrayNumbers.set(j, arrayNumbers.get(j+1));
                    arrayNumbers.set(j+1, temp);

                    temp = arrayLength.get(j);
                    arrayLength.set(j, arrayLength.get(j+1));
                    arrayLength.set(j+1, temp);
                }
            }
        }
        System.out.println();
        System.out.print("Result ->: ");
        for (int i = 0; i < size; i++) {
            System.out.print(arrayNumbers.get(i) + " ");
        }
        System.out.println();
        System.out.print("Result <-: ");

        for (int i = size - 1; i >= 0; i--) {
            System.out.print(arrayNumbers.get(i) + " ");
        }

        System.out.println(); System.out.println();
        System.out.println("Developer: Nitievsky Dmitry");
        System.out.println("Time of receipt: Tue Feb 14:20:00 MSK 2019");
        Date date = new Date();
        System.out.print("Delivery time: ");
        System.out.println(date.toString());

    }

}
