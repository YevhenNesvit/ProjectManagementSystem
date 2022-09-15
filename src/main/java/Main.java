import view.Console;
import view.Interaction;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Console console = new Console(scanner);
        Interaction interaction = new Interaction(console, console.commands());

        interaction.run();
    }
}
