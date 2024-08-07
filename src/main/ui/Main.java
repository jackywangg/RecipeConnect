package ui;

import model.Event;
import model.EventLog;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

public class Main {
    // EFFECTS: Prompts user to choose between Console or GUI of RecipeConnect
    public static void main(String[] args) {
        System.out.println("Choose Terminal or GUI (1 or 2):");
        System.out.println("1. Console");
        System.out.println("2: GUI");

        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        if (choice.equals("1")) {
            new RecipeConnectTerminal().initialize();
        } else if (choice.equals("2")) {
            RecipeConnectGUI gui = new RecipeConnectGUI();
            gui.getFrame().addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    printEventLog();
                }
            });
        } else {
            System.out.println("Invalid: Restart Application");
        }
    }

    // EFFECTS: Prints all EventLog logs into console
    private static void printEventLog() {
        EventLog eventLog = EventLog.getInstance();
        System.out.println("Event Log:");
        for (Event event : eventLog) {
            System.out.println(event.toString());
        }
    }
}
