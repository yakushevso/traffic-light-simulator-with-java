package traffic;

import java.util.ArrayDeque;
import java.util.Queue;

public class TrafficManagementSystem {
    private final UserInterface ui = new ConsoleUI();
    private boolean inSystemState = false;
    private boolean running = true;
    private int roads;
    private int interval;
    private Queue<String> deque;

    public void start() {
        ui.print(Messages.WELCOME);

        ui.print(Messages.INPUT_ROADS);
        roads = getValidNum();

        ui.print(Messages.INPUT_INTERVAL);
        interval = getValidNum();

        Thread counter = getCounter();
        counter.start();

        deque = new ArrayDeque<>(roads);

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
                    case "1" -> {
                        ui.print(Messages.ROAD_NAME);
                        String name = ui.readInput();

                        if (deque.size() >= roads) {
                            ui.print(Messages.FULL);
                        } else {
                            deque.offer(name);
                            ui.printf(Messages.ADD, name);
                        }
                    }
                    case "2" -> {
                        if (deque.isEmpty()) {
                            ui.print(Messages.EMPTY);
                        } else {
                            ui.printf(Messages.DEL, deque.poll());
                        }
                    }
                    case "3" -> inSystemState = true;
                    default -> ui.print(Messages.INCORRECT);
                }

                ui.readInput();
                inSystemState = false;
            }
        }
    }

    private Thread getCounter() {
        Thread counter = new Thread(() -> {
            long time = 0;
            try {
                while (running) {
                    Thread.sleep(1000);
                    time++;

                    if (inSystemState) {
                        ui.clearScreen();
                        ui.printf(Messages.COUNTER, time, roads, interval,
                                String.join("\n", deque));
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