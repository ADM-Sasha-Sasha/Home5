package ua.goit.controller;

import ua.goit.command.*;
import ua.goit.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainController {
    private final View view;
    private final List<Command> commands;

    public MainController(View view) {
        this.view = view;
        this.commands = new ArrayList<>(Arrays.asList(new Help(view), new Create(view), new Read(view), new Update(view),
                new Delete(view)));
    }

    public void run() {
        view.write("Welcome to the pet store");
        doCommand();
    }

    private void doCommand() {
        boolean running = true;
        while (running) {
            view.write("Please enter a command or 'help' to retrieve command list\nEnter 'exit' to leave");
            String inputCommand = view.read();
            for (Command command : commands) {
                if (command.canProcess(inputCommand)) {
                    command.process();
                    break;
                } else if (inputCommand.equalsIgnoreCase("exit")) {
                    view.write("Good Bye!");
                    running = false;
                    break;
                }
            }
        }
    }
}
