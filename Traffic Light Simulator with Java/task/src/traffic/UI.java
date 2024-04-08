package traffic;

import java.util.Scanner;

public class UI {
    public void start() {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println(Messages.WELCOME);

            System.out.println(Messages.INPUT_ROADS);
            int roads = sc.nextInt();

            System.out.println(Messages.INPUT_INTERVAL);
            int interval = sc.nextInt();

            while (true) {
                System.out.println(Messages.MENU);
                int mode = sc.nextInt();

                switch (mode) {
                    case 0 -> {
                        System.out.println("Bye!");
                        return;
                    }
                    case 1 -> System.out.println("Road added");
                    case 2 -> System.out.println("Road deleted");
                    case 3 -> System.out.println("System opened");
                    default -> System.out.println("Invalid command!");
                }
            }
        }
    }
}