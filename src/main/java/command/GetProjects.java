package command;

import repository.ProjectRepository;
import view.View;

import java.sql.SQLException;

public class GetProjects implements Command {
    public static final String GET_PROJECTS = "get projects";
    private final View view;
    ProjectRepository projectRepository = new ProjectRepository();

    public GetProjects(View view) {
        this.view = view;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(GET_PROJECTS);
    }

    @Override
    public void execute() {
        try {
            view.write("List of projects is:\n" + projectRepository.projectsList());
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }
}
