package ru.job4j.kiss;

import org.junit.jupiter.api.Test;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.*;

class ConsoleGameTest {

    @Test
    void letsPlayTest() {
        String sep = System.lineSeparator();
        String input = "2" + sep + "4" + sep + "Fizz" + sep + "8" + sep + "Buzz" + sep + "Fizz" + sep + "14"
               + sep + "16" + sep + "Fizz" + sep + "Exit";
        Scanner scanner = new Scanner(input);
        ConsoleGame consoleGame = new ConsoleGame(1, 100);
        consoleGame.letsPlay(scanner);
        assertThat(consoleGame.getStartAt()).isEqualTo(20);
    }
}