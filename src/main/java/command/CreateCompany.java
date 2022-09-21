package command;

import services.CompanyService;
import view.View;

import java.sql.SQLException;

public class CreateCompany implements Command {
    public static final String CREATE_COMPANY = "create company";
    private final View view;
    CompanyService companyService = new CompanyService();

    public CreateCompany(View view) {
        this.view = view;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(CREATE_COMPANY);
    }

    @Override
    public void execute() {
        int id;
        String name;
        String country;
        while (true) {
            try {
                view.write("Please, enter company_id of new company: ");
                id = Integer.parseInt(view.read());
                view.write("Please, enter name of new company: ");
                name = view.read();
                view.write("Please, enter country of new company: ");
                country = view.read();
                break;
            } catch (NumberFormatException e) {
                view.write("Invalid value. Use digits");
            }
        }
        try {
            companyService.createCompany(id, name, country);
            view.write("Company with id " + id + " successfully created");
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }
}
