package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Path;
import java.io.File;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> botPhrases = readPhrases();
        List<String> chatLog = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean stop = false;
        boolean out = false;
        boolean isContinue = false;
        StringBuilder massage = new StringBuilder();
        String sep = System.lineSeparator();
        massage.append("Команды:").append(sep).append("стоп - остановить бота").append(sep)
                .append("продолжить - отменить остановку бота").append(sep)
                .append("закончить - выйти из программы").append(sep);
        System.out.println(massage);
        do {
            isContinue = false;
            String phrase = scanner.nextLine();
            switch (phrase) {
                case STOP -> {
                    stop = true;
                    System.out.println("*** Бот отключен ***");
                }
                case CONTINUE -> {
                    stop = false;
                    isContinue = true;
                    System.out.println("*** Бот включен ***");
                }
                case OUT -> {
                    out = true;
                    System.out.println("*** Выход из программы ***");
                }
                default -> { }
            }
            chatLog.add("<Пользователь> " + phrase);
            if (!stop && !out && !isContinue) {
                String botPhrase = botPhrases.get(random.nextInt(botPhrases.size()));
                System.out.println(botPhrase);
                chatLog.add("<Бот> " + botPhrase);
            }
            if (out) {
                saveLog(chatLog);
            }
        } while (!out);
    }

    private List<String> readPhrases() {
        List<String> phrases = null;
        File file = new File(botAnswers);
        try (BufferedReader reader = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8))) {
            phrases = reader.lines()
                    .collect(Collectors.toList());
            if (phrases.size() == 0) {
                throw new IllegalArgumentException(String.format("Error: no phrases found in '%s'", botAnswers));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return phrases;
    }

    private void saveLog(List<String> log) {
        File file = new File(path);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, StandardCharsets.UTF_8, true))) {
            for (String s : log) {
                writer.write(s + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat("data/chatlog.txt", "data/botphrases.txt");
        consoleChat.run();
    }
}
