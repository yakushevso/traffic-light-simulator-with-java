package traffic;

import java.io.IOException;
import java.util.Scanner;

public class ConsoleUI implements UserInterface {
    private final Scanner sc = new Scanner(System.in);

    @Override
    public void print(Messages message) {
        System.out.println(message);
    }

    @Override
    public void printf(Messages message, Object... args) {
        System.out.printf(String.valueOf(message), args);
    }

    @Override
    public String readInput() {
        return sc.nextLine();
    }

    @Override
    public void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (InterruptedException | IOException e) {
            System.out.println("Error clearing the screen.");
        }
    }
}
