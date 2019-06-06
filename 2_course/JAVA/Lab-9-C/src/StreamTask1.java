/* 1. Прочитать текст Java-программы (т.е. читать java файл как текстовый документ) и
          все слова public в объявлении атрибутов и методов класса заменить на слово private. */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import static java.lang.String.join;

public class StreamTask1 {
    public static final String PUBLIC = "public";
    public static final String PRIVATE = "private";

    public void task1() throws IOException {
        File file1 = new File ("C://Users//dniti");
        File file = new File(file1,"JavaExample.txt");
        File resultFile = new File("C:\\Users\\dniti\\IdeaProjects\\Lab_9C\\src","JavaExample2.txt");


        if (file.exists()) {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                 BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(resultFile))) {
                String line;
                while ((line=bufferedReader.readLine())!=null) {
                    bufferedWriter.append(line.replace(PUBLIC, PRIVATE)).append(System.lineSeparator());
                }
                System.out.println("Process completed successufully");
            }
        }
    }
}
