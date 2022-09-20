package command;

import services.DeveloperService;
import view.View;

import java.sql.SQLException;

public class GetDevsById implements Command {
    public static final String GET_DEVS_BY_ID = "devs by id";
    private final View view;
    DeveloperService developerService = new DeveloperService();

    public GetDevsById(View view) {
        this.view = view;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(GET_DEVS_BY_ID);
    }

    @Override
    public void execute() {
        int id;
        while (true) {
            try {
                view.write("Please, enter developer id: ");
                id = Integer.parseInt(view.read());
                break;
            } catch (NumberFormatException e) {
                view.write("Invalid value. Use digits");
            }
        }
        try {
            view.write("For id " + id + " developer is:\n" + developerService.developerById(id));
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }
}
