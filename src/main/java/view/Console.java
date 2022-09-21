package view;

import command.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Console implements View {
    private final Scanner scanner;

    public Console(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String read() {
        return scanner.nextLine();
    }

    @Override
    public void write(String message) {
        System.out.println(message);
    }

    public List<Command> commands() {
        Scanner scanner = new Scanner(System.in);
        View view = new Console(scanner);

        List<Command> commands = new ArrayList<>();
        commands.add(new Help(view));
        commands.add(new GetCompanies(view));
        commands.add(new GetCompanyById(view));
        commands.add(new GetDevs(view));
        commands.add(new GetDevsById(view));
        commands.add(new GetDevsByProject(view));
        commands.add(new GetDevsBySkillName(view));
        commands.add(new GetDevsBySkillLevel(view));
        commands.add(new GetProjects(view));
        commands.add(new GetSalary(view));
        commands.add(new UpdateDeveloper(view));
        commands.add(new UpdateProject(view));
        commands.add(new UpdateCustomer(view));
        commands.add(new UpdateCompany(view));
        commands.add(new UpdateSkill(view));
        commands.add(new DeleteDeveloper(view));
        commands.add(new DeleteProject(view));
        commands.add(new DeleteCompany(view));
        commands.add(new DeleteCustomer(view));
        commands.add(new DeleteSkill(view));
        commands.add(new CreateDeveloper(view));
        commands.add(new Exit(view));

        return commands;
    }
}
