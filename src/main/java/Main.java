import command.Command;
import command.Exit;
import command.GetSalary;
import command.Help;
import config.DatabaseManagerConnector;
import config.PropertiesConfig;
import repository.DeveloperRepository;
import repository.ProjectRepository;
import view.Console;
import view.Interaction;
import view.View;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PropertiesConfig propertiesConfig = new PropertiesConfig();
        Properties properties = propertiesConfig.loadProperties("application.properties");

        DatabaseManagerConnector manager = new DatabaseManagerConnector(properties, "postgres", "1011");
        DeveloperRepository developerRepository = new DeveloperRepository(manager);
        ProjectRepository projectRepository = new ProjectRepository(manager);
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
