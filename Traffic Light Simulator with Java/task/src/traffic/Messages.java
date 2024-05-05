package traffic;

public enum Messages {
    ADD("Road added"),
    BYE("Bye!"),
    COUNTER("""
            ! %ds. have passed since system startup !
            ! Number of roads: %d !
            ! Interval: %d !
            ! Press "Enter" to open menu !
            """),
    DEL("Road deleted"),
    ERROR_INPUT("Error! Incorrect input. Try again:"),
    INCORRECT("Incorrect option"),
    INPUT_INTERVAL("Input the interval:"),
    INPUT_ROADS("Input the number of roads:"),
    MENU("""
            Menu:
            1. Add road
            2. Delete road
            3. Open system
            0. Quit"""),
    WELCOME("Welcome to the traffic management system!");

    private final String messages;

    Messages(String messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return this.messages;
    }
}
