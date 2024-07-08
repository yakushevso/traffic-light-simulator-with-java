package traffic;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.Collectors;

public class TrafficManagementSystem {
    private final UserInterface ui = new ConsoleUI();
    private boolean inSystemState = false;
    private boolean running = true;
    private int maxRoads;
    private int interval;
    private Deque<Road> roads;
    private int globalTime = 0;

    public void start() {
        ui.print(Messages.WELCOME);

        ui.print(Messages.INPUT_ROADS);
        maxRoads = getValidNum();

        ui.print(Messages.INPUT_INTERVAL);
        interval = getValidNum();

        Thread counter = getCounter();
        counter.start();

        roads = new ArrayDeque<>(maxRoads);

        while (true) {
            if (!inSystemState) {
                ui.clearScreen();
                ui.print(Messages.MENU);
                String mode = ui.readInput();

                switch (mode) {
                    case "0" -> {
                        ui.print(Messages.BYE);
                        running = false;
                        return;
                    }
                    case "1" -> {
                        addRoad();
                    }
                    case "2" -> {
                        removeRoad();
                    }
                    case "3" -> inSystemState = true;
                    default -> ui.print(Messages.INCORRECT);
                }

                ui.readInput();
                inSystemState = false;
            }
        }
    }

    private void addRoad() {
        ui.print(Messages.ROAD_NAME);
        String name = ui.readInput();

        if (roads.size() >= maxRoads) {
            ui.print(Messages.FULL);
        } else {
            Road road;

            if (roads.isEmpty()) {
                road = new Road(name, true, interval);
            } else {
                Road lastRoad = roads.getLast();
                int timeLeft;

                if (lastRoad.isOpen()) {
                    timeLeft = lastRoad.getTimeRemaining();
                } else {
                    timeLeft = lastRoad.getTimeRemaining() + interval;
                }

                road = new Road(name, false, timeLeft);
            }

            roads.add(road);
            ui.printf(Messages.ADD, name);
        }
    }

    private void removeRoad() {
        if (roads.isEmpty()) {
            ui.print(Messages.EMPTY);
        } else {
            Road road = roads.poll();
            ui.printf(Messages.DEL, road.getName());

            if (!roads.isEmpty()) {
                if (road.isOpen()) {
                    int count = 0;

                    for (Road curRoad : roads) {
                        if (count == 0) {
                            curRoad.setOpen(true);
                            curRoad.setTimeRemaining(interval + 1);
                        } else {
                            curRoad.setTimeRemaining(count * interval);
                        }

                        count += 1;
                    }
                } else {
                    for (Road curRoad : roads) {
                        if (curRoad.isOpen()) {
                            return;
                        }

                        curRoad.setTimeRemaining(road.getTimeRemaining() - interval);
                    }
                }
            }
        }
    }

    private Thread getCounter() {
        Thread counter = new Thread(() -> {
            try {
                while (running) {
                    Thread.sleep(1000);
                    globalTime++;

                    if (inSystemState) {
                        ui.clearScreen();
                        displaySystemState();
                        updateRoads();
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        counter.setName("QueueThread");
        return counter;
    }

    private void updateRoads() {
        if (!roads.isEmpty()) {
            for (Road road : roads) {
                road.updateState(roads.size(), interval);
            }
        }
    }

    private void displaySystemState() {
        ui.printf(Messages.COUNTER, globalTime, maxRoads, interval,
                roads.stream().map(Road::toString).collect(Collectors.joining("\n")));
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