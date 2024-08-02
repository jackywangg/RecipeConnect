package ui;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Choose Terminal or GUI (1 or 2):");
        System.out.println("1. Console");
        System.out.println("2: GUI");

        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        if (choice.equals("1")) {
            new RecipeConnectTerminal().initialize();
        } else if (choice.equals("2")) {
            new RecipeConnectGUI();
        } else {
            System.out.println("Invalid: Restart Application");
        }
    }
}
