package command;

import repository.DeveloperRepository;
import view.View;

import java.sql.SQLException;

public class DeleteDeveloper implements Command{
    public static final String DELETE_DEVELOPER = "delete dev";
    private final View view;
    DeveloperRepository developerRepository = new DeveloperRepository();

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
            developerRepository.deleteDeveloper(id);
            view.write("Developer with id " + id + " successfully deleted");
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }
}
