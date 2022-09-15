package command;

import repository.DeveloperRepository;
import view.View;

import java.sql.SQLException;

public class GetDevelopers implements Command {
    public static final String GET_DEVELOPERS = "get developers";
    private final View view;
    DeveloperRepository developerRepository = new DeveloperRepository();

    public GetDevelopers(View view) {
        this.view = view;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(GET_DEVELOPERS);
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
