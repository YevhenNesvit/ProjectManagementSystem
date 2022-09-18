package command;

import services.DeveloperService;
import view.View;

import java.sql.SQLException;

public class UpdateDeveloper implements Command {
    public static final String UPDATE_DEVELOPER = "update developer";
    private final View view;
    DeveloperService developerService = new DeveloperService();

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
        while (true) {
            try {
                view.write("Please, enter columnName to update: first_name, last_name, company_id or salary ");
                columnName = view.read();
                view.write("Please, enter new value: ");
                newValue = view.read();
                view.write("Please, enter developer_id: ");
                id = Integer.parseInt(view.read());
                break;
            } catch (NumberFormatException e) {
                view.write("Invalid value. Use digits");
            }
        }
        try {
            developerService.updateDeveloper(columnName, newValue, id);
            view.write("Developer with id " + id + " successfully updated");
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }
}
