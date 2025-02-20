package ru.job4j.kiss;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;

import java.util.Scanner;
import static org.mockito.Mockito.*;

import static org.assertj.core.api.Assertions.*;

class ConsoleGameTest {
    @Test
    void letsPlayTest() {
        Scanner scanner = Mockito.mock(Scanner.class);
        when(scanner.nextLine()).thenReturn(String.valueOf(2), String.valueOf(4), "Fizz",
                String.valueOf(8), "Buzz", "Fizz", String.valueOf(14), String.valueOf(16), "Fizz", "Exit");
        ConsoleGame consoleGame = new ConsoleGame(1, 100);
        consoleGame.letsPlay(scanner);
        assertThat(consoleGame.getStartAt()).isEqualTo(20);
    }
}