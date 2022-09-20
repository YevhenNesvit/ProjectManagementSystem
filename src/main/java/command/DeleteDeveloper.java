package command;

import services.CheckDevelopers;
import services.DeveloperService;
import view.View;

import java.sql.SQLException;

public class DeleteDeveloper implements Command {
    public static final String DELETE_DEVELOPER = "delete dev";
    private final View view;
    DeveloperService developerService = new DeveloperService();
    CheckDevelopers checkDevelopers = new CheckDevelopers();

    public DeleteDeveloper(View view) {
        this.view = view;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(DELETE_DEVELOPER);
    }

    @Override
    public void execute() {
        int id;
        try {
            while (true) {
                try {
                    view.write("Please, enter developer id to delete: ");
                    id = Integer.parseInt(view.read());
                    if (checkDevelopers.IsDeveloperIdExists(id)) {
                        developerService.deleteDeveloper(id);
                        view.write("Developer with id " + id + " successfully deleted");
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
