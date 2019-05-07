//Ввести с консоли n целых чисел. На консоль вывести наибольшее и наименьшее число.
package com.company;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;


public class Main {

    public static void main(String[] args) {
        //Create a dynamic array
        ArrayList<Integer> array = new ArrayList<Integer>();
        //Display text "Enter the number of numbers:"
        System.out.print("Enter the number of numbers: ");
        //Create variable for the number of numbers (size)
        int size;
        //Read size from console
        Scanner array_size = new Scanner(System.in);
        size = array_size.nextInt();
        //Display text "Enter numbers:"
        System.out.print("Enter numbers: ");
        //Read numbers from console and add in array
        Scanner number = new Scanner(System.in);
        for (int i = 0; i < size; i++) {
            array.add(number.nextInt());
        }
        //Display text "Result of program" and "Your numbers"
        System.out.println("---------Result of program-----------");
        System.out.print("Your numbers: ");
        //Display numbers in console
        for (int i = 0; i < size; i++) {
            System.out.print(array.get(i) + " ");
        }
        //Create variables for max and min elements
        int max = array.get(0), min = array.get(0);
        //Create variable for element of numbers
        int element;
        //Algorithm for finding max and min element
        for (int i = 1; i < size; i++) {
            element = array.get(i);
            if (element <= min) min = element;
            if (element >= max) max = element;
        }
        //Display max and min elements in console
        System.out.println();
        System.out.println("Maximum element: " + max);
        System.out.print("Minimum element: " + min);
    }
}

