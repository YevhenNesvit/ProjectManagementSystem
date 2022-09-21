package command;

import services.CompanyService;
import utils.CheckCompanies;
import view.View;

import java.sql.SQLException;

public class UpdateCompany implements Command {
    public static final String UPDATE_COMPANY = "update company";
    private final View view;
    CompanyService companyService = new CompanyService();
    CheckCompanies checkCompanies = new CheckCompanies();

    public UpdateCompany(View view) {
        this.view = view;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(UPDATE_COMPANY);
    }

    @Override
    public void execute() {
        String name;
        String country;
        int id;
        try {
            while (true) {
                try {
                    view.write("Please, enter name to update: ");
                    name = view.read();
                    view.write("Please, enter country to update: ");
                    country = view.read();
                    view.write("Please, enter company_id: ");
                    id = Integer.parseInt(view.read());
                    if (checkCompanies.IsCompanyIdExists(id)) {
                        companyService.updateCompany(name, country, id);
                        view.write("Company with id " + id + " successfully updated");
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
