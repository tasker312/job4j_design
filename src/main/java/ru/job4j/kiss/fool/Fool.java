package ru.job4j.kiss.fool;

import java.util.Scanner;

public class Fool {

    public static void main(String[] args) {
        System.out.println("Игра FizzBuzz.");
        var startAt = 1;
        var input = new Scanner(System.in);
        while (startAt < 100) {
            System.out.println(getCurrentAnswer(startAt));
            startAt++;
            var answer = input.nextLine();
            if (!getCurrentAnswer(startAt).equalsIgnoreCase(answer)) {
                System.out.println("Ошибка. Начинай снова.");
                startAt = 0;
            }
            startAt++;
        }
    }

    public static String getCurrentAnswer(int value) {
        if (value % 3 == 0 && value % 5 == 0) {
            return "FizzBuzz";
        }
        if (value % 3 == 0) {
            return "Fizz";
        }
        if (value % 5 == 0) {
            return "Buzz";
        }
        return String.valueOf(value);
    }
}
