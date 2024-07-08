package traffic;

public interface UserInterface {
    void print(Messages message);

    void printf(Messages message, Object... args);

    String readInput();

    void clearScreen();
}
