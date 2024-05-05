package traffic;

public class TrafficManagementSystem {
    private final UserInterface ui = new ConsoleUI();
    private boolean inSystemState = false;
    private boolean running = true;

    public void start() {
        ui.print(Messages.WELCOME);

        ui.print(Messages.INPUT_ROADS);
        int roads = getValidNum();

        ui.print(Messages.INPUT_INTERVAL);
        int interval = getValidNum();

        Thread counter = getCounter(roads, interval);
        counter.start();

        while (true) {
            ui.clearScreen();

            if (!inSystemState) {
                ui.print(Messages.MENU);
                String mode = ui.readInput();

                switch (mode) {
                    case "0" -> {
                        ui.print(Messages.BYE);
                        running = false;
                        return;
                    }
                    case "1" -> ui.print(Messages.ADD);
                    case "2" -> ui.print(Messages.DEL);
                    case "3" -> inSystemState = true;
                    default -> ui.print(Messages.INCORRECT);
                }

                ui.readInput();
                inSystemState = false;
            }
        }
    }

    private Thread getCounter(int roads, int interval) {
        Thread counter = new Thread(() -> {
            long time = 0;
            try {
                while (running) {
                    Thread.sleep(1000);
                    time++;

                    if (inSystemState) {
                        ui.clearScreen();
                        ui.printf(Messages.COUNTER, time, roads, interval);
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        counter.setName("QueueThread");
        return counter;
    }

    private int getValidNum() {
        while (true) {
            try {
                int posNum = Integer.parseInt(ui.readInput());

                if (posNum > 0) {
                    return posNum;
                }
            } catch (NumberFormatException ignored) {
            }

            ui.print(Messages.ERROR_INPUT);
        }
    }
}