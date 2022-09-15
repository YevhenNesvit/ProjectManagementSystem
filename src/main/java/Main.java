import command.Command;
import command.Exit;
import command.GetSalary;
import command.Help;
import view.Console;
import view.Interaction;
import view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        View view = new Console(scanner);

        List<Command> commands = new ArrayList<>();
        commands.add(new Help(view));
        commands.add(new Exit(view));
        commands.add(new GetSalary(view));

        Interaction interaction = new Interaction(view, commands);

        interaction.run();
    }
}
