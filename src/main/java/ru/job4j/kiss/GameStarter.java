package ru.job4j.kiss;

import java.util.Scanner;

public class GameStarter {

    public static void main(String[] args) {
        Game consoleGame = new ConsoleGame(100);
        Scanner input = new Scanner(System.in);
        consoleGame.letsPlay(input);
    }
}