package command;

import utils.CheckDevelopers;
import services.DeveloperService;
import view.View;

import java.sql.SQLException;

public class UpdateDeveloper implements Command {
    public static final String UPDATE_DEVELOPER = "update dev";
    private final View view;
    DeveloperService developerService = new DeveloperService();
    CheckDevelopers checkDevelopers = new CheckDevelopers();

    public UpdateDeveloper(View view) {
        this.view = view;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(UPDATE_DEVELOPER);
    }

    @Override
    public void execute() {
        String firstName;
        String lastName;
        String gender;
        int age;
        int companyId;
        int salary;
        int id;
        try {
            while (true) {
                try {
                    view.write("Please, enter first_name to update: ");
                    firstName = view.read();
                    view.write("Please, enter last_name to update: ");
                    lastName = view.read();
                    view.write("Please, enter gender to update: ");
                    gender = view.read();
                    view.write("Please, enter age to update: ");
                    age = Integer.parseInt(view.read());
                    view.write("Please, enter company_id to update: ");
                    companyId = Integer.parseInt(view.read());
                    view.write("Please, enter salary to update: ");
                    salary = Integer.parseInt(view.read());
                    view.write("Please, enter developer_id: ");
                    id = Integer.parseInt(view.read());
                    if (checkDevelopers.IsDeveloperIdExists(id)) {
                        developerService.updateDeveloper(firstName, lastName, gender, age, companyId, salary, id);
                        view.write("Developer with id " + id + " successfully updated");
                        break;
                    } else {
                        System.out.println("Developer id doesn't exists");
                    }
                } catch (NumberFormatException e) {
                    view.write("Invalid value. Use digits");
                }
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }
}
