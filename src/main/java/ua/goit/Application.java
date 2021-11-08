package ua.goit;

import ua.goit.controller.MainController;
import ua.goit.view.Console;
import ua.goit.view.View;

public class Application {
    public static void main(String[] args) {
        View view = new Console(System.in, System.out);
        MainController controller = new MainController(view);
        controller.run();
    }
}
