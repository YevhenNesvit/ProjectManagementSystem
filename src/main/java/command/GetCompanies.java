package command;

import services.CompanyService;
import view.View;

import java.sql.SQLException;

public class GetCompanies implements Command {
    public static final String GET_COMPANIES = "get companies";
    private final View view;
    CompanyService companyService = new CompanyService();

    public GetCompanies(View view) {
        this.view = view;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(GET_COMPANIES);
    }

    @Override
    public void execute() {
        try {
            view.write("List of all companies is:\n" + companyService.companiesList());
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }
}
