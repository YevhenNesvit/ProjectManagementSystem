package command;

import repository.DeveloperRepository;
import view.View;

import java.sql.SQLException;

public class GetDevsByProject implements Command {
    public static final String GET_DEVS_BY_PROJECT = "devs by project";
    private final View view;
    DeveloperRepository developerRepository = new DeveloperRepository();

    public GetDevsByProject(View view) {
        this.view = view;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(GET_DEVS_BY_PROJECT);
    }

    @Override
    public void execute() {
        int id;
        while (true) {
            try {
                view.write("Please, enter project id: ");
                id = Integer.parseInt(view.read());
                break;
            } catch (NumberFormatException e) {
                view.write("Invalid value. Use digits");
            }
        }
        try {
            view.write("For project with id " + id + " list of developers is:\n" + developerRepository.developersByProjectId(id));
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }
}
