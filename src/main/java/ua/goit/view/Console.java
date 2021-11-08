package ua.goit.view;

import lombok.SneakyThrows;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class Console implements View {

    private final Scanner scanner;
    private final OutputStream out;

    public Console(InputStream in, OutputStream out) {
        scanner = new Scanner(in);
        this.out = out;
    }

    @Override
    public String read() {
        return scanner.nextLine();
    }


    @SneakyThrows
    @Override
    public void write(String message) {
        out.write(message.getBytes());
        out.write("\n".getBytes());

    }
}
