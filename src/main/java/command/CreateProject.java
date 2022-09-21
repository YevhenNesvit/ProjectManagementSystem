package command;

import services.ProjectService;
import view.View;

import java.sql.Date;
import java.sql.SQLException;

public class CreateProject implements Command {
    public static final String CREATE_PROJECT = "create project";
    private final View view;
    ProjectService projectService = new ProjectService();

    public CreateProject(View view) {
        this.view = view;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(CREATE_PROJECT);
    }

    @Override
    public void execute() {
        int id;
        String name;
        int customerId;
        int companyId;
        int cost;
        Date creationDate;
        while (true) {
            try {
                view.write("Please, enter project_id of new project: ");
                id = Integer.parseInt(view.read());
                view.write("Please, enter name of new project: ");
                name = view.read();
                view.write("Please, enter customer_id of new project: ");
                customerId = Integer.parseInt(view.read());
                view.write("Please, enter company_id of new project: ");
                companyId = Integer.parseInt(view.read());
                view.write("Please, enter cost of new project: ");
                cost = Integer.parseInt(view.read());
                view.write("Please, enter creation_date in YYYY-MM-DD format of new project: ");
                creationDate = Date.valueOf(view.read());
                break;
            } catch (NumberFormatException e) {
                view.write("Invalid value. Use digits");
            }
        }
        try {
            projectService.createProject(id, name, customerId, companyId, cost, creationDate);
            view.write("Developer with id " + id + " successfully created");
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }
}
