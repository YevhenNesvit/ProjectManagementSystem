package command;

import config.DatabaseManagerConnector;
import config.PropertiesConfig;
import repository.DeveloperRepository;
import view.View;

import java.sql.SQLException;

public class GetSalary implements Command {
    public static final String GET_SALARY = "get salary";
    private final View view;
    DeveloperRepository developerRepository = new DeveloperRepository(new DatabaseManagerConnector(
            new PropertiesConfig().loadProperties("application.properties"), "postgres", "1011"));

    public GetSalary(View view) {
        this.view = view;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(GET_SALARY);
    }

    @Override
    public void execute() {
        Integer id;
        while (true) {
            try {
                view.write("Please, enter project id: ");
                id = Integer.parseInt(view.read());
                break;
            } catch (NumberFormatException e) {
                view.write("Invalid value. Use digits");
            }
        }
        try {
            view.write("For project with id " + id + " Salary is " + developerRepository.salaryByProjectId(id));
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }
}
