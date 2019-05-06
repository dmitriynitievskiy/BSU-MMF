/*Создать классы, спецификации которых приведены ниже.
 Определить конструкторы и методы setТип(), getТип(), toString().
 Определить дополнительно методы в классе,
 создающем массив объектов. Задать критерий выбора данных
 и вывести эти данные на консоль. В каждом классе, обладающем информацией,
 должно быть объявлено несколько конструкторов.
   Customer: id, Фамилия, Имя, Отчество, Адрес, Номер кредитной карточки,
   Номер банковского счета.
   Создать массив объектов. Вывести:
   a) список покупателей в алфавитном порядке;
   b) список покупателей, у которых номер кредитной карточки находится
   в заданном интервале*/
package com.company;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Customer[] human = new Customer[6];
        human[0] = new Customer(1, "Nitievsky", "Dmitry", "Igorevich", "Partizansky-41A", 83839933, 15515);
        human[1] = new Customer(2, "Moskalik", "Alex", "Sergeevich", "Oktyabr'skaya-10A", 15678999, 15516);
        human[2] = new Customer(3, "Matroskin", "Kot", "Ivanovich", "Prostokvashino", 15635532, 15517);
        human[3] = new Customer(4, "Zaytsev", "Nikolay", "LilUziVertovich", "Miami", 13299927, 15518);
        human[4] = new Customer(5, "Zhmachinsky", "Max", "Leonidovich", "Plekhanova-125", 88292002, 15519);
        human[5] = new Customer(6, "Bobikov", "Valery", "Vital'evich", "Filimonova-10", 99200272, 15520);

        List<Customer> list = new ArrayList<>();
        list.add(human[0]);
        list.add(human[1]);
        list.add(human[2]);
        list.add(human[3]);
        list.add(human[4]);
        list.add(human[5]);

        System.out.println("Enter: ");
        System.out.println("1 - Sort customers by surname");
        System.out.println("2 - Choice customers with card number in interval");
        int n;
        Scanner choice = new Scanner(System.in);
        n = choice.nextInt();

        switch (n) {
            case 1:
                Collections.sort(list);
                for (Customer i : list) {
                    System.out.println(i);
                }
                break;
            case 2:
                int start, end;
                System.out.print("Enter first part of interval: ");
                Scanner inter_start = new Scanner(System.in);
                start = inter_start.nextInt();
                System.out.print("Enter second part of interval: ");
                Scanner inter_end = new Scanner(System.in);
                end = inter_end.nextInt();

                for (int i = 0; i < 6; i++) {
                    if (human[i].getCard_number() >= start && human[i].getCard_number() <= end) {
                        System.out.println(human[i]);
                }
            }
                break;
        }
    }
}
