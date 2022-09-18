package command;

import services.DeveloperService;
import view.View;

import java.sql.SQLException;

public class DeleteDeveloper implements Command{
    public static final String DELETE_DEVELOPER = "delete dev";
    private final View view;
    DeveloperService developerService = new DeveloperService();

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
        while (true) {
            try {
                view.write("Please, enter developer id to delete: ");
                id = Integer.parseInt(view.read());
                break;
            } catch (NumberFormatException e) {
                view.write("Invalid value. Use digits");
            }
        }
        try {
            developerService.deleteDeveloper(id);
            view.write("Developer with id " + id + " successfully deleted");
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }
}
