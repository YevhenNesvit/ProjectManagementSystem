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
        String columnName;
        String newValue;
        int id;
        try {
            while (true) {
                try {
                    view.write("Please, enter columnName to update: first_name, last_name, company_id or salary ");
                    columnName = view.read();
                    view.write("Please, enter new value: ");
                    newValue = view.read();
                    view.write("Please, enter developer_id: ");
                    id = Integer.parseInt(view.read());
                    if (checkDevelopers.IsDeveloperIdExists(id)) {
                        developerService.updateDeveloper(columnName, newValue, id);
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
