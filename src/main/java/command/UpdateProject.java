package command;

import repository.ProjectRepository;
import view.View;

import java.sql.SQLException;

public class UpdateProject implements Command{
    public static final String UPDATE_PROJECT = "update project";
    private final View view;
    ProjectRepository projectRepository = new ProjectRepository();

    public UpdateProject(View view) {
        this.view = view;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(UPDATE_PROJECT);
    }

    @Override
    public void execute() {
        String columnName;
        String newValue;
        int id;
        while (true) {
            try {
                view.write("Please, enter columnName to update: name, customer_id, company_id, cost or creation_date: ");
                columnName = view.read();
                view.write("Please, enter new value: ");
                newValue = view.read();
                view.write("Please, enter project_id: ");
                id = Integer.parseInt(view.read());
                break;
            } catch (NumberFormatException e) {
                view.write("Invalid value. Use digits");
            }
        }
        try {
            projectRepository.updateProject(columnName, newValue, id);
            view.write("Project with id " + id + " successfully updated");
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }
}
