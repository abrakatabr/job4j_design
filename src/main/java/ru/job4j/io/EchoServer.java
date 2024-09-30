package ru.job4j.io;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EchoServer {

    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream output = socket.getOutputStream();
                     BufferedReader input = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    output.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String msg = "";
                    int counter = 0;
                    for (String string = input.readLine();
                         string != null && !string.isEmpty(); string = input.readLine()) {
                        System.out.println(string);
                        if (counter == 0) {
                            msg = string;
                        }
                        counter++;
                    }
                    Pattern patternHello = Pattern.compile("msg=Hello");
                    Pattern patternExit = Pattern.compile("msg=Exit");
                    Matcher matcherHello = patternHello.matcher(msg);
                    Matcher matcherExit = patternExit.matcher(msg);
                    if (matcherHello.find()) {
                        output.write("Hello".getBytes());
                    } else if (!matcherExit.find()) {
                        output.write("What".getBytes());
                    }
                    output.flush();
                    if (matcherExit.find(0)) {
                        server.close();
                    }
                }
            }
        } catch (IOException e) {
           LOG.error("IOexception in socket example", e);
        }
    }
}
