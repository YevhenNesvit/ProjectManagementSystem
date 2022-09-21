package command;

import services.DeveloperService;
import view.View;

import java.sql.SQLException;

public class CreateDeveloper implements Command {
    public static final String CREATE_DEVELOPER = "create dev";
    private final View view;
    DeveloperService developerService = new DeveloperService();

    public CreateDeveloper(View view) {
        this.view = view;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(CREATE_DEVELOPER);
    }

    @Override
    public void execute() {
        int id;
        String firstName;
        String lastName;
        String gender;
        int age;
        int companyId;
        int salary;
        while (true) {
            try {
                view.write("Please, enter developer_id of new developer: ");
                id = Integer.parseInt(view.read());
                view.write("Please, enter firstName of new developer: ");
                firstName = view.read();
                view.write("Please, enter lastName of new developer: ");
                lastName = view.read();
                view.write("Please, enter gender of new developer like 'M' or 'F': ");
                gender = view.read();
                view.write("Please, enter age of new developer: ");
                age = Integer.parseInt(view.read());
                view.write("Please, enter company_id of new developer: ");
                companyId = Integer.parseInt(view.read());
                view.write("Please, enter salary of new developer: ");
                salary = Integer.parseInt(view.read());
                break;
            } catch (NumberFormatException e) {
                view.write("Invalid value. Use digits");
            }
        }
        try {
            developerService.createDeveloper(id, firstName, lastName, gender, age, companyId, salary);
            view.write("Developer with id " + id + " successfully created");
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }
}
