package ru.job4j.kiss;

import java.util.Scanner;
import java.util.function.Predicate;

public class ConsoleGame implements Game {
    private Integer startAt;
    private final Integer endAt;

    public ConsoleGame(Integer startAt, Integer endAt) {
        this.startAt = startAt;
        this.endAt = endAt;
    }

    @Override
    public void letsPlay(Scanner input) {
        System.out.println("Игра FizzBuzz.");
        while (startAt < endAt) {
            if (checkDivision(start -> start % 3 == 0) && checkDivision(start -> start % 5 == 0)) {
                System.out.println("FizzBuzz");
            } else if (checkDivision(start -> start % 3 == 0)) {
                System.out.println("Fizz");
            } else if (checkDivision(start -> start % 5 == 0)) {
                System.out.println("Buzz");
            } else {
                System.out.println(startAt);
            }
            startAt++;
            var answer = input.nextLine();
            if ("Exit".equals((answer))) {
                break;
            }
            if (checkDivision(start -> start % 3 == 0) && checkDivision(start -> start % 5 == 0)) {
                if (!checkAnswer(a -> "FizzBuzz".equals(a), answer)) {
                    System.out.println("Ошибка. Начинай снова.");
                    startAt = 0;
                }
            } else if (checkDivision(start -> start % 3 == 0)) {
                if (!checkAnswer(a -> "Fizz".equals(a), answer)) {
                    System.out.println("Ошибка. Начинай снова.");
                    startAt = 0;
                }
            } else if (startAt % 5 == 0) {
                if (!checkAnswer(a -> "Buzz".equals(a), answer)) {
                    System.out.println("Ошибка. Начинай снова.");
                    startAt = 0;
                }
            } else {
                if (!checkAnswer(a -> String.valueOf(startAt).equals(a), answer)) {
                    System.out.println("Ошибка. Начинай снова.");
                    startAt = 0;
                }
            }
            startAt++;
        }
    }

    private boolean checkDivision(Predicate<Integer> predicate) {
        return predicate.test(startAt);
    }

    private boolean checkAnswer(Predicate<String> predicate, String answer) {
        return predicate.test(answer);
    }

    public Integer getStartAt() {
        return startAt;
    }

    public Integer getEndAt() {
        return endAt;
    }
}
