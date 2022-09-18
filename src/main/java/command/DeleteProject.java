package command;

import services.ProjectService;
import view.View;

import java.sql.SQLException;

public class DeleteProject implements Command {
    public static final String DELETE_PROJECT = "delete project";
    private final View view;
    ProjectService projectService = new ProjectService();

    public DeleteProject(View view) {
        this.view = view;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(DELETE_PROJECT);
    }

    @Override
    public void execute() {
        int id;
        while (true) {
            try {
                view.write("Please, enter project id to delete: ");
                id = Integer.parseInt(view.read());
                break;
            } catch (NumberFormatException e) {
                view.write("Invalid value. Use digits");
            }
        }
        try {
            projectService.deleteProject(id);
            view.write("Project with id " + id + " successfully deleted");
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }
}
