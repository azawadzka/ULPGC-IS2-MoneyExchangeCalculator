package view.console;

import controller.Controller;

public class ConsoleMain {

    public static void main(String[] args) {
        Controller.init();
        while(true) {
            Controller.startNewSession();
            Controller.requestCalculation();
        }
    }
}
