package ru.job4j.kiss;

import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.*;

class ConsoleGameTest {

    @Test
    void when6thenFizz() {
        ConsoleGame consoleGame = new ConsoleGame(100);
        Integer number = 6;
        String expected = "Fizz";
        assertThat(consoleGame.getCorrectAnswer(number)).isEqualTo(expected);
    }

    @Test
    void when10thenBuzz() {
        ConsoleGame consoleGame = new ConsoleGame(100);
        Integer number = 10;
        String expected = "Buzz";
        assertThat(consoleGame.getCorrectAnswer(number)).isEqualTo(expected);
    }

    @Test
    void when15thenFizzBuzz() {
        ConsoleGame consoleGame = new ConsoleGame(100);
        Integer number = 15;
        String expected = "FizzBuzz";
        assertThat(consoleGame.getCorrectAnswer(number)).isEqualTo(expected);
    }

    @Test
    void when16thenNumber() {
        ConsoleGame consoleGame = new ConsoleGame(100);
        Integer number = 16;
        String expected = "16";
        assertThat(consoleGame.getCorrectAnswer(number)).isEqualTo(expected);
    }
}