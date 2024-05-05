package traffic;

public enum Messages {
    ADD("%s Added!\n"),
    BYE("Bye!"),
    COUNTER("""
            ! %ds. have passed since system startup !
            ! Number of roads: %d !
            ! Interval: %d !
            
            %s
            ! Press "Enter" to open menu !
            """),
    DEL("%s deleted!\n"),
    EMPTY("Queue is empty"),
    ERROR_INPUT("Error! Incorrect input. Try again:"),
    FULL("Queue is full"),
    INCORRECT("Incorrect option"),
    INPUT_INTERVAL("Input the interval:"),
    INPUT_ROADS("Input the number of roads:"),
    MENU("""
            Menu:
            1. Add road
            2. Delete road
            3. Open system
            0. Quit"""),
    ROAD_NAME("Input road name:"),
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
