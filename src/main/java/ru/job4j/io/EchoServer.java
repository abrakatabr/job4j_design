package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
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
                    output.flush();
                    if (msg.contains("msg=Bye")) {
                        server.close();
                    }
                }
            }
        }
    }
}
