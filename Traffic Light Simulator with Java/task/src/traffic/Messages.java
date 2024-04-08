package traffic;

public enum Messages {
    WELCOME("Welcome to the traffic management system!"),
    INPUT_ROADS("Input the number of roads:"),
    INPUT_INTERVAL("Input the interval:"),
    MENU("""
            Menu:
            1. Add road
            2. Delete road
            3. Open system
            0. Quit""");

    private final String messages;

    Messages(String messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return this.messages;
    }
}
