package command;

import services.CompanyService;
import utils.CheckCompanies;
import view.View;

import java.sql.SQLException;

public class DeleteCompany implements Command {
    public static final String DELETE_COMPANY = "delete company";
    private final View view;
    CompanyService companyService = new CompanyService();
    CheckCompanies checkCompanies = new CheckCompanies();

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
        try {
            while (true) {
                try {
                    view.write("Please, enter company id to delete: ");
                    id = Integer.parseInt(view.read());
                    if (checkCompanies.IsCompanyIdExists(id)) {
                        companyService.deleteCompany(id);
                        view.write("Company with id " + id + " successfully deleted");
                        break;
                    } else {
                        System.out.println("Company id doesn't exists");
                    }
                } catch (NumberFormatException e) {
                    view.write("Invalid value. Use digits");
                }
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }
}
