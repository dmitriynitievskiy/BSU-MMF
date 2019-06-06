import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;

/**
 * Ввести число, занести его цифры в стек. Вывести число, у которого цифры идут в обратном порядке.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        Stack stack = new Stack();

        FileReader fr = new FileReader("C:\\Users\\dniti\\IdeaProjects\\Lab10\\src\\text.txt");
        char [] a = new char[10];   // Количество символов, которое будем считывать
        fr.read(a);   // Чтение содержимого в массив

        System.out.print("Число из файла: ");
        for(char c : a)
            System.out.print(c);   // Вывод символов один за другими
        fr.close();

        System.out.println();


    System.out.print("Реверс числа: ");
    char [] numbers = String.valueOf(a).toCharArray();
        for(char num : numbers){
            stack.push(num);}

        while(!stack.empty())
        {
            char stackNum;
            stackNum = (Character)stack.pop();
            System.out.print(stackNum);
        }
    }
}
