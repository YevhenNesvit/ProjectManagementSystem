package command;

import services.CompanyService;
import view.View;

import java.sql.SQLException;

public class DeleteCompany implements Command {
    public static final String DELETE_COMPANY = "delete company";
    private final View view;
    CompanyService companyService = new CompanyService();

    public DeleteCompany(View view) {
        this.view = view;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(DELETE_COMPANY);
    }

    @Override
    public void execute() {
        int id;
        while (true) {
            try {
                view.write("Please, enter company id to delete: ");
                id = Integer.parseInt(view.read());
                break;
            } catch (NumberFormatException e) {
                view.write("Invalid value. Use digits");
            }
        }
        try {
            companyService.deleteCompany(id);
            view.write("Company with id " + id + " successfully deleted");
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }
}
