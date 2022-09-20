package command;

import services.DeveloperService;
import view.View;

import java.sql.SQLException;

public class GetDevs implements Command {
    public static final String GET_DEVS = "get devs";
    private final View view;
    DeveloperService developerService = new DeveloperService();

    public GetDevs(View view) {
        this.view = view;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(GET_DEVS);
    }

    @Override
    public void execute() {
        try {
            view.write("List of all developers is:\n" + developerService.developersList());
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }
}
