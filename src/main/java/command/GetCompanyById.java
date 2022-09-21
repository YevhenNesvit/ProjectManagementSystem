package command;

import services.CompanyService;
import view.View;

import java.sql.SQLException;

public class GetCompanyById implements Command {
    public static final String GET_COMPANY_BY_ID = "company by id";
    private final View view;
    CompanyService companyService = new CompanyService();

    public GetCompanyById(View view) {
        this.view = view;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(GET_COMPANY_BY_ID);
    }

    @Override
    public void execute() {
        int id;
        while (true) {
            try {
                view.write("Please, enter company id: ");
                id = Integer.parseInt(view.read());
                break;
            } catch (NumberFormatException e) {
                view.write("Invalid value. Use digits");
            }
        }
        try {
            view.write("For id " + id + " company is:\n" + companyService.companyById(id));
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }
}
