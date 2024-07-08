package traffic;

public class Road {
    private final String name;
    private boolean isOpen;
    private int timeRemaining;
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RESET = "\u001B[0m";

    public Road(String name, boolean isOpen, int timeRemaining) {
        this.name = name;
        this.isOpen = isOpen;
        this.timeRemaining = timeRemaining;
    }

    public String getName() {
        return name;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public int getTimeRemaining() {
        return timeRemaining;
    }

    public void setTimeRemaining(int timeRemaining) {
        this.timeRemaining = timeRemaining;
    }

    public void updateState(int roadCount, int interval) {
        timeRemaining--;

        if (timeRemaining == 0) {
            if (roadCount > 1) {
                isOpen = !isOpen;
            }
            timeRemaining = isOpen ? interval : interval * (roadCount - 1);
        }
    }

    @Override
    public String toString() {
        String state = isOpen ? ANSI_GREEN + "open" : ANSI_RED + "closed";
        return String.format("%s will be %s for %ds.%s", name, state, timeRemaining, ANSI_RESET);
    }
}