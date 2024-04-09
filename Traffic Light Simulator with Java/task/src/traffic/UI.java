package traffic;

import java.io.IOException;
import java.util.Scanner;

public class UI {
    private final Scanner sc = new Scanner(System.in);

    public void start() {
        System.out.println(Messages.WELCOME);

        System.out.println(Messages.INPUT_ROADS);
        int roads = getPosNum();

        System.out.println(Messages.INPUT_INTERVAL);
        int interval = getPosNum();

        while (true) {
            clearConsole();

            System.out.println(Messages.MENU);
            String mode = sc.nextLine();

            switch (mode) {
                case "0" -> {
                    System.out.println("Bye!");
                    return;
                }
                case "1" -> System.out.println("Road added");
                case "2" -> System.out.println("Road deleted");
                case "3" -> System.out.println("System opened");
                default -> System.out.println("Incorrect option");
            }

            sc.nextLine();
        }
    }

    private int getPosNum() {
        while (true) {
            if (sc.hasNextInt()) {
                int number = sc.nextInt();
                sc.nextLine();

                if (number > 0) {
                    return number;
                }
            } else {
                sc.nextLine();
            }

            System.out.println("Error! Incorrect input. Try again:");
        }
    }

    private void clearConsole() {
        try {
            var clearCommand = System.getProperty("os.name").contains("Windows")
                    ? new ProcessBuilder("cmd", "/c", "cls")
                    : new ProcessBuilder("clear");
            clearCommand.inheritIO().start().waitFor();
        } catch (InterruptedException | IOException ignored) {
        }
    }
}