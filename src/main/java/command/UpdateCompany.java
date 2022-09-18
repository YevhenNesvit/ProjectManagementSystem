package command;

import repository.CompanyRepository;
import view.View;

import java.sql.SQLException;

public class UpdateCompany implements Command {
    public static final String UPDATE_COMPANY = "update company";
    private final View view;
    CompanyRepository companyRepository = new CompanyRepository();

    public UpdateCompany(View view) {
        this.view = view;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(UPDATE_COMPANY);
    }

    @Override
    public void execute() {
        String columnName;
        String newValue;
        int id;
        while (true) {
            try {
                view.write("Please, enter columnName to update: name or country ");
                columnName = view.read();
                view.write("Please, enter new value: ");
                newValue = view.read();
                view.write("Please, enter company_id: ");
                id = Integer.parseInt(view.read());
                break;
            } catch (NumberFormatException e) {
                view.write("Invalid value. Use digits");
            }
        }
        try {
            companyRepository.updateCompany(columnName, newValue, id);
            view.write("Company with id " + id + " successfully updated");
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }
}
