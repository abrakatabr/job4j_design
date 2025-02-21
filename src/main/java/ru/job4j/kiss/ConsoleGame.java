package ru.job4j.kiss;

import java.util.Scanner;
import java.util.function.Predicate;

public class ConsoleGame implements Game {

    private Integer startAt = 1;
    private final Integer endAt;
    private final Scanner input = new Scanner(System.in);

    public ConsoleGame(Integer endAt) {
        this.endAt = endAt;
    }

    @Override
    public void letsPlay(Scanner input) {
        System.out.println("Игра FizzBuzz.");
        while (startAt < endAt) {
            System.out.println(getCorrectAnswer(startAt));
            startAt++;
            String userAnswer = input.nextLine();
            if ("Exit".equals(userAnswer)) {
                break;
            }
            if (!isAnswerCorrect(getCorrectAnswer(startAt), userAnswer)) {
                System.out.println("Ошибка. Начинай снова.");
                startAt = 1;
                continue;
            }
            startAt++;
        }
    }

    public String getCorrectAnswer(Integer number) {
        boolean isFizz = number % 3 == 0;
        boolean isBuzz = number % 5 == 0;

        if (isFizz && isBuzz) {
            return "FizzBuzz";
        } else if (isFizz) {
            return "Fizz";
        } else if (isBuzz) {
            return "Buzz";
        } else {
            return String.valueOf(number);
        }
    }

    private boolean isAnswerCorrect(String correctAnswer, String userAnswer) {
        return correctAnswer.equals(userAnswer);
    }
}
